package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.SupplementaryDemographics;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.SupplementaryDemographics_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link SupplementaryDemographics}, the {@code SUPPLEMENTARY_DEMOGRAPHICS} table of the Sales History
 * schema.
 *
 * @see SupplementaryDemographics
 * @see SupplementaryDemographics_
 */
@Repository
public interface SupplementaryDemographicsRepository
        extends JpaRepository<SupplementaryDemographics, Long>,
                JpaSpecificationExecutor<SupplementaryDemographics> {

}
