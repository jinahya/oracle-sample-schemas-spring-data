package com.github.jinahya.oracle.sample.persistence.co.data;

import com.github.jinahya.oracle.sample.persistence.co.Product;
import com.github.jinahya.oracle.sample.persistence.co.Product_;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.math.BigDecimal;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests {@link ProductRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. Each test saves the products it needs, at the prices it needs,
 * and the slice rolls them back when it ends. The assertions name the products a test saved, and check the bounds of
 * whatever else comes back, rather than count the results.
 *
 * @see ProductRepository_SpringBootIT
 */
class ProductRepository_DataJpaTest
        extends _Repository_DataJpaTest<ProductRepository, Product, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ProductRepository_DataJpaTest() {
        super(ProductRepository.class, Product.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link ProductRepository#findAllByUnitPriceBetween(BigDecimal, BigDecimal, Pageable)}.
     */
    @Nested
    class FindAllByUnitPriceBetween_Test {

        /**
         * Saves products at, just inside and just outside both bounds, and one with no price, and asserts that the
         * range includes both bounds and nothing else.
         */
        @Test
        void __BothBoundsInclusive() {
            final var min = new BigDecimal("10.00");
            final var max = new BigDecimal("20.00");
            final var belowMin = saveProduct(new BigDecimal("9.99"));
            final var atMin = saveProduct(min);
            final var inside = saveProduct(new BigDecimal("15.00"));
            final var atMax = saveProduct(max);
            final var aboveMax = saveProduct(new BigDecimal("20.01"));
            final var noPrice = saveProduct(null);
            final var found = repositoryInstance().findAllByUnitPriceBetween(min, max, Pageable.unpaged());
            assertThat(found.getContent())
                    .contains(atMin, inside, atMax)
                    .doesNotContain(belowMin, aboveMax, noPrice)
                    .allSatisfy(p -> assertThat(p.getUnitPrice()).isBetween(min, max));
        }

        /**
         * Saves a product, and asserts that a range whose lower bound is greater than its upper bound finds nothing.
         */
        @Test
        void _Empty_MinGreaterThanMax() {
            saveProduct(new BigDecimal("15.00"));
            final var found = repositoryInstance().findAllByUnitPriceBetween(
                    new BigDecimal("20.00"), new BigDecimal("10.00"), Pageable.unpaged());
            assertThat(found).isEmpty();
        }

        /**
         * Saves products in the range, out of order, and asserts that a page sorted by unit price comes back sorted,
         * and counts all the matches.
         */
        @Test
        void __Sorted() {
            final var min = new BigDecimal("30.00");
            final var max = new BigDecimal("40.00");
            saveProduct(new BigDecimal("38.00"));
            saveProduct(new BigDecimal("31.00"));
            saveProduct(new BigDecimal("35.00"));
            final var found = repositoryInstance().findAllByUnitPriceBetween(
                    min, max, PageRequest.of(0, 2, JpaSort.of(Sort.Direction.ASC, Product_.unitPrice)));
            assertThat(found.getContent())
                    .hasSizeLessThanOrEqualTo(2)
                    .isSortedAccordingTo(Comparator.comparing(Product::getUnitPrice));
            assertThat(found.getTotalElements()).isGreaterThanOrEqualTo(3L);
        }
    }

    /**
     * Tests {@link ProductRepository#findAllByUnitPriceGreaterThanEqualAndUnitPriceLessThan(BigDecimal, BigDecimal,
     * Pageable)}.
     */
    @Nested
    class FindAllByUnitPriceGreaterThanEqualAndUnitPriceLessThan_Test {

        /**
         * Saves products at, just inside and just outside both bounds, and one with no price, and asserts that the
         * range includes its lower bound but not its upper one.
         */
        @Test
        void __HalfOpen() {
            final var min = new BigDecimal("10.00");
            final var max = new BigDecimal("20.00");
            final var belowMin = saveProduct(new BigDecimal("9.99"));
            final var atMin = saveProduct(min);
            final var belowMax = saveProduct(new BigDecimal("19.99"));
            final var atMax = saveProduct(max);
            final var noPrice = saveProduct(null);
            final var found = repositoryInstance().findAllByUnitPriceGreaterThanEqualAndUnitPriceLessThan(
                    min, max, Pageable.unpaged());
            assertThat(found.getContent())
                    .contains(atMin, belowMax)
                    .doesNotContain(belowMin, atMax, noPrice)
                    .allSatisfy(p -> assertThat(p.getUnitPrice()).isGreaterThanOrEqualTo(min).isLessThan(max));
        }

        /**
         * Saves a product at a price, and asserts that a range whose bounds are both that price finds nothing.
         */
        @Test
        void _Empty_MinEqualsMax() {
            final var price = new BigDecimal("15.00");
            saveProduct(price);
            final var found = repositoryInstance().findAllByUnitPriceGreaterThanEqualAndUnitPriceLessThan(
                    price, price, Pageable.unpaged());
            assertThat(found).isEmpty();
        }

        /**
         * Saves a product at the bound two consecutive ranges share, and asserts that only the upper range finds it.
         */
        @Test
        void __ConsecutiveRangesDisjoint() {
            final var lower = new BigDecimal("10.00");
            final var shared = new BigDecimal("20.00");
            final var upper = new BigDecimal("30.00");
            final var atShared = saveProduct(shared);
            final var below = repositoryInstance().findAllByUnitPriceGreaterThanEqualAndUnitPriceLessThan(
                    lower, shared, Pageable.unpaged());
            final var above = repositoryInstance().findAllByUnitPriceGreaterThanEqualAndUnitPriceLessThan(
                    shared, upper, Pageable.unpaged());
            assertThat(below.getContent()).doesNotContain(atShared);
            assertThat(above.getContent()).contains(atShared);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Saves a randomized product at the specified unit price, through the repository under test.
     *
     * @param unitPrice the unit price of the product; {@code null} for none.
     * @return the saved product.
     */
    private Product saveProduct(final @Nullable BigDecimal unitPrice) {
        final var product = _Repository_DataJpaTest_Fixtures.randomized(Product.class);
        product.setUnitPrice(unitPrice);
        return repositoryInstance().save(product);
    }
}
