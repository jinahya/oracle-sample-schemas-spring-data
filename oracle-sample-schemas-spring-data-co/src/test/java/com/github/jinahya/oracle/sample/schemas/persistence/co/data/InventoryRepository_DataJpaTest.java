package com.github.jinahya.oracle.sample.schemas.persistence.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Inventory;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Product;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Store;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests {@link InventoryRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. Each test saves the stores, products and inventories it needs,
 * through the fixtures, and the slice rolls them back when it ends. The stores and products are new to each test, so
 * the inventories found by one of them are exactly those the test saved.
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
     * Tests {@link InventoryRepository#findAllByStore(Store, Pageable)}.
     */
    @Nested
    class FindAllByStore_Test {

        /**
         * Saves a store with no inventory, and asserts that finding by it returns an empty page.
         */
        @Test
        void _Empty_Unknown() {
            final var store = fixtures().store();
            final var found = repositoryInstance().findAllByStore(store, Pageable.unpaged());
            assertThat(found).isEmpty();
        }

        /**
         * Saves two inventories at one store and one at another, and asserts that finding by the first store returns
         * its two, and not the other's.
         */
        @Test
        void _NotEmpty_Known() {
            final var store = fixtures().store();
            final var product = fixtures().product();
            final var inventory1 = fixtures().inventory(store, product);
            final var inventory2 = fixtures().inventory(store, fixtures().product());
            final var other = fixtures().inventory(fixtures().store(), product);
            final var found = repositoryInstance().findAllByStore(store, Pageable.unpaged());
            assertThat(found.getContent())
                    .containsExactlyInAnyOrder(inventory1, inventory2)
                    .doesNotContain(other);
        }
    }

    /**
     * Tests {@link InventoryRepository#findAllByProduct(Product, Pageable)}.
     */
    @Nested
    class FindAllByProduct_Test {

        /**
         * Saves a product with no inventory, and asserts that finding by it returns an empty page.
         */
        @Test
        void _Empty_Unknown() {
            final var product = fixtures().product();
            final var found = repositoryInstance().findAllByProduct(product, Pageable.unpaged());
            assertThat(found).isEmpty();
        }

        /**
         * Saves inventories of one product at two stores and one of another product, and asserts that finding by the
         * first product returns its two, and not the other's.
         */
        @Test
        void _NotEmpty_Known() {
            final var store = fixtures().store();
            final var product = fixtures().product();
            final var inventory1 = fixtures().inventory(store, product);
            final var inventory2 = fixtures().inventory(fixtures().store(), product);
            final var other = fixtures().inventory(store, fixtures().product());
            final var found = repositoryInstance().findAllByProduct(product, Pageable.unpaged());
            assertThat(found.getContent())
                    .containsExactlyInAnyOrder(inventory1, inventory2)
                    .doesNotContain(other);
        }
    }

    /**
     * Tests {@link InventoryRepository#findByStoreAndProduct(Store, Product)}.
     */
    @Nested
    class FindByStoreAndProduct_Test {

        /**
         * Saves a store and a product that each have an inventory, but not with each other, and asserts that finding
         * by the pair returns an empty optional.
         */
        @Test
        void _Empty_Unknown() {
            final var store = fixtures().store();
            final var product = fixtures().product();
            fixtures().inventory(store, fixtures().product());
            fixtures().inventory(fixtures().store(), product);
            final var found = repositoryInstance().findByStoreAndProduct(store, product);
            assertThat(found).isEmpty();
        }

        /**
         * Saves an inventory, and asserts that finding by its store and product returns that very instance.
         * <p>
         * The query goes to the database, and the row it reads resolves to the instance already managed in the test's
         * persistence context; hence {@code isSameAs}.
         */
        @Test
        void _NotEmpty_Known() {
            final var store = fixtures().store();
            final var product = fixtures().product();
            final var inventory = fixtures().inventory(store, product);
            final var found = repositoryInstance().findByStoreAndProduct(store, product);
            assertThat(found).hasValueSatisfying(v -> assertThat(v).isSameAs(inventory));
        }
    }
}
