package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Order;

/**
 * Tests {@link OrderRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see OrderRepository_SpringBootIT
 */
class OrderRepository_DataJpaTest
        extends _Repository_DataJpaTest<OrderRepository, Order, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    OrderRepository_DataJpaTest() {
        super(OrderRepository.class, Order.class, Long.class);
    }
}
