package gregtechlite.gtlitecore.common.metatileentity.multiblock.generator

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.V
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.FuelMultiblockController
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.energyOutputPredicate
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ACID_GENERATOR_FUELS
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockAcidGenerator(id: ResourceLocation)
    : FuelMultiblockController(id, ACID_GENERATOR_FUELS, IV)
{

    companion object
    {
        private val casingState
            get() = MetalCasing.COBALT_BRASS.state

        private val glassState
            get() = GTGlassCasing.CLEANROOM_GLASS.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockAcidGenerator(metaTileEntityId)

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "CCECC", "CCCCC")
        .aisle(" CCC ", " C#C ", " CCC ")
        .aisle(" GGG ", " G#G ", " COC ")
        .aisle(" CCC ", " C#C ", " CCC ")
        .aisle("CCCCC", "CCSCC", "CCCCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(20)
            .or(autoAbilities(false, true, false, false, true, false, false)))
        .where('G', states(glassState))
        .where('E', energyOutputPredicate(IV))
        .where('O', abilities(MUFFLER_HATCH))
        .where('#', air())
        .where(' ', any())
        .build()

    override fun addInformation(stack: ItemStack?,
                                player: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.tooltip.maximum_energy_output", V[IV]))
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.COBALT_BRASS_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.CHEMICAL_REACTOR_OVERLAY

    override fun hasMufflerMechanics(): Boolean = true

}