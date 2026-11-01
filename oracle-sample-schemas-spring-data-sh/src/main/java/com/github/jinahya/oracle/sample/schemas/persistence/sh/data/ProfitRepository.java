package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Profit;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.ProfitId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.Profit_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Profit}, the {@code PROFITS} view of the Sales History schema.
 *
 * @see Profit
 * @see Profit_
 */
@Repository
public interface ProfitRepository
        extends _Repository<Profit, ProfitId> {

}
