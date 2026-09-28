package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class CostId_Randomizer
        extends __Randomizer.___OfInstancio<CostId> {

    CostId_Randomizer() {
        super(CostId.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<CostId> getInstancio(final CostId instance) {
        return super.getInstancio(instance);
    }

    @Override
    public CostId get() {
        return super.get();
    }
}
