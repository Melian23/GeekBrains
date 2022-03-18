package newLesson13;

/**
 * 1. Перенести приведенный код в новый проект, где мы организуем гонки.
 * Все участники должны стартовать одновременно, несмотря на разное время подготовки.
 * В тоннель не может одновременно заехать больше половины участников (условность).
 * Попробуйте все это синхронизировать.
 * Первый участник, пересекший финишную черту, объявляется победителем (в момент пересечения
 * этой самой черты). Победитель должен быть только один (ситуация с 0 или 2+ победителями
 * недопустима).
 * Когда все завершат гонку, нужно выдать объявление об окончании.
 * Можно корректировать классы (в том числе конструктор машин) и добавлять объекты классов из
 * пакета java.util.concurrent.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Task2 {
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[1;31m";
    public static final String PURPLE = "\033[4;35m";
    public static final String YELLOW = "\033[1;93m";

    public static final int COUNT_CARS = 4;
    public static AtomicInteger WINNER_CAR = new AtomicInteger(0);
    public static final CyclicBarrier BARRIER = new CyclicBarrier(COUNT_CARS + 1);

    public static void main(String[] args) {
       new Task2().doRace();
    }

    void doRace (){
        System.out.printf("%s ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!%s\n", PURPLE, RESET);
        Race race = new Race(new Road(80), new Tunnel(), new Road(60) );
        Car [] cars = new Car[COUNT_CARS];
        for (int i = 0; i < COUNT_CARS; i++) {
            cars [i] = new Car(race, 20 + (int) (Math.random() * 10), i+1);
        }
        for (Car s: cars) {
            new Thread(s).start();
        }
        try {
            int readyCarCount = BARRIER.getNumberWaiting();
            while (readyCarCount != COUNT_CARS) {
                Thread.sleep(200);
                readyCarCount = BARRIER.getNumberWaiting();
            }
            BARRIER.await();
            System.out.printf ("%s ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!%s \n", PURPLE, RESET);
            readyCarCount = 0;
            while (readyCarCount != COUNT_CARS){
                Thread.sleep(200);
                readyCarCount = BARRIER.getNumberWaiting();
            }

            System.out.printf("%s ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!! %s \n", PURPLE, RESET);
            BARRIER.await();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

public class Car implements Runnable{
    private String name;
    private int speed;
    private Race race;

    public Car(Race race, int speed, int index) {
        this.name = "Участник №" + index;
        this.speed = speed;
        this.race = race;
    }
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов!");
            BARRIER.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            if (WINNER_CAR.getAndIncrement() == 0){
                System.out.printf(" %s У нас есть победитель: %s %s \n",RED, this.name,RESET );
            } else {
                System.out.println(this.name + " пришел " + WINNER_CAR.get() + "м!");
            }
            BARRIER.await();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
public class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() {
        return stages;
    }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
 public class Tunnel extends Stage{
    final Semaphore semaphore = new Semaphore(4/2);

    public Tunnel() {
        this.length = 80;
        this.description= "Туннель "+ length + " метров.";
    }
    @Override
    public void go(Car car) {
        try {
            System.out.println(car.getName() + " ждет освобождения этапа:  " + description);
            semaphore.acquire();
            System.out.printf("%s %s начал этап: %s %s\n", YELLOW ,car.getName(), description, RESET);
            Thread.sleep(length / car.getSpeed() * 1000);

        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            System.out.println(car.getName() + " закончил этап: " + description);
            semaphore.release();
        }
    }
}
public class Road extends Stage{
    public Road(int length) {
        this.length=length;
        this.description="Дорога " + length + " метров";
    }
    @Override
    public void go(Car car) {
        try {
            System.out.println(car.getName() + " начал этап: " + description);
            Thread.sleep(length / car.getSpeed() * 1000);
            System.out.println(car.getName() + " закончил этап: " + description);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
 public abstract class Stage {
    protected String description;
    protected int length;
    public abstract void go(Car car);
}
}
