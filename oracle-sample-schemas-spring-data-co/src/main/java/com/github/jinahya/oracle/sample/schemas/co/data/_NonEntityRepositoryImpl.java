package com.github.jinahya.oracle.sample.schemas.co.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;
import java.util.Objects;

public abstract class _NonEntityRepositoryImpl<T> {

    public _NonEntityRepositoryImpl(final Class<T> targetClass) {
        this.targetClass = Objects.requireNonNull(targetClass, "targetClass is null");
    }

    /**
     * Returns the {@link JdbcTemplate}.
     *
     * @return the {@link JdbcTemplate}.
     * @deprecated Use {@link #jdbcClient()}, which covers every read of a view; the batch, callback and
     *             stored-procedure operations only this has do not apply to read-only views.
     */
    @Deprecated(forRemoval = true)
    protected JdbcTemplate jdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * Returns the {@link NamedParameterJdbcTemplate}.
     *
     * @return the {@link NamedParameterJdbcTemplate}.
     * @deprecated Use {@link #jdbcClient()}, which binds named parameters itself.
     */
    @Deprecated(forRemoval = true)
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<T> targetClass;

    @Autowired
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    private JdbcClient jdbcClient;

    /**
     * @deprecated See {@link #jdbcTemplate()}.
     */
    @Deprecated(forRemoval = true)
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @deprecated See {@link #namedParameterJdbcTemplate()}.
     */
    @Deprecated(forRemoval = true)
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // the shared, transaction-bound proxy; @Autowired would see two EntityManager beans
    @PersistenceContext
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    private EntityManager entityManager;

    @Autowired
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    private DataSource dataSource;
}
