package magicbook.gtlitecore.api.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class AnimatedTextHandler
{

    private static final Map<ItemStack, Supplier<String>> tooltipMap = new ItemStackMap<>(false);

    public static final String BLACK;
    public static final String DARK_BLUE;
    public static final String DARK_GREEN;
    public static final String DARK_AQUA;
    public static final String DARK_RED;
    public static final String DARK_PURPLE;
    public static final String GOLD;
    public static final String GRAY;
    public static final String DARK_GRAY;
    public static final String BLUE;
    public static final String GREEN;
    public static final String AQUA;
    public static final String RED;
    public static final String LIGHT_PURPLE;
    public static final String YELLOW;
    public static final String WHITE;
    public static final String OBFUSCATED;
    public static final String BOLD;
    public static final String STRIKETHROUGH;
    public static final String UNDERLINE;
    public static final String ITALIC;
    public static final String RESET;

    public static final Supplier<String> NEW_LINE;

    static
    {
        AQUA = TextFormatting.AQUA.toString();
        BLACK = TextFormatting.BLACK.toString();
        BLUE = TextFormatting.BLUE.toString();
        BOLD = TextFormatting.BOLD.toString();
        DARK_AQUA = TextFormatting.DARK_AQUA.toString();
        DARK_BLUE = TextFormatting.DARK_BLUE.toString();
        DARK_GRAY = TextFormatting.DARK_GRAY.toString();
        DARK_GREEN = TextFormatting.DARK_GREEN.toString();
        DARK_PURPLE = TextFormatting.DARK_PURPLE.toString();
        DARK_RED = TextFormatting.DARK_RED.toString();
        GOLD = TextFormatting.GOLD.toString();
        GRAY = TextFormatting.GRAY.toString();
        GREEN = TextFormatting.GREEN.toString();
        ITALIC = TextFormatting.ITALIC.toString();
        LIGHT_PURPLE = TextFormatting.LIGHT_PURPLE.toString();
        OBFUSCATED = TextFormatting.OBFUSCATED.toString();
        RED = TextFormatting.RED.toString();
        RESET = TextFormatting.RESET.toString();
        STRIKETHROUGH = TextFormatting.STRIKETHROUGH.toString();
        UNDERLINE = TextFormatting.UNDERLINE.toString();
        WHITE = TextFormatting.WHITE.toString();
        YELLOW = TextFormatting.YELLOW.toString();

        NEW_LINE = () -> "\n";
    }

    /**
     * Helper method to concatenate multiple texts.
     *
     * @author glowredman
     *
     * @since 1.0.0
     */
    @SafeVarargs
    public static Supplier<String> chain(Supplier<String>... parts)
    {
        return () -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (Supplier<String> text : parts)
            {
                stringBuilder.append(text.get());
            }
            return stringBuilder.toString();
        };
    }

    /**
     * Helper method to create a static text.
     *
     * @author glowredman
     *
     * @since 1.0.0
     */
    public static Supplier<String> text(String text)
    {
        return () -> text;
    }

    /**
     * Helper method to create a formatted and static text.
     *
     * @author glowredman
     *
     * @since 1.0.0
     */
    public static Supplier<String> text(String format, Object... args)
    {
        return () -> String.format(Locale.ROOT, format, args);
    }

    /**
     * Helper method to create a translated and static text.
     *
     * @author glowredman
     *
     * @since 1.0.0
     */
    public static Supplier<String> translatedText(String translationKey)
    {
        return () -> TranslateUtility.translateToLocal(translationKey);
    }

    /**
     * Helper method to create a translated, formatted and static text.
     *
     * @author glowredman
     *
     * @since 1.0.0
     */
    public static Supplier<String> translatedText(String translationKey, Object... args)
    {
        return () -> TranslateUtility.translateToLocalFormatted(translationKey, args);
    }

    /**
     * Helper method to create an animated text.
     *
     * @author TTFTCUTS, glowredman
     *
     * <p>
     *     Taken and adapted from <a href=https://github.com/GTNewHorizons/Avaritia>Avaritia</a>,
     *     please see: Line 19 at {@code src/main/java/fox/spiteful/avaritia/LudicrousText.java}.
     * </p>
     *
     * @param text             The text to be animated
     * @param posstep          How many steps {@code formattingArray} is shifted each {@code delay}
     * @param delay            How many milliseconds are between each shift of {@code formattingArray}
     * @param formattingArray  An array of formatting codes. Each char of {@code text} will be prefixed by one entry,
     *                         depending on {@code posstep} and {@code delay}. Wraps around,
     *                         if shorter than {@code formattingArray}.
     *
     * @since 1.0.0
     */
    public static Supplier<String> animatedText(String text,
                                                int posstep, int delay,
                                                String... formattingArray)
    {
        if (text == null || text.isEmpty()
                || formattingArray == null || formattingArray.length == 0)
            return () -> "";

        final int finalDelay = Math.max(delay, 1);
        final int finalPosstep = Math.max(posstep, 0);

        return () -> {
            StringBuilder stringBuilder = new StringBuilder(text.length() * 3);
            int offset = (int) ((System.currentTimeMillis() / finalDelay) % formattingArray.length);
            for (int i = 0; i < text.length(); i++)
            {
                char c = text.charAt(i);
                int indexColorArray = (i * finalPosstep + formattingArray.length - offset) % formattingArray.length;
                stringBuilder.append(formattingArray[indexColorArray]);
                stringBuilder.append(c);
            }
            return stringBuilder.toString();
        };
    }

    /**
     * Helper method to create a formatted and animated text.
     *
     * @author TTFTCUTS, glowredman
     *
     * <p>
     *     Taken and adapted from <a href=https://github.com/GTNewHorizons/Avaritia>Avaritia</a>,
     *     please see: Line 19 at {@code src/main/java/fox/spiteful/avaritia/LudicrousText.java}.
     * </p>
     *
     * @param format           The text to be formatted and animated
     * @param args             The formatting arguments
     * @param posstep          How many steps {@code formattingArray} is shifted each {@code delay}
     * @param delay            How many milliseconds are between each shift of {@code formattingArray}
     * @param formattingArray  An array of formatting codes. Each char of {@code text} will be prefixed by one entry,
     *                         depending on {@code posstep} and {@code delay}. Wraps around,
     *                         if shorter than {@code formattingArray}.
     *
     * @since 1.0.0
     */
    public static Supplier<String> animatedText(String format, Object[] args,
                                                int posstep, int delay,
                                                String... formattingArray)
    {
        return animatedText(String.format(Locale.ROOT, format, args), posstep, delay, formattingArray);
    }

    /**
     * Helper method to create a translated and animated text.
     *
     * @author TTFTCUTS, glowredman
     *
     * <p>
     *     Taken and adapted from <a href=https://github.com/GTNewHorizons/Avaritia>Avaritia</a>,
     *     please see: Line 19 at {@code src/main/java/fox/spiteful/avaritia/LudicrousText.java}.
     * </p>
     *
     * @param translationKey   The key used to look up the translation
     * @param posstep          How many steps {@code formattingArray} is shifted each {@code delay}
     * @param delay            How many milliseconds are between each shift of {@code formattingArray}
     * @param formattingArray  An array of formatting codes. Each char of {@code text} will be prefixed by one entry,
     *                         depending on {@code posstep} and {@code delay}. Wraps around,
     *                         if shorter than {@code formattingArray}.
     *
     * @since 1.0.0
     */
    public static Supplier<String> translatedAnimatedText(String translationKey, int posstep, int delay,
                                                          String... formattingArray)
    {
        return animatedText(TranslateUtility.translateToLocal(translationKey), posstep, delay, formattingArray);
    }

    /**
     * Helper method to create a translated, formatted and animated text
     *
     * @author TTFTCUTS, glowredman
     *
     * <p>
     *     Taken and adapted from <a href=https://github.com/GTNewHorizons/Avaritia>Avaritia</a>,
     *     please see: Line 19 at {@code src/main/java/fox/spiteful/avaritia/LudicrousText.java}.
     * </p>
     *
     * @param translationKey   The key used to look up the translation
     * @param args             The formatting arguments
     * @param posstep          How many steps {@code formattingArray} is shifted each {@code delay}
     * @param delay            How many milliseconds are between each shift of {@code formattingArray}
     * @param formattingArray  An array of formatting codes. Each char of {@code text} will be prefixed by one entry,
     *                         depending on {@code posstep} and {@code delay}. Wraps around,
     *                         if shorter than {@code formattingArray}.
     *
     * @since 1.0.0
     */
    public static Supplier<String> translatedAnimatedText(String translationKey, Object[] args,
                                                          int posstep, int delay,
                                                          String... formattingArray)
    {
        return animatedText(TranslateUtility.translateToLocalFormatted(translationKey, args), posstep, delay, formattingArray);
    }

    /**
     * Add {@code tooltip} to all items with {@code oredictName}.
     *
     * @author glowredman
     *
     * <p>
     *     The items must be registered to the {@link OreDictionary} when this method is called,
     *     and items with equal registry name and meta but different NBT are considered equal.
     * </p>
     *
     * @since 1.0.0
     */
    public static void addOredictTooltip(String oredictName, Supplier<String> tooltip)
    {
        for (ItemStack item : OreDictionary.getOres(oredictName))
        {
            addItemTooltip(item, tooltip);
        }
    }

    /**
     * Add {@code tooltip} to item specified by {@code modid}, {@code name} and {@code meta}.
     *
     * @author glowredman
     *
     * <p>
     *     The item must be registered to the {@link GameRegistry} when this method is called,
     *     and items with equal registry name and meta but different NBT are considered equal.
     *     Using {@link OreDictionary#WILDCARD_VALUE} as {@code meta} is allowed.
     * </p>
     *
     * @since 1.0.0
     */
    public static void addItemTooltip(String modid, String name, int meta, Supplier<String> tooltip)
    {
        Item item = GameRegistry.makeItemStack(modid, 0, 1, name).getItem();
        if (tooltip == null)
            return;

        tooltipMap.merge(new ItemStack(item, 1, meta), tooltip, (a, b) -> chain(a, NEW_LINE, b));
    }

    /**
     * Add {@code tooltip} to {@code item}.
     *
     * @author glowredman
     *
     * <p>
     *     Items with equal registry name and meta but different NBT are considered equal.
     *     Using {@link OreDictionary#WILDCARD_VALUE} as meta is allowed.
     * </p>
     *
     * @since 1.0.0
     */
    public static void addItemTooltip(ItemStack item, Supplier<String> tooltip)
    {
        if (item == null || tooltip == null)
            return;
        tooltipMap.merge(item, tooltip, (a, b) -> chain(a, NEW_LINE, b));
    }

    @SubscribeEvent
    public void renderTooltip(ItemTooltipEvent event)
    {
        Supplier<String> tooltip = tooltipMap.get(event.getItemStack());
        if (tooltip == null)
            return;

        String text = tooltip.get();
        if (text == null)
            return;

        event.getToolTip().addAll(Arrays.asList(text.split("\n")));
    }

}
