# oracle-sample-schemas-spring-data-sh

Spring Data repositories for the
[Sales History](https://github.com/oracle-samples/db-sample-schemas/tree/main/sales_history) (`SH`) schema, over
the mapped superclasses of `io.github.jinahya:oracle-sample-schemas-persistence-sh`.

- Entities: `com.github.jinahya.oracle.sample.schemas.persistence.sh`, copied from upstream's test sources
- Repositories: `com.github.jinahya.oracle.sample.schemas.persistence.sh.data`

## Repositories

| Repository | Entity | Table / view | Id |
|---|---|---|---|
| `CalMonthSalesMvRepository` | `CalMonthSalesMv` | `CAL_MONTH_SALES_MV` (materialized view) | `String` |
| `ChannelRepository` | `Channel` | `CHANNELS` | `Long` |
| `CostRepository` | `Cost` | `COSTS` | `CostId` |
| `CountryRepository` | `Country` | `COUNTRIES` | `Long` |
| `CustomerRepository` | `Customer` | `CUSTOMERS` | `Long` |
| `FweekPscatSalesMvRepository` | `FweekPscatSalesMv` | `FWEEK_PSCAT_SALES_MV` (materialized view) | `FweekPscatSalesMvId` |
| `ProductRepository` | `Product` | `PRODUCTS` | `Integer` |
| `ProfitRepository` | `Profit` | `PROFITS` (view) | `ProfitId` |
| `PromotionRepository` | `Promotion` | `PROMOTIONS` | `Integer` |
| `SalesRepository` | `Sales` | `SALES` | `SalesId` |
| `SupplementaryDemographicsRepository` | `SupplementaryDemographics` | `SUPPLEMENTARY_DEMOGRAPHICS` | `Long` |
| `TimeRepository` | `Time` | `TIMES` | `LocalDateTime` |

Each extends `_Repository` (`JpaRepository` and `JpaSpecificationExecutor`). Name attributes through the static
metamodel (`CalMonthSalesMv_`, ...).

Every repository is empty so far.

The package is `@NullMarked`: a `null` argument to a repository method throws `IllegalArgumentException`.

## Usage

Spring Data JPA is a `provided` dependency, so add `spring-boot-starter-data-jpa` yourself, and point entity scanning
at the entities' package:

```java
@EntityScan(basePackageClasses = com.github.jinahya.oracle.sample.schemas.persistence.sh.CalMonthSalesMv.class)
```

## Tests

```sh
mvn -pl oracle-sample-schemas-spring-data-sh test                          # *_DataJpaTest, embedded H2
mvn -pl oracle-sample-schemas-spring-data-sh -Dtest='*_SpringBootIT' -Dsurefire.failIfNoSpecifiedTests=false test
```

The second needs the Oracle container; see the [root README](../README.md).
