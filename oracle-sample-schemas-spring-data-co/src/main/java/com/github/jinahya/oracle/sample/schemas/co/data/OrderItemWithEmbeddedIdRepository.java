package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.OrderItemId;
import com.github.jinahya.oracle.sample.schemas.persistence.co.OrderItemWithEmbeddedId;
import com.github.jinahya.oracle.sample.schemas.persistence.co.OrderItemWithEmbeddedId_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link OrderItemWithEmbeddedId}, the {@code ORDER_ITEMS} table of the Customer Orders schema.
 * <p>
 * The table is mapped twice upstream; this is the {@code @EmbeddedId} flavour. The {@code @IdClass} one,
 * {@code OrderItemWithIdClass}, has no repository here, as the test context leaves it out of the persistence unit.
 *
 * @see OrderItemWithEmbeddedId
 * @see OrderItemWithEmbeddedId_
 */
@Repository
public interface OrderItemWithEmbeddedIdRepository
        extends JpaRepository<OrderItemWithEmbeddedId, OrderItemId>,
                JpaSpecificationExecutor<OrderItemWithEmbeddedId> {

}
