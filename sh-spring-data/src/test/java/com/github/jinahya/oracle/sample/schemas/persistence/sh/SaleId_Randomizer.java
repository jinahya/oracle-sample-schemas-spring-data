package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class SaleId_Randomizer
        extends __Randomizer.___OfInstancio<SaleId> {

    SaleId_Randomizer() {
        super(SaleId.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<SaleId> getInstancio(final SaleId instance) {
        return super.getInstancio(instance);
    }

    @Override
    public SaleId get() {
        return super.get();
    }
}
