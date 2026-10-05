package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductReview;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Tests {@link ProductReviewMapper} against the installed CO schema, by comparing each of its statements with the
 * {@link ProductReviewRepository} method of the same name, which {@link ProductRepository} brings.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 *
 * @see ProductRepository_SpringBootIT
 */
@SpringBootTest
@Slf4j
class ProductReviewMapper_SpringBootIT {

    /**
     * Selects the name of a random product with reviews, straight from the view, and aborts the calling test if the
     * view holds none.
     *
     * @return the name of a product with reviews.
     */
    private String selectRandomReviewedProductName() {
        final var productName = (String) entityManager
                .createNativeQuery("SELECT PRODUCT_NAME FROM CO.PRODUCT_REVIEWS WHERE REVIEW IS NOT NULL"
                                   + " ORDER BY DBMS_RANDOM.VALUE FETCH FIRST 1 ROWS ONLY")
                .getResultList().stream().findFirst().orElse(null);
        assumeThat(productName).isNotNull();
        return productName;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link ProductReviewMapper#countByProductName(String)}.
     */
    @Nested
    class CountByProductName_Test {

        @Test
        void __() {
            final var productName = selectRandomReviewedProductName();
            assertThat(mapper.countByProductName(productName))
                    .isPositive()
                    .isEqualTo(repository.countByProductName(productName));
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
            final var count = (int) repository.countByProductName(productName);
            for (final var page : new long[][] {{0L, count}, {2L, 2L}}) {
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
     * Tests {@link ProductReviewMapper#countDistinctProductNamesOfProductReviews()}.
     */
    @Nested
    class CountDistinctProductNamesOfProductReviews_Test {

        @Test
        void __() {
            assertThat(mapper.countDistinctProductNamesOfProductReviews())
                    .isPositive()
                    .isEqualTo(repository.countDistinctProductNamesOfProductReviews());
        }
    }

    /**
     * Tests {@link ProductReviewMapper#findDistinctProductNamesOfProductReviewsOrderByAvgRatingAsc(long, int)} and
     * {@link ProductReviewMapper#findDistinctProductNamesOfProductReviewsOrderByAvgRatingDesc(long, int)}.
     */
    @Nested
    class FindDistinctProductNamesOfProductReviewsOrderByAvgRating_Test {

        /**
         * Asserts that the mapper's first two pages of seven, in each direction, equal the repository's.
         */
        @Test
        void __() {
            for (final var offset : new long[] {0L, 7L}) {
                final var asc = mapper.findDistinctProductNamesOfProductReviewsOrderByAvgRatingAsc(offset, 7);
                log.debug("asc: {}", asc);
                assertThat(asc)
                        .isNotEmpty()
                        .containsExactlyElementsOf(
                                repository.findDistinctProductNamesOfProductReviewsOrderByAvgRatingAsc(offset, 7));
                final var desc = mapper.findDistinctProductNamesOfProductReviewsOrderByAvgRatingDesc(offset, 7);
                log.debug("desc: {}", desc);
                assertThat(desc)
                        .isNotEmpty()
                        .containsExactlyElementsOf(
                                repository.findDistinctProductNamesOfProductReviewsOrderByAvgRatingDesc(offset, 7));
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private ProductReviewMapper mapper;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private EntityManager entityManager;
}
