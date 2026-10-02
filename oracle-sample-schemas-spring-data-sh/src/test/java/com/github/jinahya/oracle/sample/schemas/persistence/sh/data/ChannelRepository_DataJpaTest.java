package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Channel;

/**
 * Tests {@link ChannelRepository} in a JPA slice, over an embedded H2, through {@link _Repository_DataJpaTest}.
 * <p>
 * See that class for how the slice is configured. There are no tests here yet, and the base declares none, so this
 * class runs nothing so far.
 *
 * @see ChannelRepository_SpringBootIT
 */
class ChannelRepository_DataJpaTest
        extends _Repository_DataJpaTest<ChannelRepository, Channel, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ChannelRepository_DataJpaTest() {
        super(ChannelRepository.class, Channel.class, Long.class);
    }
}
