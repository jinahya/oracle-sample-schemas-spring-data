package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Time;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.Time_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * A repository for {@link Time}, the {@code TIMES} table of the Sales History schema.
 *
 * @see Time
 * @see Time_
 */
@Repository
public interface TimeRepository
        extends JpaRepository<Time, LocalDate>,
                JpaSpecificationExecutor<Time> {

}
