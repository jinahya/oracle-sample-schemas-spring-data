package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Location_Randomizer
        extends __Randomizer.___OfInstancio<Location> {

    Location_Randomizer() {
        super(Location.class, List.of(
                Location.ATTRIBUTE_NAME_COUNTRY,
                Location.ATTRIBUTE_NAME_DEPARTMENTS
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Location> getInstancio(final Location instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Location get() {
        return super.get();
    }
}
