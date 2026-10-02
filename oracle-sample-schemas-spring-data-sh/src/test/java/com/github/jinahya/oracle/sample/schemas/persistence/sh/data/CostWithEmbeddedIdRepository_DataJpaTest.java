package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.CostId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.CostWithEmbeddedId;

/**
 * Tests {@link CostWithEmbeddedIdRepository} in a JPA slice, over an embedded H2, through
 * {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see CostWithEmbeddedIdRepository_SpringBootIT
 */
class CostWithEmbeddedIdRepository_DataJpaTest
        extends _Repository_DataJpaTest<CostWithEmbeddedIdRepository, CostWithEmbeddedId, CostId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    CostWithEmbeddedIdRepository_DataJpaTest() {
        super(CostWithEmbeddedIdRepository.class, CostWithEmbeddedId.class, CostId.class);
    }
}
