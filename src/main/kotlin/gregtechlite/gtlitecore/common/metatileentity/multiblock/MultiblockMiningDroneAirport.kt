package gregtechlite.gtlitecore.common.metatileentity.multiblock

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
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MINING_DRONE_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTCleanroomCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.adapter.GTTurbineCasing
import gregtechlite.gtlitecore.common.block.adapter.GTWarningSignBlock
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockMiningDroneAirport(id: ResourceLocation)
    : RecipeMapMultiblockController(id, MINING_DRONE_RECIPES)
{

    companion object
    {
        private val casingState = GTWarningSignBlock.YELLOW_STRIPES.state
        private val anotherCasingState = GTWarningSignBlock.SMALL_YELLOW_STRIPES.state
        private val secondCasingState = GTCleanroomCasing.PLASCRETE.state
        private val thirdCasingState = GTMetalCasing.STEEL_SOLID.state
        private val gearboxCasingState = GTTurbineCasing.STEEL_GEARBOX.state
        private val pipeCasingState = GTBoilerCasing.STEEL_PIPE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MultiblockMiningDroneAirport(metaTileEntityId)

    // @formatter:off

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

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.SOLID_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.MINING_DRONE_AIRPORT_OVERLAY

    // @formatter:off

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

    // @formatter:on

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
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