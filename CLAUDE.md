# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this project is

Spring Data repositories on top of the Jakarta Persistence entities of
[`oracle-sample-schemas-persistence`](https://github.com/jinahya/oracle-sample-schemas-persistence),
which maps the [Oracle Database Sample Schemas](https://github.com/oracle-samples/db-sample-schemas).

The root is a parent-less aggregator (`<packaging>pom</packaging>`) over one module per schema:

| Module | Wraps | Sources here |
| --- | --- | --- |
| `oracle-sample-schemas-spring-data-co` | `io.github.jinahya:oracle-sample-schemas-persistence-co` | `com.github.jinahya.oracle.sample.schemas.co.data` |
| `oracle-sample-schemas-spring-data-hr` | `io.github.jinahya:oracle-sample-schemas-persistence-hr` | `com.github.jinahya.oracle.sample.schemas.persistence.hr.data` |
| `oracle-sample-schemas-spring-data-sh` | `io.github.jinahya:oracle-sample-schemas-persistence-sh` | `com.github.jinahya.oracle.sample.schemas.persistence.sh.data` |

Upstream's entities all live in `com.github.jinahya.oracle.sample.schemas.persistence.<schema>`, and
`hr`/`sh` here follow it with `.data`. `co` here has **not** been moved yet and is still
`…schemas.co.data`; do not extrapolate one module's package to another.

Every module has one repository per upstream `@Entity` (`<Entity>Repository`), except
`co`'s `OrderItemWithIdClass`, which the test context leaves out of the persistence unit (see below). Only
some of `co`'s repositories declare methods (`CustomerRepository`, `InventoryRepository`,
`OrderRepository`, `ProductRepository`); the rest are empty, as are their tests.

`co` also has a repository fragment for each upstream view class that is not an `@Entity`:
`ProductReviewRepository` (`PRODUCT_REVIEWS`), mixed into `ProductRepository`, and `StoreOrderRepository`
(`STORE_ORDERS`), mixed into `StoreRepository` (see "View repositories" below). Neither has methods yet. `hr` and `sh`
have none: upstream maps all of their views as entities.

## Upstream

The sibling checkout `../oracle-sample-schemas-persistence` publishes, at `0.0.1-SNAPSHOT`, one entity
jar per schema plus a second jar of the same artifact classified `test-companion` (randomizers and
persisters for tests). Each entity package also has a `__NoOp` marker class, which is what
`@EntityScan(basePackageClasses = …)` points at here.

These are **SNAPSHOTs that exist only locally**. After a change upstream, run
`./mvnw -DskipTests install` there before building here. Old `com.github.jinahya:{co,hr,sh}` jars may
still sit in `~/.m2`; nothing uses them.

Read `../oracle-sample-schemas-persistence/CLAUDE.md` before touching mappings. What constrains code
written *here*:

- **Entities are standalone by design**: no `@MappedSuperclass` hierarchy, no builder layer, each
  class carries its own column constants. Do not introduce a shared superclass. (`co` carries a
  `…co.mapped.MappedCustomer` against that rule; ask before following it.)
- **Name attributes through the generated static metamodel** (`Customer_`, `Employee_`, …), e.g.
  `root.get(Customer_.emailAddress)`, never a string. Where a compile-time constant is needed
  (annotation values), use the entity's own `ATTRIBUTE_NAME_*` constant.
- **Each table is mapped once**, with either an `@EmbeddedId` or an `@IdClass` (`co`'s
  `ProductOrder`, `hr`'s `JobHistory`, `sh`'s `Cost`, `Sale`, `Profit`, `FweekPscatSalesMv`), and
  its repository is named after the entity. The one exception is `co`'s `ORDER_ITEMS`, still mapped
  as both `OrderItemWithEmbeddedId` and `OrderItemWithIdClass`, each under its own entity name. `co`'s
  test context declares a `ManagedClassNameFilter` that drops `*WithIdClass`; `hr` and `sh` need
  none.
- **Named queries** are declared on the entities as `<EntityName>.<queryName>`, e.g.
  `Customer.selectOneByEmailAddress`. A repository method of exactly that name runs the query with no
  annotation. Its named parameters must be bound with `@Param`: this build does not compile with
  `-parameters`, so without `@Param` the argument is bound positionally and the call fails at run
  time, even though the context starts.
- Upstream jars carry **entities and metamodel only**; their `persistence.xml` is test-only, so this
  project supplies its own datasource and JPA configuration.

## Build

There is no wrapper here; use the Maven on `PATH` (3.9.x).

```sh
mvn clean install                       # whole reactor; unit tests only
M=oracle-sample-schemas-spring-data-co
mvn -pl $M -am test                     # one module
mvn -pl $M -Dtest=CustomerRepository_DataJpaTest test
mvn -pl $M -Dtest=SomeTest#method test
mvn -pl $M -Dtest='*_SpringBootIT' test # integration tests; needs Oracle, see below
mvn -o -pl $M dependency:tree
mvn -o enforcer:enforce -Denforcer.rules=dependencyConvergence
```

Surefire's default includes do not match `*IT`, so `install`/`test` never run the `*_SpringBootIT`
classes; run them explicitly with `-Dtest`.

**The integration tests need the Oracle container** that upstream owns: run
`../oracle-sample-schemas-persistence/_docker-compose-up.sh`. It serves `freepdb1` on `localhost:1521`
with CO/HR/SH installed. Each module's `src/test/resources/application.yaml` connects as `dmlonly`
(DML only, no DDL), sets `hibernate.default_schema` to the schema, and `ddl-auto: none`. Never point
a test at the schema owner or let it generate DDL against Oracle.

The compiler release is **25** (main and test); 21 is the floor set by the upstream entities. Boot
4.1.1 manages **Hibernate 7.4.5**, while upstream builds against 7.4.9: if a mapping behaves
differently here, check that first.

## Load-bearing pom decisions

Each is commented in `pom.xml`:

- **Spring Boot 4.x (`4.1.1`), not 3.x.** Boot 4 manages `jakarta.persistence-api` 3.2.0, which the
  upstream mappings are written against; Boot 3.x manages 3.1. Do not downgrade to dodge a problem.
- **`spring-boot-dependencies` is imported, not inherited.** Every Spring / Boot / Hibernate version
  comes from that BOM; declare those dependencies with `groupId`/`artifactId`/scope only, never a
  `<version>`. The one exception is the **Lombok pin** (1.18.48, over the BOM's 1.18.46, which
  breaks on newer JDKs).
- **Boot 4 starters come in pairs**: `spring-boot-starter-<technology>` and
  `spring-boot-starter-<technology>-test`. Declare the test starters of the technologies under test
  (`spring-boot-starter-data-jpa-test`, `spring-boot-starter-jackson-test`), not
  `spring-boot-starter-test`, which each of them brings. Slice annotations moved with them:
  `@DataJpaTest` is `org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest`.
- **Main depends on `spring-data-jpa` alone, `provided`** (besides Lombok, compile time only), not on a Boot starter, so consumers of
  these jars must bring Spring Data JPA and a JPA provider themselves. `jakarta.persistence-api` is
  declared, `provided`, because main code uses JPA types (`_NonEntityRepositoryImpl`'s `EntityManager`);
  `spring-data-jpa` does not bring it. Tests get `spring-boot-starter-data-jpa` through
  `spring-boot-starter-data-jpa-test`; do not declare it.
- **Shared dependencies live in the root pom.** A module pom names only its entity artifact, its
  `test-companion` jar and the Oracle driver (`ojdbc17`, test scope), with no versions.
- **Dependency convergence is a build defect**, not a warning, as upstream treats it. Resolve
  `jakarta.*` at the platform level instead of pinning individual API artifacts.
- **Lombok is for main and test sources**, `provided` (neither packaged nor passed on to consumers), and on
  the compiler's processor path for both `default-compile` and `default-testCompile`.

## Code conventions

- `oracle-sample-schemas-spring-data-co`'s main package is `@NullMarked` (JSpecify, which Spring
  Framework 7 brings). Spring Data enforces it: a `null` argument to a repository method throws
  `IllegalArgumentException` before any query runs. Return `Optional` for lookups that may find
  nothing.
- Repositories extend `JpaRepository<E, ID>` and `JpaSpecificationExecutor<E>`; criteria queries go
  through `Specification` and the metamodel. `ProductRepository` and `StoreRepository` also extend a view
  fragment (below).

## View repositories

Spring Data JPA builds repositories for managed types only: a repository of a non-`@Entity` class, even a bare
`Repository<ProductReview, …>`, fails at startup with `Not a managed type`. So a view upstream maps without `@Entity`
gets a Spring Data **custom repository fragment**:

- `<Class>Repository`, a plain interface extending nothing, holds the view's methods.
- `<Class>RepositoryImpl`, package-private, implements it over a `JdbcClient` (constructor by Lombok's
  `@RequiredArgsConstructor`). Spring Data finds it **by that name** (the fragment's plus `Impl`) in the repository
  packages and registers it itself: no `@Repository`, component scan or `@Import`. Renaming it breaks the lookup.
- The repository of the entity the view is built from extends the fragment: `ProductRepository` for
  `ProductReviewRepository`, `StoreRepository` for `StoreOrderRepository`. Callers reach the view through it.

- **No CRUD.** The views have no usable key and every column is read-only: no `findById`, `save` or `delete`.
- **Qualify the view with its schema** (`CO.PRODUCT_REVIEWS`). `hibernate.default_schema` reaches only SQL Hibernate
  generates, and the tests connect as `dmlonly`, which owns no such view; unqualified, Oracle answers `ORA-00942`.
- **Inside a transaction**, `JdbcClient` shares JPA's connection but sees only what JPA has flushed.
- **`JdbcClient` comes with what is already there**: `spring-jdbc` through `spring-data-jpa` → `spring-orm`
  (`provided`; do not declare it), and the bean from Boot's auto-configuration on the single `DataSource`. A
  consumer without Boot declares `JdbcClient.create(dataSource)` itself.
- `JdbcClient` translates `SQLException` to `DataAccessException` itself.

## Tests

Each module's test root has a `___Spring_TestContext`: `@SpringBootConfiguration` +
`@EnableAutoConfiguration`, plus `@EntityScan(basePackageClasses = __NoOp.class)` (the entities are
outside this package, so auto-configuration would not find them), and in `co` the `ManagedClassNameFilter`.
Both `@SpringBootTest` and `@DataJpaTest` find it by searching up the packages; a `@DataJpaTest` uses
its `@EntityScan` and `@Bean`s but replaces its auto-configuration with the slice's. Anything added
there reaches every test in the module.

`oracle-sample-schemas-spring-data-co`'s test classes extend a small hierarchy:

- `__Repository_TestBase<T, U, V>`: repository, entity and id types; injects the repository and the
  `EntityManager`.
- `_Repository_DataJpaTest`: `@DataJpaTest` over an embedded H2, rolled back per test. It imports
  `_Repository_DataJpaTest_Configuration`, which undoes the Oracle settings of `application.yaml`
  for H2 (`create-drop`, blank `default_schema`, `H2Dialect`, `show_sql` off).
- `_Repository_SpringBootIT`: `@SpringBootTest` against the installed schema. Repository calls commit
  unless the test is `@Transactional`. An empty table aborts a test through an assumption rather than
  failing it.

Naming: `<Repository>_DataJpaTest` and `<Repository>_SpringBootIT`, with one `@Nested` class per
method under test (`FindById_Test`, …).

A `JsonMapper` (Jackson 3, `tools.jackson.databind.json.JsonMapper`) is injectable in a
`@SpringBootTest` only; the `@DataJpaTest` slice does not configure Jackson.

Logging is set in `application.yaml`: `org.hibernate.SQL: debug` and `org.hibernate.orm.jdbc.bind:
trace` for statements and bind values, and the module's own `.data` package at `debug` so a test's
`log.debug(...)` prints. No `logback-test.xml` is needed.
