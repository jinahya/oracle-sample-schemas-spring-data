package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.Department;

/**
 * Tests {@link DepartmentRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see DepartmentRepository_SpringBootIT
 */
class DepartmentRepository_DataJpaTest
        extends _Repository_DataJpaTest<DepartmentRepository, Department, Integer> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    DepartmentRepository_DataJpaTest() {
        super(DepartmentRepository.class, Department.class, Integer.class);
    }
}
