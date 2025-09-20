package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.GTValues.VNF
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.*
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.recipes.Recipe
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.metatileentity.multiblock.RecipeMapModuleMultiblockController
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.aerospace.AerospaceCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockSpaceAssembler(metaTileEntityId: ResourceLocation,
                               tier: Int,
                               moduleTier: Int,
                               minCasingTier: Int)
    : RecipeMapModuleMultiblockController(metaTileEntityId, SPACE_ASSEMBLER_RECIPES, tier, moduleTier, minCasingTier)
{

    init
    {
        recipeMapWorkable = SpaceAssemblerRecipeLogic(this)
    }

    companion object
    {
        private val casingState = AerospaceCasing.ELEVATOR_BASE_CASING.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockSpaceAssembler(metaTileEntityId, tier, moduleTier, minCasingTier)

    override fun initializeAbilities()
    {
        inputInventory = ItemHandlerList(getAbilities(IMPORT_ITEMS))
        inputFluidInventory = FluidTankList(allowSameFluidFillForOutputs(), getAbilities(IMPORT_FLUIDS))
        extendedFluidInputs = extendedImportFluidList(inputFluidInventory)
        outputInventory = ItemHandlerList(getAbilities(EXPORT_ITEMS))
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("C", "C", "C", "C", "C")
        .aisle("C", "C", "C", "S", "C")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS, IMPORT_FLUIDS)
                .setPreviewCount(0)))
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.SPACE_ELEVATOR_BASE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.SPACE_ELEVATOR_OVERLAY

    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?, pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        for (renderSide in EnumFacing.HORIZONTALS)
        {
            if (renderSide == getFrontFacing())
            {
                getFrontOverlay().renderOrientedState(renderState, translation, pipeline,
                    getFrontFacing(), isActive(), true)
            }
            else
            {
                GTLiteOverlays.SPACE_ASSEMBLER_OVERLAY.renderSided(renderSide, renderState, translation, pipeline)
            }
        }
    }

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.space_assembler_module.tooltip"))
        when (moduleTier)
        {
            1 -> tooltip.add(I18n.format("gtlitecore.machine.space_assembler_module.mk1.tooltip.1"))
            2 -> tooltip.add(I18n.format("gtlitecore.machine.space_assembler_module.mk2.tooltip.1"))
            else -> tooltip.add(I18n.format("gtlitecore.machine.space_assembler_module.mk3.tooltip.1"))
        }
        tooltip.add(I18n.format("gtlitecore.machine.space_assembler_module.max_parallel", VNF[tier], recipeMapWorkable.parallelLimit))
        tooltip.add(I18n.format("gtlitecore.machine.space_assembler_module.orbit_tier", getOrbitTier(moduleTier)))
    }

    private fun getOrbitTier(moduleTier: Int) = when (moduleTier)
    {
        1 -> "MK1"
        2 -> "MK2"
        else -> "MK3"
    }

    override fun canBeDistinct() = true

    private inner class SpaceAssemblerRecipeLogic(metaTileEntity: RecipeMapModuleMultiblockController?) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun checkRecipe(recipe: Recipe): Boolean
        {
            if (moduleProvider != null)
            {
                return super.checkRecipe(recipe)
                        && recipe.getProperty(GTLiteRecipeProperties.ACCELERATION_TRACK_TIER, 0)!! <= moduleProvider!!.casingTier
            }
            return false
        }

        override fun getParallelLimit() = when (moduleTier)
        {
            1 -> 4
            2 -> 16
            else -> 64
        }

    }

}
