package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * A MyBatis mapper for the {@code PRODUCT_REVIEWS} view, the equivalent of {@link ProductReviewRepository}, for tests.
 * <p>
 * Its statements are in {@code ProductReviewMapper.xml}, in the same package under {@code src/test/resources}, where
 * MyBatis finds them by the interface's name. Boot's MyBatis auto-configuration registers this interface by its
 * {@link Mapper @Mapper}, in a {@code @SpringBootTest} only; the {@code @DataJpaTest} slice leaves MyBatis out.
 * <p>
 * Each parameter is named with {@link Param @Param}: this build does not compile with {@code -parameters}, and the
 * statements bind by name. The statements are those of {@link ProductReviewRepositoryImpl}'s {@code .sql} resources,
 * in MyBatis's {@code #{...}} placeholders.
 *
 * @see ProductReviewRepository
 */
@Mapper
interface ProductReviewMapper {

    /**
     * Counts the reviews of the products of the specified name.
     *
     * @param productName the product name to match.
     * @return the number of the reviews.
     * @see ProductReviewRepository#countByProductName(String)
     */
    long countByProductName(@Param("productName") String productName);

    /**
     * Finds a page of the reviews of the products of the specified name, ordered by {@code RATING}, then
     * {@code REVIEW}.
     *
     * @param productName the product name to match.
     * @param offset      the number of rows to skip.
     * @param limit       the maximum number of rows to return.
     * @return a list of the reviews.
     * @see ProductReviewRepository#findAllByProductName(String, long, int)
     */
    List<ProductReview> findAllByProductName(@Param("productName") String productName,
                                             @Param("offset") long offset, @Param("limit") int limit);

    /**
     * Counts the distinct names of the products in the view.
     *
     * @return the number of distinct product names.
     * @see ProductReviewRepository#countDistinctProductNamesOfProductReviews()
     */
    long countDistinctProductNamesOfProductReviews();

    /**
     * Finds a page of the distinct product names, by their average rating, lowest first, unreviewed last.
     *
     * @param offset the number of names to skip.
     * @param limit  the maximum number of names to return.
     * @return a list of the product names.
     * @see ProductReviewRepository#findDistinctProductNamesOfProductReviewsOrderByAvgRatingAsc(long, int)
     */
    List<String> findDistinctProductNamesOfProductReviewsOrderByAvgRatingAsc(@Param("offset") long offset,
                                                                             @Param("limit") int limit);

    /**
     * Finds a page of the distinct product names, by their average rating, highest first, unreviewed last.
     *
     * @param offset the number of names to skip.
     * @param limit  the maximum number of names to return.
     * @return a list of the product names.
     * @see ProductReviewRepository#findDistinctProductNamesOfProductReviewsOrderByAvgRatingDesc(long, int)
     */
    List<String> findDistinctProductNamesOfProductReviewsOrderByAvgRatingDesc(@Param("offset") long offset,
                                                                              @Param("limit") int limit);
}
