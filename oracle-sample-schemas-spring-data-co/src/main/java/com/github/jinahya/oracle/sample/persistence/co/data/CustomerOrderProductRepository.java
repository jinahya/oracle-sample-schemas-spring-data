package com.github.jinahya.oracle.sample.persistence.co.data;

import com.github.jinahya.oracle.sample.persistence.co.CustomerOrderProduct;
import com.github.jinahya.oracle.sample.persistence.co.CustomerOrderProduct_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link CustomerOrderProduct}, the {@code CUSTOMER_ORDER_PRODUCTS} view of the Customer Orders schema.
 *
 * @see CustomerOrderProduct
 * @see CustomerOrderProduct_
 */
@Repository
public interface CustomerOrderProductRepository
        extends _Repository<CustomerOrderProduct, Long> {

}
