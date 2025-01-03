package backend.academy.gallows.game.session;

import backend.academy.gallows.dictionary.Dictionary;
import backend.academy.gallows.dictionary.Difficulty;
import backend.academy.gallows.dictionary.Theme;
import backend.academy.gallows.guessing.result.GuessingResult;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;

@Getter public final class GameSession {
    private static final String[] HANGMAN_STAGES = {
        "\n\n\n\n\n\n\n\n", "\n\n\n\n\n\n\n—————————", "\n\n   |\n   |\n   |\n   |\n   |\n   |\n—————————",
        "\n\n   | /\n   |/\n   |\n   |\n   |\n   |\n—————————",
        "\n———————————————\n   | /\n   |/\n   |\n   |\n   |\n   |\n—————————",
        "\n———————————————\n   | /       |\n   |/\n   |\n   |\n   |\n   |\n—————————",
        "\n———————————————\n   | /       |\n   |/        o\n   |\n   |\n   |\n   |\n—————————",
        "\n———————————————\n   | /       |\n   |/        o\n   |         O\n   |\n   |\n   |\n—————————",
        "\n———————————————\n   | /       |\n   |/        o\n   |        /O\\\n   |\n   |\n   |\n—————————",
        "\n———————————————\n   | /       |\n   |/        o\n   |        /O\\\n   |        / \\\n   |\n   |\n—————————"
    };
    private static final String ENTER_OPTION_PROMPT = "Введите номер варианта ответа без дополнительных символов: ";
    private static final String UNRECOGNIZED_RESPONSE_PROMPT = "\nОтвет не распознан. Введите число от ";

    private static final int MIN_ATTEMPTS = 1;
    private static final int MAX_ATTEMPTS = 9;

    private static final int COUNT_UNIQUE_OPTIONS = 3;
    private static final int FIRST_OPTION = 1;
    private static final int SECOND_OPTION = 2;
    private static final int THIRD_OPTION = 3;
    private static final int RANDOM_OPTION = 4;

    private static final int HINT_THRESHOLD = 4;

    private final Scanner input;
    private final PrintWriter output;
    private final SecureRandom random;

    private final Dictionary dictionary;
    private Difficulty difficulty;
    private Theme theme;
    private GuessingResult guessingResult;
    public int attemptsNum;
    int madeAttemptsNum;
    private String message;

    public GameSession(Scanner input, PrintWriter output) {
        this.dictionary = new Dictionary();
        this.input = input;
        this.output = output;
        this.random = new SecureRandom();
        this.attemptsNum = -1;
        this.madeAttemptsNum = 0;
    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);

        GameSession gameSession = new GameSession(input, output);
        gameSession.start();
    }

    public void start() {
        greeting();
        difficultyLevelChoosing();
        themeChoosing();
        attemptNumChoosing();
        initialization();
        game();
    }

    private void greeting() {
        messaging("Добро пожаловать в консольную \"Виселицу\"!\n\n");
        displayGameRules();
        messaging("\nУдачной игры!\n\n");
    }

    public void messaging(String message) {
        this.message = message;
        output.print(message);
        output.flush();
    }

    public void displayGameRules() {
        messaging("Знаете ли Вы правила игры?\n");
        messaging("1. Да;\n");
        messaging("2. Нет.\n\n");
        messaging(ENTER_OPTION_PROMPT);

        String strAnswer = input.nextLine();

        while (!List.of(String.valueOf(FIRST_OPTION), String.valueOf(SECOND_OPTION)).contains(strAnswer)) {
            messaging("\nОтвет не распознан. Введите \"1\" или \"2\" (без кавычек): ");
            strAnswer = input.nextLine();
        }

        if (strAnswer.equals(String.valueOf(SECOND_OPTION))) {
            explainGameRules();
        }
    }

    public void explainGameRules() {
        messaging("\nЭто игра, в которой игрок пытается угадать загаданное слово, вводя буквы по одной за раз.\n");
        messaging(
            "Вы можете выбрать уровень сложности, категорию слова, которое будете угадывать, и количество попыток.\n");
        messaging("За каждую неверную догадку визуализируется часть виселицы и фигурка висельника.\n");
    }

    public void difficultyLevelChoosing() {
        displayDifficultyOptions();
        setDifficulty(choosing());
    }

    void displayDifficultyOptions() {
        messaging("Выберите уровень сложности игры:\n");
        messaging("1. Простой;\n");
        messaging("2. Средний;\n");
        messaging("3. Сложный;\n");
        messaging("4. Рандомный.\n\n");
        messaging(ENTER_OPTION_PROMPT);
    }

    public int choosing() {
        String strAnswer = input.nextLine();
        while (!List.of(String.valueOf(FIRST_OPTION), String.valueOf(SECOND_OPTION), String.valueOf(THIRD_OPTION),
            String.valueOf(RANDOM_OPTION)).contains(strAnswer)) {
            messaging("\nОтвет не распознан. Введите \"1\", \"2\", \"3\" или \"4\" (без кавычек): ");
            strAnswer = input.nextLine();
        }

        int intAnswer = Integer.parseInt(strAnswer);
        if (intAnswer == RANDOM_OPTION) {
            intAnswer = MIN_ATTEMPTS + random.nextInt(COUNT_UNIQUE_OPTIONS);
        }
        return intAnswer;
    }

    public void setDifficulty(int number) {
        switch (number) {
            case FIRST_OPTION -> {
                difficulty = Difficulty.EASY;
                messaging("\nВыбранный уровень сложности: простой.\n\n");
            }
            case SECOND_OPTION -> {
                difficulty = Difficulty.MEDIUM;
                messaging("\nВыбранный уровень сложности: средний.\n\n");
            }
            default -> {
                difficulty = Difficulty.HARD;
                messaging("\nВыбранный уровень сложности: сложный.\n\n");
            }
        }
    }

    public void themeChoosing() {
        displayThemeOptions();
        setTheme(choosing());
    }

    private void displayThemeOptions() {
        messaging("Выберите тему:\n");
        messaging("1. Фрукты;\n");
        messaging("2. Ягоды;\n");
        messaging("3. Овощи;\n");
        messaging("4. Рандомная.\n\n");
        messaging(ENTER_OPTION_PROMPT);
    }

    public void setTheme(int number) {
        switch (number) {
            case FIRST_OPTION -> {
                theme = Theme.FRUITS;
                messaging("\nВыбранная тема: фрукты.\n\n");
            }
            case SECOND_OPTION -> {
                theme = Theme.BERRIES;
                messaging("\nВыбранная тема: ягоды.\n\n");
            }
            default -> {
                theme = Theme.VEGETABLES;
                messaging("\nВыбранная тема: овощи.\n\n");
            }
        }
    }

    public void attemptNumChoosing() {
        String toString = " до ";
        messaging("Выберите количество попыток - число от " + MIN_ATTEMPTS + toString + MAX_ATTEMPTS + ": ");

        while (attemptsNum < MIN_ATTEMPTS || attemptsNum > MAX_ATTEMPTS) {
            String strAnswer = input.nextLine();
            try {
                attemptsNum = Integer.parseInt(strAnswer);
                if (attemptsNum < MIN_ATTEMPTS || attemptsNum > MAX_ATTEMPTS) {
                    messaging(UNRECOGNIZED_RESPONSE_PROMPT + MIN_ATTEMPTS + toString + MAX_ATTEMPTS + ": ");
                }
            } catch (NumberFormatException e) {
                messaging(UNRECOGNIZED_RESPONSE_PROMPT + MIN_ATTEMPTS + toString + MAX_ATTEMPTS + ": ");
            }
        }
    }

    public void initialization() {
        guessingResult = new GuessingResult(dictionary.getRandom(difficulty, theme), attemptsNum, output);
    }

    public void game() {
        while (!guessingResult.isGameOver()) {
            displayGameStatus();
            char guessedLetter = getUserInput();
            guessingResult.updateResponse(guessedLetter);
            madeAttemptsNum = guessingResult.madeAttemptsNum();
        }
        displayGameOutcome();
    }

    private void displayGameStatus() {
        messaging("\nКоличество оставшихся попыток: " + (attemptsNum - madeAttemptsNum));
        displayHangman();
        messaging("\nСлово: ");
        guessingResult.displayResponse();
        if (attemptsNum - madeAttemptsNum < HINT_THRESHOLD) {
            messaging("Подсказка: " + guessingResult.hint() + "\n");
        }
    }

    void displayHangman() {
        messaging(HANGMAN_STAGES[(int) (MAX_ATTEMPTS * ((double) madeAttemptsNum / attemptsNum))]);
    }

    public char getUserInput() {
        messaging("\nВведите русскую букву в любом регистре или слово: ");
        String strAnswer = input.nextLine();

        while (!(strAnswer.length() == 1 && isCyrillicLetter(strAnswer.charAt(0)))) {
            messaging("\nВы должны ввести одну русскую букву в любом регистре: ");
            strAnswer = input.nextLine();
        }

        return Character.toLowerCase(strAnswer.charAt(0));
    }

    public boolean isCyrillicLetter(char c) {
        return (c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я') || c == 'ё' || c == 'Ё';
    }

    private void displayGameOutcome() {
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
