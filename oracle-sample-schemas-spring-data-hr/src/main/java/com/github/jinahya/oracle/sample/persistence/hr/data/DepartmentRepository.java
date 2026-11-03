package com.github.jinahya.oracle.sample.persistence.hr.data;

import com.github.jinahya.oracle.sample.persistence.hr.Department;
import com.github.jinahya.oracle.sample.persistence.hr.Department_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Department}, the {@code DEPARTMENTS} table of the Human Resources schema.
 *
 * @see Department
 * @see Department_
 */
@Repository
public interface DepartmentRepository
        extends _Repository<Department, Integer> {

}
