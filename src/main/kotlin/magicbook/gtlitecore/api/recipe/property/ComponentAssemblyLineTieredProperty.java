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

public class ComponentAssemblyLineTieredProperty extends RecipeProperty<Integer>
{

    public static final String KEY = "coal_tier";

    private static final TreeMap<Integer, String> registeredCoALTiers = new TreeMap<>();

    private static ComponentAssemblyLineTieredProperty INSTANCE = new ComponentAssemblyLineTieredProperty();

    protected ComponentAssemblyLineTieredProperty()
    {
        super(KEY, Integer.class);
    }

    public static ComponentAssemblyLineTieredProperty getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new ComponentAssemblyLineTieredProperty();
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
        mc.fontRenderer.drawString(I18n.format("gtlitecore.recipe.component_assembly_line_tier",
                getTier(castValue(value))), x, y, color);
    }

    private static String getTier(Integer tier)
    {
        Map.Entry<Integer, String> registerTiers = registeredCoALTiers.ceilingEntry(tier);
        if (registerTiers == null)
            throw new IllegalArgumentException("Component Assembly Line Tier is above registered maximum tier value");
        return String.format("%s", registerTiers.getValue());
    }

    public static void register(int tier, String shortName)
    {
        Validate.notNull(shortName);
        registeredCoALTiers.put(tier, shortName);
    }

}
