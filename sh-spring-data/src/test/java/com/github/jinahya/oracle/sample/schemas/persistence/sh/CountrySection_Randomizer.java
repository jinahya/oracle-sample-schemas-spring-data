package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class CountrySection_Randomizer
        extends __Randomizer.___OfInstancio<CountrySection> {

    CountrySection_Randomizer() {
        super(CountrySection.class, List.of(

        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<CountrySection> getInstancio(final CountrySection instance) {
        return super.getInstancio(instance);
    }

    @Override
    public CountrySection get() {
        return super.get();
    }
}
