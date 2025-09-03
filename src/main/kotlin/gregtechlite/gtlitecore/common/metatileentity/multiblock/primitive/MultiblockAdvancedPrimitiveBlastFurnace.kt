package gregtechlite.gtlitecore.common.metatileentity.multiblock.primitive

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.GTValues.ULV
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.mui.GTGuiTheme
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.PRIMITIVE_BLAST_FURNACE_RECIPES
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.util.RelativeDirection
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities.ITEM_EXPORT_BUS
import gregtech.common.metatileentities.MetaTileEntities.ITEM_IMPORT_BUS
import gregtechlite.gtlitecore.api.capability.logic.NoEnergyMultiblockRecipeLogic
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.SNOW_LAYER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.optionalStates
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTFireboxCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockAdvancedPrimitiveBlastFurnace(id: ResourceLocation)
    : RecipeMapMultiblockController(id, PRIMITIVE_BLAST_FURNACE_RECIPES)
{

    private var auxiliaryFurnaceNumber = 0

    init
    {
        recipeMapWorkable = IndustrialPBFRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = GTMetalCasing.PRIMITIVE_BRICKS.state

        private val fireboxCasingState
            get() = GTFireboxCasing.STEEL_FIREBOX.state

        private val pipeCasingState
            get() = GTBoilerCasing.STEEL_PIPE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockAdvancedPrimitiveBlastFurnace(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        if (context.get<String>("AuxiliaryFurnace1") != null)
            auxiliaryFurnaceNumber += 1
        if (context.get<String>("AuxiliaryFurnace2") != null)
            auxiliaryFurnaceNumber += 1
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("     DDD     ", "             ", "             ", "             ", "             ", "             ", "             ", "             ", "             ")
        .aisle("    CDDDC    ", "    CDDDC    ", "    CDDDC    ", "     DDD     ", "             ", "             ", "             ", "             ", "             ")
        .aisle("AAAGDDDDDJFFF", "GGG D###D JJJ", " G  D###D  J ", " G  D###D  J ", " G   DDD   J ", " G    D    J ", "      D      ", "      D      ", "      D      ")
        .aisle("AAAGDDDDDJFFF", "GoGHD#o#DIJoJ", "G#G D###D J#J", "G#G D###D J#J", "G#G D###D J#J", "G#G  D#D  J#J", "     D#D     ", "     D#D     ", "     D#D     ")
        .aisle("AAAGDDDDDJFFF", "GGG D###D JJJ", " G  D###D  J ", " G  D###D  J ", " G   DDD   J ", " G    D    J ", "      D      ", "      D      ", "      D      ")
        .aisle("    CDDDC    ", "    CDSDC    ", "    CDDDC    ", "     DDD     ", "             ", "             ", "             ", "             ", "             ")
        .aisle("     DDD     ", "             ", "             ", "             ", "             ", "             ", "             ", "             ", "             ")
        .where('S', selfPredicate())
        .where('C', frames(Steel))
        .where('D', states(casingState)
            .setMinGlobalLimited(69)
            .or(autoAbilities(false, false, true, true, false, false, false)))
        .where('A', optionalStates("AuxiliaryFurnace1", fireboxCasingState))
        .where('F', optionalStates("AuxiliaryFurnace2", fireboxCasingState))
        .where('G', optionalStates("AuxiliaryFurnace1", casingState))
        .where('J', optionalStates("AuxiliaryFurnace2", casingState))
        .where('H', optionalStates("AuxiliaryFurnace1", pipeCasingState))
        .where('I', optionalStates("AuxiliaryFurnace2", pipeCasingState))
        .where('#', air())
        .where('o', air().or(SNOW_LAYER))
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.PRIMITIVE_BRICKS

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY

    override fun getUITheme(): GTGuiTheme = GTGuiTheme.PRIMITIVE

    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<out IVertexOperation>?, )
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        frontOverlay.renderOrientedState(renderState, translation, pipeline, frontFacing,
            recipeMapWorkable.isActive, recipeMapWorkable.isWorkingEnabled)
    }

    override fun getMatchingShapes(): MutableList<MultiblockShapeInfo>
    {
        val shapeInfo: MutableList<MultiblockShapeInfo> = arrayListOf()
        val builder =  MultiblockShapeInfo.builder(RelativeDirection.RIGHT, RelativeDirection.DOWN, RelativeDirection.FRONT)
            .aisle("     DDD     ", "             ", "             ", "             ", "             ", "             ", "             ", "             ", "             ")
            .aisle("    CDDDC    ", "    CDDDC    ", "    CDDDC    ", "     DDD     ", "             ", "             ", "             ", "             ", "             ")
            .aisle("AAAGDDDDDJFFF", "GGG D   D JJJ", " G  D   D  J ", " G  D   D  J ", " G   DDD   J ", " G    D    J ", "      D      ", "      D      ", "      D      ")
            .aisle("AAAGDDDDDJFFF", "G GHD   DIJ J", "G G D   D J J", "G*G D   D J!J", "G G D   D J J", "G G  D D  J J", "     D D     ", "     D D     ", "     D D     ")
            .aisle("AAAGDDDDDJFFF", "GGG D   D JJJ", " G  D   D  J ", " G  D   D  J ", " G   DDD   J ", " G    D    J ", "      D      ", "      D      ", "      D      ")
            .aisle("    CDDDC    ", "    CXSYC    ", "    CDDDC    ", "     DDD     ", "             ", "             ", "             ", "             ", "             ")
            .aisle("     DDD     ", "             ", "             ", "             ", "             ", "             ", "             ", "             ", "             ")
            .where('S', GTLiteMetaTileEntities.INDUSTRIAL_PRIMITIVE_BLAST_FURNACE, EnumFacing.SOUTH)
            .where('C', MetaBlocks.FRAMES[Steel]!!.getBlock(Steel))
            .where('D', casingState)
            .where('X', ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('Y', ITEM_EXPORT_BUS[ULV], EnumFacing.SOUTH)
        shapeInfo.add(builder.build())
        shapeInfo.add(builder.where('A', fireboxCasingState)
                          .where('G', casingState)
                          .where('H', pipeCasingState)
                          .build())
        shapeInfo.add(builder.where('F', fireboxCasingState)
                          .where('I', pipeCasingState)
                          .where('J', casingState)
                          .build())
        return shapeInfo
    }

    override fun hasMaintenanceMechanics() = false

    override fun addInformation(stack: ItemStack?,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.industrial_primitive_blast_furnace.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.industrial_primitive_blast_furnace.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.industrial_primitive_blast_furnace.tooltip.3"))
    }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progress, recipeMapWorkable.maxProgress)
            .addRecipeOutputLine(recipeMapWorkable)
    }

    private inner class IndustrialPBFRecipeLogic(metaTileEntity: RecipeMapMultiblockController) : NoEnergyMultiblockRecipeLogic(metaTileEntity)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            if (isStructureFormed)
            {
                maxProgressTime = when (auxiliaryFurnaceNumber)
                {
                    1 -> maxProgress / 8
                    2 -> maxProgress / 16
                    else -> maxProgress
                }
            }
        }

    }

}