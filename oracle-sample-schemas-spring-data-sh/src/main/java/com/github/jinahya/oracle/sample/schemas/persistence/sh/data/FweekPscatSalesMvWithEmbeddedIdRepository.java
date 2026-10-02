package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMvId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMvWithEmbeddedId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.FweekPscatSalesMvWithEmbeddedId_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link FweekPscatSalesMvWithEmbeddedId}, the {@code FWEEK_PSCAT_SALES_MV} materialized view of the
 * Sales History schema.
 * <p>
 * {@code FWEEK_PSCAT_SALES_MV} is a materialized view, not a table; the {@code save} and {@code delete} methods
 * inherited here have nothing to write to.
 * <p>
 * The table is mapped twice upstream; this is the {@code @EmbeddedId} flavour. The {@code @IdClass} one,
 * {@code FweekPscatSalesMvWithIdClass}, has no repository here, as the test context leaves it out of the persistence
 * unit.
 *
 * @see FweekPscatSalesMvWithEmbeddedId
 * @see FweekPscatSalesMvWithEmbeddedId_
 */
@Repository
public interface FweekPscatSalesMvWithEmbeddedIdRepository
        extends JpaRepository<FweekPscatSalesMvWithEmbeddedId, FweekPscatSalesMvId>,
                JpaSpecificationExecutor<FweekPscatSalesMvWithEmbeddedId> {

}
