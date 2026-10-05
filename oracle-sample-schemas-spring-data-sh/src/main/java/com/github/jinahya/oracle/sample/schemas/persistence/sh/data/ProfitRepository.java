package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Profit;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.ProfitId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.Profit_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Profit}, the {@code PROFITS} view of the Sales History schema.
 * <p>
 * {@code PROFITS} is a view, not a table; the {@code save} and {@code delete} methods inherited here have nothing to
 * write to.
 *
 * @see Profit
 * @see Profit_
 */
@Repository
public interface ProfitRepository
        extends JpaRepository<Profit, ProfitId>,
                JpaSpecificationExecutor<Profit> {

}
