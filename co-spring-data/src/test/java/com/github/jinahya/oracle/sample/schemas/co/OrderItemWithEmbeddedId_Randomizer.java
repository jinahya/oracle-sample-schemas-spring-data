package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class OrderItemWithEmbeddedId_Randomizer
        extends __Randomizer.___OfInstancio<OrderItemWithEmbeddedId> {

    OrderItemWithEmbeddedId_Randomizer() {
        super(OrderItemWithEmbeddedId.class, List.of(
                OrderItemWithEmbeddedId.ATTRIBUTE_NAME_ID,
                OrderItemWithEmbeddedId.ATTRIBUTE_NAME_ORDER,
                OrderItemWithEmbeddedId.ATTRIBUTE_NAME_PRODUCT,
                OrderItemWithEmbeddedId.ATTRIBUTE_NAME_SHIPMENT
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<OrderItemWithEmbeddedId> getInstancio(final OrderItemWithEmbeddedId instance) {
        return super.getInstancio(instance);
    }

    @Override
    public OrderItemWithEmbeddedId get() {
        return super.get();
    }
}
