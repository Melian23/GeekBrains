package Voronova.Lesson11.Task2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Написать метод, который преобразует массив в ArrayList;
 */

public class Task2 {
    public static void main(String[] args) {
        
        String[] arr01 = new String[]{"BMW", "Kia", "Renault", "Mercedes"};

        System.out.println("Массив : ");
        for (String s:arr01) {
            System.out.print(s+" ");
        }

        ArrayList <String> transform = new ArrayList<>(Arrays.asList(arr01));
        System.out.println("\nКоллекция ArrayList: " + transform);
    }
}
