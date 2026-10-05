package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductReview;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * The implementation of the {@link ProductReviewRepository} fragment, over a {@link JdbcClient}.
 * <p>
 * Spring Data finds this class by its name, the fragment's plus {@code Impl}, and wires it into every repository that
 * extends the fragment; it needs no {@code @Repository}, component scan or {@code @Import}. {@link JdbcClient} throws
 * {@code DataAccessException}s itself.
 * <p>
 * Each statement lives in a {@code .sql} resource beside this class, named after the view and the query, as
 * {@code PRODUCT_REVIEWS_FIND_ALL_BY_PRODUCT_NAME.sql}. {@code hibernate.default_schema} does not reach those, and the
 * connection's user is not the schema's owner, so they name the view with its schema: {@code CO.PRODUCT_REVIEWS}.
 * Inside a transaction this shares JPA's connection but sees only what JPA has flushed.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProductReviewRepositoryImpl
        implements ProductReviewRepository {

    /**
     * Reads the statement of the specified resource, which lives in this class's package.
     * <p>
     * A trailing {@code ;} is dropped, as Oracle's driver rejects one. A missing resource fails the loading of this
     * class, and with it the context, rather than the first call.
     *
     * @param name the name of the resource, relative to this class's package.
     * @return the statement.
     */
    private static String sql(final String name) {
        final var resource = new ClassPathResource(name, ProductReviewRepositoryImpl.class);
        try {
            return resource.getContentAsString(StandardCharsets.UTF_8).strip().replaceFirst(";$", "");
        } catch (final IOException ioe) {
            throw new UncheckedIOException("failed to read " + resource, ioe);
        }
    }

    /**
     * Checks the specified page arguments.
     *
     * @param offset the number of rows to skip.
     * @param limit  the maximum number of rows to return.
     * @throws IllegalArgumentException if {@code offset} is negative, or {@code limit} is not positive.
     */
    private static void requirePage(final long offset, final int limit) {
        if (offset < 0L) {
            throw new IllegalArgumentException("offset(" + offset + ") is negative");
        }
        if (limit <= 0) {
            throw new IllegalArgumentException("limit(" + limit + ") is not positive");
        }
    }

    /**
     * The statement of {@link #findAllByProductName(String, long, int)}, from
     * {@code PRODUCT_REVIEWS_FIND_ALL_BY_PRODUCT_NAME.sql} beside this class.
     */
    private static final String SQL_FIND_ALL_BY_PRODUCT_NAME = sql("PRODUCT_REVIEWS_FIND_ALL_BY_PRODUCT_NAME.sql");

    /**
     * The statement of {@link #countByProductName(String)}, from
     * {@code PRODUCT_REVIEWS_COUNT_BY_PRODUCT_NAME.sql} beside this class.
     */
    private static final String SQL_COUNT_BY_PRODUCT_NAME = sql("PRODUCT_REVIEWS_COUNT_BY_PRODUCT_NAME.sql");

    /**
     * The statement of {@link #countDistinctProductNamesOfProductReviews()}.
     */
    private static final String SQL_COUNT_DISTINCT_PRODUCT_NAMES =
            sql("PRODUCT_REVIEWS_COUNT_DISTINCT_PRODUCT_NAMES.sql");

    /**
     * The statement of {@link #findDistinctProductNamesOfProductReviewsOrderByAvgRatingAsc(long, int)}.
     */
    private static final String SQL_FIND_DISTINCT_PRODUCT_NAMES_ORDER_BY_AVG_RATING_ASC =
            sql("PRODUCT_REVIEWS_FIND_DISTINCT_PRODUCT_NAMES_ORDER_BY_AVG_RATING_ASC.sql");

    /**
     * The statement of {@link #findDistinctProductNamesOfProductReviewsOrderByAvgRatingDesc(long, int)}.
     */
    private static final String SQL_FIND_DISTINCT_PRODUCT_NAMES_ORDER_BY_AVG_RATING_DESC =
            sql("PRODUCT_REVIEWS_FIND_DISTINCT_PRODUCT_NAMES_ORDER_BY_AVG_RATING_DESC.sql");

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public long countByProductName(final String productName) {
        Objects.requireNonNull(productName, "productName is null");
        return jdbcClient.sql(SQL_COUNT_BY_PRODUCT_NAME)
                .param("productName", productName)
                .query(Long.class)
                .single();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Each row maps into a {@link ProductReview} by column name ({@code PRODUCT_NAME} into {@code productName}, and so
     * on), through its setters.
     */
    @Override
    public List<ProductReview> findAllByProductName(final String productName, final long offset,
                                                    final int limit) {
        Objects.requireNonNull(productName, "productName is null");
        requirePage(offset, limit);
        return jdbcClient.sql(SQL_FIND_ALL_BY_PRODUCT_NAME)
                .param("productName", productName)
                .param("offset", offset)
                .param("limit", limit)
                .query(ProductReview.class)
                .list();
    }

    @Override
    public long countDistinctProductNamesOfProductReviews() {
        return jdbcClient.sql(SQL_COUNT_DISTINCT_PRODUCT_NAMES)
                .query(Long.class)
                .single();
    }

    @Override
    public List<String> findDistinctProductNamesOfProductReviewsOrderByAvgRatingAsc(final long offset,
                                                                                    final int limit) {
        return findDistinctProductNames(SQL_FIND_DISTINCT_PRODUCT_NAMES_ORDER_BY_AVG_RATING_ASC, offset, limit);
    }

    @Override
    public List<String> findDistinctProductNamesOfProductReviewsOrderByAvgRatingDesc(final long offset,
                                                                                     final int limit) {
        return findDistinctProductNames(SQL_FIND_DISTINCT_PRODUCT_NAMES_ORDER_BY_AVG_RATING_DESC, offset, limit);
    }

    /**
     * Runs the specified statement, which selects {@code PRODUCT_NAME} and {@code AVG_RATING}, and returns the names.
     *
     * @param sql    the statement.
     * @param offset the number of rows to skip.
     * @param limit  the maximum number of rows to return.
     * @return a list of the product names.
     */
    private List<String> findDistinctProductNames(final String sql, final long offset, final int limit) {
        requirePage(offset, limit);
        return jdbcClient.sql(sql)
                .param("offset", offset)
                .param("limit", limit)
                .query((rs, rowNum) -> rs.getString(ProductReview.COLUMN_NAME_PRODUCT_NAME))
                .list();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final JdbcClient jdbcClient;
}
