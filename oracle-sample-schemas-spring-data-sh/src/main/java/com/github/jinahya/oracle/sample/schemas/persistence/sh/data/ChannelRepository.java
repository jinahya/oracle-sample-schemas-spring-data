package com.github.jinahya.oracle.sample.schemas.persistence.sh.data;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.Channel;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.Channel_;
import org.springframework.stereotype.Repository;

/**
 * A repository for {@link Channel}, the {@code CHANNELS} table of the Sales History schema.
 *
 * @see Channel
 * @see Channel_
 */
@Repository
public interface ChannelRepository
        extends _Repository<Channel, Long> {

}
