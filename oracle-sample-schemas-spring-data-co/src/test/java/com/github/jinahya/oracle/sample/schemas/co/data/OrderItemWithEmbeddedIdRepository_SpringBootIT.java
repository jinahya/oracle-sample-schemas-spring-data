package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.OrderItemId;
import com.github.jinahya.oracle.sample.schemas.persistence.co.OrderItemWithEmbeddedId;

/**
 * Tests {@link OrderItemWithEmbeddedIdRepository} against the installed CO schema, through
 * {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 *
 * @see OrderItemWithEmbeddedIdRepository_DataJpaTest
 */
class OrderItemWithEmbeddedIdRepository_SpringBootIT
        extends _Repository_SpringBootIT<OrderItemWithEmbeddedIdRepository, OrderItemWithEmbeddedId, OrderItemId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    OrderItemWithEmbeddedIdRepository_SpringBootIT() {
        super(OrderItemWithEmbeddedIdRepository.class, OrderItemWithEmbeddedId.class, OrderItemId.class);
    }
}
