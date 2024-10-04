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
    private Scanner mockInput;
    private GameSession gameSession;

    @BeforeEach
    void setUp() {
        mockInput = Mockito.mock(Scanner.class);
        PrintWriter mockOutput = Mockito.mock(PrintWriter.class);
        gameSession = new GameSession(mockInput, mockOutput);
    }

    @Test
    void givenEasyDifficultySelection_whenChoosingDifficulty_thenSetsDifficultyCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("1");

        // Act
        gameSession.difficultyLevelChoosing();

        // Assert
        assertThat(gameSession.difficulty()).isEqualTo(Difficulties.EASY);
    }

    @Test
    void givenMediumDifficultySelection_whenChoosingDifficulty_thenSetsDifficultyCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("2");

        // Act
        gameSession.difficultyLevelChoosing();

        // Assert
        assertThat(gameSession.difficulty()).isEqualTo(Difficulties.MEDIUM);
    }

    @Test
    void givenHardDifficultySelection_whenChoosingDifficulty_thenSetsDifficultyCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("3");

        // Act
        gameSession.difficultyLevelChoosing();

        // Assert
        assertThat(gameSession.difficulty()).isEqualTo(Difficulties.HARD);
    }

    @Test
    void givenFruitThemeSelection_whenChoosingTheme_thenSetsThemeCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("1");

        // Act
        gameSession.themeChoosing();

        // Assert
        assertThat(gameSession.theme()).isEqualTo(Themes.FRUITS);
    }

    @Test
    void givenBerriesThemeSelection_whenChoosingTheme_thenSetsThemeCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("2");

        // Act
        gameSession.themeChoosing();

        // Assert
        assertThat(gameSession.theme()).isEqualTo(Themes.BERRIES);
    }

    @Test
    void givenVegetablesThemeSelection_whenChoosingTheme_thenSetsThemeCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("3");

        // Act
        gameSession.themeChoosing();

        // Assert
        assertThat(gameSession.theme()).isEqualTo(Themes.VEGETABLES);
    }

    @Test
    void givenValidAttempts_whenChoosingAttempts_thenSetsAttemptsCorrectly() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("5");

        // Act
        gameSession.attemptNumChoosing();

        // Assert
        assertThat(gameSession.attemptsNum()).isEqualTo(5);
    }

    @Test
    void givenOutOfRangeAttempts_whenChoosingAttempts_thenPromptsForCorrectInput() {
        // Arrange
        Mockito.when(mockInput.nextLine())
            .thenReturn("15")
            .thenReturn("2");

        // Act
        gameSession.attemptNumChoosing();

        // Assert
        assertThat(gameSession.attemptsNum()).isEqualTo(2);
    }

    @Test
    void givenValidInput_whenGetUserInput_thenReturnsLowercaseLetter() {
        // Arrange
        Mockito.when(mockInput.nextLine()).thenReturn("А");

        // Act
        char result = gameSession.getUserInput();

        // Assert
        assertThat(result).isEqualTo('а');
    }

    @Test
    void givenInvalidInputMoreThanOneCharacter_whenGetUserInput_thenPromptsForCorrectInput() {
        // Arrange
        Mockito.when(mockInput.nextLine())
            .thenReturn("аб")
            .thenReturn("А");

        // Act
        char result = gameSession.getUserInput();

        // Assert
        assertThat(result).isEqualTo('а');
    }

    @Test
    void givenCorrectWordLength_whenInitialization_thenGameStarts() {
        // Arrange
        gameSession.setDifficulty(1);
        gameSession.setTheme(1);
        gameSession.attemptsNum = 5;
        gameSession.initialization();

        // Assert
        assertThat(gameSession.guessingResult().word()).isNotEmpty();
    }
}
