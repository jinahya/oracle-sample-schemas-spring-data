package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class CalMonthSalesMv_Randomizer
        extends __Randomizer.___OfInstancio<CalMonthSalesMv> {

    CalMonthSalesMv_Randomizer() {
        super(CalMonthSalesMv.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<CalMonthSalesMv> getInstancio(final CalMonthSalesMv instance) {
        return super.getInstancio(instance);
    }

    @Override
    public CalMonthSalesMv get() {
        return super.get();
    }
}
