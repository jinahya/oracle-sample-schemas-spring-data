package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.Sales;
import com.github.jinahya.oracle.sample.persistence.sh.mapped.SalesId;

/**
 * Tests {@link SalesRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see SalesRepository_SpringBootIT
 */
class SalesRepository_DataJpaTest
        extends _Repository_DataJpaTest<SalesRepository, Sales, SalesId> {

    /**
     * Creates a new instance.
     */
    SalesRepository_DataJpaTest() {
        super(SalesRepository.class, Sales.class, SalesId.class);
    }
}
