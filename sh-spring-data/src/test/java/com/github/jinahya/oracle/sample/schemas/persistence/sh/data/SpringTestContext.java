package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Channel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@SpringBootApplication
// The entities sit one package up, in the upstream jar, so auto-configuration would not reach them:
// this class's own package is all @SpringBootApplication registers for scanning.
@EntityScan(basePackageClasses = Channel.class)
public class SpringTestContext {

    /**
     * Drops the {@code @IdClass} flavour of every twice-mapped table from the scan.
     * <p>
     * SH maps COSTS, SALES, PROFITS and FWEEK_PSCAT_SALES_MV twice each, as Cost/Sale/Profits/ FweekPscatSalesMv
     * WithEmbeddedId and WithIdClass. The pairs share an entity name each, so a scan that takes both fails as a
     * duplicate. Upstream separates them with {@code <exclude-unlisted-classes>}; this is the Boot equivalent, and it
     * is the hook {@code JpaBaseConfiguration} hands its scanner.
     *
     * @return a filter rejecting every {@code *WithIdClass} entity.
     */
    @Bean
    ManagedClassNameFilter managedClassNameFilter() {
        return className -> !className.endsWith("WithIdClass");
    }
}
