package backend.academy.gallows.guessing.result;

import backend.academy.gallows.dictionary.WordWithHint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GuessingResultTest {
    private static GuessingResult guessingResult;

    @BeforeEach
    void setUp() {
        // Arrange
        WordWithHint wordWithHint = new WordWithHint("банан", "жёлтый тропический фрукт, который любят обезьяны.");
        guessingResult = new GuessingResult(wordWithHint, 6);
    }

    @Test
    void testInitialResponseIsUnderscores() {
        // Act
        char[] response = guessingResult.response();

        // Assert
        assertThat(response).containsExactly('_', '_', '_', '_', '_');
    }

    @Test
    void testUpdateResponseWithCorrectLetter() {
        // Act
        guessingResult.updateResponse('а');
        char[] response = guessingResult.response();

        // Assert
        assertThat(response).containsExactly('_', 'а', '_', 'а', '_');
    }

    @Test
    void testUpdateResponseWithIncorrectLetter() {
        // Act
        guessingResult.updateResponse('к');

        // Assert
        assertThat(guessingResult.madeAttemptsNum()).isEqualTo(1);
    }

    @Test
    void testIsGameWin() {
        guessingResult.updateResponse('б');
        guessingResult.updateResponse('а');
        guessingResult.updateResponse('н');

        // Act
        boolean isWin = guessingResult.isGameWin();

        // Assert
        assertThat(isWin).isTrue();
    }

    @Test
    void testIsGameLoss() {
        // Arrange
        guessingResult.updateResponse('в'); // 1 попытка
        guessingResult.updateResponse('г'); // 2 попытка
        guessingResult.updateResponse('д'); // 3 попытка
        guessingResult.updateResponse('е'); // 4 попытка
        guessingResult.updateResponse('ё'); // 5 попытка
        guessingResult.updateResponse('ж'); // 6 попытка

        // Act
        boolean isLoss = guessingResult.isGameLoss();

        // Assert
        assertThat(isLoss).isTrue();
    }

    @Test
    void testIsGameOverWin() {
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
    void testIsGameOverLoss() {
        /// Arrange
        guessingResult.updateResponse('в'); // 1 попытка
        guessingResult.updateResponse('г'); // 2 попытка
        guessingResult.updateResponse('д'); // 3 попытка
        guessingResult.updateResponse('е'); // 4 попытка
        guessingResult.updateResponse('ё'); // 5 попытка
        guessingResult.updateResponse('ж'); // 6 попытка

        // Act
        boolean isOver = guessingResult.isGameOver();

        // Assert
        assertThat(isOver).isTrue();
    }

    @Test
    void testUpdateResponseIgnoresMultipleLetters() {
        // Act
        guessingResult.updateResponse('а'); // правильная буква
        guessingResult.updateResponse('к'); // неправильная буква
        guessingResult.updateResponse('т'); // неправильная буква
        guessingResult.updateResponse('к'); // повторная неправильная буква
        char[] response = guessingResult.response();

        // Assert
        assertThat(guessingResult.madeAttemptsNum()).isEqualTo(3);
        assertThat(response).containsExactly('_', 'а', '_', 'а', '_');
    }
}
