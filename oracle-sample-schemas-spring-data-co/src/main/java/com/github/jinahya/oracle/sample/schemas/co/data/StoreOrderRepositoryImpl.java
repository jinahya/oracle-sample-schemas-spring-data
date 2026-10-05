package com.github.jinahya.oracle.sample.schemas.co.data;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;

/**
 * The implementation of the {@link StoreOrderRepository} fragment, over a {@link JdbcClient}.
 * <p>
 * Spring Data finds this class by its name, the fragment's plus {@code Impl}, and wires it into every repository
 * that extends the fragment; it needs no {@code @Repository}, component scan or {@code @Import}.
 * {@link JdbcClient} throws {@code DataAccessException}s itself.
 * <p>
 * {@code hibernate.default_schema} does not reach SQL written here, and the connection's user is not the schema's
 * owner, so name the view with its schema: {@code CO.STORE_ORDERS}. Inside a transaction this shares JPA's
 * connection but sees only what JPA has flushed.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class StoreOrderRepositoryImpl
        implements StoreOrderRepository {

    // -----------------------------------------------------------------------------------------------------------------
    private final JdbcClient jdbcClient;
}
