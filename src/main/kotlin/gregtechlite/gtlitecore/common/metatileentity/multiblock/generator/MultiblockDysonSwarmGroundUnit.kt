package gregtechlite.gtlitecore.common.metatileentity.multiblock.generator

import gregtech.api.GTValues.MAX_TRUE
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VOC
import gregtech.api.capability.IOpticalComputationProvider
import gregtech.api.capability.IOpticalComputationReceiver
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.FuelMultiblockController
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.COMPUTATION_DATA_RECEPTION
import gregtech.api.metatileentity.multiblock.MultiblockAbility.COMPUTATION_DATA_TRANSMISSION
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.items.toolitem.ToolClasses
import gregtech.api.items.toolitem.ToolHelper
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.HOUR
import gregtechlite.gtlitecore.api.metatileentity.sync.MetaTileEntitySyncer
import gregtechlite.gtlitecore.api.metatileentity.sync.SyncedMetaTileEntity
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.DYSON_SWARM_FUELS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GelidCryotheum
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.aerospace.AerospaceCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DYSON_SWARM_PHOTOVOLTAIC_PANEL
import codechicken.lib.raytracer.CuboidRayTraceResult
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.common.blocks.BlockWireCoil
import gregtechlite.gtlitecore.api.GTLiteAPI.COIL_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.SENSOR_CASING_TIER
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.sensorCasings
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ReneN5
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitanSteel
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.exp
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

class MultiblockDysonSwarmGroundUnit(id: ResourceLocation)
    : FuelMultiblockController(id, DYSON_SWARM_FUELS, UHV), IOpticalComputationReceiver, SyncedMetaTileEntity
{
    override val syncer: MetaTileEntitySyncer = MetaTileEntitySyncer(this)

    private var launchedPanels by syncer.syncedInt(0)
    private var maxPanels by syncer.syncedInt(1000)

    private var coilTier = 0
    private var sensorCasingTier = 0
    private var tier = 0

    private var computationProvider: IOpticalComputationProvider? = null

    init
    {
        recipeMapWorkable = DysonSwarmWorkableHandler(this)
        recipeMapWorkable.maximumOverclockVoltage = VOC[MAX_TRUE]
    }

    companion object
    {
        const val EU_PER_PANEL = 10_000_000L
        const val COOLANT_PER_HOUR = 3_600_000
        const val PANEL_DESTROY_CHANCE = 2 * 0.066

        private val casingState = AerospaceCasing.DYSON_SWARM_MODULE_DEPLOYMENT_UNIT_BASE_CASING.state
        private val secondCasingState = AerospaceCasing.DYSON_SWARM_CONTROL_CENTER_BASE_CASING.state
        private val thirdCasingState = AerospaceCasing.HIGH_STRENGTH_CONCRETE.state
        private val fourthCasingState = AerospaceCasing.DYSON_SWARM_MODULE_DEPLOYMENT_UNIT_SUPERCONDUCTING_MAGNET.state
        private val fifthCasingState = AerospaceCasing.DYSON_SWARM_CONTROL_CENTER_TOROID_CASING.state
        private val sixthCasingState = AerospaceCasing.DYSON_SWARM_ENERGY_RECEIVER_BASE_CASING.state

        private val uniqueCasingState = AerospaceCasing.DYSON_SWARM_MODULE_DEPLOYMENT_UNIT_CORE.state
        private val secondUniqueCasingState = MultiblockCasing.REFLECTIVE_SURFACE_CASING.state

        private val coilState = AerospaceCasing.DYSON_SWARM_CONTROL_CENTER_PRIMARY_WINDINGS.state
        private val secondCoilState = AerospaceCasing.DYSON_SWARM_CONTROL_CENTER_SECONDARY_WINDINGS.state

        init // Dummy
        {
            DYSON_SWARM_FUELS.addRecipe {
                fluidInputs(GelidCryotheum.getFluid(COOLANT_PER_HOUR))
                duration(HOUR)
                EUt(1)
            }
        }
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockDysonSwarmGroundUnit(metaTileEntityId)

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val outputEnergy = ArrayList(getAbilities(OUTPUT_ENERGY))
        outputEnergy.addAll(getAbilities(SUBSTATION_OUTPUT_ENERGY))
        outputEnergy.addAll(getAbilities(OUTPUT_LASER))
        energyContainer = EnergyContainerList(outputEnergy)
    }

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        computationProvider = getAbilities(COMPUTATION_DATA_RECEPTION).firstOrNull()
        coilTier = context.getAttributeOrDefault(COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).tier
        sensorCasingTier = context.getAttributeOrDefault(SENSOR_CASING_TIER, 0)
        tier = minOf(coilTier, sensorCasingTier)
        maxPanels = 1000 + arrayOf(sensorCasingTier, coilTier).average().toInt() * 256
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        computationProvider = null
        coilTier = 0
        sensorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("DDDDDDDDDDDDDDDD", "CCCCCCC    BBBBB", "CCCCCCC    BBBBB", "CCCCCCC    BBBBB", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ")
        .aisle("DDDDDDDDDDDDDDDD", "CCCCCCC    BBBBB", "CCCCCCC    BBBBB", "CCCCCCC    BBBBB", "  XXX       FZF ", "            FZF ", "            FZF ", "  RRR       FZF ", "            FZF ", "  RRR       FZF ", "            FZF ", "  RRR       FZF ", "             F  ", "  RRR        F  ", "             F  ", "             F  ", "  RRR        F  ", "  RRR        F  ", "  RRR        F  ", "             F  ")
        .aisle("DDDDDDDDDDDDDDDD", "CCCCCCC    BBBBB", "CCCCCCC    BBBBB", "CCXXXCC    BBOBB", " X   X      Z Z ", "            Z Z ", "            Z Z ", " R f R      Z Z ", "            Z Z ", " R f R      Z Z ", "            Z Z ", " R f R      Z Z ", "            F F ", " R f R      F F ", "            F F ", "  RRR       F F ", " RRRRR      F F ", " RRRRR      F F ", " RRRRR      F F ", "  RRR       F F ")
        .aisle("DDDDDDDDDDDDDDDD", "CCCCCCC    BBBBB", "CCCCCCC    BBBBB", "CCXCXCC    BBBBB", " X Y X      FZF ", "   Y        FZF ", "   Y        FZF ", " RfYfR      FZF ", "   Y        FZF ", " RfYfR      FZF ", "   Y        FZF ", " RfYfR      FZF ", "   Y         F  ", " RfYfR       F  ", "   Y         F  ", "  RRR        F  ", " RRRRR       F  ", " RRRRR       F  ", " RRRRR       F  ", "  RRR        F  ")
        .aisle("DDDDDDDDDDDDDDDD", "CCCCCCC    BBBBB", "CCCCCCC    BBBBB", "CCXXXCC    BBBBB", " X   X          ", "                ", "                ", " R f R          ", "                ", " R f R          ", "                ", " R f R          ", "                ", " R f R          ", "                ", "  RRR           ", " RRRRR          ", " RRRRR          ", " RRRRR          ", "  RRR           ")
        .aisle("DDDDDDDDDDDDDDDD", "CCCCCCC         ", "CCCCCCC         ", "CCCCCCC         ", "  XXX           ", "                ", "                ", "  RRR           ", "                ", "  RRR           ", "                ", "  RRR    yyy    ", "                ", "  RRR           ", "                ", "                ", "  RRR           ", "  RRR           ", "  RRR           ", "                ")
        .aisle("DDDDDDDDDDDDDDDD", "CCCCCCC         ", "CCCCCCC         ", "CCCCCCC         ", "                ", "                ", "                ", "                ", "                ", "                ", "         yyy    ", "        y   y   ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ")
        .aisle("DDDDDDDDDDDDDDDD", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "        yyyyy   ", "       y     y  ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ")
        .aisle("DDDDDDDDDDDDDDDD", "        ccccc   ", "        ccccc   ", "        ccccc   ", "                ", "                ", "                ", "                ", "                ", "         yyy    ", "       yy   yy  ", "      y       y ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ")
        .aisle("DDDDDDDDDDDDDDDD", "        ccccc   ", "        coooc   ", "        ccccc   ", "         x x    ", "         x x    ", "         x x    ", "         x x    ", "         x x    ", "        yyyyy   ", "      yy     yy ", "     y         y", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ")
        .aisle("DDDDDDDDDDDDDDDD", "        ccccc   ", "        coooc   ", "        ccccc   ", "                ", "                ", "                ", "                ", "                ", "        yyyyy   ", "      yy  g  yy ", "     y    g    y", "          r     ", "                ", "                ", "                ", "                ", "                ", "                ", "                ")
        .aisle("DDDDDDDDDDDDDDDD", "        ccccc   ", "        coooc   ", "        ccccc   ", "         x x    ", "         x x    ", "         x x    ", "         x x    ", "         x x    ", "        yyyyy   ", "      yy     yy ", "     y         y", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ")
        .aisle("DDDDDDDDDDDDDDDD", "        ccScc   ", "        ccccc   ", "        ccccc   ", "                ", "                ", "                ", "                ", "                ", "         yyy    ", "       yy   yy  ", "      y       y ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ")
        .aisle("DDDDDDDDDDDDDDDD", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "        yyyyy   ", "       y     y  ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ")
        .aisle("DDDDDDDDDDDDDDDD", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "         yyy    ", "        y   y   ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ")
        .aisle("DDDDDDDDDDDDDDDD", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "         yyy    ", "                ", "                ", "                ", "                ", "                ", "                ", "                ", "                ")
        .where('S', selfPredicate())
        .where('B', states(casingState))
        .where('C', states(secondCasingState))
        .where('D', states(thirdCasingState))
        .where('Z', states(fourthCasingState))
        .where('R', states(fifthCasingState))
        .where('c', states(sixthCasingState)
            .setMinGlobalLimited(50)
            .or(abilities(IMPORT_ITEMS)
                    .setPreviewCount(1))
            .or(abilities(IMPORT_FLUIDS)
                    .setPreviewCount(1))
            .or(abilities(OUTPUT_ENERGY)
                    .setPreviewCount(0))
            .or(abilities(OUTPUT_LASER)
                    .setPreviewCount(0)))
        .where('O', states(uniqueCasingState))
        .where('y', states(secondUniqueCasingState))
        .where('r', sensorCasings())
        .where('X', states(coilState))
        .where('o', heatingCoils())
        .where('Y', states(secondCoilState))
        .where('F', frames(Mendelevium))
        .where('f', frames(ReneN5))
        .where('x', frames(TitanSteel))
        .where('g', frames(QuantumAlloy))
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.DYSON_SWARM_ENERGY_RECEIVER_BASE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.DYSON_SWARM_GROUND_UNIT_OVERLAY

    override fun hasMaintenanceMechanics() = false

    override fun canBeDistinct() = false

    override fun shouldShowVoidingModeButton() = false

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        val recipeLogic = recipeMapWorkable as DysonSwarmWorkableHandler
        builder.setWorkingStatus(recipeLogic.isWorkingEnabled, recipeLogic.isActive)
            .addEnergyProductionLine(recipeLogic.maxVoltage, recipeLogic.recipeEUt)
            .addCustom { keyManager, syncer ->
                if (isStructureFormed)
                {
                    val panelKey = KeyUtil.number(TextFormatting.GREEN,
                                                  syncer.syncInt(launchedPanels).toLong(), "x")
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                                                "gtlitecore.machine.dyson_swarm_ground_unit.panel_count", panelKey))

                    val computationKey = KeyUtil.number(TextFormatting.BLUE,
                                                        syncer.syncInt(recipeLogic.currentComputation).toLong(), "CWU/t")
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                                                "gtlitecore.machine.dyson_swarm_ground_unit.computation", computationKey))
                }
            }
            .addWorkingStatusLine()
    }

    override fun configureWarningText(builder: MultiblockUIBuilder)
    {
        super.configureWarningText(builder)
        builder.addCustom { keyManager, syncer ->
            if (syncer.syncBoolean(::isDynamoFull))
            {
                keyManager.add(KeyUtil.lang(TextFormatting.YELLOW,
                                            "gtlitecore.tooltip.multiblock.dynamo_hatch_full"))
            }
        }
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.dyson_swarm_ground_unit.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.dyson_swarm_ground_unit.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.dyson_swarm_ground_unit.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.dyson_swarm_ground_unit.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.dyson_swarm_ground_unit.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.dyson_swarm_ground_unit.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.dyson_swarm_ground_unit.tooltip.7"))
        tooltip.add(I18n.format("gtlitecore.machine.dyson_swarm_ground_unit.tooltip.8"))
        tooltip.add(I18n.format("gtlitecore.machine.dyson_swarm_ground_unit.tooltip.9"))
    }

    override fun onLeftClick(player: EntityPlayer, facing: EnumFacing?, hitResult: CuboidRayTraceResult?)
    {
        val held = player.heldItemMainhand
        if (!held.isEmpty && ToolHelper.isTool(held, ToolClasses.PLUNGER))
        {
            extractPanels(player, player.isSneaking)
        }
    }

    private fun extractPanels(player: EntityPlayer, dropRemaining: Boolean)
    {
        val toExtract = launchedPanels
        if (toExtract <= 0) return
        launchedPanels = 0
        val panel = DYSON_SWARM_PHOTOVOLTAIC_PANEL.stack()

        var extracted = 0
        for (i in 0 until toExtract)
        {
            val stack = panel.copy()
            if (player.inventory.addItemStackToInventory(stack))
            {
                extracted++
            }
            else if (dropRemaining)
            {
                player.dropItem(stack, false, false)
                extracted++
            }
            else
            {
                launchedPanels = toExtract - extracted
                break
            }
        }
    }

    override fun getComputationProvider(): IOpticalComputationProvider? = computationProvider

    private fun isDynamoFull(): Boolean = energyContainer.energyCanBeInserted < recipeMapWorkable.recipeEUt

    private inner class DysonSwarmWorkableHandler(mte: RecipeMapMultiblockController) : MultiblockFuelRecipeLogic(mte)
    {
        private val generator = mte as MultiblockDysonSwarmGroundUnit

        var currentComputation: Int = 0 // UI only

        override fun updateRecipeProgress()
        {
            insertPanels()

            val dynamicEUt = generator.launchedPanels.toLong() * EU_PER_PANEL
            recipeEUt = dynamicEUt

            if (canRecipeProgress && canRun() && dynamicEUt > 0)
            {
                val toProduce = min(dynamicEUt, getEnergyContainer().energyCanBeInserted)
                if (toProduce > 0)
                {
                    getEnergyContainer().changeEnergy(toProduce)

                    if (generator.offsetTimer % HOUR == 0L)
                    {
                        consumeCoolantAndDestroyPanels()
                    }
                    if (++progressTime > maxProgressTime)
                    {
                        completeRecipe()
                    }
                }
            }
            else
            {
                completeRecipe()
                hasNotEnoughEnergy = true
            }
        }

        override fun shouldSearchForRecipes(): Boolean
        {
            insertPanels()
            return generator.isStructureFormed && generator.launchedPanels > 0 && hasCoolant() && isWorkingEnabled
        }

        override fun getMaxVoltage(): Long = VOC[MAX_TRUE]

        override fun boostProduction(production: Long): Long = generator.launchedPanels.toLong() * EU_PER_PANEL

        override fun isAllowOverclocking() = false

        private fun canRun(): Boolean = generator.isStructureFormed && generator.launchedPanels > 0 && hasCoolant() && isWorkingEnabled

        override fun update()
        {
            super.update()
            readComputation()
        }

        private fun readComputation()
        {
            if (!generator.isStructureFormed)
            {
                currentComputation = 0
                return
            }
            var total = 0
            for (hatch in generator.getAbilities(COMPUTATION_DATA_RECEPTION))
            {
                total += hatch.requestCWUt(Int.MAX_VALUE, true)
            }
            for (hatch in generator.getAbilities(COMPUTATION_DATA_TRANSMISSION))
            {
                total += hatch.requestCWUt(Int.MAX_VALUE, true)
            }
            currentComputation = total
        }

        private fun insertPanels()
        {
            val importItems = getInputInventory() ?: return
            val panelStack = DYSON_SWARM_PHOTOVOLTAIC_PANEL.stack()
            var launched = 0

            for (slot in 0 until importItems.slots)
            {
                val remaining = maxPanels - generator.launchedPanels
                if (remaining <= 0) break
                val stack = importItems.getStackInSlot(slot)
                if (stack.isEmpty || !stack.isItemEqual(panelStack)) continue

                val toTake = min(stack.count, remaining)
                val extracted = importItems.extractItem(slot, toTake, false)
                if (!extracted.isEmpty)
                {
                    generator.launchedPanels += extracted.count
                    launched += extracted.count
                }
            }
        }

        private fun hasCoolant(): Boolean
        {
            val handler = generator.inputFluidInventory ?: return false
            val fluid = handler.drain(GelidCryotheum.getFluid(COOLANT_PER_HOUR), false)
            return fluid != null && fluid.amount >= COOLANT_PER_HOUR
        }

        private fun consumeCoolantAndDestroyPanels()
        {
            val claimedCWUt = currentComputation
            val before = generator.launchedPanels
            val destroyed = floor(before.toDouble() * PANEL_DESTROY_CHANCE / (exp(-0.00005 * (before - 1)) + exp(
                0.001 * claimedCWUt.toDouble()))).toInt()
            generator.launchedPanels = max(0, before - destroyed)
        }
    }
}
