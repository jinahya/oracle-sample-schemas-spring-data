package com.github.jinahya.oracle.sample.persistence.sh.data;

import jakarta.persistence.EntityManagerFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests {@link ___Spring_TestContext}, in the full context.
 * <p>
 * Booting proves that the {@code @EntityScan} finds the entities in the parent package of this module.
 * <p>
 * It does <em>not</em> prove that the database is reachable. {@code application.yaml} names the dialect, so Hibernate
 * only logs a failed metadata connection ({@code HHH000342}) and starts anyway. Nothing here runs a query, so this test
 * passes with no SH schema at all, and it does not exercise {@code hibernate.default_schema} either. The first
 * repository test that queries the schema will cover both.
 *
 * @see ___Spring_TestContext
 * @see SpringTestContext_DataJpaTest
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
         * An empty metamodel would mean the scan found nothing; the composite-key entities are named so that a mapping
         * of any of them breaking the scan is noticed.
         */
        @Test
        void __() {
            final var managedTypes = entityManagerFactory.getMetamodel().getManagedTypes();
            assertThat(managedTypes)
                    .isNotEmpty()
                    .extracting(t -> t.getJavaType().getSimpleName())
                    .contains("Sales", "Cost", "Profit", "FweekPscatSalesMv");
        }
    }

    @Autowired
    private EntityManagerFactory entityManagerFactory;
}
