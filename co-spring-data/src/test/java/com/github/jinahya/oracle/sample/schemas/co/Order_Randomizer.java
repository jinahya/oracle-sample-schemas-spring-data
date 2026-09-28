package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Order_Randomizer
        extends __Randomizer.___OfInstancio<Order> {

    Order_Randomizer() {
        super(Order.class, List.of(
                Order.ATTRIBUTE_NAME_ORDER_ID,
                Order.ATTRIBUTE_NAME_CUSTOMER,
                Order.ATTRIBUTE_NAME_STORE,
                Order.ATTRIBUTE_NAME_ORDER_ITEMS
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Order> getInstancio(final Order instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Order get() {
        return super.get();
    }
}
