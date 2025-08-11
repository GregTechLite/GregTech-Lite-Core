package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.EXTRUDER_RECIPES
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.PISTON_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pistonCasings
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockExtruder(id: ResourceLocation)
    : RecipeMapMultiblockController(id, EXTRUDER_RECIPES)
{

    private var casingTier = 0

    init
    {
        this.recipeMapWorkable = LargeExtruderRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.INCONEL_625.state

        private val pipeCasingState
            get() = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MultiblockExtruder(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.casingTier = context.getAttributeOrDefault(PISTON_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.casingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCC", "CCCC", "CCC ")
        .aisle("CCCC", "CQPC", "CCC ")
        .aisle("CCCC", "CQPC", "CCC ")
        .aisle("CCCC", "CSCC", "CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(6)
            .or(this.autoAbilities(true, true, true, true, false, false, false)))
        .where('Q', states(pipeCasingState))
        .where('P', pistonCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.INCONEL_625_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_extruder.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_extruder.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_extruder.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_extruder.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class LargeExtruderRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(casingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * casingTier

    }

}
