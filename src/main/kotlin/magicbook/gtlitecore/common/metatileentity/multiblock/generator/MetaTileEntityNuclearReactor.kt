package magicbook.gtlitecore.common.metatileentity.multiblock.generator

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.FuelMultiblockController
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.ConfigHolder
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.NUCLEAR_FUELS
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.nuclearReactorCores
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockActiveUniqueCasing01
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing01
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

/**
 * Reactor Core buff factors:
 * - Thorium 1.05x
 * - Protactinium 1.1x
 * - Uranium 1.3x
 * - Neptunium 1.4x
 * - Plutonium 1.5x
 * - Americium 1.8x
 * - Curium 1.85x
 * - Berkelium 1.9x
 * - Californium 2.0x
 * - Einsteinium 2.5x
 * - Fermium 3x
 * - Mendelevium 3.2x
 */
class MetaTileEntityNuclearReactor(metaTileEntityId: ResourceLocation?) : FuelMultiblockController(metaTileEntityId, NUCLEAR_FUELS, EV)
{

    private var coreTier = 0

    init
    {
        recipeMapWorkable = NuclearReactorWorkableHandler(this)
        recipeMapWorkable.maximumOverclockVoltage = V[UHV] // TODO Should we confirm it is enough for EV-UV stages energy required?
                                                           //      If not, change it to V[MAX], or more higher?
    }

    companion object
    {
        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.INCONEL_718)
        private val secondCasingState
            get() = GTLiteMetaBlocks.ACTIVE_UNIQUE_CASING_01.getState(BlockActiveUniqueCasing01.UniqueCasingType.TEMPERATURE_CONTROLLER)
        private val glassState
            get() = GTLiteMetaBlocks.TRANSPARENT_CASING_01.getState(BlockGlassCasing01.GlassType.LEAD_SILICON)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityNuclearReactor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type: Any? = context.get<Any>("NuclearReactorCoreTieredStats")
        coreTier = getOrDefault(
            { type is WrappedIntTier },
            { (type as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        coreTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "DDD", "CGC", "CGC", "CGC", "CGC", "CGC", "CGC", "CCC")
        .aisle("CCC", "DDD", "GXG", "GXG", "GXG", "GXG", "GXG", "GXG", "COC")
        .aisle("CSC", "DDD", "CGC", "CGC", "CGC", "CGC", "CGC", "CGC", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(30)
            .or(abilities(OUTPUT_ENERGY)
                .setMinGlobalLimited(1)
                .setPreviewCount(1))
            .or(autoAbilities(false, true, true, true, true, true, false)))
        .where('D', states(secondCasingState))
        .where('G', states(glassState))
        .where('O', abilities(MUFFLER_HATCH))
        .where('X', nuclearReactorCores())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.INCONEL_718_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.NUCLEAR_REACTOR_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("EMC", "DDD", "CGC", "CGC", "CGC", "CGC", "CGC", "CGC", "CCC")
            .aisle("CCC", "DDD", "GXG", "GXG", "GXG", "GXG", "GXG", "GXG", "COC")
            .aisle("ISJ", "DDD", "CGC", "CGC", "CGC", "CGC", "CGC", "CGC", "KCL")
            .where('S', GTLiteMetaTileEntities.NUCLEAR_REACTOR, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('D', secondCasingState)
            .where('G', glassState)
            .where('E', MetaTileEntities.ENERGY_OUTPUT_HATCH[ULV], EnumFacing.NORTH)
            .where('M', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState}, EnumFacing.NORTH)
            .where('O', MetaTileEntities.MUFFLER_HATCH[LV], EnumFacing.UP)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[ULV], EnumFacing.SOUTH)
            .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[ULV], EnumFacing.SOUTH)
        GTLiteAPI.MAP_NUCLEAR_REACTOR_CORE.entries
            .sortedBy { entry -> (entry.value as WrappedIntTier).getIntTier() }
            .forEach { entry -> shapeInfo.add(builder.where('X', entry.key).build()) }
        return shapeInfo
    }

    override fun update()
    {
        super.update()
        if (world.isRemote && coreTier == 0)
            writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(coreTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            coreTier = buf.readInt()
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeInt(coreTier)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        coreTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack?,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.7"))
    }

    override fun canBeDistinct() = false

    override fun hasMufflerMechanics() = true

    override fun getMufflerParticle(): EnumParticleTypes = EnumParticleTypes.CLOUD // TODO confirm

    /**
     * Get boosted factor from [coreTier] to give reactor production a boost.
     */
    fun getBoostedFromCoreTier(tier: Int): Double = when (tier)
    {
        (1) -> 1.05 // Th
        (2) -> 1.1 // Pa
        (3) -> 1.3 // U
        (4) -> 1.4 // Np
        (5) -> 1.5 // Pu
        (6) -> 1.8 // Am
        (7) -> 1.85 // Cm
        (8) -> 1.9 // Bk
        (9) -> 2.0 // Cf
        (10) -> 2.5 // Es
        (11) -> 3.0 // Fm
        (12) -> 3.2 // Md
        else -> 0.0 // Ensure it not cause problem for workableHandler progress max value.
    }

    private inner class NuclearReactorWorkableHandler(metaTileEntity: RecipeMapMultiblockController) : MultiblockFuelRecipeLogic(metaTileEntity)
    {

        override fun getMaxVoltage() = max(super.getMaxVoltage().toDouble(), super.getMaxVoltage() * getBoostedFromCoreTier(coreTier)).toLong()

        override fun getParallelLimit() = Int.MAX_VALUE

    }

}