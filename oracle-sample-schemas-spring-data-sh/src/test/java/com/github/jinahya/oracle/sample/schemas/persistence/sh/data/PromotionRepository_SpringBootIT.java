package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Promotion;

/**
 * Tests {@link PromotionRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see PromotionRepository_DataJpaTest
 */
class PromotionRepository_SpringBootIT
        extends _Repository_SpringBootIT<PromotionRepository, Promotion, Integer> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    PromotionRepository_SpringBootIT() {
        super(PromotionRepository.class, Promotion.class, Integer.class);
    }
}
