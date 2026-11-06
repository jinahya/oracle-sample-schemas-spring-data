package com.github.jinahya.oracle.sample.persistence.co.data;

import com.github.jinahya.oracle.sample.persistence.co.OrderItem;
import com.github.jinahya.oracle.sample.persistence.co.mapped.OrderItemId;

/**
 * Tests {@link OrderItemRepository} against the installed CO schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 *
 * @see OrderItemRepository_DataJpaTest
 */
class OrderItemRepository_SpringBootIT
        extends _Repository_SpringBootIT<OrderItemRepository, OrderItem, OrderItemId> {

    /**
     * Creates a new instance.
     */
    OrderItemRepository_SpringBootIT() {
        super(OrderItemRepository.class, OrderItem.class, OrderItemId.class);
    }
}
