package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Customer_Randomizer
        extends __Randomizer.___OfInstancio<Customer> {

    Customer_Randomizer() {
        super(Customer.class, List.of(
                Customer.ATTRIBUTE_NAME_COUNTRY
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Customer> getInstancio(final Customer instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Customer get() {
        return super.get();
    }
}
