[![job4j_threads](https://github.com/Dima-Stepanov/job4j_pooh/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/Dima-Stepanov/job4j_pooh/actions/workflows/maven-publish.yml)
[![codecov](https://codecov.io/gh/Dima-Stepanov/job4j_pooh/branch/master/graph/badge.svg?token=0229BM2P1I)](https://codecov.io/gh/Dima-Stepanov/job4j_pooh)

**Мидл**

3.1. Multithreading

###### **3.1.7. Контрольные вопросы**

2. Тестовое задание - проект "Pooh JMS" [#268841]

В этом проекте мы сделаем аналог асинхронной очереди.
Приложение запускает Socket и ждет клиентов.
Клиенты могут быть двух типов: отправители (publisher), получатели (subscriber).
В качестве клиента будем использовать cURL. https://curl.se/download.html
В качестве протокола будем использовать HTTP.

###### **Задание.**
1. Создайте репозиторий job4j_pooh.
2. Допишите классы Req, Resp, QueueService, TopicService. Класс PoohServer трогать не нужно.
3. В коде не должно быть синхронизации. Внутри Service нужно использовать коллекции из класса concurrency.
4. Добавьте свои тесты.
5. Загрузите код. Оставьте ссылку на коммит.
6. Переведите ответственного на Петра Арсентьева.