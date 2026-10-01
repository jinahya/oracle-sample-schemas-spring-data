package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import com.github.jinahya.oracle.sample.schemas.co.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class CustomerRepository_SpringBootTest {

    /**
     * Tests {@link CustomerRepository#findByEmailAddress(String)}.
     */
    @Nested
    class FindByEmailAddress_Test {

        /**
         * Asserts that a saved customer is found by its own email address.
         * <p>
         * {@code hasValue(saved)} compares with {@link Customer#equals(Object)}, which compares email addresses only, so
         * this asserts the business key and not every attribute.
         */
        @Test
        void __() {
            final var randomized = ObjectRandomizerUtils.newRandomizedInstanceOf(Customer.class).orElseThrow();
            final var saved = customerRepository.save(randomized);
            final var found = customerRepository.findByEmailAddress(saved.getEmailAddress());
            assertThat(found).isNotEmpty().hasValue(saved);
        }
    }

    @Autowired
    private CustomerRepository customerRepository;
}