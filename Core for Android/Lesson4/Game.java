package newLesson4;

import java.util.Random;
import java.util.Scanner;

public class Game {

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    static final char dot_human = 'Օ';
    static final char dot_comp = 'X';
    static final char dot_empty = '•';
    static final Scanner scaner = new Scanner(System.in);
    static final Random random = new Random();
    static char[][] field;
    static int fieldSizeX = 3;
    static int fieldSizeY = 3;
    static int winCount = 3;

    static void initialize() {
        field = new char[fieldSizeX][fieldSizeY];
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                field[i][j] = dot_empty;
            }
        }
    }

    static void fieldPrint() {
// вывод в консоль шапки ( первой стоки)
        System.out.printf("%s*",CYAN_BRIGHT);
        for (int i = 0; i < fieldSizeX * 2; i++) {
            System.out.print(i % 2 == 0 ? " |" : (i + 1) / 2);
        }
        System.out.print(" |");
        System.out.println();
// вывод в консоль основного поля
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print((i + 1) + " |");
            for (int j = 0; j < fieldSizeY; j++) {
                System.out.print(field[i][j] + " |");
            }
            System.out.println();
        }
// вывод в консоль футера
        for (int i = 0; i < fieldSizeX * 2; i++) {
            System.out.printf("%s***", RESET);
        }
        System.out.println();
    }

    static void humanTurn() {
        int x, y;
        do {
            System.out.printf("%sВведите координаты хода через пробел (х и y):%s\n", YELLOW_BRIGHT, RESET);
            x = scaner.nextInt() - 1;
            y = scaner.nextInt() - 1;
        }
        while (!correctDot(x, y) || !emptyDot(x, y));

        field[x][y] = dot_human;
    }

    /**
     *проверка хода на корректность ввода
     */
    static boolean correctDot(int x, int y) {
        return (x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY);
    }

    static boolean emptyDot(int x, int y) {
        return field[x][y] == dot_empty;
    }

    /**
     * Ход компьютера
     */
    public static void compTurn() {
         for (int y = 0; y < fieldSizeX; y++) {
            for (int x = 0; x < fieldSizeY; x++) {
                if (field[y][x] == dot_empty){
                    field[y][x] = dot_comp;
                    if (checkWin(dot_comp, winCount))
                        return;
                    else
                        field[y][x] = dot_empty;
                }
            }
        }
         boolean f = checkWin(dot_human, winCount - 1);
        for (int y = 0; y < fieldSizeX; y++) {
            for (int x = 0; x < fieldSizeY; x++) {
                if (field[y][x] == dot_empty){
                    field[y][x] = dot_human;
                    if (checkWin(dot_human, winCount - (f ? 0 : 1))) {
                        field[y][x] = dot_comp;
                        return;
                    }
                    else
                        field[y][x] = dot_empty;
                }
            }
        }

        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!emptyDot(x, y));
        field[y][x] = dot_comp;
    }

    /**
     * Проверка победы по горизонтали и вертикали
     */
    static boolean checkXY(int x, int y, int dir, int win) {
        char c = field[x][y];
        /*  +-1-2-3-4-5-
            1|.|.|.|.|.|
            2|.|.|.|.|.|
            3|.|X|X|X|X|
            4|.|X|.|.|.|
            5|.|X|.|.|.|
            ------------
        */
        for (int i = 1; i < win; i++)
            if (dir > 0 && (!correctDot(x + i, y) || c != field[x + i][y])) return false;
            else if (dir < 0 && (!correctDot(x, y + i) || c != field[x][y + i])) return false;
        return true;
    }

    /**
     *Проверка победы по диагонлям
     */
    static boolean checkDiagonal(int x, int y, int dir, int win) {
        char c = field[x][y];
        /*  +-1-2-3-4-5-
            1|.|.|.|.|X|
            2|.|.|.|X|.|
            3|.|.|X|.|.|
            4|.|.|.|X|.|
            5|.|.|.|.|X|
            ------------
        */
        for (int i = 1; i < win; i++)
            if (!correctDot(x + i, y + i*dir) || c != field[x + i][y + i*dir]) return false;
        return true;
    }

    static boolean checkWin(char dot, int winCount) {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == dot)
                    if (checkXY(y, x, 1, winCount) ||
                            checkXY(y, x, -1, winCount) ||
                            checkDiagonal(y, x, -1, winCount) ||
                            checkDiagonal(y, x, 1, winCount))
                        return true;
            }
        }
        return false;
    }

    static boolean checkEvenScore() {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (emptyDot(i, j))
                    return false;
            }
        }
        return true;
    }

    static boolean checkState(char dot, String s) {
        if (checkWin(dot, winCount)) {
            System.out.println(s);
            return true;
        }
        if (checkEvenScore()) {
            System.out.printf("%sНичья!%s\n", GREEN_BRIGHT, RESET);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        while (true) {
            initialize();
            fieldPrint();
            while (true) {
                humanTurn();
                fieldPrint();
                if (checkState(dot_human, String.format("%sПоздравляем!!! Вы победили!%s", RED_BRIGHT, RESET)))
                    break;
                compTurn();
                fieldPrint();
                if (checkState(dot_comp, String.format("%sВы проиграли! Победил искуственный интеллект!%s", PURPLE_BRIGHT, RESET)))
                    break;

            }
            System.out.printf("%sХотите сыграть еще раз? (Y - да)%s\n", BLUE_BRIGHT, RESET);
            if (!scaner.next().equalsIgnoreCase("Y"))
                break;
        }

    }
}
