package com.github.jinahya.oracle.sample.schemas.persistence.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.OrderItem;
import com.github.jinahya.oracle.sample.schemas.persistence.co.OrderItemId;

/**
 * Tests {@link OrderItemRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see OrderItemRepository_SpringBootIT
 */
class OrderItemRepository_DataJpaTest
        extends _Repository_DataJpaTest<OrderItemRepository, OrderItem, OrderItemId> {

    /**
     * Creates a new instance.
     */
    OrderItemRepository_DataJpaTest() {
        super(OrderItemRepository.class, OrderItem.class, OrderItemId.class);
    }
}
