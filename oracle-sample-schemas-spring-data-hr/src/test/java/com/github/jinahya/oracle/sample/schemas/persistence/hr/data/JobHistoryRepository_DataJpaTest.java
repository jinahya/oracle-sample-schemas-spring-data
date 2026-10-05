package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistory;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;

/**
 * Tests {@link JobHistoryRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see JobHistoryRepository_SpringBootIT
 */
class JobHistoryRepository_DataJpaTest
        extends _Repository_DataJpaTest<JobHistoryRepository, JobHistory, JobHistoryId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    JobHistoryRepository_DataJpaTest() {
        super(JobHistoryRepository.class, JobHistory.class, JobHistoryId.class);
    }
}
