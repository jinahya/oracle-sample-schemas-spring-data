package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMv;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMvId;

/**
 * Tests {@link FweekPscatSalesMvRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see FweekPscatSalesMvRepository_DataJpaTest
 */
class FweekPscatSalesMvRepository_SpringBootIT
        extends _Repository_SpringBootIT<FweekPscatSalesMvRepository, FweekPscatSalesMv, FweekPscatSalesMvId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    FweekPscatSalesMvRepository_SpringBootIT() {
        super(FweekPscatSalesMvRepository.class, FweekPscatSalesMv.class, FweekPscatSalesMvId.class);
    }
}
