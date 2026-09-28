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
 * Tests {@link SpringTestContext}, which is all this module has to test until it declares repositories.
 * <p>
 * Booting at all is the assertion that matters: it proves {@code application.yaml} reaches the installed SH schema as
 * {@code dmlonly}, that {@code hibernate.default_schema} qualifies the entities' unqualified table names, and that the
 * {@code @EntityScan} finds them one package up in the upstream jar.
 *
 * @see SpringTestContext
 */
@SpringBootTest
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class SpringTestContext_SpringBootTest {

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
