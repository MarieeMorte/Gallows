package backend.academy.gallows.guessing.result;

import backend.academy.gallows.dictionary.WordWithHint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.stream.Stream;
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

    static Stream<Arguments> provideGameStates() {
        return Stream.of(
            Arguments.of(new char[] {'а', 'б', 'н'}, true, false, true),
            Arguments.of(new char[] {'в', 'г', 'д', 'е', 'ё', 'ж'}, false, true, true),
            Arguments.of(new char[] {'а', 'б', 'в', 'г', 'д', 'е'}, false, false, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideGameStates")
    void givenLettersUpdated_whenCheckingGameConditions_thenExpectCorrectResults(
        char[] letters,
        boolean expectedWin,
        boolean expectedLoss,
        boolean expectedGameOver
    ) {
        // Arrange
        for (char letter : letters) {
            guessingResult.updateResponse(letter);
        }

        // Act & Assert
        assertThat(guessingResult.isGameWin()).isEqualTo(expectedWin);
        assertThat(guessingResult.isGameLoss()).isEqualTo(expectedLoss);
        assertThat(guessingResult.isGameOver()).isEqualTo(expectedGameOver);
    }
}
