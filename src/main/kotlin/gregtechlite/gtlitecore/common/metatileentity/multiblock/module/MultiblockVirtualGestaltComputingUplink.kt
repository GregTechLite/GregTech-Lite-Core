package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.unification.material.Materials.Copernicium
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalMultiblockBase
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCoil
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockNanoForge
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockVirtualGestaltComputingUplink<T : MultiblockNanoForge<T>>(id: ResourceLocation) : AdditionalMultiblockBase<T>(id)
{
    companion object
    {
        private val casingState = MultiblockCasing.LATTICE_QCD_THERMAL_SHIELDING_CASING.state
        private val secondCasingState = MetalCasing.NEUTRONIUM.state
        private val thirdCasingState = MultiblockCasing.GRAVITY_STABILIZATION_CASING.state
        private val fourthCasingState = ScienceCasing.DIMENSIONAL_BRIDGE_CASING.state
        private val uniqueCasingState = ScienceCasing.CONTAINMENT_FIELD_GENERATOR.state
        private val secondUniqueCasingState = ScienceCasing.SPACETIME_ALTERING_CASING.state
        private val thirdUniqueCasingState = MultiblockCasing.STELLAR_CONTAINMENT_CASING.state
        private val pipeCasingState = ScienceCasing.HOLLOW_CASING.state
        private val glassState = GlassCasing.NANO_SHIELDING_FRAME.state
        private val coilState = FusionCoil.ULTIMATE.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockVirtualGestaltComputingUplink(metaTileEntityId)

    override fun updateFormedValid()
    {
        mainController?.addAdditional(this)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("   XXEDDDEXX   ", "   IIFDDDFII   ", "     FRRRF     ", "     FRRRF     ", "     FGGGF     ", "     FRRRF     ", "     FRRRF     ", "     FRRRF     ", "     FRRRF     ", "     FRRRF     ", "     FGGGF     ", "     FRRRF     ", "     FRRRF     ", "   IIFDDDFII   ", "   XXEDDDEXX   ")
        .aisle("  XXXXXXXXXXX  ", "  I HE X EH I  ", "    H  Y  H    ", "    H  Z  H    ", "    H  Y  H    ", "    H  Z  H    ", "    H  Y  H    ", "    H  Z  H    ", "    H  Y  H    ", "    H  Z  H    ", "    H  Y  H    ", "    H  Z  H    ", "    H  Y  H    ", "  I HE X EH I  ", "  XXXXXXXXXXX  ")
        .aisle(" XXXXXXXXXXXXX ", " I R   X   R I ", "   R   Y   R   ", "   R   X   R   ", "   R   X   R   ", "   R  WXW  R   ", "   R XXXXX R   ", "   R WXXXW R   ", "   R XXXXX R   ", "   R  WXW  R   ", "   R   X   R   ", "   R   X   R   ", "   R   Y   R   ", " I R   X   R I ", " XXXXXXXXXXXXX ")
        .aisle("XXXXXXXVXXXXXXX", "I R    X    R I", "  R    X    R  ", "  R    X    R  ", "  R  XXXXX  R  ", "  R YW###WY R  ", "  R X#####X R  ", "  R Y#####Y R  ", "  R X#####X R  ", "  R YW###WY R  ", "  R  XXXXX  R  ", "  R    X    R  ", "  R    X    R  ", "I R    X    R I", "XXXXXXXVXXXXXXX")
        .aisle("XXXXXXVVVXXXXXX", "IH     X     HI", " H     X     H ", " H   XXXXX   H ", " H  X#####X  H ", " H Y#######Y H ", " H X#######X H ", " H Y#######Y H ", " H X#######X H ", " H Y#######Y H ", " H  X#####X  H ", " H   XXXXX   H ", " H     X     H ", "IH     X     HI", "XXXXXXVVVXXXXXX")
        .aisle("EXXXXVXXXVXXXXE", "FE     X     EF", "F     XXX     F", "F   XX###XX   F", "F  X#######X  F", "F  W#######W  F", "F X#########X F", "F W#########W F", "F X#########X F", "F  W#######W  F", "F  X#######X  F", "F   XX###XX   F", "F     XXX     F", "FE     X     EF", "EXXXXVXXXVXXXXE")
        .aisle("DXXXVXXXXXVXXXD", "D     YXY     D", "R    XXWXX    R", "R   X#####X   R", "G  X#######X  G", "R W#########W R", "R X#########X R", "R W#########W R", "R X#########X R", "R W#########W R", "G  X#######X  G", "R   X#####X   R", "R    XXWXX    R", "D     YXY     D", "DXXXVXXXXXVXXXD")
        .aisle("DXXVVXXoXXVVXXD", "DXXXXXXoXXXXXXD", "RYYXXXWoWXXXYYR", "RZXXX##o##XXXZR", "GYXX###o###XXYG", "RZX####o####XZR", "RYX####o####XYR", "RZX####o####XZR", "RYX####o####XYR", "RZX####o####XZR", "GYXX###o###XXYG", "RZXXX##o##XXXZR", "RYYXXXWoWXXXYYR", "DXXXXXXWXXXXXXD", "DXXVVXXoXXVVXXD")
        .aisle("DXXXVXXXXXVXXXD", "D     YXY     D", "R    XXWXX    R", "R   X#####X   R", "G  X#######X  G", "R W#########W R", "R X#########X R", "R W#########W R", "R X#########X R", "R W#########W R", "G  X#######X  G", "R   X#####X   R", "R    XXWXX    R", "D     YXY     D", "DXXXVXXXXXVXXXD")
        .aisle("EXXXXVXXXVXXXXE", "FE     X     EF", "F     XXX     F", "F   XX###XX   F", "F  X#######X  F", "F  W#######W  F", "F X#########X F", "F W#########W F", "F X#########X F", "F  W#######W  F", "F  X#######X  F", "F   XX###XX   F", "F     XXX     F", "FE     X     EF", "EXXXXVXXXVXXXXE")
        .aisle("XXXXXXVVVXXXXXX", "IH     X     HI", " H     X     H ", " H   XXXXX   H ", " H  X#####X  H ", " H Y#######Y H ", " H X#######X H ", " H Y#######Y H ", " H X#######X H ", " H Y#######Y H ", " H  X#####X  H ", " H   XXXXX   H ", " H     X     H ", "IH     X     HI", "XXXXXXVVVXXXXXX")
        .aisle("XXXXXXXVXXXXXXX", "I R    X    R I", "  R    X    R  ", "  R    X    R  ", "  R  XXXXX  R  ", "  R YW###WY R  ", "  R X#####X R  ", "  R Y#####Y R  ", "  R X#####X R  ", "  R YW###WY R  ", "  R  XXXXX  R  ", "  R    X    R  ", "  R    X    R  ", "I R    X    R I", "XXXXXXXVXXXXXXX")
        .aisle(" XXXXXXXXXXXXX ", " I R   X   R I ", "   R   Y   R   ", "   R   X   R   ", "   R   X   R   ", "   R  WXW  R   ", "   R XXXXX R   ", "   R WXXXW R   ", "   R XXXXX R   ", "   R  WXW  R   ", "   R   X   R   ", "   R   X   R   ", "   R   Y   R   ", " I R   X   R I ", " XXXXXXXXXXXXX ")
        .aisle("  XXXXXXXXXXX  ", "  I HE X EH I  ", "    H  Y  H    ", "    H  Z  H    ", "    H  Y  H    ", "    H  Z  H    ", "    H  Y  H    ", "    H  Z  H    ", "    H  Y  H    ", "    H  Z  H    ", "    H  Y  H    ", "    H  Z  H    ", "    H  Y  H    ", "  I HE X EH I  ", "  XXXXXXXXXXX  ")
        .aisle("   XXEDDDEXX   ", "   IIFDSDFII   ", "     FRRRF     ", "     FRRRF     ", "     FGGGF     ", "     FRRRF     ", "     FRRRF     ", "     FRRRF     ", "     FRRRF     ", "     FRRRF     ", "     FGGGF     ", "     FRRRF     ", "     FRRRF     ", "   IIFDDDFII   ", "   XXEDDDEXX   ")
        .where('S', selfPredicate())
        .where('X', states(casingState))
        .where('Y', states(secondCasingState))
        .where('Z', states(thirdCasingState))
        .where('V', states(fourthCasingState))
        .where('W', states(coilState))
        .where('o', states(pipeCasingState))
        .where('D', states(uniqueCasingState))
        .where('E', states(secondUniqueCasingState))
        .where('I', states(thirdUniqueCasingState))
        .where('R', states(glassState))
        .where('F', frames(TranscendentMetal))
        .where('G', frames(Mellion))
        .where('H', frames(Copernicium))
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.LATTICE_QCD_THERMAL_SHIELDING_CASING

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.machine_type",
                                I18n.format("gtlitecore.machine.nano_forge.additional_structure_name")))
        tooltip.add(I18n.format("gtlitecore.machine.virtual_gestalt_computing_uplink.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.virtual_gestalt_computing_uplink.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.virtual_gestalt_computing_uplink.tooltip.3"))
    }
}