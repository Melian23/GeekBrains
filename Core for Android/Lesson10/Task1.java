package Lesson10;

/**
 * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
 *  * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 *  * Посчитать, сколько раз встречается каждое слово.
 *
 */

import java.util.*;

public class Task1 {

    public static void main(String[] args) {

        List<String> words = Arrays.asList("Pink", "Red", "Yellow", "Grey",  "Orange",  "Brown", "Yellow",
                "White", "Pink", "Red", "Yellow", "Violet", "Grey");

        Set <String> unique = new HashSet <>(words);

        System.out.println("Список первоначальных слов:");
        System.out.println(words);
        System.out.println("Список уникальных слов:");
        System.out.println(unique);
        System.out.println("Количество повторений:");
        for (String s: unique) {
            System.out.println(s + ":" + Collections.frequency(words, s));
        }
    }
}
