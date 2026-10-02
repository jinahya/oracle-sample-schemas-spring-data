package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryWithEmbeddedId;

/**
 * Tests {@link JobHistoryWithEmbeddedIdRepository} against the installed HR schema, through
 * {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the HR schema installed; see that file.
 *
 * @see JobHistoryWithEmbeddedIdRepository_DataJpaTest
 */
class JobHistoryWithEmbeddedIdRepository_SpringBootIT
        extends _Repository_SpringBootIT<JobHistoryWithEmbeddedIdRepository, JobHistoryWithEmbeddedId, JobHistoryId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    JobHistoryWithEmbeddedIdRepository_SpringBootIT() {
        super(JobHistoryWithEmbeddedIdRepository.class, JobHistoryWithEmbeddedId.class, JobHistoryId.class);
    }
}
