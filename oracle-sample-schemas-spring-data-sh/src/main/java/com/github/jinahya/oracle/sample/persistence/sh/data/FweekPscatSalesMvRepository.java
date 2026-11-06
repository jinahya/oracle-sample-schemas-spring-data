package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.FweekPscatSalesMv;
import com.github.jinahya.oracle.sample.persistence.sh.FweekPscatSalesMv_;
import com.github.jinahya.oracle.sample.persistence.sh.mapped.FweekPscatSalesMvId;
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
