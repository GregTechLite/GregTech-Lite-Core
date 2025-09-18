package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.IMufflerHatch
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BATH_CONDENSER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipDSL.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockCryogenicReactor(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(CRYOGENIC_REACTOR_RECIPES, BATH_CONDENSER_RECIPES))
{

    private var casingTier = 0

    init
    {
        recipeMapWorkable = LargeCryogenicReactorRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.MONEL_500.state
        private val secondCasingState = GTMetalCasing.STEEL_SOLID.state
        private val thirdCasingState = GTMultiblockCasing.GRATE_CASING.state
        private val pipeCasingState = GTBoilerCasing.STEEL_PIPE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockCryogenicReactor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        casingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        casingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("   FF", "   DU", "   UU", "   DU", "   DD")
        .aisle("F  FF", "CCCDD", "CCQQU", "CCCDD", "   DD")
        .aisle("     ", "CCCC ", "CPQQ ", "CCCC ", "     ")
        .aisle("F  F ", "CCCC ", "CSCC ", "CCCC ", "     ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(6)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('D', states(secondCasingState)
            .or(abilities(MultiblockAbility.MUFFLER_HATCH)
                .setExactLimit(1)))
        .where('U', states(thirdCasingState))
        .where('Q', states(pipeCasingState))
        .where('F', frames(HSLASteel))
        .where('P', pumpCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = if (sourcePart is IMufflerHatch)
        Textures.SOLID_STEEL_CASING // Muffler at Cooling Tower part (Steel Casing).
    else
        GTLiteTextures.MONEL_500_CASING // Other part at Main part (Monel-500 Casing).

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_CRYOGENIC_REACTOR_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("LCryR")
            description(true)
            overclockInfo(UV)
            durationInfo(UpgradeType.VOLTAGE_TIER, 50)
            parallelInfo(UpgradeType.PUMP_CASING, 16)
        }
    }

    override fun canBeDistinct() = true

    private inner class LargeCryogenicReactorRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(getTierByVoltage(maxVoltage).toDouble()))).toInt())
        }

        override fun getParallelLimit() = 16 * casingTier

    }

}
