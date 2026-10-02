package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Store;

/**
 * Tests {@link StoreRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see StoreRepository_SpringBootIT
 */
class StoreRepository_DataJpaTest
        extends _Repository_DataJpaTest<StoreRepository, Store, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    StoreRepository_DataJpaTest() {
        super(StoreRepository.class, Store.class, Long.class);
    }
}
