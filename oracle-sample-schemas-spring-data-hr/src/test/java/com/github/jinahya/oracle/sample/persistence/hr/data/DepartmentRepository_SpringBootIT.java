package com.github.jinahya.oracle.sample.persistence.hr.data;

import com.github.jinahya.oracle.sample.persistence.hr.Department;

/**
 * Tests {@link DepartmentRepository} against the installed HR schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 *
 * @see DepartmentRepository_DataJpaTest
 */
class DepartmentRepository_SpringBootIT
        extends _Repository_SpringBootIT<DepartmentRepository, Department, Integer> {

    /**
     * Creates a new instance.
     */
    DepartmentRepository_SpringBootIT() {
        super(DepartmentRepository.class, Department.class, Integer.class);
    }
}
