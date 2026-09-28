package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Country_Randomizer
        extends __Randomizer.___OfInstancio<Country> {

    Country_Randomizer() {
        super(Country.class, List.of(

        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Country> getInstancio(final Country instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Country get() {
        return super.get();
    }
}
