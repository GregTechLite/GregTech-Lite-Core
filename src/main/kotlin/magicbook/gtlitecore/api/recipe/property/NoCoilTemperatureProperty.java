package magicbook.gtlitecore.api.recipe.property;

import gregtech.api.recipes.properties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import org.jetbrains.annotations.NotNull;

public class NoCoilTemperatureProperty extends RecipeProperty<Integer>
{

    public static final String KEY = "temperature";

    public static NoCoilTemperatureProperty INSTANCE = new NoCoilTemperatureProperty();

    protected NoCoilTemperatureProperty()
    {
        super(KEY, Integer.class);
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
        mc.fontRenderer.drawString(I18n.format("gtlitecore.recipe.temperature", castValue(value)), x, y, color);
    }

}
