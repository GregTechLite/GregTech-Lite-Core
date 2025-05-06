package magicbook.gtlitecore.common.metatileentity.multiblock.primitive

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.ParallelLogicType
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.recipes.RecipeMaps.PRIMITIVE_BLAST_FURNACE_RECIPES
import gregtech.api.util.GTUtility.isBlockSnow
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.MetaBlocks
import magicbook.gtlitecore.api.capability.logic.NoEnergyMultiblockRecipeLogic
import magicbook.gtlitecore.api.pattern.GTLiteTraceabilityPredicate.Companion.scaleIndicatorPredicate
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityIndustrialPrimitiveBlastFurnace(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, PRIMITIVE_BLAST_FURNACE_RECIPES)
{

    private var size = 0

    init
    {
        recipeMapWorkable = IndustrialPBFRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityIndustrialPrimitiveBlastFurnace(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        size = context.getOrDefault("length", 1)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC", "CCC")
        .aisle("CCC", "C*C", "CIC", "C#C").setRepeatable(1, 16)
        .aisle("CCC", "CSC", "CCC", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS)))
        .where('I', scaleIndicatorPredicate())
        .where('*', air()
            .or(TraceabilityPredicate { bws -> isBlockSnow(bws.blockState) }))
        .where('#', air())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.PRIMITIVE_BRICKS

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY

    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<out IVertexOperation>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        frontOverlay.renderOrientedState(renderState, translation, pipeline, frontFacing,
            recipeMapWorkable.isActive, recipeMapWorkable.isWorkingEnabled)
    }

    override fun hasMaintenanceMechanics() = false

    override fun addInformation(stack: ItemStack?,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.industrial_primitive_blast_furnace.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.industrial_primitive_blast_furnace.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.industrial_primitive_blast_furnace.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.industrial_primitive_blast_furnace.tooltip.4"))
    }

    inner class IndustrialPBFRecipeLogic(metaTileEntity: RecipeMapMultiblockController) : NoEnergyMultiblockRecipeLogic(metaTileEntity)
    {

        override fun getParallelLimit() = (getMetaTileEntity() as MetaTileEntityIndustrialPrimitiveBlastFurnace).size

        override fun getMaxParallelVoltage(): Long = 2147432767L // Long.MAX_VALUE - 50800 EU

        override fun getParallelLogicType(): ParallelLogicType = ParallelLogicType.MULTIPLY

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow((getMetaTileEntity() as MetaTileEntityIndustrialPrimitiveBlastFurnace).size)).toInt())
        }

    }

}