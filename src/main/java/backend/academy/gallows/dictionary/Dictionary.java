package backend.academy.gallows.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Dictionary {
    private static final Map<Difficulties, Map<Themes, List<String>>> dictionary = new HashMap<>();
    Random random = new Random();

    static {
        Map<Themes, List<String>> easyWords = new HashMap<>();
        easyWords.put(Themes.FRUITS,
            List.of("банан", "груша", "дыня", "инжир", "киви", "лайм", "лимон", "манго", "слива", "фрукт", "хурма"));
        easyWords.put(Themes.BERRIES, List.of("бузина", "калина", "клюква", "малина", "рябина"));
        easyWords.put(Themes.VEGETABLES,
            List.of("бобы", "горох", "овощ", "перец", "редис", "тыква", "укроп"));

        Map<Themes, List<String>> mediumWords = new HashMap<>();
        mediumWords.put(Themes.FRUITS, List.of("абрикос", "ананас", "персик", "помело", "яблоко"));
        mediumWords.put(Themes.BERRIES, List.of("ежевика", "черника"));
        mediumWords.put(Themes.VEGETABLES,
            List.of("базилик", "капуста", "морковь", "огурец", "помидор", "свекла", "фасоль", "чеснок", "щавель"));

        Map<Themes, List<String>> hardWords = new HashMap<>();
        hardWords.put(Themes.FRUITS, List.of("бергамот", "виноград", "грейпфрут", "мандарин"));
        hardWords.put(Themes.BERRIES,
            List.of("барбарис", "брусника", "голубика", "клубника", "облепиха"));
        hardWords.put(Themes.VEGETABLES, List.of("баклажаны", "картофель", "петрушка", "сельдерей"));

        dictionary.put(Difficulties.EASY, easyWords);
        dictionary.put(Difficulties.MEDIUM, mediumWords);
        dictionary.put(Difficulties.HARD, hardWords);
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
