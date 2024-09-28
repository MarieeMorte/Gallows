package backend.academy.gallows.dictionary;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Dictionary {
    private static final Map<Difficulties, Map<Themes, List<String>>> dictionary = new EnumMap<>(Difficulties.class);
    Random random = new Random();

    static {
        dictionary.put(Difficulties.EASY, createWordMap(createWordMap(Map.of(Themes.FRUITS,
            List.of("банан", "груша", "дыня", "инжир", "киви", "лайм", "лимон", "манго", "слива", "фрукт", "хурма"),
            Themes.BERRIES, List.of("бузина", "калина", "клюква", "малина", "рябина"), Themes.VEGETABLES,
            List.of("бобы", "горох", "овощ", "перец", "редис", "тыква", "укроп")))));

        dictionary.put(Difficulties.MEDIUM, createWordMap(
            Map.of(Themes.FRUITS, List.of("абрикос", "ананас", "персик", "помело", "яблоко"), Themes.BERRIES,
                List.of("ежевика", "черника"), Themes.VEGETABLES,
                List.of("базилик", "капуста", "морковь", "огурец", "помидор", "свекла", "фасоль", "чеснок",
                    "щавель"))));

        dictionary.put(Difficulties.HARD, createWordMap(
            Map.of(Themes.FRUITS, List.of("бергамот", "виноград", "грейпфрут", "мандарин"), Themes.BERRIES,
                List.of("барбарис", "брусника", "голубика", "клубника", "облепиха"), Themes.VEGETABLES,
                List.of("баклажаны", "картофель", "петрушка", "сельдерей"))));
    }

    private static Map<Themes, List<String>> createWordMap(Map<Themes, List<String>> entries) {
        return new HashMap<>(entries);
    }

    public Difficulties getRandomDifficult() {
        return Difficulties.values()[random.nextInt(Difficulties.values().length)];
    }

    public Themes getRandomTheme() {
        return Themes.values()[random.nextInt(Themes.values().length)];
    }

    public String getRandomWord(Difficulties difficult, Themes theme) {
        Map<Themes, List<String>> wordsByDifficulty = dictionary.get(difficult);
        if (wordsByDifficulty == null) {
            throw new IllegalArgumentException("Difficulty level not found: " + difficult);
        }

        List<String> words = wordsByDifficulty.get(theme);
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("The topics are not found or are empty: " + theme);
        }

        int index = random.nextInt(words.size());
        return words.get(index);
    }
}
