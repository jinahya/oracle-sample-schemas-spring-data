package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Store_Randomizer
        extends __Randomizer.___OfInstancio<Store> {

    Store_Randomizer() {
        super(Store.class, List.of(
                Store.ATTRIBUTE_NAME_STORE_ID,
                Store.ATTRIBUTE_NAME_ORDERS,
                Store.ATTRIBUTE_NAME_SHIPMENTS,
                Store.ATTRIBUTE_NAME_INVENTORIES
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Store> getInstancio(final Store instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Store get() {
        return super.get();
    }
}
