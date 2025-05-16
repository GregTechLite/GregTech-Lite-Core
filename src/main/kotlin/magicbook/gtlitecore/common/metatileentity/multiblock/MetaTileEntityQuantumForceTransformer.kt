package magicbook.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues.ULV
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.TextComponentUtil.translationWithColor
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.QUANTUM_FORCE_TRANSFORMER_RECIPES
import magicbook.gtlitecore.api.recipe.property.QuantumForceTransformerTieredProperty
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.manipulators
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.shieldingCores
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing01
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import one.util.streamex.StreamEx
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow


@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityQuantumForceTransformer(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, QUANTUM_FORCE_TRANSFORMER_RECIPES)
                                                                                 // TODO IFastRenderMetaTileEntity, IBloomEffect
{

    private var manipulatorTier = 0
    private var shieldingCoreTier = 0

    init
    {
        recipeMapWorkable = QuantumForceTransformerRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var manipulators: List<IBlockState>
        private lateinit var shieldingCores: List<IBlockState>

        private val casingState
            get() = GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.PARTICLE_CONTAINMENT_CASING)
        private val coilState
            get() = GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.PARTICLE_EXCITATION_WIRE_COIL)
        private val glassState
            get() = GTLiteMetaBlocks.TRANSPARENT_CASING_01.getState(BlockGlassCasing01.GlassType.FORCE_FIELD)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val manipulator = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_MANIPULATOR)
        val shieldingCore = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_SHIELDING_CORE)
        val maxLength = maxLength(listOf(manipulator, shieldingCore))
        manipulators = consistent(manipulator, maxLength)
        shieldingCores = consistent(shieldingCore, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityQuantumForceTransformer(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("ManipulatorTieredStats")
        val type2: Any? = context.get<Any>("ShieldingCoreTieredStats")
        manipulatorTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        shieldingCoreTier = getOrDefault(
            { type2 is WrappedIntTier },
            { (type2 as WrappedIntTier).getIntTier() }, 0)
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
        val inputEnergy: MutableList<IEnergyContainer> = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        inputEnergy.addAll(getAbilities(SUBSTATION_INPUT_ENERGY))
        energyContainer = EnergyContainerList(inputEnergy)
    }

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
            .or(abilities(IMPORT_ITEMS, IMPORT_FLUIDS)))
        .where('F', states(casingState)
            .setMinGlobalLimited(16)
            .or(abilities(EXPORT_ITEMS, EXPORT_FLUIDS)))
        .where('A', manipulators())
        .where('B', shieldingCores())
        .where('C', states(coilState))
        .where('D', states(casingState))
        .where('E', states(glassState))
        .where(' ', any())
        .where('#', air())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.PARTICLE_CONTAINMENT_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.QUANTUM_FORCE_TRANSFORMER_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo: MutableList<MultiblockShapeInfo> = ArrayList()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("    A     A    ", "    A     A    ", "    A     A    ", "   BA     AB   ", "   BABBABBAB   ", "   BAAAAAAAB   ", "   BBBBABBBB   ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
            .aisle("               ", "               ", "               ", "  A         A  ", "  A         A  ", "  B         B  ", "  BAAAAAAAAAB  ", "   AAABBBAAA   ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
            .aisle("               ", "               ", "               ", " A           A ", " A           A ", " B           B ", " BAA       AAB ", "  AA       AA  ", "    AA   AA    ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
            .aisle("A             A", "A             A", "A             A", "A             A", "A             A", "B             B", "BAA         AAB", " AA         AA ", "   AA     AA   ", "     BAAAB     ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
            .aisle("      NXH      ", "      EEE      ", "      EEE      ", "      EEE      ", "B     DDD     B", "B     EEE     B", "BA    DDD    AB", " A    EEE    A ", "  AA  EEE  AA  ", "    BAEEEAB    ", "      DDD      ", "      EEE      ", "      EEE      ", "      EEE      ", "      DDD      ", "      EEE      ", "      DDD      ", "      EEE      ", "      EEE      ", "      EEE      ", "      FFF      ")
            .aisle("     HHHHH     ", "     ECCCE     ", "     ECCCE     ", "B    ECCCE    B", "B    D   D    B", "B    ECCCE    B", "BA   D   D   AB", " A   ECCCE   A ", "  A  ECCCE  A  ", "   BAECCCEAB   ", "     D   D     ", "     ECCCE     ", "     ECCCE     ", "     ECCCE     ", "     D   D     ", "     ECCCE     ", "     D   D     ", "     ECCCE     ", "     ECCCE     ", "     ECCCE     ", "     FFFFF     ")
            .aisle("    HHHHHHH    ", "    EC   CE    ", "A   EC   CE   A", "A   EC   CE   A", "A   D     D   A", "A   EC   CE   A", "BA  D     D  AB", "BB  EC   CE  BB", " B  EC   CE  B ", "  BAEC   CEAB  ", "    D     D    ", "    EC   CE    ", "    EC   CE    ", "    EC   CE    ", "    D     D    ", "    EC   CE    ", "    D     D    ", "    EC   CE    ", "    EC   CE    ", "    ECCCCCE    ", "    FFFFFFF    ")
            .aisle("    HHHHHHH    ", "    EC   CE    ", "    EC   CE    ", "    EC   CE    ", "A   D     D   A", "A   EC   CE   A", "AA  D     D  AA", "AB  EC   CE  BA", " A  EC   CE  A ", "  AAEC   CEAA  ", "    D     D    ", "    EC   CE    ", "    EC   CE    ", "    EC   CE    ", "    D     D    ", "    EC   CE    ", "    D     D    ", "    EC   CE    ", "    EC   CE    ", "    ECCCCCE    ", "    FFFFFFF    ")
            .aisle("    HHHHHHH    ", "    EC   CE    ", "    EC   CE    ", "A   EC   CE   A", "A   D     D   A", "A   EC   CE   A", "BA  D     D  AB", "BB  EC   CE  BB", " B  EC   CE  B ", "  BAEC   CEAB  ", "    D     D    ", "    EC   CE    ", "    EC   CE    ", "    EC   CE    ", "    D     D    ", "    EC   CE    ", "    D     D    ", "    EC   CE    ", "    EC   CE    ", "    ECCCCCE    ", "    FFFFFFF    ")
            .aisle("     HHHHH     ", "     ECCCE     ", "     ECCCE     ", "B    ECCCE    B", "B    D   D    B", "B    ECCCE    B", "BA   D   D   AB", " A   ECCCE   A ", "  A  ECCCE  A  ", "   BAECCCEAB   ", "     D   D     ", "     ECCCE     ", "     ECCCE     ", "     ECCCE     ", "     D   D     ", "     ECCCE     ", "     D   D     ", "     ECCCE     ", "     ECCCE     ", "     ECCCE     ", "     FFFFF     ")
            .aisle("      ISK      ", "      EEE      ", "      EEE      ", "      EEE      ", "B     DDD     B", "B     EEE     B", "BA    DDD    AB", " A    EEE    A ", "  AA  EEE  AA  ", "    BAEEEAB    ", "      DDD      ", "      EEE      ", "      EEE      ", "      EEE      ", "      DDD      ", "      EEE      ", "      DDD      ", "      EEE      ", "      EEE      ", "      EEE      ", "      JFL      ")
            .aisle("A             A", "A             A", "A             A", "A             A", "A             A", "B             B", "BAA          AB", " AA         AA ", "   AA     AA   ", "     BAAAB     ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
            .aisle("               ", "               ", "               ", " A           A ", " A           A ", " B           B ", " BA         AB ", "  AA       AA  ", "    AA   AA    ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
            .aisle("               ", "               ", "               ", "  A         A  ", "  A         A  ", "  B         B  ", "  BAAAAAAAAAB  ", "   AAABBBAAA   ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
            .aisle("    A     A    ", "    A     A    ", "    A     A    ", "   BA     AB   ", "   BABBABBAB   ", "   BAAAAAAAB   ", "   BBBBABBBB   ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
            .where('S', GTLiteMetaTileEntities.QUANTUM_FORCE_TRANSFORMER, EnumFacing.SOUTH)
            .where('H', casingState)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[ULV], EnumFacing.SOUTH)
            .where('X', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.NORTH)
            .where('N', MetaTileEntities.MAINTENANCE_HATCH, EnumFacing.NORTH)
            .where('F', casingState)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[ULV], EnumFacing.SOUTH)
            .where('C', coilState)
            .where('D', casingState)
            .where('E', glassState)
        val count = AtomicInteger()
        StreamEx.of(manipulators)
            .map { b ->
                if (builder != null)
                {
                    builder.where('A', b)
                    builder.where('B', shieldingCores[count.get()])
                    count.getAndIncrement()
                }
                builder
            }
            .nonNull()
            .forEach(Consumer { b -> shapeInfo.add(b.build()) })
        return shapeInfo
    }

    override fun update()
    {
        super.update()
        if ((world as World).isRemote)
        {
            if (manipulatorTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (shieldingCoreTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(manipulatorTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(shieldingCoreTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            manipulatorTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            shieldingCoreTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.7"))
    }

    override fun addDisplayText(textList: MutableList<ITextComponent>)
    {
        MultiblockDisplayText.builder(textList, isStructureFormed)
            .setWorkingStatus(recipeMapWorkable.isWorkingEnabled,
                recipeMapWorkable.isActive)
            .addEnergyUsageLine(energyContainer)
            .addEnergyTierLine(getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
            .addCustom { tl ->
                if (isStructureFormed)
                {
                    tl.add(translationWithColor(TextFormatting.GRAY,
                        "gtlitecore.machine.quantum_force_transformer.manipulator_info",
                        manipulatorTier) as ITextComponent)
                    tl.add(translationWithColor(TextFormatting.GRAY,
                        "gtlitecore.machine.quantum_force_transformer.shielding_core_info",
                        shieldingCoreTier) as ITextComponent)
                }
            }
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progressPercent)
    }

    override fun canBeDistinct() = true

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        recipe.chancedOutputs.chancedEntries.forEach { chanceEntry ->
            min(chanceEntry.chance * shieldingCoreTier, 10000)
        }
        recipe.chancedFluidOutputs.chancedEntries.forEach { chanceEntry ->
            min(chanceEntry.chance * shieldingCoreTier, 10000)
        }
        return super.checkRecipe(recipe, consumeIfSuccess)
                && recipe.getProperty(QuantumForceTransformerTieredProperty.getInstance(), 0)!! <= manipulatorTier
    }

    // @SideOnly(Side.CLIENT)
    // override fun renderMetaTileEntity(x: Double, y: Double, z: Double, partialTicks: Float)
    // {
    //     val forceField = GTLiteTextures.FORCE_FIELD
    //     if (isActive && MinecraftForgeClient.getRenderPass() == 0) {
    //         BloomEffectUtil.registerBloomRender(ForceFieldRenderer.INSTANCE, BloomType.UNREAL, this, this) { buffer ->
    //             val entity: Entity? = Minecraft.getMinecraft().renderViewEntity
    //             if (entity != null) {
    //                 val minU = forceField.minU.toDouble()
    //                 val maxU = forceField.maxU.toDouble()
    //                 val minV = forceField.minV.toDouble()
    //                 val maxV = forceField.maxV.toDouble()
    //                 val xBaseOffset: Double = (3 * getFrontFacing().getOpposite().getXOffset()).toDouble()
    //                 val zBaseOffset: Double = (3 * getFrontFacing().getOpposite().getZOffset()).toDouble()
    //                 GlStateManager.pushMatrix()
    //                 GlStateManager.disableCull()
    //                 GlStateManager.disableAlpha()
    //                 GlStateManager.enableBlend()
    //                 Minecraft.getMinecraft().textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE)
    //                 //Center O:  0,  0         1 ------- 8
    //                 //Corner 1:  7, -2        /           \
    //                 //Corner 2:  3, -6     2 /             \ 7
    //                 //Corner 3: -2, -6      |               |
    //                 //Corner 4: -6, -2      |       O       |
    //                 //Corner 5: -6,  3      |               |
    //                 //Corner 6: -2,  7     3 \             / 6
    //                 //Corner 7:  3,  7        \           /
    //                 //Corner 8:  7,  3         4 ------- 5
    //                 buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX)
    //                 GlStateManager.translate(
    //                     x + xBaseOffset + 0.5,
    //                     0,
    //                     MathUtils.z + zBaseOffset + 0.5
    //                 )
    //                 if (zBaseOffset == 0.0) {
    //                     GlStateManager.rotate(90f, 0f, 1f, 0f)
    //                 }
    //                 for (i in 0..7) {
    //                     renderForceField(buffer, 0, MathUtils.y, 0, i, minU, maxU, minV, maxV)
    //                 }
    //                 Tessellator.getInstance().draw()
    //                 GlStateManager.disableBlend()
    //                 GlStateManager.enableAlpha()
    //                 GlStateManager.enableCull()
    //                 GlStateManager.popMatrix()
    //             }
    //         }
    //     }
    // }

    // @SideOnly(Side.CLIENT)
    // private fun renderForceField(buffer: BufferBuilder, x: Double, y: Double, z: Double, side: Int,
    //                              minU: Double, maxU: Double, minV: Double, maxV: Double)
    // {
    //     when (side)
    //     {
    //         0 -> {
    //             buffer.pos(x + 3, y, z + 7).tex(maxU, maxV).endVertex()
    //             buffer.pos(x + 3, y + 4, z + 7).tex(maxU, minV).endVertex()
    //             buffer.pos(x - 3, y + 4, z + 7).tex(minU, minV).endVertex()
    //             buffer.pos(x - 3, y, z + 7).tex(minU, maxV).endVertex()
    //         }
    //         1 -> {
    //             buffer.pos(x + 7, y, z + 4).tex(maxU, maxV).endVertex()
    //             buffer.pos(x + 7, y + 4, z + 4).tex(maxU, minV).endVertex()
    //             buffer.pos(x + 7, y + 4, z - 4).tex(minU, minV).endVertex()
    //             buffer.pos(x + 7, y, z - 4).tex(minU, maxV).endVertex()
    //         }
    //         2 -> {
    //             buffer.pos(x + 3, y, z - 7).tex(maxU, maxV).endVertex()
    //             buffer.pos(x + 3, y + 4, z - 7).tex(maxU, minV).endVertex()
    //             buffer.pos(x - 3, y + 4, z - 7).tex(minU, minV).endVertex()
    //             buffer.pos(x - 3, y, z - 7).tex(minU, maxV).endVertex()
    //         }
    //         3 -> {
    //             buffer.pos(x - 7, y, z + 4).tex(maxU, maxV).endVertex()
    //             buffer.pos(x - 7, y + 4, z + 4).tex(maxU, minV).endVertex()
    //             buffer.pos(x - 7, y + 4, z - 4).tex(minU, minV).endVertex()
    //             buffer.pos(x - 7, y, z - 4).tex(minU, maxV).endVertex()
    //         }
    //         4 -> {
    //             buffer.pos(x - 3, y, z + 7).tex(maxU, maxV).endVertex()
    //             buffer.pos(x - 3, y + 4, z + 7).tex(maxU, minV).endVertex()
    //             buffer.pos(x - 7, y + 4, z + 4).tex(minU, minV).endVertex()
    //             buffer.pos(x - 7, y, z + 4).tex(minU, maxV).endVertex()
    //         }
    //         5 -> {
    //             buffer.pos(x - 3, y, z - 7).tex(maxU, maxV).endVertex()
    //             buffer.pos(x - 3, y + 4, z - 7).tex(maxU, minV).endVertex()
    //             buffer.pos(x - 7, y + 4, z - 4).tex(minU, minV).endVertex()
    //             buffer.pos(x - 7, y, z - 4).tex(minU, maxV).endVertex()
    //         }
    //         6 -> {
    //             buffer.pos(x + 3, y, z + 7).tex(maxU, maxV).endVertex()
    //             buffer.pos(x + 3, y + 4, z + 7).tex(maxU, minV).endVertex()
    //             buffer.pos(x + 7, y + 4, z + 4).tex(minU, minV).endVertex()
    //             buffer.pos(x + 7, y, z + 4).tex(minU, maxV).endVertex()
    //         }
    //         7 -> {
    //             buffer.pos(x + 3, y, z - 7).tex(maxU, maxV).endVertex()
    //             buffer.pos(x + 3, y + 4, z - 7).tex(maxU, minV).endVertex()
    //             buffer.pos(x + 7, y + 4, z - 4).tex(minU, minV).endVertex()
    //             buffer.pos(x + 7, y, z - 4).tex(minU, maxV).endVertex()
    //         }
    //     }
    // }

    inner class QuantumForceTransformerRecipeLogic(metaTileEntity: RecipeMapMultiblockController) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.75.pow(manipulatorTier)).toInt())
        }

        override fun getParallelLimit() = 16 * shieldingCoreTier

    }

}