package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Inventory;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Inventory_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Inventory}, the {@code INVENTORY} table of the Customer Orders schema.
 *
 * @see Inventory
 * @see Inventory_
 */
@Repository
public interface InventoryRepository
        extends JpaRepository<Inventory, Long>,
                JpaSpecificationExecutor<Inventory> {

}
