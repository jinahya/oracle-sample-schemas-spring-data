package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Channel;

/**
 * Tests {@link ChannelRepository} against the installed SH schema, through {@link _Repository_SpringBootIT}.
 * <p>
 * See that class for the context and the transactions. There are no tests here yet; the base's own, such as its
 * {@code findById} round trips, run here.
 * <p>
 * Needs the database of {@code application.yaml} up and the SH schema installed; see that file.
 *
 * @see ChannelRepository_DataJpaTest
 */
class ChannelRepository_SpringBootIT
        extends _Repository_SpringBootIT<ChannelRepository, Channel, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    ChannelRepository_SpringBootIT() {
        super(ChannelRepository.class, Channel.class, Long.class);
    }
}
