package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class SupplementaryDemographics_Randomizer
        extends __Randomizer.___OfInstancio<SupplementaryDemographics> {

    SupplementaryDemographics_Randomizer() {
        super(SupplementaryDemographics.class, List.of(

        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<SupplementaryDemographics> getInstancio(final SupplementaryDemographics instance) {
        return super.getInstancio(instance);
    }

    @Override
    public SupplementaryDemographics get() {
        return super.get();
    }
}
