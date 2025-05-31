package magicbook.gtlitecore.common.metatileentity.multiblock

import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockCleanroomCasing
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.BlockTurbineCasing
import gregtech.common.blocks.BlockWarningSign
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MINING_DRONE_RECIPES
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MetaTileEntityMiningDroneAirport(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, MINING_DRONE_RECIPES)
{

    companion object
    {
        private val casingState
            get() = MetaBlocks.WARNING_SIGN.getState(BlockWarningSign.SignType.YELLOW_STRIPES)

        private val anotherCasingState
            get() = MetaBlocks.WARNING_SIGN.getState(BlockWarningSign.SignType.SMALL_YELLOW_STRIPES)

        private val secondCasingState
            get() = MetaBlocks.CLEANROOM_CASING.getState(BlockCleanroomCasing.CasingType.PLASCRETE)

        private val thirdCasingState
            get() = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)

        private val gearboxCasingState
            get() = MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)

        private val pipeCasingState
            get() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MetaTileEntityMiningDroneAirport(metaTileEntityId)

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("    F     F", "    F     F", "     FCCCF ", "     F   F ", "           ", "           ")
        .aisle("F          ", "F          ", "FFFFFCXXXCF", " AAAF     F", " AAA       ", "           ")
        .aisle("           ", "           ", "FAAACXXXXXC", "P###P      ", "P###P      ", " AAA       ")
        .aisle("           ", "           ", "FAAACXXXXXC", "A#G#A      ", "A#G#A      ", " AMA       ")
        .aisle("           ", "           ", "FAAACXXXXXC", "P###P      ", "P###P      ", " AAA       ")
        .aisle("F          ", "F          ", "FFFFFCXXXCF", " AAAF     F", " ASA       ", "           ")
        .aisle("    F     F", "    F     F", "     FCCCF ", "     F   F ", "           ", "           ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .or(states(anotherCasingState)))
        .where('X', states(secondCasingState))
        .where('A', states(thirdCasingState)
            .setMinGlobalLimited(25)
            .or(autoAbilities(true, true, true, true, true, false, false)))
        .where('G', states(gearboxCasingState))
        .where('P', states(pipeCasingState))
        .where('F', frames(Steel))
        .where('M', abilities(MUFFLER_HATCH))
        .where('#', air())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.SOLID_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.MINING_DRONE_AIRPORT_OVERLAY

    override fun getMatchingShapes(): MutableList<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("    F     F", "    F     F", "     FCCCF ", "     F   F ", "           ", "           ")
            .aisle("F          ", "F          ", "FFFFFCXXXCF", " AEAF     F", " AAA       ", "           ")
            .aisle("           ", "           ", "FAAACXXXXXC", "P   P      ", "P   P      ", " AAA       ")
            .aisle("           ", "           ", "FAAACXXXXXC", "A G A      ", "A G A      ", " AMA       ")
            .aisle("           ", "           ", "FAAACXXXXXC", "P   P      ", "P   P      ", " AAA       ")
            .aisle("F          ", "F          ", "FFFFFCXXXCF", " IWIF     F", " JSK       ", "           ")
            .aisle("    F     F", "    F     F", "     FCCCF ", "     F   F ", "           ", "           ")
            .where('S', GTLiteMetaTileEntities.MINING_DRONE_AIRPORT, EnumFacing.SOUTH)
            .where('C', anotherCasingState)
            .where('X', secondCasingState)
            .where('A', thirdCasingState)
            .where('G', gearboxCasingState)
            .where('P', pipeCasingState)
            .where('F', MetaBlocks.FRAMES[Steel]!!.getBlock(Steel))
            .where('M', MetaTileEntities.MUFFLER_HATCH[1], EnumFacing.UP)
            .where('W', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else thirdCasingState }, EnumFacing.SOUTH)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
        shapeInfo.add(builder.shallowCopy().where('C', casingState).build())
        shapeInfo.add(builder.build())
        return shapeInfo
    }

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.mining_drone_airport.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.mining_drone_airport.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.mining_drone_airport.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.mining_drone_airport.tooltip.4"))
    }

    override fun hasMufflerMechanics() = true

    override fun canBeDistinct() = true

}