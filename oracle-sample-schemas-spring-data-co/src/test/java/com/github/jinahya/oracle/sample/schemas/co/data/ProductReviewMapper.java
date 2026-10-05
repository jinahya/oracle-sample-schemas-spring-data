package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductReview;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * A MyBatis mapper for the {@code PRODUCT_REVIEWS} view, the equivalent of {@link ProductReviewRepository}, for tests.
 * <p>
 * {@link #countAllByProductName(String)} and {@link #findAllByProductName(String, long, int)} carry their statements in
 * {@link Select @Select} annotations; the others' statements, and the result maps, are in
 * {@code ProductReviewMapper.xml}, in the same package under {@code src/test/resources}, where MyBatis finds them by
 * the interface's name. A statement is defined in one place only: the same id in both fails at startup. Boot's MyBatis
 * auto-configuration registers this interface by its {@link Mapper @Mapper}, in a {@code @SpringBootTest} only; the
 * {@code @DataJpaTest} slice leaves MyBatis out.
 * <p>
 * The constraints are {@link ProductReviewRepository}'s, enforced by Spring's method validation, which
 * {@link Validated @Validated} opts this mapper's proxy into: a bad argument throws a
 * {@code ConstraintViolationException} before any statement runs.
 * <p>
 * The statements bind by name. Each parameter is named with {@link Param @Param}, which works with or without the
 * build's {@code -parameters}. The statements are {@link ProductReviewRepositoryImpl}'s, in MyBatis's {@code #{...}}
 * placeholders.
 *
 * @see ProductReviewRepository
 */
@Validated
@Mapper
interface ProductReviewMapper {

    /**
     * Counts the reviews of the products of the specified name.
     *
     * @param productName the product name to match.
     * @return the number of the reviews.
     * @see ProductReviewRepository#countAllByProductName(String)
     */
    @Select("""
            SELECT COUNT(*)
            FROM CO.PRODUCT_REVIEWS
            WHERE PRODUCT_NAME = #{productName}""")
    long countAllByProductName(@Param("productName") @NotBlank String productName);

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
    // The view has no key; the order is fixed so that consecutive pages neither repeat nor skip rows.
    @Select("""
            SELECT *
            FROM CO.PRODUCT_REVIEWS
            WHERE PRODUCT_NAME = #{productName}
            ORDER BY RATING, REVIEW
            OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY""")
    // The column-to-property mapping of ProductReviewMapper.xml.
    @ResultMap("productReview")
    List<@Valid @NotNull ProductReview> findAllByProductName(@Param("productName") @NotBlank String productName,
                                                             @Param("offset") @PositiveOrZero long offset,
                                                             @Param("limit") @Positive int limit);

    /**
     * Counts the distinct names of the products in the view.
     *
     * @return the number of distinct product names.
     * @see ProductReviewRepository#countDistinctProductNames()
     */
    long countDistinctProductNames();

    /**
     * Finds a page of the distinct product names, by their average rating, highest first, unreviewed last.
     *
     * @param offset the number of names to skip.
     * @param limit  the maximum number of names to return.
     * @return a list of the product names.
     * @see ProductReviewRepository#findDistinctProductNamesOrderByAvgRating(long, int)
     */
    List<@NotBlank String> findDistinctProductNamesOrderByAvgRating(@Param("offset") @PositiveOrZero long offset,
                                                                    @Param("limit") @Positive int limit);
}
