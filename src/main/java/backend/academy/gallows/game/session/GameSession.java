package backend.academy.gallows.game.session;

import backend.academy.gallows.dictionary.Dictionary;
import backend.academy.gallows.dictionary.Difficulties;
import backend.academy.gallows.dictionary.Themes;
import java.util.Random;
import java.util.Scanner;

public class GameSession {
    private static final Scanner input = new Scanner(System.in);
    private static final Random random = new Random();
    private static Difficulties difficulty;
    private static Themes theme;
    private static String word;

    public static void main(String[] args) {
        greeting();
        difficultyLevelChoosing();
        themeChoosing();
        word = Dictionary.getRandomWord(difficulty, theme);
    }

    private static void greeting() {
        System.out.println("Добро пожаловать в консольную \"Виселицу\"!");
        displayGameRules();
        System.out.println("\nУдачной игры!");
    }

    private static void displayGameRules() {
        System.out.println("\nЗнаете ли Вы правила игры?");
        System.out.println("1. Да;");
        System.out.println("2. Нет.");
        System.out.print("\nВведите номер варианта ответа без дополнительных символов: ");

        String strAnswer = input.nextLine();

        while (!strAnswer.equals("1") && !strAnswer.equals("2")) {
            System.out.print("\nОтвет не распознан. Введите \"1\" или \"2\" (без кавычек): ");
            strAnswer = input.nextLine();
        }

        if (strAnswer.equals("2")) {
            explainGameRules();
        }
    }

    private static void explainGameRules() {
        System.out.println(
            "\nЭто игра, в которой игрок пытается угадать загаданное слово, вводя буквы по одной за раз.");
        System.out.println("Вы можете выбрать уровень сложности и категорию слова, которое будете угадывать.");
        System.out.println("Количество попыток ограничено!");
        System.out.println("За каждую неверную догадку визуализируется часть виселицы и фигурки висельника.");
    }

    private static void difficultyLevelChoosing() {
        displayDifficultyOptions();
        setDifficulty(choosing());
    }

    private static void displayDifficultyOptions() {
        System.out.println("\nВыберите уровень сложности игры:");
        System.out.println("1. Простой;");
        System.out.println("2. Средний;");
        System.out.println("3. Сложный;");
        System.out.println("4. Рандомный.");
        System.out.print("\nВведите номер варианта ответа без дополнительных символов: ");
    }

    private static void setDifficulty(int number) {
        switch (number) {
            case 1 -> {
                difficulty = Difficulties.EASY;
                System.out.println("\nВыбранный уровень сложности: простой.");
            }
            case 2 -> {
                difficulty = Difficulties.MEDIUM;
                System.out.println("\nВыбранный уровень сложности: средний.");
            }
            default -> {
                difficulty = Difficulties.HARD;
                System.out.println("\nВыбранный уровень сложности: сложный.");
            }
        }
    }

    private static int choosing() {
        String strAnswer = input.nextLine();
        while (!strAnswer.equals("1") && !strAnswer.equals("2") && !strAnswer.equals("3") && !strAnswer.equals("4")) {
            System.out.print("\nОтвет не распознан. Введите \"1\", \"2\", \"3\" или \"4\" (без кавычек): ");
            strAnswer = input.nextLine();
        }

        int intAnswer = Integer.parseInt(strAnswer);
        if (intAnswer == 4) {
            intAnswer = 1 + random.nextInt(3);
        }
        return intAnswer;
    }

    private static void themeChoosing() {
        displayThemeOptions();
        setTheme(choosing());
    }

    private static void displayThemeOptions() {
        System.out.println("\nВыберите тему:");
        System.out.println("1. Фрукты;");
        System.out.println("2. Овощи;");
        System.out.println("3. Ягоды;");
        System.out.println("4. Рандомная.");
        System.out.print("\nВведите номер варианта ответа без дополнительных символов: ");
    }

    private static void setTheme(int number) {
        switch (number) {
            case 1 -> {
                theme = Themes.FRUITS;
                System.out.println("\nВыбранная тема: фрукты.");
            }
            case 2 -> {
                theme = Themes.BERRIES;
                System.out.println("\nВыбранная тема: ягоды.");
            }
            default -> {
                theme = Themes.VEGETABLES;
                System.out.println("\nВыбранная тема: овощи.");
            }
        }
    }
}
