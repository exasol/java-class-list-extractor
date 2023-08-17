package com.exasol.classlistextractor.verifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

// [utest->dsn~class-list-verifier~1]
class ClassListVerifierTest {
    @TempDir
    Path tempDir;

    @AfterEach
    void afterEach() throws IOException {
        Files.deleteIfExists(Path.of("target/generated-classes.lst"));
    }

    @Test
    void testWithValidJar() throws IOException {
        final Path jar = createJarFile(true);
        assertDoesNotThrow(() -> new ClassListVerifier(Collections.emptyList())
                .verifyClassListFile(List.of("com/exasol/ExaMetadata", "com/exasol/Test"), jar));
    }

    @Test
    // [utest->dsn-fuzzy-class-list-matching~1]
    void testWithIgnoredDiff() throws IOException {
        final Path jar = createJarFile(true);
        assertDoesNotThrow(() -> new ClassListVerifier(List.of(Pattern.compile(Pattern.quote("com/exasol/Test"))))
                .verifyClassListFile(List.of("com/exasol/ExaMetadata"), jar));
    }

    @Test
    void testWithOutdatedClassList() throws IOException {
        final Path jar = createJarFile(true);
        final ClassListVerifier verifier = new ClassListVerifier(Collections.emptyList());
        final List<String> classList = List.of("com/exasol/ExaMetadata", "com/exasol/Other");
        final AssertionError exception = assertThrows(AssertionError.class,
                () -> verifier.verifyClassListFile(classList, jar));
        assertAll(//
                () -> assertThat(exception.getMessage(),
                        allOf(startsWith("E-JCLE-VF-16: Found outdated classes.lst in the jar file"))),
                () -> assertGeneratedClassList(classList)//
        );
    }

    @Test
    // [utest->dsn-fuzzy-class-list-matching~1]
    void testWithOutdatedAndIgnoredDiff() throws IOException {
        final Path jar = createJarFile(true);
        final ClassListVerifier verifier = new ClassListVerifier(
                List.of(Pattern.compile(Pattern.quote("com/exasol/Test"))));
        final List<String> classList = List.of("com/exasol/Other");
        final AssertionError exception = assertThrows(AssertionError.class,
                () -> verifier.verifyClassListFile(classList, jar));
        assertThat(exception.getMessage(), startsWith("E-JCLE-VF-16: Found outdated classes.lst in the jar file"));
    }

    private void assertGeneratedClassList(final List<String> classList) throws IOException {
        final String generatedList = Files.readString(Path.of("target/generated-classes.lst"));
        final List<String> classesInFile = Arrays.stream(generatedList.split("\n")).map(String::trim)
                .collect(Collectors.toList());
        assertThat(classesInFile, containsInAnyOrder(classList.toArray(String[]::new)));
    }

    @Test
    void testWithMissingClassList() throws IOException {
        final Path jar = createJarFile(false);
        final ClassListVerifier verifier = new ClassListVerifier(Collections.emptyList());
        final List<String> classList = List.of("com/exasol/ExaMetadata", "com/exasol/Other");
        final AssertionError exception = assertThrows(AssertionError.class,
                () -> verifier.verifyClassListFile(classList, jar));
        assertAll(//
                () -> assertThat(exception.getMessage(),
                        startsWith("E-JCLE-VF-14: Could not find classes.lst in the jar file")),
                () -> assertGeneratedClassList(classList)//
        );
    }

    @Test
    void testWithMissingJar() {
        final Path jar = this.tempDir.resolve("test.jar");
        final ClassListVerifier verifier = new ClassListVerifier(Collections.emptyList());
        final List<String> classList = List.of("com/exasol/ExaMetadata", "com/exasol/Other");
        final AssertionError exception = assertThrows(AssertionError.class,
                () -> verifier.verifyClassListFile(classList, jar));
        assertThat(exception.getMessage(), allOf(startsWith("E-JCLE-VF-13: Could not find jar file "),
                endsWith("Usually this can be solved by running 'mvn package'.")));
    }

    private Path createJarFile(final boolean withClassList) throws IOException {
        final Path jar = this.tempDir.resolve("test.jar");
        try (final FileOutputStream fileOutputStream = new FileOutputStream(jar.toFile());
                final ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {
            zipOutputStream.putNextEntry(new ZipEntry("my-file.txt"));
            zipOutputStream.write("hello world".getBytes(StandardCharsets.UTF_8));
            zipOutputStream.closeEntry();
            if (withClassList) {
                zipOutputStream.putNextEntry(new ZipEntry("classes.lst"));
                zipOutputStream.write("com/exasol/ExaMetadata\ncom/exasol/Test".getBytes(StandardCharsets.UTF_8));
                zipOutputStream.closeEntry();
            }
        }
        return jar;
    }
}