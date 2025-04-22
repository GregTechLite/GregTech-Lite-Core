package magicbook.gtlitecore.common.item.behavior;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IFoodBehavior;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.util.RandomPotionEffect;
import lombok.Getter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static magicbook.gtlitecore.api.utils.GTLiteValues.TICK;

@Getter
public class FoodBehavior implements IFoodBehavior, IItemBehaviour
{

    protected int foodLevel;
    protected float saturation;
    protected boolean isDrink;
    protected boolean alwaysEdible;

    public RandomPotionEffect[] potionEffects;
    public Supplier<ItemStack> stackSupplier;

    protected int eatingDuration = 32 * TICK;

    public FoodBehavior(int foodLevel, float saturation, boolean isDrink, boolean alwaysEdible,
                        Supplier<ItemStack> stackSupplier, RandomPotionEffect... potionEffects)
    {
        this.foodLevel = foodLevel;
        this.saturation = saturation;
        this.isDrink = isDrink;
        this.alwaysEdible = alwaysEdible;
        this.stackSupplier = stackSupplier;
        this.potionEffects = potionEffects;
    }

    public FoodBehavior(int foodLevel, float saturation, boolean isDrink, boolean alwaysEdible,
                        ItemStack stack, RandomPotionEffect... potionEffects)
    {
        this(foodLevel, saturation, isDrink, alwaysEdible, () -> stack, potionEffects);
    }

    public FoodBehavior(int foodLevel, float saturation, boolean isDrink, boolean alwaysEdible)
    {
        this(foodLevel, saturation, isDrink, alwaysEdible, ItemStack.EMPTY);
    }

    public FoodBehavior(int foodLevel, float saturation)
    {
        this(foodLevel, saturation, false, false);
    }

    @Override
    public EnumAction getFoodAction(ItemStack stack)
    {
        return this.isDrink ? EnumAction.DRINK : EnumAction.EAT;
    }

    @Override
    public int getFoodLevel(ItemStack stack, @Nullable EntityPlayer player)
    {
        return this.getFoodLevel();
    }

    @Override
    public float getSaturation(ItemStack stack, @Nullable EntityPlayer player)
    {
        return this.getSaturation();
    }

    @Override
    public boolean alwaysEdible(ItemStack stack, @Nullable EntityPlayer player)
    {
        return this.alwaysEdible;
    }

    /**
     * Set the return {@code ItemStack} when player eaten the food.
     *
     * @param stack The {@code ItemStack}.
     * @return      Regular {@code FoodBehavior} with current {@code stack} as returned stack.
     */
    public FoodBehavior setReturnStack(ItemStack stack)
    {
        this.stackSupplier = () -> stack;
        return this;
    }

    /**
     * Set the return {@code MetaValueItem} when player eaten the food.
     *
     * @param metaItem The {@code MetaValueItem}.
     * @return         Regular {@code FoodBehavior} with {@code ItemStack} of current {@code metaItem}
     *                 as returned stack.
     */
    public FoodBehavior setReturnStack(MetaItem<?>.MetaValueItem metaItem)
    {
        return this.setReturnStack(metaItem.getStackForm());
    }

    public ItemStack onFoodEaten(ItemStack stack, EntityPlayer player)
    {
        // Add all potion effects when food be eaten.
        for (RandomPotionEffect potionEffect : this.potionEffects)
        {
            if (Math.random() * 100.0D > (double) potionEffect.chance)
                player.addPotionEffect(new PotionEffect(potionEffect.effect));
        }
        // Returns the stack when player eaten the food.
        if (player != null && !player.world.isRemote)
        {
            if (this.stackSupplier != null)
            {
                ItemStack containedStack = stackSupplier.get().copy();
                if (!player.capabilities.isCreativeMode)
                {
                    if (stack.isEmpty()) return containedStack;

                    if (!player.inventory.addItemStackToInventory(containedStack))
                        player.dropItem(containedStack, false, false);
                }
            }
        }
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, List<String> lines)
    {
        if (this.potionEffects.length > 0)
            addPotionEffectTooltip(Arrays.asList(this.potionEffects), lines);

        if (this.eatingDuration == 32 * TICK)
            lines.add(new TextComponentTranslation("gtlitecore.tooltip.food.common_duration", this.eatingDuration).getFormattedText());
        else
            lines.add(new TextComponentTranslation("gtlitecore.tooltip.food.duration", this.eatingDuration).getFormattedText());
    }

    @SideOnly(Side.CLIENT)
    protected static void addPotionEffectTooltip(List<RandomPotionEffect> potionEffects, List<String> lines)
    {
        lines.add(new TextComponentTranslation("gtlitecore.tooltip.potion.header").getFormattedText());
        potionEffects.forEach(effect -> lines.add(
                new TextComponentTranslation("gtlitecore.tooltip.potion.each",
                        new TextComponentTranslation(effect.effect.getEffectName()).getFormattedText(),
                                 new TextComponentTranslation("enchantment.level." + (effect.effect.getAmplifier() + 1)),
                                            effect.effect.getDuration(), 100 - effect.chance).getFormattedText()));
    }

    public FoodBehavior setEatingDuration(int eatingDuration)
    {
        this.eatingDuration = eatingDuration;
        return this;
    }

    public FoodBehavior setPotionEffects(RandomPotionEffect... potionEffects)
    {
        this.potionEffects = potionEffects;
        return this;
    }

}
