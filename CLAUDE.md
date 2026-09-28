# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Current state

This repository is a **skeleton**: poms and empty source trees, no Java yet, no README, no Maven
wrapper. The git repository has been initialised but holds no commit.

The root is an aggregator (`<packaging>pom</packaging>`) over one module per sample schema, each
named after the upstream artifact it wraps:

```
co-spring-data/   → com.github.jinahya:co
hr-spring-data/   → com.github.jinahya:hr
sh-spring-data/   → com.github.jinahya:sh
```

The upstream project names its schema modules `co` / `hr` / `sh` with no suffix, so the
`-spring-data` suffix is what keeps the two projects' artifacts distinct on one classpath. Sources go
under `com.github.jinahya.oracle.sample.schemas.spring.data.<schema>` — one consistent scheme, unlike
the upstream entity packages below.

What is shared is declared once in the root pom: `spring-boot-starter-data-jpa` (compile) and
`spring-boot-starter-test` (test) in `<dependencies>`, and the three upstream artifacts in
`<dependencyManagement>` under `version.oracle-sample-schemas-persistence`. A module pom therefore
names only its own schema artifact, with no `<version>`.

Everything below describes the decisions the poms encode and the upstream this is built against —
the frame to add code into, not a description of existing code.

## What this project is

Spring Data on top of the Jakarta Persistence entities of
[`oracle-sample-schemas-persistence`](https://github.com/jinahya/oracle-sample-schemas-persistence),
which maps the [Oracle Database Sample Schemas](https://github.com/oracle-samples/db-sample-schemas).

That project is checked out as a sibling directory (`../oracle-sample-schemas-persistence`) and
publishes one artifact per schema, all `com.github.jinahya` / `0.0.1-SNAPSHOT`:

| Artifact | Schema | Entity package |
| --- | --- | --- |
| `co` | Customer Orders | `com.github.jinahya.oracle.sample.schemas.co` |
| `hr` | Human Resources | `com.github.jinahya.oracle.sample.schemas.persistence.hr` |
| `sh` | Sales History | `com.github.jinahya.oracle.sample.schemas.persistence.sh` |
| `test-base` | — | shared test base classes |

The package names are **not uniform**: `co` sits directly under `…schemas`, while `hr` and `sh` sit
under `…schemas.persistence`. Check the actual package before writing a `basePackages` or a
`@EntityScan` value rather than extrapolating from one schema.

These are **SNAPSHOTs that exist only locally**. Before this project can resolve them, run
`./mvnw install` (or `-DskipTests install`) in the sibling checkout; they are already present in
`~/.m2` on this machine.

## Build

There is no wrapper here, so use the Maven on `PATH` (3.9.x):

```sh
mvn clean install                      # whole reactor
mvn -pl hr-spring-data -am test        # one module and what it depends on
mvn -Dtest=SomeTest test               # a single test class
mvn -Dtest=SomeTest#method test
mvn -o -pl hr-spring-data dependency:tree
```

The reactor resolves offline today, and `hr-spring-data` pulls `jakarta.persistence-api` **3.2.0** as
intended. Note that Boot 4.1.1 manages **Hibernate 7.4.5**, while the upstream build runs 7.4.9 — the
same line, but not the same version; if a mapping behaves differently here, check that first.

`maven.compiler.release` is **21**, the floor imposed by the upstream entities' main sources. The JDK
on `PATH` is newer (27) and that is fine; do not lower the release.

## Load-bearing pom decisions

Both are commented in `pom.xml` and neither is a free choice:

- **Spring Boot 4.x (`4.1.1`), not 3.x.** Boot 4 sits on the Jakarta EE 11 baseline and manages
  `jakarta.persistence-api` 3.2.0 — the generation the upstream mappings are written against. Boot
  3.5 and earlier manage 3.1, which does not cover the `@EmbeddedId`/`@IdClass` entities compiled
  against the 3.2 surface. Do not downgrade the line to dodge an upgrade problem.
- **`spring-boot-dependencies` is imported, not inherited.** This pom is deliberately parent-less, so
  `spring-boot-starter-parent` is not in play. Every Spring / Boot / Hibernate version comes from the
  imported BOM in `<dependencyManagement>`; a dependency on any of them must declare
  `groupId`/`artifactId` and scope only, never a `<version>`.

The upstream project treats **dependency convergence as a build defect** rather than a warning, and
anything added here that reaches its classpath is subject to the same standard. Resolve `jakarta.*`
at the platform level instead of pinning individual API artifacts.

## What the upstream entities expect of consumers

Read `../oracle-sample-schemas-persistence/CLAUDE.md` before touching mappings; the parts that
constrain code written *here*:

- **Entities are standalone by design** — no `@MappedSuperclass` hierarchy, no builder layer, each
  class carries its own column constants. Do not introduce a shared superclass or a `mapped` package
  to remove the duplication. (`co` does carry a `…co.mapped.MappedCustomer`, against what its own
  CLAUDE.md states; take the rule as written, and ask before following that one file's example.)
- **The generated static metamodel (`Employee_`, `Customer_`, …) is the way to name attributes** in
  criteria code: `root.get(Customer_.emailAddress)`, not a string. Where a compile-time constant is
  required — `mappedBy`, and other annotation values — use the entity's own `ATTRIBUTE_NAME_*`
  constant instead.
- **Some tables are mapped twice, under one entity name**, one flavour per id strategy:
  `hr`'s `JobHistoryWithEmbeddedId` / `JobHistoryWithIdClass`, and likewise `co`'s `ORDER_ITEMS` and
  `sh`'s `COSTS`, `SALES`, `PROFITS`, `FWEEK_PSCAT_SALES_MV`. Upstream keeps both out of one
  persistence unit with `<exclude-unlisted-classes>true</exclude-unlisted-classes>`. Spring Boot's
  default entity scanning has no such filter, so a unit here that scans a whole schema package picks
  up both flavours and fails as a duplicate entity name — scan explicitly, or exclude one flavour.
- Upstream jars carry **entities and metamodel only**; their `persistence.xml` lives in test
  resources on purpose, so this project supplies its own datasource and JPA configuration.
