package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.GTValues.VNF
import gregtech.api.capability.IOpticalComputationProvider
import gregtech.api.capability.IOpticalComputationReceiver
import gregtech.api.capability.impl.ComputationRecipeLogic
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.COMPUTATION_DATA_RECEPTION
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.recipes.Recipe
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.metatileentity.multiblock.RecipeMapModuleMultiblockController
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_MINER_RECIPES
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

class MultiblockSpaceMiner(id: ResourceLocation, tier: Int, moduleTier: Int, minCasingTier: Int)
    : RecipeMapModuleMultiblockController(id, SPACE_MINER_RECIPES, tier, moduleTier, minCasingTier),
      IOpticalComputationReceiver
{

    private var computationProvider: IOpticalComputationProvider? = null

    init
    {
        recipeMapWorkable = SpaceMinerRecipeLogic(this)
    }

    companion object
    {
        private val casingState = AerospaceCasing.ELEVATOR_BASE_CASING.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockSpaceMiner(metaTileEntityId, tier, moduleTier, minCasingTier)

    override fun initializeAbilities()
    {
        inputInventory = ItemHandlerList(getAbilities(IMPORT_ITEMS))
        outputInventory = ItemHandlerList(getAbilities(EXPORT_ITEMS))
        computationProvider = moduleProvider?.computationProvider
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("C", "C", "C", "C", "C")
        .aisle("C", "C", "C", "S", "C")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS, COMPUTATION_DATA_RECEPTION)
                    .setPreviewCount(0)))
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.SPACE_ELEVATOR_BASE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.SPACE_ELEVATOR_OVERLAY

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
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
                GTLiteOverlays.SPACE_MINER_OVERLAY.renderSided(renderSide, renderState, translation, pipeline)
            }
        }
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.space_miner_module.tooltip.1"))
        when (moduleTier)
        {
            1 -> tooltip.add(I18n.format("gtlitecore.machine.space_miner_module.mk1.tooltip.1"))
            2 -> tooltip.add(I18n.format("gtlitecore.machine.space_miner_module.mk2.tooltip.1"))
            else -> tooltip.add(I18n.format("gtlitecore.machine.space_miner_module.mk3.tooltip.1"))
        }
        tooltip.add(I18n.format("gtlitecore.machine.space_miner_module.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.space_miner_module.max_parallel", VNF[tier], recipeMapWorkable.parallelLimit))
        tooltip.add(I18n.format("gtlitecore.machine.space_miner_module.track_tier", getTrackTier(moduleTier)))
    }

    private fun getTrackTier(moduleTier: Int) = when (moduleTier) {
        1 -> "MK1"
        2 -> "MK2"
        else -> "MK3"
    }

    override fun canBeDistinct(): Boolean = true

    override fun getComputationProvider(): IOpticalComputationProvider? = computationProvider

    private inner class SpaceMinerRecipeLogic(mte: RecipeMapModuleMultiblockController)
        : ComputationRecipeLogic(mte, ComputationType.STEADY)
    {

        override fun getMetaTileEntity(): MetaTileEntity = (super.getMetaTileEntity() as MultiblockSpaceMiner)

        override fun checkRecipe(recipe: Recipe): Boolean
        {
            if (moduleProvider != null)
            {
                return super.checkRecipe(recipe) && recipe.getProperty(
                    GTLiteRecipeProperties.ACCELERATION_TRACK_TIER, 0)!! <= moduleProvider!!.casingTier
            }
            return false
        }

        override fun getParallelLimit(): Int = when (moduleTier) {
            1 -> 16
            2 -> 64
            else -> 256
        }
    }

}