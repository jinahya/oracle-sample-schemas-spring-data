package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductReview;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * The {@link ProductReviewRepository}, over a {@link JdbcClient}.
 * <p>
 * A {@code @Repository} bean of its own, not part of any Spring Data repository: register it by component scanning
 * this package, or by {@code @Import}ing this class. {@code @Repository} also has Spring translate its exceptions,
 * an {@link IllegalArgumentException} included, into {@code DataAccessException}s, once a
 * {@code PersistenceExceptionTranslationPostProcessor} is in the context, as Boot puts one.
 * <p>
 * {@link #countAllByProductName(String)} and {@link #findAllByProductName(String, long, int)} write their statements in
 * place; the others read theirs from {@code .sql} resources beside this class, named after the view and the query, as
 * {@code PRODUCT_REVIEWS_COUNT_DISTINCT_PRODUCT_NAMES.sql}. {@code hibernate.default_schema} does not reach those,
 * and the connection's user is not the schema's owner, so they name the view with its schema:
 * {@code CO.PRODUCT_REVIEWS}. Inside a transaction this shares JPA's connection but sees only what JPA has flushed.
 */
@Validated
@Repository
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ProductReviewRepositoryImpl
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
     * The statement of {@link #countDistinctProductNames()}.
     */
    private static final String SQL_COUNT_DISTINCT_PRODUCT_NAMES =
            sql("PRODUCT_REVIEWS_COUNT_DISTINCT_PRODUCT_NAMES.sql");

    /**
     * The statement of {@link #findDistinctProductNamesOrderByAvgRating(long, int)}.
     */
    private static final String SQL_FIND_DISTINCT_PRODUCT_NAMES_ORDER_BY_AVG_RATING_DESC =
            sql("PRODUCT_REVIEWS_FIND_DISTINCT_PRODUCT_NAMES_ORDER_BY_AVG_RATING_DESC.sql");

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public long countAllByProductName(final String productName) {
        Objects.requireNonNull(productName, "productName is null");
        return jdbcClient.sql("""
                        SELECT COUNT(*)
                        FROM CO.PRODUCT_REVIEWS
                        WHERE PRODUCT_NAME = :productName""")
                .param("productName", productName)
                .query(Long.class)
                .single();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Each row maps into a {@link ProductReview} through its public all-attributes constructor: Spring's
     * {@code SimplePropertyRowMapper} matches the columns to the constructor's parameter names ({@code PRODUCT_NAME} to
     * {@code productName}, and so on), which upstream compiles in with {@code -parameters}, and converts the values, so
     * {@code RATING}'s {@code BigDecimal} arrives as the parameter's {@code Integer}.
     */
    @Override
    public List<ProductReview> findAllByProductName(final String productName, final long offset,
                                                    final int limit) {
        Objects.requireNonNull(productName, "productName is null");
        requirePage(offset, limit);
        // The view has no key; the order is fixed so that consecutive pages neither repeat nor skip rows.
        return jdbcClient.sql("""
                        SELECT *
                        FROM CO.PRODUCT_REVIEWS
                        WHERE PRODUCT_NAME = :productName
                        ORDER BY RATING, REVIEW
                        OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY""")
                .param("productName", productName)
                .param("offset", offset)
                .param("limit", limit)
                .query(ProductReview.class)
                .list();
    }

    @Override
    public long countDistinctProductNames() {
        return jdbcClient.sql(SQL_COUNT_DISTINCT_PRODUCT_NAMES)
                .query(Long.class)
                .single();
    }

    @Override
    public List<String> findDistinctProductNamesOrderByAvgRating(final long offset,
                                                                 final int limit) {
        requirePage(offset, limit);
        return jdbcClient.sql(SQL_FIND_DISTINCT_PRODUCT_NAMES_ORDER_BY_AVG_RATING_DESC)
                .param("offset", offset)
                .param("limit", limit)
                .query((rs, rowNum) -> rs.getString(ProductReview.COLUMN_NAME_PRODUCT_NAME))
                .list();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final JdbcClient jdbcClient;
}
