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
        Difficulties difficulty = Difficulties.EASY;
        Themes theme = Themes.FRUITS;

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
        Difficulties difficulty = Difficulties.EASY;
        Themes theme = Themes.VEGETABLES;

        // Act
        dictionary.addWords(Difficulties.EASY, Themes.VEGETABLES, List.of(new WordWithHint("лук",
            "овощ с многослойной структурой, часто используется в кулинарии для придания вкуса блюдам.")));
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("лук");
    }

    @Test
    void givenHardDifficultyAndBerriesTheme_whenAddingWords_thenExpectWordFromHarBerries() {
        // Arrange
        Difficulties difficulty = Difficulties.HARD;
        Themes theme = Themes.BERRIES;

        // Act
        dictionary.addWords(Difficulties.HARD, Themes.BERRIES, List.of(new WordWithHint("земляника",
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
        Difficulties difficulty = Difficulties.EASY;
        Themes theme = Themes.FRUITS;

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
        Difficulties difficulty = Difficulties.EASY;
        Themes theme = Themes.BERRIES;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("бузина", "калина", "клюква", "малина", "рябина");
    }

    @Test
    void givenEasyDifficultyAndVegetablesTheme_whenRequested_thenExpectRandomWordFromEasyVegetables() {
        // Arrange
        Difficulties difficulty = Difficulties.EASY;
        Themes theme = Themes.VEGETABLES;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("бобы", "горох", "овощ", "перец", "редис", "тыква", "укроп");
    }

    @Test
    void givenMediumDifficultyAndFruitsTheme_whenRequested_thenExpectRandomWordFromMediumFruits() {
        // Arrange
        Difficulties difficulty = Difficulties.MEDIUM;
        Themes theme = Themes.FRUITS;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("абрикос", "ананас", "персик", "помело", "яблоко");
    }

    @Test
    void givenMediumDifficultyAndBerriesTheme_whenRequested_thenExpectRandomWordFromMediumBerries() {
        // Arrange
        Difficulties difficulty = Difficulties.MEDIUM;
        Themes theme = Themes.BERRIES;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("ежевика", "черника");
    }

    @Test
    void givenMediumDifficultyAndVegetablesTheme_whenRequested_thenExpectRandomWordFromMediumVegetables() {
        // Arrange
        Difficulties difficulty = Difficulties.MEDIUM;
        Themes theme = Themes.VEGETABLES;

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
        Difficulties difficulty = Difficulties.HARD;
        Themes theme = Themes.FRUITS;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("бергамот", "виноград", "грейпфрут", "мандарин");
    }

    @Test
    void givenHardDifficultyAndBerriesTheme_whenRequested_thenExpectRandomWordFromHardBerries() {
        // Arrange
        Difficulties difficulty = Difficulties.HARD;
        Themes theme = Themes.BERRIES;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("барбарис", "брусника", "голубика", "клубника", "облепиха");
    }

    @Test
    void givenHardDifficultyAndVegetablesTheme_whenRequested_thenExpectRandomWordFromHardVegetables() {
        // Arrange
        Difficulties difficulty = Difficulties.HARD;
        Themes theme = Themes.VEGETABLES;

        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("баклажаны", "картофель", "петрушка", "сельдерей");
    }
}
