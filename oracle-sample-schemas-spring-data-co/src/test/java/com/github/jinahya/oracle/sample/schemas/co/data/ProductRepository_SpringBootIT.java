package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Product;
import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductDetails;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Product_;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Tests {@link ProductRepository} against the installed CO schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. A test here that saves outside a transaction leaves what it
 * saves in {@code CO.PRODUCTS}; the tests that are here now only read. The base's own tests, such as its
 * {@code findById} round trips, run here too.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 *
 * @see ProductRepository_DataJpaTest
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class ProductRepository_SpringBootIT
        extends _Repository_SpringBootIT<ProductRepository, Product, Long> {

    private static List<Product> entities;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ProductRepository_SpringBootIT() {
        super(ProductRepository.class, Product.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Selects the first five products, by id, as the fixture for the parameterized tests.
     * <p>
     * Ordered by id so that the rows are the ones Oracle's installer put there, not ones that some run of a test has
     * saved since. Runs once per class, which {@link TestInstance.Lifecycle#PER_CLASS} allows to be an instance method,
     * so the autowired repository is already there; the result goes into a static field so that the static method
     * sources of the nested classes can reach it.
     * <p>
     * An empty table aborts the tests of this class rather than failing them: it is the fixture that is missing, not
     * the behaviour under test that is wrong.
     */
    @BeforeAll
    void __() {
        entities = Collections.unmodifiableList(
                repositoryInstance().findAll(
                        PageRequest.of(0, 5, JpaSort.of(Sort.Direction.ASC, Product_.productId))
                ).getContent()
        );
        assumeThat(entities).isNotEmpty();
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link ProductRepository#findById(Object)}.
     */
    @Nested
    // A nested class does not inherit the lifecycle of the enclosing one. The method source below is static and
    // reads the static fixture, so it does not need PER_CLASS; an instance method source would.
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class FindById_Test {

        private static Stream<Long> productIds() {
            return entities.stream().map(Product::getProductId);
        }

        /**
         * Asserts that each of the selected ids finds the product it belongs to.
         *
         * @param productId the id of one of the products selected in {@link ProductRepository_SpringBootIT#__()}.
         */
        @MethodSource("productIds")
        @ParameterizedTest
        void __(final Long productId) {
            final var found = repositoryInstance().findById(productId);
            assertThat(found).hasValueSatisfying(f -> assertThat(f.getProductId()).isEqualTo(productId));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests the {@code PRODUCT_DETAILS} column of a product, as the entity maps it.
     */
    @Nested
    class ProductDetails_Test {

        /**
         * Selects a random product, and logs its details.
         * <p>
         * Asserts nothing yet: the column holds JSON, which the entity maps as raw bytes, so this only shows what comes
         * back.
         */
        @Test
        void __() {
            final var selected = selectRandomPresent();
            final var value = jsonMapper().readValue(selected.getProductDetails(), ProductDetails.class);
            log.debug("productDetails: {}", value);
            for (final var review : value.getReviews()) {
                log.debug("\treview: {}", review);
            }
        }
    }
}