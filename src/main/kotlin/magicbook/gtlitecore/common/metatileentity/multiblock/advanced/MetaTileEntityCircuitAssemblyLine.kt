package magicbook.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMaps
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.UP
import gregtech.api.util.RelativeDirection.RIGHT
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.TooltipHelper
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.BlockMultiblockCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import magicbook.gtlitecore.api.recipe.property.CircuitPatternProperty
import magicbook.gtlitecore.loader.recipe.producer.CircuitAssemblyLineRecipeProducer
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.world.World
import net.minecraftforge.items.IItemHandler

@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityCircuitAssemblyLine(metaTileEntityId: ResourceLocation) : MultiMapMultiblockController(metaTileEntityId, arrayOf(
    RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES, GTLiteRecipeMaps.CIRCUIT_ASSEMBLY_LINE_RECIPES))
{

    init
    {
        this.recipeMapWorkable = CircuitAssemblyLineRecipeLogic(this)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?): MetaTileEntity = MetaTileEntityCircuitAssemblyLine(metaTileEntityId)

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start(FRONT, UP, RIGHT)
        .aisle("FIF", "RTR", "SYG")
        .aisle("FIF", "RTR", "GYG")
        .setRepeatable(3, 15)
        .aisle("FOF", "RTR", "GYG")
        .where('S', selfPredicate())
        .where('F', states(getCasingState())
            .or(abilities(MultiblockAbility.MAINTENANCE_HATCH)
                .setExactLimit(1))
            .or(abilities(MultiblockAbility.IMPORT_FLUIDS)
                .setMaxGlobalLimited(4)
                .setPreviewCount(1)))
        .where('O', abilities(MultiblockAbility.EXPORT_ITEMS)
            .setExactLimit(1)
            .addTooltips("gregtech.multiblock.pattern.location_end"))
        .where('Y', states(getSecondCasingState())
            .or(abilities(MultiblockAbility.INPUT_ENERGY)
                .setMinGlobalLimited(1)
                .setMaxGlobalLimited(3)))
        .where('I', abilities(MultiblockAbility.IMPORT_ITEMS))
        .where('G', states(getSecondCasingState()))
        .where('R', states(getGlassState()))
        .where('T', states(getThirdCasingState()))
        .where(' ', any())
        .build()

    private fun getCasingState() = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)

    private fun getSecondCasingState() = MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)

    private fun getThirdCasingState() = MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_LINE_CASING)

    private fun getGlassState() = MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)

    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer
    {
        return if (sourcePart == null || (sourcePart is MetaTileEntityEnergyHatch))
            if (isStructureFormed) Textures.GRATE_CASING_STEEL_FRONT else Textures.SOLID_STEEL_CASING
        else Textures.SOLID_STEEL_CASING
    }

    override fun addInformation(stack: ItemStack?,
                                player: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.4"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.5"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.6"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.7"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.8"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.9"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.10"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.11"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.12"));
        tooltip.add(I18n.format("gtlitecore.machine.circuit_assembly_line.tooltip.13"));
    }

    override fun canBeDistinct(): Boolean = true

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    @Suppress("UNCHECKED_CAST")
    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        return if (getRecipeMap() == GTLiteRecipeMaps.CIRCUIT_ASSEMBLY_LINE_RECIPES)
        {
            val itemInputs = ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS) as MutableList<IItemHandler>)
            val targetStack = recipe.getProperty(CircuitPatternProperty.INSTANCE, null)

            val hasTargetCircuitPattern = targetStack?.let { stack ->
                (0 until itemInputs.slots).any { i ->
                    val currentStack: ItemStack = itemInputs.getStackInSlot(i)
                    return@any currentStack.isItemEqual(stack)
                }
            } ?: false

            super.checkRecipe(recipe, consumeIfSuccess) && hasTargetCircuitPattern
        }
        else super.checkRecipe(recipe, consumeIfSuccess)
    }

    fun getInputInventorySize() = getAbilities(MultiblockAbility.IMPORT_ITEMS).size

    inner class CircuitAssemblyLineRecipeLogic(tileEntity: RecipeMapMultiblockController) : MultiblockRecipeLogic(tileEntity, true)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            if (recipeMap == RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES)
                this.maxProgressTime = maxProgress / 2
            else
                this.maxProgressTime = maxProgress
        }

        override fun getParallelLimit(): Int = (getInputInventorySize() - 4) * 4

    }

}