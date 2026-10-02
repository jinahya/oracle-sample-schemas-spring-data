package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.__NoOp;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.persistenceunit.ManagedClassNameFilter;

/**
 * The Spring context the tests of this module boot against.
 * <p>
 * It lives in test sources on purpose: this module publishes repositories, not an application, so nothing in
 * {@code src/main} should carry a {@code @SpringBootApplication}. A slice test such as {@code @DataJpaTest} finds this
 * by walking up from its own package, so tests in this package and below need no {@code classes} attribute.
 */
//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = __NoOp.class)
public class ___Spring_TestContext {

    /**
     * Drops the {@code @IdClass} flavour of every twice-mapped table from the scan.
     * <p>
     * HR maps {@code JOB_HISTORY} twice, as {@code JobHistoryWithEmbeddedId} and {@code JobHistoryWithIdClass}. The two
     * share an entity name, so a scan that takes both fails as a duplicate. Upstream separates them with
     * {@code <exclude-unlisted-classes>}; this is the Boot equivalent, and it is the hook {@code JpaBaseConfiguration}
     * hands its scanner.
     *
     * @return a filter rejecting every {@code *WithIdClass} entity.
     */
    @Bean
    ManagedClassNameFilter managedClassNameFilter() {
        return className -> !className.endsWith("WithIdClass");
    }
}
