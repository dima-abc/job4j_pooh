package ru.job4j.pooh;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 3.1. Multithreading
 * 3.1.7. Контрольные вопросы
 * Тестовое задание - проект "Pooh JMS" [#268841]
 * QueueService.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.03.2022
 */
public class QueueService implements Service {
    private final Map<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();
    private static final String GET = "GET";
    private static final String POST = "POST";

    /**
     * @param req Req - класс, служит для парсинга входящего запроса.
     *            httpRequestType - GET или POST. Он указывает на тип запроса.
     *            poohMode - указывает на режим работы: queue или topic.
     *            sourceName - имя очереди или топика.
     *            param - содержимое запроса.
     * @return Resp - ответ от сервиса.
     * text - текст ответа.
     * status  - это HTTP response status codes.
     */
    @Override
    public Resp process(Req req) {
        if (POST.equals(req.httpRequestType())) {
            queue.putIfAbsent(
                    req.getSourceName(),
                    new ConcurrentLinkedQueue<>());
            queue.get(req.getSourceName()).offer(req.getParam());
            return Resp.of(req.getParam());
        }
        if (GET.equals(req.httpRequestType())) {
            return Resp.of(queue.get(req.getSourceName()).poll());
        }
        return Resp.of(null);
    }
}
