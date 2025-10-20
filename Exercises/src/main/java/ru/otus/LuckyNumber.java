// Пользователь вводит с клавиатуры целое шестизначное число.
// Нужно определить, является ли число счастливым, и вывести YES, если это так, и NO в противном случае.
// Счастливым считается шестизначное число, у которого сумма первых трех цифр равна сумме последних трех цифр.
// Если число не шестизначное, то нужно вывести ERROR
package ru.otus;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public enum LuckyNumber {
    INSTANCE;

    public void calculateLuckyNumber(Scanner scan) {
        int digit = scan.nextInt();

        if (digit < 99999 || digit > 999999) {
            System.out.println("ERROR");
            return;
        }
        int oneDigit = digit / 100000;
        int twoDigit = (digit / 10000) % 10;
        int threeDigit = (digit / 1000) % 10;
        int fourDigit = (digit / 100) % 10;
        int fiveDigit = (digit / 10) % 10;
        int sixDigit = digit % 10;

        int sumBeginning = oneDigit + twoDigit + threeDigit;
        int sumEnd = fourDigit + fiveDigit + sixDigit;
        if (sumBeginning == sumEnd) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
