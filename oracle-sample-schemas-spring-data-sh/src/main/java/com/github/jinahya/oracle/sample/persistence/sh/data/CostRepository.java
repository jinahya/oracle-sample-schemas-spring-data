package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.Cost;
import com.github.jinahya.oracle.sample.persistence.sh.CostId;
import com.github.jinahya.oracle.sample.persistence.sh.Cost_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Cost}, the {@code COSTS} table of the Sales History schema.
 *
 * @see Cost
 * @see Cost_
 */
@Repository
public interface CostRepository
        extends _Repository<Cost, CostId> {

}
