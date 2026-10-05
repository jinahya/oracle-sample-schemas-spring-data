package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrder;
import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrderId;

/**
 * Tests {@link ProductOrderRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see ProductOrderRepository_SpringBootIT
 */
class ProductOrderRepository_DataJpaTest
        extends _Repository_DataJpaTest<ProductOrderRepository, ProductOrder, ProductOrderId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ProductOrderRepository_DataJpaTest() {
        super(ProductOrderRepository.class, ProductOrder.class, ProductOrderId.class);
    }
}
