package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Customer;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.Customer_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Customer}, the {@code CUSTOMERS} table of the Sales History schema.
 *
 * @see Customer
 * @see Customer_
 */
@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long>,
                JpaSpecificationExecutor<Customer> {

}
