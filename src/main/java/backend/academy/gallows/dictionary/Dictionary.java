package backend.academy.gallows.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
    private static final Map<Difficulties, Map<Themes, List<String>>> dictionary = new HashMap<>();

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
}
