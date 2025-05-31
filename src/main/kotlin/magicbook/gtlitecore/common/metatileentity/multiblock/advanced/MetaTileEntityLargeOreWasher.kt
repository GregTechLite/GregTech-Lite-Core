package magicbook.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CRACKING_RECIPES
import gregtech.api.recipes.RecipeMaps.ORE_WASHER_RECIPES
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CATALYTIC_REFORMER_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.pumpCasings
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing01
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

class MetaTileEntityLargeOreWasher(metaTileEntityId: ResourceLocation) : MultiMapMultiblockController(metaTileEntityId, arrayOf(ORE_WASHER_RECIPES, CHEMICAL_BATH_RECIPES, CRACKING_RECIPES, CATALYTIC_REFORMER_RECIPES))
{

    private var casingTier = 0

    init
    {
        recipeMapWorkable = LargeOreWasherRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_01.getState(BlockMetalCasing01.MetalCasingType.GRISIUM)

        private val pipeCasingState
            get() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityLargeOreWasher(metaTileEntityId)

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

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start() // TODO Water?
        .aisle("CCCCC", "CCCCC", "CCCCC")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCPCC", "CCSCC", "CCCCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(9)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('Q', states(pipeCasingState))
        .where('P', pumpCasings())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.GRISIUM_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_ORE_WASHER_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("CCECC", "CCCCC", "CCCCC")
            .aisle("CCCCC", "CQ QC", "C   C")
            .aisle("CCCCC", "CQ QC", "C   C")
            .aisle("CCCCC", "CQ QC", "C   C")
            .aisle("CCCCC", "CQ QC", "C   C")
            .aisle("CCCCC", "CQ QC", "C   C")
            .aisle("CKPLC", "CISJC", "CCMCC")
            .where('S', GTLiteMetaTileEntities.LARGE_ORE_WASHER, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('Q', pipeCasingState)
            .where('M', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
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
        tooltip.add(I18n.format("gtlitecore.machine.large_ore_washer.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_ore_washer.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_ore_washer.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_ore_washer.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class LargeOreWasherRecipeLogic(metaTileEntity: RecipeMapMultiblockController) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(getTierByVoltage(maxVoltage).toDouble()))).toInt())
        }

        override fun getParallelLimit() = 16 * casingTier

    }

}

