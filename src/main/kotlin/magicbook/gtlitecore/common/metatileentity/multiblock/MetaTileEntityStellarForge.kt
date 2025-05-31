package magicbook.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues.ULV
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.utils.TooltipHelper
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.STELLAR_FORGE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bedrockium
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.emitterCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.fieldGenCasings
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.world.World
import one.util.streamex.StreamEx
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer
import kotlin.math.floor
import kotlin.math.pow

class MetaTileEntityStellarForge(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, STELLAR_FORGE_RECIPES)
{

    private var emitterCasingTier = 0
    private var fieldGenCasingTier = 0

    init
    {
        recipeMapWorkable = StellarForgeRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var emitterCasings: List<IBlockState>
        private lateinit var fieldGenCasings: List<IBlockState>

        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.QUANTUM_ALLOY)

        private val secondCasingState
            get() = GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.STELLAR_CONTAINMENT_CASING)

        private val glassState
            get() = MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.FUSION_GLASS)

        private val coilState
            get() = GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.THERMAL_ENERGY_TRANSMISSION_CASING)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityStellarForge(metaTileEntityId)

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val emitterCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_EMITTER_CASING)
        val fieldGenCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_FIELD_GEN_CASING)
        val maxLength = maxLength(listOf(emitterCasing, fieldGenCasing))
        emitterCasings = consistent(emitterCasing, maxLength)
        fieldGenCasings = consistent(fieldGenCasing, maxLength)
        hasRegistered = true
    }

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("EmitterCasingTieredStats")
        val type2: Any? = context.get<Any>("FieldGenCasingTieredStats")
        emitterCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        fieldGenCasingTier = getOrDefault(
            { type2 is WrappedIntTier },
            { (type2 as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        emitterCasingTier = 0
        fieldGenCasingTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ")
        .aisle("        A A        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        AAA        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        AAA        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        A A        ")
        .aisle("        A A        ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "        A A        ")
        .aisle("        A A        ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "        A A        ")
        .aisle("        A A        ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "        A A        ")
        .aisle("        A A        ", " A A           A A ", "  C C   EEE   C C  ", " A A    EFE    A A ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", " A A    EFE    A A ", "  C C   EEE   C C  ", " A A           A A ", "        A A        ")
        .aisle("        A A        ", " A A    EEE    A A ", "  C C  E   E  C C  ", " A A   E   E   A A ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", " A A   E   E   A A ", "  C C  E   E  C C  ", " A A    EEE    A A ", "        A A        ")
        .aisle("        A A        ", "A A    EEEEE    A A", " C C  E     E  C C ", "A A   E     E   A A", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "A A   E     E   A A", " C C  E     E  C C ", "A A    EEEEE    A A", "        A A        ")
        .aisle(" AAAAAAA   AAAAAAA ", "Q A   EEEEEEE   A Q", "QC C E       E C CQ", "Q A  E       E  A Q", " A   E       E   A ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " A   E       E   A ", "Q A  E       E  A Q", "QC C E       E C CQ", "Q A   EEEEEEE   A Q", " AAAAAAAA AAAAAAAA ")
        .aisle("         G         ", "Q A   EEEHEEE   A Q", " C C E       E C C ", "Q A  F       F  A Q", " A   F       F   A ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", " A   F       F   A ", "Q A  F       F  A Q", " C C E       E C C ", "Q A   EEEHEEE   A Q", "         G         ")
        .aisle(" AAAAAAA   AAAAAAA ", "Q A   EEEEEEE   A Q", "QC C E       E C CQ", "Q A  E       E  A Q", " A   E       E   A ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " A   E       E   A ", "Q A  E       E  A Q", "QC C E       E C CQ", "Q A   EEEEEEE   A Q", " AAAAAAAA AAAAAAAA ")
        .aisle("        A A        ", "A A    EEEEE    A A", " C C  E     E  C C ", "A A   E     E   A A", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "A A   E     E   A A", " C C  E     E  C C ", "A A    EEEEE    A A", "        A A        ")
        .aisle("        A A        ", " A A    EEE    A A ", "  C C  E   E  C C  ", " A A   E   E   A A ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", " A A   E   E   A A ", "  C C  E   E  C C  ", " A A    EEE    A A ", "        A A        ")
        .aisle("        A A        ", " A A           A A ", "  C C   EEE   C C  ", " A A    EFE    A A ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", " A A    EFE    A A ", "  C C   EEE   C C  ", " A A           A A ", "        A A        ")
        .aisle("        A A        ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "        A A        ")
        .aisle("        A A        ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "        A A        ")
        .aisle("        A A        ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "        A A        ")
        .aisle("        A A        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        AAA        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        AAA        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        A A        ")
        .aisle("                   ", "       AQQQA       ", "        QSQ        ", "       AQQQA       ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ")
        .where('S', selfPredicate())
        // Both `A` and `Q` can put hatches but Energy Hatches can only replace `Q`,
        // because then we can see that one structure can hold almost 8 controller.
        .where('A', states(casingState)
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS, IMPORT_FLUIDS, EXPORT_FLUIDS)))
        .where('Q', states(casingState)
            .or(abilities(INPUT_ENERGY)
                .setMinGlobalLimited(1)
                .setMaxGlobalLimited(3)
                .setPreviewCount(1))
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS, IMPORT_FLUIDS, EXPORT_FLUIDS)))
        .where('D', frames(Bedrockium))
        .where('E', states(secondCasingState))
        .where('F', states(glassState))
        .where('C', states(coilState))
        .where('G', fieldGenCasings())
        .where('H', emitterCasings())
        .where(' ', any())
        .build()

    override fun getBaseTexture(texture: IMultiblockPart?): ICubeRenderer = GTLiteTextures.QUANTUM_ALLOY_CASING

    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.STELLAR_FORGE_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ")
            .aisle("        A A        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        AAA        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        AAA        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        A A        ")
            .aisle("        A A        ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "        A A        ")
            .aisle("        A A        ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "        A A        ")
            .aisle("        A A        ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "        A A        ")
            .aisle("        A A        ", " A A           A A ", "  C C   EEE   C C  ", " A A    EFE    A A ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", " A A    EFE    A A ", "  C C   EEE   C C  ", " A A           A A ", "        A A        ")
            .aisle("        A A        ", " A A    EEE    A A ", "  C C  E   E  C C  ", " A A   E   E   A A ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", " A A   E   E   A A ", "  C C  E   E  C C  ", " A A    EEE    A A ", "        A A        ")
            .aisle("        A A        ", "A A    EEEEE    A A", " C C  E     E  C C ", "A A   E     E   A A", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "A A   E     E   A A", " C C  E     E  C C ", "A A    EEEEE    A A", "        A A        ")
            .aisle(" AAAAAAA   AAAAAAA ", "Q A   EEEEEEE   A Q", "QC C E       E C CQ", "Q A  E       E  A Q", " A   E       E   A ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " A   E       E   A ", "Q A  E       E  A Q", "QC C E       E C CQ", "Q A   EEEEEEE   A Q", " AAAAAAAA AAAAAAAA ")
            .aisle("         G         ", "Q A   EEEHEEE   A Q", " C C E       E C C ", "Q A  F       F  A Q", " A   F       F   A ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", " A   F       F   A ", "Q A  F       F  A Q", " C C E       E C C ", "Q A   EEEHEEE   A Q", "         G         ")
            .aisle(" AAAAAAA   AAAAAAA ", "Q A   EEEEEEE   A Q", "QC C E       E C CQ", "Q A  E       E  A Q", " A   E       E   A ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " A   E       E   A ", "Q A  E       E  A Q", "QC C E       E C CQ", "Q A   EEEEEEE   A Q", " AAAAAAAA AAAAAAAA ")
            .aisle("        A A        ", "A A    EEEEE    A A", " C C  E     E  C C ", "A A   E     E   A A", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "A A   E     E   A A", " C C  E     E  C C ", "A A    EEEEE    A A", "        A A        ")
            .aisle("        A A        ", " A A    EEE    A A ", "  C C  E   E  C C  ", " A A   E   E   A A ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", " A A   E   E   A A ", "  C C  E   E  C C  ", " A A    EEE    A A ", "        A A        ")
            .aisle("        A A        ", " A A           A A ", "  C C   EEE   C C  ", " A A    EFE    A A ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", " A A    EFE    A A ", "  C C   EEE   C C  ", " A A           A A ", "        A A        ")
            .aisle("        A A        ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "        A A        ")
            .aisle("        A A        ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "        A A        ")
            .aisle("        A A        ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "        A A        ")
            .aisle("        A A        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        AAA        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        AAA        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        A A        ")
            .aisle("                   ", "       AKPLA       ", "        ISJ        ", "       AQQQA       ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ")
            .where('S', GTLiteMetaTileEntities.STELLAR_FORGE, EnumFacing.SOUTH)
            .where('A', casingState)
            .where('Q', casingState)
            .where('D', MetaBlocks.FRAMES[Bedrockium]!!.getBlock(Bedrockium))
            .where('E', secondCasingState)
            .where('F', glassState)
            .where('C', coilState)
            .where('P', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.SOUTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[ULV], EnumFacing.SOUTH)
            .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[ULV], EnumFacing.SOUTH)
        val count = AtomicInteger()
        StreamEx.of(emitterCasings)
            .map { b ->
                builder.where('H', b)
                builder.where('G', fieldGenCasings[count.get()])
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
            if (emitterCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (fieldGenCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(emitterCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(fieldGenCasingTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            emitterCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            fieldGenCasingTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.stellar_forge.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.stellar_forge.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.stellar_forge.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.stellar_forge.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.stellar_forge.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.stellar_forge.tooltip.6"))
    }

    override fun canBeDistinct() = true

    override fun hasMaintenanceMechanics() = false

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    private inner class StellarForgeRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity, true)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.5.pow(emitterCasingTier)).toInt())
        }

        override fun getParallelLimit() =  32 * fieldGenCasingTier

    }

}
