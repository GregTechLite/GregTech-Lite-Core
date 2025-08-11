package gregtechlite.gtlitecore.loader.ore

import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

internal object TieredGlassOreDicts
{

    // @formatter:off

    fun init()
    {
        // ULV: Glass (vanilla)
        OreDictUnifier.registerOre(ItemStack(Blocks.GLASS), GTLiteOrePrefix.glass, Tier.ULV)

        // LV: Glass (vanilla)
        OreDictUnifier.registerOre(ItemStack(Blocks.GLASS), GTLiteOrePrefix.glass, Tier.LV)

        // MV: Glass (vanilla), Cleanroom Glass
        OreDictUnifier.registerOre(ItemStack(Blocks.GLASS), GTLiteOrePrefix.glass, Tier.MV)
        OreDictUnifier.registerOre(GTGlassCasing.CLEANROOM_GLASS.stack, GTLiteOrePrefix.glass, Tier.MV)

        // HV: Tempered Glass, Borosilicate Glass, Greenhouse Glass
        OreDictUnifier.registerOre(GTGlassCasing.TEMPERED_GLASS.stack, GTLiteOrePrefix.glass, Tier.HV)
        OreDictUnifier.registerOre(GlassCasing.BOROSILICATE.stack, GTLiteOrePrefix.glass, Tier.HV)
        OreDictUnifier.registerOre(GlassCasing.GREENHOUSE.stack, GTLiteOrePrefix.glass, Tier.HV)

        // EV: Tempered Glass, Titanium reinforced Borosilicate Glass
        OreDictUnifier.registerOre(GTGlassCasing.TEMPERED_GLASS.stack, GTLiteOrePrefix.glass, Tier.EV)
        OreDictUnifier.registerOre(GlassCasing.SILICON_CARBIDE.stack, GTLiteOrePrefix.glass, Tier.EV)
        OreDictUnifier.registerOre(GlassCasing.LEAD_SILICON.stack, GTLiteOrePrefix.glass, Tier.EV)
        OreDictUnifier.registerOre(GlassCasing.TITANIUM_BOROSILICATE.stack, GTLiteOrePrefix.glass, Tier.EV)

        // IV: Laminated Glass, Tungsten Steel reinforced Borosilicate Glass
        OreDictUnifier.registerOre(GTGlassCasing.LAMINATED_GLASS.stack, GTLiteOrePrefix.glass, Tier.IV)
        OreDictUnifier.registerOre(GlassCasing.THORIUM_YTTRIUM.stack, GTLiteOrePrefix.glass, Tier.IV)
        OreDictUnifier.registerOre(GlassCasing.TUNGSTEN_STEEL_BOROSILICATE.stack, GTLiteOrePrefix.glass, Tier.IV)

        // LuV: Laminated Glass, Rhodium Plated Palladium reinforced Borosilicate Glass
        OreDictUnifier.registerOre(GTGlassCasing.LAMINATED_GLASS.stack, GTLiteOrePrefix.glass, Tier.LuV)
        OreDictUnifier.registerOre(GlassCasing.WOODS.stack, GTLiteOrePrefix.glass, Tier.LuV)
        OreDictUnifier.registerOre(GlassCasing.RHODIUM_PLATED_PALLADIUM_BOROSILICATE.stack, GTLiteOrePrefix.glass, Tier.LuV)

        // ZPM: Fusion Glass, BPA Polycarbonate Glass, Osmiridium reinforced Borosilicate Glass
        OreDictUnifier.registerOre(GTGlassCasing.FUSION_GLASS.stack, GTLiteOrePrefix.glass, Tier.ZPM)
        OreDictUnifier.registerOre(GlassCasing.BPA_POLYCARBONATE.stack, GTLiteOrePrefix.glass, Tier.ZPM)
        OreDictUnifier.registerOre(GlassCasing.OSMIRIDIUM_BOROSILICATE.stack, GTLiteOrePrefix.glass, Tier.ZPM)

        // UV: Fusion Glass, BPA Polycarbonate Glass, Tritanium reinforced Borosilicate Glass, ZBLAN Glass, GST Glass
        OreDictUnifier.registerOre(GTGlassCasing.FUSION_GLASS.stack, GTLiteOrePrefix.glass, Tier.UV)
        OreDictUnifier.registerOre(GlassCasing.BPA_POLYCARBONATE.stack, GTLiteOrePrefix.glass, Tier.UV)
        OreDictUnifier.registerOre(GlassCasing.TRITANIUM_BOROSILICATE.stack, GTLiteOrePrefix.glass, Tier.UV)
        OreDictUnifier.registerOre(GlassCasing.ZBLAN.stack, GTLiteOrePrefix.glass, Tier.UV)
        OreDictUnifier.registerOre(GlassCasing.GST.stack, GTLiteOrePrefix.glass, Tier.UV)

        // UHV: PMMA Glass, Neutronium reinforced Borosilicate Glass, Erbium-doped ZBLAN Glass, Praseodymium-doped ZBLAN Glass, Quantum Glass
        OreDictUnifier.registerOre(GlassCasing.PMMA.stack, GTLiteOrePrefix.glass, Tier.UHV)
        OreDictUnifier.registerOre(GlassCasing.NEUTRONIUM_BOROSILICATE.stack, GTLiteOrePrefix.glass, Tier.UHV)
        OreDictUnifier.registerOre(GlassCasing.ERBIUM_ZBLAN.stack, GTLiteOrePrefix.glass, Tier.UHV)
        OreDictUnifier.registerOre(GlassCasing.PRASEODYMIUM_ZBLAN.stack, GTLiteOrePrefix.glass, Tier.UHV)
        OreDictUnifier.registerOre(GlassCasing.QUANTUM.stack, GTLiteOrePrefix.glass, Tier.UHV)

        // UEV: CBDO Polycarbonate Glass, Cosmic Neutronium reinforced Borosilicate Glass, Force Field Glass, Chromatic Glass
        OreDictUnifier.registerOre(GlassCasing.CBDO_POLYCARBONATE.stack, GTLiteOrePrefix.glass, Tier.UEV)
        OreDictUnifier.registerOre(GlassCasing.COSMIC_NEUTRONIUM_BOROSILICATE.stack, GTLiteOrePrefix.glass, Tier.UEV)
        OreDictUnifier.registerOre(GlassCasing.FORCE_FIELD.stack, GTLiteOrePrefix.glass, Tier.UEV)
        OreDictUnifier.registerOre(GlassCasing.CHROMATIC.stack, GTLiteOrePrefix.glass, Tier.UEV)

        // UIV: CBDO Polycarbonate Glass, Infinity reinforced Borosilicate Glass, Antimatter Containment Glass
        OreDictUnifier.registerOre(GlassCasing.CBDO_POLYCARBONATE.stack, GTLiteOrePrefix.glass, Tier.UIV)
        OreDictUnifier.registerOre(GlassCasing.INFINITY_BOROSILICATE.stack, GTLiteOrePrefix.glass, Tier.UIV)
        OreDictUnifier.registerOre(GlassCasing.ANTIMATTER_CONTAINMENT.stack, GTLiteOrePrefix.glass, Tier.UIV)

        // UXV: Transcendent Metal reinforced Borosilicate Glass, Nano Shielding Frame
        OreDictUnifier.registerOre(GlassCasing.TRANSCENDENT_METAL_BOROSILICATE.stack, GTLiteOrePrefix.glass, Tier.UXV)
        OreDictUnifier.registerOre(GlassCasing.NANO_SHIELDING_FRAME.stack, GTLiteOrePrefix.glass, Tier.UXV)

        // TODO OpV and MAX
    }

    // @formatter:on

}