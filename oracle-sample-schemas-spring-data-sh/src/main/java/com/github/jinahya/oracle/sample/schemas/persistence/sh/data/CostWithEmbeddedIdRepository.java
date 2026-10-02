package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.CostId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.CostWithEmbeddedId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.CostWithEmbeddedId_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link CostWithEmbeddedId}, the {@code COSTS} table of the Sales History schema.
 * <p>
 * The table is mapped twice upstream; this is the {@code @EmbeddedId} flavour. The {@code @IdClass} one,
 * {@code CostWithIdClass}, has no repository here, as the test context leaves it out of the persistence unit.
 *
 * @see CostWithEmbeddedId
 * @see CostWithEmbeddedId_
 */
@Repository
public interface CostWithEmbeddedIdRepository
        extends JpaRepository<CostWithEmbeddedId, CostId>,
                JpaSpecificationExecutor<CostWithEmbeddedId> {

}
