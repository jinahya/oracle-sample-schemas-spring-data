package com.github.jinahya.oracle.sample.schemas.persistence.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.OrderItem;
import com.github.jinahya.oracle.sample.schemas.persistence.co.OrderItemId;
import com.github.jinahya.oracle.sample.schemas.persistence.co.OrderItem_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link OrderItem}, the {@code ORDER_ITEMS} table of the Customer Orders schema.
 *
 * @see OrderItem
 * @see OrderItem_
 */
@Repository
public interface OrderItemRepository
        extends _Repository<OrderItem, OrderItemId> {

}
