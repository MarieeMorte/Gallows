package backend.academy.gallows.guessing.result;

import backend.academy.gallows.dictionary.WordWithHint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.assertj.core.api.Assertions.assertThat;

public class GuessingResultTest {
    private final WordWithHint BANANA_WITH_HINT =
        new WordWithHint("банан", "жёлтый тропический фрукт, который любят обезьяны.");
    private GuessingResult guessingResult;
    private StringWriter stringWriter;

    @BeforeEach
    void setUp() {
        // Arrange
        stringWriter = new StringWriter();
        guessingResult = new GuessingResult(BANANA_WITH_HINT, 6, new PrintWriter(stringWriter));
    }

    @Test
    void givenGuessingResultIsCreated_whenInitialWordIsRetrieved_thenExpectWordToBeBanana() {
        // Act
        String word = guessingResult.word();

        // Assert
        assertThat(word).isEqualTo("банан");
    }

    @Test
    void givenGuessingResultIsCreated_whenInitialHintIsRetrieved_thenExpectHintToBeCorrect() {
        // Act
        String hint = guessingResult.hint();

        // Assert
        assertThat(hint).isEqualTo("жёлтый тропический фрукт, который любят обезьяны.");
    }

    @Test
    void givenGuessingResultIsCreated_whenInitialResponseIsRetrieved_thenExpectResponseToBeUnderscores() {
        // Act
        char[] response = guessingResult.response();

        // Assert
        assertThat(response).hasSize(5);
        assertThat(response).containsExactly('_', '_', '_', '_', '_');
    }

    @Test
    void givenGuessingResultIsCreated_whenDisplayIsCalled_thenExpectDisplayResponseToBeCorrect() {
        // Act
        guessingResult.displayResponse();
        String message = stringWriter.toString().trim();

        // Assert
        assertThat(message).hasSize(5);
        assertThat(message).isEqualTo("_____");
    }

    @Test
    void givenGuessingResultIsCreated_whenInitialAttemptsNumIsRetrieved_thenExpectAttemptsNumToBeCorrect() {
        // Act, Assert
        assertThat(guessingResult.attemptsNum()).isEqualTo(6);
    }

    @Test
    void givenGuessingResultIsCreated_whenInitialMadeAttemptsNumIsRetrieved_thenExpectMadeAttemptsNumToBeZero() {
        // Act, Assert
        assertThat(guessingResult.madeAttemptsNum()).isEqualTo(0);
    }

    @Test
    void givenCorrectLetterIsGuessed_whenResponseIsUpdated_thenExpectCorrectLettersToBeShown() {
        // Act
        guessingResult.updateResponse('а');
        guessingResult.displayResponse();
        String message = stringWriter.toString().trim();

        // Assert
        assertThat(guessingResult.response()).containsExactly('_', 'а', '_', 'а', '_');
        assertThat(message).isEqualTo("_а_а_");
        assertThat(guessingResult.madeAttemptsNum()).isEqualTo(0);
    }

    @Test
    void givenIncorrectLetterIsGuessed_whenResponseIsUpdated_thenExpectMadeAttemptsNumToBeUpdated() {
        // Act
        guessingResult.updateResponse('в');
        guessingResult.displayResponse();
        String message = stringWriter.toString().trim(); // Получаем вывод

        // Assert
        assertThat(guessingResult.response()).containsExactly('_', '_', '_', '_', '_');
        assertThat(message).isEqualTo("_____");
        assertThat(guessingResult.madeAttemptsNum()).isEqualTo(1);
    }

    @Test
    void givenAllLettersAreGuessed_whenCheckingWinCondition_thenExpectGameToBeWon() {
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
    void givenNotAllLettersAreGuessed_whenCheckingWinCondition_thenExpectGameToNotBeWon() {
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
    void givenMaxAttemptsReached_whenCheckingLossCondition_thenExpectGameToBeLost() {
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
    void givenAttemptsAreStillAvailable_whenCheckingLossCondition_thenExpectGameToNotBeLost() {
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
    void givenGameIsWon_whenCheckingGameOverCondition_thenExpectGameToBeOver() {
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
    void givenGameIsLost_whenCheckingGameOverCondition_thenExpectGameToBeOver() {
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
    void givenAttemptsRemaining_whenCheckingGameOverCondition_thenExpectGameToNotBeOver() {
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
