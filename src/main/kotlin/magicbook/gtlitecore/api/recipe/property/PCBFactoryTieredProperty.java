package magicbook.gtlitecore.api.recipe.property;

import gregtech.api.recipes.properties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.TreeMap;

/**
 * Structure Tier for main structure tier.
 *
 * @see magicbook.gtlitecore.api.recipe.builder.PCBFactoryRecipeBuilder
 * @see magicbook.gtlitecore.api.recipe.property.PCBFactoryAuxiliaryTieredProperty
 */
public class PCBFactoryTieredProperty extends RecipeProperty<Integer>
{

    public static final String KEY = "pcb_factory_tier";

    private static final TreeMap<Integer, String> registeredPcbTiers = new TreeMap<>();

    private static PCBFactoryTieredProperty INSTANCE = new PCBFactoryTieredProperty();

    protected PCBFactoryTieredProperty()
    {
        super(KEY, Integer.class);
    }

    public static PCBFactoryTieredProperty getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new PCBFactoryTieredProperty();
        return INSTANCE;
    }

    @NotNull
    @Override
    public NBTBase serialize(@NotNull Object value)
    {
        return new NBTTagInt((Integer) value);
    }

    @NotNull
    @Override
    public Object deserialize(@NotNull NBTBase nbt)
    {
        return ((NBTTagInt) nbt).getInt();
    }

    @Override
    public void drawInfo(Minecraft mc, int x, int y, int color, Object value)
    {
        mc.fontRenderer.drawString(I18n.format("gtlitecore.recipe.pcb_factory_tier")
                + getTier(castValue(value)), x, y, color);
    }

    private static String getTier(Integer tier)
    {
        Map.Entry<Integer, String> registerTiers = registeredPcbTiers.ceilingEntry(tier);
        if (registerTiers == null)
            throw new IllegalArgumentException("PCB Factory Tier is above registered maximum tier value");
        return String.format("%s", registerTiers.getValue());
    }

    public static void register(int tier, String shortName)
    {
        Validate.notNull(shortName);
        registeredPcbTiers.put(tier, shortName);
    }

}
