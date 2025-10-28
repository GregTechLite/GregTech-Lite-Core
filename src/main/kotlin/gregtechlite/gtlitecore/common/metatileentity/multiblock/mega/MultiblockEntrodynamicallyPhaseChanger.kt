package gregtechlite.gtlitecore.common.metatileentity.multiblock.mega

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VOC
import gregtech.api.capability.IHeatingCoil
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.HeatingCoilRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
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
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.recipes.properties.impl.TemperatureProperty
import gregtech.api.util.GTUtility.getFloorTierByVoltage
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.KeyUtil
import gregtech.api.util.TextFormattingUtil.formatNumbers
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.blocks.BlockWireCoil
import gregtechlite.gtlitecore.api.GTLiteAPI.COIL_TIER
import gregtechlite.gtlitecore.api.capability.logic.ExtendedPowerHeatingCoilRecipeLogic
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.coils
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ALLOY_BLAST_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
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
import kotlin.math.pow

class MultiblockEntrodynamicallyPhaseChanger(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(TOPOLOGICAL_ORDER_CHANGING_RECIPES, ALLOY_BLAST_RECIPES)), IHeatingCoil
{

    private var tier = 0
    private var level = 0
    private var temperature = 0

    init
    {
        recipeMapWorkable = EntrodynamicallyPhaseChangerRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MultiblockCasing.LATTICE_QCD_THERMAL_SHIELDING_CASING.state
        private val secondCasingState = MultiblockCasing.HAMILTON_KILLING_FLOW_CONTROL_CASING.state
        private val thirdCasingState = MetalCasing.NEUTRONIUM.state
        private val fourthCasingState = ScienceCasing.DIMENSIONAL_BRIDGE_CASING.state
        private val glassState = GlassCasing.NANO_SHIELDING_FRAME.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MultiblockEntrodynamicallyPhaseChanger(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)

        tier = context.getAttributeOrDefault(COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).tier
        level = context.getAttributeOrDefault(COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).level

        temperature = context.getAttributeOrDefault(COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).coilTemperature
        temperature += 1000 * max(0, getTierByVoltage(energyContainer.inputVoltage) - LV)
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

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("         AAAAA         ", "         AAAAA         ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "         AAAAA         ", "         AAAAA         ")
        .aisle("      AAAAAAAAAAA      ", "      AAAAAAAAAAA      ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "      AAAAAAAAAAA      ", "      AAAAAAAAAAA      ")
        .aisle("    AAAAAAAAAAAAAAA    ", "    AAAAACCCCCAAAAA    ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "    AAAAACCCCCAAAAA    ", "    AAAAAAAAAAAAAAA    ")
        .aisle("   AAAAAAAAAAAAAAAAA   ", "   AAACCCEEEEECCCAAA   ", "      DDDFFFFFDDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDDFFFFFDDD      ", "   AAACCCEEEEECCCAAA   ", "   AAAAAAAAAAAAAAAAA   ")
        .aisle("  AAAAAAAAAAAAAAAAAAA  ", "  AACCEEEEEEEEEEECCAA  ", "    DDFFF     FFFDD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DDFFF     FFFDD    ", "  AACCEEEEEEEEEEECCAA  ", "  AAAAAAAAAAAAAAAAAAA  ")
        .aisle("  AAAAAAAAAAAAAAAAAAA  ", "  AACEEEEEEEEEEEEECAA  ", "    DF   GGGGG   FD    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    DF   GGGGG   FD    ", "  AACEEEEEEEEEEEEECAA  ", "  AAAAAAAAAAAAAAAAAAA  ")
        .aisle(" AAAAAAAAAAAAAAAAAAAAA ", " AACEEEEECCCCCEEEEECAA ", "   DF  GG     GG  FD   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   DF  GG     GG  FD   ", " AACEEEEECCCCCEEEEECAA ", " AAAAAAAAAAAAAAAAAAAAA ")
        .aisle(" AAAAAAAAAAAAAAAAAAAAA ", " AACEEECCAAAAACCEEECAA ", "   DF G         G FD   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   DF G         G FD   ", " AACEEECCAAAAACCEEECAA ", " AAAAAAAAAAAAAAAAAAAAA ")
        .aisle(" AAAAAAAAAAAAAAAAAAAAA ", " AACEEECAAAAAAACEEECAA ", "   DF G   CCC   G FD   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   DF G   CCC   G FD   ", " AACEEECAAAAAAACEEECAA ", " AAAAAAAAAAAAAAAAAAAAA ")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAA", "AACEEECAAAAAAAAACEEECAA", "  DF G   CAAAC   G FD  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  DF G   CAAAC   G FD  ", "AACEEECAAAAAAAAACEEECAA", "AAAAAAAAAAAAAAAAAAAAAAA")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAA", "AACEEECAAAAAAAAACEEECAA", "  DF G  CAAAAAC  G FD  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  DF G  CAAAAAC  G FD  ", "AACEEECAAAAAAAAACEEECAA", "AAAAAAAAAAAAAAAAAAAAAAA")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAA", "AACEEECAAAAAAAAACEEECAA", "  DF G  CAAAAAC  G FD  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  DF G  CAAAAAC  G FD  ", "AACEEECAAAAAAAAACEEECAA", "AAAAAAAAAAAAAAAAAAAAAAA")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAA", "AACEEECAAAAAAAAACEEECAA", "  DF G  CAAAAAC  G FD  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  DF G  CAAAAAC  G FD  ", "AACEEECAAAAAAAAACEEECAA", "AAAAAAAAAAAAAAAAAAAAAAA")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAA", "AACEEECAAAAAAAAACEEECAA", "  DF G   CAAAC   G FD  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  DF G   CAAAC   G FD  ", "AACEEECAAAAAAAAACEEECAA", "AAAAAAAAAAAAAAAAAAAAAAA")
        .aisle(" AAAAAAAAAAAAAAAAAAAAA ", " AACEEECAAAAAAACEEECAA ", "   DF G   CCC   G FD   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   DF G   CCC   G FD   ", " AACEEECAAAAAAACEEECAA ", " AAAAAAAAAAAAAAAAAAAAA ")
        .aisle(" AAAAAAAAAAAAAAAAAAAAA ", " AACEEECCAAAAACCEEECAA ", "   DF G         G FD   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   DF G         G FD   ", " AACEEECCAAAAACCEEECAA ", " AAAAAAAAAAAAAAAAAAAAA ")
        .aisle(" AAAAAAAAAAAAAAAAAAAAA ", " AACEEEEECCCCCEEEEECAA ", "   DF  GG     GG  FD   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   DF  GG     GG  FD   ", " AACEEEEECCCCCEEEEECAA ", " AAAAAAAAAAAAAAAAAAAAA ")
        .aisle("  AAAAAAAAAAAAAAAAAAA  ", "  AACEEEEEEEEEEEEECAA  ", "    DF   GGGGG   FD    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    DF   GGGGG   FD    ", "  AACEEEEEEEEEEEEECAA  ", "  AAAAAAAAAAAAAAAAAAA  ")
        .aisle("  AAAAAAAAAAAAAAAAAAA  ", "  AACCEEEEEEEEEEECCAA  ", "    DDFFF     FFFDD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DDFFF     FFFDD    ", "  AACCEEEEEEEEEEECCAA  ", "  AAAAAAAAAAAAAAAAAAA  ")
        .aisle("   AAAAAAAAAAAAAAAAA   ", "   AAACCCEEEEECCCAAA   ", "      DDDFFFFFDDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDDFFFFFDDD      ", "   AAACCCEEEEECCCAAA   ", "   AAAAAAAAAAAAAAAAA   ")
        .aisle("    AAAAAAAAAAAAAAA    ", "    AAAAACCCCCAAAAA    ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "    AAAAACCCCCAAAAA    ", "    AAAAAAAAAAAAAAA    ")
        .aisle("      AAAAAAAAAAA      ", "      AAAAAAAAAAA      ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "      AAAAAAAAAAA      ", "      AAAAAAAAAAA      ")
        .aisle("         AAAAA         ", "         AASAA         ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "         AAAAA         ", "         AAAAA         ")
        .where('S', selfPredicate())
        .where('A', states(casingState)
            .setMinGlobalLimited(1200)
            .or(abilities(INPUT_ENERGY)
                    .setPreviewCount(1))
            .or(abilities(INPUT_LASER)
                    .setPreviewCount(0))
            .or(autoAbilities(false, false, true, true, true, true, false)))
        .where('C', states(secondCasingState))
        .where('E', states(thirdCasingState))
        .where('F', states(fourthCasingState))
        .where('D', states(glassState))
        .where('G', coils())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.LATTICE_QCD_THERMAL_SHIELDING_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.ENTRODYNAMICALLY_PHASE_CHANGER_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gtlitecore.machine.entrodynamically_phase_changer.tooltip.1",
                               "gtlitecore.machine.entrodynamically_phase_changer.tooltip.2",
                               "gtlitecore.machine.entrodynamically_phase_changer.tooltip.3",
                               "gtlitecore.machine.entrodynamically_phase_changer.tooltip.4",
                               "gtlitecore.machine.entrodynamically_phase_changer.tooltip.5")
            addOverclockInfo(OverclockMode.PERFECT_DOUBLE)
            addParallelInfo(UpgradeMode.WIRE_COIL_TEMPERATURE, 256)
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
            keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gregtech.multiblock.blast_furnace.max_temperature", heatKey))
        }
    }

    override fun getCurrentTemperature(): Int = this.temperature

    override fun hasMaintenanceMechanics(): Boolean = false

    override fun canBeDistinct(): Boolean = true

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        return this.temperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0)!!
    }

    override fun getDataInfo(): MutableList<ITextComponent>
    {
        val components = super.getDataInfo()
        components.add(TextComponentTranslation("gregtech.multiblock.blast_furnace.max_temperature",
            TextComponentTranslation(formatNumbers(this.temperature) + "K")
                .setStyle(Style().setColor(TextFormatting.RED))))
        return components
    }

    private inner class EntrodynamicallyPhaseChangerRecipeLogic(mte: RecipeMapMultiblockController) : ExtendedPowerHeatingCoilRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = PERFECT_DURATION_FACTOR / 2

        override fun modifyOverclockPre(ocParams: OCParams, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPre(ocParams, storage)
            ocParams.setEut(applyCoilEUtDiscount(ocParams.eut(), (this.metaTileEntity as IHeatingCoil).currentTemperature,
                storage.get(TemperatureProperty.getInstance(), 0)!!))
        }

        override fun completeRecipe()
        {
            super.completeRecipe()
            temperature += 100
        }

        override fun getParallelLimit(): Int = min(level * 256 * currentTemperature, Int.MAX_VALUE)

        private fun calculateAmountCoilEUtDiscount(providedTemp: Int, requiredTemp: Int): Int
        {
            return max(0, (providedTemp - requiredTemp) / 450)
        }

        private fun applyCoilEUtDiscount(recipeEUt: Long, providedTemp: Int, requiredTemp: Int): Long
        {
            if (requiredTemp < 450)
            {
                return recipeEUt
            }
            else
            {
                val amountEUtDiscount = calculateAmountCoilEUtDiscount(providedTemp, requiredTemp)
                return if (amountEUtDiscount < 1) recipeEUt else (recipeEUt * min(1.0, 0.5.pow(amountEUtDiscount))).toLong()
            }
        }

    }

}