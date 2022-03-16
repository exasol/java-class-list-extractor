package com.exasol.classlistextractor.verifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;

import com.exasol.bucketfs.Bucket;
import com.exasol.bucketfs.BucketAccessException;

class ClassListExtractorTest {
    private static final ClassListExtractor.CallbackWithQueryToScript EMPTY_CALLBACK = () -> {
    };

    @Test
    void testMissingGetJvmOptionsClass() {
        try (final ClassListExtractor extractor = new ClassListExtractor(mock(Bucket.class), this::dummyPortExposer)) {
            final IllegalStateException exception = assertThrows(IllegalStateException.class,
                    () -> extractor.capture(EMPTY_CALLBACK));
            assertThat(exception.getMessage(), equalTo(
                    "E-JCLE-V-11: Missing call to getJvmOptions(). Make sure that you add the JVM options provided by getJvmOptions to your script definition."));
        }
    }

    @Test
    void testExceptionInCaptureFunction() {
        try (final ClassListExtractor extractor = new ClassListExtractor(mock(Bucket.class), this::dummyPortExposer)) {
            extractor.getJvmOptions();
            final IllegalStateException exceptionThrownInCallback = new IllegalStateException();
            final ClassListExtractor.CallbackWithQueryToScript throwingCallback = () -> {
                throw exceptionThrownInCallback;
            };
            final IllegalStateException exception = assertThrows(IllegalStateException.class,
                    () -> extractor.capture(throwingCallback));
            assertAll(//
                    () -> assertThat(exception.getMessage(), equalTo("E-JCLE-V-11: Exception in capture function.")), //
                    () -> assertThat(exception.getCause(), equalTo(exceptionThrownInCallback))//
            );
        }
    }

    @Test
    void testNoClassDataReported() {
        try (final ClassListExtractor extractor = new ClassListExtractor(mock(Bucket.class), this::dummyPortExposer)) {
            extractor.getJvmOptions();
            final IllegalStateException exception = assertThrows(IllegalStateException.class,
                    () -> extractor.capture(EMPTY_CALLBACK));
            assertThat(exception.getMessage(), equalTo(
                    "E-JCLE-V-10: Something went wrong with extracting the class list, since no classes were captured. Known mitigations:\n* Check that the function passed to capture runs a query that involves the UDF / VirtualSchema you want to analyze.\n* Make sure you added the JVM options to the script definition."));
        }
    }

    @Test
    void testUploadToBucketFs() throws BucketAccessException, TimeoutException {
        final Bucket bucket = mock(Bucket.class);
        new ClassListExtractor(bucket, this::dummyPortExposer).close();
        verify(bucket).uploadInputStream(any(), eq("java-class-list-extractor-agent.jar"));
    }

    @Test
    void testUploadError() throws BucketAccessException, TimeoutException {
        final Bucket bucket = mock(Bucket.class);
        doThrow(new BucketAccessException("")).when(bucket).uploadInputStream(any(), any());
        final IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> new ClassListExtractor(bucket, this::dummyPortExposer));
        assertThat(exception.getMessage(), equalTo("E-JCLE-VF-2: Failed to upload the agent jar to BucketFS."));
    }

    private InetSocketAddress dummyPortExposer(final int port) {
        return new InetSocketAddress("127.0.0.1", port);
    }
}