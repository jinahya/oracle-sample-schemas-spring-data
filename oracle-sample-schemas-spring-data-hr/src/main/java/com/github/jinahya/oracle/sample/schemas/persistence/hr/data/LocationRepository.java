package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Location;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.Location_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Location}, the {@code LOCATIONS} table of the Human Resources schema.
 *
 * @see Location
 * @see Location_
 */
@Repository
public interface LocationRepository
        extends JpaRepository<Location, Integer>,
                JpaSpecificationExecutor<Location> {

}
