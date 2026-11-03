package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.Time;

import java.time.LocalDateTime;

/**
 * Tests {@link TimeRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 *
 * @see TimeRepository_DataJpaTest
 */
class TimeRepository_SpringBootIT
        extends _Repository_SpringBootIT<TimeRepository, Time, LocalDateTime> {

    /**
     * Creates a new instance.
     */
    TimeRepository_SpringBootIT() {
        super(TimeRepository.class, Time.class, LocalDateTime.class);
    }
}
