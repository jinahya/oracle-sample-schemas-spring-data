package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrderId;
import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrderWithEmbeddedId;

/**
 * Tests {@link ProductOrderWithEmbeddedIdRepository} against the installed CO schema, through
 * {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 *
 * @see ProductOrderWithEmbeddedIdRepository_DataJpaTest
 */
class ProductOrderWithEmbeddedIdRepository_SpringBootIT
        extends
        _Repository_SpringBootIT<ProductOrderWithEmbeddedIdRepository, ProductOrderWithEmbeddedId, ProductOrderId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ProductOrderWithEmbeddedIdRepository_SpringBootIT() {
        super(ProductOrderWithEmbeddedIdRepository.class, ProductOrderWithEmbeddedId.class, ProductOrderId.class);
    }
}
