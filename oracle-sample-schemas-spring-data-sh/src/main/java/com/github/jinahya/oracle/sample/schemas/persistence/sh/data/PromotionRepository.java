package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Promotion;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.Promotion_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Promotion}, the {@code PROMOTIONS} table of the Sales History schema.
 *
 * @see Promotion
 * @see Promotion_
 */
@Repository
public interface PromotionRepository
        extends JpaRepository<Promotion, Integer>,
                JpaSpecificationExecutor<Promotion> {

}
