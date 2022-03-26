package ru.job4j.pooh;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 3.1. Multithreading
 * 3.1.7. Контрольные вопросы
 * Тестовое задание - проект "Pooh JMS" [#268841]
 * TopicService.
 * <p>
 * Отправитель посылает запрос на добавление данных с указанием топика (weather) и значением параметра (temperature=18).
 * Сообщение помещается в конец каждой индивидуальной очереди получателей.
 * Если топика нет в сервисе, то данные игнорируются.
 * <p>
 * Получатель посылает запрос на получение данных с указанием топика.
 * Если топик отсутствует, то создается новый.
 * А если топик присутствует,
 * то сообщение забирается из начала индивидуальной очереди получателя и удаляется.
 * Когда получатель впервые получает данные из топика – для него создается индивидуальная пустая очередь.
 * Все последующие сообщения от отправителей с данными для этого топика помещаются в эту очередь тоже.
 * Таким образом в режиме "topic" для каждого потребителя своя будет уникальная очередь с данными,
 * в отличие от режима "queue", где для все потребители получают данные из одной и той же очереди.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.03.2022
 */
public class TopicService implements Service {
    private final Map<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> topic = new ConcurrentHashMap<>();
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String ERROR = "501";

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
        Resp result = Resp.of(ERROR);
        if (GET.equals(req.httpRequestType())) {
            topic.putIfAbsent(req.getSourceName(), new ConcurrentHashMap<>());
            topic.get(req.getSourceName())
                    .putIfAbsent(req.getParam(), new ConcurrentLinkedQueue<>());
            result = Resp.of(topic.get(req.getSourceName())
                    .get(req.getParam()).poll()
            );
        } else if (POST.equals(req.httpRequestType())) {
            topic.computeIfPresent(req.getSourceName(),
                    (key, hashMap) -> {
                        hashMap.values()
                                .forEach(value -> value.add(req.getParam()));
                        return hashMap;
                    });
            result = Resp.of(req.getParam());
        }
        return result;
    }
}
