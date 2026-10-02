# oracle-sample-schemas-spring-data-sh

Spring Data repositories for the
[Sales History](https://github.com/oracle-samples/db-sample-schemas/tree/main/sales_history) (`SH`) schema, over the
entities of `io.github.jinahya:oracle-sample-schemas-persistence-sh`.

Package: `com.github.jinahya.oracle.sample.schemas.persistence.sh.data`

## Repositories

| Repository | Entity | Table / view | Id |
|---|---|---|---|
| `CalMonthSalesMvRepository` | `CalMonthSalesMv` | `CAL_MONTH_SALES_MV` | `String` |
| `ChannelRepository` | `Channel` | `CHANNELS` | `Long` |
| `CostWithEmbeddedIdRepository` | `CostWithEmbeddedId` | `COSTS` | `CostId` |
| `CountryRepository` | `Country` | `COUNTRIES` | `Long` |
| `CustomerRepository` | `Customer` | `CUSTOMERS` | `Long` |
| `FweekPscatSalesMvWithEmbeddedIdRepository` | `FweekPscatSalesMvWithEmbeddedId` | `FWEEK_PSCAT_SALES_MV` | `FweekPscatSalesMvId` |
| `ProductRepository` | `Product` | `PRODUCTS` | `Integer` |
| `ProfitWithEmbeddedIdRepository` | `ProfitWithEmbeddedId` | `PROFITS` | `ProfitId` |
| `PromotionRepository` | `Promotion` | `PROMOTIONS` | `Integer` |
| `SaleWithEmbeddedIdRepository` | `SaleWithEmbeddedId` | `SALES` | `SaleId` |
| `SupplementaryDemographicsRepository` | `SupplementaryDemographics` | `SUPPLEMENTARY_DEMOGRAPHICS` | `Long` |
| `TimeRepository` | `Time` | `TIMES` | `LocalDate` |

Each extends `JpaRepository` and `JpaSpecificationExecutor`, and declares no methods yet.

## Usage

Spring Data JPA is a `provided` dependency, so add `spring-boot-starter-data-jpa` yourself, and point entity scanning
at `com.github.jinahya.oracle.sample.schemas.persistence.sh.__NoOp`.

`COSTS`, `SALES`, `PROFITS` and `FWEEK_PSCAT_SALES_MV` are each mapped twice (`*WithEmbeddedId` and `*WithIdClass`).
Both load, each under its own entity name; the test context keeps one per table by dropping `*WithIdClass` with a
`ManagedClassNameFilter`.

## Tests

```sh
mvn -pl oracle-sample-schemas-spring-data-sh -am test                       # unit tests, embedded H2
mvn -pl oracle-sample-schemas-spring-data-sh -Dtest='*_SpringBootIT' test   # needs the Oracle container
```

See the [root README](../README.md) for the Oracle container.
