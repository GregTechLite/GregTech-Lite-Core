package gregtechlite.gtlitecore.common.metatileentity.multiblock.generator

import gregtech.api.GTValues.MV
import gregtech.api.GTValues.V
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.FuelMultiblockController
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.recipes.RecipeMaps.STEAM_TURBINE_FUELS
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.energyOutputPredicate
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTTurbineCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockSteamEngine(id: ResourceLocation)
    : FuelMultiblockController(id, STEAM_TURBINE_FUELS, MV)
{

    companion object
    {
        private val casingState = MetalCasing.BRASS.state
        private val gearboxCasingState = GTTurbineCasing.BRONZE_GEARBOX.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockSteamEngine(metaTileEntityId)

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CC", "CEC", " CC")
        .aisle("CCC", "CGC", "CMC")
        .aisle(" CC", "CGC", " CC")
        .aisle(" CC", " SC", " CC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(18)
            .or(autoAbilities(false, true, true, true, true, true, false)))
        .where('G', states(gearboxCasingState))
        .where('E', energyOutputPredicate(MV))
        .where('M', abilities(MUFFLER_HATCH))
        .where(' ', any())
        .build()

    // @formatter:on

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.tooltip.maximum_energy_output", V[MV]))
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.BRASS_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.LARGE_STEAM_TURBINE_OVERLAY

    override fun hasMufflerMechanics() = true

}