package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.CalMonthSalesMv;

/**
 * Tests {@link CalMonthSalesMvRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see CalMonthSalesMvRepository_DataJpaTest
 */
class CalMonthSalesMvRepository_SpringBootIT
        extends _Repository_SpringBootIT<CalMonthSalesMvRepository, CalMonthSalesMv, String> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    CalMonthSalesMvRepository_SpringBootIT() {
        super(CalMonthSalesMvRepository.class, CalMonthSalesMv.class, String.class);
    }
}
