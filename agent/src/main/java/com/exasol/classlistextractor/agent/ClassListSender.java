package com.exasol.classlistextractor.agent;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;

import com.exasol.errorreporting.ExaError;

class ClassListSender implements Runnable {
    private final InetSocketAddress serverAddress;
    private final Path classListPath;

    ClassListSender(final InetSocketAddress serverAddress, final Path classListPath) {
        this.serverAddress = serverAddress;
        this.classListPath = classListPath;
    }

    @Override
    public void run() {
        final String classList = readClassList();
        try {
            try (final SocketChannel socketChannel = SocketChannel.open(this.serverAddress)) {
                socketChannel.write(ByteBuffer.wrap(classList.getBytes()));
            }
        } catch (final IOException exception) {
            throw new UncheckedIOException(
                    ExaError.messageBuilder("E-JCLE-AG-2").message("Failed to send classes list to socket.").toString(),
                    exception);
        }
    }

    private String readClassList() {
        try {
            return Files.readString(this.classListPath);
        } catch (final IOException exception) {
            throw new IllegalStateException(ExaError.messageBuilder("E-JCLE-AG-3")
                    .message("Failed to read class list {{path}}.", this.classListPath)
                    .mitigation("Make sure that you set the correct JVM options so that the class list is exported.")
                    .toString(), exception);
        }
    }
}
