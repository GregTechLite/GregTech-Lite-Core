package gregtechlite.gtlitecore.common.metatileentity.multiblock.mega

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.util.RelativeDirection
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.coils
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.COMPLEX_PYROLYSIS_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumCarbide
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTFireboxCasing
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.BoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockConcentratedCokingCluster(id: ResourceLocation) : RecipeMapMultiblockController(id, COMPLEX_PYROLYSIS_RECIPES)
{
    companion object
    {
        private val casingState = MetalCasing.TANTALUM_CARBIDE.state
        private val secondCasingState = GTFireboxCasing.TUNGSTENSTEEL_FIREBOX.state
        private val uniqueCasingState = ActiveUniqueCasing.HEAT_VENT.state
        private val pipeCasingState = BoilerCasing.POLYBENZIMIDAZOLE.state
    }

    init
    {

    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockConcentratedCokingCluster(metaTileEntityId)

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start(RelativeDirection.LEFT, RelativeDirection.UP, RelativeDirection.BACK)
        .aisle("AAAAAAAAAAAAAAAAAAAAAAAAA   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "                            ")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAAAAAAA", " ACDCDCDCDCDCDCDCDCDCDCDCAAA", " ACECECECECECECECECECECECAAA", " ACECECECECECECECECECECECAA ", " ACECECECECECECECECECECECA  ", "  CDCDCDCDCDCDCDCDCDCDCDC   ", "                            ")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAAAAAAA", " AF F F F F F F F F F F AA A", " AFEFEFEFEFEFEFEFEFEFEFEAAAA", " AF F F F F F F F F F F A F ", " AFEFEFEFEFEFEFEFEFEFEFEA F ", "  F F F F F F F F F F F F F ", "  FFFFFFFFFFFFFFFFFFFFFFFFF ")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAAAAAAA", " ACDCDCDCDCDCDCDCDCDCDCDCASA", " ACECECECECECECECECECECECAAA", " ACECECECECECECECECECECECAA ", " ACECECECECECECECECECECECA  ", "  CDCDCDCDCDCDCDCDCDCDCDC   ", "                            ")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAAAA   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "  B B B B B B B B B B B B   ", "                            ")
        .where('S', selfPredicate())
        .where('A', states(casingState)
            .setMinGlobalLimited(150))
        .where('C', states(secondCasingState))
        .where('B', frames(TantalumCarbide))
        .where('D', states(uniqueCasingState))
        .where('F', states(pipeCasingState))
        .where('E', coils())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PYROLYSE_OVEN_OVERLAY

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.TANTALUM_CARBIDE_CASING

    override fun hasMaintenanceMechanics(): Boolean = false
}