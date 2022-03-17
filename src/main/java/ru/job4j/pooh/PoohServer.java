package ru.job4j.pooh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 3.1. Multithreading
 * 3.1.7. Контрольные вопросы
 * Тестовое задание - проект "Pooh JMS" [#268841]
 * Класс Сервер.
 * PoohServer
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.03.2022
 */
public class PoohServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PoohServer.class.getName());
    private final HashMap<String, Service> modes = new HashMap<>();

    public void start() {
        modes.put("queue", new QueueService());
        modes.put("topic", new TopicService());
        ExecutorService pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors());
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                pool.execute(() -> {
                    try (OutputStream out = socket.getOutputStream();
                         InputStream input = socket.getInputStream()) {
                        byte[] buff = new byte[1_000_000];
                        var total = input.read(buff);
                        var content = new String(Arrays.copyOfRange(buff, 0, total), StandardCharsets.UTF_8);
                        var req = Req.of(content);
                        var resp = modes.get(req.getPoohMode()).process(req);
                        String ls = System.lineSeparator();
                        out.write(("HTTP/1.1 " + resp.status() + ls + ls).getBytes());
                        out.write((resp.text().concat(ls)).getBytes());
                    } catch (IOException e) {
                        LOGGER.error("IO exception", e.getCause());
                    }
                });
            }
        } catch (IOException e) {
            LOGGER.error("IO exception", e.getCause());
        }
    }

    public static void main(String[] args) {
        new PoohServer().start();
    }
}
