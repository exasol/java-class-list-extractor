
### Verifier for class list

`dsn~class-list-verifier~1`

The `ClassListVerifier` checks that the built jar contains a class list and verifies that it contains the correct classes.
If it's missing or outdated, it writes the expected class list to a temporary file and throws and `AssertionError` with the suggestion to copy the class list from the temporary file to the project resourced.

An alternative would have been to generate the class list during the tests, and then add it to the jar. The downside of this approach is, that it is nto very clean to modify the jar after the integration tests. If the jar got corrupted by that, it would not be recognized by the tests.  
 
Covers:
 
* `req~include-class-list-in-jar~1`

Needs: impl, utest

### Handling the Unstable Class List Generation

`dsn~handling-unstable-class-list-generation~1`

A challenge for this tool is, that the generated class list can differ from run to run a bit. The reason for that is that due to different hardware. machine utilization or just coincidence the executions might differ a bit. By that slightly different classes might get loaded. Usually the differing classes are from the `/util/concurrent` package.  

To mitigate this problem we use the following steps:
* Run capture function multiple times and take the intersection of the class lists
* Make validation fuzzy using am ignore-list

Needs: dsn

#### Stabilize Tests Using Multiple Runs
`dsn~multiple-runs-for-more-stable-extraction~1`

In order to stabilize the class list generation, the ClassListExtractor runs the capture function multiple times and builds the intersection of the extracted class lists. That filters out all classes that were not loaded on all runs.

Covers:

* `dsn~handling-unstable-class-list-generation~1`

Needs: impl

#### Fuzzy matching with ignore list

`dsn-fuzzy-class-list-matching~1`

We solve the problem of the unstable class list by an exclude-list with pattern for classes that are ignored when checking the class list.
Each project using the `ClassListVerifier` can specify a dedicated class list. By that we can exclude the classes that seem to be flaky for the validation. For example all classes of `/util/concurrent`.

Covers:

* `dsn~handling-unstable-class-list-generation~1`

Needs: impl, utest 