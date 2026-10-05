package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Inventory;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Inventory_;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Product;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Store;
import com.github.jinahya.persistence.test.util.JinahyaPersistenceTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Tests {@link InventoryRepository} against the installed CO schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. The tests here only read. The base's own tests, such as its
 * {@code findById} round trips, run here too.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 *
 * @see InventoryRepository_DataJpaTest
 */
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InventoryRepository_SpringBootIT
        extends _Repository_SpringBootIT<InventoryRepository, Inventory, Long> {

    private static List<Inventory> entities;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    InventoryRepository_SpringBootIT() {
        super(InventoryRepository.class, Inventory.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Selects the first five inventories, by id, as the fixture for the parameterized tests.
     * <p>
     * Their stores and products are lazy, so each comes back as an uninitialized proxy of a persistence context that
     * has already closed; only its id is usable, which is all a query by it needs.
     * <p>
     * An empty table aborts the tests of this class rather than failing them.
     */
    @BeforeAll
    void __() {
        entities = Collections.unmodifiableList(
                repositoryInstance().findAll(
                        PageRequest.of(0, 5, JpaSort.of(Sort.Direction.ASC, Inventory_.inventoryId))
                ).getContent()
        );
        assumeThat(entities).isNotEmpty();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link InventoryRepository#findAllByStore(Store, Pageable)}.
     */
    @Nested
    class FindAllByStore_Test {

        private static Stream<Inventory> inventories() {
            return entities.stream();
        }

        /**
         * Asserts that finding by the store of a selected inventory, a detached uninitialized proxy, finds that
         * inventory.
         *
         * @param inventory one of the inventories selected in {@link InventoryRepository_SpringBootIT#__()}.
         */
        @MethodSource("inventories")
        @ParameterizedTest
        void __(final Inventory inventory) {
            final var found = repositoryInstance().findAllByStore(inventory.getStore(), Pageable.unpaged());
            assertThat(found.getContent())
                    .extracting(Inventory::getInventoryId)
                    .contains(inventory.getInventoryId());
        }

        /**
         * Selects a random inventory, and asserts, in one transaction, that every inventory found by its store has that
         * very store.
         */
        @Transactional
        @Test
        void __Transactional() {
            final var selected = selectRandomPresent();
            final var store = selected.getStore();
            final var found = repositoryInstance().findAllByStore(store, Pageable.unpaged());
            assertThat(found.getContent())
                    .contains(selected)
                    .allSatisfy(i -> assertThat(i.getStore()).isSameAs(store));
        }

        @Test
        void __OrderByProductInventoryAsc() {
            final Store store;
            {
                final var random = JinahyaPersistenceTestUtils.selectRandom(entityManager(), Store.class);
                assumeThat(random).isNotEmpty();
                store = random.get();
            }
            final var inventories = repositoryInstance().findAllByStore(
                    store,
                    PageRequest.of(0, 128, JpaSort.of(Inventory_.productInventory).ascending())
            ).getContent();
            for (final var inventory : inventories) {
                log.debug("inventory: {}", inventory);
            }
        }
    }

    /**
     * Tests {@link InventoryRepository#findAllByProduct(Product, Pageable)}.
     */
    @Nested
    class FindAllByProduct_Test {

        private static Stream<Inventory> inventories() {
            return entities.stream();
        }

        /**
         * Asserts that finding by the product of a selected inventory, a detached uninitialized proxy, finds that
         * inventory.
         *
         * @param inventory one of the inventories selected in {@link InventoryRepository_SpringBootIT#__()}.
         */
        @MethodSource("inventories")
        @ParameterizedTest
        void __(final Inventory inventory) {
            final var found = repositoryInstance().findAllByProduct(inventory.getProduct(), Pageable.unpaged());
            assertThat(found.getContent())
                    .extracting(Inventory::getInventoryId)
                    .contains(inventory.getInventoryId());
        }

        /**
         * Selects a random inventory, and asserts, in one transaction, that every inventory found by its product has
         * that very product.
         */
        @Transactional
        @Test
        void __Transactional() {
            final var selected = selectRandomPresent();
            final var product = selected.getProduct();
            final var found = repositoryInstance().findAllByProduct(product, Pageable.unpaged());
            assertThat(found.getContent())
                    .contains(selected)
                    .allSatisfy(i -> assertThat(i.getProduct()).isSameAs(product));
        }
    }

    /**
     * Tests {@link InventoryRepository#findByStoreAndProduct(Store, Product)}.
     */
    @Nested
    class FindByStoreAndProduct_Test {

        private static Stream<Inventory> inventories() {
            return entities.stream();
        }

        /**
         * Asserts that finding by the store and the product of a selected inventory, both detached uninitialized
         * proxies, finds that inventory.
         *
         * @param inventory one of the inventories selected in {@link InventoryRepository_SpringBootIT#__()}.
         */
        @MethodSource("inventories")
        @ParameterizedTest
        void __(final Inventory inventory) {
            final var found = repositoryInstance().findByStoreAndProduct(inventory.getStore(), inventory.getProduct());
            assertThat(found).hasValueSatisfying(
                    v -> assertThat(v.getInventoryId()).isEqualTo(inventory.getInventoryId())
            );
        }

        /**
         * Selects a random inventory, and asserts, in one transaction, that finding by its store and product returns
         * that very instance.
         */
        @Transactional
        @Test
        void __Transactional() {
            final var selected = selectRandomPresent();
            final var found = repositoryInstance().findByStoreAndProduct(selected.getStore(), selected.getProduct());
            assertThat(found).hasValueSatisfying(v -> assertThat(v).isSameAs(selected));
        }
    }
}
