package com.exasol.classlistextractor.verifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ClassListServerTest {

    @Test
    void testWithSingleClient() throws IOException {
        try (final ClassListServer classListServer = new ClassListServer()) {
            writeToServer(classListServer, "test\nother");
            assertThat(classListServer.getClassList(), containsInAnyOrder("test", "other"));
        }
    }

    @Test
    void testWithNoClient() throws IOException {
        try (final ClassListServer classListServer = new ClassListServer()) {
            assertThat(classListServer.getClassList(), containsInAnyOrder());
        }
    }

    @Test
    void testWithManyConnections() throws IOException {
        try (final ClassListServer classListServer = new ClassListServer()) {
            final List<String> expected = new ArrayList<>();
            for (int counter = 0; counter < 1_000; counter++) {
                final String line = "test" + counter;
                expected.add(line);
                writeToServer(classListServer, line);
            }
            assertThat(classListServer.getClassList(), containsInAnyOrder(expected.toArray(String[]::new)));
        }
    }

    @Test
    void testWriteLargeList() throws IOException {
        try (final ClassListServer classListServer = new ClassListServer()) {
            final List<String> expected = new ArrayList<>();
            try (final SocketChannel socketChannel = SocketChannel
                    .open(new InetSocketAddress("127.0.0.1", classListServer.getPort()))) {
                for (int counter = 0; counter < 1_000; counter++) {
                    final String line = "test" + counter;
                    expected.add(line);
                    socketChannel.write(ByteBuffer.wrap((line + "\n").getBytes()));
                }
            }
            assertThat(classListServer.getClassList(), containsInAnyOrder(expected.toArray(String[]::new)));
        }
    }

    @Test
    void testClear() throws IOException {
        try (final ClassListServer classListServer = new ClassListServer()) {
            writeToServer(classListServer, "test");
            classListServer.clear();
            writeToServer(classListServer, "other");
            assertThat(classListServer.getClassList(), contains("other"));
        }
    }

    private void writeToServer(final ClassListServer classListServer, final String message) throws IOException {
        try (final SocketChannel socketChannel = SocketChannel
                .open(new InetSocketAddress("127.0.0.1", classListServer.getPort()))) {
            socketChannel.write(ByteBuffer.wrap(message.getBytes()));
        }
    }
}