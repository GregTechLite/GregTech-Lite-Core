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
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockPCBFactory
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World

class MultiblockWaterCoolingTower<T : MultiblockPCBFactory<T>>(id: ResourceLocation) : AdditionalMultiblockBase<T>(id)
{
    companion object
    {
        private val casingState = MetalCasing.OSMIRIDIUM.state
        private val secondCasingState = MetalCasing.NAQUADAH_ALLOY.state

        private val pipeCasingState = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state
        private val turbineCasingState = GTMultiblockCasing.EXTREME_ENGINE_INTAKE_CASING.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockWaterCoolingTower(metaTileEntityId)

    override fun updateFormedValid()
    {
        mainController?.addAdditional(this)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("gHHHg", "gPPPg", "g   g", "g   g", "gJJJg", "g   g", "g   g", "g   g", "g   g", "gIIIg")
        .aisle("HHHHH", "PIIIP", " III ", " III ", "JIIIJ", " III ", " III ", " PPP ", " III ", "I***I")
        .aisle("HHHHH", "PI*IP", " I#I ", " I#I ", "JI#IJ", " I#I ", " I#I ", " P#P ", " I#I ", "I*#*I")
        .aisle("HHHHH", "PIIIP", " III ", " III ", "JIIIJ", " III ", " III ", " PPP ", " III ", "I***I")
        .aisle("gHSHg", "gPPPg", "g   g", "g   g", "gJJJg", "g   g", "g   g", "g   g", "g   g", "gIIIg")
        .where('S', selfPredicate())
        .where('H', states(casingState))
        .where('I', states(secondCasingState))
        .where('J', states(turbineCasingState))
        .where('P', states(pipeCasingState))
        .where('g', frames(HSLASteel))
        .where('*', air().or(SNOW_LAYER))
        .where('#', air())
        .where(' ', air())
        .build()

    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.OSMIRIDIUM_CASING

    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.machine_type",
                                I18n.format("gtlitecore.machine.pcb_factory.additional_structure_name")))
        tooltip.add(I18n.format("gtlitecore.machine.water_cooling_tower.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.water_cooling_tower.tooltip.2"))
    }
}