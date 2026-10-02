package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.CustomerOrderProduct;

/**
 * Tests {@link CustomerOrderProductRepository} in a JPA slice, over an embedded H2, through
 * {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see CustomerOrderProductRepository_SpringBootIT
 */
class CustomerOrderProductRepository_DataJpaTest
        extends _Repository_DataJpaTest<CustomerOrderProductRepository, CustomerOrderProduct, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    CustomerOrderProductRepository_DataJpaTest() {
        super(CustomerOrderProductRepository.class, CustomerOrderProduct.class, Long.class);
    }
}
