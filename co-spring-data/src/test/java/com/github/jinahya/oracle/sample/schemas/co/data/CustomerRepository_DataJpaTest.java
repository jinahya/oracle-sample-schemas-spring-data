package com.github.jinahya.oracle.sample.schemas.co.data;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class CustomerRepository_DataJpaTest {

    @Nested
    class FindByEmailAddress_Test {

        @Test
        void __() {
//            final Customer persisted = __RandomizerUtils.newRandomizerInstanceOf(entityManager, Customer.class);
//            customerRepository.findByEmailAddress(persisted.getEmailAddress());
        }
    }

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;
}