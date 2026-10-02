package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Time;

import java.time.LocalDate;

/**
 * Tests {@link TimeRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see TimeRepository_DataJpaTest
 */
class TimeRepository_SpringBootIT
        extends _Repository_SpringBootIT<TimeRepository, Time, LocalDate> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    TimeRepository_SpringBootIT() {
        super(TimeRepository.class, Time.class, LocalDate.class);
    }
}
