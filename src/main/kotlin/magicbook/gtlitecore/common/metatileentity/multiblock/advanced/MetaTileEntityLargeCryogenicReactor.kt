package magicbook.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.IMufflerHatch
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.BlockMultiblockCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BATH_CONDENSER_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYOGENIC_REACTOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HSLASteel
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.pumpCasings
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MetaTileEntityLargeCryogenicReactor(metaTileEntityId: ResourceLocation?) : MultiMapMultiblockController(metaTileEntityId, arrayOf(CRYOGENIC_REACTOR_RECIPES, BATH_CONDENSER_RECIPES))
{

    private var casingTier = 0

    init
    {
        recipeMapWorkable = LargeCryogenicReactorRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.MONEL_500)

        private val secondCasingState
            get() = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)

        private val thirdCasingState
            get() = MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)

        private val pipeCasingState
            get() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityLargeCryogenicReactor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type: Any? = context.get<Any>("PumpCasingTieredStats")
        casingTier = getOrDefault(
            { type is WrappedIntTier },
            { (type as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        casingTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("   FF", "   DU", "   UU", "   DU", "   DD")
        .aisle("F  FF", "CCCDD", "CCQQU", "CCCDD", "   DD")
        .aisle("     ", "CCCC ", "CPQQ ", "CCCC ", "     ")
        .aisle("F  F ", "CCCC ", "CSCC ", "CCCC ", "     ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(6)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('D', states(secondCasingState)
            .or(abilities(MultiblockAbility.MUFFLER_HATCH)
                .setExactLimit(1)))
        .where('U', states(thirdCasingState))
        .where('Q', states(pipeCasingState))
        .where('F', frames(HSLASteel))
        .where('P', pumpCasings())
        .where(' ', any())
        .build()

    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = if (sourcePart is IMufflerHatch)
        Textures.SOLID_STEEL_CASING // Muffler at Cooling Tower part (Steel Casing).
    else
        GTLiteTextures.MONEL_500_CASING // Other part at Main part (Monel-500 Casing).

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_CRYOGENIC_REACTOR_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("   FF", "   DU", "   UU", "   DU", "   DD")
            .aisle("F  FF", "ECCDD", "CCQQU", "CCCDD", "   OD")
            .aisle("     ", "CCCC ", "CPQQ ", "CCCC ", "     ")
            .aisle("F  F ", "KMLC ", "ISJC ", "CCCC ", "     ")
            .where('S', GTLiteMetaTileEntities.LARGE_CRYOGENIC_REACTOR, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('D', secondCasingState)
            .where('U', thirdCasingState)
            .where('Q', pipeCasingState)
            .where('F', MetaBlocks.FRAMES[HSLASteel]!!.getBlock(HSLASteel))
            .where('M', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.SOUTH)
            .where('O', MetaTileEntities.MUFFLER_HATCH[1], EnumFacing.UP)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
            .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH)
        GTLiteAPI.MAP_PUMP_CASING.entries
            .sortedBy { entry -> (entry.value as WrappedIntTier).getIntTier() }
            .forEach { entry -> shapeInfo.add(builder.where('P', entry.key).build()) }
        return shapeInfo
    }

    override fun update()
    {
        super.update()
        if (world.isRemote && casingTier == 0)
            writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(casingTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            casingTier = buf.readInt()
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeInt(casingTier)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        casingTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_cryogenic_reactor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_cryogenic_reactor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_cryogenic_reactor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_cryogenic_reactor.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class LargeCryogenicReactorRecipeLogic(metaTileEntity: RecipeMapMultiblockController) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(getTierByVoltage(maxVoltage).toDouble()))).toInt())
        }

        override fun getParallelLimit() = 16 * casingTier

    }

}
