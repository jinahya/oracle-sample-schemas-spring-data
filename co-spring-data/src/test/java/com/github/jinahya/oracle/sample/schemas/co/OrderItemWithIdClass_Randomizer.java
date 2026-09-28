package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class OrderItemWithIdClass_Randomizer
        extends __Randomizer.___OfInstancio<OrderItemWithIdClass> {

    OrderItemWithIdClass_Randomizer() {
        super(OrderItemWithIdClass.class, List.of(
                OrderItemWithIdClass.ATTRIBUTE_NAME_ORDER_ID,
                OrderItemWithIdClass.ATTRIBUTE_NAME_ORDER,
                OrderItemWithIdClass.ATTRIBUTE_NAME_LINE_ITEM_ID,
                OrderItemWithIdClass.ATTRIBUTE_NAME_PRODUCT,
                OrderItemWithIdClass.ATTRIBUTE_NAME_SHIPMENT
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<OrderItemWithIdClass> getInstancio(final OrderItemWithIdClass instance) {
        return super.getInstancio(instance);
    }

    @Override
    public OrderItemWithIdClass get() {
        return super.get();
    }
}
