<!-- @formatter:off -->
# Dependencies

## Class List Extractor Agent

### Compile Dependencies

| Dependency                | License  |
| ------------------------- | -------- |
| [error-reporting-java][0] | [MIT][1] |

### Test Dependencies

| Dependency                | License                          |
| ------------------------- | -------------------------------- |
| [JUnit Jupiter Engine][2] | [Eclipse Public License v2.0][3] |
| [JUnit Jupiter Params][2] | [Eclipse Public License v2.0][3] |
| [Hamcrest][6]             | [BSD License 3][7]               |
| [JaCoCo :: Agent][8]      | [Eclipse Public License 2.0][9]  |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [Apache Maven Enforcer Plugin][10]                      | [Apache License, Version 2.0][11]              |
| [Maven Flatten Plugin][12]                              | [Apache Software Licenese][13]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][14] | [ASL2][13]                                     |
| [Reproducible Build Maven Plugin][16]                   | [Apache 2.0][13]                               |
| [Maven Surefire Plugin][18]                             | [Apache License, Version 2.0][11]              |
| [Versions Maven Plugin][20]                             | [Apache License, Version 2.0][11]              |
| [Apache Maven Compiler Plugin][22]                      | [Apache License, Version 2.0][11]              |
| [Apache Maven Assembly Plugin][24]                      | [Apache License, Version 2.0][11]              |
| [Apache Maven JAR Plugin][26]                           | [Apache License, Version 2.0][11]              |
| [Artifact reference checker and unifier][28]            | [MIT][1]                                       |
| [Apache Maven Dependency Plugin][30]                    | [Apache License, Version 2.0][11]              |
| [Maven Failsafe Plugin][32]                             | [Apache License, Version 2.0][11]              |
| [JaCoCo :: Maven Plugin][34]                            | [Eclipse Public License 2.0][9]                |
| [error-code-crawler-maven-plugin][36]                   | [MIT][1]                                       |
| [Maven Clean Plugin][38]                                | [The Apache Software License, Version 2.0][13] |
| [Maven Resources Plugin][40]                            | [The Apache Software License, Version 2.0][13] |
| [Maven Install Plugin][42]                              | [The Apache Software License, Version 2.0][13] |
| [Maven Deploy Plugin][44]                               | [The Apache Software License, Version 2.0][13] |
| [Maven Site Plugin 3][46]                               | [The Apache Software License, Version 2.0][13] |

## Class List Verifier

### Compile Dependencies

| Dependency                | License               |
| ------------------------- | --------------------- |
| [BucketFS Java][48]       | [MIT][1]              |
| [error-reporting-java][0] | [MIT][1]              |
| [Project Lombok][52]      | [The MIT License][53] |

### Test Dependencies

| Dependency                                      | License                          |
| ----------------------------------------------- | -------------------------------- |
| [JUnit Jupiter Engine][2]                       | [Eclipse Public License v2.0][3] |
| [JUnit Jupiter Params][2]                       | [Eclipse Public License v2.0][3] |
| [Hamcrest][6]                                   | [BSD License 3][7]               |
| [Testcontainers :: JUnit Jupiter Extension][60] | [MIT][61]                        |
| [Test containers for Exasol on Docker][62]      | [MIT][1]                         |
| [Test Database Builder for Java][64]            | [MIT][1]                         |
| [udf-debugging-java][66]                        | [MIT][1]                         |
| [mockito-junit-jupiter][68]                     | [The MIT License][69]            |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [Apache Maven Enforcer Plugin][10]                      | [Apache License, Version 2.0][11]              |
| [Maven Flatten Plugin][12]                              | [Apache Software Licenese][13]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][14] | [ASL2][13]                                     |
| [Reproducible Build Maven Plugin][16]                   | [Apache 2.0][13]                               |
| [Maven Surefire Plugin][18]                             | [Apache License, Version 2.0][11]              |
| [Versions Maven Plugin][20]                             | [Apache License, Version 2.0][11]              |
| [Apache Maven Deploy Plugin][82]                        | [Apache License, Version 2.0][11]              |
| [Apache Maven GPG Plugin][84]                           | [Apache License, Version 2.0][11]              |
| [Apache Maven Source Plugin][86]                        | [Apache License, Version 2.0][11]              |
| [Apache Maven Javadoc Plugin][88]                       | [Apache License, Version 2.0][11]              |
| [Nexus Staging Maven Plugin][90]                        | [Eclipse Public License][91]                   |
| [Lombok Maven Plugin][92]                               | [The MIT License][1]                           |
| [Maven Failsafe Plugin][32]                             | [Apache License, Version 2.0][11]              |
| [JaCoCo :: Maven Plugin][34]                            | [Eclipse Public License 2.0][9]                |
| [error-code-crawler-maven-plugin][36]                   | [MIT][1]                                       |
| [Apache Maven Compiler Plugin][22]                      | [Apache License, Version 2.0][11]              |
| [Apache Maven Resources Plugin][102]                    | [Apache License, Version 2.0][11]              |
| [Maven Clean Plugin][38]                                | [The Apache Software License, Version 2.0][13] |
| [Maven JAR Plugin][106]                                 | [The Apache Software License, Version 2.0][13] |
| [Maven Install Plugin][42]                              | [The Apache Software License, Version 2.0][13] |
| [Maven Site Plugin 3][46]                               | [The Apache Software License, Version 2.0][13] |

[8]: https://www.eclemma.org/jacoco/index.html
[48]: https://github.com/exasol/bucketfs-java
[0]: https://github.com/exasol/error-reporting-java
[13]: http://www.apache.org/licenses/LICENSE-2.0.txt
[18]: https://maven.apache.org/surefire/maven-surefire-plugin/
[52]: https://projectlombok.org
[90]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[38]: http://maven.apache.org/plugins/maven-clean-plugin/
[1]: https://opensource.org/licenses/MIT
[32]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[68]: https://github.com/mockito/mockito
[64]: https://github.com/exasol/test-db-builder-java
[20]: http://www.mojohaus.org/versions-maven-plugin/
[7]: http://opensource.org/licenses/BSD-3-Clause
[22]: https://maven.apache.org/plugins/maven-compiler-plugin/
[61]: http://opensource.org/licenses/MIT
[102]: https://maven.apache.org/plugins/maven-resources-plugin/
[9]: https://www.eclipse.org/legal/epl-2.0/
[82]: https://maven.apache.org/plugins/maven-deploy-plugin/
[91]: http://www.eclipse.org/legal/epl-v10.html
[34]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[62]: https://github.com/exasol/exasol-testcontainers
[69]: https://github.com/mockito/mockito/blob/main/LICENSE
[16]: http://zlika.github.io/reproducible-build-maven-plugin
[30]: https://maven.apache.org/plugins/maven-dependency-plugin/
[53]: https://projectlombok.org/LICENSE
[106]: http://maven.apache.org/plugins/maven-jar-plugin/
[11]: https://www.apache.org/licenses/LICENSE-2.0.txt
[10]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[92]: https://awhitford.github.com/lombok.maven/lombok-maven-plugin/
[3]: https://www.eclipse.org/legal/epl-v20.html
[42]: http://maven.apache.org/plugins/maven-install-plugin/
[2]: https://junit.org/junit5/
[14]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[84]: https://maven.apache.org/plugins/maven-gpg-plugin/
[12]: https://www.mojohaus.org/flatten-maven-plugin/flatten-maven-plugin
[60]: https://testcontainers.org
[66]: https://github.com/exasol/udf-debugging-java
[86]: https://maven.apache.org/plugins/maven-source-plugin/
[6]: http://hamcrest.org/JavaHamcrest/
[44]: http://maven.apache.org/plugins/maven-deploy-plugin/
[46]: http://maven.apache.org/plugins/maven-site-plugin/
[40]: http://maven.apache.org/plugins/maven-resources-plugin/
[88]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[28]: https://github.com/exasol/artifact-reference-checker-maven-plugin
[36]: https://github.com/exasol/error-code-crawler-maven-plugin
[26]: https://maven.apache.org/plugins/maven-jar-plugin/
[24]: https://maven.apache.org/plugins/maven-assembly-plugin/
