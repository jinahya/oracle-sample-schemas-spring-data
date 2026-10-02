package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Country;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.Country_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Country}, the {@code COUNTRIES} table of the Sales History schema.
 *
 * @see Country
 * @see Country_
 */
@Repository
public interface CountryRepository
        extends JpaRepository<Country, Long>,
                JpaSpecificationExecutor<Country> {

}
