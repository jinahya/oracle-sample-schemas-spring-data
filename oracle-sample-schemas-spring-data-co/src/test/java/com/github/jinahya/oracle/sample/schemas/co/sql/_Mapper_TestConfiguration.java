package com.github.jinahya.oracle.sample.schemas.co.sql;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.TestConfiguration;

/**
 * Registers the MyBatis mappers of this package.
 * <p>
 * Boot's MyBatis auto-configuration scans the auto-configuration package only, which is that of
 * {@code ___Spring_TestContext}, {@code …co.data}; this package is not under it. A test here {@code @Import}s this.
 */
@TestConfiguration(proxyBeanMethods = false)
@MapperScan(basePackageClasses = _Mapper_TestConfiguration.class, annotationClass = Mapper.class)
class _Mapper_TestConfiguration {

}
