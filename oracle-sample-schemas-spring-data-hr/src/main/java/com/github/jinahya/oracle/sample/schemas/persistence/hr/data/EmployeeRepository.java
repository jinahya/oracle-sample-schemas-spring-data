package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Employee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.Employee_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Employee}, the {@code EMPLOYEES} table of the Human Resources schema.
 *
 * @see Employee
 * @see Employee_
 */
@Repository
public interface EmployeeRepository
        extends _Repository<Employee, Integer> {

}
