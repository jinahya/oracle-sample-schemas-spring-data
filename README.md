# oracle-sample-schemas-spring-data

Spring Data repositories for the [Oracle Database Sample Schemas](https://github.com/oracle-samples/db-sample-schemas),
over the Jakarta Persistence mappings of
[oracle-sample-schemas-persistence](https://github.com/jinahya/oracle-sample-schemas-persistence).

## Modules

| Module                                                                         | Schema          | Upstream                                                 | Entities |
|--------------------------------------------------------------------------------|-----------------|----------------------------------------------------------|---------:|
| [`oracle-sample-schemas-spring-data-co`](oracle-sample-schemas-spring-data-co) | Customer Orders | `io.github.jinahya:oracle-sample-schemas-persistence-co` |        9 |
| [`oracle-sample-schemas-spring-data-hr`](oracle-sample-schemas-spring-data-hr) | Human Resources | `io.github.jinahya:oracle-sample-schemas-persistence-hr` |        8 |
| [`oracle-sample-schemas-spring-data-sh`](oracle-sample-schemas-spring-data-sh) | Sales History   | `io.github.jinahya:oracle-sample-schemas-persistence-sh` |       12 |

Upstream publishes mapped superclasses, not entities, so each module carries its own entities, copied from upstream's
test sources, in `com.github.jinahya.oracle.sample.persistence.<schema>`, and one repository per entity in
`com.github.jinahya.oracle.sample.persistence.<schema>.data`. Most of the repositories, and their tests, are
still empty.

## Requirements

* JDK 25 to build (the jars themselves run on 17)
* Maven 3.9.x or later
* Spring Boot 4.1.x, Spring Data JPA (`provided`: bring it yourself)

The upstream jars are `0.0.6-SNAPSHOT`s that exist only locally. Install them first:

```sh
(cd ../../oracle-sample-schemas-persistence && ./mvnw -DskipTests install)
```

## Build

```sh
mvn clean verify                                    # whole reactor; unit tests only (embedded H2)
mvn -pl oracle-sample-schemas-spring-data-co test
```

### Integration tests

The `*_SpringBootIT` classes run against a real Oracle database and are not run by `verify`/`test`. Start the
container that upstream provides, which serves `freepdb1` on `localhost:1521` with CO, HR and SH installed:

```sh
../../oracle-sample-schemas-persistence/_compose.sh
mvn -pl oracle-sample-schemas-spring-data-co -Dtest='*_SpringBootIT' -Dsurefire.failIfNoSpecifiedTests=false test
```

The tests connect as `dmlonly` (DML only) and never generate DDL.

## Usage

```xml
<dependency>
  <groupId>com.github.jinahya</groupId>
  <artifactId>oracle-sample-schemas-spring-data-co</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

Spring Data JPA is a `provided` dependency, so add `spring-boot-starter-data-jpa` yourself. The entities live outside
the repositories' package, so point entity scanning at them:

```java
@EntityScan(basePackageClasses = com.github.jinahya.oracle.sample.persistence.co.Customer.class)
@EnableJpaRepositories(basePackageClasses = com.github.jinahya.oracle.sample.persistence.co.data.CustomerRepository.class)
```

The tables are unqualified; on Oracle, set `hibernate.default_schema` to the schema unless you connect as its owner.

## Links

### [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)

#### [JPA](https://docs.spring.io/spring-data/jpa/reference/jpa.html)

* [Using JPA Named Queries](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.named-queries)
