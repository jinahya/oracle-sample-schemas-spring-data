package com.github.jinahya.oracle.sample.schemas.persistence.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Shipment;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Shipment_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Shipment}, the {@code SHIPMENTS} table of the Customer Orders schema.
 *
 * @see Shipment
 * @see Shipment_
 */
@Repository
public interface ShipmentRepository
        extends _Repository<Shipment, Long> {

}
