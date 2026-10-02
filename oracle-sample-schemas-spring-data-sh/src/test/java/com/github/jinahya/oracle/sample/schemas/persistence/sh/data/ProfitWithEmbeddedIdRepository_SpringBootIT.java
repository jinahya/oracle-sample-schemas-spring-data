package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.ProfitId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.ProfitWithEmbeddedId;

/**
 * Tests {@link ProfitWithEmbeddedIdRepository} against the installed SH schema, through
 * {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see ProfitWithEmbeddedIdRepository_DataJpaTest
 */
class ProfitWithEmbeddedIdRepository_SpringBootIT
        extends _Repository_SpringBootIT<ProfitWithEmbeddedIdRepository, ProfitWithEmbeddedId, ProfitId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ProfitWithEmbeddedIdRepository_SpringBootIT() {
        super(ProfitWithEmbeddedIdRepository.class, ProfitWithEmbeddedId.class, ProfitId.class);
    }
}
