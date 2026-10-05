package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import jakarta.persistence.EntityManagerFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests {@link ___Spring_TestContext}, which is all this module has to test until it declares repositories.
 * <p>
 * Booting proves that the {@code @EntityScan} finds the entities one package up in the upstream jar.
 * <p>
 * It does <em>not</em> prove that the database is reachable. {@code application.yaml} names the dialect, so Hibernate
 * only logs a failed metadata connection ({@code HHH000342}) and starts anyway. Nothing here runs a query, so this test
 * passes with no HR schema at all, and it does not exercise {@code hibernate.default_schema} either. The first
 * repository test that queries the schema will cover both.
 *
 * @see ___Spring_TestContext
 */
@SpringBootTest
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class SpringTestContext_SpringBootTest {

    /**
     * Tests the metamodel of the persistence unit that {@link ___Spring_TestContext} configures.
     */
    @Nested
    class Metamodel_Test {

        /**
         * Asserts that the persistence unit holds entities, among them the composite-key ones.
         * <p>
         * Upstream used to map JOB_HISTORY twice, and this module had to keep one flavour of each out of the unit.
         * Each is now mapped once, as {@code JobHistory}, so the scan takes everything. An empty metamodel would mean the
         * scan found nothing.
         */
        @Test
        void __() {
            final var managedTypes = entityManagerFactory.getMetamodel().getManagedTypes();
            assertThat(managedTypes)
                    .isNotEmpty()
                    .extracting(t -> t.getJavaType().getSimpleName())
                    .contains("JobHistory");
        }
    }

    @Autowired
    private EntityManagerFactory entityManagerFactory;
}
