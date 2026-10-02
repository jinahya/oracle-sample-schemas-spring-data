package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.SupplementaryDemographics;

/**
 * Tests {@link SupplementaryDemographicsRepository} in a JPA slice, over an embedded H2, through
 * {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see SupplementaryDemographicsRepository_SpringBootIT
 */
class SupplementaryDemographicsRepository_DataJpaTest
        extends _Repository_DataJpaTest<SupplementaryDemographicsRepository, SupplementaryDemographics, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    SupplementaryDemographicsRepository_DataJpaTest() {
        super(SupplementaryDemographicsRepository.class, SupplementaryDemographics.class, Long.class);
    }
}
