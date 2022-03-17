package ru.job4j.pooh;

import java.util.Objects;

/**
 * 3.1. Multithreading
 * 3.1.7. Контрольные вопросы
 * Тестовое задание - проект "Pooh JMS" [#268841]
 * Req - класс, служит для парсинга входящего запроса.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.03.2022
 */
public class Req {
    private static final String POST = "POST";
    private static final String GET = "GET";
    private static final String HTTP = "HTTP";
    /**
     * Поле указывает на тип запроса.
     * GET получить данные.
     * POST отправить данные.
     */
    private final String httpRequestType;
    /**
     * Указывает на режим работы: queue или topic.
     */
    private final String poohMode;
    /**
     * Имя очереди или топика.
     */
    private final String sourceName;
    /**
     * Содержание запроса.
     */
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        Req result = new Req(null, null, null, null);
        String[] strings = content.split("\s/|\s|/|[\r\n]+");
        if (POST.equals(strings[0])) {
            result = new Req(strings[0], strings[1], strings[2], strings[strings.length - 1]);
        }
        if (GET.equals(strings[0])) {
            String id = HTTP.equals(strings[3]) ? "" : strings[3];
            result = new Req(strings[0], strings[1], strings[2], id);
        }
        return result;
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Req req = (Req) o;
        return Objects.equals(httpRequestType, req.httpRequestType)
                && Objects.equals(poohMode, req.poohMode)
                && Objects.equals(sourceName, req.sourceName)
                && Objects.equals(param, req.param);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpRequestType, poohMode, sourceName, param);
    }

    @Override
    public String toString() {
        return "Req{"
                + "httpRequestType='" + httpRequestType + '\''
                + ", poohMode='" + poohMode + '\''
                + ", sourceName='" + sourceName + '\''
                + ", param='" + param + '\'' + '}';
    }
}
