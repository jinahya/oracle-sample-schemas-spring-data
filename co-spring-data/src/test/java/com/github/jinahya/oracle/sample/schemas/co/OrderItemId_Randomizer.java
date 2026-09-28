package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class OrderItemId_Randomizer
        extends __Randomizer.___OfInstancio<OrderItemId> {

    OrderItemId_Randomizer() {
        super(OrderItemId.class, List.of());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<OrderItemId> getInstancio(final OrderItemId instance) {
        return super.getInstancio(instance);
    }

    @Override
    public OrderItemId get() {
        return super.get();
    }
}
