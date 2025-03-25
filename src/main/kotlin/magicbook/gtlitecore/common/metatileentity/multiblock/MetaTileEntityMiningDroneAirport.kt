package magicbook.gtlitecore.common.metatileentity.multiblock

import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.unification.material.Materials
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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityChemicalPlant
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.function.Supplier

@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityMiningDroneAirport(metaTileEntityId: ResourceLocation) : RecipeMapMultiblockController(metaTileEntityId, GTLiteRecipeMaps.MINING_DRONE_RECIPES)
{

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
        .where('C', states(getCasingState())
            .or(states(getAnotherCasingState())))
        .where('X', states(getSecondCasingState()))
        .where('A', states(getThirdCasingState())
            .setMinGlobalLimited(25)
            .or(autoAbilities(true, true, true, true, true, false, false)))
        .where('G', states(getGearboxCasingState()))
        .where('P', states(getPipeCasingState()))
        .where('F', frames(Materials.Steel))
        .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
        .where('#', air())
        .where(' ', any())
        .build()

    private fun getCasingState() = MetaBlocks.WARNING_SIGN.getState(BlockWarningSign.SignType.YELLOW_STRIPES)
    private fun getAnotherCasingState() = MetaBlocks.WARNING_SIGN.getState(BlockWarningSign.SignType.SMALL_YELLOW_STRIPES)
    private fun getSecondCasingState() = MetaBlocks.CLEANROOM_CASING.getState(BlockCleanroomCasing.CasingType.PLASCRETE)
    private fun getThirdCasingState() = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)
    private fun getGearboxCasingState() = MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)
    private fun getPipeCasingState() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.SOLID_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.MINING_DRONE_AIRPORT_OVERLAY

    override fun getMatchingShapes(): MutableList<MultiblockShapeInfo>
    {
        val shapeInfos = arrayListOf<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("    F     F", "    F     F", "     FCCCF ", "     F   F ", "           ", "           ")
            .aisle("F          ", "F          ", "FFFFFCXXXCF", " AEAF     F", " AAA       ", "           ")
            .aisle("           ", "           ", "FAAACXXXXXC", "P   P      ", "P   P      ", " AAA       ")
            .aisle("           ", "           ", "FAAACXXXXXC", "A G A      ", "A G A      ", " AMA       ")
            .aisle("           ", "           ", "FAAACXXXXXC", "P   P      ", "P   P      ", " AAA       ")
            .aisle("F          ", "F          ", "FFFFFCXXXCF", " IWIF     F", " JSK       ", "           ")
            .aisle("    F     F", "    F     F", "     FCCCF ", "     F   F ", "           ", "           ")
            .where('S', GTLiteMetaTileEntities.MINING_DRONE_AIRPORT, EnumFacing.SOUTH)
            .where('C', getAnotherCasingState())
            .where('X', getSecondCasingState())
            .where('A', getThirdCasingState())
            .where('G', getGearboxCasingState())
            .where('P', getPipeCasingState())
            .where('F', MetaBlocks.FRAMES[Materials.Steel]!!.getBlock(Materials.Steel))
            .where('M', MetaTileEntities.MUFFLER_HATCH[1], EnumFacing.UP)
            .where('W', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else getThirdCasingState() }, EnumFacing.SOUTH)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
        if (builder != null)
        {
            shapeInfos.add(builder.shallowCopy().where('C', getCasingState()).build())
            shapeInfos.add(builder.build())
        }
        return shapeInfos
    }

    override fun hasMufflerMechanics(): Boolean = true

    override fun canBeDistinct(): Boolean = true

}