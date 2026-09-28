package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Promotion_Randomizer
        extends __Randomizer.___OfInstancio<Promotion> {

    Promotion_Randomizer() {
        super(Promotion.class, List.of(

        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Promotion> getInstancio(final Promotion instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Promotion get() {
        return super.get();
    }
}
