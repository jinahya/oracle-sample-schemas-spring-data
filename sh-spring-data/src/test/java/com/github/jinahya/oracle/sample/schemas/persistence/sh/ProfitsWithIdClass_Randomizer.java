package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class ProfitsWithIdClass_Randomizer
        extends __Randomizer.___OfInstancio<ProfitsWithIdClass> {

    ProfitsWithIdClass_Randomizer() {
        super(ProfitsWithIdClass.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<ProfitsWithIdClass> getInstancio(final ProfitsWithIdClass instance) {
        return super.getInstancio(instance);
    }

    @Override
    public ProfitsWithIdClass get() {
        return super.get();
    }
}
