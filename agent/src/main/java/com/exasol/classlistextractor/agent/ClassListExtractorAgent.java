package com.exasol.classlistextractor.agent;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;

import com.exasol.errorreporting.ExaError;

/**
 * This class is a Java agent that can be attached to the JVM in a UDF. It installs a hook on shutdown of the JVM and
 * then transfers the contents of /tmp/classes.lst to a tcp socket. This agent is required since we can't access to the
 * /tmp of a UDF.
 */
public class ClassListExtractorAgent {

    private ClassListExtractorAgent() {
        // static class.
    }

    /**
     * Entrypoint of the Java agent.
     * 
     * @param argument argument string
     */
    public static void premain(final String argument) {
        final InetSocketAddress serverAddress = parseArgumentString(argument);
        Runtime.getRuntime().addShutdownHook(new Thread(new ClassListSender(serverAddress)));
    }

    private static InetSocketAddress parseArgumentString(final String argument) {
        if (argument == null) {
            throw getInvalidArgsException("<missing>");
        }
        try {
            final String[] parts = argument.split(":");
            if (parts.length != 2) {
                throw getInvalidArgsException(argument);
            }
            final int port = Integer.parseInt(parts[1]);
            return new InetSocketAddress(parts[0], port);
        } catch (final NumberFormatException exception) {
            throw getInvalidArgsException(argument);
        }
    }

    private static IllegalArgumentException getInvalidArgsException(final String agentArgs) {
        return new IllegalArgumentException(
                ExaError.messageBuilder("E-JCLE-AG-1").message("Invalid argument string {{arguments}}.", agentArgs)
                        .mitigation("The arguments must have the format host:port.").toString());
    }

    private static class ClassListSender implements Runnable {
        private final InetSocketAddress serverAddress;

        private ClassListSender(final InetSocketAddress serverAddress) {
            this.serverAddress = serverAddress;
        }

        @Override
        public void run() {
            try {
                final String classList = Files.readString(Path.of("/tmp/classes.lst"));
                try (final SocketChannel socketChannel = SocketChannel.open(this.serverAddress)) {
                    socketChannel.write(ByteBuffer.wrap(classList.getBytes()));
                }
            } catch (final IOException exception) {
                throw new UncheckedIOException(ExaError.messageBuilder("E-JCLE-AG-2")
                        .message("Failed to send classes list to socket.").toString(), exception);
            }
        }
    }
}
