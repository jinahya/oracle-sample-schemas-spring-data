package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Store;

/**
 * Tests {@link StoreRepository} against the installed CO schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 *
 * @see StoreRepository_DataJpaTest
 */
class StoreRepository_SpringBootIT
        extends _Repository_SpringBootIT<StoreRepository, Store, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    StoreRepository_SpringBootIT() {
        super(StoreRepository.class, Store.class, Long.class);
    }
}
