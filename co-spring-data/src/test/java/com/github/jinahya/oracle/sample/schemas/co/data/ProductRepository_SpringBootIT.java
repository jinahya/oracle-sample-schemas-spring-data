package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.co.Customer;
import com.github.jinahya.oracle.sample.schemas.co.Customer_;
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

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Tests {@link CustomerRepository} against the installed CO schema, through the full context of
 * {@link SpringTestContext}.
 * <p>
 * Unlike a {@code @DataJpaTest}, a {@code @SpringBootTest} neither swaps in an embedded database nor wraps a test in a
 * rolled-back transaction. Each repository call runs in its own transaction and commits, so <em>every run leaves the
 * customers it saves in {@code CO.CUSTOMERS}</em>. Each of them has a fresh random email address, so the runs do not
 * collide with each other.
 *
 * @see CustomerRepository_DataJpaTest
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class CustomerRepository_SpringBootIT {

    private static List<Customer> CUSTOMERS;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Selects the first five customers, by id, as the fixture for the parameterized tests.
     * <p>
     * Ordered by id so that the rows are the ones Oracle's installer put there, not the ones earlier runs of this class
     * have saved. Runs once per class, which {@link TestInstance.Lifecycle#PER_CLASS} allows to be an instance method,
     * so the autowired repository is already there.
     */
    @BeforeAll
    void __() {
        CUSTOMERS = customerRepository.findAll(
                PageRequest.of(0, 5, JpaSort.of(Sort.Direction.ASC, Customer_.customerId))
        ).getContent();
        assumeThat(CUSTOMERS).isNotEmpty();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link CustomerRepository#findByEmailAddress(String)}.
     */
    @Nested
    // A nested class does not inherit the lifecycle of the enclosing one; PER_CLASS here lets the method source
    // below be
    // an instance method, and so reach the fixture of the enclosing instance.
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class FindByEmailAddress_Test {

        private static Stream<String> emailAddresses() {
            return CUSTOMERS.stream().map(Customer::getEmailAddress);
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
            assertThat(found).hasValueSatisfying(f -> assertThat(f.getEmailAddress()).isEqualTo(emailAddress));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private CustomerRepository customerRepository;
}