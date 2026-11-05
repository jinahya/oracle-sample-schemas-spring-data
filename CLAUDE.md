# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this project is

Spring Data repositories over the Jakarta Persistence mappings of
[`oracle-sample-schemas-persistence`](https://github.com/jinahya/oracle-sample-schemas-persistence), which maps the
[Oracle Database Sample Schemas](https://github.com/oracle-samples/db-sample-schemas). Modelled on the sibling
`postgresql-demodb-spring-data`, with one module per schema, as upstream has.

The root is a parent-less aggregator (`<packaging>pom</packaging>`) over the three modules, and their parent: it holds
what they share, and a module's own pom names only its schema's artifacts.

| Module | Wraps | Entities | Repositories |
| --- | --- | --- | --- |
| `oracle-sample-schemas-spring-data-co` | `io.github.jinahya:oracle-sample-schemas-persistence-co` | `…persistence.co` | `…persistence.co.data` |
| `oracle-sample-schemas-spring-data-hr` | `io.github.jinahya:oracle-sample-schemas-persistence-hr` | `…persistence.hr` | `…persistence.hr.data` |
| `oracle-sample-schemas-spring-data-sh` | `io.github.jinahya:oracle-sample-schemas-persistence-sh` | `…persistence.sh` | `…persistence.sh.data` |

`…` is `com.github.jinahya.oracle.sample`. The test packages are the repositories' packages.

- Each `.data` package is `@NullMarked` (JSpecify: Spring Data rejects a `null` argument to a repository method with
  `IllegalArgumentException` before any query runs; a lookup that may find nothing returns `Optional`).
- One repository per entity, named `<Entity>Repository`, extending the module's `_Repository<E, ID>` (`JpaRepository`
  + `JpaSpecificationExecutor`, `@NoRepositoryBean`). Each module has its own `_Repository`; there is no shared module.
- The published jars carry **the entities and the repositories**: `spring-data-jpa`, `jakarta.persistence-api` and
  `jakarta.validation-api` are `provided`, so a consumer brings Spring Data JPA, a JPA provider, and (to enforce the
  constraints on repository parameters) a validation provider plus `@Validated`. No Boot starter is named in main.

| Module | Entities | Repositories with methods |
| --- | --- | --- |
| `co` (9) | `Customer`, `CustomerOrderProduct`, `Inventory`, `Order`, `OrderItem` (`OrderItemId`), `Product`, `ProductOrder` (`ProductOrderId`), `Shipment`, `Store` | `CustomerRepository`, `InventoryRepository`, `OrderRepository`, `ProductRepository` |
| `hr` (8) | `Country`, `Department`, `EmpDetailsView`, `Employee`, `Job`, `JobHistory` (`JobHistoryId`), `Location`, `Region` | none |
| `sh` (12) | `CalMonthSalesMv`, `Channel`, `Cost` (`CostId`), `Country`, `Customer`, `FweekPscatSalesMv` (`FweekPscatSalesMvId`), `Product`, `Profit` (`ProfitId`), `Promotion`, `Sales` (`SalesId`), `SupplementaryDemographics`, `Time` | none |

The other repositories are empty, and so are their tests.

## Upstream, and the entities

The upstream checkout `../../oracle-sample-schemas-persistence` publishes, at `0.0.6-SNAPSHOT`, per schema, a jar of
**mapped superclasses (`<schema>.mapped.Mapped<Entity>`), their generated static metamodel (`MappedCustomer_`, ...),
and the embeddables and converters the mappings name**, and a second jar of the same artifact classified
`test-companion`, holding the `*_Randomizer` and `*_Persister` classes compiled with its tests. Both are **SNAPSHOTs
that exist only locally**: after a change upstream, run `./mvnw -DskipTests install` there before building here. Read
`../../oracle-sample-schemas-persistence/CLAUDE.md` before touching anything about the mappings.

- **Upstream keeps its entities in `src/test`**, on purpose: which associations an entity maps, lazy or not, with what
  cascade, is an application's decision, and this project is that application. The entities here are copies of
  upstream's test entities and id classes, verbatim
  (`oracle-sample-schemas-persistence-<schema>/src/test/java/com/github/jinahya/oracle/sample/persistence/<schema>/<Entity>.java`
  there), in the same package, so that the `test-companion` randomizers and persisters, compiled against that
  package, find them. **Keep them in step with upstream**: diff against upstream's before touching a mapping, and
  change the mapping there first.
- **Do not copy upstream's randomizers or persisters into `src/test`.** The `test-companion` jar is on the test
  classpath, and `ObjectRandomizerUtils` / `EntityPersisterUtils` find `<Entity>_Randomizer` / `<Entity>_Persister`
  next to the entity by name. A copy only shadows the jar and drifts.
- **The one exception is `co`'s `ProductDetails_TestUtils`**, copied verbatim into `co`'s test sources
  (`…persistence.co`). `Product_Randomizer` calls it, but the jar's includes take `*_Randomizer` and `*_Persister`
  only, so without the copy every `Product` randomization fails with `NoClassDefFoundError`. Drop the copy once
  upstream packages the helper. The same randomizer writes `PRODUCT_DETAILS` with Jackson 2's `ObjectMapper`, which
  upstream has at test scope, so `co` declares `jackson-databind` (test, from the root's `jackson-bom` import).
- **Not copied: the non-entity view classes.** Upstream maps `co`'s keyless views `PRODUCT_REVIEWS` and `STORE_ORDERS`
  as plain classes (`ProductReview`, `StoreOrder`), not entities. Spring Data JPA builds repositories for managed types
  only, and the classes are neither entities nor id classes, so there is no repository for either view.
- **Name attributes through the static metamodel** (`root.get(Customer_.emailAddress)`, generated here by
  `hibernate-processor` for the entities, extending upstream's `MappedCustomer_`), never a string. Where a compile-time
  constant is needed, use the mapped superclass's own `ATTRIBUTE_NAME_*` constant, or the metamodel's `EMAIL_ADDRESS`.
- **Named queries** are declared on the entities as `<EntityName>.<queryName>` (`Customer.selectOneByEmailAddress`).
  A repository method of exactly that name runs the query with no annotation; its named parameters are bound by name
  (`maven.compiler.parameters`), and the existing `@Param`s are kept.
- **Each composite key is mapped once**: `co`'s `OrderItem` and `ProductOrder`, and `sh`'s `Sales` and
  `FweekPscatSalesMv` with an `@EmbeddedId`; `hr`'s `JobHistory` and `sh`'s `Cost` and `Profit` with an `@IdClass`.
  Hibernate logs `HHH90005707` ("Unable to locate static metamodel field") for the `@IdClass` attributes, which the
  mapped superclass declares and the entity's metamodel does not repeat; it is a warning, and the mappings work.
- Identifiers of `co`'s tables are `IDENTITY` columns, which the randomizers leave `null`.

## Build

There is no wrapper here; use the Maven on `PATH`. The build JDK must be **25** (enforced): main sources compile at
release 17, as upstream, tests at 25.

```sh
mvn clean verify                                 # whole reactor; unit tests only (@DataJpaTest slices on H2)
M=oracle-sample-schemas-spring-data-co
mvn -pl $M test                                  # one module
mvn -pl $M -Dtest=CustomerRepository_DataJpaTest test
mvn -pl $M -Dtest=SomeTest#method test
mvn -pl $M -Dtest='*_SpringBootIT' -Dsurefire.failIfNoSpecifiedTests=false test   # integration tests; needs Oracle
mvn -o dependency:tree
mvn -o enforcer:enforce -Denforcer.rules=dependencyConvergence
```

Surefire's default includes do not match `*IT`, and no failsafe plugin is configured, so `test`/`verify`/`install`
never run the `*_SpringBootIT` classes; run them explicitly with `-Dtest`.

**The integration tests need the Oracle container** that upstream owns: run `../../oracle-sample-schemas-persistence/_compose.sh`.
It serves `freepdb1` on `localhost:1521` with CO, HR and SH installed. Each module's `src/test/resources/application.yaml`
connects as `dmlonly` (DML only, no DDL), sets `hibernate.default_schema` to the schema, and `ddl-auto: none`. Never
point a test at the schema owner or let it generate DDL against Oracle.

## Load-bearing pom decisions

Each is commented in `pom.xml`:

- **Spring Boot 4.x (`4.1.2`), not 3.x.** Boot 4 manages `jakarta.persistence-api` 3.2, which the upstream mappings are
  written against. Do not downgrade to dodge a problem.
- **`spring-boot-dependencies` is imported, not inherited** (the root is parent-less). Every Spring / Boot / Hibernate
  version comes from the BOM; declare those with no `<version>`. Direct `dependencyManagement` entries beat the BOM,
  which is how the pins work: Lombok `1.18.48` (the BOM's breaks on newer JDKs), and `ojdbc17` and `h2` at upstream's
  versions. The JUnit, Mockito and Jackson 2 BOMs are imported before Boot's, so they win.
- **`hibernate-processor` is on the processor path** of both compile executions, from the BOM's version, to generate
  the entities' static metamodel.
- **Boot 4 starters come in pairs.** Tests declare `spring-boot-starter-data-jpa-test` and `-validation-test`, not
  `spring-boot-starter-test`, which each of them brings. `@DataJpaTest` is
  `org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest`.
- **Shared dependencies live in the root pom.** A module pom names only its upstream artifact and its `test-companion`
  jar (and, for `co`, `jackson-databind`), with no versions. The Oracle driver and H2 are test scope, in the root.
- **Dependency convergence is a build defect**, not a warning; `enforce-dependency-convergence` runs on every module.
  Resolve `jakarta.*` at the platform level instead of pinning individual API artifacts.
- **Lombok is `provided`** for main and test, on the processor path of both compile executions.

## Tests

Each module's test root, `…persistence.<schema>.data`, has `___Spring_TestContext`: `@SpringBootConfiguration` +
`@EnableAutoConfiguration` + `@EntityScan(basePackageClasses = <an entity>.class)` (the entities are in the parent
package; the scan covers its subpackages, upstream's `.mapped` among them). It lives in test sources on purpose:
nothing in `src/main` carries a `@SpringBootApplication`. `SpringTestContext_SpringBootTest` and
`SpringTestContext_DataJpaTest` test the context itself; the latter generates every entity's table into H2 and counts
each, so **every entity of every module runs on H2**, and no `_DataJpaTest` is left out.

Repository tests extend a small hierarchy, one copy per module, kept in step by hand:

- `__Repository_TestBase<T, U, V>`: repository, entity and id types; injects the repository (`repositoryInstance()`)
  and the `EntityManager` (`entityManager()`); `identifier(entity)` reads an id through the metamodel; `column(..)`,
  `longColumn(..)`, `intColumn(..)` read a tuple's columns by alias.
- `_Repository_DataJpaTest`: `@DataJpaTest` over an embedded H2 (profile `h2`, `application-h2.yaml`, `replace: none`,
  `MODE=LEGACY;DATABASE_TO_UPPER=FALSE` as upstream's `<schema>-test` unit), rolled back per test. It imports
  `_Repository_DataJpaTest_Configuration`, which undoes the Oracle settings of `application.yaml` for H2
  (`create-drop`, blank `default_schema`, `H2Dialect`, `show_sql` off), and `_Repository_DataJpaTest_Fixtures`, which
  saves randomized entities through their repositories (`fixtures().customer()`, `fixtures().inventory(store,
  product)`, ...). Only `co`'s fixtures have methods so far.
- `_Repository_SpringBootIT`: `@SpringBootTest` against the installed schema. Repository calls commit unless the test
  is `@Transactional`. It declares `FindById_Test` for every subclass, and `selectRandom()` /
  `selectRandomPresent()`, which aborts the test by an assumption when the table is empty.

**The injected `EntityManager` is kept, but a `_DataJpaTest` does not use it.** Everything a slice test does with the
database goes through the repository under test or the fixtures. Some of `co`'s `_SpringBootIT`s, carried over from
before, still select a random row of another entity through `entityManager()`.

Naming: `<Repository>_DataJpaTest` and `<Repository>_SpringBootIT`, with one `@Nested` class per method under test
(`FindById_Test`, `FindAllByStore_Test`, ...), and test methods named `_Empty_Unknown`, `_NotEmpty_Known`,
`__Transactional` and the like.

An integration test **reads the sample data; it never fabricates rows**. A test that must write does so inside
`@Transactional`, so it is rolled back.

Logging is set in `src/test/resources/logback-spring.xml` of each module, which includes Boot's `defaults.xml` and
`console-appender.xml` and sets the levels: `org.hibernate.SQL` at DEBUG and `org.hibernate.orm.jdbc.bind` at TRACE,
the module's `.data` package at DEBUG, podam at INFO. Keep levels there, not under `logging.level` in
`application.yaml`, and do not add a `logback.xml` / `logback-test.xml`.
