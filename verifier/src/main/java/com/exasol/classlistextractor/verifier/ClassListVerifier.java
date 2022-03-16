package com.exasol.classlistextractor.verifier;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.exasol.errorreporting.ExaError;

/**
 * This class verifies that a jar file contains an {@code classes.lst} file and validates that it contains the expected
 * classes.
 */
public class ClassListVerifier {
    private static final String CLASSES_LIST_FILE_NAME = "classes.lst";

    /**
     * Verify that a jar file contains an {@code classes.lst} file and validates that it contains the expected classes.
     * 
     * @param classList list of expected
     * @param jarFile   jar file to verify
     */
    public void verifyClassListFile(final Collection<String> classList, final Path jarFile) {
        if (!Files.exists(jarFile)) {
            throw new AssertionError(
                    ExaError.messageBuilder("E-JCLE-V-13").message("Could not find jar file {{jar file}}.", jarFile)
                            .mitigation("Usually this can be solved by running 'mvn package'.").toString());
        }
        final Optional<String> classListFile = findZipFileInJar(jarFile);
        if (classListFile.isEmpty()) {
            handleEmptyClassList(classList, jarFile);
        } else {
            final Set<String> classesInFile = Arrays.stream(classListFile.get().split("\n")).map(String::trim)
                    .collect(Collectors.toSet());
            if (!classesInFile.equals(new HashSet<>(classList))) {
                final Path generatedFilePath = writeClassListToTarget(classList);
                throw new AssertionError(ExaError.messageBuilder("E-JCLE-V-16")
                        .message("Found outdated " + CLASSES_LIST_FILE_NAME + " in the jar file {{jar file}}.", jarFile)
                        .mitigation(
                                "You can fix that by copying the generated file from {{generated class file path}} to src/main/resources/"
                                        + CLASSES_LIST_FILE_NAME
                                        + ": \ncp {{generated class file path|uq}} src/main/resources/")
                        .parameter("generated class file path", generatedFilePath).toString());
            }
        }
    }

    private void handleEmptyClassList(final Collection<String> classList, final Path jarFile) {
        final Path generatedFilePath = writeClassListToTarget(classList);
        throw new AssertionError(ExaError.messageBuilder("E-JCLE-V-14")
                .message("Could not find " + CLASSES_LIST_FILE_NAME + " in the jar file {{jar file}}.", jarFile)
                .mitigation(
                        "You can fix that by copying the generated file from {{generated class file path}} to src/main/resources/"
                                + CLASSES_LIST_FILE_NAME
                                + ": \ncp {{generated class file path|uq}} src/main/resources/")
                .parameter("generated class file path", generatedFilePath).toString());
    }

    private Path writeClassListToTarget(final Collection<String> classList) {
        final Path generatedFilePath = Path.of("target/generated-" + CLASSES_LIST_FILE_NAME);
        try {
            Files.writeString(generatedFilePath, String.join(System.lineSeparator(), classList));
            return generatedFilePath;
        } catch (final IOException exception) {
            throw new IllegalStateException(ExaError.messageBuilder("E-JCLE-V-15")
                    .message("Failed to write extracted class list to {{target path}}.", generatedFilePath).toString(),
                    exception);
        }
    }

    private Optional<String> findZipFileInJar(final Path jarFile) {
        try (final FileInputStream inputStream = new FileInputStream(jarFile.toFile());
                final BufferedInputStream bufferedStream = new BufferedInputStream(inputStream);
                final ZipInputStream zipInputStream = new ZipInputStream(bufferedStream)) {
            ZipEntry entry = zipInputStream.getNextEntry();
            while (entry != null) {
                if (CLASSES_LIST_FILE_NAME.equals(entry.getName())) {
                    return Optional.of(new String(zipInputStream.readAllBytes(), StandardCharsets.UTF_8));
                }
                entry = zipInputStream.getNextEntry();
            }
            return Optional.empty();
        } catch (final IOException exception) {
            throw new UncheckedIOException(ExaError.messageBuilder("E-JCLE-V-12")
                    .message("Error while reading jar-file contents for checking if it contains the correct "
                            + CLASSES_LIST_FILE_NAME + " file.")
                    .toString(), exception);
        }
    }
}
