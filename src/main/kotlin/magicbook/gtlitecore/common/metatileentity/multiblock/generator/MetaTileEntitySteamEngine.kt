package magicbook.gtlitecore.common.metatileentity.multiblock.generator

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
import gregtech.common.blocks.BlockTurbineCasing
import gregtech.common.blocks.MetaBlocks
import magicbook.gtlitecore.api.pattern.GTLiteTraceabilityPredicate.Companion.energyOutputPredicate
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MetaTileEntitySteamEngine(metaTileEntityId: ResourceLocation) : FuelMultiblockController(metaTileEntityId, STEAM_TURBINE_FUELS, MV)
{

    companion object
    {
        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.BRASS)

        private val gearboxCasingState
            get() = MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.BRONZE_GEARBOX)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntitySteamEngine(metaTileEntityId)

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

    override fun addInformation(stack: ItemStack?,
                                player: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.tooltip.maximum_energy_output", V[MV]))
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.BRASS_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.LARGE_STEAM_TURBINE_OVERLAY

    override fun hasMufflerMechanics() = true

}