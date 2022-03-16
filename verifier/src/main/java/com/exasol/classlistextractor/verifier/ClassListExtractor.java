package com.exasol.classlistextractor.verifier;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.exasol.bucketfs.Bucket;
import com.exasol.bucketfs.BucketAccessException;
import com.exasol.errorreporting.ExaError;

/**
 * This class captures the classes loaded the JVM of a UDF.
 */
public class ClassListExtractor implements AutoCloseable {
    private static final String AGENT_JAR = "java-class-list-extractor-agent.jar";
    private final String agentInBucket;
    private final InetSocketAddress inDbServerAddress;
    private final ClassListServer server;
    private boolean getJvmOptionsWasCalled = false;

    /**
     * Create a new instance of {@link ClassListExtractor}.
     * 
     * @param bucket         bucket for uploading an agent used for extracting the class list
     * @param serviceExposer callback that makes a local TCP service available inside the database
     */
    public ClassListExtractor(final Bucket bucket, final LocalServiceExposer serviceExposer) {
        uploadAgent(bucket);
        this.server = new ClassListServer();
        this.inDbServerAddress = serviceExposer.exposeLocalServiceToDatabase(this.server.getPort());
        this.agentInBucket = "/buckets/" + bucket.getBucketFsName() + "/" + bucket.getBucketName() + "/" + AGENT_JAR;
    }

    /**
     * Get the JVM options for that must be added to the script.
     * 
     * @return jvm options
     */
    public String[] getJvmOptions() {
        this.getJvmOptionsWasCalled = true;
        return new String[] {
                "-javaagent:" + this.agentInBucket + "=" + this.inDbServerAddress.getHostName() + ":"
                        + this.inDbServerAddress.getPort(), //
                "-XX:DumpLoadedClassList=/tmp/classes.lst" };
    }

    /**
     * Capture the class list of a UDF call.
     * <p>
     * The query you want to analyze must involve a script that contains the JVM options provided by
     * {@link #getJvmOptions()}. To make sure that you don't forget this, this method will throw an exception if you did
     * not call {@link #getJvmOptions()} before this method.
     * </p>
     * 
     * @param runnable function that performs the UDF call
     * @return list of captured classes
     */
    public List<String> capture(final CallbackWithQueryToScript runnable) {
        assertGetJvmOptionsWasCalled();
        this.server.clear();
        runCallback(runnable);
        final List<String> classList = this.server.getClassList();
        if (classList.isEmpty()) {
            throw new IllegalStateException(ExaError.messageBuilder("E-JCLE-VF-10")
                    .message("Something went wrong with extracting the class list, since no classes were captured.")
                    .mitigation(
                            "Check that the function passed to capture runs a query that involves the UDF / VirtualSchema you want to analyze.")
                    .mitigation("Make sure you added the JVM options to the script definition.").toString());
        }
        return classList;
    }

    private void runCallback(final CallbackWithQueryToScript runnable) {
        try {
            runnable.run();
        } catch (final Exception exception) {
            throw new IllegalStateException(
                    ExaError.messageBuilder("E-JCLE-VF-17").message("Exception in capture function.").toString(),
                    exception);
        }
    }

    private void assertGetJvmOptionsWasCalled() {
        if (!this.getJvmOptionsWasCalled) {
            throw new IllegalStateException(ExaError.messageBuilder("E-JCLE-VF-11")
                    .message("Missing call to getJvmOptions().")
                    .mitigation(
                            "Make sure that you add the JVM options provided by getJvmOptions to your script definition.")
                    .toString());
        }
    }

    private void uploadAgent(final Bucket bucket) {
        try {
            bucket.uploadInputStream(this::getAgentJarAsStream, AGENT_JAR);
        } catch (final BucketAccessException | TimeoutException exception) {
            throw new IllegalStateException(ExaError.messageBuilder("E-JCLE-VF-2")
                    .message("Failed to upload the agent jar to BucketFS.").toString(), exception);
        }
    }

    private InputStream getAgentJarAsStream() {
        final InputStream stream = getClass().getClassLoader().getResourceAsStream(AGENT_JAR);
        if (stream == null) {
            throw new IllegalStateException(ExaError.messageBuilder("F-JCLE-VF-1")
                    .message("Could not access agent jar " + AGENT_JAR + ".")
                    .mitigation("The jar should be bundled with this package. "
                            + "If this happens in production please report an issue. "
                            + "If it happens when testing the verifier itself make sure you ran mvn package before.")
                    .toString());
        }
        return stream;
    }

    @Override
    public void close() {
        this.server.close();
    }

    /**
     * Interface for a method that runs a query to a script, that's class loadig you want to analyze with
     * {@link #capture(CallbackWithQueryToScript)}.
     */
    @FunctionalInterface
    public interface CallbackWithQueryToScript {
        /**
         * Method that runs a query to a script, that's class loadig you want to analyze with
         * {@link #capture(CallbackWithQueryToScript)}.
         *
         * @throws Exception if something goes wrong
         */
        @SuppressWarnings("java:S112") // generic exception is intended since it's used in test-code
        void run() throws Exception;
    }
}
