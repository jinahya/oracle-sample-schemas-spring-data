package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Inventory;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Product;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Store;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests {@link InventoryRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. Each test persists the stores, products and inventories it needs, and
 * the slice rolls them back when it ends. The stores and products are new to each test, so the inventories found by one
 * of them are exactly those the test persisted.
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

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Persists a randomized inventory of the specified product at the specified store, through the injected entity
     * manager.
     *
     * @param store   the store of the inventory, already persisted.
     * @param product the product of the inventory, already persisted.
     * @return the persisted inventory.
     */
    private Inventory persistInventory(final Store store, final Product product) {
        final var inventory = ObjectRandomizerUtils.newRandomizedInstanceOf(Inventory.class).orElseThrow();
        inventory.setStore(store);
        inventory.setProduct(product);
        entityManager().persist(inventory);
        return inventory;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link InventoryRepository#findAllByStore(Store, Pageable)}.
     */
    @Nested
    class FindAllByStore_Test {

        /**
         * Persists a store with no inventory, and asserts that finding by it returns an empty page.
         */
        @Test
        void _Empty_Unknown() {
            final var store = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Store.class);
            final var found = repositoryInstance().findAllByStore(store, Pageable.unpaged());
            assertThat(found).isEmpty();
        }

        /**
         * Persists two inventories at one store and one at another, and asserts that finding by the first store returns
         * its two, and not the other's.
         */
        @Test
        void _NotEmpty_Known() {
            final var store = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Store.class);
            final var product = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Product.class);
            final var inventory1 = persistInventory(store, product);
            final var inventory2 = persistInventory(
                    store, EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Product.class));
            final var other = persistInventory(
                    EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Store.class), product);
            final var found = repositoryInstance().findAllByStore(store, Pageable.unpaged());
            assertThat(found.getContent())
                    .containsExactlyInAnyOrder(inventory1, inventory2)
                    .doesNotContain(other);
        }

        /**
         * Persists an inventory, clears the persistence context, and asserts that finding by a reference to its store
         * finds it without loading the store.
         */
        @Test
        void __Reference() {
            final var inventory = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Inventory.class);
            final var storeId = inventory.getStore().getStoreId();
            entityManager().flush();
            entityManager().clear();
            final var reference = entityManager().getReference(Store.class, storeId);
            final var found = repositoryInstance().findAllByStore(reference, Pageable.unpaged());
            assertThat(found.getContent())
                    .extracting(Inventory::getInventoryId)
                    .containsExactly(inventory.getInventoryId());
            assertThat(Hibernate.isInitialized(reference)).isFalse();
        }
    }

    /**
     * Tests {@link InventoryRepository#findAllByProduct(Product, Pageable)}.
     */
    @Nested
    class FindAllByProduct_Test {

        /**
         * Persists a product with no inventory, and asserts that finding by it returns an empty page.
         */
        @Test
        void _Empty_Unknown() {
            final var product = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Product.class);
            final var found = repositoryInstance().findAllByProduct(product, Pageable.unpaged());
            assertThat(found).isEmpty();
        }

        /**
         * Persists inventories of one product at two stores and one of another product, and asserts that finding by the
         * first product returns its two, and not the other's.
         */
        @Test
        void _NotEmpty_Known() {
            final var store = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Store.class);
            final var product = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Product.class);
            final var inventory1 = persistInventory(store, product);
            final var inventory2 = persistInventory(
                    EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Store.class), product);
            final var other = persistInventory(
                    store, EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Product.class));
            final var found = repositoryInstance().findAllByProduct(product, Pageable.unpaged());
            assertThat(found.getContent())
                    .containsExactlyInAnyOrder(inventory1, inventory2)
                    .doesNotContain(other);
        }

        /**
         * Persists an inventory, clears the persistence context, and asserts that finding by a reference to its product
         * finds it without loading the product.
         */
        @Test
        void __Reference() {
            final var inventory = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Inventory.class);
            final var productId = inventory.getProduct().getProductId();
            entityManager().flush();
            entityManager().clear();
            final var productReference = entityManager().getReference(Product.class, productId);
            final var found = repositoryInstance().findAllByProduct(productReference, Pageable.unpaged());
            assertThat(found.getContent())
                    .extracting(Inventory::getInventoryId)
                    .containsExactly(inventory.getInventoryId());
            assertThat(Hibernate.isInitialized(productReference)).isFalse();
        }
    }

    /**
     * Tests {@link InventoryRepository#findByStoreAndProduct(Store, Product)}.
     */
    @Nested
    class FindByStoreAndProduct_Test {

        /**
         * Persists a store and a product that each have an inventory, but not with each other, and asserts that finding
         * by the pair returns an empty optional.
         */
        @Test
        void _Empty_Unknown() {
            final var store = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Store.class);
            final var product = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Product.class);
            persistInventory(store, EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Product.class));
            persistInventory(EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Store.class), product);
            final var found = repositoryInstance().findByStoreAndProduct(store, product);
            assertThat(found).isEmpty();
        }

        /**
         * Persists an inventory, and asserts that finding by its store and product returns that very instance.
         * <p>
         * The query goes to the database, and the row it reads resolves to the instance already managed in the test's
         * persistence context; hence {@code isSameAs}.
         */
        @Test
        void _NotEmpty_Known() {
            final var store = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Store.class);
            final var product = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Product.class);
            final var inventory = persistInventory(store, product);
            final var found = repositoryInstance().findByStoreAndProduct(store, product);
            assertThat(found).hasValueSatisfying(v -> assertThat(v).isSameAs(inventory));
        }

        /**
         * Persists an inventory, clears the persistence context, and asserts that finding by references to its store
         * and product finds it without loading either.
         */
        @Test
        void __Reference() {
            final var inventory = EntityPersisterUtils.newPersistedInstanceOf(entityManager(), Inventory.class);
            final var storeId = inventory.getStore().getStoreId();
            final var productId = inventory.getProduct().getProductId();
            entityManager().flush();
            entityManager().clear();
            final var storeReference = entityManager().getReference(Store.class, storeId);
            final var productReference = entityManager().getReference(Product.class, productId);
            final var found = repositoryInstance().findByStoreAndProduct(storeReference, productReference);
            assertThat(found).hasValueSatisfying(
                    v -> assertThat(v.getInventoryId()).isEqualTo(inventory.getInventoryId())
            );
            assertThat(Hibernate.isInitialized(storeReference)).isFalse();
            assertThat(Hibernate.isInitialized(productReference)).isFalse();
        }
    }
}
