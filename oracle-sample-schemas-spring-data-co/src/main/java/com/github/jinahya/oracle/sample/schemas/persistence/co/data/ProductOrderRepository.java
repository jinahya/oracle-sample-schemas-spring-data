package com.github.jinahya.oracle.sample.schemas.persistence.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrder;
import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrderId;
import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductOrder_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link ProductOrder}, the {@code PRODUCT_ORDERS} view of the Customer Orders schema.
 *
 * @see ProductOrder
 * @see ProductOrder_
 */
@Repository
public interface ProductOrderRepository
        extends _Repository<ProductOrder, ProductOrderId> {

}
