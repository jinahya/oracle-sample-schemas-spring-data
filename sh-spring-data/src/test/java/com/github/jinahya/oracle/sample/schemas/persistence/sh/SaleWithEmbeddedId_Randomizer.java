package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class SaleWithEmbeddedId_Randomizer
        extends __Randomizer.___OfInstancio<SaleWithEmbeddedId> {

    SaleWithEmbeddedId_Randomizer() {
        super(SaleWithEmbeddedId.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<SaleWithEmbeddedId> getInstancio(final SaleWithEmbeddedId instance) {
        return super.getInstancio(instance);
    }

    @Override
    public SaleWithEmbeddedId get() {
        return super.get();
    }
}
