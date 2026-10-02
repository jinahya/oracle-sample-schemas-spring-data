package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Shipment;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Shipment_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Shipment}, the {@code SHIPMENTS} table of the Customer Orders schema.
 *
 * @see Shipment
 * @see Shipment_
 */
@Repository
public interface ShipmentRepository
        extends JpaRepository<Shipment, Long>,
                JpaSpecificationExecutor<Shipment> {

}
