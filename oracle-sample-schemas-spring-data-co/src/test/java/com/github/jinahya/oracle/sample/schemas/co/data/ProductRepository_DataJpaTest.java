package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Product;

/**
 * Tests {@link ProductRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see ProductRepository_SpringBootIT
 */
class ProductRepository_DataJpaTest
        extends _Repository_DataJpaTest<ProductRepository, Product, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ProductRepository_DataJpaTest() {
        super(ProductRepository.class, Product.class, Long.class);
    }
}