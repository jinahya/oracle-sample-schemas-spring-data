package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

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
 * Booting proves that the {@code @EntityScan} finds the entities one package up in the upstream jar, and that the
 * {@code ManagedClassNameFilter} keeps the persistence unit free of duplicate entity names.
 * <p>
 * It does <em>not</em> prove that the database is reachable. {@code application.yaml} names the dialect, so Hibernate
 * only logs a failed metadata connection ({@code HHH000342}) and starts anyway. Nothing here runs a query, so this
 * test passes with no SH schema at all, and it does not exercise {@code hibernate.default_schema} either. The first
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
         * Asserts that the persistence unit holds entities, and only one flavour of each twice-mapped table.
         * <p>
         * SH maps COSTS, SALES, PROFITS and FWEEK_PSCAT_SALES_MV twice over, one {@code @EmbeddedId} flavour and one
         * {@code @IdClass} flavour sharing an entity name, which is a duplicate the unit cannot hold both of. An empty
         * metamodel would mean the scan found nothing; a {@code *WithIdClass} in it would mean the
         * {@code ManagedClassNameFilter} is not being applied.
         */
        @Test
        void __() {
            final var managedTypes = entityManagerFactory.getMetamodel().getManagedTypes();
            assertThat(managedTypes)
                    .isNotEmpty()
                    .noneMatch(t -> t.getJavaType().getSimpleName().endsWith("WithIdClass"));
        }
    }

    @Autowired
    private EntityManagerFactory entityManagerFactory;
}
