package Lesson8.Task1;

/**
 * 1. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса. Эти
 * классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в
 * консоль).
 * 2. Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники
 * должны выполнять соответствующие действия (бежать или прыгать), результат выполнения
 * печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
 * 3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти
 * этот набор препятствий.
 * 4. * У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения
 * на бег и прыжки. Если участник не смог пройти одно из препятствий, то дальше по списку он
 * препятствий не идет.
 */

import java.util.Random;

public class Task1 {

        public static Random random = new Random();

        public static void main(String[] args) {

            // Объявляем бегунов
            Runner cat01 = new Cat ("Барсик", random.nextInt(10)+1, random.nextInt(10)+1);
            Runner robot01 = new Robot("Валли", random.nextInt(10)+1, random.nextInt(10)+1);
            Runner human1=new Human("Петр", random.nextInt(10)+1, random.nextInt(10)+1);

            Runner [] rivals =new Runner[3];
            rivals [0] = robot01;
            rivals [1] = cat01;
            rivals [2] = human1;

            // Объявим препятствия
            Obstacle[] obstacles = {
                    new Track(1),
                    new Wall(3),
                    new Track(2),
                    new Wall(4),
                    new Track(3),
                    new Wall( 2)
            };

            // Организуем два цикла
            // Проходим по всем бегунам и внутри каждого бегуна, прогоняем его по всем препятствиям
            for (Runner r : rivals) {
                for (Obstacle o : obstacles) {
                    // В зависимости от типа препятствия, вызываем тот или иной метод (бежать или прыгать)
                    if (o instanceof Track) {
                        if (!r.runAble(o.getLength()))
                            break; // Если нам не удалось пробежать или перепрыгнуть препятствия, выходит из внутреннего цикла
                        // На этом забег для нашего бегуна заканчивается и мы переходим к следующему "спортсмену"
                    } else if (o instanceof  Wall) {
                        if (!r.jumpAble(o.getHeight()))
                            break;
                    }
                }
            }
        }
    }

    /**
     * Препятствие
     */
    interface Obstacle {

        /**
         * Длинна препятствия
         * @return
         */
        int getLength();

        /**
         * Высота препятствия
         * @return
         */
        int getHeight();
    }

    interface Runner {

        boolean jumpAble (int distance);
        boolean runAble (int length);
    }

    /**
     * Беговая дорожка
     */
    class Track implements Obstacle {

        private final int length;

        public Track(int length) {
            this.length = length;
        }

        @Override
        public int getLength() {
            return length;
        }

        @Override
        public int getHeight() {
            return 0;
        }
    }

    /**
     * Стена
     */
    class Wall implements Obstacle {

        private final int height;

        public Wall(int height) {
            this.height = height;
        }

        @Override
        public int getLength() {
            return 0;
        }

        @Override
        public int getHeight() {
            return height;
        }

    }

    class Robot implements Runner {

        private String name;
        private int heigth;
        private int length;

        public Robot(String name, int heigth, int length) {
            this.name = name;
            this.heigth = heigth;
            this.length = length;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean jumpAble(int distance) {
            if (distance <= this.heigth) {
                System.out.println(name + " перепрыгнул препятствие высотой " + distance + " метров!");
                return true;
            } else {
                System.out.println(name + " не смог перепрыгнуть препятствие высотой " + distance + " метров!");
                return false;
            }
        }

        @Override
        public boolean runAble(int distance) {
            if (distance <= this.length) {
                System.out.println(name + " пробежал дистанцию " + distance + " метров!");
                return true;
            } else {
                System.out.println(name + " не смог пробежать " + distance + " метров!");
                return false;
            }
        }

        public String toString() {
            return String.format("%s, имеет способность перепрыгивать препятствие высотой %d метров, " +
                    "и пробегать трассы длиной %d километров \n", name, heigth, length );
        }
    }

    class Human implements Runner {

        private String name;
        private int heigth;
        private int length;

        public Human(String name, int heigth, int length) {
            this.name = name;
            this.heigth = heigth;
            this.length = length;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean jumpAble(int distance) {
            if (distance <= this.heigth) {
                System.out.println(name + " перепрыгнул препятствие высотой " + distance + " метров!");
                return true;
            } else {
                System.out.println(name + " не смог перепрыгнуть препятствие высотой " + distance + " метров");
                return false;
            }
        }

        @Override
        public boolean runAble(int distance) {
            if (distance <= this.length) {
                System.out.println(name + " пробежал дистанцию " + distance + " метров");
                return true;
            } else {
                System.out.println(name + " не смог пробежать " + distance + " метров");
                return false;
            }
        }

        public String toString() {
            return String.format("%s, имеет способность перепрыгивать препятствие высотой %d метров, " +
                    "и пробегать трассы длиной %d километров!\n", name, heigth, length);
        }
    }

    class Cat implements Runner {

        private String name;
        private int heigth;
        private int length;

        public Cat(String name, int heigth, int length) {
            this.name = name;
            this.heigth = heigth;
            this.length = length;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean jumpAble(int distance) {
            if (distance <= this.heigth) {
                System.out.println(name + " перепрыгнул препятствие высотой " + distance + " метров!");
                return true;
            } else {
                System.out.println(name + " не смог перепрыгнуть препятствие высотой " + distance + " метров!");
                return false;
            }
        }

        @Override
        public boolean runAble(int distance) {
            if (distance <= this.length) {
                System.out.println(name + " пробежал дистанцию " + distance + " метров!");
                return true;
            } else {
                System.out.println(name + " не смог пробежать " + distance + " метров!");
                return false;
            }
        }

        public String toString() {
            return String.format("%s, имеет способность перепрыгивать препятствие высотой %d метров, " +
                    "и пробегать трассы длиной %d километров! \n", name, heigth, length);
        }
    }
