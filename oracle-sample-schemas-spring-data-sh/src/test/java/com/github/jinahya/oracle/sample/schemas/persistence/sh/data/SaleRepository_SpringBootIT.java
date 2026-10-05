package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Sale;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.SaleId;

/**
 * Tests {@link SaleRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see SaleRepository_DataJpaTest
 */
class SaleRepository_SpringBootIT
        extends _Repository_SpringBootIT<SaleRepository, Sale, SaleId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    SaleRepository_SpringBootIT() {
        super(SaleRepository.class, Sale.class, SaleId.class);
    }
}
