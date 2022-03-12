package Lesson12;

/**
 * Необходимо написать два метода, которые делают следующее:
 * 1) Создают одномерный длинный массив, например:
 *          static final int SIZE = 10 000 000;
 *          static final int HALF = size / 2;
 *          float[] arr = new float[size].
 * 2) Заполняют этот массив единицами.
 * 3) Засекают время выполнения: long a = System.currentTimeMillis().
 * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 *          arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * 5) Проверяется время окончания метода System.currentTimeMillis().
 * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
 *
 * Отличие первого метода от второго:
 * ● Первый просто бежит по массиву и вычисляет значения.
 * ● Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и
 * потом склеивает эти массивы обратно в один.
 * По замерам времени:
 * Для первого метода надо считать время только на цикл расчета:
 *          for (int i = 0; i < size; i++) {
 *          arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));}
 * Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и
 * склейки.
 */

public class Part1 {

    static final int size = 10000000;

    public static void main(String[] args) {

        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        FirstThread firstThread = new FirstThread(arr);
        Thread first = new Thread(firstThread);
        first.start();

        SecondThread secondThread = new SecondThread (arr);
        Thread second = new Thread(secondThread);
        second.start();

        ThirdMethod thirdMethod = new ThirdMethod(arr);
        Thread third = new Thread (thirdMethod);
        third.start();

        }
    }

class FirstThread implements Runnable {

    private float [] arr;
    public FirstThread(float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        System.currentTimeMillis();
        System.out.println("На вычисление формулы в одном потоке потребовалось :" + (System.currentTimeMillis() - a) + "мс.");
    }
}

class SecondThread implements Runnable {

    private float [] arr;
    private int size = 10000000;
    private int half = size / 2;

    public SecondThread(float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        long a = System.currentTimeMillis();

        float[] arr1 = new float[half];
        float[] arr2 = new float[half];

        System.arraycopy(arr, 0, arr1, 0, half);
        System.arraycopy(arr, half, arr2, 0, half);

        MyThread t1 = new MyThread(arr1);
        MyThread t2 = new MyThread(arr2);

        t1.start();
        t2.start();

        System.arraycopy (arr1, 0, arr, 0, half);
        System.arraycopy (arr2, 0, arr, half, half);

        System.currentTimeMillis();
        System.out.println("На вычисление формулы в двух потоках потребовалось :" + (System.currentTimeMillis() - a) + " мс.");
    }
}

class MyThread extends Thread {
    private float [] arr;

    public MyThread(float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
    }
}

//
class ThirdMethod implements Runnable {

    private float [] arr;
    private int size = 10000000;
    private int quarter = size / 4;


    public ThirdMethod(float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        long a = System.currentTimeMillis();

        float[] arr1 = new float[quarter];
        float[] arr2 = new float[quarter];
        float[] arr3 = new float[quarter];
        float[] arr4 = new float[quarter];

        System.arraycopy(arr, 0, arr1, 0, quarter);
        System.arraycopy(arr, quarter, arr2, 0, quarter);
        System.arraycopy(arr, quarter*2, arr3, 0, quarter);
        System.arraycopy(arr, quarter*3, arr4, 0, quarter);

        MyThreadV2 t1 = new MyThreadV2(arr1);
        MyThreadV2 t2 = new MyThreadV2(arr2);
        MyThreadV2 t3 = new MyThreadV2(arr3);
        MyThreadV2 t4 = new MyThreadV2(arr4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        System.arraycopy (arr1, 0, arr, 0, quarter);
        System.arraycopy (arr2, 0, arr, quarter, quarter);
        System.arraycopy (arr3, 0, arr, quarter*2, quarter);
        System.arraycopy (arr4, 0, arr, quarter*3, quarter);

        System.currentTimeMillis();
        System.out.println("На вычисление формулы в четырех потоках потребовалось :" + (System.currentTimeMillis() - a) + " мс.");
    }
}

class MyThreadV2 extends Thread {
    private float [] arr;

    public MyThreadV2(float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
    }
}

