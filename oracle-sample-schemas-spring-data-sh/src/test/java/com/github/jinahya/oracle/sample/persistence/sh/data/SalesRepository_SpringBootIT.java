package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.Sales;
import com.github.jinahya.oracle.sample.persistence.sh.mapped.SalesId;

/**
 * Tests {@link SalesRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 *
 * @see SalesRepository_DataJpaTest
 */
class SalesRepository_SpringBootIT
        extends _Repository_SpringBootIT<SalesRepository, Sales, SalesId> {

    /**
     * Creates a new instance.
     */
    SalesRepository_SpringBootIT() {
        super(SalesRepository.class, Sales.class, SalesId.class);
    }
}
