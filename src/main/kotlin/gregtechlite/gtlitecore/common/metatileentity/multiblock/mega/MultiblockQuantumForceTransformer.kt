package gregtechlite.gtlitecore.common.metatileentity.multiblock.mega

import com.cleanroommc.modularui.widgets.slot.ItemSlot
import com.cleanroommc.modularui.widgets.slot.ModularSlot
import com.morphismmc.morphismlib.client.Games
import gregtech.api.block.machines.MachineItemBlock
import gregtech.api.capability.IMultipleTankHandler
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.capability.impl.NotifiableItemStackHandler
import gregtech.api.metatileentity.IFastRenderMetaTileEntity
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.*
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.mui.GTGuiTextures
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.KeyUtil
import gregtech.api.util.RelativeDirection
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.shader.postprocessing.BloomType
import gregtech.client.utils.BloomEffectUtil
import gregtech.client.utils.EffectRenderContext
import gregtech.client.utils.IBloomEffect
import gregtech.core.sound.GTSoundEvents
import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.api.metatileentity.multiblock.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.metatileentity.multiblock.UpgradeMode
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.manipulators
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.shieldingCores
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BATH_CONDENSER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.QUANTUM_FORCE_TRANSFORMER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import gregtechlite.gtlitecore.client.renderer.handler.bloom.ForceFieldBloomSetup
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import org.lwjgl.opengl.GL11
import kotlin.math.max
import kotlin.math.min

class MultiblockQuantumForceTransformer(id: ResourceLocation)
    : RecipeMapMultiblockController(id, QUANTUM_FORCE_TRANSFORMER_RECIPES), IFastRenderMetaTileEntity, IBloomEffect
{
    private var manipulatorTier = 0
    private var shieldingCoreTier = 0
    private var tier = 0

    private var workingRecipeMap: RecipeMap<*>? = null
    private var installedMachine = InstalledMachineSlot()

    @SideOnly(Side.CLIENT)
    private var registeredBloomRenderTicket = false

    init
    {
        recipeMapWorkable = QuantumForceTransformerRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MultiblockCasing.PARTICLE_CONTAINMENT_CASING.state
        private val coilState = MultiblockCasing.PARTICLE_EXCITATION_WIRE_COIL.state
        private val glassState = GlassCasing.FORCE_FIELD.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockQuantumForceTransformer(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        manipulatorTier = context.getAttributeOrDefault(GTLiteAPI.MANIPULATOR_TIER, 0)
        shieldingCoreTier = context.getAttributeOrDefault(GTLiteAPI.SHIELDING_CORE_TIER, 0)
        tier = minOf(manipulatorTier, shieldingCoreTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        manipulatorTier = 0
        shieldingCoreTier = 0
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        inputEnergy.addAll(getAbilities(SUBSTATION_INPUT_ENERGY))
        energyContainer = EnergyContainerList(inputEnergy)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("    A     A    ", "    A     A    ", "    A     A    ", "   BA     AB   ", "   BABBABBAB   ", "   BAAAAAAAB   ", "   BBBBABBBB   ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("               ", "               ", "               ", "  A         A  ", "  A         A  ", "  B         B  ", "  BAAAAAAAAAB  ", "   AAABBBAAA   ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("               ", "               ", "               ", " A           A ", " A           A ", " B           B ", " BAA       AAB ", "  AA       AA  ", "    AA   AA    ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("A             A", "A             A", "A             A", "A             A", "A             A", "B             B", "BAA         AAB", " AA         AA ", "   AA     AA   ", "     BAAAB     ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("      HHH      ", "      EEE      ", "      EEE      ", "      EEE      ", "B     DDD     B", "B     EEE     B", "BA    DDD    AB", " A    EEE    A ", "  AA  EEE  AA  ", "    BAEEEAB    ", "      DDD      ", "      EEE      ", "      EEE      ", "      EEE      ", "      DDD      ", "      EEE      ", "      DDD      ", "      EEE      ", "      EEE      ", "      EEE      ", "      FFF      ")
        .aisle("     HHHHH     ", "     ECCCE     ", "     ECCCE     ", "B    ECCCE    B", "B    D###D    B", "B    ECCCE    B", "BA   D###D   AB", " A   ECCCE   A ", "  A  ECCCE  A  ", "   BAECCCEAB   ", "     D###D     ", "     ECCCE     ", "     ECCCE     ", "     ECCCE     ", "     D###D     ", "     ECCCE     ", "     D###D     ", "     ECCCE     ", "     ECCCE     ", "     ECCCE     ", "     FFFFF     ")
        .aisle("    HHHHHHH    ", "    EC###CE    ", "A   EC###CE   A", "A   EC###CE   A", "A   D#####D   A", "A   EC###CE   A", "BA  D#####D  AB", "BB  EC###CE  BB", " B  EC###CE  B ", "  BAEC###CEAB  ", "    D#####D    ", "    EC###CE    ", "    EC###CE    ", "    EC###CE    ", "    D#####D    ", "    EC###CE    ", "    D#####D    ", "    EC###CE    ", "    EC###CE    ", "    ECCCCCE    ", "    FFFFFFF    ")
        .aisle("    HHHHHHH    ", "    EC###CE    ", "    EC###CE    ", "    EC###CE    ", "A   D#####D   A", "A   EC###CE   A", "AA  D#####D  AA", "AB  EC###CE  BA", " A  EC###CE  A ", "  AAEC###CEAA  ", "    D#####D    ", "    EC###CE    ", "    EC###CE    ", "    EC###CE    ", "    D#####D    ", "    EC###CE    ", "    D#####D    ", "    EC###CE    ", "    EC###CE    ", "    ECCCCCE    ", "    FFFFFFF    ")
        .aisle("    HHHHHHH    ", "    EC###CE    ", "    EC###CE    ", "A   EC###CE   A", "A   D#####D   A", "A   EC###CE   A", "BA  D#####D  AB", "BB  EC###CE  BB", " B  EC###CE  B ", "  BAEC###CEAB  ", "    D#####D    ", "    EC###CE    ", "    EC###CE    ", "    EC###CE    ", "    D#####D    ", "    EC###CE    ", "    D#####D    ", "    EC###CE    ", "    EC###CE    ", "    ECCCCCE    ", "    FFFFFFF    ")
        .aisle("     HHHHH     ", "     ECCCE     ", "     ECCCE     ", "B    ECCCE    B", "B    D###D    B", "B    ECCCE    B", "BA   D###D   AB", " A   ECCCE   A ", "  A  ECCCE  A  ", "   BAECCCEAB   ", "     D###D     ", "     ECCCE     ", "     ECCCE     ", "     ECCCE     ", "     D###D     ", "     ECCCE     ", "     D###D     ", "     ECCCE     ", "     ECCCE     ", "     ECCCE     ", "     FFFFF     ")
        .aisle("      HSH      ", "      EEE      ", "      EEE      ", "      EEE      ", "B     DDD     B", "B     EEE     B", "BA    DDD    AB", " A    EEE    A ", "  AA  EEE  AA  ", "    BAEEEAB    ", "      DDD      ", "      EEE      ", "      EEE      ", "      EEE      ", "      DDD      ", "      EEE      ", "      DDD      ", "      EEE      ", "      EEE      ", "      EEE      ", "      FFF      ")
        .aisle("A             A", "A             A", "A             A", "A             A", "A             A", "B             B", "BAA          AB", " AA         AA ", "   AA     AA   ", "     BAAAB     ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("               ", "               ", "               ", " A           A ", " A           A ", " B           B ", " BA         AB ", "  AA       AA  ", "    AA   AA    ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("               ", "               ", "               ", "  A         A  ", "  A         A  ", "  B         B  ", "  BAAAAAAAAAB  ", "   AAABBBAAA   ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("    A     A    ", "    A     A    ", "    A     A    ", "   BA     AB   ", "   BABBABBAB   ", "   BAAAAAAAB   ", "   BBBBABBBB   ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .where('S', selfPredicate())
        .where('H', states(casingState)
            .setMinGlobalLimited(16)
            .or(abilities(MAINTENANCE_HATCH)
                    .setExactLimit(1))
            .or(abilities(INPUT_ENERGY)
                    .setMaxGlobalLimited(2))
            .or(abilities(INPUT_LASER)
                    .setMaxGlobalLimited(1))
            .or(abilities(IMPORT_ITEMS, IMPORT_FLUIDS)
                    .setPreviewCount(1)))
        .where('F', states(casingState)
            .setMinGlobalLimited(16)
            .or(abilities(EXPORT_ITEMS, EXPORT_FLUIDS)
                    .setPreviewCount(1)))
        .where('A', manipulators())
        .where('B', shieldingCores())
        .where('C', states(coilState))
        .where('D', states(casingState))
        .where('E', states(glassState))
        .where(' ', any())
        .where('#', air())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.PARTICLE_CONTAINMENT_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.QUANTUM_FORCE_TRANSFORMER_OVERLAY

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gtlitecore.machine.quantum_force_transformer.tooltip.1",
                               "gtlitecore.machine.quantum_force_transformer.tooltip.2",
                               "gtlitecore.machine.quantum_force_transformer.tooltip.3",
                               "gtlitecore.machine.quantum_force_transformer.tooltip.4")
            addOverclockInfo("gtlitecore.machine.quantum_force_transformer.tooltip.5")
            addParallelInfo("gtlitecore.machine.quantum_force_transformer.tooltip.6")
            addDurationInfo(1200, UpgradeMode.MANIPULATOR)
            addLaserHatchInfo()
            addDescriptionLine("gtlitecore.machine.quantum_force_transformer.tooltip.7",
                               "gtlitecore.machine.quantum_force_transformer.tooltip.8")
        }
    }

    @Suppress("UnstableApiUsage")
    override fun createUIFactory(): MultiblockUIFactory = super.createUIFactory()
        .createFlexButton { guiData, syncManager ->
            syncManager.registerSlotGroup("machine_slot", 1, true)
            return@createFlexButton ItemSlot()
                .slot(object : ModularSlot(installedMachine, 0)
                {
                    override fun onTake(thePlayer: EntityPlayer, stack: ItemStack): ItemStack
                        = super.onTake(thePlayer, stack).also { recipeMapWorkable.forceRecipeRecheck() }
                }
                .slotGroup("machine_slot"))
                .background(GTGuiTextures.SLOT)
        }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(energyContainer)
            .addEnergyTierLine(getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
            .addCustom { keyManager, syncer ->
                if (isStructureFormed)
                {
                    val manipulatorTierKey = KeyUtil.number(TextFormatting.GREEN,
                                                            syncer.syncInt(manipulatorTier).toLong())
                    val shieldingCoreTierKey = KeyUtil.number(TextFormatting.GREEN,
                                                              syncer.syncInt(shieldingCoreTier).toLong())
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.quantum_force_transformer.manipulator_info",
                                                manipulatorTierKey))
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.quantum_force_transformer.shielding_core_info",
                                                shieldingCoreTierKey))
                }
            }
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progress, recipeMapWorkable.maxProgress)
            .addRecipeOutputLine(recipeMapWorkable)
    }

    override fun canBeDistinct() = true

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        recipe.chancedOutputs.chancedEntries.forEach { min(it.chance * shieldingCoreTier, 10000) }
        recipe.chancedFluidOutputs.chancedEntries.forEach { min(it.chance * shieldingCoreTier, 10000) }
        return super.checkRecipe(recipe, consumeIfSuccess)
                && recipe.getProperty(GTLiteRecipeProperties.QUANTUM_FORCE_TRANSFORMER_TIER, 0)!! <= manipulatorTier
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(x: Double, y: Double, z: Double, partialTicks: Float)
    {
        if (isActive && !registeredBloomRenderTicket)
        {
            registeredBloomRenderTicket = true
            BloomEffectUtil.registerBloomRender(ForceFieldBloomSetup.INSTANCE, BloomType.UNREAL, this, this)
        }
    }

    override fun getRenderBoundingBox(): AxisAlignedBB
    {
        val relativeBack = RelativeDirection.BACK.getRelativeFacing(getFrontFacing(), getUpwardsFacing(), isFlipped())
        val relativeRight = RelativeDirection.RIGHT.getRelativeFacing(getFrontFacing(), getUpwardsFacing(), isFlipped())
        return AxisAlignedBB(pos.offset(relativeBack, -4).offset(relativeRight, -7),
                             pos.offset(relativeBack, 10).offset(relativeRight, 7).up(4))
    }

    @SideOnly(Side.CLIENT)
    override fun renderBloomEffect(buffer: BufferBuilder, context: EffectRenderContext)
    {
        if (!isActive) return

        val texture = GTLiteTextures.FORCE_FIELD
        val minU = texture.minU.toDouble()
        val maxU = texture.maxU.toDouble()
        val minV = texture.minV.toDouble()
        val maxV = texture.maxV.toDouble()

        val frontFacing = getFrontFacing()
        val forward = frontFacing.opposite
        val right = forward.rotateY()

        // Center
        val cx = pos.x - context.cameraX() + forward.xOffset * 3 + 0.5
        val cy = pos.y - context.cameraY()
        val cz = pos.z - context.cameraZ() + forward.zOffset * 3 + 0.5

        Games.mc().textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE)
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX)

        for (side in 0..7)
        {
            renderForceField(buffer, cx, cy, cz, side, minU, maxU, minV, maxV,
                             forward.xOffset.toDouble(), forward.zOffset.toDouble(),
                             right.xOffset.toDouble(), right.zOffset.toDouble())
        }

        Tessellator.getInstance().draw()
    }

    @SideOnly(Side.CLIENT)
    override fun shouldRenderBloomEffect(context: EffectRenderContext): Boolean
        = isActive && context.camera().isBoundingBoxInFrustum(getRenderBoundingBox())

    @SideOnly(Side.CLIENT)
    private fun renderForceField(buffer: BufferBuilder, cx: Double, cy: Double, cz: Double, side: Int,
                                 minU: Double, maxU: Double, minV: Double, maxV: Double,
                                 fx: Double, fz: Double, rx: Double, rz: Double)
    {
        fun vertex(lx: Double, ly: Double, lz: Double, u: Double, v: Double)
        {
            buffer.pos(cx + lx * rx + lz * fx, cy + ly, cz + lx * rz + lz * fz).tex(u, v).endVertex()
        }
        // Center O:  0,  0         1 ------- 8
        // Corner 1:  7, -2        /           \
        // Corner 2:  3, -6     2 /             \ 7
        // Corner 3: -2, -6      |               |
        // Corner 4: -6, -2      |       O       |
        // Corner 5: -6,  3      |               |
        // Corner 6: -2,  7     3 \             / 6
        // Corner 7:  3,  7        \           /
        // Corner 8:  7,  3         4 ------- 5
        when (side)
        {
            0 ->
            {
                vertex(3.0, 0.0, 7.0, maxU, maxV)
                vertex(3.0, 4.0, 7.0, maxU, minV)
                vertex(-3.0, 4.0, 7.0, minU, minV)
                vertex(-3.0, 0.0, 7.0, minU, maxV)
            }
            1 ->
            {
                vertex(7.0, 0.0, 4.0, maxU, maxV)
                vertex(7.0, 4.0, 4.0, maxU, minV)
                vertex(7.0, 4.0, -4.0, minU, minV)
                vertex(7.0, 0.0, -4.0, minU, maxV)
            }
            2 ->
            {
                vertex(3.0, 0.0, -7.0, maxU, maxV)
                vertex(3.0, 4.0, -7.0, maxU, minV)
                vertex(-3.0, 4.0, -7.0, minU, minV)
                vertex(-3.0, 0.0, -7.0, minU, maxV)
            }
            3 ->
            {
                vertex(-7.0, 0.0, 4.0, maxU, maxV)
                vertex(-7.0, 4.0, 4.0, maxU, minV)
                vertex(-7.0, 4.0, -4.0, minU, minV)
                vertex(-7.0, 0.0, -4.0, minU, maxV)
            }
            4 ->
            {
                vertex(-3.0, 0.0, 7.0, maxU, maxV)
                vertex(-3.0, 4.0, 7.0, maxU, minV)
                vertex(-7.0, 4.0, 4.0, minU, minV)
                vertex(-7.0, 0.0, 4.0, minU, maxV)
            }
            5 ->
            {
                vertex(-3.0, 0.0, -7.0, maxU, maxV)
                vertex(-3.0, 4.0, -7.0, maxU, minV)
                vertex(-7.0, 4.0, -4.0, minU, minV)
                vertex(-7.0, 0.0, -4.0, minU, maxV)
            }
            6 ->
            {
                vertex(3.0, 0.0, 7.0, maxU, maxV)
                vertex(3.0, 4.0, 7.0, maxU, minV)
                vertex(7.0, 4.0, 4.0, minU, minV)
                vertex(7.0, 0.0, 4.0, minU, maxV)
            }
            7 ->
            {
                vertex(3.0, 0.0, -7.0, maxU, maxV)
                vertex(3.0, 4.0, -7.0, maxU, minV)
                vertex(7.0, 4.0, -4.0, minU, minV)
                vertex(7.0, 0.0, -4.0, minU, maxV)
            }
        }
    }

    @SideOnly(Side.CLIENT)
    override fun shouldRenderInPass(pass: Int): Boolean = pass == 0

    override fun isGlobalRenderer(): Boolean = true

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(data)
        val stack = installedMachine.getStackInSlot(0)
        if (!stack.isEmpty)
        {
            data.setTag("InstalledMachine", stack.writeToNBT(NBTTagCompound()))
        }
        return data
    }

    override fun readFromNBT(data: NBTTagCompound) {
        super.readFromNBT(data)
        installedMachine.setStackInSlot(0, ItemStack.EMPTY)
        if (data.hasKey("InstalledMachine"))
        {
            installedMachine.setStackInSlot(0, ItemStack(data.getCompoundTag("InstalledMachine")))
        }
    }

    private fun workableRecipeMaps(): Array<RecipeMap<*>>
    {
        val machine = installedMachine.getStackInSlot(0)
        if (machine.isEmpty)
            return arrayOf(QUANTUM_FORCE_TRANSFORMER_RECIPES)
        return when (machine.metadata)
        {
            //10125: LARGE_BURNER_REACTOR, 10126: LARGE_CRYOGENIC_REACTOR, 10131: CHEMICAL_PLANT
            10125 -> arrayOf(BURNER_REACTOR_RECIPES   , ROASTER_RECIPES       , QUANTUM_FORCE_TRANSFORMER_RECIPES)
            10126 -> arrayOf(CRYOGENIC_REACTOR_RECIPES, BATH_CONDENSER_RECIPES, QUANTUM_FORCE_TRANSFORMER_RECIPES)
            10131 -> arrayOf(LARGE_CHEMICAL_RECIPES   , CHEMICAL_PLANT_RECIPES, QUANTUM_FORCE_TRANSFORMER_RECIPES)
            else  -> arrayOf(                                                   QUANTUM_FORCE_TRANSFORMER_RECIPES)
        }
    }

    private inner class InstalledMachineSlot: NotifiableItemStackHandler(this, 1, null, false)
    {
        //10125: LARGE_BURNER_REACTOR, 10126: LARGE_CRYOGENIC_REACTOR, 10131: CHEMICAL_PLANT
        private val allowedMachines = arrayOf(10125, 10126, 10131)

        override fun isItemValid(slot: Int, stack: ItemStack): Boolean
        {
            if (stack.isEmpty)
                return false
            if (stack.item !is MachineItemBlock)
                return false
            return stack.metadata in allowedMachines
        }

        override fun getSlotLimit(slot: Int): Int = 1

        override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack
        {
            if (!simulate)
                return stack
            return super.insertItem(slot, stack, true)
        }

        override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack
        {
            if (!simulate)
                return ItemStack.EMPTY
            return super.extractItem(slot, amount, true)
        }

        override fun setStackInSlot(slot: Int, stack: ItemStack)
        {
            var s = stack.copy()
            if (!s.isEmpty && !isItemValid(slot, s))
                s = ItemStack.EMPTY
            if (s.count > getSlotLimit(slot))
                s.count = getSlotLimit(slot)
            super.setStackInSlot(slot, s)
        }

        override fun onContentsChanged(slot: Int)
        {
            recipeMapWorkable.forceRecipeRecheck()
            super.onContentsChanged(slot)
        }
    }

    private inner class QuantumForceTransformerRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {
        private val isQFTRecipeMap: Boolean
            get() = workingRecipeMap == QUANTUM_FORCE_TRANSFORMER_RECIPES

        override fun findRecipe(maxVoltage: Long, inputs: IItemHandlerModifiable?, fluidInputs: IMultipleTankHandler?): Recipe?
        {
            workableRecipeMaps().forEach { map ->
                val result = map.findRecipe(maxVoltage, inputs, fluidInputs)
                result?.let {
                    workingRecipeMap = map
                    return result
                }
            }
            return null
        }

        override fun getRecipeMap(): RecipeMap<*>? = workingRecipeMap

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)
            // +1200% / manipulator | D' = D / (1 + 12.0 * (T - 1.0)) = D / (12.0 * T - 11.0), where k = 12.0
            val actualTier = manipulatorTier + 1
            if (actualTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (12.0 * manipulatorTier - 11.0)).toInt()))
        }

        override fun getOverclockingDurationFactor(): Double
            = if (!isQFTRecipeMap || (shieldingCoreTier == 4 && manipulatorTier == 4)) PERFECT_DURATION_FACTOR / 2
              else super.getOverclockingDurationFactor()

        override fun getParallelLimit() = (if (isQFTRecipeMap) 16 else 1024) * shieldingCoreTier
    }
}