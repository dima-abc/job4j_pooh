package ru.job4j.pooh;

/**
 * 3.1. Multithreading
 * 3.1.7. Контрольные вопросы
 * Тестовое задание - проект "Pooh JMS" [#268841]
 * Resp - ответ от сервиса.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.03.2022
 */
public class Resp {
    private final String text;
    public final String status;

    public Resp(String text, String status) {
        this.text = text;
        this.status = status;
    }

    public String text() {
        return text;
    }

    public String status() {
        return status;
    }
}
