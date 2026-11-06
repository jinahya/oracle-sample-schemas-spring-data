package com.github.jinahya.oracle.sample.persistence.hr.data;

import com.github.jinahya.oracle.sample.persistence.hr.JobHistory;
import com.github.jinahya.oracle.sample.persistence.hr.JobHistory_;
import com.github.jinahya.oracle.sample.persistence.hr.mapped.JobHistoryId;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link JobHistory}, the {@code JOB_HISTORY} table of the Human Resources schema.
 *
 * @see JobHistory
 * @see JobHistory_
 */
@Repository
public interface JobHistoryRepository
        extends _Repository<JobHistory, JobHistoryId> {

}
