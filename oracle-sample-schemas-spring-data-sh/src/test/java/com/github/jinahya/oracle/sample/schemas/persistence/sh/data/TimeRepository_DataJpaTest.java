package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Time;

import java.time.LocalDate;

/**
 * Tests {@link TimeRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see TimeRepository_SpringBootIT
 */
class TimeRepository_DataJpaTest
        extends _Repository_DataJpaTest<TimeRepository, Time, LocalDate> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    TimeRepository_DataJpaTest() {
        super(TimeRepository.class, Time.class, LocalDate.class);
    }
}
