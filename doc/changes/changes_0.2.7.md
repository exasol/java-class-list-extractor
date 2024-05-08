# Java Class List Extractor 0.2.7, released 2024-05-08

Code name: Sort class list

## Summary

This release sorts the class list alphabetically that is written to disk when verification fails. This simplifies comparison with the previous class list.

## Features

* #26: Sort class list alphabetically

## Dependency Updates

### Java Class List Extractor (aggregator Pom)

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.1` to `2.0.3`
* Updated `com.exasol:project-keeper-maven-plugin:4.1.0` to `4.3.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.12.1` to `3.13.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.1` to `3.1.2`
* Removed `org.apache.maven.plugins:maven-gpg-plugin:3.1.0`
* Removed `org.apache.maven.plugins:maven-javadoc-plugin:3.6.3`
* Removed `org.apache.maven.plugins:maven-source-plugin:3.2.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.11` to `0.8.12`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.10.0.2594` to `3.11.0.3922`
* Removed `org.sonatype.plugins:nexus-staging-maven-plugin:1.6.13`

### Class List Extractor Agent

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.0` to `2.0.2`
* Updated `com.exasol:project-keeper-maven-plugin:4.1.0` to `4.3.0`
* Updated `org.apache.maven.plugins:maven-assembly-plugin:3.6.0` to `3.7.1`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.12.1` to `3.13.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.1` to `3.1.2`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.11` to `0.8.12`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.10.0.2594` to `3.11.0.3922`

### Class List Verifier

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.0.1` to `7.1.0`
* Updated `com.exasol:test-db-builder-java:3.5.3` to `3.5.4`
* Updated `com.exasol:udf-debugging-java:0.6.12` to `0.6.13`
* Updated `org.slf4j:slf4j-jdk14:2.0.12` to `2.0.13`
* Updated `org.testcontainers:junit-jupiter:1.19.6` to `1.19.7`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.0` to `2.0.2`
* Updated `com.exasol:project-keeper-maven-plugin:4.1.0` to `4.3.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.12.1` to `3.13.0`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:3.1.0` to `3.2.2`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.11` to `0.8.12`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.10.0.2594` to `3.11.0.3922`
