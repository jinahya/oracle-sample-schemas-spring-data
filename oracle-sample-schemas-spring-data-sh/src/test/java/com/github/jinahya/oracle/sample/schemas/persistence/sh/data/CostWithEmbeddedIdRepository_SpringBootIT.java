package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.CostId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.CostWithEmbeddedId;

/**
 * Tests {@link CostWithEmbeddedIdRepository} against the installed SH schema, through
 * {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see CostWithEmbeddedIdRepository_DataJpaTest
 */
class CostWithEmbeddedIdRepository_SpringBootIT
        extends _Repository_SpringBootIT<CostWithEmbeddedIdRepository, CostWithEmbeddedId, CostId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    CostWithEmbeddedIdRepository_SpringBootIT() {
        super(CostWithEmbeddedIdRepository.class, CostWithEmbeddedId.class, CostId.class);
    }
}
