package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import com.github.jinahya.oracle.sample.schemas.co.Customer;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TransactionRequiredException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests {@link CustomerRepository} in a JPA slice.
 * <p>
 * A bare {@code @DataJpaTest} implies {@code @AutoConfigureTestDatabase}, which replaces the Oracle datasource of
 * {@code application.yaml} with an embedded H2. The JPA settings of that file still apply, so the H2 would get
 * {@code ddl-auto: none}, {@code default_schema: CO} and {@code OracleDialect}, and hold no {@code CO} schema.
 * {@link _DataJpaTest_Configuration} overrides those three, so the tests here run against a schema generated from the
 * entities.
 *
 * @see CustomerRepository_SpringBootIT
 */
@Import(_DataJpaTest_Configuration.class)
@DataJpaTest
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class CustomerRepository_DataJpaTest {

    /**
     * Tests {@link CustomerRepository#findByEmailAddress(String)}.
     */
    @Nested
    class FindByEmailAddress_Test {

        /**
         * Saves a randomized customer and finds it back by its email address.
         */
        @Test
        void __() {
            final var persisted = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Customer.class);
            final var found = customerRepository.findByEmailAddress(persisted.getEmailAddress());
            assertThat(found).hasValueSatisfying(
                    v -> assertThat(v)
                            .isSameAs(persisted)
            );
        }

        @Transactional(propagation = Propagation.NOT_SUPPORTED)
        @Test
        void __NonTransactional() {
            assertThatThrownBy(() -> {
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, Customer.class);
            }).isInstanceOf(TransactionRequiredException.class);
            final var randomized = ObjectRandomizerUtils.newRandomizedInstanceOf(Customer.class).orElseThrow();
            final var saved = customerRepository.save(randomized);
            final var found = customerRepository.findByEmailAddress(saved.getEmailAddress());
            assertThat(found).hasValueSatisfying(
                    v -> assertThat(v)
                            .isNotSameAs(saved)
                            .isEqualTo(saved)
            );
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;
}