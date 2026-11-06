package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.Sales;
import com.github.jinahya.oracle.sample.persistence.sh.Sales_;
import com.github.jinahya.oracle.sample.persistence.sh.mapped.SalesId;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Sales}, the {@code SALES} table of the Sales History schema.
 *
 * @see Sales
 * @see Sales_
 */
@Repository
public interface SalesRepository
        extends _Repository<Sales, SalesId> {

}
