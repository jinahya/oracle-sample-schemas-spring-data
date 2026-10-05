package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductReview;

import java.util.List;

/**
 * A repository for the {@code PRODUCT_REVIEWS} view of the Customer Orders schema, which upstream maps as
 * {@link ProductReview}, a class that is not an {@code @Entity}: no column, and no combination of columns, identifies a
 * row of the view.
 * <p>
 * This is a Spring Data repository fragment, not a repository: Spring Data JPA builds repositories for managed types
 * only, and rejects {@link ProductReview} at startup ({@code Not a managed type}). {@link ProductRepository} extends
 * it, and Spring Data backs its methods with {@link ProductReviewRepositoryImpl}, which it finds by name. Declare no
 * {@code findById}, {@code save} or {@code delete}: the view has no usable key, and every column of it is read-only.
 *
 * @see ProductReview
 * @see ProductReviewRepositoryImpl
 * @see ProductRepository
 */
public interface ProductReviewRepository {

    /**
     * Counts the reviews of the products of the specified name.
     * <p>
     * The view carries the product's name, not its id, so reviews of every product of that name are counted together.
     * The match is exact and case-sensitive.
     * <p>
     * The name is not {@code countByProductName}: {@link ProductRepository} extends this fragment, where that name
     * would read as a count of products.
     *
     * @param productName the value of the {@link ProductReview#COLUMN_NAME_PRODUCT_NAME} column to match; must not be
     *                    {@code null}.
     * @return the number of the reviews of the products named {@code productName}.
     * @see #findAllByProductName(String, long, int)
     */
    long countByProductName(String productName);

    /**
     * Finds a page of the reviews of the products of the specified name.
     * <p>
     * The view carries the product's name, not its id, so reviews of every product of that name come back together. The
     * match is exact and case-sensitive. The rows are ordered by {@code RATING}, then {@code REVIEW}, both ascending
     * with {@code NULL}s last, so that consecutive pages neither repeat nor skip rows. Two reviews alike in both are
     * indistinguishable anyway.
     * <p>
     * The name is not {@code findAllByProductName}: {@link ProductRepository} extends this fragment, where that name
     * would read as a search for products.
     *
     * @param productName the value of the {@link ProductReview#COLUMN_NAME_PRODUCT_NAME} column to match; must not be
     *                    {@code null}.
     * @param offset      the number of rows to skip; must not be negative.
     * @param limit       the maximum number of rows to return; must be positive.
     * @return a list of at most {@code limit} reviews of the products named {@code productName}, after the first
     *         {@code offset}; empty if there is none.
     * @throws org.springframework.dao.InvalidDataAccessApiUsageException if {@code offset} is negative, or
     *                                                                    {@code limit} is not positive; the
     *                                                                    repository's exception translation wraps the
     *                                                                    {@link IllegalArgumentException} the fragment
     *                                                                    throws.
     * @see #countByProductName(String)
     */
    List<ProductReview> findAllByProductName(String productName, long offset, int limit);

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Counts the distinct names of the products in the view, reviewed or not.
     *
     * @return the number of distinct product names.
     * @see #findDistinctProductNamesOfProductReviewsOrderByAvgRatingAsc(long, int)
     * @see #findDistinctProductNamesOfProductReviewsOrderByAvgRatingDesc(long, int)
     */
    long countDistinctProductNamesOfProductReviews();

    /**
     * Finds a page of the distinct names of the products in the view, by their average rating, lowest first.
     * <p>
     * The average is the view's {@code AVG_RATING}, which it computes over all the reviews of a name, so each name has
     * one. A product with no reviews has none, and comes last. Names of the same average are ordered by name, so
     * consecutive pages neither repeat nor skip a name.
     *
     * @param offset the number of names to skip; must not be negative.
     * @param limit  the maximum number of names to return; must be positive.
     * @return a list of at most {@code limit} product names, after the first {@code offset}; empty past the last.
     * @throws org.springframework.dao.InvalidDataAccessApiUsageException if {@code offset} is negative, or
     *                                                                   {@code limit} is not positive; the
     *                                                                   repository's exception translation wraps the
     *                                                                   {@link IllegalArgumentException} the fragment
     *                                                                   throws.
     * @see #countDistinctProductNamesOfProductReviews()
     * @see #findDistinctProductNamesOfProductReviewsOrderByAvgRatingDesc(long, int)
     */
    List<String> findDistinctProductNamesOfProductReviewsOrderByAvgRatingAsc(long offset, int limit);

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
     * @throws org.springframework.dao.InvalidDataAccessApiUsageException if {@code offset} is negative, or
     *                                                                   {@code limit} is not positive; the
     *                                                                   repository's exception translation wraps the
     *                                                                   {@link IllegalArgumentException} the fragment
     *                                                                   throws.
     * @see #countDistinctProductNamesOfProductReviews()
     * @see #findDistinctProductNamesOfProductReviewsOrderByAvgRatingAsc(long, int)
     */
    List<String> findDistinctProductNamesOfProductReviewsOrderByAvgRatingDesc(long offset, int limit);
}
