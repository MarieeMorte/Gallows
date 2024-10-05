package backend.academy.gallows.dictionary;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DifficultyTest {
    @Test
    void givenDifficultiesEnum_whenValuesAreRetrieved_thenExpectCorrectEnumValues() {
        // Act
        Difficulty[] difficulties = Difficulty.values();

        // Assert
        assertThat(difficulties).containsExactlyInAnyOrder(Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD);
    }
}
