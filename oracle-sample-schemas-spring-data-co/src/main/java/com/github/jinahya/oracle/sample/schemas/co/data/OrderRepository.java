package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Order;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Order_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Order}, the {@code ORDERS} table of the Customer Orders schema.
 *
 * @see Order
 * @see Order_
 */
@Repository
public interface OrderRepository
        extends JpaRepository<Order, Long>,
                JpaSpecificationExecutor<Order> {

}
