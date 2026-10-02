package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Location;

/**
 * Tests {@link LocationRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see LocationRepository_SpringBootIT
 */
class LocationRepository_DataJpaTest
        extends _Repository_DataJpaTest<LocationRepository, Location, Integer> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    LocationRepository_DataJpaTest() {
        super(LocationRepository.class, Location.class, Integer.class);
    }
}
