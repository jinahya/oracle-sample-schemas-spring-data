package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Order;

/**
 * Tests {@link OrderRepository} against the installed CO schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 *
 * @see OrderRepository_DataJpaTest
 */
class OrderRepository_SpringBootIT
        extends _Repository_SpringBootIT<OrderRepository, Order, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    OrderRepository_SpringBootIT() {
        super(OrderRepository.class, Order.class, Long.class);
    }
}
