package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Product_Randomizer
        extends __Randomizer.___OfInstancio<Product> {

    Product_Randomizer() {
        super(Product.class, List.of(

        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Product> getInstancio(final Product instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Product get() {
        return super.get();
    }
}
