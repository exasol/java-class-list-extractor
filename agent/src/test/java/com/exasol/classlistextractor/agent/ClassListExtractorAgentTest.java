package com.exasol.classlistextractor.agent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ClassListExtractorAgentTest {

    @Test
    void testMissingArgument() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ClassListExtractorAgent.premain(null));
        assertThat(exception.getMessage(), equalTo(
                "E-JCLE-AG-1: Invalid argument string '<missing>'. The arguments must have the format host:port."));
    }

    @Test
    void testInvalidArgument() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ClassListExtractorAgent.premain("abc"));
        assertThat(exception.getMessage(),
                equalTo("E-JCLE-AG-1: Invalid argument string 'abc'. The arguments must have the format host:port."));
    }
}