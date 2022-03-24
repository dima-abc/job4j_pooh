package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1. Multithreading
 * 3.1.7. Контрольные вопросы
 * Тестовое задание - проект "Pooh JMS" [#268841]
 * Test QueueService.
 * Забираем данные из очереди weather. Режим queue
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.03.2022
 */
public class QueueServiceTest {

    @Test
    public void whenPostThenGetQueue() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.text(), is("temperature=18"));
    }

    @Test
    public void whenNotPostThenNotGetThenStatus501() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        Resp result = queueService.process(
                new Req("HOST", "queue", "weather", paramForPostMethod)
        );
        assertThat(result.status(), is("501"));
    }

    @Test
    public void whenGetQueueNullThenStatus204() {
        QueueService queueService = new QueueService();
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.status(), is("204"));
    }

    @Test
    public void whenGetQueueIsEmptyThenStatus204() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "";
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.status(), is("204"));
    }
}