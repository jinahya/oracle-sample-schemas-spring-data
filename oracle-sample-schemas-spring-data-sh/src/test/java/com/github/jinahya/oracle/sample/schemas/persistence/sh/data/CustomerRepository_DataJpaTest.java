package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Customer;

/**
 * Tests {@link CustomerRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see CustomerRepository_SpringBootIT
 */
class CustomerRepository_DataJpaTest
        extends _Repository_DataJpaTest<CustomerRepository, Customer, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    CustomerRepository_DataJpaTest() {
        super(CustomerRepository.class, Customer.class, Long.class);
    }
}
