package com.github.jinahya.oracle.sample.schemas.co.data;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

/**
 * Selects the {@code STORE_ORDERS} view, which upstream maps as {@code StoreOrder}, not as an entity: its subtotal and
 * grand-total rows have no key.
 */
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class StoreOrderService {

    // -----------------------------------------------------------------------------------------------------------------
    private final DSLContext dsl;
}
