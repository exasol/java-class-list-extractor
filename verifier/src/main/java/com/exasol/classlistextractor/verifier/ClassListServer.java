package com.exasol.classlistextractor.verifier;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import com.exasol.errorreporting.ExaError;

import lombok.Getter;

/**
 * This class receives the class list from the agent running in the UDFs via TCP.
 */
class ClassListServer implements AutoCloseable {
    private final ClientHandler clientHandler;

    /**
     * Create a new instance of {@link ClassListServer}.
     */
    public ClassListServer() {
        this.clientHandler = new ClientHandler();
    }

    /**
     * Get the port the server listens on.
     * 
     * @return port number
     */
    public int getPort() {
        return this.clientHandler.getPort();
    }

    @Override
    public void close() {
        this.clientHandler.stop();
    }

    /**
     * Clear the captured class list.
     */
    public void clear() {
        this.clientHandler.clear();
    }

    /**
     * Get the received class list.
     * <p>
     * The class list is a union (with duplicate removal) of all revieved class lists since the last call to
     * {@link #clear()}.
     * </p>
     * 
     * @return list of classes.
     */
    public List<String> getClassList() {
        return this.clientHandler.getClassList();
    }

    private static class ClientHandler implements Runnable {
        private final ServerSocket serverSocket;
        private final AtomicBoolean isRunning = new AtomicBoolean(true);
        private final ConcurrentLinkedQueue<Future<List<String>>> classListReaders = new ConcurrentLinkedQueue<>();
        @Getter
        private final int port;
        private final ServerThreadMonitor serverThreadMonitor = new ServerThreadMonitor();

        ClientHandler() {
            try {
                this.serverSocket = new ServerSocket(0);
                this.serverSocket.setSoTimeout(50);
                this.port = this.serverSocket.getLocalPort();
            } catch (final IOException exception) {
                throw new UncheckedIOException(ExaError.messageBuilder("E-JCLE-VF-3")
                        .message("Failed to start a server for retrieving the class lists of a project.").toString(),
                        exception);
            }
            final Thread thread = new Thread(this);
            thread.start();
        }

        void stop() {
            this.isRunning.set(false);
            try {
                this.serverSocket.close();
            } catch (final IOException exception) {
                throw new UncheckedIOException(ExaError.messageBuilder("E-JCLE-VF-6")
                        .message("Failed to close the server for retrieving the class lists of a project.").toString(),
                        exception);
            }
        }

        synchronized void clear() {
            waitForServerThreadToHandlePendingClients();
            this.classListReaders.clear();
        }

        List<String> getClassList() {
            final Set<String> union = new HashSet<>();
            waitForServerThreadToHandlePendingClients();
            for (final Future<List<String>> classListReader : this.classListReaders) {
                union.addAll(getResult(classListReader));
            }
            return new ArrayList<>(union);
        }

        private void waitForServerThreadToHandlePendingClients() {
            synchronized (this.serverThreadMonitor) {
                this.serverThreadMonitor.resetNoMoreClients();
                while (!this.serverThreadMonitor.isNoMoreClients()) {
                    try {
                        this.serverThreadMonitor.wait();
                    } catch (final InterruptedException exception) {
                        Thread.currentThread().interrupt();
                        throw new IllegalStateException(ExaError.messageBuilder("E-JCLE-VF-9")
                                .message("Interrupted while waiting for class list.").toString(), exception);
                    }
                }
            }
        }

        private List<String> getResult(final Future<List<String>> classListReader) {
            try {
                return classListReader.get(1, TimeUnit.SECONDS);
            } catch (final InterruptedException exception) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(
                        ExaError.messageBuilder("E-JCLE-VF-7")
                                .message("Interrupted while waiting for class list reader to finish.").toString(),
                        exception);
            } catch (final ExecutionException | TimeoutException exception) {
                throw new IllegalStateException(ExaError.messageBuilder("F-JCLE-VF-8")
                        .message("Failed to read result of class list client.").ticketMitigation().toString(),
                        exception);
            }
        }

        @Override
        public void run() {
            while (this.isRunning.get()) {
                try {
                    final Socket client = this.serverSocket.accept();
                    final CompletableFuture<List<String>> future = new CompletableFuture<List<String>>()
                            .completeAsync(() -> readFromClientBlocking(client));
                    this.classListReaders.add(future);
                } catch (final SocketTimeoutException exception) {
                    synchronized (this.serverThreadMonitor) {
                        this.serverThreadMonitor.setNoMoreClients();
                        this.serverThreadMonitor.notifyAll();
                    }
                } catch (final IOException exception) {
                    if (!exception.getMessage().equals("Socket closed")) {
                        throw new UncheckedIOException(ExaError.messageBuilder("E-JCLE-VF-4")
                                .message("Failed accept a class list client.").toString(), exception);
                    }
                }
            }
        }

        private List<String> readFromClientBlocking(final Socket client) {
            try (final InputStream inputStream = client.getInputStream()) {
                final String classList = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                return Arrays.asList(classList.split("\n"));
            } catch (final IOException exception) {
                throw new UncheckedIOException(ExaError.messageBuilder("E-JCLE-VF-5")
                        .message("Failed to read from class list client.").toString(), exception);
            }
        }

        private static class ServerThreadMonitor {
            @Getter
            private boolean noMoreClients = false;

            public void setNoMoreClients() {
                this.noMoreClients = true;
            }

            public void resetNoMoreClients() {
                this.noMoreClients = false;
            }
        }
    }
}
