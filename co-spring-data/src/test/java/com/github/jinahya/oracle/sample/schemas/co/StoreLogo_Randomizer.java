package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class StoreLogo_Randomizer
        extends __Randomizer.___OfInstancio<StoreLogo> {

    StoreLogo_Randomizer() {
        super(StoreLogo.class, List.of());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<StoreLogo> getInstancio(final StoreLogo instance) {
        return super.getInstancio(instance);
    }

    @Override
    public StoreLogo get() {
        return super.get();
    }
}
