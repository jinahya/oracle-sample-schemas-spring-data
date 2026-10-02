package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Employee;

/**
 * Tests {@link EmployeeRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see EmployeeRepository_SpringBootIT
 */
class EmployeeRepository_DataJpaTest
        extends _Repository_DataJpaTest<EmployeeRepository, Employee, Integer> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    EmployeeRepository_DataJpaTest() {
        super(EmployeeRepository.class, Employee.class, Integer.class);
    }
}
