package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Shipment;

/**
 * Tests {@link ShipmentRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see ShipmentRepository_SpringBootIT
 */
class ShipmentRepository_DataJpaTest
        extends _Repository_DataJpaTest<ShipmentRepository, Shipment, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ShipmentRepository_DataJpaTest() {
        super(ShipmentRepository.class, Shipment.class, Long.class);
    }
}
