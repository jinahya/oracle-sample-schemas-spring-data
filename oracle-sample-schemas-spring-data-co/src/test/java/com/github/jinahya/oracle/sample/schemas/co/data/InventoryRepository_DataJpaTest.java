package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Inventory;

/**
 * Tests {@link InventoryRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see InventoryRepository_SpringBootIT
 */
class InventoryRepository_DataJpaTest
        extends _Repository_DataJpaTest<InventoryRepository, Inventory, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    InventoryRepository_DataJpaTest() {
        super(InventoryRepository.class, Inventory.class, Long.class);
    }
}
