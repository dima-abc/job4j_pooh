package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 3.1. Multithreading
 * 3.1.7. Контрольные вопросы
 * Тестовое задание - проект "Pooh JMS" [#268841]
 * Test Resp.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 18.03.2022
 */
public class RespTest {
    @Test
    public void whenTextIsEmptyThenStatus204() {
        Resp resp = Resp.of("");
        assertThat(resp.status(), is("204"));
    }

    @Test
    public void whenNullIsEmptyThenStatus204() {
        Resp resp = Resp.of(null);
        assertThat(resp.status(), is("204"));
    }

    @Test
    public void when504ThenNoImplemented() {
        Resp resp = Resp.of("501");
        assertThat(resp.status(), is("501"));
    }

    @Test
    public void whenTextMessageThenStatus200AndTextMessage() {
        Resp resp = Resp.of("Message");
        assertThat(resp.text(), is("Message"));
        assertThat(resp.status(), is("200"));
    }
}