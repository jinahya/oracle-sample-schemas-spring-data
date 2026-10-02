package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.ProfitId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.ProfitWithEmbeddedId;

/**
 * Tests {@link ProfitWithEmbeddedIdRepository} in a JPA slice, over an embedded H2, through
 * {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see ProfitWithEmbeddedIdRepository_SpringBootIT
 */
class ProfitWithEmbeddedIdRepository_DataJpaTest
        extends _Repository_DataJpaTest<ProfitWithEmbeddedIdRepository, ProfitWithEmbeddedId, ProfitId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ProfitWithEmbeddedIdRepository_DataJpaTest() {
        super(ProfitWithEmbeddedIdRepository.class, ProfitWithEmbeddedId.class, ProfitId.class);
    }
}
