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
| `CostRepository` | `Cost` | `COSTS` | `CostId` |
| `CountryRepository` | `Country` | `COUNTRIES` | `Long` |
| `CustomerRepository` | `Customer` | `CUSTOMERS` | `Long` |
| `FweekPscatSalesMvRepository` | `FweekPscatSalesMv` | `FWEEK_PSCAT_SALES_MV` | `FweekPscatSalesMvId` |
| `ProductRepository` | `Product` | `PRODUCTS` | `Integer` |
| `ProfitRepository` | `Profit` | `PROFITS` | `ProfitId` |
| `PromotionRepository` | `Promotion` | `PROMOTIONS` | `Integer` |
| `SaleRepository` | `Sale` | `SALES` | `SaleId` |
| `SupplementaryDemographicsRepository` | `SupplementaryDemographics` | `SUPPLEMENTARY_DEMOGRAPHICS` | `Long` |
| `TimeRepository` | `Time` | `TIMES` | `LocalDate` |

Each extends `JpaRepository` and `JpaSpecificationExecutor`, and declares no methods yet.

## Usage

Spring Data JPA is a `provided` dependency, so add `spring-boot-starter-data-jpa` yourself, and point entity scanning
at `com.github.jinahya.oracle.sample.schemas.persistence.sh.__NoOp`.

## Tests

```sh
mvn -pl oracle-sample-schemas-spring-data-sh -am test                       # unit tests, embedded H2
mvn -pl oracle-sample-schemas-spring-data-sh -Dtest='*_SpringBootIT' test   # needs the Oracle container
```

See the [root README](../README.md) for the Oracle container.
