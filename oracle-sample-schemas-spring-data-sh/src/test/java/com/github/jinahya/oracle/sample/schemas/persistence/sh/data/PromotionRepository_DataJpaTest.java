package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Promotion;

/**
 * Tests {@link PromotionRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see PromotionRepository_SpringBootIT
 */
class PromotionRepository_DataJpaTest
        extends _Repository_DataJpaTest<PromotionRepository, Promotion, Integer> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    PromotionRepository_DataJpaTest() {
        super(PromotionRepository.class, Promotion.class, Integer.class);
    }
}
