package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Time_Randomizer
        extends __Randomizer.___OfInstancio<Time> {

    Time_Randomizer() {
        super(Time.class, List.of(

        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Time> getInstancio(final Time instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Time get() {
        return super.get();
    }
}
