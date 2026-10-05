package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Cost;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.CostId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.Cost_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Cost}, the {@code COSTS} table of the Sales History schema.
 *
 * @see Cost
 * @see Cost_
 */
@Repository
public interface CostRepository
        extends JpaRepository<Cost, CostId>,
                JpaSpecificationExecutor<Cost> {

}
