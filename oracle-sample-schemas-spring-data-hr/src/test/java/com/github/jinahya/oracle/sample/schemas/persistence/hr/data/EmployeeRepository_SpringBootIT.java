package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Employee;

/**
 * Tests {@link EmployeeRepository} against the installed HR schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the HR schema installed; see that file.
 *
 * @see EmployeeRepository_DataJpaTest
 */
class EmployeeRepository_SpringBootIT
        extends _Repository_SpringBootIT<EmployeeRepository, Employee, Integer> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    EmployeeRepository_SpringBootIT() {
        super(EmployeeRepository.class, Employee.class, Integer.class);
    }
}
