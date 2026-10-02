package com.github.jinahya.oracle.sample.schemas.persistence.hr.data;

import org.jooq.jpa.extensions.DefaultAnnotatedPojoMemberProvider;
import org.springframework.boot.jooq.autoconfigure.DefaultConfigurationCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Lets jOOQ read the {@code @Column} annotations of a class it maps results into, such as an upstream class without
 * {@code @Entity}. jOOQ does not find the provider on its own.
 * <p>
 * Pull it in with {@code @Import(_Jooq_TestConfiguration.class)}; component scanning leaves test configurations out.
 */
@TestConfiguration(proxyBeanMethods = false)
class _Jooq_TestConfiguration {

    @Bean
    DefaultConfigurationCustomizer annotatedPojoMemberProvider() {
        return c -> c.set(new DefaultAnnotatedPojoMemberProvider());
    }
}
