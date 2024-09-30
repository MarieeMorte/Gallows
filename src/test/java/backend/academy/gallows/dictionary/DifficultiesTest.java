package backend.academy.gallows.dictionary;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DifficultiesTest {
    @Test
    void expectDifficultiesEnumValues_whenValuesAreRetrieved() {
        // Act
        Difficulties[] difficulties = Difficulties.values();

        // Assert
        assertThat(difficulties).containsExactlyInAnyOrder(Difficulties.EASY, Difficulties.MEDIUM, Difficulties.HARD);
    }
}
