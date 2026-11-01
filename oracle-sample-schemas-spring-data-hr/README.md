# oracle-sample-schemas-spring-data-hr

Spring Data repositories for the
[Human Resources](https://github.com/oracle-samples/db-sample-schemas/tree/main/human_resources) (`HR`) schema, over
the mapped superclasses of `io.github.jinahya:oracle-sample-schemas-persistence-hr`.

- Entities: `com.github.jinahya.oracle.sample.schemas.persistence.hr`, copied from upstream's test sources
- Repositories: `com.github.jinahya.oracle.sample.schemas.persistence.hr.data`

## Repositories

| Repository | Entity | Table / view | Id |
|---|---|---|---|
| `CountryRepository` | `Country` | `COUNTRIES` | `String` |
| `DepartmentRepository` | `Department` | `DEPARTMENTS` | `Integer` |
| `EmpDetailsViewRepository` | `EmpDetailsView` | `EMP_DETAILS_VIEW` (view) | `Integer` |
| `EmployeeRepository` | `Employee` | `EMPLOYEES` | `Integer` |
| `JobRepository` | `Job` | `JOBS` | `String` |
| `JobHistoryRepository` | `JobHistory` | `JOB_HISTORY` | `JobHistoryId` |
| `LocationRepository` | `Location` | `LOCATIONS` | `Integer` |
| `RegionRepository` | `Region` | `REGIONS` | `Long` |

Each extends `_Repository` (`JpaRepository` and `JpaSpecificationExecutor`). Name attributes through the static
metamodel (`Country_`, ...).

Every repository is empty so far.

The package is `@NullMarked`: a `null` argument to a repository method throws `IllegalArgumentException`.

## Usage

Spring Data JPA is a `provided` dependency, so add `spring-boot-starter-data-jpa` yourself, and point entity scanning
at the entities' package:

```java
@EntityScan(basePackageClasses = com.github.jinahya.oracle.sample.schemas.persistence.hr.Country.class)
```

## Tests

```sh
mvn -pl oracle-sample-schemas-spring-data-hr test                          # *_DataJpaTest, embedded H2
mvn -pl oracle-sample-schemas-spring-data-hr -Dtest='*_SpringBootIT' -Dsurefire.failIfNoSpecifiedTests=false test
```

The second needs the Oracle container; see the [root README](../README.md).
