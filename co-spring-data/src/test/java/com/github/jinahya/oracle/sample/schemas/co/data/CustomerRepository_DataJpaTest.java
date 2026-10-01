package com.github.jinahya.oracle.sample.schemas.co.data;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

/**
 * Tests {@link CustomerRepository} in a JPA slice.
 * <p>
 * A bare {@code @DataJpaTest} implies {@code @AutoConfigureTestDatabase}, which replaces the Oracle datasource of
 * {@code application.yaml} with an embedded H2. The JPA settings of that file still apply, so the H2 gets
 * {@code ddl-auto: none}, {@code default_schema: CO} and {@code OracleDialect}, and holds no {@code CO} schema. Before
 * a test here runs a query, choose one target explicitly. Either override those properties for a generated H2, or add
 * {@code @AutoConfigureTestDatabase(replace = Replace.NONE)} for the installed schema. The root {@code pom.xml}
 * describes both at its H2 dependency.
 * <p>
 * The test body is a placeholder for now. This class only proves that the slice context boots.
 *
 * @see CustomerRepository_SpringBootTest
 */
@DataJpaTest
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class CustomerRepository_DataJpaTest {

    /**
     * Tests {@link CustomerRepository#findByEmailAddress(String)}.
     */
    @Nested
    class FindByEmailAddress_Test {

        /**
         * Not yet written; see the class comment for the database it must choose first.
         */
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