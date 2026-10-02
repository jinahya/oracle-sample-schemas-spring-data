package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrderId;
import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrderWithEmbeddedId;
import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrderWithEmbeddedId_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link ProductOrderWithEmbeddedId}, the {@code PRODUCT_ORDERS} view of the Customer Orders schema.
 * <p>
 * {@code PRODUCT_ORDERS} is a view, not a table; the {@code save} and {@code delete} methods inherited here have
 * nothing to write to.
 * <p>
 * The table is mapped twice upstream; this is the {@code @EmbeddedId} flavour. The {@code @IdClass} one,
 * {@code ProductOrderWithIdClass}, has no repository here, as the test context leaves it out of the persistence unit.
 *
 * @see ProductOrderWithEmbeddedId
 * @see ProductOrderWithEmbeddedId_
 */
@Repository
public interface ProductOrderWithEmbeddedIdRepository
        extends JpaRepository<ProductOrderWithEmbeddedId, ProductOrderId>,
                JpaSpecificationExecutor<ProductOrderWithEmbeddedId> {

}
