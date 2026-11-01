package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Job;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.Job_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Job}, the {@code JOBS} table of the Human Resources schema.
 *
 * @see Job
 * @see Job_
 */
@Repository
public interface JobRepository
        extends _Repository<Job, String> {

}
