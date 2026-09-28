package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class JobHistoryWithIdClass_Randomizer
        extends __Randomizer.___OfInstancio<JobHistoryWithIdClass> {

    JobHistoryWithIdClass_Randomizer() {
        super(JobHistoryWithIdClass.class, List.of(
                JobHistoryWithIdClass.ATTRIBUTE_NAME_EMPLOYEE,
                JobHistoryWithIdClass.ATTRIBUTE_NAME_JOB,
                JobHistoryWithIdClass.ATTRIBUTE_NAME_DEPARTMENT
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<JobHistoryWithIdClass> getInstancio(final JobHistoryWithIdClass instance) {
        return super.getInstancio(instance);
    }

    @Override
    public JobHistoryWithIdClass get() {
        return super.get();
    }
}
