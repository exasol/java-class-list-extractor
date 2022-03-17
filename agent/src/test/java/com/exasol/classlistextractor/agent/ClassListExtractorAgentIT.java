package com.exasol.classlistextractor.agent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ClassListExtractorAgentIT {
    private static final String HELLO_WORLD_CLASS = "src/test/java/com/exasol/classlistextractor/agent/HelloWorld.java";
    private static final Path AGENT_JAR = Path.of("target/java-class-list-extractor-agent.jar");

    @Test
    void testGetClassList(@TempDir final Path tempDir) throws IOException, InterruptedException {
        runCommand(new String[] { "javac", Path.of(HELLO_WORLD_CLASS).toString(), "-d", tempDir.toString() });
        final SimpleServer simpleServer = new SimpleServer();
        new Thread(simpleServer).start();
        final Path coverage = Path.of("target/jacoco-it-nested.exec").toAbsolutePath();
        runCommand(new String[] { "java", "-XX:DumpLoadedClassList=/tmp/classes.lst",
                "-javaagent:target/jacoco-agent/org.jacoco.agent-runtime.jar=destfile=" + coverage,
                "-javaagent:" + AGENT_JAR + "=localhost:" + simpleServer.getPort(), "-cp", tempDir.toString(),
                "com/exasol/classlistextractor/agent/HelloWorld" });
        simpleServer.stop();
        assertThat(simpleServer.getResult(), containsString("com/exasol/classlistextractor/agent/HelloWorld"));
    }

    private void runCommand(final String[] args) throws IOException, InterruptedException {
        final Process process = Runtime.getRuntime().exec(args);
        final int exitCode = process.waitFor();
        System.out.println(new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
        System.out.println(new String(process.getErrorStream().readAllBytes(), StandardCharsets.UTF_8));
        if (exitCode != 0) {
            throw new IllegalStateException("Command had exit code != 0: " + String.join(" ", args));
        }
    }

}