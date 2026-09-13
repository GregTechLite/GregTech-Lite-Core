package gregtechlite.gtlitecore.common.metatileentity.multiblock.mega

import gregtech.api.GTValues
import gregtech.api.capability.IHeatingCoil
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.KeyManager
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.metatileentity.multiblock.ui.UISyncer
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.logic.OCParams
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.applyCoilEUtDiscount
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.recipes.properties.impl.TemperatureProperty
import gregtech.api.util.GTUtility
import gregtech.api.util.KeyUtil
import gregtech.api.util.RelativeDirection
import gregtech.api.util.TextFormattingUtil.formatNumbers
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.blocks.BlockWireCoil
import gregtechlite.gtlitecore.api.GTLiteAPI.COIL_TIER
import gregtechlite.gtlitecore.api.capability.logic.ExtendedPowerHeatingCoilRecipeLogic
import gregtechlite.gtlitecore.api.metatileentity.multiblock.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.metatileentity.multiblock.OverclockMode
import gregtechlite.gtlitecore.api.metatileentity.multiblock.UpgradeMode
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.coils
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.COMPLEX_PYROLYSIS_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumCarbide
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTFireboxCasing
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.BoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.Style
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max
import kotlin.math.min

class MultiblockConcentratedCokingCluster(id: ResourceLocation) : RecipeMapMultiblockController(id, COMPLEX_PYROLYSIS_RECIPES), IHeatingCoil
{
    private var tier = 0
    private var level = 0
    private var temperature = 0

    init
    {
        recipeMapWorkable
    }

    companion object
    {
        private val casingState = MetalCasing.TANTALUM_CARBIDE.state
        private val secondCasingState = GTFireboxCasing.TUNGSTENSTEEL_FIREBOX.state
        private val uniqueCasingState = ActiveUniqueCasing.HEAT_VENT.state
        private val pipeCasingState = BoilerCasing.POLYBENZIMIDAZOLE.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockConcentratedCokingCluster(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        tier = context.getAttributeOrDefault(COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).tier
        level = context.getAttributeOrDefault(COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).level

        temperature = context.getAttributeOrDefault(COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).coilTemperature
        temperature += 100 * max(0, GTUtility.getTierByVoltage(energyContainer.inputVoltage) - GTValues.MV)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        tier = 0
        level = 0
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

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start(RelativeDirection.LEFT, RelativeDirection.UP, RelativeDirection.BACK)
        .aisle("AAAAAAAAAAAAAAAAAAAAAAAAA   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "                            ")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAAAAAAA", " ACDCDCDCDCDCDCDCDCDCDCDCAAA", " ACECECECECECECECECECECECAAA", " ACECECECECECECECECECECECAA ", " ACECECECECECECECECECECECA  ", "  CDCDCDCDCDCDCDCDCDCDCDC   ", "                            ")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAAAAAAA", " AF F F F F F F F F F F AA A", " AFEFEFEFEFEFEFEFEFEFEFEAAAA", " AF F F F F F F F F F F A F ", " AFEFEFEFEFEFEFEFEFEFEFEA F ", "  F F F F F F F F F F F F F ", "  FFFFFFFFFFFFFFFFFFFFFFFFF ")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAAAAAAA", " ACDCDCDCDCDCDCDCDCDCDCDCASA", " ACECECECECECECECECECECECAAA", " ACECECECECECECECECECECECAA ", " ACECECECECECECECECECECECA  ", "  CDCDCDCDCDCDCDCDCDCDCDC   ", "                            ")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAAAA   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "                            ")
        .where('S', selfPredicate())
        .where('A', states(casingState)
            .setMinGlobalLimited(150)
            .or(abilities(INPUT_ENERGY)
                    .setPreviewCount(1))
            .or(abilities(INPUT_LASER)
                    .setPreviewCount(0))
            .or(autoAbilities(false, false, true, true, true, true, false)))
        .where('C', states(secondCasingState))
        .where('B', frames(TantalumCarbide))
        .where('D', states(uniqueCasingState))
        .where('F', states(pipeCasingState))
        .where('E', coils())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PYROLYSE_OVEN_OVERLAY

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.TANTALUM_CARBIDE_CASING

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gregtech.machine.electric_blast_furnace.tooltip.1",
                               "gregtech.machine.electric_blast_furnace.tooltip.2",
                               "gregtech.machine.electric_blast_furnace.tooltip.3")
            addOverclockInfo(OverclockMode.PERFECT_DOUBLE)
            addParallelInfo(32, UpgradeMode.WIRE_COIL_TEMPERATURE)
            addDurationInfo(400, UpgradeMode.WIRE_COIL)
            addEnergyInfo(50)
            addMaxVoltageInfo()
            addLaserHatchInfo()
        }
    }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(energyContainer) // Deleted energy tier line because this machine not used those logic.
            .addCustom(this::addHeatCapacity)
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progress, recipeMapWorkable.maxProgress)
            .addRecipeOutputLine(recipeMapWorkable)
    }

    private fun addHeatCapacity(keyManager: KeyManager, syncer: UISyncer)
    {
        if (isStructureFormed)
        {
            val heatKey = KeyUtil.number(TextFormatting.RED, syncer.syncInt(currentTemperature).toLong(), "K")
            keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                                        "gregtech.multiblock.blast_furnace.max_temperature", heatKey))
        }
    }

    override fun canBeDistinct(): Boolean = true

    override fun hasMaintenanceMechanics(): Boolean = false

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
        = temperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0)!!

    override fun getDataInfo(): List<ITextComponent?>
    {
        val components = super.getDataInfo()
        components.add(TextComponentTranslation("gregtech.multiblock.blast_furnace.max_temperature",
                                                TextComponentTranslation(formatNumbers(temperature) + "K")
                                                    .setStyle(Style().setColor(TextFormatting.RED))))
        return components
    }

    override fun getCurrentTemperature(): Int = temperature

    private inner class ConcentratedCokingClusterRecipeLogic(mte: RecipeMapMultiblockController)
        : ExtendedPowerHeatingCoilRecipeLogic(mte)
    {
        override fun getOverclockingDurationFactor(): Double = PERFECT_DURATION_FACTOR / 2

        override fun modifyOverclockPre(ocParams: OCParams, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPre(ocParams, storage)
            ocParams.setEut(applyCoilEUtDiscount(ocParams.eut(), (metaTileEntity as IHeatingCoil).currentTemperature,
                                                 storage.get(TemperatureProperty.getInstance(), 0)!!))
        }

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)
            // -50%
            ocResult.setEut(max(1, (ocResult.eut() * 0.5).toLong()))

            // +400% / coil tier | D' = D / (1 + 4.0 * (T - 1.0)) = D / (4.0 * T - 3.0), where k = 4.0
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (4.0 * tier - 3.0)).toInt()))
        }

        override fun getParallelLimit(): Int = min(level * 16L * currentTemperature, Int.MAX_VALUE.toLong()).toInt()
    }
}