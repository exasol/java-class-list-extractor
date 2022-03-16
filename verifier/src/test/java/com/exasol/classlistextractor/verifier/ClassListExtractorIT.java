package com.exasol.classlistextractor.verifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.net.InetSocketAddress;
import java.sql.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.exasol.containers.ExasolContainer;
import com.exasol.dbbuilder.dialects.exasol.*;
import com.exasol.dbbuilder.dialects.exasol.udf.UdfScript;
import com.exasol.udfdebugging.UdfTestSetup;

@Testcontainers
class ClassListExtractorIT {
    @Container
    private static final ExasolContainer<? extends ExasolContainer<?>> EXASOL = new ExasolContainer<>().withReuse(true);
    private static final String HELLO_WORLD_JAVA_UDF = "class MY_UDF {\n"
            + " static String run(ExaMetadata exa, ExaIterator ctx) throws Exception {\n"
            + "        return \"Hello Java World!\";\n" + " }\n" + "}";
    private static Connection connection;
    private static Statement statement;
    private static ClassListExtractor extractor;

    @BeforeAll
    static void beforeAll() throws SQLException {
        connection = EXASOL.createConnection();
        statement = connection.createStatement();
        final UdfTestSetup udfTestSetup = new UdfTestSetup(EXASOL.getHostIp(), EXASOL.getDefaultBucket(), connection);
        extractor = new ClassListExtractor(EXASOL.getDefaultBucket(),
                port -> new InetSocketAddress(EXASOL.getHostIp() + "", port));
        final List<String> jvmOptions = new ArrayList<>(Arrays.asList(udfTestSetup.getJvmOptions()));
        jvmOptions.addAll(Arrays.asList(extractor.getJvmOptions()));
        final ExasolObjectFactory objectFactory = new ExasolObjectFactory(connection,
                ExasolObjectConfiguration.builder().withJvmOptions(jvmOptions.toArray(String[]::new)).build());
        final ExasolSchema schema = objectFactory.createSchema("TEST");
        schema.createUdfBuilder("MY_UDF").inputType(UdfScript.InputType.SCALAR).language(UdfScript.Language.JAVA)
                .returns("VARCHAR(50) UTF8").content(HELLO_WORLD_JAVA_UDF).build();
    }

    @AfterAll
    static void afterAll() throws SQLException {
        statement.close();
        connection.close();
        extractor.close();
    }

    @Test
    void testExtraction() {
        final List<String> classList = extractor.capture(() -> {
            try (final ResultSet result = statement.executeQuery("SELECT TEST.MY_UDF() AS RES")) {
                result.next();
                assertThat(result.getString("RES"), equalTo("Hello Java World!"));
            }
        });
        assertThat(classList, hasItem("com/exasol/ExaMetadata"));
    }
}