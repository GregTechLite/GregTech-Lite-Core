package gregtechlite.gtlitecore.common.metatileentity.multiblock.mega

import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.RelativeDirection
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.STANDARD_STABILIZATION_FIELD_GEN_TIER
import gregtechlite.gtlitecore.api.capability.logic.ExtendedPowerMultiblockRecipeLogic
import gregtechlite.gtlitecore.api.metatileentity.multiblock.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.metatileentity.multiblock.OverclockMode
import gregtechlite.gtlitecore.api.metatileentity.multiblock.UpgradeMode
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.standardStabilizationFieldGens
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BLACKHOLE_FORMING_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.aerospace.AerospaceCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockBlackholeFormer(id: ResourceLocation) : RecipeMapMultiblockController(id, BLACKHOLE_FORMING_RECIPES)
{
    private var tier = 0

    init
    {
        recipeMapWorkable = BlackholeFormerRecipeLogic(this)
    }

    companion object
    {
        private val casingState = AerospaceCasing.ELEVATOR_BASE_CASING.state
        private val secondCasingState = ScienceCasing.ULTIMATE_MOLECULAR_CASING.state
        private val thirdCasingState = MultiblockCasing.ADVANCED_FILTER_CASING.state
        private val uniqueCasingState = MultiblockCasing.HAWKING_RADIATION_ABSORPTION_CASING.state
        private val glassState = GlassCasing.QUANTUM.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockBlackholeFormer(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        tier = context.getAttributeOrDefault(STANDARD_STABILIZATION_FIELD_GEN_TIER, 0)
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

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.LEFT)
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "     A                 A     ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "    A                   A    ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "   B                     B   ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "            CCCCC            ", "           CCCCCCC           ", "   B       CCCCCCC       B   ", "   D       CCCCCCC       D   ", "   B       CCCCCCC       B   ", "           CCCCCCC           ", "            CCCCC            ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "              C              ", "           CCCCCCC           ", "          CC     CC          ", "          C       C          ", "  B       C       C       B  ", "  D      CC       CC      D  ", "  B       C       C       B  ", "          C       C          ", "          CC     CC          ", "           CCCCCCC           ", "              C              ", "                             ", "                             ")
        .aisle("                             ", "              C              ", "           CCC CCC           ", "          C       C          ", "         C         C         ", "  B      C         C      B  ", " A       C         C       A ", " E      C           C      E ", " A       C         C       A ", "  B      C         C      B  ", "         C         C         ", "          C       C          ", "           CCC CCC           ", "              C              ", "                             ")
        .aisle("                             ", "           CCCCCCC           ", "          C       C          ", "         C         C         ", "        C           C        ", "  A     C           C     A  ", "  B     C           C     B  ", " B      C           C      B ", "  B     C           C     B  ", "  A     C           C     A  ", "        C           C        ", "         C         C         ", "          C       C          ", "           CCCCCCC           ", "                             ")
        .aisle("            CCCCC            ", "          CC     CC          ", "         C         C         ", "        C           C        ", "        C           C        ", "  B    C             C    B  ", " B     C             C     B ", " DF    C             C    FD ", " B     C             C     B ", "  B    C             C    B  ", "        C           C        ", "        C           C        ", "         C         C         ", "          CC     CC          ", "            CCCCC            ")
        .aisle("           CCCCCCC           ", "          C       C          ", "         C         C         ", "        C           C        ", "       C             C       ", " B     C             C     B ", " DF    C             C    FD ", " G     C             C     G ", " DF    C             C    FD ", " B     C             C     B ", "       C             C       ", "        C           C        ", "         C         C         ", "          C       C          ", "           CCCCCCC           ")
        .aisle("           CCCCCCC           ", "          C       C          ", "         C         C         ", "        C           C        ", " B     C             C     B ", " DF    C             C    FD ", "GG     C             C     GG", "GG     C             C     GG", "GG     C             C     GG", " DF    C             C    FD ", " B     C             C     B ", "        C           C        ", "         C         C         ", "          C       C          ", "           CCCCCCC           ")
        .aisle("           CCCCCCC           ", "         CC       CC         ", "        C           C        ", " B      C           C      B ", " DF    C             C    FD ", " G     C             C     G ", "GG     C             C     GG", "S      C             C       ", "GG     C             C     GG", " G     C             C     G ", " DF    C             C    FD ", " B      C           C      B ", "        C           C        ", "         CC       CC         ", "           CCCCCCC           ")
        .aisle("           CCCCCCC           ", "          C       C          ", "         C         C         ", "        C           C        ", " B     C             C     B ", " DF    C             C    FD ", "GG     C             C     GG", "GG     C             C     GG", "GG     C             C     GG", " DF    C             C    FD ", " B     C             C     B ", "        C           C        ", "         C         C         ", "          C       C          ", "           CCCCCCC           ")
        .aisle("           CCCCCCC           ", "          C       C          ", "         C         C         ", "        C           C        ", "       C             C       ", " B     C             C     B ", " DF    C             C    FD ", " G     C             C     G ", " DF    C             C    FD ", " B     C             C     B ", "       C             C       ", "        C           C        ", "         C         C         ", "          C       C          ", "           CCCCCCC           ")
        .aisle("            CCCCC            ", "          CC     CC          ", "         C         C         ", "        C           C        ", "        C           C        ", "  B    C             C    B  ", " B     C             C     B ", " DF    C             C    FD ", " B     C             C     B ", "  B    C             C    B  ", "        C           C        ", "        C           C        ", "         C         C         ", "          CC     CC          ", "            CCCCC            ")
        .aisle("                             ", "           CCCCCCC           ", "          C       C          ", "         C         C         ", "        C           C        ", "  A     C           C     A  ", "  B     C           C     B  ", " B      C           C      B ", "  B     C           C     B  ", "  A     C           C     A  ", "        C           C        ", "         C         C         ", "          C       C          ", "           CCCCCCC           ", "                             ")
        .aisle("                             ", "              C              ", "           CCC CCC           ", "          C       C          ", "         C         C         ", "  B      C         C      B  ", " A       C         C       A ", " E      C           C      E ", " A       C         C       A ", "  B      C         C      B  ", "         C         C         ", "          C       C          ", "           CCC CCC           ", "              C              ", "                             ")
        .aisle("                             ", "                             ", "              C              ", "           CCCCCCC           ", "          CC     CC          ", "          C       C          ", "  B       C       C       B  ", "  D      CC       CC      D  ", "  B       C       C       B  ", "          C       C          ", "          CC     CC          ", "           CCCCCCC           ", "              C              ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "            CCCCC            ", "           CCCCCCC           ", "   B       CCCCCCC       B   ", "   D       CCCCCCC       D   ", "   B       CCCCCCC       B   ", "           CCCCCCC           ", "            CCCCC            ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "   B                     B   ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "    A                   A    ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "     A                 A     ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .where('S', selfPredicate())
        .where('B', states(casingState)
            .setMinGlobalLimited(60)
            .or(abilities(INPUT_ENERGY)
                    .setPreviewCount(1))
            .or(abilities(INPUT_LASER)
                    .setPreviewCount(0)))
        .where('D', states(glassState))
        .where('F', states(thirdCasingState))
        .where('G', states(secondCasingState))
        .where('C', states(uniqueCasingState)
            .setMinGlobalLimited(560)
            .or(autoAbilities(false, false, true, true, false, false, false)))
        .where('A', frames(Pikyonium64B))
        .where('E', standardStabilizationFieldGens())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.SPACE_ELEVATOR_BASE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.BLACKHOLE_FORMER_OVERLAY

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gtlitecore.machine.blackhole_former.tooltip.1",
                               "gtlitecore.machine.blackhole_former.tooltip.2",
                               "gtlitecore.machine.blackhole_former.tooltip.3")
            addOverclockInfo(OverclockMode.PERFECT_DOUBLE)
            addParallelInfo("gtlitecore.machine.blackhole_former.tooltip.4")
            addDurationInfo(1200, UpgradeMode.STABILIZATION_FIELD_GEN)
            addMaxVoltageInfo()
            addLaserHatchInfo()
        }
    }

    override fun canBeDistinct(): Boolean = true

    override fun hasMaintenanceMechanics(): Boolean = false

    private inner class BlackholeFormerRecipeLogic(mte: RecipeMapMultiblockController) : ExtendedPowerMultiblockRecipeLogic(mte)
    {
        override fun getOverclockingDurationFactor(): Double = PERFECT_DURATION_FACTOR / 2

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)
            // +1200% / stab field gen | D' = D / (1 + 12.0 * (T - 1.0)) = D / (12.0 * T - 11.0), where k = 12.0
            val actualTier = tier + 1
            if (actualTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (12.0 * tier - 11.0)).toInt()))
        }

        override fun getParallelLimit(): Int = 4096 * (tier + 1)
    }
}