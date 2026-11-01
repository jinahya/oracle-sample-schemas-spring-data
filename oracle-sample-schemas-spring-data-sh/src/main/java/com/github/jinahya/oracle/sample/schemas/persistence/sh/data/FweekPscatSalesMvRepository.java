package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMv;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMvId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMv_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link FweekPscatSalesMv}, the {@code FWEEK_PSCAT_SALES_MV} materialized view of the Sales History schema.
 *
 * @see FweekPscatSalesMv
 * @see FweekPscatSalesMv_
 */
@Repository
public interface FweekPscatSalesMvRepository
        extends _Repository<FweekPscatSalesMv, FweekPscatSalesMvId> {

}
