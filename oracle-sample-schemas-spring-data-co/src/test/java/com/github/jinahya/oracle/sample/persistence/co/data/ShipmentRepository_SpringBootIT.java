package com.github.jinahya.oracle.sample.persistence.co.data;

import com.github.jinahya.oracle.sample.persistence.co.Shipment;

/**
 * Tests {@link ShipmentRepository} against the installed CO schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 *
 * @see ShipmentRepository_DataJpaTest
 */
class ShipmentRepository_SpringBootIT
        extends _Repository_SpringBootIT<ShipmentRepository, Shipment, Long> {

    /**
     * Creates a new instance.
     */
    ShipmentRepository_SpringBootIT() {
        super(ShipmentRepository.class, Shipment.class, Long.class);
    }
}
