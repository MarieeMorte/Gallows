package backend.academy.gallows.game.session;

import backend.academy.gallows.dictionary.Difficulty;
import backend.academy.gallows.dictionary.Theme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.Stream;

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

    static Stream<Arguments> provideOptions() {
        return Stream.of(
            Arguments.of(FIRST_OPTION),
            Arguments.of(SECOND_OPTION),
            Arguments.of(THIRD_OPTION),
            Arguments.of(RANDOM_OPTION)
        );
    }

    @ParameterizedTest
    @MethodSource("provideOptions")
    void givenOption_whenChoosing_thenReturnsCorrectOption(int option) {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(option));

        // Act
        int result = gameSession.choosing();

        // Assert
        if (option == RANDOM_OPTION) {
            assertThat(result).isIn(FIRST_OPTION, SECOND_OPTION, THIRD_OPTION);
        } else {
            assertThat(result).isEqualTo(option);
        }
    }

    @Test
    void givenMultipleInvalidInputs_whenChoosing_thenPromptsUntilValidInput() {
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

    static Stream<Arguments> provideDifficultyOptions() {
        return Stream.of(
            Arguments.of(FIRST_OPTION, Difficulty.EASY),
            Arguments.of(SECOND_OPTION, Difficulty.MEDIUM),
            Arguments.of(THIRD_OPTION, Difficulty.HARD)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDifficultyOptions")
    void givenDifficultySelection_whenChoosingDifficulty_thenSetsDifficultyCorrectly(
        int option,
        Difficulty expectedDifficulty
    ) {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(option));

        // Act
        gameSession.difficultyLevelChoosing();

        // Assert
        assertThat(gameSession.difficulty()).isEqualTo(expectedDifficulty);
    }

    static Stream<Arguments> provideThemeOptions() {
        return Stream.of(
            Arguments.of(FIRST_OPTION, Theme.FRUITS),
            Arguments.of(SECOND_OPTION, Theme.BERRIES),
            Arguments.of(THIRD_OPTION, Theme.VEGETABLES)
        );
    }

    @ParameterizedTest
    @MethodSource("provideThemeOptions")
    void givenThemeSelection_whenChoosingTheme_thenSetsThemeCorrectly(int option, Theme expectedTheme) {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn(String.valueOf(option));

        // Act
        gameSession.themeChoosing();

        // Assert
        assertThat(gameSession.theme()).isEqualTo(expectedTheme);
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
