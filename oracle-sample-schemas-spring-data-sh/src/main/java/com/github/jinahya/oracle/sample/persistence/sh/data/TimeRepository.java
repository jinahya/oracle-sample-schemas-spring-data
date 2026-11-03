package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.Time;
import com.github.jinahya.oracle.sample.persistence.sh.Time_;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * A repository for {@link Time}, the {@code TIMES} table of the Sales History schema.
 *
 * @see Time
 * @see Time_
 */
@Repository
public interface TimeRepository
        extends _Repository<Time, LocalDateTime> {

}
