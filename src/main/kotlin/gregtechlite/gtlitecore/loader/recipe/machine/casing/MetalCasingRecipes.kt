package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.BismuthBronze
import gregtech.api.unification.material.Materials.BlackSteel
import gregtech.api.unification.material.Materials.BlueSteel
import gregtech.api.unification.material.Materials.Brass
import gregtech.api.unification.material.Materials.CobaltBrass
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Potin
import gregtech.api.unification.material.Materials.RedSteel
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AluminiumBronze
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BabbitAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EglinSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Grisium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyC276
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyN
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyX
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IncoloyMA813
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IncoloyMA956
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Inconel625
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kovar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MaragingSteel250
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Monel500
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nitinol60
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ReneN5
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SiliconCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Staballoy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Stellite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Talonite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitaniumTungstenCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trinaquadalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tumbaga
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WatertightSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Zeron100
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZirconiumCarbide
import gregtechlite.gtlitecore.common.block.variant.MetalCasing

internal object MetalCasingRecipes
{

    // @formatter:off

    fun init()
    {
        create(MetalCasing.MARAGING_STEEL_250, MaragingSteel250)
        create(MetalCasing.INCONEL_625, Inconel625)
        create(MetalCasing.BLUE_STEEL, BlueSteel)
        create(MetalCasing.STABALLOY, Staballoy)
        create(MetalCasing.TALONITE, Talonite)
        create(MetalCasing.IRIDIUM, Iridium)
        create(MetalCasing.ZERON_100, Zeron100)
        create(MetalCasing.WATERTIGHT_STEEL, WatertightSteel)
        create(MetalCasing.STELLITE, Stellite)
        create(MetalCasing.TUMBAGA, Tumbaga)
        create(MetalCasing.EGLIN_STEEL, EglinSteel)
        create(MetalCasing.POTIN, Potin)
        create(MetalCasing.GRISIUM, Grisium)
        create(MetalCasing.BABBIT_ALLOY, BabbitAlloy)
        create(MetalCasing.SILICON_CARBIDE, SiliconCarbide)
        create(MetalCasing.RED_STEEL, RedSteel)

        create(MetalCasing.HSLA_STEEL, HSLASteel)
        create(MetalCasing.KOVAR, Kovar)
        create(MetalCasing.BLACK_STEEL, BlackSteel)
        create(MetalCasing.INCOLOY_MA813, IncoloyMA813)
        create(MetalCasing.MONEL_500, Monel500)
        create(MetalCasing.INCOLOY_MA956, IncoloyMA956)
        create(MetalCasing.ZIRCONIUM_CARBIDE, ZirconiumCarbide)
        create(MetalCasing.HASTELLOY_C276, HastelloyC276)
        create(MetalCasing.HASTELLOY_X, HastelloyX)
        create(MetalCasing.POLYBENZIMIDAZOLE, Polybenzimidazole)
        create(MetalCasing.ALUMINIUM_BRONZE, AluminiumBronze)
        create(MetalCasing.HASTELLOY_N, HastelloyN)
        create(MetalCasing.RENE_N5, ReneN5)
        create(MetalCasing.BISMUTH_BRONZE, BismuthBronze)
        create(MetalCasing.BRASS, Brass)
        create(MetalCasing.TITANIUM_TUNGSTEN_CARBIDE, TitaniumTungstenCarbide)

        create(MetalCasing.COBALT_BRASS, CobaltBrass)
        create(MetalCasing.TRINAQUADALLOY, Trinaquadalloy)
        create(MetalCasing.OSMIRIDIUM, Osmiridium)
        create(MetalCasing.NEUTRONIUM, Neutronium)
        create(MetalCasing.NAQUADAH_ALLOY, NaquadahAlloy)
        create(MetalCasing.QUANTUM_ALLOY, QuantumAlloy)
        create(MetalCasing.INCONEL_718, Inconel718)
        create(MetalCasing.NITINOL_60, Nitinol60)

    }

    private fun create(outputCasing: BlockVariant, material: Material)
    {
        ModHandler.addShapedRecipe(true, material.name.lowercase() + "_casing",
                                   outputCasing.getStack(ConfigHolder.recipes.casingsPerCraft),
                                   "PhP", "PFP", "PwP",
                                   'P', UnificationEntry(plate, material),
                                   'F', UnificationEntry(frameGt, material))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(plate, material, 6)
            .input(frameGt, material)
            .outputs(outputCasing.getStack(ConfigHolder.recipes.casingsPerCraft))
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    // @formatter:on

}