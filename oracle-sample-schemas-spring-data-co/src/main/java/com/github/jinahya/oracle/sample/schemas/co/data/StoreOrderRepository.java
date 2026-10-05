package com.github.jinahya.oracle.sample.schemas.co.data;

import com.github.jinahya.oracle.sample.schemas.persistence.co.StoreOrder;

/**
 * A repository for the {@code STORE_ORDERS} view of the Customer Orders schema, which upstream maps as
 * {@link StoreOrder}, a class that is not an {@code @Entity}: its {@code GROUPING SETS} key
 * {@code (STORE_NAME, ORDER_STATUS)} has no duplicates, but both columns are {@code NULL} in the subtotal and
 * grand-total rows, and an {@code @Id} may not be null.
 * <p>
 * This is a Spring Data repository fragment, not a repository: Spring Data JPA builds repositories for managed types
 * only, and rejects {@link StoreOrder} at startup ({@code Not a managed type}). {@link StoreRepository} extends it,
 * and Spring Data backs its methods with {@link StoreOrderRepositoryImpl}, which it finds by name. Declare no
 * {@code findById}, {@code save} or {@code delete}: the view has no usable key, and every column of it is
 * read-only.
 *
 * @see StoreOrder
 * @see StoreOrderRepositoryImpl
 * @see StoreRepository
 */
public interface StoreOrderRepository {

}
