package com.github.jinahya.oracle.sample.persistence.hr.data;

import com.github.jinahya.oracle.sample.persistence.hr.Country;

/**
 * Tests {@link CountryRepository} against the installed HR schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 *
 * @see CountryRepository_DataJpaTest
 */
class CountryRepository_SpringBootIT
        extends _Repository_SpringBootIT<CountryRepository, Country, String> {

    /**
     * Creates a new instance.
     */
    CountryRepository_SpringBootIT() {
        super(CountryRepository.class, Country.class, String.class);
    }
}
