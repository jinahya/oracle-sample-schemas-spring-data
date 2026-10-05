package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Cost;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.CostId;

/**
 * Tests {@link CostRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see CostRepository_SpringBootIT
 */
class CostRepository_DataJpaTest
        extends _Repository_DataJpaTest<CostRepository, Cost, CostId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    CostRepository_DataJpaTest() {
        super(CostRepository.class, Cost.class, CostId.class);
    }
}
