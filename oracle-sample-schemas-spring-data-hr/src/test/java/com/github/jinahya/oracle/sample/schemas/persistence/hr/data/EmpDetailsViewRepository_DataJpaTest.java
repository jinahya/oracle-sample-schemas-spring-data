package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.EmpDetailsView;

/**
 * Tests {@link EmpDetailsViewRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see EmpDetailsViewRepository_SpringBootIT
 */
class EmpDetailsViewRepository_DataJpaTest
        extends _Repository_DataJpaTest<EmpDetailsViewRepository, EmpDetailsView, Integer> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    EmpDetailsViewRepository_DataJpaTest() {
        super(EmpDetailsViewRepository.class, EmpDetailsView.class, Integer.class);
    }
}
