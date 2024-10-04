package backend.academy.gallows.game.session;

import backend.academy.gallows.dictionary.Dictionary;
import backend.academy.gallows.dictionary.Difficulties;
import backend.academy.gallows.dictionary.Themes;
import backend.academy.gallows.guessing.result.GuessingResult;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import lombok.Getter;

@Getter public final class GameSession {
    private final String[] hangmanStages =
        {"\n\n\n\n\n\n\n\n", "\n\n\n\n\n\n\n—————————", "\n\n   |\n   |\n   |\n   |\n   |\n   |\n—————————",
            "\n\n   | /\n   |/\n   |\n   |\n   |\n   |\n—————————",
            "\n———————————————\n   | /\n   |/\n   |\n   |\n   |\n   |\n—————————",
            "\n———————————————\n   | /       |\n   |/\n   |\n   |\n   |\n   |\n—————————",
            "\n———————————————\n   | /       |\n   |/        o\n   |\n   |\n   |\n   |\n—————————",
            "\n———————————————\n   | /       |\n   |/        o\n   |         O\n   |\n   |\n   |\n—————————",
            "\n———————————————\n   | /       |\n   |/        o\n   |        /O\\\n   |\n   |\n   |\n—————————",
            "\n———————————————\n   | /       |\n   |/        o\n   |        /O\\\n   |        / \\\n   |\n   |\n—————————"};
    private final String enterOptionPrompt = "Введите номер варианта ответа без дополнительных символов: ";
    private final String unrecognizedResponsePrompt = "\nОтвет не распознан. Введите число от ";
    private final int minAttempts = 1;
    private final int maxAttempts = 9;
    private final int firstOption = 1;
    private final int secondOption = 2;

    private final Scanner input;
    private final PrintWriter output;
    private final Random random;

    private final Dictionary dictionary;
    private Difficulties difficulty;
    private Themes theme;
    private GuessingResult guessingResult;
    private int attemptsNum;
    private int madeAttemptsNum;
    private String message;

    public GameSession(Scanner input, PrintWriter output) {
        this.dictionary = new Dictionary();
        this.input = input;
        this.output = output;
        this.random = new Random();
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
        messaging(enterOptionPrompt);

        String strAnswer = input.nextLine();

        while (!strAnswer.equals("1") && !strAnswer.equals("2")) {
            messaging("\nОтвет не распознан. Введите \"1\" или \"2\" (без кавычек): ");
            strAnswer = input.nextLine();
        }

        if (strAnswer.equals("2")) {
            explainGameRules();
        }
    }

    public void explainGameRules() {
        messaging("\nЭто игра, в которой игрок пытается угадать загаданное слово, вводя буквы по одной за раз.\n");
        messaging(
            "Вы можете выбрать уровень сложности, категорию слова, которое будете угадывать, и количество попыток.\n");
        messaging("За каждую неверную догадку визуализируется часть виселицы и фигурки висельника.\n");
    }

    public void difficultyLevelChoosing() {
        displayDifficultyOptions();
        setDifficulty(choosing());
    }

    private void displayDifficultyOptions() {
        messaging("Выберите уровень сложности игры:\n");
        messaging("1. Простой;\n");
        messaging("2. Средний;\n");
        messaging("3. Сложный;\n");
        messaging("4. Рандомный.\n\n");
        messaging(enterOptionPrompt);
    }

    public int choosing() {
        String strAnswer = input.nextLine();
        while (!strAnswer.equals("1") && !strAnswer.equals("2") && !strAnswer.equals("3") && !strAnswer.equals("4")) {
            messaging("\nОтвет не распознан. Введите \"1\", \"2\", \"3\" или \"4\" (без кавычек): ");
            strAnswer = input.nextLine();
        }

        int intAnswer = Integer.parseInt(strAnswer);
        int randomOption = 4;
        if (intAnswer == randomOption) {
            int countOptions = 3;
            intAnswer = 1 + random.nextInt(countOptions);
        }
        return intAnswer;
    }

    public void setDifficulty(int number) {
        switch (number) {
            case firstOption -> {
                difficulty = Difficulties.EASY;
                messaging("\nВыбранный уровень сложности: простой.\n\n");
            }
            case secondOption -> {
                difficulty = Difficulties.MEDIUM;
                messaging("\nВыбранный уровень сложности: средний.\n\n");
            }
            default -> {
                difficulty = Difficulties.HARD;
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
        messaging("2. Овощи;\n");
        messaging("3. Ягоды;\n");
        messaging("4. Рандомная.\n\n");
        messaging(enterOptionPrompt);
    }

    public void setTheme(int number) {
        switch (number) {
            case firstOption -> {
                theme = Themes.FRUITS;
                messaging("\nВыбранная тема: фрукты.\n\n");
            }
            case secondOption -> {
                theme = Themes.BERRIES;
                messaging("\nВыбранная тема: ягоды.\n\n");
            }
            default -> {
                theme = Themes.VEGETABLES;
                messaging("\nВыбранная тема: овощи.\n\n");
            }
        }
    }

    public void attemptNumChoosing() {
        String toString = " до ";
        messaging("Выберите количество попыток - число от " + minAttempts + toString + maxAttempts + ": ");

        while (attemptsNum < minAttempts || attemptsNum > maxAttempts) {
            String strAnswer = input.nextLine();
            try {
                attemptsNum = Integer.parseInt(strAnswer);
                if (attemptsNum < minAttempts || attemptsNum > maxAttempts) {
                    messaging(unrecognizedResponsePrompt + minAttempts + toString + maxAttempts + ": ");
                }
            } catch (NumberFormatException e) {
                messaging(unrecognizedResponsePrompt + minAttempts + toString + maxAttempts + ": ");
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
        int hintThreshold = 4;
        if (attemptsNum - madeAttemptsNum < hintThreshold) {
            messaging("Подсказка: " + guessingResult.hint() + "\n");
        }
    }

    private void displayHangman() {
        messaging(hangmanStages[(int) (maxAttempts * ((double) madeAttemptsNum / attemptsNum))]);
    }

    public char getUserInput() {
        messaging("\nВведите русскую букву в любом регистре или слово: ");
        String strAnswer = input.nextLine();

        while (strAnswer.length() != 1 || !isCyrillicLetter(strAnswer.charAt(0))) {
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
