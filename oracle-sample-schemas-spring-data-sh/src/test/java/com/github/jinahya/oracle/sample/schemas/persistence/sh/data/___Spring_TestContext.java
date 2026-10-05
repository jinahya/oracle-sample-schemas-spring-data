package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.__NoOp;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

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

}
