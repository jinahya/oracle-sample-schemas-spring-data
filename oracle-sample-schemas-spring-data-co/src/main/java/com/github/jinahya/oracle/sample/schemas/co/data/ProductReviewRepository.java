package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductReview;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

/**
 * A repository for the {@code PRODUCT_REVIEWS} view of the Customer Orders schema, which upstream maps as
 * {@link ProductReview}, a class that is not an {@code @Entity}: no column, and no combination of columns, identifies a
 * row of the view.
 * <p>
 * This is not a Spring Data repository: Spring Data JPA builds repositories for managed types only, and rejects
 * {@link ProductReview} at startup ({@code Not a managed type}). It is a plain interface, implemented by
 * {@link ProductReviewRepositoryImpl}, a {@code @Repository} bean over a {@code JdbcClient}; inject it as this
 * interface. Declare no {@code findById}, {@code save} or {@code delete}: the view has no usable key, and every column
 * of it is read-only.
 *
 * @see ProductReview
 * @see ProductReviewRepositoryImpl
 */
public interface ProductReviewRepository {

    /**
     * Counts the reviews of the products of the specified name.
     * <p>
     * The view carries the product's name, not its id, so reviews of every product of that name are counted together.
     * The match is exact and case-sensitive.
     *
     * @param productName the value of the {@link ProductReview#COLUMN_NAME_PRODUCT_NAME} column to match; must not be
     *                    blank.
     * @return the number of the reviews of the products named {@code productName}.
     * @throws jakarta.validation.ConstraintViolationException if {@code productName} is blank, when called through a
     *                                                         validating proxy, as the {@code @Validated}
     *                                                         implementation bean is.
     * @see #findAllByProductName(String, long, int)
     */
    @PositiveOrZero
    long countAllByProductName(@NotBlank String productName);

    /**
     * Finds a page of the reviews of the products of the specified name.
     * <p>
     * The view carries the product's name, not its id, so reviews of every product of that name come back together. The
     * match is exact and case-sensitive. The rows are ordered by {@code RATING}, then {@code REVIEW}, both ascending
     * with {@code NULL}s last, so that consecutive pages neither repeat nor skip rows. Two reviews alike in both are
     * indistinguishable anyway.
     *
     * @param productName the value of the {@link ProductReview#COLUMN_NAME_PRODUCT_NAME} column to match; must not be
     *                    blank.
     * @param offset      the number of rows to skip; must not be negative.
     * @param limit       the maximum number of rows to return; must be positive.
     * @return a list of at most {@code limit} reviews of the products named {@code productName}, after the first
     *         {@code offset}; empty if there is none.
     * @throws jakarta.validation.ConstraintViolationException if an argument breaks its constraint, when called through
     *                                                         a validating proxy, as the {@code @Validated}
     *                                                         implementation bean is.
     * @see #countAllByProductName(String)
     */
    List<@Valid @NotNull ProductReview> findAllByProductName(@NotBlank String productName, @PositiveOrZero long offset,
                                                             @Positive int limit);

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Counts the distinct names of the products in the view, reviewed or not.
     *
     * @return the number of distinct product names.
     * @see #findDistinctProductNamesOrderByAvgRating(long, int)
     */
    @PositiveOrZero
    long countDistinctProductNames();

    /**
     * Finds a page of the distinct names of the products in the view, by their average rating, highest first.
     * <p>
     * The average is the view's {@code AVG_RATING}, which it computes over all the reviews of a name, so each name has
     * one. A product with no reviews has none, and comes last. Names of the same average are ordered by name, so
     * consecutive pages neither repeat nor skip a name.
     *
     * @param offset the number of names to skip; must not be negative.
     * @param limit  the maximum number of names to return; must be positive.
     * @return a list of at most {@code limit} product names, after the first {@code offset}; empty past the last.
     * @throws jakarta.validation.ConstraintViolationException if an argument breaks its constraint, when called through
     *                                                         a validating proxy, as the {@code @Validated}
     *                                                         implementation bean is.
     * @see #countDistinctProductNames()
     */
    List<@NotBlank String> findDistinctProductNamesOrderByAvgRating(@PositiveOrZero long offset,
                                                                    @Positive int limit);
}
