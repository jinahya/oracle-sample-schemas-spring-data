package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Customer;

/**
 * Tests {@link CustomerRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see CustomerRepository_DataJpaTest
 */
class CustomerRepository_SpringBootIT
        extends _Repository_SpringBootIT<CustomerRepository, Customer, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    CustomerRepository_SpringBootIT() {
        super(CustomerRepository.class, Customer.class, Long.class);
    }
}
