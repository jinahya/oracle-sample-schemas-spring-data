package com.github.jinahya.oracle.sample.persistence.hr.data;

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import org.springframework.boot.test.context.TestComponent;

/**
 * Persists the fixtures a {@code @DataJpaTest} needs, through the repositories, never through the entity manager.
 * <p>
 * Each method randomizes an entity with the upstream randomizer, gives it whatever it refers to (persisted the same
 * way, or passed in), and saves it through that entity's repository. No test of this module needs one yet, so there
 * is no method here so far; add one, with the repository it saves through, when a test does.
 * <p>
 * {@link _Repository_DataJpaTest} imports it; a slice test reaches it as {@code fixtures()}.
 */
@TestComponent
class _Repository_DataJpaTest_Fixtures {

    /**
     * Returns a randomized instance of the specified class, through its upstream {@code <Entity>_Randomizer}.
     *
     * @param clazz the class to randomize.
     * @param <T>   the type of the class.
     * @return a randomized instance.
     */
    static <T> T randomized(final Class<T> clazz) {
        return ObjectRandomizerUtils.newRandomizedInstanceOf(clazz).orElseThrow();
    }
}
