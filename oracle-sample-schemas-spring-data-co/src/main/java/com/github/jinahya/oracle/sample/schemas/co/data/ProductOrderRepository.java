package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrder;
import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrderId;
import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrder_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link ProductOrder}, the {@code PRODUCT_ORDERS} view of the Customer Orders schema.
 * <p>
 * {@code PRODUCT_ORDERS} is a view, not a table; the {@code save} and {@code delete} methods inherited here have
 * nothing to write to.
 *
 * @see ProductOrder
 * @see ProductOrder_
 */
@Repository
public interface ProductOrderRepository
        extends JpaRepository<ProductOrder, ProductOrderId>,
                JpaSpecificationExecutor<ProductOrder> {

}
