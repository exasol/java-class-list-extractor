# Java Class List Extractor 0.2.1, released 2022-10-25

Code name: Fix vulnerabilities in dependencies

## Summary

In this release we fixed vulnerabilities in the following test dependencies:

* org.postgresql:postgresql:jar:42.3.3:test: sonatype-2022-4402
* io.netty:netty-common:jar:4.1.72.Final:test: CVE-2022-24823
* org.apache.xmlrpc:xmlrpc-common:jar:3.1.3:test: CVE-2016-5003 and CVE-2016-5002
* io.netty:netty-handler:jar:4.1.72.Final:test: sonatype-2020-0026
* com.google.protobuf:protobuf-java:jar:3.11.4:test: CVE-2022-3171 and CVE-2021-22569
* commons-codec:commons-codec:jar:1.11:test: sonatype-2012-0050
* org.apache.xmlrpc:xmlrpc-client:jar:3.1.3:test: CVE-2016-5004

## Bugfixes

* #10: Fixed vulnerabilities in dependencies

## Dependency Updates

### Class List Extractor Agent

#### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.4.1` to `1.0.0`

#### Test Dependency Updates

* Updated `org.jacoco:org.jacoco.agent:0.8.5` to `0.8.8`
* Added `org.junit.jupiter:junit-jupiter-api:5.9.1`
* Removed `org.junit.jupiter:junit-jupiter-engine:5.8.2`
* Updated `org.junit.jupiter:junit-jupiter-params:5.8.2` to `5.9.1`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:0.7.1` to `1.1.2`
* Added `com.exasol:project-keeper-maven-plugin:2.8.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.9.0` to `3.10.1`
* Updated `org.apache.maven.plugins:maven-dependency-plugin:3.2.0` to `3.3.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.0.0-M2` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0` to `3.1.0`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.2.0` to `3.2.2`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.8.1` to `2.10.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.7` to `0.8.8`
* Added `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184`
* Updated `org.sonatype.ossindex.maven:ossindex-maven-plugin:3.1.0` to `3.2.0`

### Class List Verifier

#### Compile Dependency Updates

* Updated `com.exasol:bucketfs-java:2.2.0` to `2.4.1`
* Updated `com.exasol:error-reporting-java:0.4.1` to `1.0.0`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:6.1.1` to `6.3.0`
* Updated `com.exasol:test-db-builder-java:3.3.1` to `3.4.0`
* Updated `com.exasol:udf-debugging-java:0.6.0` to `0.6.4`
* Added `org.junit.jupiter:junit-jupiter-api:5.9.1`
* Removed `org.junit.jupiter:junit-jupiter-engine:5.8.2`
* Updated `org.junit.jupiter:junit-jupiter-params:5.8.2` to `5.9.1`
* Updated `org.mockito:mockito-junit-jupiter:4.3.1` to `4.8.1`
* Updated `org.testcontainers:junit-jupiter:1.16.3` to `1.17.5`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:0.7.1` to `1.1.2`
* Added `com.exasol:project-keeper-maven-plugin:2.8.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.9.0` to `3.10.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0` to `3.1.0`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.3.1` to `3.4.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.8.1` to `2.10.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.7` to `0.8.8`
* Removed `org.projectlombok:lombok-maven-plugin:1.18.20.0`
* Added `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184`
* Updated `org.sonatype.ossindex.maven:ossindex-maven-plugin:3.1.0` to `3.2.0`
* Updated `org.sonatype.plugins:nexus-staging-maven-plugin:1.6.8` to `1.6.13`
