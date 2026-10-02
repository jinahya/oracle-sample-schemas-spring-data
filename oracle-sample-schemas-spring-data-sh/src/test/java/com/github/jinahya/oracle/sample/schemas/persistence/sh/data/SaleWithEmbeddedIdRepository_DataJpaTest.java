package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.SaleId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.SaleWithEmbeddedId;

/**
 * Tests {@link SaleWithEmbeddedIdRepository} in a JPA slice, over an embedded H2, through
 * {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see SaleWithEmbeddedIdRepository_SpringBootIT
 */
class SaleWithEmbeddedIdRepository_DataJpaTest
        extends _Repository_DataJpaTest<SaleWithEmbeddedIdRepository, SaleWithEmbeddedId, SaleId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    SaleWithEmbeddedIdRepository_DataJpaTest() {
        super(SaleWithEmbeddedIdRepository.class, SaleWithEmbeddedId.class, SaleId.class);
    }
}
