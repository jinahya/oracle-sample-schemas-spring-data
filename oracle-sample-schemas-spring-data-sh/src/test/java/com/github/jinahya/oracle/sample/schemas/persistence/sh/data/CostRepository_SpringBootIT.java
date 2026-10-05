package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Cost;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.CostId;

/**
 * Tests {@link CostRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see CostRepository_DataJpaTest
 */
class CostRepository_SpringBootIT
        extends _Repository_SpringBootIT<CostRepository, Cost, CostId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    CostRepository_SpringBootIT() {
        super(CostRepository.class, Cost.class, CostId.class);
    }
}
