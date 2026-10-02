package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Region;

/**
 * Tests {@link RegionRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see RegionRepository_SpringBootIT
 */
class RegionRepository_DataJpaTest
        extends _Repository_DataJpaTest<RegionRepository, Region, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    RegionRepository_DataJpaTest() {
        super(RegionRepository.class, Region.class, Long.class);
    }
}
