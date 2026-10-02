package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.SaleId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.SaleWithEmbeddedId;

/**
 * Tests {@link SaleWithEmbeddedIdRepository} against the installed SH schema, through
 * {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see SaleWithEmbeddedIdRepository_DataJpaTest
 */
class SaleWithEmbeddedIdRepository_SpringBootIT
        extends _Repository_SpringBootIT<SaleWithEmbeddedIdRepository, SaleWithEmbeddedId, SaleId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    SaleWithEmbeddedIdRepository_SpringBootIT() {
        super(SaleWithEmbeddedIdRepository.class, SaleWithEmbeddedId.class, SaleId.class);
    }
}
