package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.CalMonthSalesMv;

/**
 * Tests {@link CalMonthSalesMvRepository} in a JPA slice, over an embedded H2, through
 * {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see CalMonthSalesMvRepository_SpringBootIT
 */
class CalMonthSalesMvRepository_DataJpaTest
        extends _Repository_DataJpaTest<CalMonthSalesMvRepository, CalMonthSalesMv, String> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    CalMonthSalesMvRepository_DataJpaTest() {
        super(CalMonthSalesMvRepository.class, CalMonthSalesMv.class, String.class);
    }
}
