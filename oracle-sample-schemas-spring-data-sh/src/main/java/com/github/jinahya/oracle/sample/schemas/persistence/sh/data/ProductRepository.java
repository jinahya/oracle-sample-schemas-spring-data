package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Product;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.Product_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Product}, the {@code PRODUCTS} table of the Sales History schema.
 *
 * @see Product
 * @see Product_
 */
@Repository
public interface ProductRepository
        extends JpaRepository<Product, Integer>,
                JpaSpecificationExecutor<Product> {

}
