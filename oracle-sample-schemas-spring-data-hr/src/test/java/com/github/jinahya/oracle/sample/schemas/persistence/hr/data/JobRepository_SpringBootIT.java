package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Job;

/**
 * Tests {@link JobRepository} against the installed HR schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the HR schema installed; see that file.
 *
 * @see JobRepository_DataJpaTest
 */
class JobRepository_SpringBootIT
        extends _Repository_SpringBootIT<JobRepository, Job, String> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    JobRepository_SpringBootIT() {
        super(JobRepository.class, Job.class, String.class);
    }
}
