package backend.academy.gallows.game.session;

import backend.academy.gallows.dictionary.Dictionary;
import backend.academy.gallows.dictionary.Difficulties;
import backend.academy.gallows.dictionary.Themes;
import backend.academy.gallows.guessing.result.GuessingResult;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import lombok.Getter;

public final class GameSession {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static final PrintWriter OUTPUT = new PrintWriter(System.out);

    private static final String[] HANGMAN_STAGES = {
        "\n\n\n\n\n\n\n\n",
        "\n\n\n\n\n\n\n—————————",
        "\n\n   |\n   |\n   |\n   |\n   |\n   |\n—————————",
        "\n\n   | /\n   |/\n   |\n   |\n   |\n   |\n—————————",
        "\n———————————————\n   | /\n   |/\n   |\n   |\n   |\n   |\n—————————",
        "\n———————————————\n   | /       |\n   |/\n   |\n   |\n   |\n   |\n—————————",
        "\n———————————————\n   | /       |\n   |/        o\n   |\n   |\n   |\n   |\n—————————",
        "\n———————————————\n   | /       |\n   |/        o\n   |         O\n   |\n   |\n   |\n—————————",
        "\n———————————————\n   | /       |\n   |/        o\n   |        /O\\\n   |\n   |\n   |\n—————————",
        "\n———————————————\n   | /       |\n   |/        o\n   |        /O\\\n   |        / \\\n   |\n   |\n—————————"
    };

    private static final String ENTER_OPTION_PROMPT = "Введите номер варианта ответа без дополнительных символов: ";
    private static final String TO_STRING = " до ";
    private static final String UNRECOGNIZED_RESPONSE_PROMPT = "\nОтвет не распознан. Введите число от ";

    private static final int MIN_ATTEMPTS = 1;
    private static final int MAX_ATTEMPTS = 9;

    private static final int FIRST_OPTION = 1;
    private static final int SECOND_OPTION = 2;
    private static final int COUNT_OPTIONS = 3;
    private static final int RANDOM_OPTION = 4;

    private static final int HINT_THRESHOLD = 4;

    @Getter private static Difficulties difficulty;
    @Getter private static Themes theme;
    @Getter private static GuessingResult guessingResult;

    @Getter private static int attemptsNum = -1;
    @Getter private static int madeAttemptsNum = 0;

    @Getter static String message;

    private GameSession() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Главный метод для запуска игры "Виселица".
     * Этот метод инициализирует игру, запрашивает настройки у игрока и запускает игровой процесс.
     */
    @SuppressWarnings("UncommentedMain")
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
        messaging("Добро пожаловать в консольную \"Виселицу\"!\n\n");
        displayGameRules();
        messaging("\nУдачной игры!\n\n");
    }

    static void messaging(String message) {
        GameSession.message = message;
        OUTPUT.print(message);
        OUTPUT.flush();
    }

    static void displayGameRules() {
        messaging("Знаете ли Вы правила игры?\n");
        messaging("1. Да;\n");
        messaging("2. Нет.\n\n");
        messaging(ENTER_OPTION_PROMPT);

        String strAnswer = INPUT.nextLine();

        while (!strAnswer.equals("1") && !strAnswer.equals("2")) {
            messaging("\nОтвет не распознан. Введите \"1\" или \"2\" (без кавычек): ");
            strAnswer = INPUT.nextLine();
        }

        if (strAnswer.equals("2")) {
            explainGameRules();
        }
    }

    private static void explainGameRules() {
        messaging("\nЭто игра, в которой игрок пытается угадать загаданное слово, вводя буквы по одной за раз.\n");
        messaging(
            "Вы можете выбрать уровень сложности, категорию слова, которое будете угадывать, и количество попыток.\n");
        messaging("За каждую неверную догадку визуализируется часть виселицы и фигурки висельника.\n");
    }

    static void difficultyLevelChoosing() {
        displayDifficultyOptions();
        setDifficulty(choosing());
    }

    private static void displayDifficultyOptions() {
        messaging("Выберите уровень сложности игры:\n");
        messaging("1. Простой;\n");
        messaging("2. Средний;\n");
        messaging("3. Сложный;\n");
        messaging("4. Рандомный.\n\n");
        messaging(ENTER_OPTION_PROMPT);
    }

    static int choosing() {
        String strAnswer = INPUT.nextLine();
        while (!strAnswer.equals("1") && !strAnswer.equals("2") && !strAnswer.equals("3") && !strAnswer.equals("4")) {
            messaging("\nОтвет не распознан. Введите \"1\", \"2\", \"3\" или \"4\" (без кавычек): ");
            strAnswer = INPUT.nextLine();
        }

        int intAnswer = Integer.parseInt(strAnswer);
        if (intAnswer == RANDOM_OPTION) {
            intAnswer = 1 + RANDOM.nextInt(COUNT_OPTIONS);
        }
        return intAnswer;
    }

    static void setDifficulty(int number) {
        switch (number) {
            case FIRST_OPTION -> {
                difficulty = Difficulties.EASY;
                messaging("\nВыбранный уровень сложности: простой.\n\n");
            }
            case SECOND_OPTION -> {
                difficulty = Difficulties.MEDIUM;
                messaging("\nВыбранный уровень сложности: средний.\n\n");
            }
            default -> {
                difficulty = Difficulties.HARD;
                messaging("\nВыбранный уровень сложности: сложный.\n\n");
            }
        }
    }

    static void themeChoosing() {
        displayThemeOptions();
        setTheme(choosing());
    }

    private static void displayThemeOptions() {
        messaging("Выберите тему:\n");
        messaging("1. Фрукты;\n");
        messaging("2. Овощи;\n");
        messaging("3. Ягоды;\n");
        messaging("4. Рандомная.\n\n");
        messaging(ENTER_OPTION_PROMPT);
    }

    static void setTheme(int number) {
        switch (number) {
            case FIRST_OPTION -> {
                theme = Themes.FRUITS;
                messaging("\nВыбранная тема: фрукты.\n\n");
            }
            case SECOND_OPTION -> {
                theme = Themes.BERRIES;
                messaging("\nВыбранная тема: ягоды.\n\n");
            }
            default -> {
                theme = Themes.VEGETABLES;
                messaging("\nВыбранная тема: овощи.\n\n");
            }
        }
    }

    static void attemptNumChoosing() {
        messaging("Выберите количество попыток - число от " + MIN_ATTEMPTS + TO_STRING + MAX_ATTEMPTS + ": ");

        while (attemptsNum < MIN_ATTEMPTS || attemptsNum > MAX_ATTEMPTS) {
            String strAnswer = INPUT.nextLine();
            try {
                attemptsNum = Integer.parseInt(strAnswer);
                if (attemptsNum < MIN_ATTEMPTS || attemptsNum > MAX_ATTEMPTS) {
                    messaging(UNRECOGNIZED_RESPONSE_PROMPT + MIN_ATTEMPTS + TO_STRING + MAX_ATTEMPTS + ": ");
                }
            } catch (NumberFormatException e) {
                messaging(UNRECOGNIZED_RESPONSE_PROMPT + MIN_ATTEMPTS + TO_STRING + MAX_ATTEMPTS + ": ");
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
        messaging("\nКоличество оставшихся попыток: " + (attemptsNum - madeAttemptsNum));
        displayHangman();
        messaging("\nСлово: ");
        guessingResult.displayResponse();
        if (attemptsNum - madeAttemptsNum < HINT_THRESHOLD) {
            messaging("Подсказка: " + guessingResult.hint() + "\n");
        }
    }

    private static void displayHangman() {
        messaging(HANGMAN_STAGES[(int) (MAX_ATTEMPTS * ((double) madeAttemptsNum / attemptsNum))]);
    }

    static char getUserInput() {
        messaging("\nВведите русскую букву в любом регистре или слово: ");
        String strAnswer = INPUT.nextLine();

        while (strAnswer.length() != 1 || !isCyrillicLetter(strAnswer.charAt(0))) {
            messaging("\nВы должны ввести одну русскую букву в любом регистре: ");
            strAnswer = INPUT.nextLine();
        }

        return Character.toLowerCase(strAnswer.charAt(0));
    }

    static boolean isCyrillicLetter(char c) {
        return (c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я') || c == 'ё' || c == 'Ё';
    }

    private static void displayGameOutcome() {
        if (guessingResult.isGameWin()) {
            messaging("\nВы выиграли! Загаданное слово: ");
            guessingResult.displayResponse();
        } else {
            messaging("\nВы проиграли!");
            displayHangman();
            messaging("\nЗагаданное слово: " + guessingResult.word());
        }
    }
}
