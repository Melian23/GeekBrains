package Lesson14;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Написать метод, который проверяет состав массива из чисел 1 и 4.
 * Если в нем нет хоть одной четверки или единицы, то метод вернет false;
 * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 * [ 1 1 1 4 4 1 4 4 ] -> true
 * [ 1 1 1 1 1 1 ] -> false
 * [ 4 4 4 4 ] -> false
 * [ 1 4 4 1 1 4 3 ] -> false
 */

public class Task2 {
    public static void main(String[] args) {

        int [] arr1 = new int[] {1, 1, 1, 4, 4, 1, 4, 4};
        int [] arr2 = new int[] {1, 1, 1, 1, 1, 1, 1};
        int [] arr3 = new int[] { 4, 4, 4, 4};
        int [] arr4 = new int[] {1, 1, 1, 4, 3, 1, 4, 4};


        System.out.println(checkArray (arr1));
        System.out.println(checkArray (arr2));
        System.out.println(checkArray (arr3));
        System.out.println(checkArray (arr4));


    }

    public static boolean checkArray(int[] arr){

        boolean checkOne = false;
        boolean checkFour = false;
        for (int s : arr) {
            if (s == 1) {
                checkOne = true;
            } else if (s == 4) {
                checkFour = true;
            } else return false;
        } return checkOne && checkFour;



        /*for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 4 && arr[i] != 1){
                return true;
            } else return false;
        }
return false;*/
    }

}
