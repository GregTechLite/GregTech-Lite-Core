package gregtechlite.gtlitecore.integration.appeng.item

import com.morphismmc.morphismlib.integration.Mods
import net.minecraft.item.ItemStack

enum class AEItems(val itemName: String, val itemMeta: Int) {
    LOGIC_PROCESSOR("material", 22),
    CALCULATION_PROCESSOR("material", 23),
    ENGINEERING_PROCESSOR("material", 24),
    STORAGE_COMPONENT_1K("material", 35),
    STORAGE_COMPONENT_4K("material", 36),
    STORAGE_COMPONENT_16K("material", 37),
    STORAGE_COMPONENT_64K("material", 38),
    FLUID_STORAGE_COMPONENT_1K("material", 54),
    FLUID_STORAGE_COMPONENT_4K("material", 55),
    FLUID_STORAGE_COMPONENT_16K("material", 56),
    FLUID_STORAGE_COMPONENT_64K("material", 57),

    QUARTZ_FIBER("part", 140);

    val stack: ItemStack
        get() = getStack(1)

    fun getStack(amount: Int = 1): ItemStack = Mods.AppliedEnergistics2.getItem(itemName, itemMeta, amount)
}