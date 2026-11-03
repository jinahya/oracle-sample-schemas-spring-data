/**
 * Spring Data repositories for the entities of the Customer Orders ({@code CO}) schema, which live one package up,
 * in {@code com.github.jinahya.oracle.sample.persistence.co}: this module's own entities, each extending a mapped superclass of the upstream
 * {@code oracle-sample-schemas-persistence-co} artifact.
 * <p>
 * The package is {@link NullMarked}: every parameter and return type here is non-null unless marked {@code @Nullable}.
 * Spring Data reads that from the repository interfaces, and rejects a {@code null} argument to a query method with an
 * {@link IllegalArgumentException} before any query runs. A lookup that may find nothing returns an
 * {@link java.util.Optional} instead of a nullable entity.
 * <p>
 * Every repository here is a subinterface of {@link com.github.jinahya.oracle.sample.persistence.co.data._Repository}, one per entity, the views of the schema that
 * are entities included; Spring Data JPA implements them.
 */
@NullMarked
package com.github.jinahya.oracle.sample.persistence.co.data;

import org.jspecify.annotations.NullMarked;
