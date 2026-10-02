package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryWithEmbeddedId;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryWithEmbeddedId_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link JobHistoryWithEmbeddedId}, the {@code JOB_HISTORY} table of the Human Resources schema.
 * <p>
 * The table is mapped twice upstream; this is the {@code @EmbeddedId} flavour. The {@code @IdClass} one,
 * {@code JobHistoryWithIdClass}, has no repository here, as the test context leaves it out of the persistence unit.
 *
 * @see JobHistoryWithEmbeddedId
 * @see JobHistoryWithEmbeddedId_
 */
@Repository
public interface JobHistoryWithEmbeddedIdRepository
        extends JpaRepository<JobHistoryWithEmbeddedId, JobHistoryId>,
                JpaSpecificationExecutor<JobHistoryWithEmbeddedId> {

}
