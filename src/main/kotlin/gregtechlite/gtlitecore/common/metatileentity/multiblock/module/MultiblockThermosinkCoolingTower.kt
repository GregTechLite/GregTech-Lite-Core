package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalMultiblockBase
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.SNOW_LAYER
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockPCBFactory
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World

class MultiblockThermosinkCoolingTower<T : MultiblockPCBFactory<T>>(id: ResourceLocation) : AdditionalMultiblockBase<T>(id)
{
    companion object
    {
        private val casingState = MultiblockCasing.INFINITY_COOLING_CASING.state
        private val secondCasingState = MetalCasing.OSMIRIDIUM.state

        private val pipeCasingState = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state
        private val turbineCasingState = GTMultiblockCasing.EXTREME_ENGINE_INTAKE_CASING.state

        private val coilState = GTFusionCasing.SUPERCONDUCTOR_COIL.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockThermosinkCoolingTower(metaTileEntityId)

    override fun updateFormedValid()
    {
        mainController?.addAdditional(this)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("nTTTn", "nQQQn", "n   n", "n   n", "nRRRn", "n   n", "n   n", "n   n", "n   n", "nTTTn")
        .aisle("TTTTT", "QOOOQ", " OOO ", " OOO ", "ROOOR", " OOO ", " OOO ", " QQQ ", " TTT ", "T***T")
        .aisle("TTTTT", "QOUOQ", " OUO ", " OUO ", "ROUOR", " OUO ", " OUO ", " QUQ ", " TUT ", "T*#*T")
        .aisle("TTTTT", "QOOOQ", " OOO ", " OOO ", "ROOOR", " OOO ", " OOO ", " QQQ ", " TTT ", "T***T")
        .aisle("nTSTn", "nQQQn", "n   n", "n   n", "nRRRn", "n   n", "n   n", "n   n", "n   n", "nTTTn")
        .where('S', selfPredicate())
        .where('O', states(casingState))
        .where('Q', states(pipeCasingState))
        .where('R', states(turbineCasingState))
        .where('T', states(secondCasingState))
        .where('U', states(coilState))
        .where('n', frames(HSLASteel))
        .where('*', air().or(SNOW_LAYER))
        .where('#', air())
        .where(' ', any())
        .build()

    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.INFINITY_COOLING_CASING

    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.machine_type",
                                I18n.format("gtlitecore.machine.pcb_factory.additional_structure_name")))
        tooltip.add(I18n.format("gtlitecore.machine.thermosink_cooling_tower.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.thermosink_cooling_tower.tooltip.2"))
    }
}