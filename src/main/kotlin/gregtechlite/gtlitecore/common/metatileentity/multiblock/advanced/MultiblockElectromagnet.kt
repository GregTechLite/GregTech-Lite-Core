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
import gregtech.api.recipes.RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.POLARIZER_RECIPES
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.FIELD_GEN_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.fieldGenCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockElectromagnet(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(ELECTROMAGNETIC_SEPARATOR_RECIPES, POLARIZER_RECIPES))
{

    private var casingTier = 0

    init
    {
        recipeMapWorkable = LargeElectromagnetRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.BABBIT_ALLOY.state
        private val secondCasingState = GTMultiblockCasing.GRATE_CASING.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockElectromagnet(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        casingTier = context.getAttributeOrDefault(FIELD_GEN_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        casingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " CCC ", " CCC ")
        .aisle("CCCCC", "C###C", "CGCGC")
        .aisle("CCCCC", "C#F#C", "CGCGC")
        .aisle("CCCCC", "C###C", "CGCGC")
        .aisle(" CCC ", " CSC ", " CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(8)
            .or(autoAbilities(true, true, true, true, true, false, false)))
        .where('G', states(secondCasingState))
        .where('F', fieldGenCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.BABBIT_ALLOY_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.LARGE_ELECTROMAGNET_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine("LEm")
            addDescriptionLine(true)
            overclockInfo(UV)
            durationInfo(UpgradeType.VOLTAGE_TIER, 50)
            parallelInfo(UpgradeType.FIELD_GEN_CASING, 8)
        }
    }

    override fun canBeDistinct() = true

    private inner class LargeElectromagnetRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.5.pow(getTierByVoltage(maxVoltage).toDouble())).toInt())
        }

        override fun getParallelLimit() = 8 * casingTier

    }

}
