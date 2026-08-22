package gregtechlite.gtlitecore.api.item

import mezz.jei.plugins.vanilla.brewing.BrewingRecipeUtil
import net.minecraft.init.PotionTypes
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionType
import net.minecraft.potion.PotionUtils

object MCPotions
{
    @JvmStatic
    val EMPTY: ItemStack = potion(PotionTypes.EMPTY) // Empty means uncraftable potion.
    @JvmStatic
    val WATER: ItemStack = potion(PotionTypes.WATER)
    @JvmStatic
    val MUNDANE: ItemStack = potion(PotionTypes.MUNDANE)
    @JvmStatic
    val THICK: ItemStack = potion(PotionTypes.THICK)
    @JvmStatic
    val AWKWARD: ItemStack = potion(PotionTypes.AWKWARD)
    @JvmStatic
    val NIGHT_VISION: ItemStack = potion(PotionTypes.NIGHT_VISION)
    @JvmStatic
    val LONG_NIGHT_VISION: ItemStack = potion(PotionTypes.LONG_NIGHT_VISION)
    @JvmStatic
    val INVISIBILITY: ItemStack = potion(PotionTypes.INVISIBILITY)
    @JvmStatic
    val LONG_INVISIBILITY: ItemStack = potion(PotionTypes.LONG_INVISIBILITY)
    @JvmStatic
    val LEAPING: ItemStack = potion(PotionTypes.LEAPING)
    @JvmStatic
    val LONG_LEAPING: ItemStack = potion(PotionTypes.LONG_LEAPING)
    @JvmStatic
    val STRONG_LEAPING: ItemStack = potion(PotionTypes.STRONG_LEAPING)
    @JvmStatic
    val FIRE_RESISTANCE: ItemStack = potion(PotionTypes.FIRE_RESISTANCE)
    @JvmStatic
    val LONG_FIRE_RESISTANCE: ItemStack = potion(PotionTypes.LONG_FIRE_RESISTANCE)
    @JvmStatic
    val SWIFTNESS: ItemStack = potion(PotionTypes.SWIFTNESS)
    @JvmStatic
    val LONG_SWIFTNESS: ItemStack = potion(PotionTypes.LONG_SWIFTNESS)
    @JvmStatic
    val STRONG_SWIFTNESS: ItemStack = potion(PotionTypes.STRONG_SWIFTNESS)
    @JvmStatic
    val SLOWNESS: ItemStack = potion(PotionTypes.SLOWNESS)
    @JvmStatic
    val LONG_SLOWNESS: ItemStack = potion(PotionTypes.LONG_SLOWNESS)
    @JvmStatic
    val WATER_BREATHING: ItemStack = potion(PotionTypes.WATER_BREATHING)
    @JvmStatic
    val LONG_WATER_BREATHING: ItemStack = potion(PotionTypes.LONG_WATER_BREATHING)
    @JvmStatic
    val HEALING: ItemStack = potion(PotionTypes.HEALING)
    @JvmStatic
    val STRONG_HEALING: ItemStack = potion(PotionTypes.STRONG_HEALING)
    @JvmStatic
    val HARMING: ItemStack = potion(PotionTypes.HARMING)
    @JvmStatic
    val STRONG_HARMING: ItemStack = potion(PotionTypes.STRONG_HARMING)
    @JvmStatic
    val POISON: ItemStack = potion(PotionTypes.POISON)
    @JvmStatic
    val LONG_POISON: ItemStack = potion(PotionTypes.LONG_POISON)
    @JvmStatic
    val STRONG_POISON: ItemStack = potion(PotionTypes.STRONG_POISON)
    @JvmStatic
    val REGENERATION: ItemStack = potion(PotionTypes.REGENERATION)
    @JvmStatic
    val LONG_REGENERATION: ItemStack = potion(PotionTypes.LONG_REGENERATION)
    @JvmStatic
    val STRONG_REGENERATION: ItemStack = potion(PotionTypes.STRONG_REGENERATION)
    @JvmStatic
    val STRENGTH: ItemStack = potion(PotionTypes.STRENGTH)
    @JvmStatic
    val LONG_STRENGTH: ItemStack = potion(PotionTypes.LONG_STRENGTH)
    @JvmStatic
    val STRONG_STRENGTH: ItemStack = potion(PotionTypes.STRONG_STRENGTH)
    @JvmStatic
    val WEAKNESS: ItemStack = potion(PotionTypes.WEAKNESS)
    @JvmStatic
    val LONG_WEAKNESS: ItemStack = potion(PotionTypes.LONG_WEAKNESS)

    private fun potion(type: PotionType) = PotionUtils.addPotionToItemStack(BrewingRecipeUtil.POTION.copy(), type)
}