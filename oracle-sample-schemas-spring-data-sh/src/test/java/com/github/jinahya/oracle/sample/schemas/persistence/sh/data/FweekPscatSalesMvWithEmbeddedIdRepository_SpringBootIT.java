package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMvId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMvWithEmbeddedId;

/**
 * Tests {@link FweekPscatSalesMvWithEmbeddedIdRepository} against the installed SH schema, through
 * {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see FweekPscatSalesMvWithEmbeddedIdRepository_DataJpaTest
 */
class FweekPscatSalesMvWithEmbeddedIdRepository_SpringBootIT
        extends
        _Repository_SpringBootIT<FweekPscatSalesMvWithEmbeddedIdRepository, FweekPscatSalesMvWithEmbeddedId,
                FweekPscatSalesMvId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    FweekPscatSalesMvWithEmbeddedIdRepository_SpringBootIT() {
        super(FweekPscatSalesMvWithEmbeddedIdRepository.class, FweekPscatSalesMvWithEmbeddedId.class,
              FweekPscatSalesMvId.class);
    }
}
