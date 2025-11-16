// Вычислите стоимость товара с учетом скидки.
// Пользователь вводит цену товара в виде двух целых чисел через пробел (рубли копейки).
// В следующей строке он вводит размер скидки в процентах (также целое число).
// Цену нужно вывести так, как показано в тесте.
// Учтите, что здесь не применяется округление по правилам математики. Стоимость товара всегда учитывается в большую
// сторону.
// При выводе число рублей и копеек выводится в двух позициях (т.е. 00 коп., если копеек нет)
package ru.otus;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class App {
    public static void main(String... args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Выберите задачу:");
        System.out.println("1 - Сравнение расстояний точек");
        System.out.println("2 - Сумма нечетных цифр трехзначного числа");
        System.out.println("3 - Конвертер времени");
        System.out.println("4 - Стоимость товара со скидкой");
        System.out.println("5 - Счастливое число");
        System.out.print("Введите номер задачи: ");

        int taskNumber = scan.nextInt();
        scan.nextLine();

        switch (taskNumber) {
            case 1:
                PointDistance pointDistance = PointDistance.getInstance(); // получение экземпляра
                pointDistance.compareDistances(scan); // вызов метода
                break;
            case 2:
                OddDigitSumCalculator.INSTANCE.calculateOddDigitSum(scan); // вызов енам через INSTANCE
                break;
            case 3:
                TimeConverter.convertTime(scan);
                break;
            case 4:
                CostCalculator.INSTANCE.calculateCost(scan);
                break;
            case 5:
                LuckyNumber.INSTANCE.calculateLuckyNumber(scan);
                break;
            default:
                System.out.println("Неверный номер задачи");
        }
        scan.close();
    }
}
