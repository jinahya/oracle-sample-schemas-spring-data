package com.github.jinahya.oracle.sample.persistence.sh.data;

import com.github.jinahya.oracle.sample.persistence.sh.Country;
import com.github.jinahya.oracle.sample.persistence.sh.Country_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Country}, the {@code COUNTRIES} table of the Sales History schema.
 *
 * @see Country
 * @see Country_
 */
@Repository
public interface CountryRepository
        extends _Repository<Country, Long> {

}
