package magicbook.gtlitecore.common.item.behavior

import gregtech.api.items.metaitem.stats.IFoodBehavior
import gregtech.api.items.metaitem.stats.IItemBehaviour
import gregtech.api.util.RandomPotionEffect
import magicbook.gtlitecore.api.utils.GTLiteUtility
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumAction
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionEffect
import net.minecraft.util.text.TextComponentTranslation
import java.util.function.Supplier

@Suppress("MISSING_DEPENDENCY_CLASS")
class FoodBehavior(private var foodLevel: Int,
                   private var saturation: Float,
                   private var isDrink: Boolean,
                   private var alwaysEdible: Boolean,
                   private var stackSupplier: Supplier<ItemStack>,
                   vararg potionEffects: RandomPotionEffect) : IFoodBehavior, IItemBehaviour
{

    var potionEffects: Array<out RandomPotionEffect> = potionEffects
    var eatingDuration: Int = 32
        private set

    constructor(foodLevel: Int,
                saturation: Float,
                isDrink: Boolean,
                alwaysEdible: Boolean,
                stack: ItemStack,
                vararg potionEffects: RandomPotionEffect) : this(foodLevel, saturation, isDrink, alwaysEdible, Supplier { stack }, *potionEffects)

    constructor(foodLevel: Int,
                saturation: Float,
                isDrink: Boolean,
                alwaysEdible: Boolean) : this(foodLevel, saturation, isDrink, alwaysEdible, ItemStack.EMPTY)

    constructor(foodLevel: Int,
                saturation: Float) : this(foodLevel, saturation, false, false)

    override fun getFoodAction(itemStack: ItemStack): EnumAction
        = if (this.isDrink) EnumAction.DRINK else EnumAction.EAT

    override fun getFoodLevel(stack: ItemStack?, player: EntityPlayer?): Int = this.foodLevel

    override fun getSaturation(stack: ItemStack?, player: EntityPlayer?): Float = this.saturation

    override fun alwaysEdible(stack: ItemStack?, player: EntityPlayer?): Boolean = this.alwaysEdible

    fun setReturnStack(stack: ItemStack): FoodBehavior
    {
        this.stackSupplier = Supplier { stack }
        return this
    }

    override fun onFoodEaten(itemStack: ItemStack, player: EntityPlayer?): ItemStack
    {
        player ?: return itemStack
        this.potionEffects.forEach { effect ->
            if (Math.random() * 100 > effect.chance)
                player.addPotionEffect(PotionEffect(effect.effect))
        }

        if (!player.world.isRemote)
        {
            this.stackSupplier.get().copy().let { containerItem ->
                if (!player.capabilities.isCreativeMode)
                {
                    if (itemStack.isEmpty)
                        return containerItem

                    if (!player.inventory.addItemStackToInventory(containerItem))
                        player.dropItem(containerItem, false, false)

                }
            }
        }

        return itemStack
    }

    override fun addInformation(stack: ItemStack?,
                                tooltip: MutableList<String>?)
    {
        if (potionEffects.isNotEmpty())
            GTLiteUtility.addPotionEffectTooltip(potionEffects.toList(), tooltip)

        if (eatingDuration != 32)
            tooltip!!.add(TextComponentTranslation("gtlitecore.tooltip.food.duration", this.eatingDuration).formattedText)
        else if (eatingDuration == 32)
            tooltip!!.add(TextComponentTranslation("gtlitecore.tooltip.food.common_duration", this.eatingDuration).formattedText)
    }

    fun setEatingDuration(duration: Int): FoodBehavior
    {
        this.eatingDuration = duration
        return this
    }

    fun setPotionEffects(vararg effects: RandomPotionEffect): FoodBehavior
    {
        this.potionEffects = effects
        return this
    }

}