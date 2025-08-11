package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues.ULV
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.gui.Widget
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.recipes.Recipe
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.util.GTUtility.isBlockSnow
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.optionalStates
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.PCB_FACTORY_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTCleanroomCasing
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.client.resources.I18n
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.MathHelper.clamp
import net.minecraft.world.World
import kotlin.math.floor

/**
 * TODO Redo structure pattern checking with Mui2 and patterning structures when GTCEu merged related
 *      pull (patterning MTEs and Mui2 rework).
 */
class MultiblockPCBFactory(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, PCB_FACTORY_RECIPES)
{

    /**
     * Main structure level of PCB Factory, has 3 upgrade.
     */
    private var mainUpgradeNumber = 0
    /**
     * Auxiliary structure level of PCB Factory, has 1 upgrade now.
     */
    private var auxiliaryUpgradeNumber = 0
    /**
     * Cooling structure level of PCB Factory, has 2 upgrade now.
     */
    private var coolingUpgradeNumber = 0

    /**
     * Trace size to modify durations and OC params,  default: 100μm, range: 25~200μm.
     */
    private var traceSize = 100
    private val minTraceSize = 25
    private val maxTraceSize = 200

    init
    {
        recipeMapWorkable = PCBFactoryRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.IRIDIUM.state
        private val secondCasingState
            get() = GTCleanroomCasing.PLASCRETE.state
        private val thirdCasingState
            get() = GTMultiblockCasing.GRATE_CASING.state
        private val fourthCasingState
            get() = MultiblockCasing.SUBSTRATE_CASING.state
        private val fifthCasingState
            get() = MetalCasing.OSMIRIDIUM.state
        private val sixthCasingState
            get() = MetalCasing.NAQUADAH_ALLOY.state
        private val seventhCasingState
            get() = MetalCasing.NEUTRONIUM.state
        private val eighthCasingState
            get() = MultiblockCasing.INFINITY_COOLING_CASING.state
        private val ninthCasingState
            get() = GTMetalCasing.STAINLESS_CLEAN.state

        private val pipeCasingState
            get() = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state
        private val turbineCasingState
            get() = GTMultiblockCasing.EXTREME_ENGINE_INTAKE_CASING.state

        private val coilState
            get() = GTFusionCasing.SUPERCONDUCTOR_COIL.state

        private val glassState
            get() = GTGlassCasing.LAMINATED_GLASS.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockPCBFactory(metaTileEntityId)

    /**
     * PCB Factory has an asynchronous upgrade system, which used 3 params to control it:
     * - [mainUpgradeNumber]: Main structure tier predicate (3);
     * - [auxiliaryUpgradeNumber]: Auxiliary structure tier predicate (1);
     * - [coolingUpgradeNumber]: Cooling structure tier predicate (2).
     */
    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        // Main structure upgrade numbers.
        mainUpgradeNumber += 1 // T1 structure for default
        if (context.get<String>("MainStructureUpgradeT2") != null)
            mainUpgradeNumber += 1 // T2 structure upgrade
        if (context.get<String>("MainStructureUpgradeT3") != null)
            mainUpgradeNumber += 1 // T3 structure upgrade
        // Auxiliary structure upgrade numbers.
        if (context.get<String>("BioChamberStructureUpgrade") != null)
            auxiliaryUpgradeNumber += 1 // Bio chamber structure upgrade
        // Cooling structure upgrade numbers.
        if (context.get<String>("CoolingStructureUpgradeT1") != null)
            coolingUpgradeNumber += 1 // Liquid cooling tower structure upgrade
        if (context.get<String>("CoolingStructureUpgradeT2") != null)
            coolingUpgradeNumber += 1 // Thermosink structure upgrade
    }

    override fun invalidate()
    {
        super.invalidate()
        mainUpgradeNumber = 0
        auxiliaryUpgradeNumber = 0
        coolingUpgradeNumber = 0
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy: MutableList<IEnergyContainer> = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(SUBSTATION_INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        energyContainer = EnergyContainerList(inputEnergy)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("              gHHHg  nTTTn       ", "              gPPPg  nQQQn       ", "              g   g  n   n       ", "              g   g  n   n       ", "              gJJJg  nRRRn       ", "              g   g  n   n       ", "              g   g  n   n       ", "              g   g  n   n       ", "              g   g  n   n       ", "              gIIIg  nTTTn       ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
        .aisle("              HHHHH  TTTTT       ", "              PIIIP  QOOOQ       ", "               III    OOO        ", "               III    OOO        ", "              JIIIJ  ROOOR       ", "               III    OOO        ", "               III    OOO        ", "               PPP    QQQ        ", "               III    TTT        ", "              I###I  T###T       ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
        .aisle("              HHHHH  TTTTT       ", "              PI*IP  QOUOQ       ", "               I#I    OUO        ", "               I#I    OUO        ", "              JI#IJ  ROUOR       ", "               I#I    OUO        ", "               I#I    OUO        ", "               P#P    QUQ        ", "               I#I    TUT        ", "              I###I  T###T       ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
        .aisle(" KKKKK        HHHHH  TTTTT       ", "              PIIIP  QOOOQ       ", "               III    OOO        ", "               III    OOO        ", "              JIIIJ  ROOOR       ", "               III    OOO        ", "               III    OOO        ", "               PPP    QQQ        ", "               III    TTT        ", "              I###I  T###T       ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
        .aisle("KKKKKKK       gHHHg  nTTTn       ", "  KKK         gPPPg  nQQQn       ", "  KKK         g   g  n   n       ", "  KKK         g   g  n   n       ", "  KKK         gJJJg  nRRRn       ", "  KKK         g   g  n   n       ", "  KKK         g   g  n   n       ", "  KKK         g   g  n   n       ", "  KKK         g   g  n   n       ", "  KKK         gIIIg  nTTTn       ", "  KKK                            ", "  KKK                            ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
        .aisle("KKKKKKK                          ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", "  LLL                            ", "  LLL                            ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
        .aisle("KKKKKKK                          ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", "  L#L                            ", " K###K                           ", "  L#L                            ", "  L#L                            ", "  LKL                            ", "  LKL                            ", "   K                             ", "   K                             ", "   K                             ", "   K                             ", "   K                             ", "                                 ")
        .aisle("KKKKKKK  fEEf                    ", " K###K   fEEf                    ", " K###K   fEEf                    ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", "  L#L                            ", " K###K                           ", "  L#L                            ", "  L#L                            ", "  L#L                            ", "  L#L                            ", "  L#L                            ", "  L#L                            ", "  L#L                            ", "  L#L                            ", "   K                             ", "   K                             ")
        .aisle("KKKKKKK  EEEE                    ", " K###K   E##E                    ", " K###K   E##E                    ", " K###K   fEEf                    ", " K###K   fEEf                    ", " K###K                           ", "  L#L                            ", "  L#L                            ", "  L#L                            ", " K###K                           ", "  L#L                            ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", "  L#L                            ", "  L#L                            ", "   K                             ", "   K                             ")
        .aisle("KKKKKKK  EEEEFCCCCCF             ", " K###K   E##EFCCCCCF             ", " K###K   E##EFCCCCCF             ", " K###K   E##EFCCCCCF             ", " K###K   E##EF     F             ", " K###K   fEEf                    ", "  L#L                            ", "  L#L                            ", "  L#L                            ", " K###K                           ", "  L#L                            ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", "  L#L                            ", "  LKL                            ", "   K                             ", "                                 ")
        .aisle("KKKKKKK  EEEECcccccC hMMMh  hMMMh", " K###K   E##EC#####C hNNNh  hNNNh", " K###K   E##EC#####C hNNNh  hNNNh", " K###K   E##EC#####C hNNNh  hNNNh", " K###K   E##ECCCCCCC h   h  h   h", " K###K   EEEEF     F             ", "  LLL    fEEf                    ", "  LLL                            ", "  LLL                            ", " K###K                           ", " K###K                           ", " K###K                           ", " K#L#K                           ", " K#L#K                           ", " K#L#K                           ", " K###K                           ", " K###K                           ", " K###K                           ", "  LLL                            ", "  LLL                            ", "                                 ", "                                 ")
        .aisle("KKKKKKK  EEEECcccccC MMMMM  MMMMM", "  KKK    E##ED#XXX#D N###N  N###N", "  KKK    E##ED#####D N###N  N###N", "  KKK    E##EC#####C N###N  N###N", "  KKK    E##ECCCCCCC  MMM    MMM ", "  KKK    EEEEF     F             ", "         fEEf                    ", "                                 ", "                                 ", "  KKK                            ", "  KKK                            ", "  KKK                            ", "  K K                            ", "  K K                            ", "  K K                            ", "  KKK                            ", "  KKK                            ", "  KKK                            ", "                                 ", "                                 ", "                                 ", "                                 ")
        .aisle(" KKKKK   EEEECcccccC MMMMM  MMMMM", "         E##ED#XXX#D N###N  N###N", "         E##ED#####D N###N  N###N", "         E##EC#####C N###N  N###N", "         E##ECCCCCCC  MMM    MMM ", "         EEEEFFFFFFF   M      M  ", "         fEEf           MMMMMM   ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
        .aisle("         EEEECcccccC MMMMM  MMMMM", "         E##ED#XXX#D N###N  N###N", "         E##ED#####D N###N  N###N", "         E##EC#####C N###N  N###N", "         E##ECGGGGGC  MMM    MMM ", "         EEEEF     F             ", "         fEEf                    ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
        .aisle("         EEEECcccccC hMMMh  hMMMh", "         E##EC#####C hNNNh  hNNNh", "         E##EC#####C hNNNh  hNNNh", "         E##EC#####C hNNNh  hNNNh", "         E##ECGGGGGC h   h  h   h", "         EEEEF     F             ", "         fEEf                    ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
        .aisle("         EEEEFCCSCCF             ", "         E##EFGGGGGF             ", "         E##EFGGGGGF             ", "         E##EFGGGGGF             ", "         E##EFFFFFFF             ", "         fEEf                    ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
        .aisle("         EEEE                    ", "         E##E                    ", "         E##E                    ", "         fEEf                    ", "         fEEf                    ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
        .aisle("         fEEf                    ", "         fEEf                    ", "         fEEf                    ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
        // Multiblock controller
        .where('S', selfPredicate())
        // T1 Main structure
        .where('C', states(casingState) // Iridium casing
            .setMinGlobalLimited(40)
            .or(abilities(MAINTENANCE_HATCH)
                .setExactLimit(1))
            .or(abilities(INPUT_ENERGY)
                .setMaxGlobalLimited(2))
            .or(abilities(INPUT_LASER)
                .setMaxGlobalLimited(1))
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS, IMPORT_FLUIDS)))
        .where('c', states(secondCasingState)) // Plascrete
        .where('D', states(thirdCasingState)) // Grate casing
        .where('F', frames(HSLASteel))
        .where('G', states(glassState)) // Laminated glass
        .where('X', states(fourthCasingState)) // Substrate casing
        // T2 Main structure
        .where('E', optionalStates("MainStructureUpgradeT2", fifthCasingState)) // Osmiridium casing
        .where('f', optionalStates("MainStructureUpgradeT2", MetaBlocks.FRAMES[Osmiridium]!!.getBlock(Osmiridium)))
        // Liquid Cooling Tower structure
        .where('H', optionalStates("CoolingStructureUpgradeT1", fifthCasingState)) // Osmiridium casing
        .where('I', optionalStates("CoolingStructureUpgradeT1", sixthCasingState)) // Naquadah Alloy casing
        .where('J', optionalStates("CoolingStructureUpgradeT1", turbineCasingState)) // Extreme intake casing
        .where('P', optionalStates("CoolingStructureUpgradeT1", pipeCasingState)) // Tungsten steel pipe casing
        .where('g', optionalStates("CoolingStructureUpgradeT1", MetaBlocks.FRAMES[HSLASteel]!!.getBlock(HSLASteel)))
        // T3 Main structure
        .where('K', optionalStates("MainStructureUpgradeT3", seventhCasingState)) // Neutronium casing
        .where('L', optionalStates("MainStructureUpgradeT3", sixthCasingState)) // Naquadah Alloy casing
        // Bio Chamber structure
        .where('M', optionalStates("BioChamberStructureUpgrade", ninthCasingState)) // Stainless Steel casing
        .where('h', optionalStates("BioChamberStructureUpgrade", MetaBlocks.FRAMES[HSLASteel]!!.getBlock(HSLASteel)))
        .where('N', optionalStates("BioChamberStructureUpgrade", glassState))
        // Thermosink structure
        .where('O', optionalStates("CoolingStructureUpgradeT2", eighthCasingState)) // Infinity cooling machine casing
        .where('Q', optionalStates("CoolingStructureUpgradeT2", pipeCasingState)) // Tungsten steel pipe casing
        .where('R', optionalStates("CoolingStructureUpgradeT2", turbineCasingState)) // Extreme intake casing
        .where('T', optionalStates("CoolingStructureUpgradeT2", fifthCasingState)) // Osmiridium casing
        .where('U', optionalStates("CoolingStructureUpgradeT2", coilState)) // Superconductor coil
        .where('n', optionalStates("CoolingStructureUpgradeT2", MetaBlocks.FRAMES[HSLASteel]!!.getBlock(HSLASteel)))
        // Misc structure contents
        .where('#', air())
        .where('*', air().or(TraceabilityPredicate { bws -> isBlockSnow(bws.blockState) }))
        .where(' ', any())
        .build()

    // TODO
    /*override fun getFlexButton(x: Int, y: Int, width: Int, height: Int): Widget
    {
        val group = WidgetGroup(x, y, width, height)
        group.addWidget(ClickButtonWidget(0, 0, 9, 18, "", this::decrementTraceSize)
            .setButtonTexture(GuiTextures.BUTTON_THROTTLE_MINUS)
            .setTooltipText("gtlitecore.machine.pcb_factory.trace_size.decrement"))
        group.addWidget(ClickButtonWidget(9, 0, 9, 18, "", this::incrementTraceSize)
            .setButtonTexture(GuiTextures.BUTTON_THROTTLE_PLUS)
            .setTooltipText("gtlitecore.machine.pcb_factory.trace_size.increment"))
        return group
    }*/

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        data.setInteger("TraceSize", traceSize)
        return super.writeToNBT(data)
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        traceSize = data.getInteger("TraceSize")
        super.readFromNBT(data)
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeVarInt(traceSize)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        traceSize = buf.readVarInt()
    }

    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.IRIDIUM_CASING

    override fun getMatchingShapes(): MutableList<MultiblockShapeInfo>
    {
        val shapeInfo: MutableList<MultiblockShapeInfo> = ArrayList()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("              gHHHg  nTTTn       ", "              gPPPg  nQQQn       ", "              g   g  n   n       ", "              g   g  n   n       ", "              gJJJg  nRRRn       ", "              g   g  n   n       ", "              g   g  n   n       ", "              g   g  n   n       ", "              g   g  n   n       ", "              gIIIg  nTTTn       ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
            .aisle("              HHHHH  TTTTT       ", "              PIIIP  QOOOQ       ", "               III    OOO        ", "               III    OOO        ", "              JIIIJ  ROOOR       ", "               III    OOO        ", "               III    OOO        ", "               PPP    QQQ        ", "               III    TTT        ", "              I###I  T###T       ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
            .aisle("              HHHHH  TTTTT       ", "              PI*IP  QOUOQ       ", "               I#I    OUO        ", "               I#I    OUO        ", "              JI#IJ  ROUOR       ", "               I#I    OUO        ", "               I#I    OUO        ", "               P#P    QUQ        ", "               I#I    TUT        ", "              I###I  T###T       ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
            .aisle(" KKKKK        HHHHH  TTTTT       ", "              PIIIP  QOOOQ       ", "               III    OOO        ", "               III    OOO        ", "              JIIIJ  ROOOR       ", "               III    OOO        ", "               III    OOO        ", "               PPP    QQQ        ", "               III    TTT        ", "              I###I  T###T       ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
            .aisle("KKKKKKK       gHHHg  nTTTn       ", "  KKK         gPPPg  nQQQn       ", "  KKK         g   g  n   n       ", "  KKK         g   g  n   n       ", "  KKK         gJJJg  nRRRn       ", "  KKK         g   g  n   n       ", "  KKK         g   g  n   n       ", "  KKK         g   g  n   n       ", "  KKK         g   g  n   n       ", "  KKK         gIIIg  nTTTn       ", "  KKK                            ", "  KKK                            ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
            .aisle("KKKKKKK                          ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", "  LLL                            ", "  LLL                            ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
            .aisle("KKKKKKK                          ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", "  L#L                            ", " K###K                           ", "  L#L                            ", "  L#L                            ", "  LKL                            ", "  LKL                            ", "   K                             ", "   K                             ", "   K                             ", "   K                             ", "   K                             ", "                                 ")
            .aisle("KKKKKKK  fEEf                    ", " K###K   fEEf                    ", " K###K   fEEf                    ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", "  L#L                            ", " K###K                           ", "  L#L                            ", "  L#L                            ", "  L#L                            ", "  L#L                            ", "  L#L                            ", "  L#L                            ", "  L#L                            ", "  L#L                            ", "   K                             ", "   K                             ")
            .aisle("KKKKKKK  EEEE                    ", " K###K   E##E                    ", " K###K   E##E                    ", " K###K   fEEf                    ", " K###K   fEEf                    ", " K###K                           ", "  L#L                            ", "  L#L                            ", "  L#L                            ", " K###K                           ", "  L#L                            ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", "  L#L                            ", "  L#L                            ", "   K                             ", "   K                             ")
            .aisle("KKKKKKK  EEEEFCCCeeF             ", " K###K   E##EFCCCCCF             ", " K###K   E##EFCCCCCF             ", " K###K   E##EFCCCCCF             ", " K###K   E##EF     F             ", " K###K   fEEf                    ", "  L#L                            ", "  L#L                            ", "  L#L                            ", " K###K                           ", "  L#L                            ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", " K###K                           ", "  L#L                            ", "  LKL                            ", "   K                             ", "                                 ")
            .aisle("KKKKKKK  EEEECcccccC hMMMh  hMMMh", " K###K   E##EC#####C hNNNh  hNNNh", " K###K   E##EC#####C hNNNh  hNNNh", " K###K   E##EC#####C hNNNh  hNNNh", " K###K   E##ECCCCCCC h   h  h   h", " K###K   EEEEF     F             ", "  LLL    fEEf                    ", "  LLL                            ", "  LLL                            ", " K###K                           ", " K###K                           ", " K###K                           ", " K#L#K                           ", " K#L#K                           ", " K#L#K                           ", " K###K                           ", " K###K                           ", " K###K                           ", "  LLL                            ", "  LLL                            ", "                                 ", "                                 ")
            .aisle("KKKKKKK  EEEECcccccC MMMMM  MMMMM", "  KKK    E##ED#XXX#D N###N  N###N", "  KKK    E##ED#####D N###N  N###N", "  KKK    E##EC#####C N###N  N###N", "  KKK    E##ECCCCCCC  MMM    MMM ", "  KKK    EEEEF     F             ", "         fEEf                    ", "                                 ", "                                 ", "  KKK                            ", "  KKK                            ", "  KKK                            ", "  K K                            ", "  K K                            ", "  K K                            ", "  KKK                            ", "  KKK                            ", "  KKK                            ", "                                 ", "                                 ", "                                 ", "                                 ")
            .aisle(" KKKKK   EEEECcccccC MMMMM  MMMMM", "         E##ED#XXX#D N###N  N###N", "         E##ED#####D N###N  N###N", "         E##EC#####C N###N  N###N", "         E##ECCCCCCC  MMM    MMM ", "         EEEEFFFFFFF   M      M  ", "         fEEf           MMMMMM   ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
            .aisle("         EEEECcccccC MMMMM  MMMMM", "         E##ED#XXX#D N###N  N###N", "         E##ED#####D N###N  N###N", "         E##EC#####C N###N  N###N", "         E##ECGGGGGC  MMM    MMM ", "         EEEEF     F             ", "         fEEf                    ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
            .aisle("         EEEECcccccC hMMMh  hMMMh", "         E##EC#####C hNNNh  hNNNh", "         E##EC#####C hNNNh  hNNNh", "         E##EC#####C hNNNh  hNNNh", "         E##ECGGGGGC h   h  h   h", "         EEEEF     F             ", "         fEEf                    ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
            .aisle("         EEEEFijSmkF             ", "         E##EFGGGGGF             ", "         E##EFGGGGGF             ", "         E##EFGGGGGF             ", "         E##EFFFFFFF             ", "         fEEf                    ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
            .aisle("         EEEE                    ", "         E##E                    ", "         E##E                    ", "         fEEf                    ", "         fEEf                    ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
            .aisle("         fEEf                    ", "         fEEf                    ", "         fEEf                    ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ", "                                 ")
            .where('S', GTLiteMetaTileEntities.PCB_FACTORY, EnumFacing.SOUTH)
            .where('C', casingState) // Iridium casing
            .where('c', secondCasingState) // Plascrete
            .where('D', thirdCasingState) // Grate casing
            .where('F', MetaBlocks.FRAMES[HSLASteel]!!.getBlock(HSLASteel))
            .where('G', glassState)
            .where('X', fourthCasingState)
            .where('i', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('j', MetaTileEntities.ITEM_EXPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('k', MetaTileEntities.FLUID_IMPORT_HATCH[ULV], EnumFacing.SOUTH)
            .where('m', MetaTileEntities.MAINTENANCE_HATCH, EnumFacing.SOUTH)
            .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.NORTH)
            .where('#', Blocks.AIR.defaultState)
            .where('*', Blocks.AIR.defaultState)
            .where(' ', Blocks.AIR.defaultState)
        shapeInfo.add(builder.build())
        // T2 main structure
        shapeInfo.add(builder
            .where('E', fifthCasingState) // Osmiridium casing
            .where('f', MetaBlocks.FRAMES[Osmiridium]!!.getBlock(Osmiridium))
            .build())
        // Liquid Cooling Tower structure
        shapeInfo.add(builder
            .where('H', fifthCasingState) // Osmiridium casing
            .where('I', sixthCasingState) // Naquadah Alloy casing
            .where('J', turbineCasingState) // Extreme intake casing
            .where('P', pipeCasingState) // Tungsten steel pipe casing
            .where('g', MetaBlocks.FRAMES[HSLASteel]!!.getBlock(HSLASteel))
            .build())
        // T3 main structure
        shapeInfo.add(builder
            .where('K', seventhCasingState) // Neutronium casing
            .where('L', sixthCasingState) // Naquadah Alloy casing
            .build())
        // Bio Chamber structure
        shapeInfo.add(builder
            .where('M', ninthCasingState) // Stainless Steel casing
            .where('h', MetaBlocks.FRAMES[HSLASteel]!!.getBlock(HSLASteel))
            .where('N', glassState)
            .build())
        // Thermosink structure
        shapeInfo.add(builder
            .where('O', eighthCasingState) // Infinity cooling machine casing
            .where('Q', pipeCasingState) // Tungsten steel pipe casing
            .where('R', turbineCasingState) // Extreme intake casing
            .where('T', fifthCasingState) // Osmiridium casing
            .where('U', coilState) // Superconductor coil
            .where('n', MetaBlocks.FRAMES[HSLASteel]!!.getBlock(HSLASteel))
            .build())
        return shapeInfo
    }

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.7"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.8"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.9"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.10"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.11"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.12"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.13"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.14"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.15"))
    }

    // TODO
    /*override fun addDisplayText(textList: MutableList<ITextComponent>?)
    {
        MultiblockDisplayText.builder(textList, isStructureFormed)
            .addCustom { tl ->
                if (isStructureFormed)
                {
                    // Main structure tier (trace size)
                    tl.add(translationWithColor(TextFormatting.GRAY,
                        "gtlitecore.machine.pcb_factory.structure_info",
                        mainUpgradeNumber, traceSize) as ITextComponent)
                    // Cooling structure tier
                    if (coolingUpgradeNumber > 0)
                    {
                        tl.add(translationWithColor(TextFormatting.AQUA,
                            "gtlitecore.machine.pcb_factory.structure_cooling"))
                        if (coolingUpgradeNumber == 2)
                            tl.add(translationWithColor(TextFormatting.BLUE,
                                "gtlitecore.machine.pcb_factory.structure_thermosink"))
                    }
                    // Auxiliary structure tier
                    if (auxiliaryUpgradeNumber == 1)
                        tl.add(translationWithColor(TextFormatting.GREEN,
                            "gtlitecore.machine.pcb_factory.structure_bio_chamber"))
                }
            }
            .setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(recipeMapWorkable.energyContainer)
            .addEnergyTierLine(getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progressPercent)
    }*/

    override fun canBeDistinct() = true

    override fun shouldShowVoidingModeButton() = true

    private fun incrementTraceSize(clickData: Widget.ClickData)
    {
        traceSize = clamp(traceSize - 25, minTraceSize, maxTraceSize)
    }

    private fun decrementTraceSize(clickData: Widget.ClickData)
    {
        traceSize = clamp(traceSize + 25, minTraceSize, maxTraceSize)
    }

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        return super.checkRecipe(recipe, consumeIfSuccess)
                && recipe.getProperty(GTLiteRecipeProperties.PCB_FACTORY_TIER, 0)!! <= mainUpgradeNumber
                && recipe.getProperty(GTLiteRecipeProperties.PCB_FACTORY_BIO_CHAMBER_UPGRADE, 0)!! <= auxiliaryUpgradeNumber
    }

    private inner class PCBFactoryRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor() = when (coolingUpgradeNumber)
        {
            0 -> 1.0  // Non OC
            1 -> 0.5 // Normal OC
            2 -> 0.25 // Perfect OC
            else -> 0.0 // Error OC
        }

        override fun getParallelLimit() = calculateParallelByNanites()

        override fun setMaxProgress(maxProgress: Int)
        {
            maxProgressTime = when (traceSize)
            {
                25 -> floor(0.4 * maxProgress).toInt()
                50 -> floor(0.6 * maxProgress).toInt()
                75 -> floor(0.8 * maxProgress).toInt()
                125 -> floor(1.2 * maxProgress).toInt()
                150 -> floor(1.4 * maxProgress).toInt()
                175 -> floor(1.6 * maxProgress).toInt()
                200 -> floor(1.8 * maxProgress).toInt()
                else -> maxProgress
            }
        }

        override fun updateRecipeProgress()
        {
            val traceSizeFactor = when (traceSize)
            {
                25 -> 2.5
                50 -> 2
                75 -> 1.5
                125 -> 0.9
                150 -> 0.8
                175 -> 0.7
                200 -> 0.6
                else -> 1
            }
            val actuallyEnergyConsumed: Int = recipeEUt.toInt() * traceSizeFactor.toInt()
            if (canRecipeProgress && drawEnergy(actuallyEnergyConsumed.toLong(), true))
            {
                drawEnergy(actuallyEnergyConsumed.toLong(), false)
                if (++progressTime > maxProgressTime)
                    completeRecipe()
                if (hasNotEnoughEnergy && energyInputPerSecond > 19L * actuallyEnergyConsumed.toLong())
                    hasNotEnoughEnergy = false
            }
            else if (actuallyEnergyConsumed > 0)
            {
                hasNotEnoughEnergy = true
                decreaseProgress()
            }
        }

        private fun calculateParallelByNanites(): Int
        {
            val itemInputInventory = getAbilities(IMPORT_ITEMS)
            val itemInputs = ItemHandlerList(itemInputInventory)
            var parallelBase = 0
            for (i in 0 until itemInputs.slots)
            {
                parallelBase = itemInputs.getStackInSlot(i).count
                if (mainUpgradeNumber == 2)
                {
                    if (itemInputs.getStackInSlot(i).isItemEqual(OreDictUnifier.get(nanite, Silver)))
                        return parallelBase * 2
                }
                if (mainUpgradeNumber == 3)
                {
                    if (itemInputs.getStackInSlot(i).isItemEqual(OreDictUnifier.get(nanite, Gold)))
                        return parallelBase * 4
                }
            }
            return parallelBase
        }

    }

}