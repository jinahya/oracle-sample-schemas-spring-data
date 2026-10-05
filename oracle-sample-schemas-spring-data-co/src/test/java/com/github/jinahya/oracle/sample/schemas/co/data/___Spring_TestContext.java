package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.__NoOp;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.persistenceunit.ManagedClassNameFilter;

/**
 * The Spring context the tests of this module boot against.
 * <p>
 * It lives in test sources on purpose: this module publishes repositories, not an application, so nothing in
 * {@code src/main} should carry a {@code @SpringBootApplication}. A slice test such as {@code @DataJpaTest} finds this
 * by walking up from its own package, so tests in this package and below need no {@code classes} attribute.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = __NoOp.class)
// The view repositories are plain @Repository beans, which Spring Data does not register and nothing here scans for.
@Import({
        ProductReviewRepositoryImpl.class,
        StoreOrderRepositoryImpl.class
})
public class ___Spring_TestContext {

    /**
     * Drops the {@code @IdClass} flavour of {@code ORDER_ITEMS} from the scan.
     * <p>
     * CO maps {@code ORDER_ITEMS} twice, as {@code OrderItemWithEmbeddedId} and {@code OrderItemWithIdClass}; it is the
     * only table upstream still maps both ways. The two have entity names of their own and both load, but this module
     * keeps one, the one {@link OrderItemWithEmbeddedIdRepository} is for. Upstream separates them with
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
