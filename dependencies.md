<!-- @formatter:off -->
# Dependencies

## Class List Extractor Agent

### Compile Dependencies

| Dependency                | License          |
| ------------------------- | ---------------- |
| [error-reporting-java][0] | [MIT License][1] |

### Test Dependencies

| Dependency                | License                          |
| ------------------------- | -------------------------------- |
| [JUnit Jupiter API][2]    | [Eclipse Public License v2.0][3] |
| [JUnit Jupiter Params][2] | [Eclipse Public License v2.0][3] |
| [Hamcrest][4]             | [BSD License 3][5]               |
| [JaCoCo :: Agent][6]      | [Eclipse Public License 2.0][7]  |

### Plugin Dependencies

| Dependency                                              | License                           |
| ------------------------------------------------------- | --------------------------------- |
| [SonarQube Scanner for Maven][8]                        | [GNU LGPL 3][9]                   |
| [Apache Maven Toolchains Plugin][10]                    | [Apache License, Version 2.0][11] |
| [Apache Maven Compiler Plugin][12]                      | [Apache-2.0][11]                  |
| [Apache Maven Enforcer Plugin][13]                      | [Apache-2.0][11]                  |
| [Maven Flatten Plugin][14]                              | [Apache Software Licenese][11]    |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][15] | [ASL2][16]                        |
| [Maven Surefire Plugin][17]                             | [Apache-2.0][11]                  |
| [Versions Maven Plugin][18]                             | [Apache License, Version 2.0][11] |
| [duplicate-finder-maven-plugin Maven Mojo][19]          | [Apache License 2.0][20]          |
| [Apache Maven Assembly Plugin][21]                      | [Apache-2.0][11]                  |
| [Apache Maven JAR Plugin][22]                           | [Apache License, Version 2.0][11] |
| [Artifact reference checker and unifier][23]            | [MIT License][24]                 |
| [Apache Maven Dependency Plugin][25]                    | [Apache-2.0][11]                  |
| [Maven Failsafe Plugin][26]                             | [Apache-2.0][11]                  |
| [JaCoCo :: Maven Plugin][27]                            | [Eclipse Public License 2.0][7]   |
| [error-code-crawler-maven-plugin][28]                   | [MIT License][29]                 |
| [Reproducible Build Maven Plugin][30]                   | [Apache 2.0][16]                  |
| [Apache Maven Deploy Plugin][31]                        | [Apache-2.0][11]                  |
| [Project Keeper Maven plugin][32]                       | [The MIT License][33]             |

## Class List Verifier

### Compile Dependencies

| Dependency                | License           |
| ------------------------- | ----------------- |
| [BucketFS Java][34]       | [MIT License][35] |
| [error-reporting-java][0] | [MIT License][1]  |

### Test Dependencies

| Dependency                                      | License                          |
| ----------------------------------------------- | -------------------------------- |
| [JUnit Jupiter API][2]                          | [Eclipse Public License v2.0][3] |
| [JUnit Jupiter Params][2]                       | [Eclipse Public License v2.0][3] |
| [Hamcrest][4]                                   | [BSD License 3][5]               |
| [SLF4J JDK14 Provider][36]                      | [MIT License][37]                |
| [Testcontainers :: JUnit Jupiter Extension][38] | [MIT][39]                        |
| [Test containers for Exasol on Docker][40]      | [MIT License][41]                |
| [Test Database Builder for Java][42]            | [MIT License][43]                |
| [udf-debugging-java][44]                        | [MIT License][45]                |
| [mockito-junit-jupiter][46]                     | [MIT][47]                        |

### Plugin Dependencies

| Dependency                                              | License                           |
| ------------------------------------------------------- | --------------------------------- |
| [SonarQube Scanner for Maven][8]                        | [GNU LGPL 3][9]                   |
| [Apache Maven Toolchains Plugin][10]                    | [Apache License, Version 2.0][11] |
| [Apache Maven Compiler Plugin][12]                      | [Apache-2.0][11]                  |
| [Apache Maven Enforcer Plugin][13]                      | [Apache-2.0][11]                  |
| [Maven Flatten Plugin][14]                              | [Apache Software Licenese][11]    |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][15] | [ASL2][16]                        |
| [Maven Surefire Plugin][17]                             | [Apache-2.0][11]                  |
| [Versions Maven Plugin][18]                             | [Apache License, Version 2.0][11] |
| [duplicate-finder-maven-plugin Maven Mojo][19]          | [Apache License 2.0][20]          |
| [Apache Maven Deploy Plugin][31]                        | [Apache-2.0][11]                  |
| [Apache Maven GPG Plugin][48]                           | [Apache-2.0][11]                  |
| [Apache Maven Source Plugin][49]                        | [Apache License, Version 2.0][11] |
| [Apache Maven Javadoc Plugin][50]                       | [Apache-2.0][11]                  |
| [Nexus Staging Maven Plugin][51]                        | [Eclipse Public License][52]      |
| [Maven Failsafe Plugin][26]                             | [Apache-2.0][11]                  |
| [JaCoCo :: Maven Plugin][27]                            | [Eclipse Public License 2.0][7]   |
| [error-code-crawler-maven-plugin][28]                   | [MIT License][29]                 |
| [Reproducible Build Maven Plugin][30]                   | [Apache 2.0][16]                  |
| [Apache Maven Resources Plugin][53]                     | [Apache-2.0][11]                  |
| [Project Keeper Maven plugin][32]                       | [The MIT License][33]             |

[0]: https://github.com/exasol/error-reporting-java/
[1]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[2]: https://junit.org/junit5/
[3]: https://www.eclipse.org/legal/epl-v20.html
[4]: http://hamcrest.org/JavaHamcrest/
[5]: http://opensource.org/licenses/BSD-3-Clause
[6]: https://www.eclemma.org/jacoco/index.html
[7]: https://www.eclipse.org/legal/epl-2.0/
[8]: http://sonarsource.github.io/sonar-scanner-maven/
[9]: http://www.gnu.org/licenses/lgpl.txt
[10]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[11]: https://www.apache.org/licenses/LICENSE-2.0.txt
[12]: https://maven.apache.org/plugins/maven-compiler-plugin/
[13]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[14]: https://www.mojohaus.org/flatten-maven-plugin/
[15]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[16]: http://www.apache.org/licenses/LICENSE-2.0.txt
[17]: https://maven.apache.org/surefire/maven-surefire-plugin/
[18]: https://www.mojohaus.org/versions/versions-maven-plugin/
[19]: https://basepom.github.io/duplicate-finder-maven-plugin
[20]: http://www.apache.org/licenses/LICENSE-2.0.html
[21]: https://maven.apache.org/plugins/maven-assembly-plugin/
[22]: https://maven.apache.org/plugins/maven-jar-plugin/
[23]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[24]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[25]: https://maven.apache.org/plugins/maven-dependency-plugin/
[26]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[27]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[28]: https://github.com/exasol/error-code-crawler-maven-plugin/
[29]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[30]: http://zlika.github.io/reproducible-build-maven-plugin
[31]: https://maven.apache.org/plugins/maven-deploy-plugin/
[32]: https://github.com/exasol/project-keeper/
[33]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[34]: https://github.com/exasol/bucketfs-java/
[35]: https://github.com/exasol/bucketfs-java/blob/main/LICENSE
[36]: http://www.slf4j.org
[37]: http://www.opensource.org/licenses/mit-license.php
[38]: https://java.testcontainers.org
[39]: http://opensource.org/licenses/MIT
[40]: https://github.com/exasol/exasol-testcontainers/
[41]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[42]: https://github.com/exasol/test-db-builder-java/
[43]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[44]: https://github.com/exasol/udf-debugging-java/
[45]: https://github.com/exasol/udf-debugging-java/blob/main/LICENSE
[46]: https://github.com/mockito/mockito
[47]: https://opensource.org/licenses/MIT
[48]: https://maven.apache.org/plugins/maven-gpg-plugin/
[49]: https://maven.apache.org/plugins/maven-source-plugin/
[50]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[51]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[52]: http://www.eclipse.org/legal/epl-v10.html
[53]: https://maven.apache.org/plugins/maven-resources-plugin/
