package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrderId;
import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrderWithEmbeddedId;

/**
 * Tests {@link ProductOrderWithEmbeddedIdRepository} in a JPA slice, over an embedded H2, through
 * {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see ProductOrderWithEmbeddedIdRepository_SpringBootIT
 */
class ProductOrderWithEmbeddedIdRepository_DataJpaTest
        extends
        _Repository_DataJpaTest<ProductOrderWithEmbeddedIdRepository, ProductOrderWithEmbeddedId, ProductOrderId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ProductOrderWithEmbeddedIdRepository_DataJpaTest() {
        super(ProductOrderWithEmbeddedIdRepository.class, ProductOrderWithEmbeddedId.class, ProductOrderId.class);
    }
}
