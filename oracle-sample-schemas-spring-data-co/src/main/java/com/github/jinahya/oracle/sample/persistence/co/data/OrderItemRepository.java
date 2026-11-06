package com.github.jinahya.oracle.sample.persistence.co.data;

import com.github.jinahya.oracle.sample.persistence.co.OrderItem;
import com.github.jinahya.oracle.sample.persistence.co.OrderItem_;
import com.github.jinahya.oracle.sample.persistence.co.mapped.OrderItemId;
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
