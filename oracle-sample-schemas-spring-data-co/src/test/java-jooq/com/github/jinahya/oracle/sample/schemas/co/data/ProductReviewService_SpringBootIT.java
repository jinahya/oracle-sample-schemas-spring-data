package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.co.data.jooq.tables.records.ProductReviewsRecord;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

import java.util.Comparator;
import java.util.List;

import static com.github.jinahya.oracle.sample.schemas.co.data.jooq.Tables.PRODUCT_REVIEWS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Tests {@link ProductReviewService} against the installed CO schema.
 * <p>
 * Each test takes its fixture from {@link ProductReviewService#select()}, and checks another method of the service
 * against it. An empty view aborts a test rather than failing it.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 */
@Import({_Jooq_TestConfiguration.class, ProductReviewService.class})
@SpringBootTest
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class ProductReviewService_SpringBootIT {

    /**
     * Tests {@link ProductReviewService#select()}.
     */
    @DisplayName("select()")
    @Nested
    class Select_Test {

        /**
         * Asserts that every row of the view names its product.
         */
        @Test
        void __() {
            final var records = selectAllPresent();
            assertThat(records).allSatisfy(r -> assertThat(r.getProductName()).isNotBlank());
        }
    }

    /**
     * Tests {@link ProductReviewService#select(String, java.util.function.Function)}.
     */
    @DisplayName("select(productName, function)")
    @Nested
    class SelectByProductName_Test {

        /**
         * Asserts that a product's name selects exactly the rows of that product.
         */
        @Test
        void __() {
            final var all = selectAllPresent();
            final var productName = all.getFirst().getProductName();
            final var records = service.select(productName, s -> s.fetch());
            assertThat(records).containsExactlyInAnyOrderElementsOf(rowsOf(all, List.of(productName)));
        }

        /**
         * Asserts that the function is applied to the step, here as an {@code ORDER BY}.
         */
        @Test
        void __ordered() {
            final var productName = selectAllPresent().getFirst().getProductName();
            final var records = service.select(
                    productName, s -> s.orderBy(PRODUCT_REVIEWS.RATING.desc().nullsLast()).fetch());
            assertThat(records)
                    .isNotEmpty()
                    .isSortedAccordingTo(Comparator.comparing(
                            ProductReviewsRecord::getRating, Comparator.nullsLast(Comparator.reverseOrder())));
        }

        /**
         * Asserts that a name of no product selects nothing.
         */
        @Test
        void __unknown() {
            final var records = service.select("\u0000", s -> s.fetch());
            assertThat(records).isEmpty();
        }
    }

    /**
     * Tests {@link ProductReviewService#select(java.util.Collection, java.util.function.Function)}.
     */
    @DisplayName("select(productNames, function)")
    @Nested
    class SelectByProductNames_Test {

        /**
         * Asserts that two names select exactly the rows of both products.
         */
        @Test
        void __() {
            final var all = selectAllPresent();
            final var productNames = all.stream().map(ProductReviewsRecord::getProductName).distinct().limit(2)
                    .toList();
            assumeThat(productNames).hasSize(2);
            final var records = service.select(productNames, s -> s);
            assertThat(records).containsExactlyInAnyOrderElementsOf(rowsOf(all, productNames));
        }

        /**
         * Asserts that no names select nothing, rather than an {@code IN ()} that Oracle rejects.
         */
        @Test
        void __empty() {
            final var records = service.select(List.of(), s -> s);
            assertThat(records).isEmpty();
        }
    }

    /**
     * Tests {@link ProductReviewService#findAllByProductId(Long, java.util.function.Function)}.
     */
    @DisplayName("findAllByProductId(productId, function)")
    @Nested
    class FindAllByProductId_Test {

        /**
         * Asserts that a product's id selects the same rows as its name.
         */
        @Test
        void __() {
            final var product = productRepository.findAll(PageRequest.of(0, 1)).stream().findFirst();
            assumeThat(product).isPresent();
            final var records = service.findAllByProductId(product.get().getProductId(), s -> s);
            assertThat(records).containsExactlyInAnyOrderElementsOf(
                    service.select(product.get().getProductName(), s -> s.fetch()));
        }

        /**
         * Asserts that an id of no product selects nothing.
         */
        @Test
        void __unknown() {
            final var records = service.findAllByProductId(-1L, s -> s);
            assertThat(records).isEmpty();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Selects every row through the service, and aborts the calling test if the view holds none.
     *
     * @return every row of the view; never empty.
     */
    private List<ProductReviewsRecord> selectAllPresent() {
        final var records = service.select();
        assumeThat(records).isNotEmpty();
        return records;
    }

    private static List<ProductReviewsRecord> rowsOf(final List<ProductReviewsRecord> records,
                                                     final List<String> productNames) {
        return records.stream().filter(r -> productNames.contains(r.getProductName()))
                .toList();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private ProductReviewService service;

    @Autowired
    private ProductRepository productRepository;
}
