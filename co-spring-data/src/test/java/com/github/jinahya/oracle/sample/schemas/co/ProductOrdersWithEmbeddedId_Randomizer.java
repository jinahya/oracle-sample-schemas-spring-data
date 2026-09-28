package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class ProductOrdersWithEmbeddedId_Randomizer
        extends __Randomizer.___OfInstancio<ProductOrdersWithEmbeddedId> {

    ProductOrdersWithEmbeddedId_Randomizer() {
        super(ProductOrdersWithEmbeddedId.class, List.of(

        ));
    }

    // ---------------------------------------------------------------------------------------------------------------- 
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<ProductOrdersWithEmbeddedId> getInstancio(final ProductOrdersWithEmbeddedId instance) {
        return super.getInstancio(instance);
    }

    @Override
    public ProductOrdersWithEmbeddedId get() {
        return super.get();
    }
}
