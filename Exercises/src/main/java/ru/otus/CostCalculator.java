// Вычислите стоимость товара с учетом скидки.
// Пользователь вводит цену товара в виде двух целых чисел через пробел (рубли копейки).
// В следующей строке он вводит размер скидки в процентах (также целое число).
// Учтите, что здесь не применяется округление по правилам математики. Стоимость товара всегда учитывается в большую
// сторону.
// При выводе число рублей и копеек выводится в двух позициях (т.е. 00 коп., если копеек нет)
package ru.otus;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public enum CostCalculator {
    INSTANCE;

    public void calculateCost(Scanner scan) {
        int rubles = scan.nextInt();
        int kopecks = scan.nextInt();
        int discount = scan.nextInt();
        // вся цена в копейках
        int totalPriceInKopecks = rubles * 100 + kopecks;

        int discountAmount = totalPriceInKopecks * discount / 100;
        int finalPriceInKopecks = totalPriceInKopecks - discountAmount;

        int rub = finalPriceInKopecks / 100;
        int kop = finalPriceInKopecks % 100;

        System.out.printf("%02d руб. %02d коп.", rub, kop);
    }
}
