package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.CalMonthSalesMv;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.CalMonthSalesMv_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link CalMonthSalesMv}, the {@code CAL_MONTH_SALES_MV} materialized view of the Sales History
 * schema.
 * <p>
 * {@code CAL_MONTH_SALES_MV} is a materialized view, not a table; the {@code save} and {@code delete} methods inherited
 * here have nothing to write to.
 *
 * @see CalMonthSalesMv
 * @see CalMonthSalesMv_
 */
@Repository
public interface CalMonthSalesMvRepository
        extends JpaRepository<CalMonthSalesMv, String>,
                JpaSpecificationExecutor<CalMonthSalesMv> {

}
