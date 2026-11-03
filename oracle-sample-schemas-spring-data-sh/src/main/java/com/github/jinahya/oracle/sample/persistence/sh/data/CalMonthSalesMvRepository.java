package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.CalMonthSalesMv;
import com.github.jinahya.oracle.sample.persistence.sh.CalMonthSalesMv_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link CalMonthSalesMv}, the {@code CAL_MONTH_SALES_MV} materialized view of the Sales History schema.
 *
 * @see CalMonthSalesMv
 * @see CalMonthSalesMv_
 */
@Repository
public interface CalMonthSalesMvRepository
        extends _Repository<CalMonthSalesMv, String> {

}
