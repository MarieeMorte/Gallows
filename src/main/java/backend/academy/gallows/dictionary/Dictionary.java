package backend.academy.gallows.dictionary;

import java.security.SecureRandom;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class Dictionary {
    private final Map<Difficulty, EnumMap<Theme, List<WordWithHint>>> dictionary;
    private final SecureRandom random;

    public Dictionary() {
        this.dictionary = new EnumMap<>(Difficulty.class);
        this.random = new SecureRandom();
        initializeDictionary();
    }

    private void initializeDictionary() {
        addWords(Difficulty.EASY, Theme.FRUITS,
            List.of(new WordWithHint("банан", "жёлтый тропический фрукт, который любят обезьяны."),
                new WordWithHint("груша", "сладкий фрукт с характерной формой, часто зелёного или жёлтого цвета."),
                new WordWithHint("дыня", "крупный сочный фрукт, обычно оранжевого цвета внутри."),
                new WordWithHint("инжир", "сладкий фрукт с мелкими семечками, часто используется в десертах."),
                new WordWithHint("киви", "небольшой коричневый фрукт с зелёной мякотью и мелкими чёрными семечками."),
                new WordWithHint("лайм", "мелкий зелёный цитрусовый фрукт, часто используемый в коктейлях."),
                new WordWithHint("лимон", "кислый жёлтый фрукт, популярный в кулинарии и напитках."),
                new WordWithHint("манго", "сладкий тропический фрукт с оранжевой мякотью и косточкой внутри."),
                new WordWithHint("слива",
                    "косточковый фрукт, который может быть сладким или кислым, часто фиолетового цвета."),
                new WordWithHint("фрукт", "общее название для сладких съедобных плодов растений."),
                new WordWithHint("хурма", "сладкий фрукт оранжевого цвета, часто употребляется в свежем виде.")));

        addWords(Difficulty.EASY, Theme.BERRIES, List.of(
            new WordWithHint("бузина", "ягоды чёрного цвета, часто используются для приготовления сиропов и варений."),
            new WordWithHint("калина", "красные ягоды, известные своими лечебными свойствами."),
            new WordWithHint("клюква", "кислые ягоды, часто используются для приготовления соков и морсов."),
            new WordWithHint("малина", "сладкие красные ягоды, которые часто добавляют в десерты."),
            new WordWithHint("рябина",
                "ягоды оранжевого цвета, которые бывают горькими, но ценятся за полезные свойства.")));

        String herbHintDescription = "ароматная зелень, часто используется для украшения и приправы блюд.";
        addWords(Difficulty.EASY, Theme.VEGETABLES, List.of(
            new WordWithHint("бобы", "семена, часто используемые в блюдах, имеют высокое содержание белка."),
            new WordWithHint("горох", "зелёные круглые семена, которые обычно добавляют в супы и гарниры."),
            new WordWithHint("овощ", "съедобная часть растения, используемая в кулинарии."),
            new WordWithHint("перец", "может быть сладким или острым, часто используется для приправы блюд."),
            new WordWithHint("редис", "круглый или овальный корнеплод, обычно красного цвета с острым вкусом."),
            new WordWithHint("тыква", "крупный оранжевый овощ, используемый в супах и десертах."),
            new WordWithHint("укроп", herbHintDescription)));

        addWords(Difficulty.MEDIUM, Theme.FRUITS,
            List.of(new WordWithHint("абрикос", "сладкий фрукт с бархатистой кожурой и косточкой внутри."),
                new WordWithHint("ананас", "тропический фрукт с шершавой коркой и сладкой мякотью."),
                new WordWithHint("персик", "сочный фрукт с пушистой кожицей, часто жёлтого или розового цвета."),
                new WordWithHint("помело", "крупный цитрусовый фрукт с толстой кожурой и сладким вкусом."),
                new WordWithHint("яблоко",
                    "популярный фрукт, бывает разных сортов и цветов, часто используется в пирогах.")));

        addWords(Difficulty.MEDIUM, Theme.BERRIES,
            List.of(new WordWithHint("ежевика", "тёмные ягоды с характерными шипами, сладкие и слегка кислые на вкус."),
                new WordWithHint("черника",
                    "маленькие тёмно-синие ягоды, известные своим сладким вкусом и полезными свойствами.")));

        addWords(Difficulty.MEDIUM, Theme.VEGETABLES,
            List.of(new WordWithHint("базилик", "ароматная зелень, часто используется в итальянской кухне."),
                new WordWithHint("капуста", "листовой овощ, бывает белокочанной, краснокочанной и брюссельской."),
                new WordWithHint("морковь", "оранжевый корнеплод, богатый витаминами, часто используется в салатах."),
                new WordWithHint("огурец", "сочный зеленый овощ, часто добавляется в салаты и закуски."),
                new WordWithHint("помидор", "круглый или овальный плод, " + "может быть красным, желтым или зеленым, "
                    + "используется в салатах и соусах."),
                new WordWithHint("свекла", "корнеплод тёмного цвета, часто используется в салатах и борщах."),
                new WordWithHint("фасоль", "Бобовое растение, семена которого популярны в различных блюдах."),
                new WordWithHint("чеснок", "ароматная специя, используемая для придания вкуса блюдам."),
                new WordWithHint("щавель", "Листовой овощ с кислым вкусом, часто используется в супах и салатах.")));

        addWords(Difficulty.HARD, Theme.FRUITS,
            List.of(new WordWithHint("бергамот",
                    "цитрусовый фрукт с характерным ароматом, часто используется в парфюмерии и чае."),
                new WordWithHint("виноград", "сладкие ягоды, " + "которые могут быть зелёными, красными или чёрными, "
                    + "часто используются для производства вина."),
                new WordWithHint("грейпфрут",
                    "крупный цитрусовый фрукт с горьким вкусом, может быть жёлтым или розовым."),
                new WordWithHint("мандарин", "сладкий цитрусовый фрукт с легко снимаемой кожурой.")));

        addWords(Difficulty.HARD, Theme.BERRIES,
            List.of(new WordWithHint("барбарис",
                    "ягоды красного или жёлтого цвета, часто используются в компотах и десертах."),
                new WordWithHint("брусника",
                    "кисло-сладкие ягоды, часто используемые для приготовления варений и соусов."),
                new WordWithHint("голубика",
                    "маленькие тёмно-синие ягоды, известные своими антиоксидантными свойствами."),
                new WordWithHint("клубника", "сладкие красные ягоды с характерными семенами на поверхности."),
                new WordWithHint("облепиха",
                    "Ярко-оранжевые ягоды, известные своими полезными свойствами и высоким содержанием витаминов.")));

        addWords(Difficulty.HARD, Theme.VEGETABLES,
            List.of(new WordWithHint("баклажаны", "тёмно-фиолетовые овощи, часто используемые в рагу и запеканках."),
                new WordWithHint("картофель",
                    "клубнеплод, который является основным продуктом питания во многих странах."),
                new WordWithHint("петрушка", herbHintDescription),
                new WordWithHint("сельдерей",
                    "хрустящий овощ с характерным вкусом, часто добавляется в салаты и супы.")));
    }

    void addWords(Difficulty difficulty, Theme theme, List<WordWithHint> words) {
        dictionary.computeIfAbsent(difficulty, k -> new EnumMap<>(Theme.class)).put(theme, words);
    }

    public WordWithHint getRandom(Difficulty difficult, Theme theme) {
        List<WordWithHint> words = dictionary.get(difficult).get(theme);
        int index = random.nextInt(words.size());
        return words.get(index);
    }
}
