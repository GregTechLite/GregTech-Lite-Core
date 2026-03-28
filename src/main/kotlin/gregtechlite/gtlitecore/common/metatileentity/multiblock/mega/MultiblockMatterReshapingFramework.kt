package gregtechlite.gtlitecore.common.metatileentity.multiblock.mega

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.RECOMBINATION_SUBMODULE_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SOLIDIFICATION_SUBMODULE_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.util.ResourceLocation

class MultiblockMatterReshapingFramework(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(SOLIDIFICATION_SUBMODULE_RECIPES, RECOMBINATION_SUBMODULE_RECIPES))
{

    companion object
    {
        private val casingState = MetalCasing.NEUTRONIUM.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockMatterReshapingFramework(metaTileEntityId)

    // TODO: Actual multiblock structure
    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC")
        .aisle("CCC", "C C", "CCC")
        .aisle("CCC", "CSC", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .or(autoAbilities()))
        .where(' ', any())
        .build()

    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.NEUTRONIUM_CASING

}