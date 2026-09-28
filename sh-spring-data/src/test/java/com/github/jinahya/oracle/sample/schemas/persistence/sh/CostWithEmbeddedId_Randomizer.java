package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class CostWithEmbeddedId_Randomizer
        extends __Randomizer.___OfInstancio<CostWithEmbeddedId> {

    CostWithEmbeddedId_Randomizer() {
        super(CostWithEmbeddedId.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<CostWithEmbeddedId> getInstancio(final CostWithEmbeddedId instance) {
        return super.getInstancio(instance);
    }

    @Override
    public CostWithEmbeddedId get() {
        return super.get();
    }
}
