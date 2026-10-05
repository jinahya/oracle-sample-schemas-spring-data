package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Sale;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.SaleId;

/**
 * Tests {@link SaleRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see SaleRepository_SpringBootIT
 */
class SaleRepository_DataJpaTest
        extends _Repository_DataJpaTest<SaleRepository, Sale, SaleId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    SaleRepository_DataJpaTest() {
        super(SaleRepository.class, Sale.class, SaleId.class);
    }
}
