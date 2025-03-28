package magicbook.gtlitecore.api.utils;

import gregtech.api.GTValues;
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
    // (ItemStack, Supplier<String>) => ItemStack.tooltip(String)
    private static final Map<ItemStack, Supplier<String>> tooltipMap = new ItemStackMap<>(false);

    // Vanilla color/format strings.
    public static final String BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED,
            DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE,
            YELLOW, WHITE, OBFUSCATED, BOLD, STRIKETHROUGH, UNDERLINE, ITALIC, RESET;
    public static final Supplier<String> NEW_LINE;

    // Initialized all vanilla color/format strings.
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

    /**
     * Create an animated text, this method taken and adapted from GT:NH modpack team's
     * fork mod <a href=https://github.com/GTNewHorizons/Avaritia>Avaritia</a>, please
     * see line 19 at {@code fox/spiteful/avaritia/LudicrousText.java}.
     *
     * @param text    The text to be animated.
     * @param step    The steps {@code formats} is shifted each {@code delay}.
     * @param delay   The milliseconds which between each shift of {@code formats}.
     * @param formats An array of formatting codes. Each char of {@code text} will be
     *                prefixed by one entry, depending on {@code step} and {@code delay}.
     *                Wraps around, if shorter than {@code formats}.
     * @return        An animated text with properties.
     *
     * @author TTFTCUTS, glowredman
     */
    public static Supplier<String> animatedText(String text,
                                                int step, int delay,
                                                String... formats)
    {
        if (text == null || text.isEmpty() || formats == null || formats.length == 0)
            return () -> "";

        final int finalDelay = Math.max(delay, 1);
        final int finalStep = Math.max(step, 0);

        return () -> {
            StringBuilder stringBuilder = new StringBuilder(text.length() * 3);
            int offset = (int) ((System.currentTimeMillis() / finalDelay) % formats.length);
            for (int i = 0; i < text.length(); i++)
            {
                char c = text.charAt(i);
                int indexColorArray = (i * finalStep + formats.length - offset) % formats.length;
                stringBuilder.append(formats[indexColorArray]);
                stringBuilder.append(c);
            }
            return stringBuilder.toString();
        };
    }

    /**
     * Create a formatted and animated text, this method taken and adapted from GT:NH modpack
     * team's fork mod <a href=https://github.com/GTNewHorizons/Avaritia>Avaritia</a>,
     * please see line 19 at {@code fox/spiteful/avaritia/LudicrousText.java}.
     *
     * @param format  The text to be formatted and animated.
     * @param args    The formatting arguments.
     * @param step    The steps {@code formats} is shifted each {@code delay}.
     * @param delay   The milliseconds which between each shift of {@code formats}.
     * @param formats An array of formatting codes. Each char of {@code text} will be
     *                prefixed by one entry, depending on {@code step} and {@code delay}.
     *                Wraps around, if shorter than {@code formats}.
     * @return        A formatted and animated text with properties.
     *
     * @author TTFTCUTS, glowredman
     */
    public static Supplier<String> animatedText(String format, Object[] args,
                                                int step, int delay,
                                                String... formats)
    {
        return animatedText(String.format(Locale.ROOT, format, args), step, delay, formats);
    }

    /**
     * Create a translated and animated text, this method taken and adapted from GT:NH modpack
     * fork mod <a href=https://github.com/GTNewHorizons/Avaritia>Avaritia</a>, please
     * see line 19 at {@code fox/spiteful/avaritia/LudicrousText.java}.
     *
     * @param translationKey The key used to look up the translation.
     * @param step           The steps {@code formats} is shifted each {@code delay}.
     * @param delay          The milliseconds which between each shift of {@code formats}.
     * @param formats        An array of formatting codes. Each char of {@code text} will be
     *                       prefixed by one entry, depending on {@code step} and {@code delay}.
     *                       Wraps around, if shorter than {@code formats}.
     * @return               A translated and animated text with properties.
     *
     * @author TTFTCUTS, glowredman
     */
    public static Supplier<String> translatedAnimatedText(String translationKey,
                                                          int step, int delay,
                                                          String... formats)
    {
        return animatedText(FormattingUtility.translateToLocal(translationKey), step, delay, formats);
    }

    /**
     * Create a translated, formatted and animated text, this method taken and adapted from
     * GT:NH modpack fork mod <a href=https://github.com/GTNewHorizons/Avaritia>Avaritia</a>,
     * please see line 19 at {@code fox/spiteful/avaritia/LudicrousText.java}.
     *
     * @param translationKey The key used to look up the translation.
     * @param args           The formatting arguments.
     * @param step           The steps {@code formats} is shifted each {@code delay}.
     * @param delay          The milliseconds which between each shift of {@code formats}.
     * @param formats        An array of formatting codes. Each char of {@code text} will be
     *                       prefixed by one entry, depending on {@code step} and {@code delay}.
     *                       Wraps around, if shorter than {@code formats}.
     * @return               A translated and animated text with properties.
     *
     * @author TTFTCUTS, glowredman
     */
    public static Supplier<String> translatedAnimatedText(String translationKey, Object[] args,
                                                          int step, int delay,
                                                          String... formats)
    {
        return animatedText(FormattingUtility.translateToLocalFormatted(translationKey, args), step, delay, formats);
    }

    /**
     * Add static {@code tooltip} to all items with {@code oreDictName}, this method is
     * useful for some grouped items like Gregtech materials.
     * <p>
     * Should use static text ({@link FormattingUtility#text}) in {@code tooltip}.
     * <p>
     * The items must be registered in the {@link OreDictionary} when this method is
     * called, and items with equal registry name and meta but different NBT are
     * considered equal.
     *
     * @param oreDictName Ore Dictionary name.
     * @param tooltip     Static string supplier.
     *
     * @author glowredman
     */
    public static void addOreDictTooltip(String oreDictName, Supplier<String> tooltip)
    {
        for (ItemStack item : OreDictionary.getOres(oreDictName))
            addItemTooltip(item, tooltip);
    }

    /**
     * Add {@code tooltip} to specific item with {@code modId}, {@code name} and {@code meta}.
     * <p>
     * For default, if {@code modId} is {@code null}, then will search {@code name} and
     * {@code meta} in the mod.
     * <p>
     * Should use static text ({@link FormattingUtility#text}) in {@code tooltip}.
     *
     * @param modId   The mod Id.
     * @param name    The item name.
     * @param meta    The item meta.
     * @param tooltip Static string supplier.
     *
     * @author glowredman
     */
    public static void addItemTooltip(String modId, String name, int meta, Supplier<String> tooltip)
    {
        Item item = GameRegistry.makeItemStack(modId == null ? GTLiteValues.MODID : modId,
                0, 1, name).getItem();

        if (tooltip == null)
            return;

        tooltipMap.merge(new ItemStack(item, 1, meta), tooltip,
                (a, b) -> FormattingUtility.chain(a, NEW_LINE, b));
    }

    /**
     * Add {@code tooltip} to {@code item}.
     * <p>
     * Items with equal registry name and meta but different NBT are considered equal,
     * using {@link OreDictionary#WILDCARD_VALUE} or {@link GTValues#W} as meta is allowed.
     * <p>
     * Should use static text ({@link FormattingUtility#text}) in {@code tooltip}.
     *
     * @param item    The item stack.
     * @param tooltip Static string supplier.
     *
     * @author glowredman
     */
    public static void addItemTooltip(ItemStack item, Supplier<String> tooltip)
    {
        if (item == null || tooltip == null)
            return;
        tooltipMap.merge(item, tooltip, (a, b) -> FormattingUtility.chain(a, NEW_LINE, b));
    }

}
