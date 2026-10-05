package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistory;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;

/**
 * Tests {@link JobHistoryRepository} against the installed HR schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the HR schema installed; see that file.
 *
 * @see JobHistoryRepository_DataJpaTest
 */
class JobHistoryRepository_SpringBootIT
        extends _Repository_SpringBootIT<JobHistoryRepository, JobHistory, JobHistoryId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    JobHistoryRepository_SpringBootIT() {
        super(JobHistoryRepository.class, JobHistory.class, JobHistoryId.class);
    }
}
