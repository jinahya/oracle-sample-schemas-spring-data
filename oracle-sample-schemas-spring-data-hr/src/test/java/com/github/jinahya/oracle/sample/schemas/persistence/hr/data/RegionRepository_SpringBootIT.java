package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Region;

/**
 * Tests {@link RegionRepository} against the installed HR schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the HR schema installed; see that file.
 *
 * @see RegionRepository_DataJpaTest
 */
class RegionRepository_SpringBootIT
        extends _Repository_SpringBootIT<RegionRepository, Region, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    RegionRepository_SpringBootIT() {
        super(RegionRepository.class, Region.class, Long.class);
    }
}
