package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class ProfitsWithEmbeddedId_Randomizer
        extends __Randomizer.___OfInstancio<ProfitsWithEmbeddedId> {

    ProfitsWithEmbeddedId_Randomizer() {
        super(ProfitsWithEmbeddedId.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<ProfitsWithEmbeddedId> getInstancio(final ProfitsWithEmbeddedId instance) {
        return super.getInstancio(instance);
    }

    @Override
    public ProfitsWithEmbeddedId get() {
        return super.get();
    }
}
