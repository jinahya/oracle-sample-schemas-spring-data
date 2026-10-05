package com.github.jinahya.oracle.sample.schemas.co.data;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

/**
 * The {@link StoreOrderRepository}, over a {@link JdbcClient}.
 * <p>
 * A {@code @Repository} bean of its own, not part of any Spring Data repository: register it by component scanning
 * this package, or by {@code @Import}ing this class. {@code @Repository} also has Spring translate its exceptions,
 * an {@link IllegalArgumentException} included, into {@code DataAccessException}s, once a
 * {@code PersistenceExceptionTranslationPostProcessor} is in the context, as Boot puts one.
 * <p>
 * {@code hibernate.default_schema} does not reach SQL written here, and the connection's user is not the schema's
 * owner, so name the view with its schema: {@code CO.STORE_ORDERS}. Inside a transaction this shares JPA's
 * connection but sees only what JPA has flushed.
 */
@Repository
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StoreOrderRepositoryImpl
        implements StoreOrderRepository {

    // -----------------------------------------------------------------------------------------------------------------
    private final JdbcClient jdbcClient;
}
