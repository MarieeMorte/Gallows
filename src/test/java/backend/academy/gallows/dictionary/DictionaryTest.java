package backend.academy.gallows.dictionary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryTest {
    private Dictionary dictionary;

    @BeforeEach
    void setUp() {
        dictionary = new Dictionary();
    }

    @Test
    void givenEasyDifficultyAndFruitsTheme_whenAddingWords_thenExpectWordFromEasyBerries() {
        // Arrange
        Difficulty difficulty = Difficulty.EASY;
        Theme theme = Theme.FRUITS;

        // Act
        dictionary.addWords(difficulty, theme,
            List.of(new WordWithHint("арбуз", "большая ягода с зелёной коркой и сладкой красной мякотью."),
                new WordWithHint("вишня", "красная ягода, которую часто добавляют в пироги и варенье."),
                new WordWithHint("кизил", "маленькие красные или чёрные плоды, часто растут на деревьях и кустах."),
                new WordWithHint("ягода", "часто используется в десертах.")));
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("арбуз", "вишня", "кизил", "ягода");
    }

    @Test
    void givenEasyDifficultyAndVegetablesTheme_whenAddingWords_thenExpectWordFromEasyVegetables() {
        // Arrange
        Difficulty difficulty = Difficulty.EASY;
        Theme theme = Theme.VEGETABLES;

        // Act
        dictionary.addWords(Difficulty.EASY, Theme.VEGETABLES, List.of(new WordWithHint("лук",
            "овощ с многослойной структурой, часто используется в кулинарии для придания вкуса блюдам.")));
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("лук");
    }

    @Test
    void givenHardDifficultyAndBerriesTheme_whenAddingWords_thenExpectWordFromHarBerries() {
        // Arrange
        Difficulty difficulty = Difficulty.HARD;
        Theme theme = Theme.BERRIES;

        // Act
        dictionary.addWords(Difficulty.HARD, Theme.BERRIES, List.of(new WordWithHint("земляника",
                "маленькие красные ягоды с характерным сладким вкусом, растут на низких кустах."),
            new WordWithHint("крыжовник",
                "ягода, которая может быть зелёной, красной или желтой, часто используется для варенья."),
            new WordWithHint("смородина", "тёмные ягоды, " + "которые могут быть чёрными, красными или белыми, "
                + "часто используются для приготовления напитков и десертов.")));
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("земляника", "крыжовник", "смородина");
    }

    @Test
    void givenEasyDifficultyAndFruitsTheme_whenRequested_thenExpectRandomWordFromEasyFruits() {
        // Arrange
        Difficulty difficulty = Difficulty.EASY;
        Theme theme = Theme.FRUITS;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("банан", "груша", "дыня", "инжир", "киви", "лайм", "лимон", "манго", "слива",
            "фрукт", "хурма");
    }

    @Test
    void givenEasyDifficultyAndBerriesTheme_whenRequested_thenExpectRandomWordFromEasyBerries() {
        // Arrange
        Difficulty difficulty = Difficulty.EASY;
        Theme theme = Theme.BERRIES;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("бузина", "калина", "клюква", "малина", "рябина");
    }

    @Test
    void givenEasyDifficultyAndVegetablesTheme_whenRequested_thenExpectRandomWordFromEasyVegetables() {
        // Arrange
        Difficulty difficulty = Difficulty.EASY;
        Theme theme = Theme.VEGETABLES;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("бобы", "горох", "овощ", "перец", "редис", "тыква", "укроп");
    }

    @Test
    void givenMediumDifficultyAndFruitsTheme_whenRequested_thenExpectRandomWordFromMediumFruits() {
        // Arrange
        Difficulty difficulty = Difficulty.MEDIUM;
        Theme theme = Theme.FRUITS;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("абрикос", "ананас", "персик", "помело", "яблоко");
    }

    @Test
    void givenMediumDifficultyAndBerriesTheme_whenRequested_thenExpectRandomWordFromMediumBerries() {
        // Arrange
        Difficulty difficulty = Difficulty.MEDIUM;
        Theme theme = Theme.BERRIES;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("ежевика", "черника");
    }

    @Test
    void givenMediumDifficultyAndVegetablesTheme_whenRequested_thenExpectRandomWordFromMediumVegetables() {
        // Arrange
        Difficulty difficulty = Difficulty.MEDIUM;
        Theme theme = Theme.VEGETABLES;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("базилик", "капуста", "морковь", "огурец", "помидор", "свекла", "фасоль", "чеснок",
            "щавель");
    }

    @Test
    void givenHardDifficultyAndFruitsTheme_whenRequested_thenExpectRandomWordFromHardFruits() {
        // Arrange
        Difficulty difficulty = Difficulty.HARD;
        Theme theme = Theme.FRUITS;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("бергамот", "виноград", "грейпфрут", "мандарин");
    }

    @Test
    void givenHardDifficultyAndBerriesTheme_whenRequested_thenExpectRandomWordFromHardBerries() {
        // Arrange
        Difficulty difficulty = Difficulty.HARD;
        Theme theme = Theme.BERRIES;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("барбарис", "брусника", "голубика", "клубника", "облепиха");
    }

    @Test
    void givenHardDifficultyAndVegetablesTheme_whenRequested_thenExpectRandomWordFromHardVegetables() {
        // Arrange
        Difficulty difficulty = Difficulty.HARD;
        Theme theme = Theme.VEGETABLES;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("баклажаны", "картофель", "петрушка", "сельдерей");
    }
}
