package com.exasol.classlistextractor.agent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ClassListSenderTest {

    @TempDir
    Path tempDir;

    @Test
    void testSend() throws IOException {
        final SimpleServer simpleServer = new SimpleServer();
        new Thread(simpleServer).start();
        final InetSocketAddress address = new InetSocketAddress("127.0.0.1", simpleServer.getPort());
        final Path classListPath = this.tempDir.resolve("classes.lst");
        Files.writeString(classListPath, "test\nother");
        final ClassListSender sender = new ClassListSender(address, classListPath);
        sender.run();
        simpleServer.stop();
        assertThat(simpleServer.getResult(), equalTo("test\nother"));
    }

    @Test
    void testClassListDoesNotExist() {
        final InetSocketAddress address = new InetSocketAddress("127.0.0.1", 1234);
        final ClassListSender sender = new ClassListSender(address, this.tempDir.resolve("non-existing.lst"));
        final IllegalStateException exception = assertThrows(IllegalStateException.class, sender::run);
        assertThat(exception.getMessage(), startsWith("E-JCLE-AG-3: Failed to read class list"));
    }

    @Test
    void testSendFails() throws IOException {
        final InetSocketAddress address = new InetSocketAddress("127.0.0.1", findFreePort());
        final Path classListPath = this.tempDir.resolve("classes.lst");
        Files.writeString(classListPath, "test\nother");
        final ClassListSender sender = new ClassListSender(address, classListPath);
        final UncheckedIOException exception = assertThrows(UncheckedIOException.class, sender::run);
        assertThat(exception.getMessage(), equalTo("E-JCLE-AG-2: Failed to send classes list to socket."));
    }

    private int findFreePort() throws IOException {
        final ServerSocket serverSocket = new ServerSocket(0);
        final int port = serverSocket.getLocalPort();
        serverSocket.close();
        return port;
    }
}