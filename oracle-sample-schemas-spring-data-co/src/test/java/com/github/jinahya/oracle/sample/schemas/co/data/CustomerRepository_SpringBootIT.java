package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.Customer;
import com.github.jinahya.oracle.sample.schemas.persistence.co.Customer_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Tests {@link CustomerRepository} against the installed CO schema, through the full context of
 * {@link ___Spring_TestContext}.
 * <p>
 * Unlike a {@code @DataJpaTest}, a {@code @SpringBootTest} neither swaps in an embedded database nor wraps a test in a
 * rolled-back transaction. Each repository call runs in its own transaction, and in a persistence context of its own,
 * and commits. A test here that saves therefore leaves what it saves in {@code CO.CUSTOMERS}; the tests that are here
 * now only read.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 *
 * @see CustomerRepository_DataJpaTest
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class CustomerRepository_SpringBootIT {

    private static List<Customer> entities;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Selects the first five customers, by id, as the fixture for the parameterized tests.
     * <p>
     * Ordered by id so that the rows are the ones Oracle's installer put there, not ones that some run of a test has
     * saved since. Runs once per class, which {@link TestInstance.Lifecycle#PER_CLASS} allows to be an instance method,
     * so the autowired repository is already there; the result goes into a static field so that the static method
     * sources of the nested classes can reach it.
     * <p>
     * An empty table aborts the tests of this class rather than failing them: it is the fixture that is missing, not
     * the behaviour under test that is wrong.
     */
    @BeforeAll
    void __() {
        entities = Collections.unmodifiableList(
                customerRepository.findAll(
                        PageRequest.of(0, 5, JpaSort.of(Sort.Direction.ASC, Customer_.customerId))
                ).getContent()
        );
        assumeThat(entities).isNotEmpty();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link CustomerRepository#findByEmailAddress(String)}.
     */
    @Nested
    class FindByEmailAddress_Test {

        private static Stream<String> emailAddresses() {
            return entities.stream().map(Customer::getEmailAddress);
        }

        /**
         * Asserts that each of the selected email addresses finds the customer it belongs to.
         *
         * @param emailAddress the email address of one of the customers selected in
         *                     {@link CustomerRepository_SpringBootIT#__()}.
         */
        @MethodSource("emailAddresses")
        @ParameterizedTest
        void __(final String emailAddress) {
            final var found = customerRepository.findByEmailAddress(emailAddress);
            assertThat(found).hasValueSatisfying(
                    v -> assertThat(v.getEmailAddress())
                            .isEqualTo(emailAddress)
            );
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private CustomerRepository customerRepository;
}