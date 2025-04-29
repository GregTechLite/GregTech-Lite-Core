package magicbook.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.ULV
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ADVANCED_FUSION_RECIPES
import magicbook.gtlitecore.api.recipe.property.AdvancedFusionTieredProperty
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.cryostats
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.divertors
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.fusionCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.fusionCoils
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.vacuums
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
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
class MetaTileEntityAdvancedFusionReactor(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, ADVANCED_FUSION_RECIPES)
{

    private var fusionCasingTier = 0
    private var fusionCoilTier = 0
    private var cryostatTier = 0
    private var divertorTier = 0
    private var vacuumTier = 0

    init
    {
        recipeMapWorkable = AdvancedFusionRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var fusionCasings: List<IBlockState>
        private lateinit var fusionCoils: List<IBlockState>
        private lateinit var cryostats: List<IBlockState>
        private lateinit var divertors: List<IBlockState>
        private lateinit var vacuums: List<IBlockState>
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val fusionCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_FUSION_CASING)
        val fusionCoil = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_FUSION_COIL)
        val cryostat = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_CRYOSTAT)
        val divertor = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_DIVERTOR)
        val vacuum = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_VACUUM)
        val maxLength = maxLength(listOf(fusionCasing, fusionCoil, cryostat, divertor, vacuum))
        fusionCasings = consistent(fusionCasing, maxLength)
        fusionCoils = consistent(fusionCoil, maxLength)
        cryostats = consistent(cryostat, maxLength)
        divertors = consistent(divertor, maxLength)
        vacuums = consistent(vacuum, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityAdvancedFusionReactor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("FusionCasingTieredStats")
        val type2: Any? = context.get<Any>("FusionCoilTieredStats")
        val type3: Any? = context.get<Any>("CryostatTieredStats")
        val type4: Any? = context.get<Any>("DivertorTieredStats")
        val type5: Any? = context.get<Any>("VacuumTieredStats")
        fusionCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        fusionCoilTier = getOrDefault(
            { type2 is WrappedIntTier },
            { (type2 as WrappedIntTier).getIntTier() }, 0)
        cryostatTier = getOrDefault(
            { type3 is WrappedIntTier },
            { (type3 as WrappedIntTier).getIntTier() }, 0)
        divertorTier = getOrDefault(
            { type4 is WrappedIntTier },
            { (type4 as WrappedIntTier).getIntTier() }, 0)
        vacuumTier = getOrDefault(
            { type5 is WrappedIntTier },
            { (type5 as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        fusionCasingTier = 0
        fusionCoilTier = 0
        cryostatTier = 0
        divertorTier = 0
        vacuumTier = 0
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy: MutableList<IEnergyContainer> = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        energyContainer = EnergyContainerList(inputEnergy)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("               ", "               ", "     cECEc     ", "     cECEc     ", "               ", "               ")
        .aisle("               ", "       C       ", "   icvvvvvci   ", "   icvvvvvci   ", "       C       ", "               ")
        .aisle("       C       ", "  C  ddddd  C  ", "  Cvv#####vvC  ", "  Cvv#####vvC  ", "  C  bbbbb  C  ", "       C       ")
        .aisle("   C   C   C   ", "   ddddddddd   ", " Iv#########vI ", " Iv#########vI ", "   bbbbbbbbb   ", "   C   C   C   ")
        .aisle("    C     C    ", "   ddd#C#ddd   ", " cv###vvv###vc ", " cv###vvv###vc ", "   bbb#C#bbb   ", "    C     C    ")
        .aisle("               ", "  dddC###Cddd  ", "cv###v#C#v###vc", "cv###v#C#v###vc", "  bbbC###Cbbb  ", "               ")
        .aisle("               ", "  dd##CCC##dd  ", "Ev##v#CXC#v##vE", "Ev##v#CXC#v##vE", "  bb##CCC##bb  ", "               ")
        .aisle("  CC       CC  ", " CddC#CCC#CddC ", "Cv##vCXXXCv##vC", "Cv##vCXXXCv##vC", " CbbC#CCC#CbbC ", "  CC       CC  ")
        .aisle("               ", "  dd##CCC##dd  ", "Ev##v#CXC#v##vE", "Ev##v#CXC#v##vE", "  bb##CCC##bb  ", "               ")
        .aisle("               ", "  dddC###Cddd  ", "cv###v#C#v###vc", "cv###v#C#v###vc", "  bbbC###Cbbb  ", "               ")
        .aisle("    C     C    ", "   ddd#C#ddd   ", " cv###vvv###vc ", " cv###vvv###vc ", "   bbb#C#bbb   ", "    C     C    ")
        .aisle("   C   C   C   ", "   ddddddddd   ", " Iv#########vI ", " Iv#########vI ", "   bbbbbbbbb   ", "   C   C   C   ")
        .aisle("       C       ", "  C  ddddd  C  ", "  Cvv#####vvC  ", "  Cvv#####vvC  ", "  C  bbbbb  C  ", "       C       ")
        .aisle("               ", "       C       ", "   icvvvvvci   ", "   icvvvvvci   ", "       S       ", "               ")
        .aisle("               ", "               ", "     cECEc     ", "     cECEc     ", "               ", "               ")
        .where('S', selfPredicate())
        .where('c', fusionCasings())
        .where('E', fusionCasings()
            .or(abilities(INPUT_ENERGY)
                .setMaxGlobalLimited(16))
            .or(abilities(INPUT_LASER)
                .setMaxGlobalLimited(16)))
        .where('I', fusionCasings()
            .or(abilities(IMPORT_FLUIDS)
                .setMinGlobalLimited(1)
                .setPreviewCount(8)))
        .where('i', fusionCasings()
            .or(abilities(EXPORT_FLUIDS)
                .setMinGlobalLimited(2)
                .setPreviewCount(8)))
        .where('b', fusionCasings())
        .where('X', fusionCoils())
        .where('C', cryostats())
        .where('d', divertors())
        .where('v', vacuums())
        .where('#', air())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = if (recipeMapWorkable.isActive) Textures.ACTIVE_FUSION_TEXTURE else Textures.FUSION_TEXTURE

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.ADVANCED_FUSION_REACTOR_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo: MutableList<MultiblockShapeInfo> = ArrayList()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("               ", "               ", "     c2C2c     ", "     c2C2c     ", "               ", "               ")
            .aisle("               ", "       C       ", "   8cvvvvvc8   ", "   8cvvvvvc8   ", "       C       ", "               ")
            .aisle("       C       ", "  C  ddddd  C  ", "  Cvv     vvC  ", "  Cvv     vvC  ", "  C  bbbbb  C  ", "       C       ")
            .aisle("   C   C   C   ", "   ddddddddd   ", " 6v         v6 ", " 6v         v6 ", "   bbbbbbbbb   ", "   C   C   C   ")
            .aisle("    C     C    ", "   ddd C ddd   ", " cv   vvv   vc ", " cv   vvv   vc ", "   bbb C bbb   ", "    C     C    ")
            .aisle("               ", "  dddC   Cddd  ", "cv   v C v   vc", "cv   v C v   vc", "  bbbC   Cbbb  ", "               ")
            .aisle("               ", "  dd  CCC  dd  ", "3v  v CXC v  v4", "3v  v CXC v  v4", "  bb  CCC  bb  ", "               ")
            .aisle("  CC       CC  ", " CddC CCC CddC ", "Cv  vCXXXCv  vC", "Cv  vCXXXCv  vC", " CbbC CCC CbbC ", "  CC       CC  ")
            .aisle("               ", "  dd  CCC  dd  ", "3v  v CXC v  v4", "3v  v CXC v  v4", "  bb  CCC  bb  ", "               ")
            .aisle("               ", "  dddC   Cddd  ", "cv   v C v   vc", "cv   v C v   vc", "  bbbC   Cbbb  ", "               ")
            .aisle("    C     C    ", "   ddd C ddd   ", " cv   vvv   vc ", " cv   vvv   vc ", "   bbb C bbb   ", "    C     C    ")
            .aisle("   C   C   C   ", "   ddddddddd   ", " 5v         v5 ", " 5v         v5 ", "   bbbbbbbbb   ", "   C   C   C   ")
            .aisle("       C       ", "  C  ddddd  C  ", "  Cvv     vvC  ", "  Cvv     vvC  ", "  C  bbbbb  C  ", "       C       ")
            .aisle("               ", "       C       ", "   7cvvvvvc7   ", "   7cvvvvvc7   ", "       S       ", "               ")
            .aisle("               ", "               ", "     c1C1c     ", "     c1C1c     ", "               ", "               ")
            .where('S', GTLiteMetaTileEntities.ADVANCED_FUSION_REACTOR, EnumFacing.SOUTH)
            .where('1', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.SOUTH)
            .where('2', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.NORTH)
            .where('3', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.WEST)
            .where('4', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.EAST)
            .where('5', MetaTileEntities.FLUID_IMPORT_HATCH[ULV], EnumFacing.SOUTH)
            .where('6', MetaTileEntities.FLUID_IMPORT_HATCH[ULV], EnumFacing.NORTH)
            .where('7', MetaTileEntities.FLUID_EXPORT_HATCH[ULV], EnumFacing.SOUTH)
            .where('8', MetaTileEntities.FLUID_EXPORT_HATCH[ULV], EnumFacing.NORTH)
        val count = AtomicInteger()
        StreamEx.of(fusionCasings)
            .map { b ->
                if (builder != null)
                {
                    builder.where('c', b)
                    builder.where('b', b)
                    builder.where('X', fusionCoils[count.get()])
                    builder.where('C', cryostats[count.get()])
                    builder.where('d', divertors[count.get()])
                    builder.where('v', vacuums[count.get()])
                    count.getAndIncrement()
                }
                builder
            }
            .nonNull()
            .forEach(Consumer { b -> shapeInfo.add(b.build()) })
        return shapeInfo
    }

    override fun update() {
        super.update()
        if ((world as World).isRemote)
        {
            if (fusionCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (fusionCoilTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (cryostatTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (divertorTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_LAST_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (vacuumTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_EXTRA_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(fusionCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(fusionCoilTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(cryostatTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_LAST_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_LAST_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(divertorTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_EXTRA_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_EXTRA_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(vacuumTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            fusionCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            fusionCoilTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE)
            cryostatTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_LAST_TIERED_MACHINE)
            divertorTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_EXTRA_TIERED_MACHINE)
            vacuumTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.7"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.8"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.9"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.10"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.11"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.12"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.13"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.14"))
    }

    override fun canBeDistinct() = false

    override fun hasMaintenanceMechanics() = false

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        return super.checkRecipe(recipe, consumeIfSuccess)
                && fusionCasingTier >= recipe.getProperty(AdvancedFusionTieredProperty.getInstance(), 0)!!
                && fusionCoilTier >= recipe.getProperty(AdvancedFusionTieredProperty.getInstance(), 0)!!
                && cryostatTier >= recipe.getProperty(AdvancedFusionTieredProperty.getInstance(), 0)!!
                && divertorTier >= recipe.getProperty(AdvancedFusionTieredProperty.getInstance(), 0)!!
                && vacuumTier >= recipe.getProperty(AdvancedFusionTieredProperty.getInstance(), 0)!!
    }

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    inner class AdvancedFusionRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor() = 0.125

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.5.pow(min(fusionCasingTier, min(vacuumTier, cryostatTier)))).toInt())
        }

        override fun getParallelLimit() =  64 * min(fusionCoilTier, divertorTier)

    }

}
