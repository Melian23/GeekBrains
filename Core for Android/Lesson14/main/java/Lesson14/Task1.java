package Lesson14;

import static java.lang.System.arraycopy;

/**
 * Написать метод, которому в качестве аргумента передается не пустой одномерный
 * целочисленный массив. Метод должен вернуть новый массив, который получен путем
 * вытаскивания из исходного массива элементов, идущих после последней четверки. Входной
 * массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить
 * RuntimeException.
 * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 * Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
 */

public class Task1 {

    static boolean found = false;
    static int index = 0;

    public static void main(String[] args) {

        int [] arrIn2 = new int [] {5,8,7,4,1,6,1,2,3};
        int [] arrIn3 = new int [] {5,4,7, 8,1,6,4,2,5,3};
        int [] arrIn4 = new int [] {5,8,7,1,6,7,2,4};
        int [] arrIn5 = new int [] {5,8,7,1,6,7,2,9};

        newArray (arrIn2);
        newArray(arrIn3);
        newArray(arrIn4);
        newArray(arrIn5);

    }
    public static int[] newArray(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                index = i;
                found = true;
            }
        }

        System.out.println("Новый массив:");
        int a = arr.length - index - 1;
        int[] arrOut = new int[a];

        try {
            if (found) {
                arraycopy(arr, index + 1, arrOut, 0, a);
                for (int i = 0; i < a; i++) {
                    System.out.print(arrOut[i] + " ");
                }
            }else throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println("В массиве нет элемента - 4");
        }

        System.out.println();
        index = 0;
        found = false;
        return arrOut;
    }

}