package backend.academy.gallows.guessing.result;

import backend.academy.gallows.dictionary.WordWithHint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GuessingResultTest {
    private static final WordWithHint BANANA_WITH_HINT =
        new WordWithHint("банан", "жёлтый тропический фрукт, который любят обезьяны.");
    private static GuessingResult guessingResult;

    @BeforeEach
    void setUp() {
        // Arrange
        guessingResult = new GuessingResult(BANANA_WITH_HINT, 6);
    }

    @Test
    void expectInitialWordToBeBanana_whenGuessingResultIsCreated() {
        // Act
        String word = guessingResult.word();

        // Assert
        assertThat(word).isNotNull();
        assertThat(word).isEqualTo("банан");
    }

    @Test
    void expectInitialHintToBeCorrect_whenGuessingResultIsCreated() {
        // Act
        String hint = guessingResult.hint();

        // Assert
        assertThat(hint).isNotNull();
        assertThat(hint).isEqualTo("жёлтый тропический фрукт, который любят обезьяны.");
    }

    @Test
    void expectInitialResponseToBeUnderscores_whenGuessingResultIsCreated() {
        // Act
        char[] response = guessingResult.response();

        // Assert
        assertThat(response).isNotNull();
        assertThat(response).isNotEmpty();
        assertThat(response).hasSize(5);
        assertThat(response).containsExactly('_', '_', '_', '_', '_');
    }

    @Test
    void expectDisplayResponseToBeCorrect_whenDisplayIsCalled() {
        // Act
        guessingResult.displayResponse();
        String message = guessingResult.message();

        // Assert
        assertThat(message).isNotNull();
        assertThat(message).hasSize(5);
        assertThat(message).isEqualTo("_____");
    }

    @Test
    void expectInitialAttemptsNumToBeCorrect_whenGuessingResultIsCreated() {
        // Act, Assert
        assertThat(guessingResult.attemptsNum()).isEqualTo(6);
    }

    @Test
    void expectInitialMadeAttemptsNumToBeZero_whenGuessingResultIsCreated() {
        // Act, Assert
        assertThat(guessingResult.madeAttemptsNum()).isEqualTo(0);
    }

    @Test
    void expectUpdateAndDisplayResponseToShowCorrectLetters_whenCorrectLetterIsGuessed() {
        // Act
        guessingResult.updateResponse('а');
        guessingResult.displayResponse();
        String message = guessingResult.message();

        // Assert
        assertThat(guessingResult.response()).containsExactly('_', 'а', '_', 'а', '_');
        assertThat(message).isEqualTo("_а_а_");
        assertThat(guessingResult.madeAttemptsNum()).isEqualTo(0);
    }

    @Test
    void expectUpdateMadeAttemptsNum_whenIncorrectLetterIsGuessed() {
        // Act
        guessingResult.updateResponse('в');
        guessingResult.displayResponse();
        String message = guessingResult.message();

        // Assert
        assertThat(guessingResult.response()).containsExactly('_', '_', '_', '_', '_');
        assertThat(message).isEqualTo("_____");
        assertThat(guessingResult.madeAttemptsNum()).isEqualTo(1);
    }

    @Test
    void expectGameToBeWon_whenAllLettersAreGuessed() {
        // Arrange
        guessingResult.updateResponse('а');
        guessingResult.updateResponse('б');
        guessingResult.updateResponse('н');

        // Act
        boolean isWin = guessingResult.isGameWin();

        // Assert
        assertThat(isWin).isTrue();
    }

    @Test
    void expectGameToNotBeWon_whenNotAllLettersAreGuessed() {
        // Arrange
        guessingResult.updateResponse('в');
        guessingResult.updateResponse('г');
        guessingResult.updateResponse('д');
        guessingResult.updateResponse('е');
        guessingResult.updateResponse('ё');
        guessingResult.updateResponse('ж');

        // Act
        boolean isWin = guessingResult.isGameWin();

        // Assert
        assertThat(isWin).isFalse();
    }

    @Test
    void expectGameToBeLost_whenMaxAttemptsReached() {
        // Arrange
        guessingResult.updateResponse('в');
        guessingResult.updateResponse('г');
        guessingResult.updateResponse('д');
        guessingResult.updateResponse('е');
        guessingResult.updateResponse('ё');
        guessingResult.updateResponse('ж');

        // Act
        boolean isLoss = guessingResult.isGameLoss();

        // Assert
        assertThat(isLoss).isTrue();
    }

    @Test
    void expectGameToNotBeLost_whenAttemptsAreStillAvailable() {
        // Arrange
        guessingResult.updateResponse('а');
        guessingResult.updateResponse('б');
        guessingResult.updateResponse('н');

        // Act
        boolean isLoss = guessingResult.isGameLoss();

        // Assert
        assertThat(isLoss).isFalse();
    }

    @Test
    void expectGameToBeOver_whenGameIsWon() {
        // Arrange
        guessingResult.updateResponse('б');
        guessingResult.updateResponse('а');
        guessingResult.updateResponse('н');

        // Act
        boolean isOver = guessingResult.isGameOver();

        // Assert
        assertThat(isOver).isTrue();
    }

    @Test
    void expectGameToBeOver_whenGameIsLost() {
        /// Arrange
        guessingResult.updateResponse('в');
        guessingResult.updateResponse('г');
        guessingResult.updateResponse('д');
        guessingResult.updateResponse('е');
        guessingResult.updateResponse('ё');
        guessingResult.updateResponse('ж');

        // Act
        boolean isOver = guessingResult.isGameOver();

        // Assert
        assertThat(isOver).isTrue();
    }

    @Test
    void expectGameToNotBeOver_whenAttemptsRemaining() {
        // Arrange
        guessingResult.updateResponse('а');
        guessingResult.updateResponse('б');
        guessingResult.updateResponse('в');
        guessingResult.updateResponse('г');
        guessingResult.updateResponse('д');
        guessingResult.updateResponse('е');

        // Act
        boolean isOver = guessingResult.isGameOver();

        // Assert
        assertThat(isOver).isFalse();
    }
}
