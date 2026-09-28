package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Region_Randomizer
        extends __Randomizer.___OfInstancio<Region> {

    Region_Randomizer() {
        super(Region.class, List.of(
                Region.ATTRIBUTE_NAME_COUNTRIES
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Region> getInstancio(final Region instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Region get() {
        return super.get();
    }
}
