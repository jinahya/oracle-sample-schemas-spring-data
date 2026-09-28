package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Customer_Randomizer
        extends __Randomizer.___OfInstancio<Customer> {

    Customer_Randomizer() {
        super(Customer.class, List.of(
                Customer.ATTRIBUTE_NAME_CUSTOMER_ID,
                Customer.ATTRIBUTE_NAME_ORDERS,
                Customer.ATTRIBUTE_NAME_SHIPMENTS
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Customer> getInstancio(final Customer instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Customer get() {
        final var value = super.get();
        // Instancio fills the attribute with an arbitrary string, which no @Email would accept; replace it with an
        // address which actually validates, and which is distinct enough to stand in for the business key that
        // Customer.equals(Object) compares.
        final var random = ThreadLocalRandom.current();
        value.setEmailAddress(
                "customer" + Long.toUnsignedString(random.nextLong(), Character.MAX_RADIX)
                + "@mail" + Long.toUnsignedString(random.nextLong(), Character.MAX_RADIX) + ".com"
        );
        return value;
    }
}
