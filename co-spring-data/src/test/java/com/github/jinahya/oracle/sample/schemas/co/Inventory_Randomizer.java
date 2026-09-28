package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Inventory_Randomizer
        extends __Randomizer.___OfInstancio<Inventory> {

    Inventory_Randomizer() {
        super(Inventory.class, List.of(
                Inventory.ATTRIBUTE_NAME_INVENTORY_ID,
                Inventory.ATTRIBUTE_NAME_STORE,
                Inventory.ATTRIBUTE_NAME_PRODUCT
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Inventory> getInstancio(final Inventory instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Inventory get() {
        return super.get();
    }
}
