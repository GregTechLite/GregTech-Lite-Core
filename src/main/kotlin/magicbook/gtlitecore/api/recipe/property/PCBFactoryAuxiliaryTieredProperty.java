package magicbook.gtlitecore.api.recipe.property;

import gregtech.api.recipes.properties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;

import java.util.TreeMap;

/**
 * A pseudo tier for auxiliary structure tier.
 * <p>
 * This tier is the auxiliary upgrade checking, is same as an upgrade tier check as functional.
 * For example, Wetware Circuit Board needs Bio Chamber Upgrade (auxiliary structure of PCB Factory),
 * so the tier of this is 1. Hint: this is an extendable properties, but in the mod, we just add one
 * auxiliary structure, but if you want to add more auxiliary structure and its level, please change
 * this class and codes in PCB Factory.
 *
 * @see magicbook.gtlitecore.api.recipe.builder.PCBFactoryRecipeBuilder
 * @see magicbook.gtlitecore.api.recipe.property.PCBFactoryTieredProperty
 */
public class PCBFactoryAuxiliaryTieredProperty extends RecipeProperty<Integer>
{

    public static final String KEY = "pcb_factory_auxiliary_tier";

    private static final TreeMap<Integer, String> registeredPcbAuxTiers = new TreeMap<>();

    private static PCBFactoryAuxiliaryTieredProperty INSTANCE = new PCBFactoryAuxiliaryTieredProperty();

    protected PCBFactoryAuxiliaryTieredProperty()
    {
        super(KEY, Integer.class);
    }

    public static PCBFactoryAuxiliaryTieredProperty getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new PCBFactoryAuxiliaryTieredProperty();
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
        mc.fontRenderer.drawString(I18n.format("gtlitecore.recipe.required_bio_upgrade"), x, y, color);
    }

    public static void register(int tier, String shortName)
    {
        Validate.notNull(shortName);
        registeredPcbAuxTiers.put(tier, shortName);
    }

}
