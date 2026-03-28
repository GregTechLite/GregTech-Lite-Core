package gregtechlite.gtlitecore.common.metatileentity.multiblock.mega

import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityFluidHatch
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityItemBus
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityLaserHatch
import gregtech.common.metatileentities.multi.multiblockpart.appeng.MetaTileEntityMEInputBus
import gregtech.common.metatileentities.multi.multiblockpart.appeng.MetaTileEntityMEInputHatch
import gregtechlite.gtlitecore.api.GTLiteAPI.FUSION_COIL_TIER
import gregtechlite.gtlitecore.api.capability.logic.ExtendedPowerMultiblockRecipeLogic
import gregtechlite.gtlitecore.api.metatileentity.multiblock.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.metatileentity.multiblock.OverclockMode
import gregtechlite.gtlitecore.api.metatileentity.multiblock.UpgradeMode
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.fusionCoils
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.RECOMBINATION_SUBMODULE_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SOLIDIFICATION_SUBMODULE_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTComputerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max
import kotlin.math.pow

class MultiblockMatterReshapingFramework(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(SOLIDIFICATION_SUBMODULE_RECIPES, RECOMBINATION_SUBMODULE_RECIPES))
{

    private var tier = 0

    init
    {
        recipeMapWorkable = MatterReshapingFrameworkRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.NEUTRONIUM.state
        private val secondCasingState = GTComputerCasing.HIGH_POWER_CASING.state
        private val thirdCasingState = MultiblockCasing.PARTICLE_CONTAINMENT_CASING.state
        private val pipeCasingState = ScienceCasing.HOLLOW_CASING.state
        private val uniqueCasingState = ActiveUniqueCasing.HEAT_VENT.state

        private val glassState = GTGlassCasing.FUSION_GLASS.state
        private val secondGlassState = GlassCasing.QUANTUM.state
        private val coilState = ScienceCasing.MOLECULAR_COIL.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockMatterReshapingFramework(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        tier = context.getAttributeOrDefault(FUSION_COIL_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        tier = 0
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(SUBSTATION_INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        energyContainer = EnergyContainerList(inputEnergy)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("AAA          AAA", "ABA          ABA", "AAA          AAA", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "AAA          AAA", "ABA          ABA", "AAA          AAA")
        .aisle("ABA          ABA", "BCBDAAAAAAAADBCB", "AAA          AAA", " D  DEEFFEED  D ", " A DEEGGGGEED A ", " A EEGGGGGGEE A ", " A EGGGGGGGGE A ", " A FGGGGGGGGF A ", " A FGGGGGGGGF A ", " A EGGGGGGGGE A ", " A EEGGGGGGEE A ", " A DEEGGGGEED A ", " D  DEEFFEED  D ", "ABA          ABA", "BCBDAAAAAAAADBCB", "ABA          ABA")
        .aisle("AAA          AAA", "ABA          ABA", "AAA          AAA", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "AAA          AAA", "ABA          ABA", "AAA          AAA")
        .aisle("                ", " D  DEEFFEED  D ", "                ", "                ", " D            D ", " E            E ", " E            E ", " F            F ", " F            F ", " E            E ", " E            E ", " D            D ", "                ", "                ", " D  DEEFFEED  D ", "                ")
        .aisle("                ", " A DEEGGGGEED A ", "                ", " D            D ", " E  AA    AA  E ", " E  AA    AA  E ", " G    HHHH      ", " G    HHHH      ", " G    HHHH      ", " G    HHHH      ", " E  AA    AA  E ", " E  AA    AA  E ", " D            D ", "                ", " A DEEGGGGEED A ", "                ")
        .aisle("                ", " A EEGGGGGGEE A ", "                ", " E            E ", " E  AA    AA  E ", " G  AAAAAAAA    ", " G   A    A     ", " G   A    A     ", " G   A    A     ", " G   A    A     ", " G  AAAAAAAA    ", " E  AA    AA  E ", " E            E ", "                ", " A EEGGGGGGEE A ", "                ")
        .aisle("                ", " A EGGGGGGGGE A ", "                ", " E            E ", " G    HHHH      ", " G   A    A     ", " G  H      H    ", " G  H  II  H    ", " G  H  II  H    ", " G  H      H    ", " G   A    A     ", " G    HHHH      ", " E            E ", "                ", " A EGGGGGGGGE A ", "                ")
        .aisle("                ", " A FGGGGGGGGF A ", "                ", " F            F ", " G    HHHH      ", " G   A    A     ", " G  H  II  H    ", " G  H IIII H    ", " G  H IIII H    ", " G  H  II  H    ", " G   A    A     ", " G    HHHH      ", " F            F ", "                ", " A FGGGGGGGGF A ", "                ")
        .aisle("                ", " A FGGGGGGGGF A ", "                ", " F            F ", " G    HHHH      ", " G   A    A     ", " G  H  II  H    ", " G  H IIII H    ", " G  H IIII H    ", " G  H  II  H    ", " G   A    A     ", " G    HHHH      ", " F            F ", "                ", " A FGGGGGGGGF A ", "                ")
        .aisle("                ", " A EGGGGGGGGE A ", "                ", " E            E ", " G    HHHH      ", " G   A    A     ", " G  H      H    ", " G  H  II  H    ", " G  H  II  H    ", " G  H      H    ", " G   A    A     ", " G    HHHH      ", " E            E ", "                ", " A EGGGGGGGGE A ", "                ")
        .aisle("                ", " A EEGGGGGGEE A ", "                ", " E            E ", " E  AA    AA  E ", " G  AAAAAAAA    ", " G   A    A     ", " G   A    A     ", " G   A    A     ", " G   A    A     ", " G  AAAAAAAA    ", " E  AA    AA  E ", " E            E ", "                ", " A EEGGGGGGEE A ", "                ")
        .aisle("                ", " A DEEGGGGEED A ", "                ", " D            D ", " E  AA    AA  E ", " E  AA    AA  E ", " G    HHHH      ", " G    HHHH      ", " G    HHHH      ", " G    HHHH      ", " E  AA    AA  E ", " E  AA    AA  E ", " D            D ", "                ", " A DEEGGGGEED A ", "                ")
        .aisle("                ", " D  DEEFFEED  D ", "                ", "                ", " D            D ", " E            E ", " E            E ", " F            F ", " F            F ", " E            E ", " E            E ", " D            D ", "                ", "                ", " D  DEEFFEED  D ", "                ")
        .aisle("AAA          AAA", "ABA          ABA", "AAA          AAA", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "AAA          AAA", "ABA          ABA", "AAA          AAA")
        .aisle("ABA          ABA", "BCBDAAAAAAAADBCB", "AAA          AAA", " D  DEEFFEED  D ", " A DEEGGGGEED A ", " A EEGGGGGGEE A ", " A EGGGGGGGGE A ", " A FGGGGGGGGF A ", " A FGGGGGGGGF A ", " A EGGGGGGGGE A ", " A EEGGGGGGEE A ", " A DEEGGGGEED A ", " D  DEEFFEED  D ", "ABA          ABA", "BCBDAAAAAAAADBCB", "ABA          ABA")
        .aisle("AAA          AAA", "ASA          ABA", "AAA          AAA", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "AAA          AAA", "ABA          ABA", "AAA          AAA")
        .where('S', selfPredicate())
        .where('A', states(casingState)
            .setMinGlobalLimited(280)
            .or(autoAbilities(false, false, true, true, true, true, false))
            .or(abilities(INPUT_ENERGY)
                    .setPreviewCount(1))
            .or(abilities(INPUT_LASER)
                    .setPreviewCount(0)))
        .where('B', states(coilState))
        .where('C', states(pipeCasingState))
        .where('D', states(secondCasingState))
        .where('E', states(thirdCasingState))
        .where('F', states(uniqueCasingState))
        .where('G', states(glassState))
        .where('H', states(secondGlassState))
        .where('I', fusionCoils())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = when (sourcePart)
    {
        is MetaTileEntityItemBus      -> GTLiteOverlays.NEUTRONIUM_CASING
        is MetaTileEntityFluidHatch   -> GTLiteOverlays.NEUTRONIUM_CASING
        is MetaTileEntityEnergyHatch  -> GTLiteOverlays.NEUTRONIUM_CASING
        is MetaTileEntityLaserHatch   -> GTLiteOverlays.NEUTRONIUM_CASING
        is MetaTileEntityMEInputBus   -> GTLiteOverlays.NEUTRONIUM_CASING
        is MetaTileEntityMEInputHatch -> GTLiteOverlays.NEUTRONIUM_CASING
        else -> GTLiteOverlays.MOLECULAR_CASING
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gtlitecore.machine.matter_reshaping_framework.tooltip.1")
            addDescriptionLine("gtlitecore.machine.matter_reshaping_framework.tooltip.2")
            addDescriptionLine("gtlitecore.machine.matter_reshaping_framework.tooltip.3")
            addDescriptionLine("gtlitecore.machine.matter_reshaping_framework.tooltip.4")
            addDescriptionLine("gtlitecore.machine.matter_reshaping_framework.tooltip.5")
            addDescriptionLine("gtlitecore.machine.matter_reshaping_framework.tooltip.6")
            addDescriptionLine("gtlitecore.machine.matter_reshaping_framework.tooltip.7")
            addOverclockInfo(OverclockMode.PERFECT_DOUBLE)
            addDescriptionLine(I18n.format("gtlitecore.tooltip.machine.parallel_mode")
                                       + I18n.format("gtlitecore.machine.matter_reshaping_framework.tooltip.8"))
            addDurationInfo(UpgradeMode.VOLTAGE_TIER, 1200)
            addEnergyInfo(UpgradeMode.FUSION_COIL, 10)
            addMaxVoltageInfo()
            addLaserHatchInfo()
        }
    }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(energyContainer) // Deleted energy tier line because this machine not used those logic.
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progress, recipeMapWorkable.maxProgress)
            .addRecipeOutputLine(recipeMapWorkable)
    }

    override fun canBeDistinct(): Boolean = true

    override fun hasMaintenanceMechanics(): Boolean = false

    private inner class MatterReshapingFrameworkRecipeLogic(mte: RecipeMapMultiblockController)
        : ExtendedPowerMultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor(): Double = PERFECT_DURATION_FACTOR / 2

        override fun getParallelLimit(): Int = (256.0.pow(((tier + 1) * getTierByVoltage(maxVoltage))))
            .toInt().coerceIn(0, Int.MAX_VALUE)

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)
            if (tier <= 0) return

            // -10% / fusion coil tiier
            ocResult.setEut(max(1, (ocResult.eut() * (1.0 - tier * 0.1)).toLong()))

            // +1200% / voltage tier | D' = D / (1 + 12.0 * (T - 1.0)) = D / (12.0 * T - 11.0), where k = 12.0
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (12 * getTierByVoltage(maxVoltage) - 11.0)).toInt()))
        }

    }

}