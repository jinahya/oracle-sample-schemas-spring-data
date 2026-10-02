package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.OrderItemId;
import com.github.jinahya.oracle.sample.schemas.persistence.co.OrderItemWithEmbeddedId;

/**
 * Tests {@link OrderItemWithEmbeddedIdRepository} in a JPA slice, over an embedded H2, through
 * {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see OrderItemWithEmbeddedIdRepository_SpringBootIT
 */
class OrderItemWithEmbeddedIdRepository_DataJpaTest
        extends _Repository_DataJpaTest<OrderItemWithEmbeddedIdRepository, OrderItemWithEmbeddedId, OrderItemId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    OrderItemWithEmbeddedIdRepository_DataJpaTest() {
        super(OrderItemWithEmbeddedIdRepository.class, OrderItemWithEmbeddedId.class, OrderItemId.class);
    }
}
