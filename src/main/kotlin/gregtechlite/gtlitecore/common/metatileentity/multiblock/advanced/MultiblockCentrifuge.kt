package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.THERMAL_CENTRIFUGE_RECIPES
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockCentrifuge(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(CENTRIFUGE_RECIPES, THERMAL_CENTRIFUGE_RECIPES))
{

    private var casingTier = 0

    init
    {
        recipeMapWorkable = LargeCentrifugeRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.TUMBAGA.state
        private val secondCasingState = GTMultiblockCasing.GRATE_CASING.state
        private val pipeCasingState = GTBoilerCasing.STEEL_PIPE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockCentrifuge(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        casingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        casingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", "CCGCC", " CCC ")
        .aisle("CCCCC", "C###C", "CCCCC")
        .aisle("CCMCC", "G#P#G", "CCGCC")
        .aisle("CCCCC", "C###C", "CCCCC")
        .aisle(" CCC ", "CCSCC", " CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(10)
            .or(this.autoAbilities(true, true, true, true, true, true, false)))
        .where('G', states(secondCasingState))
        .where('P', states(pipeCasingState))
        .where('M', motorCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.TUMBAGA_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.THERMAL_CENTRIFUGE_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine("LCen")
            addDescriptionLine(true)
            overclockInfo(UV)
            durationInfo(UpgradeType.MOTOR_CASING, 50)
            parallelInfo(UpgradeType.VOLTAGE_TIER, 8)
        }
    }

    override fun canBeDistinct() = true

    private inner class LargeCentrifugeRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(casingTier))).toInt())
        }

        override fun getParallelLimit() = 8 * getTierByVoltage(maxVoltage)

    }

}
