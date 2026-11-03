package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.Product;

/**
 * Tests {@link ProductRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 *
 * @see ProductRepository_DataJpaTest
 */
class ProductRepository_SpringBootIT
        extends _Repository_SpringBootIT<ProductRepository, Product, Integer> {

    /**
     * Creates a new instance.
     */
    ProductRepository_SpringBootIT() {
        super(ProductRepository.class, Product.class, Integer.class);
    }
}
