package magicbook.gtlitecore.api.recipe.property;

import gregtech.api.recipes.properties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.TreeMap;

public class AccelerationOrbitTieredProperty extends RecipeProperty<Integer>
{

    public static final String KEY = "acc_orbit_tier";

    private static final TreeMap<Integer, String> registeredAccOrbitTiers = new TreeMap<>();

    private static AccelerationOrbitTieredProperty INSTANCE = new AccelerationOrbitTieredProperty();

    protected AccelerationOrbitTieredProperty()
    {
        super(KEY, Integer.class);
    }

    public static AccelerationOrbitTieredProperty getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new AccelerationOrbitTieredProperty();
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
        mc.fontRenderer.drawString("gtlitecore.recipe.acceleration_orbit_tier"
                + getTier(castValue(value)), x, y, color);
    }

    private static String getTier(Integer tier)
    {
        Map.Entry<Integer, String> registerTiers = registeredAccOrbitTiers.ceilingEntry(tier);
        if (registerTiers == null)
            throw new IllegalArgumentException("Acceleration Orbit Tier is above registered maximum tier value");
        return String.format("%s", registerTiers.getValue());
    }

    public static void register(int tier, String shortName)
    {
        Validate.notNull(shortName);
        registeredAccOrbitTiers.put(tier, shortName);
    }

}
