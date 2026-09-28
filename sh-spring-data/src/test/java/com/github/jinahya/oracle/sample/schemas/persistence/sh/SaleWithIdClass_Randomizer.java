package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class SaleWithIdClass_Randomizer
        extends __Randomizer.___OfInstancio<SaleWithIdClass> {

    SaleWithIdClass_Randomizer() {
        super(SaleWithIdClass.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<SaleWithIdClass> getInstancio(final SaleWithIdClass instance) {
        return super.getInstancio(instance);
    }

    @Override
    public SaleWithIdClass get() {
        return super.get();
    }
}
