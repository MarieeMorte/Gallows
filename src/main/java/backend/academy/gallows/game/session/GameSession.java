package backend.academy.gallows.game.session;

import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import backend.academy.gallows.dictionary.Dictionary;
import backend.academy.gallows.dictionary.Difficulties;
import backend.academy.gallows.dictionary.Themes;
import backend.academy.gallows.guessing.result.GuessingResult;

public final class GameSession {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final Logger LOGGER = Logger.getLogger(GameSession.class.getName());
    private static final Random RANDOM = new Random();
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
    private static final String ENTER_OPTION_PROMPT = "\nВведите номер варианта ответа без дополнительных символов: ";
    @Getter private static Difficulties difficulty;
    @Getter private static Themes theme;
    @Getter private static int attemptsNum = -1;
    @Getter private static int madeAttemptsNum = 0;
    @Getter private static GuessingResult guessingResult;

    private GameSession() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void main(String[] args) {
        // Запуск игры "Виселица"

        // Приветствие игрока и сообщение о начале игры
        greeting();

        // Выбор уровня сложности игры
        difficultyLevelChoosing();

        // Выбор темы для угадывания
        themeChoosing();

        // Выбор количества попыток для угадывания
        attemptNumChoosing();

        // Инициализация необходимых переменных и состояния игры
        initialization();

        // Запуск игрового процесса
        game();
    }

    static void greeting() {
        LOGGER.log(Level.INFO, "Добро пожаловать в консольную \"Виселицу\"!");
        displayGameRules();
        LOGGER.log(Level.INFO, "\nУдачной игры!");
    }

    static void displayGameRules() {
        LOGGER.log(Level.INFO, "\nЗнаете ли Вы правила игры?");
        LOGGER.log(Level.INFO, "1. Да;");
        LOGGER.log(Level.INFO, "2. Нет.");
        LOGGER.log(Level.INFO, ENTER_OPTION_PROMPT);

        String strAnswer = INPUT.nextLine();

        while (!strAnswer.equals("1") && !strAnswer.equals("2")) {
            LOGGER.log(Level.WARNING, "\nОтвет не распознан. Введите \"1\" или \"2\" (без кавычек): ");
            strAnswer = INPUT.nextLine();
        }

        if (strAnswer.equals("2")) {
            explainGameRules();
        }
    }

    private static void explainGameRules() {
        LOGGER.log(Level.INFO,
            "\nЭто игра, в которой игрок пытается угадать загаданное слово, вводя буквы по одной за раз.");
        LOGGER.log(Level.INFO,
            "Вы можете выбрать уровень сложности, категорию слова, которое будете угадывать, и количество попыток.");
        LOGGER.log(Level.INFO, "За каждую неверную догадку визуализируется часть виселицы и фигурки висельника.");
    }

    static void difficultyLevelChoosing() {
        displayDifficultyOptions();
        setDifficulty(choosing());
    }

    private static void displayDifficultyOptions() {
        LOGGER.log(Level.INFO, "\nВыберите уровень сложности игры:");
        LOGGER.log(Level.INFO, "1. Простой;");
        LOGGER.log(Level.INFO, "2. Средний;");
        LOGGER.log(Level.INFO, "3. Сложный;");
        LOGGER.log(Level.INFO, "4. Рандомный.");
        LOGGER.log(Level.INFO, ENTER_OPTION_PROMPT);
    }

    static int choosing() {
        String strAnswer = INPUT.nextLine();
        while (!strAnswer.equals("1") && !strAnswer.equals("2") && !strAnswer.equals("3") && !strAnswer.equals("4")) {
            LOGGER.log(Level.WARNING, "\nОтвет не распознан. Введите \"1\", \"2\", \"3\" или \"4\" (без кавычек): ");
            strAnswer = INPUT.nextLine();
        }

        int intAnswer = Integer.parseInt(strAnswer);
        if (intAnswer == 4) {
            intAnswer = 1 + RANDOM.nextInt(3);
        }
        return intAnswer;
    }

    static void setDifficulty(int number) {
        switch (number) {
            case 1 -> {
                difficulty = Difficulties.EASY;
                LOGGER.log(Level.INFO, "\nВыбранный уровень сложности: простой.");
            }
            case 2 -> {
                difficulty = Difficulties.MEDIUM;
                LOGGER.log(Level.INFO, "\nВыбранный уровень сложности: средний.");
            }
            default -> {
                difficulty = Difficulties.HARD;
                LOGGER.log(Level.INFO, "\nВыбранный уровень сложности: сложный.");
            }
        }
    }

    static void themeChoosing() {
        displayThemeOptions();
        setTheme(choosing());
    }

    private static void displayThemeOptions() {
        LOGGER.log(Level.INFO, "\nВыберите тему:");
        LOGGER.log(Level.INFO, "1. Фрукты;");
        LOGGER.log(Level.INFO, "2. Овощи;");
        LOGGER.log(Level.INFO, "3. Ягоды;");
        LOGGER.log(Level.INFO, "4. Рандомная.");
        LOGGER.log(Level.INFO, ENTER_OPTION_PROMPT);
    }

    static void setTheme(int number) {
        switch (number) {
            case 1 -> {
                theme = Themes.FRUITS;
                LOGGER.log(Level.INFO, "\nВыбранная тема: фрукты.");
            }
            case 2 -> {
                theme = Themes.BERRIES;
                LOGGER.log(Level.INFO, "\nВыбранная тема: ягоды.");
            }
            default -> {
                theme = Themes.VEGETABLES;
                LOGGER.log(Level.INFO, "\nВыбранная тема: овощи.");
            }
        }
    }

    static void attemptNumChoosing() {
        LOGGER.log(Level.INFO,
            "\nВыберите количество попыток - число от " + MIN_ATTEMPTS + " до " + MAX_ATTEMPTS + ": ");

        while (attemptsNum < MIN_ATTEMPTS || attemptsNum > MAX_ATTEMPTS) {
            String strAnswer = INPUT.nextLine();
            try {
                attemptsNum = Integer.parseInt(strAnswer);
                if (attemptsNum < MIN_ATTEMPTS || attemptsNum > MAX_ATTEMPTS) {
                    LOGGER.log(Level.WARNING,
                        "\nОтвет не распознан. Введите число от " + MIN_ATTEMPTS + " до " + MAX_ATTEMPTS + ": ");
                }
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING,
                    "\nОтвет не распознан. Введите число от " + MIN_ATTEMPTS + " до " + MAX_ATTEMPTS + ": ");
            }
        }
    }

    static void initialization() {
        guessingResult = new GuessingResult(Dictionary.getRandom(difficulty, theme), attemptsNum);
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
        LOGGER.log(Level.INFO, "\nКоличество оставшихся попыток: " + (attemptsNum - madeAttemptsNum));
        displayHangman();
        LOGGER.log(Level.INFO, "Слово: ");
        guessingResult.displayResponse();
        if (attemptsNum - madeAttemptsNum < 4) {
            LOGGER.log(Level.INFO, "Подсказка: " + guessingResult.hint());
        }
    }

    private static void displayHangman() {
        LOGGER.log(Level.INFO, HANGMAN_STAGES[(int) (MAX_ATTEMPTS * ((double) madeAttemptsNum / attemptsNum))]);
    }

    static char getUserInput() {
        LOGGER.log(Level.INFO, "\nВведите русскую букву в любом регистре или слово: ");
        String strAnswer = INPUT.nextLine();

        while (strAnswer.length() != 1 || !isCyrillicLetter(strAnswer.charAt(0))) {
            LOGGER.log(Level.WARNING, "\nВы должны ввести одну русскую букву в любом регистре: ");
            strAnswer = INPUT.nextLine();
        }

        return Character.toLowerCase(strAnswer.charAt(0));
    }

    static boolean isCyrillicLetter(char c) {
        return (c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я') || c == 'ё' || c == 'Ё';
    }

    private static void displayGameOutcome() {
        if (guessingResult.isGameWin()) {
            LOGGER.log(Level.INFO, "\nВы выиграли! Загаданное слово: ");
            guessingResult.displayResponse();
        } else {
            LOGGER.log(Level.INFO, "\nВы проиграли!");
            displayHangman();
            LOGGER.log(Level.INFO, "Загаданное слово: " + guessingResult.word());
        }
    }
}
