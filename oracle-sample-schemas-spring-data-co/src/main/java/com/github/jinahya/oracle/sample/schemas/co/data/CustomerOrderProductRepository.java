package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.CustomerOrderProduct;
import com.github.jinahya.oracle.sample.schemas.persistence.co.CustomerOrderProduct_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link CustomerOrderProduct}, the {@code CUSTOMER_ORDER_PRODUCTS} view of the Customer Orders
 * schema.
 * <p>
 * {@code CUSTOMER_ORDER_PRODUCTS} is a view, not a table; the {@code save} and {@code delete} methods inherited here
 * have nothing to write to.
 *
 * @see CustomerOrderProduct
 * @see CustomerOrderProduct_
 */
@Repository
public interface CustomerOrderProductRepository
        extends JpaRepository<CustomerOrderProduct, Long>,
                JpaSpecificationExecutor<CustomerOrderProduct> {

}
