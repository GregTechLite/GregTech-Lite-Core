package gregtechlite.gtlitecore.loader

import gregtech.loaders.WoodTypeEntry
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.common.block.adapter.LogBlock
import gregtechlite.gtlitecore.common.block.adapter.PlankBlock
import gregtechlite.gtlitecore.common.block.adapter.WoodFenceBlock
import gregtechlite.gtlitecore.common.block.adapter.WoodFenceGateBlock
import gregtechlite.gtlitecore.common.block.adapter.WoodSlabBlock
import gregtechlite.gtlitecore.common.block.adapter.WoodStairBlock

@Suppress("Deprecation")
internal object WoodTypeEntryLoader
{

    // @formatter:off

    lateinit var BANANA: WoodTypeEntry
    lateinit var ORANGE: WoodTypeEntry
    lateinit var MANGO: WoodTypeEntry
    lateinit var APRICOT: WoodTypeEntry
    lateinit var LEMON: WoodTypeEntry
    lateinit var LIME: WoodTypeEntry
    lateinit var OLIVE: WoodTypeEntry
    lateinit var NUTMEG: WoodTypeEntry
    lateinit var COCONUT: WoodTypeEntry
    lateinit var RAINBOW: WoodTypeEntry

    // TODO Doors and Boats supported for all woods.
    fun init()
    {
        BANANA = WoodTypeEntry.Builder(MOD_ID, "banana")
            .log(LogBlock.BANANA.stack)
            .planks(PlankBlock.BANANA.stack, null)
            .slab(WoodSlabBlock.BANANA.stack, null)
            .fence(WoodFenceBlock.BANANA.stack, null)
            .fenceGate(WoodFenceGateBlock.BANANA.stack, null)
            .stairs(WoodStairBlock.BANANA.stack, null)
            .registerAllOres()
            .registerAllUnificationInfo()
            .addCharcoalRecipe()
            .addStairsRecipe()
            .build()

        ORANGE = WoodTypeEntry.Builder(MOD_ID, "orange")
            .log(LogBlock.ORANGE.stack)
            .planks(PlankBlock.ORANGE.stack, null)
            .slab(WoodSlabBlock.ORANGE.stack, null)
            .fence(WoodFenceBlock.ORANGE.stack, null)
            .fenceGate(WoodFenceGateBlock.ORANGE.stack, null)
            .stairs(WoodStairBlock.ORANGE.stack, null)
            .registerAllOres()
            .registerAllUnificationInfo()
            .addCharcoalRecipe()
            .addStairsRecipe()
            .build()

        MANGO = WoodTypeEntry.Builder(MOD_ID, "mango")
            .log(LogBlock.MANGO.stack)
            .planks(PlankBlock.MANGO.stack, null)
            .slab(WoodSlabBlock.MANGO.stack, null)
            .fence(WoodFenceBlock.MANGO.stack, null)
            .fenceGate(WoodFenceGateBlock.MANGO.stack, null)
            .stairs(WoodStairBlock.MANGO.stack, null)
            .registerAllOres()
            .registerAllUnificationInfo()
            .addCharcoalRecipe()
            .addStairsRecipe()
            .build()

        APRICOT = WoodTypeEntry.Builder(MOD_ID, "apricot")
            .log(LogBlock.APRICOT.stack)
            .planks(PlankBlock.APRICOT.stack, null)
            .slab(WoodSlabBlock.APRICOT.stack, null)
            .fence(WoodFenceBlock.APRICOT.stack, null)
            .fenceGate(WoodFenceGateBlock.APRICOT.stack, null)
            .stairs(WoodStairBlock.APRICOT.stack, null)
            .registerAllOres()
            .registerAllUnificationInfo()
            .addCharcoalRecipe()
            .addStairsRecipe()
            .build()

        LEMON = WoodTypeEntry.Builder(MOD_ID, "lemon")
            .log(LogBlock.LEMON.stack)
            .planks(PlankBlock.LEMON.stack, null)
            .slab(WoodSlabBlock.LEMON.stack, null)
            .fence(WoodFenceBlock.LEMON.stack, null)
            .fenceGate(WoodFenceGateBlock.LEMON.stack, null)
            .stairs(WoodStairBlock.LEMON.stack, null)
            .registerAllOres()
            .registerAllUnificationInfo()
            .addCharcoalRecipe()
            .addStairsRecipe()
            .build()

        LIME = WoodTypeEntry.Builder(MOD_ID, "lime")
            .log(LogBlock.LIME.stack)
            .planks(PlankBlock.LIME.stack, null)
            .slab(WoodSlabBlock.LIME.stack, null)
            .fence(WoodFenceBlock.LIME.stack, null)
            .fenceGate(WoodFenceGateBlock.LIME.stack, null)
            .stairs(WoodStairBlock.LIME.stack, null)
            .registerAllOres()
            .registerAllUnificationInfo()
            .addCharcoalRecipe()
            .addStairsRecipe()
            .build()

        OLIVE = WoodTypeEntry.Builder(MOD_ID, "olive")
            .log(LogBlock.OLIVE.stack)
            .planks(PlankBlock.OLIVE.stack, null)
            .slab(WoodSlabBlock.OLIVE.stack, null)
            .fence(WoodFenceBlock.OLIVE.stack, null)
            .fenceGate(WoodFenceGateBlock.OLIVE.stack, null)
            .stairs(WoodStairBlock.OLIVE.stack, null)
            .registerAllOres()
            .registerAllUnificationInfo()
            .addCharcoalRecipe()
            .addStairsRecipe()
            .build()

        NUTMEG = WoodTypeEntry.Builder(MOD_ID, "nutmeg")
            .log(LogBlock.NUTMEG.stack)
            .planks(PlankBlock.NUTMEG.stack, null)
            .slab(WoodSlabBlock.NUTMEG.stack, null)
            .fence(WoodFenceBlock.NUTMEG.stack, null)
            .fenceGate(WoodFenceGateBlock.NUTMEG.stack, null)
            .stairs(WoodStairBlock.NUTMEG.stack, null)
            .registerAllOres()
            .registerAllUnificationInfo()
            .addCharcoalRecipe()
            .addStairsRecipe()
            .build()

        COCONUT = WoodTypeEntry.Builder(MOD_ID, "coconut")
            .log(LogBlock.COCONUT.stack)
            .planks(PlankBlock.COCONUT.stack, null)
            .slab(WoodSlabBlock.COCONUT.stack, null)
            .fence(WoodFenceBlock.COCONUT.stack, null)
            .fenceGate(WoodFenceGateBlock.COCONUT.stack, null)
            .stairs(WoodStairBlock.COCONUT.stack, null)
            .registerAllOres()
            .registerAllUnificationInfo()
            .addCharcoalRecipe()
            .addStairsRecipe()
            .build()

        RAINBOW = WoodTypeEntry.Builder(MOD_ID, "rainbow")
            .log(LogBlock.RAINBOW.stack)
            .planks(PlankBlock.RAINBOW.stack, null)
            .slab(WoodSlabBlock.RAINBOW.stack, null)
            .fence(WoodFenceBlock.RAINBOW.stack, null)
            .fenceGate(WoodFenceGateBlock.RAINBOW.stack, null)
            .stairs(WoodStairBlock.RAINBOW.stack, null)
            .registerAllOres()
            .registerAllUnificationInfo()
            .addCharcoalRecipe()
            .addStairsRecipe()
            .build()
    }

    // @formatter:on

}