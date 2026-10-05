package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistory;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistory_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link JobHistory}, the {@code JOB_HISTORY} table of the Human Resources schema.
 *
 * @see JobHistory
 * @see JobHistory_
 */
@Repository
public interface JobHistoryRepository
        extends JpaRepository<JobHistory, JobHistoryId>,
                JpaSpecificationExecutor<JobHistory> {

}
