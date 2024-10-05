package backend.academy.gallows.game.session;

import backend.academy.gallows.dictionary.Difficulties;
import backend.academy.gallows.dictionary.Themes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintWriter;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class GameSessionTest {
    private static final String ENTER_OPTION_PROMPT = "Введите номер варианта ответа без дополнительных символов: ";

    private static final int FIRST_OPTION = 1;
    private static final int SECOND_OPTION = 2;
    private static final int THIRD_OPTION = 3;
    private static final int RANDOM_OPTION = 4;

    private Scanner mockInput;
    private PrintWriter mockOutput;
    private GameSession gameSession;

    @BeforeEach void setUp() {
        // Arrange
        mockInput = Mockito.mock(Scanner.class);
        mockOutput = Mockito.mock(PrintWriter.class);
        gameSession = new GameSession(mockInput, mockOutput);
    }

    @Test void givenYesAnswer_whenDisplayGameRules_thenDoesNotExplainRules() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(FIRST_OPTION));

        // Act
        gameSession.displayGameRules();

        // Assert
        Mockito.verify(mockOutput).print("Знаете ли Вы правила игры?\n");
        Mockito.verify(mockOutput).print("1. Да;\n");
        Mockito.verify(mockOutput).print("2. Нет.\n\n");
        Mockito.verify(mockOutput).print(ENTER_OPTION_PROMPT);
        Mockito.verify(mockOutput, Mockito.never()).print(Mockito.contains("Введите \"1\" или \"2\""));
    }

    @Test void givenNoAnswer_whenDisplayGameRules_thenExplainsRules() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(SECOND_OPTION));

        // Act
        gameSession.displayGameRules();

        // Assert
        Mockito.verify(mockOutput).print("Знаете ли Вы правила игры?\n");
        Mockito.verify(mockOutput).print("1. Да;\n");
        Mockito.verify(mockOutput).print("2. Нет.\n\n");
        Mockito.verify(mockOutput).print(ENTER_OPTION_PROMPT);
        Mockito.verify(mockOutput)
            .print(Mockito.contains("Это игра, в которой игрок пытается угадать загаданное слово"));
    }

    @Test void givenMultipleInvalidAnswers_whenDisplayGameRules_thenPromptsUntilValidInput() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("abc").thenReturn("0").thenReturn("3")
            .thenReturn(String.valueOf(FIRST_OPTION));

        // Act
        gameSession.displayGameRules();

        // Assert
        Mockito.verify(mockOutput, Mockito.times(3))
            .print("\nОтвет не распознан. Введите \"1\" или \"2\" (без кавычек): ");
        Mockito.verify(mockOutput).print(ENTER_OPTION_PROMPT);
    }

    @Test void givenOne_whenChoosing_thenReturnsCorrectOption() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(FIRST_OPTION));

        // Act
        int result = gameSession.choosing();

        // Assert
        assertThat(result).isEqualTo(FIRST_OPTION);
    }

    @Test void givenTwo_whenChoosing_thenReturnsCorrectOption() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(SECOND_OPTION));

        // Act
        int result = gameSession.choosing();

        // Assert
        assertThat(result).isEqualTo(SECOND_OPTION);
    }

    @Test void givenThree_whenChoosing_thenReturnsCorrectOption() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(THIRD_OPTION));

        // Act
        int result = gameSession.choosing();

        // Assert
        assertThat(result).isEqualTo(THIRD_OPTION);
    }

    @Test void givenFour_whenChoosing_thenReturnsCorrectOption() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(RANDOM_OPTION));

        // Act
        int result = gameSession.choosing();

        // Assert
        assertThat(result).isIn(FIRST_OPTION, SECOND_OPTION, THIRD_OPTION);
    }

    @Test void givenMultipleInvalidInputs_whenChoosing_thenPromptsUntilValidInput() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("abc").thenReturn("0").thenReturn("5")
            .thenReturn(String.valueOf(FIRST_OPTION));

        // Act
        int result = gameSession.choosing();

        // Assert
        assertThat(result).isEqualTo(FIRST_OPTION);
        Mockito.verify(mockOutput, Mockito.times(3))
            .print("\nОтвет не распознан. Введите \"1\", \"2\", \"3\" или \"4\" (без кавычек): ");
    }

    @Test void givenEasyDifficultySelection_whenChoosingDifficulty_thenSetsDifficultyCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(FIRST_OPTION));

        // Act
        gameSession.difficultyLevelChoosing();

        // Assert
        assertThat(gameSession.difficulty()).isEqualTo(Difficulties.EASY);
    }

    @Test void givenMediumDifficultySelection_whenChoosingDifficulty_thenSetsDifficultyCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(SECOND_OPTION));

        // Act
        gameSession.difficultyLevelChoosing();

        // Assert
        assertThat(gameSession.difficulty()).isEqualTo(Difficulties.MEDIUM);
    }

    @Test void givenHardDifficultySelection_whenChoosingDifficulty_thenSetsDifficultyCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(THIRD_OPTION));

        // Act
        gameSession.difficultyLevelChoosing();

        // Assert
        assertThat(gameSession.difficulty()).isEqualTo(Difficulties.HARD);
    }

    @Test void givenFruitThemeSelection_whenChoosingTheme_thenSetsThemeCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(FIRST_OPTION));

        // Act
        gameSession.themeChoosing();

        // Assert
        assertThat(gameSession.theme()).isEqualTo(Themes.FRUITS);
    }

    @Test void givenBerriesThemeSelection_whenChoosingTheme_thenSetsThemeCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(SECOND_OPTION));

        // Act
        gameSession.themeChoosing();

        // Assert
        assertThat(gameSession.theme()).isEqualTo(Themes.BERRIES);
    }

    @Test void givenVegetablesThemeSelection_whenChoosingTheme_thenSetsThemeCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(THIRD_OPTION));

        // Act
        gameSession.themeChoosing();

        // Assert
        assertThat(gameSession.theme()).isEqualTo(Themes.VEGETABLES);
    }

    @Test void givenValidAttempts_whenChoosingAttempts_thenSetsAttemptsCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("6");

        // Act
        gameSession.attemptNumChoosing();

        // Assert
        assertThat(gameSession.attemptsNum()).isEqualTo(6);
    }

    @Test void givenMultipleInvalidAttempts_whenChoosingAttempts_thenPromptsUntilValidInput() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("abs").thenReturn("0").thenReturn("10").thenReturn("6");

        // Act
        gameSession.attemptNumChoosing();

        // Assert
        assertThat(gameSession.attemptsNum()).isEqualTo(6);
        Mockito.verify(mockOutput, Mockito.times(3)).print("\nОтвет не распознан. Введите число от 1 до 9: ");
    }

    @Test void givenValidInput_whenGetUserInput_thenReturnsLowercaseLetter() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("А");

        // Act
        char result = gameSession.getUserInput();

        // Assert
        assertThat(result).isEqualTo('а');
    }

    @Test
    void givenInvalidInput_whenGetUserInput_thenPromptsUntilValidInput() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("аб").thenReturn("1").thenReturn("w").thenReturn("а");

        // Act
        char result = gameSession.getUserInput();

        // Assert
        assertThat(result).isEqualTo('а');
        Mockito.verify(mockOutput, Mockito.times(3))
            .print("\nВы должны ввести одну русскую букву в любом регистре: ");
    }
}
