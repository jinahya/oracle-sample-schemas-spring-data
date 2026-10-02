package com.github.jinahya.oracle.sample.schemas.co.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * Tests {@link StoreOrderService} against the installed CO schema. There are no tests here yet; each one, when added,
 * calls the service rather than querying the view itself.
 * <p>
 * Needs the database of {@code application.yaml} up and the CO schema installed; see that file.
 */
@Import({_Jooq_TestConfiguration.class, StoreOrderService.class})
@SpringBootTest
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class StoreOrderService_SpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private StoreOrderService service;
}
