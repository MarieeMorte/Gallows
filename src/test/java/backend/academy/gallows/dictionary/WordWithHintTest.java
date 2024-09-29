package backend.academy.gallows.dictionary;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WordWithHintTest {
    @Test
    void testWordWithHintCreation() {
        // Arrange
        String expectedWord = "банан";
        String expectedHint = "жёлтый тропический фрукт, который любят обезьяны.";

        // Act
        WordWithHint wordWithHint = new WordWithHint(expectedWord, expectedHint);

        // Assert
        assertThat(wordWithHint.word()).isEqualTo(expectedWord);
        assertThat(wordWithHint.hint()).isEqualTo(expectedHint);
    }
}
