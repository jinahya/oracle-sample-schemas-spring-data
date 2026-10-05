package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Sale;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.SaleId;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.Sale_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Sale}, the {@code SALES} table of the Sales History schema.
 *
 * @see Sale
 * @see Sale_
 */
@Repository
public interface SaleRepository
        extends JpaRepository<Sale, SaleId>,
                JpaSpecificationExecutor<Sale> {

}
