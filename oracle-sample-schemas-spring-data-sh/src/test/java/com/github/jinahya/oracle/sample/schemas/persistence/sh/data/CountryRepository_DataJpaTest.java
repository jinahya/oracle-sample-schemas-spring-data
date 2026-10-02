package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Country;

/**
 * Tests {@link CountryRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see CountryRepository_SpringBootIT
 */
class CountryRepository_DataJpaTest
        extends _Repository_DataJpaTest<CountryRepository, Country, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    CountryRepository_DataJpaTest() {
        super(CountryRepository.class, Country.class, Long.class);
    }
}
