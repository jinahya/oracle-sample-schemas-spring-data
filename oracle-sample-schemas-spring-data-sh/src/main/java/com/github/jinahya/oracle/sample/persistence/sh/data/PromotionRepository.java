package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.Promotion;
import com.github.jinahya.oracle.sample.persistence.sh.Promotion_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Promotion}, the {@code PROMOTIONS} table of the Sales History schema.
 *
 * @see Promotion
 * @see Promotion_
 */
@Repository
public interface PromotionRepository
        extends _Repository<Promotion, Integer> {

}
