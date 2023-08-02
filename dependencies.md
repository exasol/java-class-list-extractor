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

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][8]                        | [GNU LGPL 3][9]                                |
| [Apache Maven Compiler Plugin][10]                      | [Apache-2.0][11]                               |
| [Apache Maven Enforcer Plugin][12]                      | [Apache-2.0][11]                               |
| [Maven Flatten Plugin][13]                              | [Apache Software Licenese][11]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][14] | [ASL2][15]                                     |
| [Maven Surefire Plugin][16]                             | [Apache-2.0][11]                               |
| [Versions Maven Plugin][17]                             | [Apache License, Version 2.0][11]              |
| [duplicate-finder-maven-plugin Maven Mojo][18]          | [Apache License 2.0][19]                       |
| [Apache Maven Assembly Plugin][20]                      | [Apache-2.0][11]                               |
| [Apache Maven JAR Plugin][21]                           | [Apache License, Version 2.0][11]              |
| [Artifact reference checker and unifier][22]            | [MIT License][23]                              |
| [Apache Maven Dependency Plugin][24]                    | [Apache-2.0][11]                               |
| [Maven Failsafe Plugin][25]                             | [Apache-2.0][11]                               |
| [JaCoCo :: Maven Plugin][26]                            | [Eclipse Public License 2.0][7]                |
| [error-code-crawler-maven-plugin][27]                   | [MIT License][28]                              |
| [Reproducible Build Maven Plugin][29]                   | [Apache 2.0][15]                               |
| [Apache Maven Deploy Plugin][30]                        | [Apache-2.0][11]                               |
| [Project keeper maven plugin][31]                       | [The MIT License][32]                          |
| [Maven Clean Plugin][33]                                | [The Apache Software License, Version 2.0][15] |
| [Maven Resources Plugin][34]                            | [The Apache Software License, Version 2.0][15] |
| [Maven Install Plugin][35]                              | [The Apache Software License, Version 2.0][15] |
| [Maven Site Plugin 3][36]                               | [The Apache Software License, Version 2.0][15] |

## Class List Verifier

### Compile Dependencies

| Dependency                | License           |
| ------------------------- | ----------------- |
| [BucketFS Java][37]       | [MIT License][38] |
| [error-reporting-java][0] | [MIT License][1]  |

### Test Dependencies

| Dependency                                      | License                          |
| ----------------------------------------------- | -------------------------------- |
| [JUnit Jupiter API][2]                          | [Eclipse Public License v2.0][3] |
| [JUnit Jupiter Params][2]                       | [Eclipse Public License v2.0][3] |
| [Hamcrest][4]                                   | [BSD License 3][5]               |
| [SLF4J JDK14 Binding][39]                       | [MIT License][40]                |
| [Testcontainers :: JUnit Jupiter Extension][41] | [MIT][42]                        |
| [Test containers for Exasol on Docker][43]      | [MIT License][44]                |
| [Test Database Builder for Java][45]            | [MIT License][46]                |
| [udf-debugging-java][47]                        | [MIT License][48]                |
| [mockito-junit-jupiter][49]                     | [The MIT License][50]            |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][8]                        | [GNU LGPL 3][9]                                |
| [Apache Maven Compiler Plugin][10]                      | [Apache-2.0][11]                               |
| [Apache Maven Enforcer Plugin][12]                      | [Apache-2.0][11]                               |
| [Maven Flatten Plugin][13]                              | [Apache Software Licenese][11]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][14] | [ASL2][15]                                     |
| [Maven Surefire Plugin][16]                             | [Apache-2.0][11]                               |
| [Versions Maven Plugin][17]                             | [Apache License, Version 2.0][11]              |
| [duplicate-finder-maven-plugin Maven Mojo][18]          | [Apache License 2.0][19]                       |
| [Apache Maven Deploy Plugin][30]                        | [Apache-2.0][11]                               |
| [Apache Maven GPG Plugin][51]                           | [Apache-2.0][11]                               |
| [Apache Maven Source Plugin][52]                        | [Apache License, Version 2.0][11]              |
| [Apache Maven Javadoc Plugin][53]                       | [Apache-2.0][11]                               |
| [Nexus Staging Maven Plugin][54]                        | [Eclipse Public License][55]                   |
| [Maven Failsafe Plugin][25]                             | [Apache-2.0][11]                               |
| [JaCoCo :: Maven Plugin][26]                            | [Eclipse Public License 2.0][7]                |
| [error-code-crawler-maven-plugin][27]                   | [MIT License][28]                              |
| [Reproducible Build Maven Plugin][29]                   | [Apache 2.0][15]                               |
| [Apache Maven Resources Plugin][56]                     | [Apache-2.0][11]                               |
| [Project keeper maven plugin][31]                       | [The MIT License][32]                          |
| [Maven Clean Plugin][33]                                | [The Apache Software License, Version 2.0][15] |
| [Maven JAR Plugin][57]                                  | [The Apache Software License, Version 2.0][15] |
| [Maven Install Plugin][35]                              | [The Apache Software License, Version 2.0][15] |
| [Maven Site Plugin 3][36]                               | [The Apache Software License, Version 2.0][15] |

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
[10]: https://maven.apache.org/plugins/maven-compiler-plugin/
[11]: https://www.apache.org/licenses/LICENSE-2.0.txt
[12]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[13]: https://www.mojohaus.org/flatten-maven-plugin/
[14]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[15]: http://www.apache.org/licenses/LICENSE-2.0.txt
[16]: https://maven.apache.org/surefire/maven-surefire-plugin/
[17]: https://www.mojohaus.org/versions/versions-maven-plugin/
[18]: https://basepom.github.io/duplicate-finder-maven-plugin
[19]: http://www.apache.org/licenses/LICENSE-2.0.html
[20]: https://maven.apache.org/plugins/maven-assembly-plugin/
[21]: https://maven.apache.org/plugins/maven-jar-plugin/
[22]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[23]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[24]: https://maven.apache.org/plugins/maven-dependency-plugin/
[25]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[26]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[27]: https://github.com/exasol/error-code-crawler-maven-plugin/
[28]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[29]: http://zlika.github.io/reproducible-build-maven-plugin
[30]: https://maven.apache.org/plugins/maven-deploy-plugin/
[31]: https://github.com/exasol/project-keeper/
[32]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[33]: http://maven.apache.org/plugins/maven-clean-plugin/
[34]: http://maven.apache.org/plugins/maven-resources-plugin/
[35]: http://maven.apache.org/plugins/maven-install-plugin/
[36]: http://maven.apache.org/plugins/maven-site-plugin/
[37]: https://github.com/exasol/bucketfs-java/
[38]: https://github.com/exasol/bucketfs-java/blob/main/LICENSE
[39]: http://www.slf4j.org
[40]: http://www.opensource.org/licenses/mit-license.php
[41]: https://testcontainers.org
[42]: http://opensource.org/licenses/MIT
[43]: https://github.com/exasol/exasol-testcontainers/
[44]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[45]: https://github.com/exasol/test-db-builder-java/
[46]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[47]: https://github.com/exasol/udf-debugging-java/
[48]: https://github.com/exasol/udf-debugging-java/blob/main/LICENSE
[49]: https://github.com/mockito/mockito
[50]: https://github.com/mockito/mockito/blob/main/LICENSE
[51]: https://maven.apache.org/plugins/maven-gpg-plugin/
[52]: https://maven.apache.org/plugins/maven-source-plugin/
[53]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[54]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[55]: http://www.eclipse.org/legal/epl-v10.html
[56]: https://maven.apache.org/plugins/maven-resources-plugin/
[57]: http://maven.apache.org/plugins/maven-jar-plugin/
