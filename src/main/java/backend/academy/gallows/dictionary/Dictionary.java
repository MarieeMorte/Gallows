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
        EnumMap<Themes, List<String>> easyWords = new EnumMap<>(Themes.class);
        easyWords.put(Themes.FRUITS,
            List.of("банан", "груша", "дыня", "инжир", "киви", "лайм", "лимон", "манго", "слива", "фрукт", "хурма"));
        easyWords.put(Themes.BERRIES, List.of("бузина", "калина", "клюква", "малина", "рябина"));
        easyWords.put(Themes.VEGETABLES, List.of("бобы", "горох", "овощ", "перец", "редис", "тыква", "укроп"));
        dictionary.put(Difficulties.EASY, easyWords);

        EnumMap<Themes, List<String>> mediumWords = new EnumMap<>(Themes.class);
        mediumWords.put(Themes.FRUITS, List.of("абрикос", "ананас", "персик", "помело", "яблоко"));
        mediumWords.put(Themes.BERRIES, List.of("ежевика", "черника"));
        mediumWords.put(Themes.VEGETABLES,
            List.of("базилик", "капуста", "морковь", "огурец", "помидор", "свекла", "фасоль", "чеснок", "щавель"));
        dictionary.put(Difficulties.MEDIUM, mediumWords);

        EnumMap<Themes, List<String>> hardWords = new EnumMap<>(Themes.class);
        hardWords.put(Themes.FRUITS, List.of("бергамот", "виноград", "грейпфрут", "мандарин"));
        hardWords.put(Themes.BERRIES, List.of("барбарис", "брусника", "голубика", "клубника", "облепиха"));
        hardWords.put(Themes.VEGETABLES, List.of("баклажаны", "картофель", "петрушка", "сельдерей"));
        dictionary.put(Difficulties.HARD, hardWords);
    }

    public Difficulties getRandomDifficulty() {
        return Difficulties.values()[random.nextInt(Difficulties.values().length)];
    }

    public Themes getRandomTheme() {
        return Themes.values()[random.nextInt(Themes.values().length)];
    }

    public String getRandomWord(Difficulties difficult, Themes theme) {
        List<String> words = dictionary.get(difficult).get(theme);
        int index = random.nextInt(words.size());
        return words.get(index);
    }
}
