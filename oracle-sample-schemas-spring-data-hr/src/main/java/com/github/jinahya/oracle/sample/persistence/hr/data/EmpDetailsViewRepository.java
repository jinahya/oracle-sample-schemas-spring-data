package com.github.jinahya.oracle.sample.persistence.hr.data;

import com.github.jinahya.oracle.sample.persistence.hr.EmpDetailsView;
import com.github.jinahya.oracle.sample.persistence.hr.EmpDetailsView_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link EmpDetailsView}, the {@code EMP_DETAILS_VIEW} view of the Human Resources schema.
 *
 * @see EmpDetailsView
 * @see EmpDetailsView_
 */
@Repository
public interface EmpDetailsViewRepository
        extends _Repository<EmpDetailsView, Integer> {

}
