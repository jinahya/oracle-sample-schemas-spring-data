# oracle-sample-schemas-spring-data-hr

Spring Data repositories for the
[Human Resources](https://github.com/oracle-samples/db-sample-schemas/tree/main/human_resources) (`HR`) schema, over
the entities of `io.github.jinahya:oracle-sample-schemas-persistence-hr`.

Package: `com.github.jinahya.oracle.sample.schemas.persistence.hr.data`

## Repositories

| Repository | Entity | Table / view | Id |
|---|---|---|---|
| `CountryRepository` | `Country` | `COUNTRIES` | `String` |
| `DepartmentRepository` | `Department` | `DEPARTMENTS` | `Integer` |
| `EmpDetailsViewRepository` | `EmpDetailsView` | `EMP_DETAILS_VIEW` | `Integer` |
| `EmployeeRepository` | `Employee` | `EMPLOYEES` | `Integer` |
| `JobRepository` | `Job` | `JOBS` | `String` |
| `JobHistoryRepository` | `JobHistory` | `JOB_HISTORY` | `JobHistoryId` |
| `LocationRepository` | `Location` | `LOCATIONS` | `Integer` |
| `RegionRepository` | `Region` | `REGIONS` | `Long` |

Each extends `JpaRepository` and `JpaSpecificationExecutor`, and declares no methods yet.

## Usage

Spring Data JPA is a `provided` dependency, so add `spring-boot-starter-data-jpa` yourself, and point entity scanning
at `com.github.jinahya.oracle.sample.schemas.persistence.hr.__NoOp`.

## Tests

```sh
mvn -pl oracle-sample-schemas-spring-data-hr -am test                       # unit tests, embedded H2
mvn -pl oracle-sample-schemas-spring-data-hr -Dtest='*_SpringBootIT' test   # needs the Oracle container
```

See the [root README](../README.md) for the Oracle container.
