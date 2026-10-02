# oracle-sample-schemas-spring-data

Spring Data repositories for the [Oracle Database Sample Schemas](https://github.com/oracle-samples/db-sample-schemas),
built on the Jakarta Persistence entities of
[oracle-sample-schemas-persistence](https://github.com/jinahya/oracle-sample-schemas-persistence).

## Modules

| Module                                                                   | Schema            | Entities                                         |
|--------------------------------------------------------------------------|-------------------|--------------------------------------------------|
| [`oracle-sample-schemas-spring-data-co`](oracle-sample-schemas-spring-data-co) | Customer Orders   | `io.github.jinahya:oracle-sample-schemas-persistence-co` |
| [`oracle-sample-schemas-spring-data-hr`](oracle-sample-schemas-spring-data-hr) | Human Resources   | `io.github.jinahya:oracle-sample-schemas-persistence-hr` |
| [`oracle-sample-schemas-spring-data-sh`](oracle-sample-schemas-spring-data-sh) | Sales History     | `io.github.jinahya:oracle-sample-schemas-persistence-sh` |

Each module has one repository per upstream entity; most of them, and their tests, are still empty.

## Requirements

* JDK 25 (the upstream entities need 21 at least)
* Maven 3.9.x
* Spring Boot 4.1.x, Spring Data JPA (`provided`: bring it yourself)

The upstream entity jars are `0.0.1-SNAPSHOT`s that exist only locally. Install them first:

```sh
(cd ../oracle-sample-schemas-persistence && ./mvnw -DskipTests install)
```

## Build

```sh
mvn clean install                                   # whole reactor; unit tests only (embedded H2)
mvn -pl oracle-sample-schemas-spring-data-co -am test
```

### Integration tests

The `*_SpringBootIT` classes run against a real Oracle database and are not run by `install`/`test`. Start the
container that upstream provides, which serves `freepdb1` on `localhost:1521` with CO, HR and SH installed:

```sh
../oracle-sample-schemas-persistence/_docker-compose-up.sh
mvn -pl oracle-sample-schemas-spring-data-co -Dtest='*_SpringBootIT' test
```

The tests connect as `dmlonly` (DML only) and never generate DDL.

### jOOQ classes (tests only)

Each module has jOOQ classes, generated from the live schema, under `src/test/java-jooq`. Regenerate them with the
container running:

```sh
./_mvn_jooq-codegen.sh
```

## Links

### [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)

#### [JPA](https://docs.spring.io/spring-data/jpa/reference/jpa.html)

* [Using JPA Named Queries](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.named-queries)
