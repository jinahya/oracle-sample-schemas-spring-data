package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Customer;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Customer_;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests {@link CustomerRepository} in a JPA slice.
 * <p>
 * A bare {@code @DataJpaTest} implies {@code @AutoConfigureTestDatabase}, which replaces the Oracle datasource of
 * {@code application.yaml} with an embedded H2. The JPA settings of that file still apply, so the H2 would get
 * {@code ddl-auto: none}, {@code default_schema: CO} and {@code OracleDialect}, and hold no {@code CO} schema.
 * {@link _Repository_DataJpaTest_Configuration} overrides those three, so the tests here run against a schema generated
 * from the entities. It also turns off the {@code show_sql} that {@code @DataJpaTest} turns on, so each statement is
 * logged once, by the {@code org.hibernate.SQL} logger.
 * <p>
 * {@code @DataJpaTest} is {@code @Transactional}, so each test runs in one transaction, and one persistence context,
 * which is rolled back when it ends. Nothing a test writes survives it.
 *
 * @see CustomerRepository_2DataJpaTest
 * @see CustomerRepository_SpringBootIT
 */
@Import(_Repository_DataJpaTest_Configuration.class)
@DataJpaTest
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class CustomerRepository_DataJpaTest {

    /**
     * Tests {@link CustomerRepository#findById(Object)}.
     */
    @Nested
    class FindById_Test {

        /**
         * Asserts that finding by a random id returns an empty optional.
         * <p>
         * The id is drawn from the whole {@code long} range, so it is all but certain to miss the small identity values
         * the H2 hands out, including those of customers that a non-transactional test has committed.
         */
        @Test
        void _Empty_Unknown() {
            final var id = ThreadLocalRandom.current().nextLong();
            final var found = customerRepository.findById(id);
            assertThat(found).isEmpty();
        }

        /**
         * Persists a randomized customer, and asserts that finding it by its id returns it.
         * <p>
         * {@code findById} is {@code EntityManager.find}, which answers from the test's persistence context, where the
         * customer is already managed, without going to the database.
         */
        @Test
        void _NotEmpty_Known() {
            final var persisted = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Customer.class);
            final var found = customerRepository.findById(persisted.getCustomerId());
            assertThat(found).hasValue(persisted);
        }
    }

    /**
     * Tests {@link CustomerRepository#selectOneByEmailAddress(String)}.
     */
    @Nested
    class SelectOneByEmailAddress_Test {

        /**
         * Asserts that selecting by an email address no customer has returns an empty optional.
         */
        @Test
        void _Empty_Unknown() {
            final var selected = customerRepository.selectOneByEmailAddress("unknown@unknown.com");
            assertThat(selected).isEmpty();
        }

        /**
         * Persists a randomized customer, and asserts that selecting it by its email address, through the
         * {@code Customer.selectOneByEmailAddress} named query, returns that very instance.
         * <p>
         * The query does go to the database, and the row it reads resolves to the instance already managed in the
         * test's persistence context; hence {@code isSameAs}.
         * <p>
         * This is also the test that catches a lost {@code @Param}. The context starts either way; only a call that
         * binds the argument fails.
         */
        @Test
        void _NotEmpty_Known() {
            final var persisted = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Customer.class);
            final var selected = customerRepository.selectOneByEmailAddress(persisted.getEmailAddress());
            assertThat(selected).hasValueSatisfying(
                    v -> assertThat(v)
                            .isSameAs(persisted)
            );
        }
    }

    /**
     * Tests {@link CustomerRepository#findByEmailAddress(String)}.
     */
    @Nested
    class FindByEmailAddress_Test {

        /**
         * Asserts that finding by an email address no customer has returns an empty optional.
         */
        @Test
        void _Empty_Unknown() {
            final var found = customerRepository.findByEmailAddress("unknown@unknown.com");
            assertThat(found).isEmpty();
        }

        /**
         * Persists a randomized customer, and asserts that finding it by its email address returns that very instance.
         * <p>
         * The query does go to the database, but the test's transaction holds one persistence context, and a row whose
         * id is already managed there resolves to the managed instance; hence {@code isSameAs}, not just
         * {@code isEqualTo}.
         */
        @Test
        void _NotEmpty_Known() {
            final var persisted = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Customer.class);
            final var found = customerRepository.findByEmailAddress(persisted.getEmailAddress());
            assertThat(found).hasValueSatisfying(
                    v -> assertThat(v)
                            .isSameAs(persisted)
            );
        }

        /**
         * Runs the same round trip with the test's transaction suspended, and asserts what that changes.
         * <p>
         * Writing through the injected {@link EntityManager} now fails with {@link TransactionRequiredException}, for
         * there is no transaction for it to join. The repository still works, because each of its calls opens and
         * commits a transaction of its own, but each in a persistence context of its own too. So the customer found is
         * a different instance, equal to the saved one only by its email address. It is also committed, and outlives
         * this test in the H2 of the cached context.
         */
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

        /**
         * Runs the same lookup as a {@link Specification}, and asserts that it finds that very instance too.
         * <p>
         * A query method is not the only way to a query: {@link CustomerRepository} extends
         * {@link org.springframework.data.jpa.repository.JpaSpecificationExecutor}, so a criteria predicate built by
         * the caller works whatever methods the repository declares. The attribute is named through the generated
         * {@link Customer_#emailAddress} metamodel, not by string.
         */
        @Test
        void __CriteriaApi() {
            final var persisted = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Customer.class);
            final Specification<Customer> specification =
                    (r, q, b) -> b.equal(
                            r.get(Customer_.emailAddress),
                            persisted.getEmailAddress()
                    );
            final var found = customerRepository.findOne(specification);
            assertThat(found).hasValueSatisfying(
                    v -> assertThat(v)
                            .isSameAs(persisted)
            );
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;
}