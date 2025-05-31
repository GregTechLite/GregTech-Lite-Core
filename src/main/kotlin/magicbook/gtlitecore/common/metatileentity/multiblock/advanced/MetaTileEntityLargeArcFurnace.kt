package magicbook.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GregTechAPI
import gregtech.api.block.IHeatingCoilBlockStats
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.ALLOY_SMELTER_RECIPES
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.BlockMultiblockCasing
import gregtech.common.blocks.BlockWireCoil
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.pumpCasings
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import one.util.streamex.StreamEx
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.floor
import kotlin.math.pow

class MetaTileEntityLargeArcFurnace(metaTileEntityId: ResourceLocation?) : MultiMapMultiblockController(metaTileEntityId, arrayOf(ARC_FURNACE_RECIPES, ALLOY_SMELTER_RECIPES))
{

    private var pumpCasingTier = 0
    private var coilTier = 0

    init
    {
        recipeMapWorkable = LargeArcFurnaceRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var pumpCasings: List<IBlockState>
        private lateinit var heatingCoils: List<IBlockState>

        private val casingState
            get() = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF)

        private val secondCasingState
            get() = MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)

        private val pipeCasingState
            get() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val pumpCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PUMP_CASING)
        val heatingCoil = LazyStreams.fastSortedByKey(GregTechAPI.HEATING_COILS)
        val maxLength = maxLength(listOf(pumpCasing, heatingCoil))
        pumpCasings = consistent(pumpCasing, maxLength)
        heatingCoils = consistent(heatingCoil, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityLargeArcFurnace(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("PumpCasingTieredStats")
        val type2: Any? = context.get<Any>("CoilType")
        pumpCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        coilTier = if (type2 is IHeatingCoilBlockStats) type2.tier else BlockWireCoil.CoilType.CUPRONICKEL.tier
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pumpCasingTier = 0
        coilTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " CCC ", " CCC ")
        .aisle("CCCCC", "CHQHC", " CGC ")
        .aisle("CCCCC", "CHPHC", " CGC ")
        .aisle("CCCCC", "CHQHC", " CGC ")
        .aisle(" CCC ", " CSC ", " CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(10)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('G', states(secondCasingState))
        .where('Q', states(pipeCasingState))
        .where('P', pumpCasings())
        .where('H', heatingCoils())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.HEAT_PROOF_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_ARC_FURNACE_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle(" CEC ", " CCC ", " CCC ")
            .aisle("CCCCC", "CHQHC", " CGC ")
            .aisle("CCCCC", "CHPHC", " CGC ")
            .aisle("CCCCC", "CHQHC", " CGC ")
            .aisle(" KNL ", " ISJ ", " CCC ")
            .where('S', GTLiteMetaTileEntities.LARGE_ARC_FURNACE, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('G', secondCasingState)
            .where('Q', pipeCasingState)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
            .where('N', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
            .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH)
        val count = AtomicInteger()
        StreamEx.of(pumpCasings)
            .map { b ->
                builder.where('P', b)
                builder.where('H', heatingCoils[count.get()])
                count.getAndIncrement()
                builder
            }
            .nonNull()
            .forEach { b -> shapeInfo.add(b.build()) }
        return shapeInfo
    }

    override fun update()
    {
        super.update()
        if (world.isRemote)
        {
            if (pumpCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (coilTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(pumpCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(coilTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            pumpCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            coilTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_arc_furnace.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_arc_furnace.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_arc_furnace.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_arc_furnace.tooltip.4"))
    }

    override fun canBeDistinct(): Boolean = true

    private inner class LargeArcFurnaceRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(coilTier))).toInt())
        }

        override fun getParallelLimit() = 8 * pumpCasingTier

    }

}
