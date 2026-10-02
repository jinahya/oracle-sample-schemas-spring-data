package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.co.data.jooq.tables.records.ProductReviewsRecord;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.ResultQuery;
import org.jooq.SelectConditionStep;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static com.github.jinahya.oracle.sample.schemas.co.data.jooq.Tables.PRODUCT_REVIEWS;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class ProductReviewService {

    public List<ProductReviewsRecord> select() {
        return dsl
                .selectFrom(PRODUCT_REVIEWS)
                .fetch();
    }

    public <R> R select(final String productName,
                        final Function<? super SelectConditionStep<ProductReviewsRecord>, ? extends R> function) {
        Objects.requireNonNull(productName, "productName is null");
        final var step = dsl
                .selectFrom(PRODUCT_REVIEWS)
                .where(PRODUCT_REVIEWS.PRODUCT_NAME.eq(productName));
        return function.apply(step);
    }

    public List<ProductReviewsRecord> select(
            final @NotEmpty Collection<@NotBlank String> productNames,
            final Function<
                    ? super SelectConditionStep<ProductReviewsRecord>,
                    ? extends ResultQuery<ProductReviewsRecord>> function) {
        Objects.requireNonNull(productNames, "productNames is null");
        Objects.requireNonNull(function, "function is null");
        if (productNames.isEmpty()) {
            return List.of(); // jOOQ would render IN (), which Oracle rejects
        }
        final var step = dsl
                .selectFrom(PRODUCT_REVIEWS)
                .where(PRODUCT_REVIEWS.PRODUCT_NAME.in(productNames));
        return function.apply(step).fetch();
    }

    /**
     * The view carries no product id, only the product's name, so this looks the product up and selects by its name;
     * reviews of another product with the same name come along.
     */
    public List<ProductReviewsRecord> findAllByProductId(
            final Long productId,
            final Function<? super SelectConditionStep<ProductReviewsRecord>,
                    ? extends ResultQuery<ProductReviewsRecord>> function) {
        Objects.requireNonNull(productId, "productId is null");
        Objects.requireNonNull(function, "function is null");
        final var product = productRepository.findById(productId);
        if (product.isEmpty()) {
            return List.of();
        }
        final var step = dsl.selectFrom(PRODUCT_REVIEWS)
                .where(PRODUCT_REVIEWS.PRODUCT_NAME.eq(product.get().getProductName()));
        return function.apply(step).fetch();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final ProductRepository productRepository;

    private final DSLContext dsl;
}
