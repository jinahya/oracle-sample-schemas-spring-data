package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Channel_Randomizer
        extends __Randomizer.___OfInstancio<Channel> {

    Channel_Randomizer() {
        super(Channel.class, List.of(

        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Channel> getInstancio(final Channel instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Channel get() {
        return super.get();
    }
}
