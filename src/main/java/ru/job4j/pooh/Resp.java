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
    private final String status;
    private static final String ERROR = "501";
    private static final String OK = "200";

    private Resp(String text, String status) {
        this.text = text;
        this.status = status;
    }

    public static Resp of(String text) {
        if (text == null || text.isEmpty()) {
            return new Resp("Not Implemented", ERROR);
        }
        return new Resp(text, OK);
    }

    public String text() {
        return text;
    }

    public String status() {
        return status;
    }
}
