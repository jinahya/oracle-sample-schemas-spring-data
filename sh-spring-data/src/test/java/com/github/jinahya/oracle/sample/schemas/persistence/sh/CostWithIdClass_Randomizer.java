package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class CostWithIdClass_Randomizer
        extends __Randomizer.___OfInstancio<CostWithIdClass> {

    CostWithIdClass_Randomizer() {
        super(CostWithIdClass.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<CostWithIdClass> getInstancio(final CostWithIdClass instance) {
        return super.getInstancio(instance);
    }

    @Override
    public CostWithIdClass get() {
        return super.get();
    }
}
