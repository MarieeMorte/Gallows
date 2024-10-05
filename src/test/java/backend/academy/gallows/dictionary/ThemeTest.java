package backend.academy.gallows.dictionary;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ThemeTest {
    @Test
    void givenThemesEnum_whenValuesAreRetrieved_thenExpectCorrectEnumValues() {
        // Act
        Theme[] themes = Theme.values();

        // Assert
        assertThat(themes).containsExactlyInAnyOrder(Theme.FRUITS, Theme.BERRIES, Theme.VEGETABLES);
    }
}
