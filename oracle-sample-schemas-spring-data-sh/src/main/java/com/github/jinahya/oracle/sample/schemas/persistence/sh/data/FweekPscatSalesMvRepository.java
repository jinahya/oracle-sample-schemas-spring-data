package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMv;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMvId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMv_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link FweekPscatSalesMv}, the {@code FWEEK_PSCAT_SALES_MV} materialized view of the
 * Sales History schema.
 * <p>
 * {@code FWEEK_PSCAT_SALES_MV} is a materialized view, not a table; the {@code save} and {@code delete} methods
 * inherited here have nothing to write to.
 *
 * @see FweekPscatSalesMv
 * @see FweekPscatSalesMv_
 */
@Repository
public interface FweekPscatSalesMvRepository
        extends JpaRepository<FweekPscatSalesMv, FweekPscatSalesMvId>,
                JpaSpecificationExecutor<FweekPscatSalesMv> {

}
