package com.exasol.classlistextractor.verifier;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.exasol.errorreporting.ExaError;

/**
 * This class verifies that a jar file contains a {@code classes.lst} file and validates that it contains the expected
 * classes.
 */
// [impl->dsn~class-list-verifier~1]
public class ClassListVerifier {
    private static final Logger LOGGER = Logger.getLogger(ClassListVerifier.class.getName());
    private static final String CLASSES_LIST_FILE_NAME = "classes.lst";
    private static final String GENERATED_CLASS_LIST_PATH = "target/generated-" + CLASSES_LIST_FILE_NAME;

    private final List<Pattern> ignoreInDiffPattern;

    /**
     * Create a new instance of {@link ClassListVerifier}.
     * 
     * @param ignoreInDiffPattern patterns that match class names that will be ignored when verifying the class list,
     *                            e.g. {@code "/util/concurrent/.*"}.
     */
    public ClassListVerifier(final List<Pattern> ignoreInDiffPattern) {
        this.ignoreInDiffPattern = ignoreInDiffPattern;
    }

    /**
     * Verify that a jar file contains a {@code classes.lst} file and validates that it contains the expected classes.
     * 
     * @param classList list of expected
     * @param jarFile   jar file to verify
     */
    public void verifyClassListFile(final Collection<String> classList, final Path jarFile) {
        if (!Files.exists(jarFile)) {
            throw new AssertionError(
                    ExaError.messageBuilder("E-JCLE-VF-13").message("Could not find jar file {{jar file}}.", jarFile)
                            .mitigation("Usually this can be solved by running 'mvn package'.").toString());
        }
        final SortedSet<String> sortedClassList = new TreeSet<>(classList);
        final Optional<String> classListFile = findClassListInJar(jarFile);
        if (classListFile.isEmpty()) {
            handleEmptyClassList(sortedClassList, jarFile);
        } else {
            final Path generatedFilePath = writeClassListToTarget(sortedClassList);
            LOGGER.info(() -> "Wrote generated class list to " + generatedFilePath);
            final Set<String> classesInFile = Arrays.stream(classListFile.get().split("\n")).map(String::trim)
                    .collect(Collectors.toSet());
            if (classListsAreDifferent(sortedClassList, classesInFile)) {
                throw new AssertionError(ExaError.messageBuilder("E-JCLE-VF-16")
                        .message("Found outdated " + CLASSES_LIST_FILE_NAME + " in the jar file {{jar file}}.", jarFile)
                        .mitigation("You can fix that by downloading the generated file " + GENERATED_CLASS_LIST_PATH
                                + " from the GitHub action summary and copy it to to 'src/main/resources/"
                                + CLASSES_LIST_FILE_NAME + "'")
                        .toString());
            }
        }
    }

    private boolean classListsAreDifferent(final Collection<String> classList, final Set<String> classesInFile) {
        return !applyIgnores(classesInFile).equals((applyIgnores(classList)));
    }

    // [impl->dsn-fuzzy-class-list-matching~1]
    private Set<String> applyIgnores(final Collection<String> classList) {
        return classList.stream().filter(
                className -> this.ignoreInDiffPattern.stream().noneMatch(pattern -> pattern.matcher(className).find()))
                .collect(Collectors.toSet());
    }

    private void handleEmptyClassList(final SortedSet<String> classList, final Path jarFile) {
        final Path generatedFilePath = writeClassListToTarget(classList);
        throw new AssertionError(ExaError.messageBuilder("E-JCLE-VF-14")
                .message("Could not find " + CLASSES_LIST_FILE_NAME + " in the jar file {{jar file}}.", jarFile)
                .mitigation(
                        "You can fix that by copying the generated file from {{generated class file path}} to 'src/main/resources/"
                                + CLASSES_LIST_FILE_NAME
                                + "':\ncp {{generated class file path|u}} src/main/resources/classes.lst")
                .parameter("generated class file path", generatedFilePath).toString());
    }

    private Path writeClassListToTarget(final SortedSet<String> classList) {
        final Path generatedFilePath = Path.of(GENERATED_CLASS_LIST_PATH).toAbsolutePath();
        try {
            Files.writeString(generatedFilePath, String.join(System.lineSeparator(), classList));
            return generatedFilePath;
        } catch (final IOException exception) {
            throw new IllegalStateException(ExaError.messageBuilder("E-JCLE-VF-15")
                    .message("Failed to write extracted class list to {{target path}}.", generatedFilePath).toString(),
                    exception);
        }
    }

    private Optional<String> findClassListInJar(final Path jarFile) {
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
            throw new UncheckedIOException(ExaError.messageBuilder("E-JCLE-VF-12").message(
                    "Error while reading jar-file {{jar file}} contents for checking if it contains the correct "
                            + CLASSES_LIST_FILE_NAME + " file.",
                    jarFile).toString(), exception);
        }
    }
}
