package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class ProductImage_Randomizer
        extends __Randomizer.___OfInstancio<ProductImage> {

    ProductImage_Randomizer() {
        super(ProductImage.class, List.of());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<ProductImage> getInstancio(final ProductImage instance) {
        return super.getInstancio(instance);
    }

    @Override
    public ProductImage get() {
        return super.get();
    }
}
