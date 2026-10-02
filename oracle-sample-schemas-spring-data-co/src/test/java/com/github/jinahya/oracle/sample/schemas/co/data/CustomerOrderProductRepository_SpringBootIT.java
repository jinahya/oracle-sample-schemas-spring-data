package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.CustomerOrderProduct;

/**
 * Tests {@link CustomerOrderProductRepository} against the installed CO schema, through
 * {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 *
 * @see CustomerOrderProductRepository_DataJpaTest
 */
class CustomerOrderProductRepository_SpringBootIT
        extends _Repository_SpringBootIT<CustomerOrderProductRepository, CustomerOrderProduct, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    CustomerOrderProductRepository_SpringBootIT() {
        super(CustomerOrderProductRepository.class, CustomerOrderProduct.class, Long.class);
    }
}
