package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Department;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.Department_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Department}, the {@code DEPARTMENTS} table of the Human Resources schema.
 *
 * @see Department
 * @see Department_
 */
@Repository
public interface DepartmentRepository
        extends JpaRepository<Department, Integer>,
                JpaSpecificationExecutor<Department> {

}
