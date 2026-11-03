package com.github.jinahya.oracle.sample.persistence.co.data;

import com.github.jinahya.oracle.sample.persistence.co.Customer;
import com.github.jinahya.oracle.sample.persistence.co.Customer_;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests {@link CustomerRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. Each test saves the customers it needs through the fixtures, and the
 * slice rolls them back when it ends.
 *
 * @see CustomerRepository_SpringBootIT
 */
class CustomerRepository_DataJpaTest
        extends _Repository_DataJpaTest<CustomerRepository, Customer, Long> {

    /**
     * Creates a new instance.
     */
    CustomerRepository_DataJpaTest() {
        super(CustomerRepository.class, Customer.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link CustomerRepository#findById(Object)}.
     */
    @Nested
    class FindById_Test {

        /**
         * Asserts that finding by a random id returns an empty optional.
         * <p>
         * The id is drawn from the whole {@code long} range, so it is all but certain to miss the small identity values
         * the H2 hands out.
         */
        @Test
        void _Empty_Unknown() {
            final var id = ThreadLocalRandom.current().nextLong();
            final var found = repositoryInstance().findById(id);
            assertThat(found).isEmpty();
        }

        /**
         * Saves a randomized customer, and asserts that finding it by its id returns it.
         * <p>
         * {@code findById} is {@code EntityManager.find}, which answers from the test's persistence context, where the
         * customer is already managed, without going to the database.
         */
        @Test
        void _NotEmpty_Known() {
            final var saved = fixtures().customer();
            final var found = repositoryInstance().findById(identifier(saved));
            assertThat(found).hasValueSatisfying(v -> assertThat(v).isSameAs(saved));
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
            final var selected = repositoryInstance().selectOneByEmailAddress("unknown@unknown.com");
            assertThat(selected).isEmpty();
        }

        /**
         * Saves a randomized customer, and asserts that selecting it by its email address, through the
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
            final var saved = fixtures().customer();
            final var selected = repositoryInstance().selectOneByEmailAddress(saved.getEmailAddress());
            assertThat(selected).hasValueSatisfying(v -> assertThat(v).isSameAs(saved));
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
            final var found = repositoryInstance().findByEmailAddress("unknown@unknown.com");
            assertThat(found).isEmpty();
        }

        /**
         * Saves a randomized customer, and asserts that finding it by its email address returns that very instance.
         * <p>
         * The query does go to the database, but the test's transaction holds one persistence context, and a row whose
         * id is already managed there resolves to the managed instance; hence {@code isSameAs}, not just
         * {@code isEqualTo}.
         */
        @Test
        void _NotEmpty_Known() {
            final var saved = fixtures().customer();
            final var found = repositoryInstance().findByEmailAddress(saved.getEmailAddress());
            assertThat(found).hasValueSatisfying(v -> assertThat(v).isSameAs(saved));
        }

        /**
         * Runs the same lookup as a {@link Specification}, and asserts that it finds that very instance too.
         * <p>
         * A query method is not the only way to a query: {@link CustomerRepository} extends
         * {@link org.springframework.data.jpa.repository.JpaSpecificationExecutor}, through {@link _Repository}, so a
         * criteria predicate built by the caller works whatever methods the repository declares. The attribute is
         * named through the generated {@link Customer_#emailAddress} metamodel, not by string.
         */
        @Test
        void __CriteriaApi() {
            final var saved = fixtures().customer();
            final Specification<Customer> specification =
                    (r, q, b) -> b.equal(r.get(Customer_.emailAddress), saved.getEmailAddress());
            final var found = repositoryInstance().findOne(specification);
            assertThat(found).hasValueSatisfying(v -> assertThat(v).isSameAs(saved));
        }
    }
}
