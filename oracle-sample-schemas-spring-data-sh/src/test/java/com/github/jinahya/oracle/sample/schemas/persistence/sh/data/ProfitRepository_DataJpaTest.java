package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Profit;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.ProfitId;

/**
 * Tests {@link ProfitRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see ProfitRepository_SpringBootIT
 */
class ProfitRepository_DataJpaTest
        extends _Repository_DataJpaTest<ProfitRepository, Profit, ProfitId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ProfitRepository_DataJpaTest() {
        super(ProfitRepository.class, Profit.class, ProfitId.class);
    }
}
