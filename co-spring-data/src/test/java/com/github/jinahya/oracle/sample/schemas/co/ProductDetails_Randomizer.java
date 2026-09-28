package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class ProductDetails_Randomizer
        extends __Randomizer.___OfInstancio<ProductDetails> {

    ProductDetails_Randomizer() {
        super(ProductDetails.class, List.of());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<ProductDetails> getInstancio(final ProductDetails instance) {
        return super.getInstancio(instance);
    }

    @Override
    public ProductDetails get() {
        return super.get();
    }
}
