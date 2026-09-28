package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;

class JobHistoryId_Randomizer
        extends __Randomizer.___OfInstancio<JobHistoryId> {

    JobHistoryId_Randomizer() {
        super(JobHistoryId.class, List.of(

        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<JobHistoryId> getInstancio(final JobHistoryId instance) {
        return super.getInstancio(instance);
    }

    @Override
    public JobHistoryId get() {
        return super.get();
    }
}
