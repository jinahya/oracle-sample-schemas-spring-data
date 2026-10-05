# oracle-sample-schemas-spring-data-co

Spring Data repositories for the
[Customer Orders](https://github.com/oracle-samples/db-sample-schemas/tree/main/customer_orders) (`CO`) schema, over
the entities of `io.github.jinahya:oracle-sample-schemas-persistence-co`.

Package: `com.github.jinahya.oracle.sample.schemas.co.data`

## Repositories

| Repository                           | Entity                       | Table / view              | Id               |
|--------------------------------------|------------------------------|---------------------------|------------------|
| `CustomerRepository`                 | `Customer`                   | `CUSTOMERS`               | `Long`           |
| `CustomerOrderProductRepository`     | `CustomerOrderProduct`       | `CUSTOMER_ORDER_PRODUCTS` | `Long`           |
| `InventoryRepository`                | `Inventory`                  | `INVENTORY`               | `Long`           |
| `OrderRepository`                    | `Order`                      | `ORDERS`                  | `Long`           |
| `OrderItemWithEmbeddedIdRepository`  | `OrderItemWithEmbeddedId`    | `ORDER_ITEMS`             | `OrderItemId`    |
| `ProductRepository`                  | `Product`                    | `PRODUCTS`                | `Long`           |
| `ProductOrderRepository`             | `ProductOrder`               | `PRODUCT_ORDERS`          | `ProductOrderId` |
| `ShipmentRepository`                 | `Shipment`                   | `SHIPMENTS`               | `Long`           |
| `StoreRepository`                    | `Store`                      | `STORES`                  | `Long`           |

Only `CustomerRepository` declares methods so far. Each extends `JpaRepository` and `JpaSpecificationExecutor`. Name attributes through the static metamodel:

```java
customerRepository.findAll(
        (root, query, builder) -> builder.equal(root.get(Customer_.fullName), name));
```

`CustomerRepository` also runs a named query of `Customer` by method name:

```java
Optional<Customer> customer = customerRepository.selectOneByEmailAddress("someone@example.com");
```

The package is `@NullMarked`: a `null` argument to a repository method throws `IllegalArgumentException`.

## Usage

```xml
<dependency>
  <groupId>com.github.jinahya</groupId>
  <artifactId>oracle-sample-schemas-spring-data-co</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

Spring Data JPA is a `provided` dependency, so add `spring-boot-starter-data-jpa` yourself. The entities live outside
this package, so point entity scanning at them:

```java
@EntityScan(basePackageClasses = com.github.jinahya.oracle.sample.schemas.persistence.co.__NoOp.class)
@EnableJpaRepositories(basePackageClasses = CustomerRepository.class)
```

`ORDER_ITEMS` is mapped twice (`OrderItemWithEmbeddedId` and `OrderItemWithIdClass`); both load, but you will
usually want only one of them. The test context drops `*WithIdClass` with a `ManagedClassNameFilter`.

The views with no key (`PRODUCT_REVIEWS`, `STORE_ORDERS`) have no entity and no repository.

## Tests

```sh
mvn -pl oracle-sample-schemas-spring-data-co -am test                       # *_DataJpaTest, embedded H2
mvn -pl oracle-sample-schemas-spring-data-co -Dtest='*_SpringBootIT' test   # needs the Oracle container
```

See the [root README](../README.md) for the Oracle container.
