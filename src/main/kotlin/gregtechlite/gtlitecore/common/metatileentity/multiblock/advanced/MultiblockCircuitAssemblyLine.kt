package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.MultiblockAbility.*
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.util.RelativeDirection.*
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.TooltipHelper
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch
import gregtech.core.sound.GTSoundEvents
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CIRCUIT_ASSEMBLY_LINE_RECIPES
import gregtechlite.gtlitecore.api.recipe.property.CircuitPatternProperty
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockCircuitAssemblyLine(id: ResourceLocation) :
    MultiMapMultiblockController(id, arrayOf(CIRCUIT_ASSEMBLER_RECIPES, CIRCUIT_ASSEMBLY_LINE_RECIPES))
{

    init
    {
        this.recipeMapWorkable = CircuitAssemblyLineRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = GTMetalCasing.STEEL_SOLID.state

        private val secondCasingState
            get() = GTMultiblockCasing.GRATE_CASING.state

        private val thirdCasingState
            get() = GTMultiblockCasing.ASSEMBLY_LINE_CASING.state

        private val glassState
            get() = GTGlassCasing.LAMINATED_GLASS.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockCircuitAssemblyLine(metaTileEntityId)

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start(FRONT, UP, RIGHT)
        .aisle("FIF", "RTR", "SYG")
        .aisle("FIF", "RTR", "GYG")
        .setRepeatable(3, 15)
        .aisle("FOF", "RTR", "GYG")
        .where('S', selfPredicate())
        .where('F', states(casingState)
            .or(abilities(MAINTENANCE_HATCH)
                    .setExactLimit(1))
            .or(abilities(IMPORT_FLUIDS)
                    .setMaxGlobalLimited(4)
                    .setPreviewCount(1)))
        .where('O', abilities(EXPORT_ITEMS)
            .setExactLimit(1)
            .addTooltips("gregtech.multiblock.pattern.location_end"))
        .where('Y', states(secondCasingState)
            .or(abilities(INPUT_ENERGY)
                    .setMinGlobalLimited(1)
                    .setMaxGlobalLimited(3)))
        .where('I', abilities(IMPORT_ITEMS))
        .where('G', states(secondCasingState))
        .where('R', states(glassState))
        .where('T', states(thirdCasingState))
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer
    {
        return if (sourcePart == null || (sourcePart is MetaTileEntityEnergyHatch))
            if (isStructureFormed)
                Textures.GRATE_CASING_STEEL_FRONT
            else
                Textures.SOLID_STEEL_CASING
        else
            Textures.SOLID_STEEL_CASING
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.7"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.8"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.9"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.10"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.11"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.12"))
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.13"))
    }

    override fun canBeDistinct() = true

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        return if (getRecipeMap() == CIRCUIT_ASSEMBLY_LINE_RECIPES)
        {
            val itemInputs = ItemHandlerList(getAbilities(IMPORT_ITEMS))
            val targetStack = recipe.getProperty(CircuitPatternProperty, null)
            val hasTargetCircuitPattern = targetStack?.let { stack ->
                (0 until itemInputs.slots).any { i ->
                    val currentStack: ItemStack = itemInputs.getStackInSlot(i)
                    return@any currentStack.isItemEqual(stack)
                }
            } ?: false

            super.checkRecipe(recipe, consumeIfSuccess) && hasTargetCircuitPattern
        } else super.checkRecipe(recipe, consumeIfSuccess)
    }

    fun getInputInventorySize() = getAbilities(IMPORT_ITEMS).size

    private inner class CircuitAssemblyLineRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte, true)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            maxProgressTime = if (recipeMap == CIRCUIT_ASSEMBLER_RECIPES) maxProgress / 2 else maxProgress
        }

        override fun getParallelLimit() = (getInputInventorySize() - 4) * 4

    }

}