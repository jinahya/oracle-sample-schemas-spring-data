package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class ProfitsId_Randomizer
        extends __Randomizer.___OfInstancio<ProfitsId> {

    ProfitsId_Randomizer() {
        super(ProfitsId.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<ProfitsId> getInstancio(final ProfitsId instance) {
        return super.getInstancio(instance);
    }

    @Override
    public ProfitsId get() {
        return super.get();
    }
}
