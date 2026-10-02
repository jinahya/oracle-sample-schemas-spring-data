package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.SaleId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.SaleWithEmbeddedId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.SaleWithEmbeddedId_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link SaleWithEmbeddedId}, the {@code SALES} table of the Sales History schema.
 * <p>
 * The table is mapped twice upstream; this is the {@code @EmbeddedId} flavour. The {@code @IdClass} one,
 * {@code SaleWithIdClass}, has no repository here, as the test context leaves it out of the persistence unit.
 *
 * @see SaleWithEmbeddedId
 * @see SaleWithEmbeddedId_
 */
@Repository
public interface SaleWithEmbeddedIdRepository
        extends JpaRepository<SaleWithEmbeddedId, SaleId>,
                JpaSpecificationExecutor<SaleWithEmbeddedId> {

}
