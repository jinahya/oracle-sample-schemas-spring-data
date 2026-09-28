package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__RandomizerUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests the {@code *_Randomizer} classes of this package, all 16 of them.
 * <p>
 * Nothing Spring here: {@link __RandomizerUtils} finds a randomizer by name, instantiates it and calls it, so this is
 * the cheapest place to catch a randomizer that no longer matches its entity. A {@code newRandomizerInstanceOf} that
 * comes back empty means the lookup missed the class, which for a {@code <Entity>_Randomizer} in this package means a
 * naming slip; an empty {@code newRandomizedInstanceOf} means the randomizer was found but could not produce a value.
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class Randomizers_Test {

    private static Stream<Class<?>> targetClasses() {
        return Stream.of(
                Customer.class,
                CustomerOrderProducts.class,
                Inventory.class,
                Order.class,
                OrderItemId.class,
                OrderItemWithEmbeddedId.class,
                OrderItemWithIdClass.class,
                Product.class,
                ProductDetails.class,
                ProductImage.class,
                ProductOrdersId.class,
                ProductOrdersWithEmbeddedId.class,
                ProductOrdersWithIdClass.class,
                Shipment.class,
                Store.class,
                StoreLogo.class
        );
    }

    @MethodSource("targetClasses")
    @ParameterizedTest
    void newRandomizerInstanceOf__(final Class<?> targetClass) {
        assertThat(__RandomizerUtils.newRandomizerInstanceOf(targetClass))
                .as("the randomizer located for %s", targetClass)
                .isNotEmpty();
    }

    @MethodSource("targetClasses")
    @ParameterizedTest
    void newRandomizedInstanceOf__(final Class<?> targetClass) {
        assertThat(__RandomizerUtils.newRandomizedInstanceOf(targetClass))
                .as("a randomized instance of %s", targetClass)
                .isNotEmpty();
    }
}
