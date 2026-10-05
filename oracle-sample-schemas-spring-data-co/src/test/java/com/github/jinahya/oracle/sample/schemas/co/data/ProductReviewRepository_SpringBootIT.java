package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductReview;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Tests {@link ProductReviewRepository} against the installed CO schema, checking each method against the view itself.
 * <p>
 * The repository is not a Spring Data one, so this does not extend {@link _Repository_SpringBootIT}; the context
 * {@code @Import}s its implementation. Needs the database of {@code application.yaml} up and the CO schema installed;
 * see that file.
 */
@SpringBootTest
@Slf4j
class ProductReviewRepository_SpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Selects the name of a random product with reviews: a random row of the view, picked by
     * {@link __NativeSql_TestUtils} over its entity manager.
     * <p>
     * Aborts the calling test if the view holds no row, or the row picked has no review.
     *
     * @return the name of a product with reviews.
     */
    private String selectRandomReviewedProductName() {
        final var index = __NativeSql_TestUtils.randomIndex(entityManager, "CO.PRODUCT_REVIEWS");
        assumeThat(index).isPresent();
        // A product with no reviews has one row, with no REVIEW; such a pick aborts the test.
        final var row = (Object[]) entityManager
                .createNativeQuery("""
                        SELECT PRODUCT_NAME, REVIEW
                        FROM CO.PRODUCT_REVIEWS
                        ORDER BY PRODUCT_NAME, RATING, REVIEW
                        OFFSET ?1 ROWS FETCH NEXT 1 ROWS ONLY""")
                .setParameter(1, index.getAsLong())
                .getSingleResult();
        assumeThat(row[1]).as("review").isNotNull();
        return (String) row[0];
    }

    /**
     * Tests {@link ProductReviewRepository#countAllByProductName(String)}.
     */
    @Nested
    class CountAllByProductName_Test {

        /**
         * Asserts that the count of a reviewed product's name is positive, and is what the view holds for it.
         */
        @Test
        void __() {
            final var productName = selectRandomReviewedProductName();
            final var expected = (Long) entityManager
                    .createNativeQuery("""
                            SELECT COUNT(*)
                            FROM CO.PRODUCT_REVIEWS
                            WHERE PRODUCT_NAME = ?1""", Long.class)
                    .setParameter(1, productName)
                    .getSingleResult();
            final var count = repository.countAllByProductName(productName);
            assertThat(count).isPositive().isEqualTo(expected);
        }

        /**
         * Asserts that a name no product has counts zero.
         */
        @Test
        void _Zero_NoSuchProduct() {
            assertThat(repository.countAllByProductName("no such product, surely")).isZero();
        }
    }

    /**
     * Tests {@link ProductReviewRepository#findAllByProductName(String, long, int)}.
     */
    @Nested
    class FindAllByProductName_Test {

        /**
         * Asserts that one page as large as the count holds all the reviews of a reviewed product's name, all of that
         * name, mapped column by column.
         */
        @Test
        void __All() {
            final var productName = selectRandomReviewedProductName();
            final var count = repository.countAllByProductName(productName);
            final var found = repository.findAllByProductName(productName, 0L, (int) count);
            found.forEach(r -> log.debug("review: {}", r));
            assertThat(found)
                    .hasSize((int) count)
                    .allSatisfy(r -> assertThat(r.getProductName()).isEqualTo(productName))
                    .anySatisfy(r -> assertThat(r.getReview()).isNotNull());
        }

        /**
         * Asserts that pages of two, read one after another, add up to the single page of all, in the same order.
         */
        @Test
        void __Paged() {
            final var productName = selectRandomReviewedProductName();
            final var count = repository.countAllByProductName(productName);
            final var all = repository.findAllByProductName(productName, 0L, (int) count);
            final var limit = 2;
            final var paged = new ArrayList<ProductReview>();
            for (long offset = 0L; ; offset += limit) {
                final var page = repository.findAllByProductName(productName, offset, limit);
                assertThat(page).hasSizeLessThanOrEqualTo(limit);
                if (page.isEmpty()) {
                    break;
                }
                paged.addAll(page);
            }
            assertThat(paged)
                    .extracting(ProductReview::getRating, ProductReview::getReview)
                    .containsExactlyElementsOf(
                            all.stream().map(r -> tuple(r.getRating(), r.getReview()))
                                    .toList());
        }

        /**
         * Asserts that a name no product has finds nothing.
         */
        @Test
        void _Empty_NoSuchProduct() {
            final var found = repository.findAllByProductName(
                    "no such product, surely", 0L, 10);
            assertThat(found).isEmpty();
        }

        /**
         * Asserts that a blank product name, a negative offset and a non-positive limit are rejected, by the
         * constraints of {@link ProductReviewRepository}, before any query runs.
         */
        @Test
        void _ConstraintViolationException_Arguments() {
            assertThatThrownBy(() -> repository.findAllByProductName(" ", 0L, 10))
                    .isInstanceOf(ConstraintViolationException.class);
            assertThatThrownBy(() -> repository.findAllByProductName("x", -1L, 10))
                    .isInstanceOf(ConstraintViolationException.class);
            assertThatThrownBy(() -> repository.findAllByProductName("x", 0L, 0))
                    .isInstanceOf(ConstraintViolationException.class);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Reads every distinct {@code (PRODUCT_NAME, AVG_RATING)} of the view, straight from it, ordered as the method
     * under test orders them, as the expected result of the distinct-names methods.
     *
     * @param direction {@code ASC} or {@code DESC}.
     * @return the product names, in order.
     */
    @SuppressWarnings({"unchecked"})
    private List<String> selectDistinctProductNamesOrderByAvgRating(final String direction) {
        return ((List<Object[]>) entityManager
                .createNativeQuery("""
                        SELECT DISTINCT PRODUCT_NAME, AVG_RATING
                        FROM CO.PRODUCT_REVIEWS
                        ORDER BY AVG_RATING %s NULLS LAST, PRODUCT_NAME""".formatted(direction))
                .getResultList())
                .stream().map(r -> (String) r[0]).toList();
    }

    /**
     * Reads the specified method a page of {@code limit} at a time, until a page comes back empty.
     *
     * @param method the method under test.
     * @param limit  the size of a page.
     * @return the names of all the pages, in order.
     */
    private static List<String> readAllPages(final BiFunction<Long, Integer, List<String>> method,
                                             final int limit) {
        final var all = new ArrayList<String>();
        for (long offset = 0L; ; offset += limit) {
            final var page = method.apply(offset, limit);
            assertThat(page).hasSizeLessThanOrEqualTo(limit);
            if (page.isEmpty()) {
                return all;
            }
            all.addAll(page);
        }
    }

    /**
     * Tests {@link ProductReviewRepository#countDistinctProductNames()}.
     */
    @Nested
    class CountDistinctProductNames_Test {

        /**
         * Asserts that the count is what the view holds.
         */
        @Test
        void __() {
            final var expected = (Long) entityManager
                    .createNativeQuery("""
                            SELECT COUNT(DISTINCT PRODUCT_NAME)
                            FROM CO.PRODUCT_REVIEWS""", Long.class)
                    .getSingleResult();
            assertThat(repository.countDistinctProductNames()).isEqualTo(expected);
        }
    }

    /**
     * Tests {@link ProductReviewRepository#findDistinctProductNamesOrderByAvgRating(long, int)}.
     */
    @Nested
    class FindDistinctProductNamesOrderByAvgRatingDesc_Test {

        /**
         * Asserts that pages of seven, descending, add up to every distinct name, once each, in the view's order.
         */
        @Test
        void __Desc() {
            final var expected = selectDistinctProductNamesOrderByAvgRating("DESC");
            assumeThat(expected).isNotEmpty();
            final var names = readAllPages(
                    repository::findDistinctProductNamesOrderByAvgRating, 7);
            log.debug("desc: {}", names);
            assertThat(names)
                    .doesNotHaveDuplicates()
                    .hasSize((int) repository.countDistinctProductNames())
                    .containsExactlyElementsOf(expected);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private ProductReviewRepository repository;

    @Autowired
    private EntityManager entityManager;
}
