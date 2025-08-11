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
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.CONVEYOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.EMITTER_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.conveyorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.emitterCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockLaserEngraver(id: ResourceLocation)
    : RecipeMapMultiblockController(id, LASER_ENGRAVER_RECIPES)
{

    private var emitterCasingTier = 0
    private var conveyorCasingTier = 0
    private var tier = 0

    init
    {
        this.recipeMapWorkable = LargeLaserEngraverRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.ZERON_100.state

        private val glassState
            get() = GlassCasing.WOODS.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockLaserEngraver(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        this.conveyorCasingTier = context.getAttributeOrDefault(CONVEYOR_CASING_TIER, 0)
        this.tier = minOf(emitterCasingTier, conveyorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.emitterCasingTier = 0
        this.conveyorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC", " C ")
        .aisle("CCC", "GMG", "CEC", " C ")
        .aisle("CCC", "GMG", "CEC", " C ")
        .aisle("CCC", "GMG", "CEC", " C ")
        .aisle("CCC", "CSC", "CCC", " C ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(4)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('G', states(glassState))
        .where('M', conveyorCasings())
        .where('E', emitterCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.ZERON_100_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.LASER_ENGRAVER_OVERLAY

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_laser_engraver.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_laser_engraver.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_laser_engraver.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_laser_engraver.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class LargeLaserEngraverRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(conveyorCasingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * emitterCasingTier

    }

}