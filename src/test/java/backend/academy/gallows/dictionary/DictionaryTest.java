package backend.academy.gallows.dictionary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryTest {
    @AfterEach
    void tearDown() {
        Dictionary.initializeDictionary();
    }

    @Test
    void expectWordFromEasyBerries_whenDifficultyIsEasyAndThemeIsFruits() {
        // Arrange
        Difficulties difficulty = Difficulties.EASY;
        Themes theme = Themes.FRUITS;

        // Act
        Dictionary.addWords(difficulty, theme,
            List.of(new WordWithHint("арбуз", "большая ягода с зелёной коркой и сладкой красной мякотью."),
                new WordWithHint("вишня", "красная ягода, которую часто добавляют в пироги и варенье."),
                new WordWithHint("кизил", "маленькие красные или чёрные плоды, часто растут на деревьях и кустах."),
                new WordWithHint("ягода", "часто используется в десертах.")));
        WordWithHint word = Dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("арбуз", "вишня", "кизил", "ягода");
    }

    @Test
    void expectWordFromEasyVegetables_whenDifficultyIsEasyAndThemeIsVegetables() {
        // Arrange
        Difficulties difficulty = Difficulties.EASY;
        Themes theme = Themes.VEGETABLES;

        // Act
        Dictionary.addWords(Difficulties.EASY, Themes.VEGETABLES, List.of(new WordWithHint("лук",
            "овощ с многослойной структурой, часто используется в кулинарии для придания вкуса блюдам.")));
        WordWithHint word = Dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("лук");
    }

    @Test
    void expectWordFromHardBerries_whenDifficultyIsHardAndThemeIsBerries() {
        // Arrange
        Difficulties difficulty = Difficulties.HARD;
        Themes theme = Themes.BERRIES;

        // Act
        Dictionary.addWords(Difficulties.HARD, Themes.BERRIES, List.of(new WordWithHint("земляника",
                "маленькие красные ягоды с характерным сладким вкусом, растут на низких кустах."),
            new WordWithHint("крыжовник",
                "ягода, которая может быть зелёной, красной или желтой, часто используется для варенья."),
            new WordWithHint("смородина", "тёмные ягоды, " + "которые могут быть чёрными, красными или белыми, "
                + "часто используются для приготовления напитков и десертов.")));
        WordWithHint word = Dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn("земляника", "крыжовник", "смородина");
    }

    @Test
    void expectRandomWordFromEasyFruits_whenCalled() {
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
    void expectRandomWordFromEasyBerries_whenRequested() {
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
    void expectRandomWordFromEasyVegetables_whenRequested() {
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
    void expectRandomWordFromMediumFruits_whenRequested() {
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
    void expectRandomWordFromMediumBerries_whenRequested() {
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
    void expectRandomWordFromMediumVegetables_whenRequested() {
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
    void expectRandomWordFromHardFruits_whenRequested() {
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
    void expectRandomWordFromHardBerries_whenRequested() {
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
    void expectRandomWordFromHardVegetables_whenRequested() {
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
