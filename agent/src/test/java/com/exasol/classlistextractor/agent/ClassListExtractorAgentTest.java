package com.exasol.classlistextractor.agent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ClassListExtractorAgentTest {

    @Test
    void testMissingArgument() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ClassListExtractorAgent.premain(null));
        assertThat(exception.getMessage(), equalTo(
                "E-JCLE-AG-1: Invalid argument string '<missing>'. The arguments must have the format host:port."));
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "abc", "localhost:1abc", "a:b:c" })
    void testInvalidArgument(final String argument) {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ClassListExtractorAgent.premain(argument));
        assertThat(exception.getMessage(), equalTo("E-JCLE-AG-1: Invalid argument string '" + argument
                + "'. The arguments must have the format host:port."));
    }
}