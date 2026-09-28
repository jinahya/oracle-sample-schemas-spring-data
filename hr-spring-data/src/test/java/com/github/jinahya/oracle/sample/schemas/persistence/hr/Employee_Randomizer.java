package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Employee_Randomizer
        extends __Randomizer.___OfInstancio<Employee> {

    Employee_Randomizer() {
        super(Employee.class, List.of(
                Employee.ATTRIBUTE_NAME_JOB,
                Employee.ATTRIBUTE_NAME_MANAGER,
                Employee.ATTRIBUTE_NAME_DEPARTMENT,
                Employee.ATTRIBUTE_NAME_SUBORDINATES,
                Employee.ATTRIBUTE_NAME_MANAGED_DEPARTMENTS,
                Employee.ATTRIBUTE_NAME_JOB_HISTORIES
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Employee> getInstancio(final Employee instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Employee get() {
        return super.get();
    }
}
