package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberToWordsConverter {

    private static final String[] units = {
            "", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять",
            "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
            "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    };

    private static final String[] tens = {
            "", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят",
            "восемьдесят", "девяносто"
    };

    private static final String[] hundreds = {
            "", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот",
            "семьсот", "восемьсот", "девятьсот"
    };

    public String convert(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0 || amount.compareTo(new BigDecimal("99999.99")) > 0) {
            throw new IllegalArgumentException("Значение должно быть в пределах от 0 до 99,999.99");
        }

        amount = amount.setScale(2, RoundingMode.FLOOR);
        int wholePart = amount.intValue();

        int rub = wholePart % 1000;
        int thousand = wholePart / 1000;

        return convertPart(thousand) + (thousand > 0 ? " " + (thousand == 1 ? "тысяча" : "тысяч") + " " : "") +
                convertPart(rub) + " рублей" +
                (amount.remainder(BigDecimal.ONE).movePointRight(2).intValue() > 0 ? " " + convertPart(amount.remainder(BigDecimal.ONE).movePointRight(2).intValue()) + " копеек" : "");
    }

    private String convertPart(int number) {
        if (number == 0) {
            return "";
        } else if (number < 20) {
            return units[number];
        } else if (number < 100) {
            return tens[number / 10] + (number % 10 != 0 ? " " + units[number % 10] : "");
        } else {
            return hundreds[number / 100] + (number % 100 != 0 ? " " + convertPart(number % 100) : "");
        }
    }

    public static void main(String[] args) {
        NumberToWordsConverter converter = new NumberToWordsConverter();

        BigDecimal amount = new BigDecimal("12345.67");
        String words = converter.convert(amount);
        System.out.println(words);
    }
}