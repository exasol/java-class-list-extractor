package com.exasol.classlistextractor.agent;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class SimpleServer implements Runnable {
    private final ServerSocket serverSocket;
    private final StringBuilder result = new StringBuilder();
    private boolean running = true;

    SimpleServer() throws IOException {
        this.serverSocket = new ServerSocket(0);
    }

    int getPort() {
        return this.serverSocket.getLocalPort();
    }

    void stop() throws IOException {
        this.running = false;
        this.serverSocket.close();
    }

    String getResult() {
        return this.result.toString();
    }

    @Override
    public void run() {
        while (this.running) {
            try {
                final Socket client = this.serverSocket.accept();
                this.result.append(new String(client.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
            } catch (final IOException exception) {
                if (!exception.getMessage().equals("Socket closed"))
                    throw new UncheckedIOException("Failed to read from client.", exception);
            }
        }
    }
}
