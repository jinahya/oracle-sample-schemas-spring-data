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

Only `oracle-sample-schemas-spring-data-co` has repositories so far (`CustomerRepository`,
`ProductRepository`). `hr` and `sh` hold just the test context and a test that boots it.

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
- **Some tables are mapped twice**, one class per id strategy (`*WithEmbeddedId` / `*WithIdClass`):
  `co`'s `ORDER_ITEMS` and `PRODUCT_ORDERS`; `hr`'s `JOB_HISTORY`; `sh`'s `COSTS`, `SALES`,
  `PROFITS`, `FWEEK_PSCAT_SALES_MV`. Only in **`hr`** do the two share an entity name
  (`@Entity(name = "JobHistory")`), so scanning both fails there as a duplicate. In `co` and `sh`
  each class has its own entity name and both load. Every module's test context declares a
  `ManagedClassNameFilter` that drops `*WithIdClass`: required in `hr`, and it keeps one flavour per
  table in the other two.
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
- **`spring-boot-starter-data-jpa` is `provided`**, so consumers of these jars must bring Spring Data
  JPA themselves.
- **Shared dependencies live in the root pom.** A module pom names only its entity artifact, its
  `test-companion` jar and the Oracle driver (`ojdbc17`, test scope), with no versions.
- **Dependency convergence is a build defect**, not a warning, as upstream treats it. Resolve
  `jakarta.*` at the platform level instead of pinning individual API artifacts.
- **Lombok is for test sources only**, on the `default-testCompile` processor path.

## Code conventions

- `oracle-sample-schemas-spring-data-co`'s main package is `@NullMarked` (JSpecify, which Spring
  Framework 7 brings). Spring Data enforces it: a `null` argument to a repository method throws
  `IllegalArgumentException` before any query runs. Return `Optional` for lookups that may find
  nothing.
- Repositories extend `JpaRepository<E, ID>` and `JpaSpecificationExecutor<E>`; criteria queries go
  through `Specification` and the metamodel.

## Tests

Each module's test root has a `___Spring_TestContext`: `@SpringBootConfiguration` +
`@EnableAutoConfiguration`, plus `@EntityScan(basePackageClasses = __NoOp.class)` (the entities are
outside this package, so auto-configuration would not find them) and the `ManagedClassNameFilter`.
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
