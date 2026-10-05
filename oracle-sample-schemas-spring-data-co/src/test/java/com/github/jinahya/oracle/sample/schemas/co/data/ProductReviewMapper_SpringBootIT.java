package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductReview;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Tests {@link ProductReviewMapper} against the installed CO schema, by comparing each of its statements with the
 * {@link ProductReviewRepository} method of the same name.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 */
@SpringBootTest
@Slf4j
class ProductReviewMapper_SpringBootIT {

    /**
     * Selects the name of a random product with reviews: a random row of the view, picked by
     * {@link __NativeSql_TestUtils} over a {@code JdbcClient}.
     * <p>
     * Aborts the calling test if the view holds no row, or the row picked has no review.
     *
     * @return the name of a product with reviews.
     */
    private String selectRandomReviewedProductName() {
        final var index = __NativeSql_TestUtils.randomIndex(jdbcClient, "CO.PRODUCT_REVIEWS");
        assumeThat(index).isPresent();
        // A product with no reviews has one row, with no REVIEW; such a pick aborts the test.
        final var row = jdbcClient
                .sql("""
                        SELECT PRODUCT_NAME, REVIEW
                        FROM CO.PRODUCT_REVIEWS
                        ORDER BY PRODUCT_NAME, RATING, REVIEW
                        OFFSET :offset ROWS FETCH NEXT 1 ROWS ONLY""")
                .param("offset", index.getAsLong())
                .query((rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2)})
                .single();
        assumeThat(row[1]).as("review").isNotNull();
        return row[0];
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link ProductReviewMapper#countAllByProductName(String)}.
     */
    @Nested
    class CountAllByProductName_Test {

        @Test
        void __() {
            final var productName = selectRandomReviewedProductName();
            assertThat(mapper.countAllByProductName(productName))
                    .isPositive()
                    .isEqualTo(repository.countAllByProductName(productName));
        }
    }

    /**
     * Tests {@link ProductReviewMapper#findAllByProductName(String, long, int)}.
     */
    @Nested
    class FindAllByProductName_Test {

        /**
         * Asserts that the mapper's page of all and its page of the second two equal the repository's, column by
         * column.
         */
        @Test
        void __() {
            final var productName = selectRandomReviewedProductName();
            final var count = (int) repository.countAllByProductName(productName);
            for (final var page : new long[][]{{0L, count}, {2L, 2L}}) {
                final var found = mapper.findAllByProductName(productName, page[0], (int) page[1]);
                found.forEach(r -> log.debug("review: {}", r));
                final var expected = repository.findAllByProductName(productName, page[0], (int) page[1]);
                assertThat(found)
                        .extracting(ProductReview::getProductName, ProductReview::getRating,
                                    ProductReview::getAvgRating, ProductReview::getReview)
                        .containsExactlyElementsOf(
                                expected.stream()
                                        .map(r -> tuple(r.getProductName(), r.getRating(), r.getAvgRating(),
                                                        r.getReview()))
                                        .toList());
            }
        }
    }

    /**
     * Tests the constraints of {@link ProductReviewMapper}'s parameters.
     */
    @Nested
    class Constraints_Test {

        /**
         * Asserts that a blank product name, a negative offset and a non-positive limit are rejected before any
         * statement runs.
         */
        @Test
        void _ConstraintViolationException_Arguments() {
            assertThatThrownBy(() -> mapper.countAllByProductName(" "))
                    .isInstanceOf(ConstraintViolationException.class);
            assertThatThrownBy(() -> mapper.findAllByProductName("x", -1L, 10))
                    .isInstanceOf(ConstraintViolationException.class);
            assertThatThrownBy(() -> mapper.findDistinctProductNamesOrderByAvgRating(0L, 0))
                    .isInstanceOf(ConstraintViolationException.class);
        }
    }

    /**
     * Tests {@link ProductReviewMapper#countDistinctProductNames()}.
     */
    @Nested
    class CountDistinctProductNames_Test {

        @Test
        void __() {
            assertThat(mapper.countDistinctProductNames())
                    .isPositive()
                    .isEqualTo(repository.countDistinctProductNames());
        }
    }

    /**
     * Tests {@link ProductReviewMapper#findDistinctProductNamesOrderByAvgRating(long, int)}.
     */
    @Nested
    class FindDistinctProductNamesOrderByAvgRatingDesc_Test {

        /**
         * Asserts that the mapper's first two pages of seven equal the repository's.
         */
        @Test
        void __() {
            for (final var offset : new long[]{0L, 7L}) {
                final var desc = mapper.findDistinctProductNamesOrderByAvgRating(offset, 7);
                log.debug("desc: {}", desc);
                assertThat(desc)
                        .isNotEmpty()
                        .containsExactlyElementsOf(
                                repository.findDistinctProductNamesOrderByAvgRating(offset, 7));
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private ProductReviewMapper mapper;

    @Autowired
    private ProductReviewRepository repository;

    @Autowired
    private JdbcClient jdbcClient;
}
