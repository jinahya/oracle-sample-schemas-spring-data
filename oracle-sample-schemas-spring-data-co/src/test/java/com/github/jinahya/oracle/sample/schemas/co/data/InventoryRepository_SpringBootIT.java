package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Inventory;

/**
 * Tests {@link InventoryRepository} against the installed CO schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 *
 * @see InventoryRepository_DataJpaTest
 */
class InventoryRepository_SpringBootIT
        extends _Repository_SpringBootIT<InventoryRepository, Inventory, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    InventoryRepository_SpringBootIT() {
        super(InventoryRepository.class, Inventory.class, Long.class);
    }
}
