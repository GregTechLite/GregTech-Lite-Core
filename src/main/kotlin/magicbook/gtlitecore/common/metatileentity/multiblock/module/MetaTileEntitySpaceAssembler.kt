package magicbook.gtlitecore.common.metatileentity.multiblock.module

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.GTValues.VNF
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.gui.resources.TextureArea
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.recipes.Recipe
import gregtech.client.renderer.ICubeRenderer
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures
import magicbook.gtlitecore.api.metatileentity.multiblock.RecipeMapModuleMultiblockController
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SPACE_ASSEMBLER_RECIPES
import magicbook.gtlitecore.api.recipe.property.AccelerationOrbitTieredProperty
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockSpaceElevatorCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MetaTileEntitySpaceAssembler(metaTileEntityId: ResourceLocation?, tier: Int, moduleTier: Int, minCasingTier: Int) : RecipeMapModuleMultiblockController(metaTileEntityId, SPACE_ASSEMBLER_RECIPES, tier, moduleTier, minCasingTier)
{

    init
    {
        recipeMapWorkable = SpaceAssemblerRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = GTLiteMetaBlocks.SPACE_ELEVATOR_CASING.getState(BlockSpaceElevatorCasing.CasingType.BASE_CASING)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntitySpaceAssembler(metaTileEntityId, tier, moduleTier, minCasingTier)

    override fun initializeAbilities()
    {
        inputInventory = ItemHandlerList(getAbilities(IMPORT_ITEMS))
        inputFluidInventory = FluidTankList(allowSameFluidFillForOutputs(), getAbilities(IMPORT_FLUIDS))
        extendedFluidInputs = extendedImportFluidList(inputFluidInventory)
        outputInventory = ItemHandlerList(getAbilities(EXPORT_ITEMS))
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("C", "C", "C", "C", "C")
        .aisle("C", "C", "C", "S", "C")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS, IMPORT_FLUIDS)
                .setPreviewCount(0)))
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.SPACE_ELEVATOR_BASE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.SPACE_ELEVATOR_OVERLAY

    override fun getLogo(): TextureArea = GTLiteGuiTextures.SPACE_ELEVATOR_LOGO_DARK

    override fun getWarningLogo(): TextureArea = GTLiteGuiTextures.SPACE_ELEVATOR_LOGO_DARK

    override fun getErrorLogo(): TextureArea = GTLiteGuiTextures.SPACE_ELEVATOR_LOGO_DARK

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
                GTLiteTextures.SPACE_ASSEMBLER_OVERLAY.renderSided(renderSide, renderState, translation, pipeline)
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
            if (getModuleProvider() != null)
            {
                return super.checkRecipe(recipe)
                        && recipe.getProperty(AccelerationOrbitTieredProperty.getInstance(), 0)!! <= getModuleProvider()!!.getCasingTier()
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
