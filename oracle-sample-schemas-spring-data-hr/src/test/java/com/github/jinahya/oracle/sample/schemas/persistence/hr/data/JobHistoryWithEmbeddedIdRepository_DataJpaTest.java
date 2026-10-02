package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryWithEmbeddedId;

/**
 * Tests {@link JobHistoryWithEmbeddedIdRepository} in a JPA slice, over an embedded H2, through
 * {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see JobHistoryWithEmbeddedIdRepository_SpringBootIT
 */
class JobHistoryWithEmbeddedIdRepository_DataJpaTest
        extends _Repository_DataJpaTest<JobHistoryWithEmbeddedIdRepository, JobHistoryWithEmbeddedId, JobHistoryId> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    JobHistoryWithEmbeddedIdRepository_DataJpaTest() {
        super(JobHistoryWithEmbeddedIdRepository.class, JobHistoryWithEmbeddedId.class, JobHistoryId.class);
    }
}
