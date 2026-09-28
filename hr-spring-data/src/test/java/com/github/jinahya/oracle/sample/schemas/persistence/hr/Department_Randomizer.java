package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class Department_Randomizer
        extends __Randomizer.___OfInstancio<Department> {

    Department_Randomizer() {
        super(Department.class, List.of(
                Department.ATTRIBUTE_NAME_MANAGER,
                Department.ATTRIBUTE_NAME_LOCATION,
                Department.ATTRIBUTE_NAME_EMPLOYEES,
                Department.ATTRIBUTE_NAME_JOB_HISTORIES
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Department> getInstancio(final Department instance) {
        return super.getInstancio(instance);
    }

    @Override
    public Department get() {
        return super.get();
    }
}
