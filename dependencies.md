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
| [Apache Maven Compiler Plugin][10]                      | [Apache License, Version 2.0][11]              |
| [Apache Maven Enforcer Plugin][12]                      | [Apache License, Version 2.0][11]              |
| [Maven Flatten Plugin][13]                              | [Apache Software Licenese][14]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][15] | [ASL2][14]                                     |
| [Maven Surefire Plugin][16]                             | [Apache License, Version 2.0][11]              |
| [Versions Maven Plugin][17]                             | [Apache License, Version 2.0][11]              |
| [Apache Maven Assembly Plugin][18]                      | [Apache License, Version 2.0][11]              |
| [Apache Maven JAR Plugin][19]                           | [Apache License, Version 2.0][11]              |
| [Artifact reference checker and unifier][20]            | [MIT][21]                                      |
| [Apache Maven Dependency Plugin][22]                    | [Apache License, Version 2.0][11]              |
| [Maven Failsafe Plugin][23]                             | [Apache License, Version 2.0][11]              |
| [JaCoCo :: Maven Plugin][24]                            | [Eclipse Public License 2.0][7]                |
| [error-code-crawler-maven-plugin][25]                   | [MIT License][26]                              |
| [Reproducible Build Maven Plugin][27]                   | [Apache 2.0][14]                               |
| [Apache Maven Deploy Plugin][28]                        | [Apache License, Version 2.0][11]              |
| [Project keeper maven plugin][29]                       | [The MIT License][30]                          |
| [Maven Clean Plugin][31]                                | [The Apache Software License, Version 2.0][14] |
| [Maven Resources Plugin][32]                            | [The Apache Software License, Version 2.0][14] |
| [Maven Install Plugin][33]                              | [The Apache Software License, Version 2.0][14] |
| [Maven Site Plugin 3][34]                               | [The Apache Software License, Version 2.0][14] |

## Class List Verifier

### Compile Dependencies

| Dependency                | License           |
| ------------------------- | ----------------- |
| [BucketFS Java][35]       | [MIT License][36] |
| [error-reporting-java][0] | [MIT License][1]  |

### Test Dependencies

| Dependency                                      | License                          |
| ----------------------------------------------- | -------------------------------- |
| [JUnit Jupiter API][2]                          | [Eclipse Public License v2.0][3] |
| [JUnit Jupiter Params][2]                       | [Eclipse Public License v2.0][3] |
| [Hamcrest][4]                                   | [BSD License 3][5]               |
| [Testcontainers :: JUnit Jupiter Extension][37] | [MIT][38]                        |
| [Test containers for Exasol on Docker][39]      | [MIT License][40]                |
| [Test Database Builder for Java][41]            | [MIT License][42]                |
| [udf-debugging-java][43]                        | [MIT][21]                        |
| [mockito-junit-jupiter][44]                     | [The MIT License][45]            |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][8]                        | [GNU LGPL 3][9]                                |
| [Apache Maven Compiler Plugin][10]                      | [Apache License, Version 2.0][11]              |
| [Apache Maven Enforcer Plugin][12]                      | [Apache License, Version 2.0][11]              |
| [Maven Flatten Plugin][13]                              | [Apache Software Licenese][14]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][15] | [ASL2][14]                                     |
| [Maven Surefire Plugin][16]                             | [Apache License, Version 2.0][11]              |
| [Versions Maven Plugin][17]                             | [Apache License, Version 2.0][11]              |
| [Apache Maven Deploy Plugin][28]                        | [Apache License, Version 2.0][11]              |
| [Apache Maven GPG Plugin][46]                           | [Apache License, Version 2.0][11]              |
| [Apache Maven Source Plugin][47]                        | [Apache License, Version 2.0][11]              |
| [Apache Maven Javadoc Plugin][48]                       | [Apache License, Version 2.0][11]              |
| [Nexus Staging Maven Plugin][49]                        | [Eclipse Public License][50]                   |
| [Maven Failsafe Plugin][23]                             | [Apache License, Version 2.0][11]              |
| [JaCoCo :: Maven Plugin][24]                            | [Eclipse Public License 2.0][7]                |
| [error-code-crawler-maven-plugin][25]                   | [MIT License][26]                              |
| [Reproducible Build Maven Plugin][27]                   | [Apache 2.0][14]                               |
| [Apache Maven Resources Plugin][51]                     | [Apache License, Version 2.0][11]              |
| [Project keeper maven plugin][29]                       | [The MIT License][30]                          |
| [Maven Clean Plugin][31]                                | [The Apache Software License, Version 2.0][14] |
| [Maven JAR Plugin][52]                                  | [The Apache Software License, Version 2.0][14] |
| [Maven Install Plugin][33]                              | [The Apache Software License, Version 2.0][14] |
| [Maven Site Plugin 3][34]                               | [The Apache Software License, Version 2.0][14] |

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
[14]: http://www.apache.org/licenses/LICENSE-2.0.txt
[15]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[16]: https://maven.apache.org/surefire/maven-surefire-plugin/
[17]: http://www.mojohaus.org/versions-maven-plugin/
[18]: https://maven.apache.org/plugins/maven-assembly-plugin/
[19]: https://maven.apache.org/plugins/maven-jar-plugin/
[20]: https://github.com/exasol/artifact-reference-checker-maven-plugin
[21]: https://opensource.org/licenses/MIT
[22]: https://maven.apache.org/plugins/maven-dependency-plugin/
[23]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[24]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[25]: https://github.com/exasol/error-code-crawler-maven-plugin/
[26]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[27]: http://zlika.github.io/reproducible-build-maven-plugin
[28]: https://maven.apache.org/plugins/maven-deploy-plugin/
[29]: https://github.com/exasol/project-keeper/
[30]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[31]: http://maven.apache.org/plugins/maven-clean-plugin/
[32]: http://maven.apache.org/plugins/maven-resources-plugin/
[33]: http://maven.apache.org/plugins/maven-install-plugin/
[34]: http://maven.apache.org/plugins/maven-site-plugin/
[35]: https://github.com/exasol/bucketfs-java/
[36]: https://github.com/exasol/bucketfs-java/blob/main/LICENSE
[37]: https://testcontainers.org
[38]: http://opensource.org/licenses/MIT
[39]: https://github.com/exasol/exasol-testcontainers/
[40]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[41]: https://github.com/exasol/test-db-builder-java/
[42]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[43]: https://github.com/exasol/udf-debugging-java/
[44]: https://github.com/mockito/mockito
[45]: https://github.com/mockito/mockito/blob/main/LICENSE
[46]: https://maven.apache.org/plugins/maven-gpg-plugin/
[47]: https://maven.apache.org/plugins/maven-source-plugin/
[48]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[49]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[50]: http://www.eclipse.org/legal/epl-v10.html
[51]: https://maven.apache.org/plugins/maven-resources-plugin/
[52]: http://maven.apache.org/plugins/maven-jar-plugin/
