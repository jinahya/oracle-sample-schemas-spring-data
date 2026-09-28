package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Shipment_Randomizer
        extends __Randomizer.___OfInstancio<Shipment> {

    Shipment_Randomizer() {
        super(Shipment.class, List.of(
                Shipment.ATTRIBUTE_NAME_SHIPMENT_ID,
                Shipment.ATTRIBUTE_NAME_STORE,
                Shipment.ATTRIBUTE_NAME_CUSTOMER,
                Shipment.ATTRIBUTE_NAME_ORDER_ITEMS
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Shipment> getInstancio(final Shipment instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Shipment get() {
        return super.get();
    }
}
