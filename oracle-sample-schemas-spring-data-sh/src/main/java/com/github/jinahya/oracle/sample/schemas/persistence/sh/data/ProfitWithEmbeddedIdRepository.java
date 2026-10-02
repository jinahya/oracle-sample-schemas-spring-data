package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.ProfitId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.ProfitWithEmbeddedId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.ProfitWithEmbeddedId_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link ProfitWithEmbeddedId}, the {@code PROFITS} view of the Sales History schema.
 * <p>
 * {@code PROFITS} is a view, not a table; the {@code save} and {@code delete} methods inherited here have nothing to
 * write to.
 * <p>
 * The table is mapped twice upstream; this is the {@code @EmbeddedId} flavour. The {@code @IdClass} one,
 * {@code ProfitWithIdClass}, has no repository here, as the test context leaves it out of the persistence unit.
 *
 * @see ProfitWithEmbeddedId
 * @see ProfitWithEmbeddedId_
 */
@Repository
public interface ProfitWithEmbeddedIdRepository
        extends JpaRepository<ProfitWithEmbeddedId, ProfitId>,
                JpaSpecificationExecutor<ProfitWithEmbeddedId> {

}
