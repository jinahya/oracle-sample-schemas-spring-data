package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Profit;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.ProfitId;

/**
 * Tests {@link ProfitRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see ProfitRepository_DataJpaTest
 */
class ProfitRepository_SpringBootIT
        extends _Repository_SpringBootIT<ProfitRepository, Profit, ProfitId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ProfitRepository_SpringBootIT() {
        super(ProfitRepository.class, Profit.class, ProfitId.class);
    }
}
