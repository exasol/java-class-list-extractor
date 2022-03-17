# Java Class List Extractor

[![Build Status](https://github.com/exasol/java-class-list-extractor/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/java-class-list-extractor/actions/workflows/ci-build.yml)

The Java Class List Extractor is a tool that helps you to capture a list of loaded classed from Java UDFs / AdapterScripts in an Exasol database. The class list is required for improving the start-up performance of Java UDF using [java-udf-startup-time-improver](https://github.com/exasol/java-udf-startup-time-improver/).

This tool consists of two parts:

* The `ClassListExtractor`
* The `ClassListVerifier`

The extractor captures the class list of a UDF run. The verifier checks that this class list is embedded into the JAR archive of the application. If it's not it breaks the build and writes the expected class list to `target/expeced-classes.lst`. From there you can copy it to your resources and restart the build.

## What to Optimize for

The class list determines which classes will be preloaded. This can reduce the start-up time of the JVM. However, if you preload many classes that are not used at runtime that's an overhead. So you should make a conscious decision which test cases you want to analyze. Analyzing all test-cases (including lots of tests for unlikely edge cases) gives a non-ideal result.

## Usage

* Add this project as maven test dependency.
* Create a ClassListExtractor:
    ```
    final ClassListExtractor extractor = new ClassListExtractor(EXASOL.getDefaultBucket(), port -> new InetSocketAddress(EXASOL.getHostIp() + "", port));
    ```
* Make sure that you close the extractor. For example in `afterAll()` if you created it in `beforeAll()`:
  ```
  @AfterAll
  static void afterAll() throws SQLException {
    statement.close();
    connection.close();
    extractor.close();
  }
  ```
* Add the JVM options provided by `extractor.getJvmOptions()` to your UDF or Adapter Script definitions.

  If you use the [test-db-builder-java](https://github.com/exasol/test-db-builder-java), you can use the following snippet:
  ```
  final ExasolObjectFactory objectFactory = new ExasolObjectFactory(connection,
     ExasolObjectConfiguration.builder().withJvmOptions(extractor.getJvmOptions()).build());
  ```
  If you also use [udf-debugging-java](https://github.com/exasol/udf-debugging-java/), use:
  ```
  final List<String> jvmOptions = new ArrayList<>(Arrays.asList(udfTestSetup.getJvmOptions()));
  jvmOptions.addAll(Arrays.asList(extractor.getJvmOptions()));
  final ExasolObjectFactory objectFactory = new ExasolObjectFactory(connection,
         ExasolObjectConfiguration.builder().withJvmOptions(jvmOptions.toArray(String[]::new)).build());
  ```
* Run the queries you want to optimize inside of `extractor.capture()`:
  ```
  final List<String> classList = extractor.capture(() -> {
     try (final ResultSet result = statement.executeQuery("SELECT TEST.MY_UDF() AS RES")) {
       result.next();
       assertThat(result.getString("RES"), equalTo("Hello Java World!"));
     }
  });
  ```
* Add the verifier:
  ```
  new ClassListVerifier()
    .verifyClassListFile(classList, Path.of("target/my-app.jar"));
  ```

If you've multiple queries that you want to optimize for, you can either run all of them in the same `capture()` or run `capture()` multiple times. In the second case you have to take care for merging the generated class lists. We recommend using a `Set` for that task. That makes sure that there are no duplicate entries.

## Additional Information

* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)