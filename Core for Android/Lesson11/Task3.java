package Voronova.Lesson11.Task2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Задача:
 * +Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
 * +Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
 * поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 * Для хранения фруктов внутри коробки можно использовать ArrayList;
 * -Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
 * вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
 * -Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той,
 * которую подадут в compare() в качестве параметра.
 * true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
 * - Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
 * Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
 * Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
 * +Не забываем про метод добавления фрукта в коробку.
 */

public class Task3 {

    public static void main(String[] args) {

        Apple apple1 = new Apple ();
        Orange orange1 = new Orange();
        Apple apple2 = new Apple ();
        Orange orange2 = new Orange();

        ArrayList<Apple> appleArrayList = new ArrayList<>();
        appleArrayList.add(apple1);
        appleArrayList.add(apple2);

        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        System.out.println("\nДобавление фруктов в коробки:\n");
        appleBox.addFruit(apple1);
        appleBox.addFruit(apple1);
        appleBox.addFruit(apple1);
        appleBox.addFruit(apple2);
        appleBox.addFruit(apple1);
        appleBox.addFruit(apple1);

        System.out.println("\n***\n");
        orangeBox.addFruit(orange1);
        orangeBox.addFruit(orange1);
        orangeBox.addFruit(orange2);

        System.out.println("\n***\n Вычисление веса коробки\n");
        appleBox.getWeigth(apple1);
        orangeBox.getWeigth(orange1);

        System.out.println("\n***\n Сравнение коробок с фруктами:\n");
        appleBox.compare(orangeBox);

        System.out.println("\n***\n Пересыпание фруктов из одной коробки в другую:\n");
        appleBox.pourFruit(appleBox, orangeBox, apple1);
        orangeBox.pourFruit(orangeBox,orangeBox,orange1);
    }
}

 class Box <T extends Fruit> {

     protected float currentWeight = 0f;
     protected boolean isFree = true;
     protected float maxWeight = 5f;
     protected T type;
     ArrayList<T> arrayList = new ArrayList<>();

                                    // метод добавления фруктов в коробку
     public void addFruit(T fruit) {
         if (currentWeight < maxWeight) {
             arrayList.add(fruit);
             type = fruit;
             currentWeight++;
             System.out.printf("В коробку добавлен %s\n", type);
             if (arrayList.size() < 1) {
                 System.out.println("В коробке нет фруктов!\n");
             } else
                 isFree = true;
         } else {
             currentWeight =maxWeight;
             System.out.printf("Коробка c %s переполнена!\n", type);
         }
     }
                                    //метод вычисления веса коробки с фруктами
     public float getWeigth (Fruit f){
         System.out.println("Вес коробки с " + type + " составляет " + (arrayList.size() * f.weight));
         return arrayList.size() * f.weight;

     }
                                        //метод сравнения веса коробок с фруктами
     public boolean compare (Box box){
         if (arrayList.size() == box.arrayList.size()) {
             System.out.println("Коробки равны!");
             return true;
         } else {
             System.out.println("Коробки разные по весу!");
             return false;
         }
     }
                                    // метод пересыпания фруктов из одной коробки в другую
     public void pourFruit (Box from, Box to, T fruit){
         if (currentWeight < maxWeight && from.type == to.type){
             to.arrayList.add(fruit);
             from.arrayList.remove(fruit);
             System.out.printf("В коробку с %s добавлено %s из коробки с %s\n", to.type, fruit, from.type);
         }else {
             System.out.printf("В коробку c %s невозможно добавить %s\n",to.type , fruit);
         }
     }
 }

abstract class Fruit {
    protected Float weight;
    protected String type;

    @Override
    public String toString() {
        return type;
    }
}

class Apple extends Fruit {
    public Apple() {
        super.weight = 1f;
        super.type = "apple";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class Orange extends Fruit {
    public Orange() {
        super.weight=1.5f;
        super.type = "orange";
    }
}



