// На вход программы подается размер интервала времени в секундах.
// Переведите это в часы, минуты и секунды и выведите в фотмате "5 часов 39 минут 27 секунд".
package ru.otus;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class TimeConverter {
    // приватный конструктор, предотвращает создание экземпляров
    private TimeConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static void convertTime(Scanner scan) {

        int totalSeconds = scan.nextInt();

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        System.out.println(hours + " часов " + minutes + " минут " + seconds + " секунд");
    }
}
