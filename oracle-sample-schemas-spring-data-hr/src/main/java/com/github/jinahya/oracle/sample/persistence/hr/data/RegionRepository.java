package com.github.jinahya.oracle.sample.persistence.hr.data;

import com.github.jinahya.oracle.sample.persistence.hr.Region;
import com.github.jinahya.oracle.sample.persistence.hr.Region_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Region}, the {@code REGIONS} table of the Human Resources schema.
 *
 * @see Region
 * @see Region_
 */
@Repository
public interface RegionRepository
        extends _Repository<Region, Long> {

}
