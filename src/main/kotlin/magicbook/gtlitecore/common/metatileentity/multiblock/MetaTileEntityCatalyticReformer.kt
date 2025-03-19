package magicbook.gtlitecore.common.metatileentity.multiblock

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.unification.material.Materials
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.MetaBlocks
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityCatalyticReformer(metaTileEntityId: ResourceLocation) : RecipeMapMultiblockController(metaTileEntityId, GTLiteRecipeMaps.CATALYTIC_REFORMER_RECIPES)
{

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MetaTileEntityCatalyticReformer(metaTileEntityId)

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("F   F", "CCCPC", "CCCPC", "CCCPC")
        .aisle("     ", "CCCPC", "C###O", "CCCPC")
        .aisle("F   F", "CCCPC", "CSCPC", "CCCPC")
        .where('S', selfPredicate())
        .where('C', states(getCasingState())
            .setMinGlobalLimited(24)
            .or(autoAbilities(true, true, true, false, true, true, false)))
        .where('P', states(getPipeCasingState()))
        .where('F', frames(Materials.StainlessSteel))
        .where('O', abilities(MultiblockAbility.MUFFLER_HATCH))
        .where('#', air())
        .where(' ', any())
        .build()

    private fun getCasingState() = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN)

    private fun getPipeCasingState() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.CLEAN_STAINLESS_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun hasMufflerMechanics(): Boolean = true

}