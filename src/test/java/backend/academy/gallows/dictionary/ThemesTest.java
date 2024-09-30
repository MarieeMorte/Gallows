package backend.academy.gallows.dictionary;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ThemesTest {
    @Test
    void expectThemesEnumValues_whenValuesAreRetrieved() {
        // Act
        Themes[] themes = Themes.values();

        // Assert
        assertThat(themes).containsExactlyInAnyOrder(Themes.FRUITS, Themes.BERRIES, Themes.VEGETABLES);
    }
}
