package ru.job4j.pooh;

/**
 * 3.1. Multithreading
 * 3.1.7. Контрольные вопросы
 * Тестовое задание - проект "Pooh JMS" [#268841]
 * Service интерфейс описывает поведение сервисов.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.03.2022
 */
public interface Service {
    Resp process(Req req);
}
