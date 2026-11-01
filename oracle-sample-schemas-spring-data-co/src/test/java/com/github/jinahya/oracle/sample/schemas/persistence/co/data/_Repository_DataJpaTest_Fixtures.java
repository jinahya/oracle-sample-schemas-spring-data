package com.github.jinahya.oracle.sample.schemas.persistence.co.data;

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Customer;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Inventory;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Product;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

/**
 * Persists the fixtures a {@code @DataJpaTest} needs, through the repositories, never through the entity manager.
 * <p>
 * Each method randomizes an entity with the upstream randomizer, gives it whatever it refers to (persisted the same
 * way, or passed in), and saves it through that entity's repository. The ids of {@code CO}'s tables are
 * {@code IDENTITY} columns, which the randomizers leave {@code null}, so a saved fixture gets an id the H2 hands out.
 * <p>
 * {@link _Repository_DataJpaTest} imports it; a slice test reaches it as {@code fixtures()}.
 */
@TestComponent
class _Repository_DataJpaTest_Fixtures {

    /**
     * Returns a randomized instance of the specified class, through its upstream {@code <Entity>_Randomizer}.
     *
     * @param clazz the class to randomize.
     * @param <T>   the type of the class.
     * @return a randomized instance.
     */
    static <T> T randomized(final Class<T> clazz) {
        return ObjectRandomizerUtils.newRandomizedInstanceOf(clazz).orElseThrow();
    }

    // -----------------------------------------------------------------------------------------------------------------

    Customer customer() {
        return customerRepository.save(randomized(Customer.class));
    }

    Product product() {
        return productRepository.save(randomized(Product.class));
    }

    Store store() {
        return storeRepository.save(randomized(Store.class));
    }

    /**
     * Saves a randomized inventory of the specified product at the specified store.
     *
     * @param store   the store.
     * @param product the product.
     * @return the saved inventory.
     */
    Inventory inventory(final Store store, final Product product) {
        final var inventory = randomized(Inventory.class);
        inventory.setStore(store);
        inventory.setProduct(product);
        return inventoryRepository.save(inventory);
    }

    Inventory inventory() {
        return inventory(store(), product());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;
}
