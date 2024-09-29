package backend.academy.gallows.dictionary;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryTest {
    @Test
    void testGetRandomWordFromEasyFruits() {
        // Arrange
        Difficulties difficulty = Difficulties.EASY;
        Themes theme = Themes.FRUITS;

        // Act
        WordWithHint word = Dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("банан", "груша", "дыня", "инжир", "киви", "лайм", "лимон", "манго", "слива",
            "фрукт", "хурма");
    }

    @Test
    void testGetRandomWordFromEasyBerries() {
        // Arrange
        Difficulties difficulty = Difficulties.EASY;
        Themes theme = Themes.BERRIES;

        // Act
        WordWithHint word = Dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("бузина", "калина", "клюква", "малина", "рябина");
    }

    @Test
    void testGetRandomWordFromEasyVegetables() {
        // Arrange
        Difficulties difficulty = Difficulties.EASY;
        Themes theme = Themes.VEGETABLES;

        // Act
        WordWithHint word = Dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("бобы", "горох", "овощ", "перец", "редис", "тыква", "укроп");
    }

    @Test
    void testGetRandomWordFromMediumFruits() {
        // Arrange
        Difficulties difficulty = Difficulties.MEDIUM;
        Themes theme = Themes.FRUITS;

        // Act
        WordWithHint word = Dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("абрикос", "ананас", "персик", "помело", "яблоко");
    }

    @Test
    void testGetRandomWordFromMediumBerries() {
        // Arrange
        Difficulties difficulty = Difficulties.MEDIUM;
        Themes theme = Themes.BERRIES;

        // Act
        WordWithHint word = Dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("ежевика", "черника");
    }

    @Test
    void testGetRandomWordFromMediumVegetables() {
        // Arrange
        Difficulties difficulty = Difficulties.MEDIUM;
        Themes theme = Themes.VEGETABLES;

        // Act
        WordWithHint word = Dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("базилик", "капуста", "морковь", "огурец", "помидор", "свекла", "фасоль", "чеснок",
            "щавель");
    }

    @Test
    void testGetRandomWordFromHardFruits() {
        // Arrange
        Difficulties difficulty = Difficulties.HARD;
        Themes theme = Themes.FRUITS;

        // Act
        WordWithHint word = Dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("бергамот", "виноград", "грейпфрут", "мандарин");
    }

    @Test
    void testGetRandomWordFromHardBerries() {
        // Arrange
        Difficulties difficulty = Difficulties.HARD;
        Themes theme = Themes.BERRIES;

        // Act
        WordWithHint word = Dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("барбарис", "брусника", "голубика", "клубника", "облепиха");
    }

    @Test
    void testGetRandomWordFromHardVegetables() {
        // Arrange
        Difficulties difficulty = Difficulties.HARD;
        Themes theme = Themes.VEGETABLES;

        // Act
        WordWithHint word = Dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("баклажаны", "картофель", "петрушка", "сельдерей");
    }
}
