package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.test.util.__Randomizer;
import org.instancio.InstancioObjectApi;
import org.instancio.settings.Settings;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Job_Randomizer
        extends __Randomizer.___OfInstancio<Job> {

    Job_Randomizer() {
        super(Job.class, List.of(
                Job.ATTRIBUTE_NAME_EMPLOYEES,
                Job.ATTRIBUTE_NAME_JOB_HISTORIES
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Settings getInstancioSettings() {
        return super.getInstancioSettings();
    }

    @Override
    protected InstancioObjectApi<Job> getInstancio(final Job instance) {
        return super.getInstancio(instance);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The two salaries are assigned here rather than left to PODAM, which draws them independently:
     *         {@link Job} asserts that both are positive and that the minimum does not exceed the maximum, so an
     *         independent pair fails validation roughly half of the time.
     */
    @Override
    public Job get() {
        final var instance = super.get();
        final var minSalary = ThreadLocalRandom.current().nextInt(1, Job.ATTRIBUTE_MAX_MIN_SALARY);
        instance.setMinSalary(minSalary);
        instance.setMaxSalary(ThreadLocalRandom.current().nextInt(minSalary, Job.ATTRIBUTE_MAX_MAX_SALARY));
        return instance;
    }
}
