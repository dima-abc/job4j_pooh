package ru.job4j;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Hello world!
 * Test.
 */
public class TriggerTest {
    @Test
    public void whenGetIntThen1() {
        Trigger trigger = new Trigger();
        int result = trigger.getInt();
        int expected = 1;
        assertThat(result, is(expected));
    }
}
