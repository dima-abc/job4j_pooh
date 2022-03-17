package ru.job4j.pooh;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1. Multithreading
 * 3.1.7. Контрольные вопросы
 * Тестовое задание - проект "Pooh JMS" [#268841]
 * Test. TopicService.
 * Режим topic. Подписываемся на топик weather. client407.
 * Режим topic. Добавляем данные в топик weather.
 * Режим topic. Забираем данные из индивидуальной очереди в топике weather. Очередь client407.
 * Режим topic. Забираем данные из индивидуальной очереди в топике weather. Очередь client6565.
 * Очередь отсутствует, т.к. еще не был подписан - получит пустую строку
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 17.03.2022
 */
public class TopicServiceTest {
    @Test
    public void whenTopic() {
        TopicService topicService = new TopicService();
        String paramForPublisher = "temperature=18";
        String paramForSubscriber1 = "client407";
        String paramForSubscriber2 = "client6565";
        topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        topicService.process(
                new Req("POST", "topic", "weather", paramForPublisher)
        );
        Resp result1 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        Resp result2 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber2)
        );
        assertThat(result1.text(), is("temperature=18"));
        assertThat(result2.text(), is("No Content"));
    }
}