package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class JobHistoryWithEmbeddedId_Randomizer
        extends __Randomizer.___OfInstancio<JobHistoryWithEmbeddedId> {

    JobHistoryWithEmbeddedId_Randomizer() {
        super(JobHistoryWithEmbeddedId.class, List.of(
                JobHistoryWithEmbeddedId.ATTRIBUTE_NAME_EMPLOYEE,
                JobHistoryWithEmbeddedId.ATTRIBUTE_NAME_JOB,
                JobHistoryWithEmbeddedId.ATTRIBUTE_NAME_DEPARTMENT
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<JobHistoryWithEmbeddedId> getInstancio(final JobHistoryWithEmbeddedId instance) {
        return super.getInstancio(instance);
    }

    @Override
    public JobHistoryWithEmbeddedId get() {
        return super.get();
    }
}
