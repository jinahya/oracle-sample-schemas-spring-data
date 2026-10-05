package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrder;
import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrderId;

/**
 * Tests {@link ProductOrderRepository} against the installed CO schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 *
 * @see ProductOrderRepository_DataJpaTest
 */
class ProductOrderRepository_SpringBootIT
        extends _Repository_SpringBootIT<ProductOrderRepository, ProductOrder, ProductOrderId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ProductOrderRepository_SpringBootIT() {
        super(ProductOrderRepository.class, ProductOrder.class, ProductOrderId.class);
    }
}
