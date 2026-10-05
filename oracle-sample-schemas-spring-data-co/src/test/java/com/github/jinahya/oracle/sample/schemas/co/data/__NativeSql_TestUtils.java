package com.github.jinahya.oracle.sample.schemas.co.data;

import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.ThreadLocalRandom;

final class __NativeSql_TestUtils {

    private static String countStatement(final String tableName) {
        return """
                SELECT COUNT(*)
                FROM %s""".formatted(Objects.requireNonNull(tableName, "tableName is null"));
    }

    private static OptionalLong randomIndex(final @PositiveOrZero long count) {
        if (count == 0L) {
            return OptionalLong.empty();
        }
        return OptionalLong.of(ThreadLocalRandom.current().nextLong(count));
    }

    // --------------------------------------------------------------------------------------------------- EntityManager

    public static long count(final EntityManager entityManager, final String tableName) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        return (Long) entityManager
                .createNativeQuery(countStatement(tableName), Long.class)
                .getSingleResult();
    }

    public static OptionalLong randomIndex(final EntityManager entityManager, final String tableName) {
        return randomIndex(count(entityManager, tableName));
    }

    // Any row will do, so the statement has no ORDER BY; an offset past the end, after rows went away since the count,
    // finds none. Hibernate maps the row into targetClass as an entity, or, for any other class, through its one
    // constructor with a parameter per column, by position, with the column types as they come: no conversion.
    public static <T> Optional<T> randomMapped(final EntityManager entityManager, final String tableName,
                                               final Class<T> targetClass) {
        Objects.requireNonNull(targetClass, "targetClass is null");
        final var index = randomIndex(entityManager, tableName);
        if (index.isEmpty()) {
            return Optional.empty();
        }
        @SuppressWarnings({"unchecked"})
        final var list = (List<T>) entityManager
                .createNativeQuery("""
                        SELECT *
                        FROM %s
                        OFFSET ?1 ROWS FETCH NEXT 1 ROWS ONLY""".formatted(tableName), targetClass)
                .setParameter(1, index.getAsLong())
                .getResultList();
        return list.stream().findFirst();
    }

    // ------------------------------------------------------------------------------------------------------ JdbcClient

    public static long count(final JdbcClient jdbcClient, final String tableName) {
        Objects.requireNonNull(jdbcClient, "jdbcClient is null");
        return jdbcClient
                .sql(countStatement(tableName))
                .query(Long.class)
                .single();
    }

    public static OptionalLong randomIndex(final JdbcClient jdbcClient, final String tableName) {
        return randomIndex(count(jdbcClient, tableName));
    }

    // Any row will do, so the statement has no ORDER BY; an offset past the end, after rows went away since the count,
    // finds none. JdbcClient maps the row into targetClass by column name, through its constructor or its setters.
    public static <T> Optional<T> randomMapped(final JdbcClient jdbcClient, final String tableName,
                                               final Class<T> targetClass) {
        Objects.requireNonNull(targetClass, "targetClass is null");
        final var index = randomIndex(jdbcClient, tableName);
        if (index.isEmpty()) {
            return Optional.empty();
        }
        return jdbcClient
                .sql("""
                        SELECT *
                        FROM %s
                        OFFSET :offset ROWS FETCH NEXT 1 ROWS ONLY""".formatted(tableName))
                .param("offset", index.getAsLong())
                .query(targetClass)
                .optional();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private __NativeSql_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
