package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.co.data.jooq.tables.records.ProductReviewsRecord;
import com.github.jinahya.oracle.sample.schemas.persistence.co.ProductReview;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.jinahya.oracle.sample.schemas.co.data.jooq.Tables.PRODUCT_REVIEWS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

@Import({_Jooq_TestConfiguration.class, ProductReviewService.class})
@SpringBootTest
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class ProductReviewService_SpringBootIT {

    /**
     * Selects the reviewed rows of the view, and asserts that each row's {@code AVG_RATING} is the average of the
     * {@code RATING}s of its product, rounded to two places, as the view computes it.
     * <p>
     * A product with no reviews still yields one row, with a {@code NULL} {@code RATING}, {@code AVG_RATING} and
     * {@code REVIEW}: the view's {@code JSON_TABLE} keeps the product and finds nothing in it. The {@code WHERE} leaves
     * those out.
     */
    @Test
    void selectAll() {
        final var records = dsl.selectFrom(PRODUCT_REVIEWS)
                .where(PRODUCT_REVIEWS.RATING.isNotNull())
                .fetch();
        assumeThat(records).isNotEmpty();
        final var averages = records.stream().collect(Collectors.groupingBy(
                ProductReviewsRecord::getProductName,
                Collectors.averagingDouble(r -> r.getRating().doubleValue())
        ));
        assertThat(records).allSatisfy(r -> assertThat(r.getAvgRating()).isEqualByComparingTo(
                BigDecimal.valueOf(averages.get(r.getProductName())).setScale(2, RoundingMode.HALF_UP)
        ));
    }

    /**
     * Selects the reviews of one product, with a typed {@code WHERE}, and asserts that each is of that product.
     */
    @Test
    void selectWhereProductName() {
        final var any = dsl.selectFrom(PRODUCT_REVIEWS).fetchAny();
        assumeThat(any).isNotNull();
        final var productName = any.getProductName();
        final var records = dsl.selectFrom(PRODUCT_REVIEWS)
                .where(PRODUCT_REVIEWS.PRODUCT_NAME.eq(productName))
                .orderBy(PRODUCT_REVIEWS.RATING.desc())
                .fetch();
        assertThat(records)
                .isNotEmpty()
                .allSatisfy(r -> assertThat(r.getProductName()).isEqualTo(productName));
        log.debug("reviews of {}: {}", productName, records.size());
    }

    /**
     * Selects the rows into upstream's {@link ProductReview}, instead of jOOQ records, and asserts that the columns
     * land in the right components.
     */
    @Test
    void selectInto() {
        final var reviews = dsl.selectFrom(PRODUCT_REVIEWS)
                .where(PRODUCT_REVIEWS.RATING.isNotNull())
                .fetchInto(ProductReview.class);
        assumeThat(reviews).isNotEmpty();
        assertThat(reviews).allSatisfy(r -> {
            assertThat(r.getProductName()).isNotBlank();
            assertThat(r.getRating()).isNotNull();
            assertThat(r.getAvgRating()).isNotNull();
        });
    }

    /**
     * Selects one product's reviews, highest rating first, through the function, and asserts that they are that
     * product's reviews only, all of them, in that order.
     */
    @Test
    void findAllByProductName() {
        final var any = dsl.selectFrom(PRODUCT_REVIEWS).where(PRODUCT_REVIEWS.RATING.isNotNull()).fetchAny();
        assumeThat(any).isNotNull();
        final var productName = any.getProductName();
        final var expected = dsl.fetchCount(PRODUCT_REVIEWS, PRODUCT_REVIEWS.PRODUCT_NAME.eq(productName));
        final var records = service.select(
                productName, s -> s.orderBy(PRODUCT_REVIEWS.RATING.desc().nullsLast()).fetch());
        assertThat(records)
                .hasSize(expected)
                .allSatisfy(r -> assertThat(r.getProductName()).isEqualTo(productName))
                .isSortedAccordingTo(Comparator.comparing(
                        ProductReviewsRecord::getRating, Comparator.nullsLast(Comparator.reverseOrder())));
    }

    /**
     * Asserts that two product names find the reviews of both, and no names finds none.
     */
    @Test
    void findAllByProductNames() {
        final var names = dsl.selectDistinct(PRODUCT_REVIEWS.PRODUCT_NAME).from(PRODUCT_REVIEWS)
                .fetch(PRODUCT_REVIEWS.PRODUCT_NAME).stream().limit(2).toList();
        assumeThat(names).hasSize(2);
        final var records = service.select(names, s -> s);
        assertThat(records)
                .isNotEmpty()
                .allSatisfy(r -> assertThat(r.getProductName()).isIn(names));
        assertThat(service.select(List.of(), s -> s)).isEmpty();
    }

    /**
     * Asserts that a product's id finds the same reviews as its name, and an unknown id finds none.
     */
    @Test
    void findAllByProductId() {
        final var product = productRepository.findAll(PageRequest.of(0, 1)).stream().findFirst();
        assumeThat(product).isPresent();
        final var byId = service.findAllByProductId(product.get().getProductId(), s -> s);
        final var byName = service.select(product.get().getProductName(), s -> s.fetch());
        assertThat(byId).hasSameSizeAs(byName);
        assertThat(service.findAllByProductId(-1L, s -> s)).isEmpty();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private DSLContext dsl;

    @Autowired
    private ProductReviewService service;

    @Autowired
    private ProductRepository productRepository;
}
