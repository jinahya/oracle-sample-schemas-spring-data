package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.Cost;
import com.github.jinahya.oracle.sample.persistence.sh.mapped.CostId;

/**
 * Tests {@link CostRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 *
 * @see CostRepository_DataJpaTest
 */
class CostRepository_SpringBootIT
        extends _Repository_SpringBootIT<CostRepository, Cost, CostId> {

    /**
     * Creates a new instance.
     */
    CostRepository_SpringBootIT() {
        super(CostRepository.class, Cost.class, CostId.class);
    }
}
