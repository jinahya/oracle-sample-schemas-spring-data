package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Sales;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.SalesId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.Sales_;
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
