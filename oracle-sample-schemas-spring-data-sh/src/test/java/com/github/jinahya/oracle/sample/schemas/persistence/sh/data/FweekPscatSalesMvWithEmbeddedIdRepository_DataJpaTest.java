package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMvId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMvWithEmbeddedId;

/**
 * Tests {@link FweekPscatSalesMvWithEmbeddedIdRepository} in a JPA slice, over an embedded H2, through
 * {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see FweekPscatSalesMvWithEmbeddedIdRepository_SpringBootIT
 */
class FweekPscatSalesMvWithEmbeddedIdRepository_DataJpaTest
        extends
        _Repository_DataJpaTest<FweekPscatSalesMvWithEmbeddedIdRepository, FweekPscatSalesMvWithEmbeddedId,
                FweekPscatSalesMvId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    FweekPscatSalesMvWithEmbeddedIdRepository_DataJpaTest() {
        super(FweekPscatSalesMvWithEmbeddedIdRepository.class, FweekPscatSalesMvWithEmbeddedId.class,
              FweekPscatSalesMvId.class);
    }
}
