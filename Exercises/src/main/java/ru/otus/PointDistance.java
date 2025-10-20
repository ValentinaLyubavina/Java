// Пользователь вводит координаты двух точек на плоскости.
// В первой строке через пробел - координата X и координата Y первой точки, во второй строке также через пробел
// координаты второй точки.
// Нужно определить, какая из точек ближе к началу координат.
// Выводится одно из сообщений: "Первая точка ближе", "Вторая точка ближе", "Точки на равных расстояниях".
// Подсказка: расстояние до начала координат находится по формуле:

package ru.otus;

import java.util.Scanner;

@SuppressWarnings("java:S6548")
public class PointDistance {
    // хранит единственный экземпляр класса
    private static PointDistance INSTANCE;
    // блокирует создание объектов через new PointDistance(), экземпляр можно получить через getInstance()
    private PointDistance() {}
    // проверяет, что инстанса нет, если нет - создает new PointDistance() и сохраняет в инстанс
    public static synchronized PointDistance getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PointDistance();
        }
        return INSTANCE;
    }

    public void compareDistances(Scanner scan) {
        double x1;
        double y1;
        double x2;
        double y2;

        x1 = scan.nextDouble();
        y1 = scan.nextDouble();

        x2 = scan.nextDouble();
        y2 = scan.nextDouble();

        double distance1 = Math.sqrt(x1 * x1 + y1 * y1);
        double distance2 = Math.sqrt(x2 * x2 + y2 * y2);

        if (distance1 < distance2) {
            System.out.println("Первая точка ближе");
        } else if (distance1 > distance2) {
            System.out.println("Вторая точка ближе");
        } else {
            System.out.println("Точки на равных расстояниях");
        }
    }
}
