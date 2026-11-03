# oracle-sample-schemas-spring-data-co

Spring Data repositories for the
[Customer Orders](https://github.com/oracle-samples/db-sample-schemas/tree/main/customer_orders) (`CO`) schema, over
the mapped superclasses of `io.github.jinahya:oracle-sample-schemas-persistence-co`.

- Entities: `com.github.jinahya.oracle.sample.persistence.co`, copied from upstream's test sources
- Repositories: `com.github.jinahya.oracle.sample.persistence.co.data`

## Repositories

| Repository | Entity | Table / view | Id |
|---|---|---|---|
| `CustomerRepository` | `Customer` | `CUSTOMERS` | `Long` |
| `CustomerOrderProductRepository` | `CustomerOrderProduct` | `CUSTOMER_ORDER_PRODUCTS` (view) | `Long` |
| `InventoryRepository` | `Inventory` | `INVENTORY` | `Long` |
| `OrderRepository` | `Order` | `ORDERS` | `Long` |
| `OrderItemRepository` | `OrderItem` | `ORDER_ITEMS` | `OrderItemId` |
| `ProductRepository` | `Product` | `PRODUCTS` | `Long` |
| `ProductOrderRepository` | `ProductOrder` | `PRODUCT_ORDERS` (view) | `ProductOrderId` |
| `ShipmentRepository` | `Shipment` | `SHIPMENTS` | `Long` |
| `StoreRepository` | `Store` | `STORES` | `Long` |

Each extends `_Repository` (`JpaRepository` and `JpaSpecificationExecutor`). Name attributes through the static
metamodel (`Customer_`, ...).

`CustomerRepository`, `InventoryRepository`, `OrderRepository` and `ProductRepository` declare methods; the rest are
empty. `CustomerRepository` also runs a named query of `Customer` by method name:

```java
Optional<Customer> customer = customerRepository.selectOneByEmailAddress("someone@example.com");
```

The keyless views `PRODUCT_REVIEWS` and `STORE_ORDERS`, which upstream maps as plain classes rather than entities,
have no repository.

The package is `@NullMarked`: a `null` argument to a repository method throws `IllegalArgumentException`.

## Usage

Spring Data JPA is a `provided` dependency, so add `spring-boot-starter-data-jpa` yourself, and point entity scanning
at the entities' package:

```java
@EntityScan(basePackageClasses = com.github.jinahya.oracle.sample.persistence.co.Customer.class)
```

## Tests

```sh
mvn -pl oracle-sample-schemas-spring-data-co test                          # *_DataJpaTest, embedded H2
mvn -pl oracle-sample-schemas-spring-data-co -Dtest='*_SpringBootIT' -Dsurefire.failIfNoSpecifiedTests=false test
```

The second needs the Oracle container; see the [root README](../README.md).
