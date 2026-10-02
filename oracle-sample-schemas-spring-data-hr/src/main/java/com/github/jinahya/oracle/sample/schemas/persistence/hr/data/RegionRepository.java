package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Region;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.Region_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Region}, the {@code REGIONS} table of the Human Resources schema.
 *
 * @see Region
 * @see Region_
 */
@Repository
public interface RegionRepository
        extends JpaRepository<Region, Long>,
                JpaSpecificationExecutor<Region> {

}
