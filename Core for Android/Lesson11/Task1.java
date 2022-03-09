package Voronova.Lesson11.Task2;

import java.util.Arrays;

/**
 * Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
 */

public class Task1 {
    public static void main(String[] args) {

        Element n1 = new Element("Анастасия");
        Element n2 = new Element("Илья");
        Element n3 = new Element("Олег");
        Element n4 = new Element("Евгения");
        Element n5 = new Element("Станислав");

        Element [] arr = {n1, n2, n3, n4, n5};

        System.out.println("Изначальный вид массива: "+Arrays.toString(arr));

        System.out.println("\n***\n");

      n1.transform (arr, 0, 1);
        // Почему я к методу трансформ могу обратиться только через переменную n1 или n2...
        // почему не могу обратиться напрямую к методу?

        System.out.println("Вид массива после замены: " + Arrays.toString(arr));
    }
}

class Element {
    private String name;

    public Element(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public  <T> void transform (T[] array,  int index1, int index2){
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
