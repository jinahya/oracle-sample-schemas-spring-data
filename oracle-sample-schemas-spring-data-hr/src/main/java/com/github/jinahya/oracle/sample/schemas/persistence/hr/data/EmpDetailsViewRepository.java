package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.EmpDetailsView;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.EmpDetailsView_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link EmpDetailsView}, the {@code EMP_DETAILS_VIEW} view of the Human Resources schema.
 * <p>
 * {@code EMP_DETAILS_VIEW} is a view, not a table; the {@code save} and {@code delete} methods inherited here have
 * nothing to write to.
 *
 * @see EmpDetailsView
 * @see EmpDetailsView_
 */
@Repository
public interface EmpDetailsViewRepository
        extends JpaRepository<EmpDetailsView, Integer>,
                JpaSpecificationExecutor<EmpDetailsView> {

}
