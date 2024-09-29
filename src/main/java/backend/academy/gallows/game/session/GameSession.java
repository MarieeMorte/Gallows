package backend.academy.gallows.game.session;

import backend.academy.gallows.dictionary.Dictionary;
import backend.academy.gallows.dictionary.Difficulties;
import backend.academy.gallows.dictionary.Themes;
import backend.academy.gallows.guessing.result.GuessingResult;
import java.util.Random;
import java.util.Scanner;

public class GameSession {
    private static final Scanner input = new Scanner(System.in);
    private static final Random random = new Random();
    private static Difficulties difficulty;
    private static Themes theme;
    private static int attemptsNum = -1;
    private static int madeAttemptsNum = 0;
    private static GuessingResult guessingResult;
    private static final int MIN_ATTEMPTS = 1;
    private static final int MAX_ATTEMPTS = 9;
    private static final String[] HANGMAN_STAGES = {
        "\n\n\n\n\n\n\n",
        "\n\n\n\n\n\n—————————",
        "\n   |\n   |\n   |\n   |\n   |\n   |\n—————————",
        "\n   | /\n   |/\n   |\n   |\n   |\n   |\n—————————",
        "———————————————\n   | /\n   |/\n   |\n   |\n   |\n   |\n—————————",
        "———————————————\n   | /       |\n   |/\n   |\n   |\n   |\n   |\n—————————",
        "———————————————\n   | /       |\n   |/        o\n   |\n   |\n   |\n   |\n—————————",
        "———————————————\n   | /       |\n   |/        o\n   |         O\n   |\n   |\n   |\n—————————",
        "———————————————\n   | /       |\n   |/        o\n   |        /O\\\n   |\n   |\n   |\n—————————",
        "———————————————\n   | /       |\n   |/        o\n   |        /O\\\n   |        / \\\n   |\n   |\n—————————"
    };

    public static void main(String[] args) {
        greeting();
        difficultyLevelChoosing();
        themeChoosing();
        attemptNumChoosing();
        initialization();
        game();
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
        System.out.println(
            "Вы можете выбрать уровень сложности, категорию слова, которое будете угадывать, и количество попыток.");
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

    private static void attemptNumChoosing() {
        System.out.print("\nВыберите количество попыток - число от " + MIN_ATTEMPTS + " до " + MAX_ATTEMPTS + ": ");

        while (attemptsNum < MIN_ATTEMPTS || attemptsNum > MAX_ATTEMPTS) {
            String strAnswer = input.nextLine();
            try {
                attemptsNum = Integer.parseInt(strAnswer);
                if (attemptsNum < MIN_ATTEMPTS || attemptsNum > MAX_ATTEMPTS) {
                    System.out.print(
                        "\nОтвет не распознан. Введите число от " + MIN_ATTEMPTS + " до " + MAX_ATTEMPTS + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print(
                    "\nОтвет не распознан. Введите число от " + MIN_ATTEMPTS + " до " + MAX_ATTEMPTS + ": ");
            }
        }
    }

    private static void initialization() {
        guessingResult = new GuessingResult(Dictionary.getRandomWord(difficulty, theme), attemptsNum);
    }

    private static void game() {
        while (!guessingResult.isGameOver()) {
            displayGameStatus();
            char guessedLetter = getUserInput();
            guessingResult.updateResponse(guessedLetter);
            madeAttemptsNum = guessingResult.madeAttemptsNum();
        }
        displayGameOutcome();
    }

    private static void displayGameStatus() {
        System.out.println("\nКоличество оставшихся попыток: " + (attemptsNum - madeAttemptsNum));
        displayHangman();
        guessingResult.displayResponse();
    }

    private static void displayHangman() {
        System.out.println(HANGMAN_STAGES[(int) (MAX_ATTEMPTS * ((double) madeAttemptsNum / attemptsNum))]);
    }

    private static char getUserInput() {
        System.out.print("\nВведите русскую букву в любом регистре: ");
        String strAnswer = input.nextLine();

        while (strAnswer.length() != 1 || !isCyrillicLetter(strAnswer.charAt(0))) {
            System.out.print("\nВы должны ввести одну русскую букву в любом регистре: ");
            strAnswer = input.nextLine();
        }

        return Character.toLowerCase(strAnswer.charAt(0));
    }

    private static boolean isCyrillicLetter(char c) {
        return (c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я') || c == 'ё' || c == 'Ё';
    }

    private static void displayGameOutcome() {
        if (guessingResult.isGameWin()) {
            System.out.print("\nВы выиграли! Загаданное слово: ");
            guessingResult.displayResponse();
        } else {
            System.out.println("\nВы проиграли!");
            displayHangman();
            System.out.println("Загаданное слово: " + guessingResult.word());
        }
    }
}
