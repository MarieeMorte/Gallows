package backend.academy.gallows.dictionary;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Dictionary {
    private static final Map<Difficulties, EnumMap<Themes, List<String>>> dictionary =
        new EnumMap<>(Difficulties.class);
    private static final Random random = new Random();

    static {
        initializeDictionary();
    }

    private static void initializeDictionary() {
        addWords(Difficulties.EASY, Themes.FRUITS,
            List.of("банан", "груша", "дыня", "инжир", "киви", "лайм", "лимон", "манго", "слива", "фрукт", "хурма"));
        addWords(Difficulties.EASY, Themes.BERRIES, List.of("бузина", "калина", "клюква", "малина", "рябина"));
        addWords(Difficulties.EASY, Themes.VEGETABLES,
            List.of("бобы", "горох", "овощ", "перец", "редис", "тыква", "укроп"));

        addWords(Difficulties.MEDIUM, Themes.FRUITS, List.of("абрикос", "ананас", "персик", "помело", "яблоко"));
        addWords(Difficulties.MEDIUM, Themes.BERRIES, List.of("ежевика", "черника"));
        addWords(Difficulties.MEDIUM, Themes.VEGETABLES,
            List.of("базилик", "капуста", "морковь", "огурец", "помидор", "свекла", "фасоль", "чеснок", "щавель"));

        addWords(Difficulties.HARD, Themes.FRUITS, List.of("бергамот", "виноград", "грейпфрут", "мандарин"));
        addWords(Difficulties.HARD, Themes.BERRIES,
            List.of("барбарис", "брусника", "голубика", "клубника", "облепиха"));
        addWords(Difficulties.HARD, Themes.VEGETABLES, List.of("баклажаны", "картофель", "петрушка", "сельдерей"));
    }

    private static void addWords(Difficulties difficulty, Themes theme, List<String> words) {
        dictionary.computeIfAbsent(difficulty, k -> new EnumMap<>(Themes.class)).put(theme, words);
    }

    public static Difficulties getRandomDifficulty() {
        return Difficulties.values()[random.nextInt(Difficulties.values().length)];
    }

    public static Themes getRandomTheme() {
        return Themes.values()[random.nextInt(Themes.values().length)];
    }

    public static String getRandomWord(Difficulties difficult, Themes theme) {
        List<String> words = dictionary.get(difficult).get(theme);
        int index = random.nextInt(words.size());
        return words.get(index);
    }
}
