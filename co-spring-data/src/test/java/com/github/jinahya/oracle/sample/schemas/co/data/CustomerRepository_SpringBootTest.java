package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.co.Customer;
import com.github.jinahya.persistence.test.util.__RandomizerUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class CustomerRepository_SpringBootTest {

    @Nested
    class FindByEmailAddress_Test {

        @Test
        void __() {
            final var randomized = __RandomizerUtils.newRandomizedInstanceOf(Customer.class).orElseThrow();
            final var saved = customerRepository.save(randomized);
            final var found = customerRepository.findByEmailAddress(saved.getEmailAddress());
            assertThat(found).isNotEmpty().hasValue(saved);
        }
    }

    @Autowired
    private CustomerRepository customerRepository;
}