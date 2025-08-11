package gregtechlite.gtlitecore.common.item.behavior

import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.stats.IFoodBehavior
import gregtech.api.items.metaitem.stats.IItemBehaviour
import gregtech.api.util.RandomPotionEffect
import gregtechlite.gtlitecore.api.TICK
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumAction
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionEffect
import net.minecraft.util.text.TextComponentTranslation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class FoodBehavior(var foodLevel: Int,
                   var saturation: Float,
                   var isDrink: Boolean,
                   var isAlwaysEdible: Boolean,
                   var stackSupplier: () -> ItemStack?,
                   vararg var effects: RandomPotionEffect) : IFoodBehavior, IItemBehaviour
{
    // Default eating duration from vanilla foods.
    var eatingDuration = 32 * TICK

    constructor(foodLevel: Int,
                saturation: Float,
                isDrink: Boolean,
                alwaysEdible: Boolean,
                stack: ItemStack?,
                vararg effects: RandomPotionEffect) : this(foodLevel, saturation, isDrink, alwaysEdible, { stack }, *effects)
    
    @JvmOverloads 
    constructor(foodLevel: Int,
                saturation: Float,
                isDrink: Boolean = false,
                alwaysEdible: Boolean = false) : this(foodLevel, saturation, isDrink, alwaysEdible, ItemStack.EMPTY)

    companion object
    {
        @SideOnly(Side.CLIENT)
        private fun addPotionEffectTooltip(effects: MutableList<RandomPotionEffect?>, lines: MutableList<String?>)
        {
            lines.add(TextComponentTranslation("gtlitecore.tooltip.potion.header").getFormattedText())
            effects.forEach { effect ->
                lines.add(TextComponentTranslation("gtlitecore.tooltip.potion.each",
                      TextComponentTranslation(effect!!.effect.effectName).getFormattedText(),
                      TextComponentTranslation("enchantment.level." + (effect.effect.amplifier + 1)),
                          effect.effect.duration, 100 - effect.chance).getFormattedText())
            }
        }
    }

    override fun getFoodAction(stack: ItemStack?): EnumAction = if (this.isDrink) EnumAction.DRINK else EnumAction.EAT
    
    override fun getFoodLevel(stack: ItemStack?, player: EntityPlayer?) = this.foodLevel

    override fun getSaturation(stack: ItemStack?, player: EntityPlayer?) = this.saturation

    override fun alwaysEdible(stack: ItemStack?, player: EntityPlayer?) = this.isAlwaysEdible

    /**
     * Set the return `ItemStack` when player eaten the food.
     *
     * @param stack The `ItemStack`.
     * @return      Regular `FoodBehavior` with current `stack` as returned stack.
     */
    fun setReturnStack(stack: ItemStack?): FoodBehavior
    {
        this.stackSupplier = { stack }
        return this
    }
    
    /**
     * Set the return `MetaValueItem` when player eaten the food.
     *
     * @param metaItem The `MetaValueItem`.
     * @return Regular `FoodBehavior` with `ItemStack` of current `metaItem`
     * as returned stack.
     */
    fun setReturnStack(metaItem: MetaItem<*>.MetaValueItem): FoodBehavior
    {
        return this.setReturnStack(metaItem.stackForm)
    }

    fun setEatingDuration(eatingDuration: Int): FoodBehavior
    {
        this.eatingDuration = eatingDuration
        return this
    }

    fun setPotionEffects(vararg effects: RandomPotionEffect): FoodBehavior
    {
        this.effects = effects
        return this
    }

    override fun onFoodEaten(stack: ItemStack, player: EntityPlayer?): ItemStack
    {
        // Add all potion effects when food be eaten.
        for (effect in this.effects)
        {
            if (Math.random() * 100 > effect.chance)
                player!!.addPotionEffect(PotionEffect(effect.effect))
        }
        // Returns the stack when player eaten the food.
        if (player != null && !player.world.isRemote)
        {
            val containedStack = stackSupplier.invoke()!!.copy()
            if (!player.capabilities.isCreativeMode)
            {
                if (stack.isEmpty) return containedStack
                    
                if (!player.inventory.addItemStackToInventory(containedStack))
                    player.dropItem(containedStack, false, false)
            }
        }
        return stack
    }
    
    override fun addInformation(stack: ItemStack?, lines: MutableList<String?>)
    {
        if (this.effects.isNotEmpty())
            addPotionEffectTooltip(mutableListOf(*this.effects), lines)
        
        if (this.eatingDuration == 32 * TICK)
            lines.add(TextComponentTranslation("gtlitecore.tooltip.food.common_duration", this.eatingDuration).getFormattedText())
        else
            lines.add(TextComponentTranslation("gtlitecore.tooltip.food.duration", this.eatingDuration).getFormattedText())
    }

}

