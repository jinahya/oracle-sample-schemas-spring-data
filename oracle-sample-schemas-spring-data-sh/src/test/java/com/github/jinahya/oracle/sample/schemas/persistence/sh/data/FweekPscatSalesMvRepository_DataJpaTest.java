package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMv;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMvId;

/**
 * Tests {@link FweekPscatSalesMvRepository} in a JPA slice, over an embedded H2, through
 * {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see FweekPscatSalesMvRepository_SpringBootIT
 */
class FweekPscatSalesMvRepository_DataJpaTest
        extends _Repository_DataJpaTest<FweekPscatSalesMvRepository, FweekPscatSalesMv, FweekPscatSalesMvId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    FweekPscatSalesMvRepository_DataJpaTest() {
        super(FweekPscatSalesMvRepository.class, FweekPscatSalesMv.class, FweekPscatSalesMvId.class);
    }
}
