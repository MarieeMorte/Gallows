package backend.academy.gallows.dictionary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryTest {
    private Dictionary dictionary;

    @BeforeEach
    void setUp() {
        dictionary = new Dictionary();
    }

    static Stream<Arguments> provideWordsForAdding() {
        return Stream.of(Arguments.of(Difficulty.EASY, Theme.FRUITS,
            List.of(new WordWithHint("арбуз", "большая ягода с зелёной коркой и сладкой красной мякотью."),
                new WordWithHint("вишня", "красная ягода, которую часто добавляют в пироги и варенье."),
                new WordWithHint("кизил", "маленькие красные или чёрные плоды, часто растут на деревьях и кустах."),
                new WordWithHint("ягода", "часто используется в десертах.")),
            List.of("арбуз", "вишня", "кизил", "ягода")), Arguments.of(Difficulty.EASY, Theme.VEGETABLES, List.of(
                new WordWithHint("лук",
                    "овощ с многослойной структурой, часто используется в кулинарии для придания вкуса блюдам.")),
            List.of("лук")), Arguments.of(Difficulty.HARD, Theme.BERRIES, List.of(new WordWithHint("земляника",
                    "маленькие красные ягоды с характерным сладким вкусом, растут на низких кустах."),
                new WordWithHint("крыжовник",
                    "ягода, которая может быть зелёной, красной или желтой, часто используется для варенья."),
                new WordWithHint("смородина",
                    "тёмные ягоды, которые могут быть чёрными, красными или белыми, часто используются для приготовления напитков и десертов.")),
            List.of("земляника", "крыжовник", "смородина")));
    }

    @ParameterizedTest
    @MethodSource("provideWordsForAdding")
    void givenDifficultyAndTheme_whenAddingWords_thenExpectWordFromTheme(
        Difficulty difficulty,
        Theme theme,
        List<WordWithHint> wordsToAdd,
        List<String> expectedWords
    ) {
        // Act
        dictionary.addWords(difficulty, theme, wordsToAdd);
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn(expectedWords);
    }

    static Stream<Arguments> provideRandomWords() {
        return Stream.of(
            Arguments.of(Difficulty.EASY, Theme.FRUITS,
                List.of("банан", "груша", "дыня", "инжир", "киви", "лайм", "лимон", "манго", "слива", "фрукт",
                    "хурма")),
            Arguments.of(Difficulty.EASY, Theme.BERRIES, List.of("бузина", "калина", "клюква", "малина", "рябина")),
            Arguments.of(Difficulty.EASY, Theme.VEGETABLES,
                List.of("бобы", "горох", "овощ", "перец", "редис", "тыква", "укроп")),
            Arguments.of(Difficulty.MEDIUM, Theme.FRUITS, List.of("абрикос", "ананас", "персик", "помело", "яблоко")),
            Arguments.of(Difficulty.MEDIUM, Theme.BERRIES, List.of("ежевика", "черника")),
            Arguments.of(Difficulty.MEDIUM, Theme.VEGETABLES,
                List.of("базилик", "капуста", "морковь", "огурец", "помидор", "свекла", "фасоль", "чеснок", "щавель")),
            Arguments.of(Difficulty.HARD, Theme.FRUITS, List.of("бергамот", "виноград", "грейпфрут", "мандарин")),
            Arguments.of(Difficulty.HARD, Theme.BERRIES,
                List.of("барбарис", "брусника", "голубика", "клубника", "облепиха")),
            Arguments.of(Difficulty.HARD, Theme.VEGETABLES, List.of("баклажаны", "картофель", "петрушка", "сельдерей"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideRandomWords")
    void givenDifficultyAndTheme_whenRequested_thenExpectRandomWordFromTheme(
        Difficulty difficulty,
        Theme theme,
        List<String> expectedWords
    ) {
        // Act
        WordWithHint word = dictionary.getRandom(difficulty, theme);

        // Assert
        assertThat(word).isNotNull();
        assertThat(word.word()).isIn(expectedWords);
    }
}
