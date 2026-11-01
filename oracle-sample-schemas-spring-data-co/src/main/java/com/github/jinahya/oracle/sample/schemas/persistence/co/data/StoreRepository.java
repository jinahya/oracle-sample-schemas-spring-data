package com.github.jinahya.oracle.sample.schemas.persistence.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Store;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Store_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Store}, the {@code STORES} table of the Customer Orders schema.
 *
 * @see Store
 * @see Store_
 */
@Repository
public interface StoreRepository
        extends _Repository<Store, Long> {

}
