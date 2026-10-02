/**
 * Spring Data repositories for the entities of the Customer Orders ({@code CO}) schema, which live one package up, in
 * the upstream {@code co} artifact.
 * <p>
 * The package is {@link NullMarked}: every parameter and return type here is non-null unless marked {@code @Nullable}.
 * Spring Data reads that from the repository interfaces, and rejects a {@code null} argument to a query method with an
 * {@link IllegalArgumentException} before any query runs. A lookup that may find nothing returns an
 * {@link java.util.Optional} instead of a nullable entity.
 */
@NullMarked
package com.github.jinahya.oracle.sample.schemas.co.data;

import org.jspecify.annotations.NullMarked;
