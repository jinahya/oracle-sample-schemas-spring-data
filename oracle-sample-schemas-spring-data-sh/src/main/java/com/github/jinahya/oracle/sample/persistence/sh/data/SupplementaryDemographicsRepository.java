package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.SupplementaryDemographics;
import com.github.jinahya.oracle.sample.persistence.sh.SupplementaryDemographics_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link SupplementaryDemographics}, the {@code SUPPLEMENTARY_DEMOGRAPHICS} table of the Sales History schema.
 *
 * @see SupplementaryDemographics
 * @see SupplementaryDemographics_
 */
@Repository
public interface SupplementaryDemographicsRepository
        extends _Repository<SupplementaryDemographics, Long> {

}
