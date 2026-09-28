package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class CustomerOrderProducts_Randomizer
        extends __Randomizer.___OfInstancio<CustomerOrderProducts> {

    CustomerOrderProducts_Randomizer() {
        super(CustomerOrderProducts.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<CustomerOrderProducts> getInstancio(final CustomerOrderProducts instance) {
        return super.getInstancio(instance);
    }

    @Override
    public CustomerOrderProducts get() {
        return super.get();
    }
}
