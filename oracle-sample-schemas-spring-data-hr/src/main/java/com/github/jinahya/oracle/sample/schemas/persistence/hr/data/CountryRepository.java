package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Country;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.Country_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Country}, the {@code COUNTRIES} table of the Human Resources schema.
 *
 * @see Country
 * @see Country_
 */
@Repository
public interface CountryRepository
        extends _Repository<Country, String> {

}
