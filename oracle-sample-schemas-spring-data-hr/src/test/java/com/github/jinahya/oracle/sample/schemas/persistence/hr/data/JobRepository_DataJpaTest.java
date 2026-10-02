package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Job;

/**
 * Tests {@link JobRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see JobRepository_SpringBootIT
 */
class JobRepository_DataJpaTest
        extends _Repository_DataJpaTest<JobRepository, Job, String> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    JobRepository_DataJpaTest() {
        super(JobRepository.class, Job.class, String.class);
    }
}
