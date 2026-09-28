package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class ProductOrdersId_Randomizer
        extends __Randomizer.___OfInstancio<ProductOrdersId> {

    ProductOrdersId_Randomizer() {
        super(ProductOrdersId.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<ProductOrdersId> getInstancio(final ProductOrdersId instance) {
        return super.getInstancio(instance);
    }

    @Override
    public ProductOrdersId get() {
        return super.get();
    }
}
