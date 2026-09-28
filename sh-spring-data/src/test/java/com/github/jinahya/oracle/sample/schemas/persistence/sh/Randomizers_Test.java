package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__RandomizerUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests the {@code *_Randomizer} classes of this package, all 21 of them.
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
                CalMonthSalesMv.class,
                Channel.class,
                CostId.class,
                CostWithEmbeddedId.class,
                CostWithIdClass.class,
                Country.class,
                CountrySection.class,
                Customer.class,
                FweekPscatSalesMvId.class,
                FweekPscatSalesMvWithEmbeddedId.class,
                FweekPscatSalesMvWithIdClass.class,
                Product.class,
                ProfitsId.class,
                ProfitsWithEmbeddedId.class,
                ProfitsWithIdClass.class,
                Promotion.class,
                SaleId.class,
                SaleWithEmbeddedId.class,
                SaleWithIdClass.class,
                SupplementaryDemographics.class,
                Time.class
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
