package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.SupplementaryDemographics;

/**
 * Tests {@link SupplementaryDemographicsRepository} against the installed SH schema, through
 * {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see SupplementaryDemographicsRepository_DataJpaTest
 */
class SupplementaryDemographicsRepository_SpringBootIT
        extends _Repository_SpringBootIT<SupplementaryDemographicsRepository, SupplementaryDemographics, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    SupplementaryDemographicsRepository_SpringBootIT() {
        super(SupplementaryDemographicsRepository.class, SupplementaryDemographics.class, Long.class);
    }
}
