package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.BatteryAlloy
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.BlueAlloy
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.BorosilicateGlass
import gregtech.api.unification.material.Materials.Brass
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.CobaltBrass
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Cupronickel
import gregtech.api.unification.material.Materials.Electrotine
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.Glass
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Lanthanum
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Magnalium
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.Potin
import gregtech.api.unification.material.Materials.RedAlloy
import gregtech.api.unification.material.Materials.Redstone
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Xenon
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ALLOY_BLAST_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BETSPerrhenate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumStrontiumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BerylliumDifluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BismuthStrontiumCalciumCuprate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BismuthTrioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EglinSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Firestone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullereneSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GSTGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyLeptonMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumFullereneNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumFluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumFluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RedPhosphorus
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ResonantStrangeMeson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SeaborgiumDopedCarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumFluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Strontianite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StrontiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tairitsium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tenorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitanSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZBLANGlass
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import net.minecraftforge.fluids.Fluid

internal object AlloyBlastSmelterRecipes
{

    // @formatter:off

    fun init()
    {
        formulaicRecipes()
        manualRecipes()
    }

    private fun formulaicRecipes()
    {
        registerBinaryAlloy(Copper, 3, Tin, 1,
            Bronze, 4, 20 * SECOND)
        registerBinaryAlloy(Copper, 3, Zinc, 1,
            Brass, 4, 20 * SECOND)
        registerBinaryAlloy(Copper, 1, Nickel, 1,
            Cupronickel, 2, 10 * SECOND)
        registerBinaryAlloy(Copper, 1, Redstone, 4,
            RedAlloy, 1, 5 * SECOND)
        registerBinaryAlloy(Sodium, 7, Potassium, 3,
            SodiumPotassiumEutatic, 10, 5 * SECOND)
        registerBinaryAlloy(Lead, 3, Bismuth, 7,
            LeadBismuthEutatic, 10, 2 * SECOND + 10 * TICK)
        registerBinaryAlloy(BariumTitanate, 5, StrontiumOxide, 2,
            BariumStrontiumTitanate, 7, 28 * SECOND)

        registerBinaryAlloy(Iron, 1, Tin, 1,
            TinAlloy, 2, 5 * SECOND)
        registerBinaryAlloy(Iron, 2, Nickel, 1,
            Invar, 3, 15 * SECOND)
        registerBinaryAlloy(Lead, 4, Antimony, 1,
            BatteryAlloy, 5, 12 * SECOND + 10 * TICK)
        registerBinaryAlloy(Gold, 1, Silver, 1,
            Electrum, 2, 10 * SECOND)
        registerBinaryAlloy(Magnesium, 1, Aluminium, 2,
            Magnalium, 3, 7 * SECOND + 10 * TICK)
        registerBinaryAlloy(Silver, 1, Electrotine, 4,
            BlueAlloy, 1, 5 * SECOND)
        registerBinaryAlloy(Glass, 7, Boron, 1,
            BorosilicateGlass, 8, 10 * SECOND)
        registerBinaryAlloy(LithiumFluoride, 2, BerylliumDifluoride, 3,
            LithiumBerylliumFluorides, 5, 5 * SECOND)

        registerTrinaryAlloy(Brass, 7, Aluminium, 1,
            Cobalt, 1, CobaltBrass, 9, 45 * SECOND)
        registerTrinaryAlloy(Tin, 6, Lead, 3,
            Antimony, 1, SolderingAlloy, 10, 10 * SECOND)
        registerTrinaryAlloy(Copper, 6, Tin, 2,
            Lead, 1, Potin, 9, 20 * SECOND)
        registerTrinaryAlloy(LithiumFluoride, 2, SodiumFluoride, 2,
            PotassiumFluoride, 2, LithiumSodiumPotassiumFluorides, 6, 5 * SECOND)
    }

    private fun manualRecipes()
    {

        // One-Step ABS recipe of Eglin Steel
        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(dust, Iron, 4)
            .input(dust, Kanthal, 1)
            .input(dust, Invar, 5)
            .input(dust, Sulfur, 1)
            .input(dust, Silicon, 1)
            .input(dust, Carbon, 1)
            .fluidInputs(Nitrogen.getFluid(13000))
            .fluidOutputs(EglinSteel.getFluid(L * 13))
            .EUt(VA[MV])
            .duration(7 * SECOND + 16 * TICK)
            .blastFurnaceTemp(1048) // Cupronickel
            .buildAndRegister()

        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(dust, Iron, 4)
            .input(dust, Kanthal, 1)
            .input(dust, Invar, 5)
            .input(dust, Sulfur, 1)
            .input(dust, Silicon, 1)
            .input(dust, Carbon, 1)
            .fluidOutputs(EglinSteel.getFluid(L * 13))
            .EUt(VA[MV])
            .duration(11 * SECOND + 14 * TICK)
            .blastFurnaceTemp(1048) // Cupronickel
            .buildAndRegister()

        // ZBLAN Glass
        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(dust, Zirconium, 5)
            .input(dust, Barium, 2)
            .input(dust, Lanthanum)
            .input(dust, Aluminium)
            .input(dust, Sodium, 2)
            .fluidInputs(Fluorine.getFluid(6200))
            .fluidOutputs(ZBLANGlass.getFluid(L * 11))
            .EUt(VA[HV])
            .duration(1 * MINUTE + 30 * SECOND)
            .blastFurnaceTemp(1073) // Cupronickel
            .buildAndRegister()

        // GST Glass
        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Germanium, 2)
            .input(dust, Antimony, 2)
            .input(dust, Tellurium, 5)
            .fluidOutputs(GSTGlass.getFluid(L * 9))
            .EUt(VA[HV])
            .duration(MINUTE + 20 * SECOND)
            .blastFurnaceTemp(873) // Cupronickel
            .buildAndRegister()

        // BSCCO
        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(14)
            .input(dust, BismuthTrioxide, 5)
            .input(dust, Strontianite, 10)
            .input(dust, Calcite, 5)
            .input(dust, Tenorite, 4)
            .fluidInputs(Neon.getFluid(150))
            .fluidOutputs(BismuthStrontiumCalciumCuprate.getFluid(L * 15))
            .EUt(VA[UV])
            .duration(2 * MINUTE + 9 * SECOND + 12 * TICK)
            .blastFurnaceTemp(7000) // Naquadah
            .buildAndRegister()

        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(dust, BismuthTrioxide, 5)
            .input(dust, Strontianite, 10)
            .input(dust, Calcite, 5)
            .input(dust, Tenorite, 4)
            .fluidOutputs(BismuthStrontiumCalciumCuprate.getFluid(L * 15))
            .EUt(VA[UV])
            .duration(3 * MINUTE + 13 * SECOND + 10 * TICK)
            .blastFurnaceTemp(7000) // Naquadah
            .buildAndRegister()

        // Halkonite Steel
        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(dust, CosmicNeutronium, 2)
            .input(dust, Tairitsium, 2)
            .input(dust, RedPhosphorus, 2)
            .input(dust, TitanSteel)
            .input(dust, Infinity)
            .fluidInputs(HeavyLeptonMixture.getFluid(1000))
            .fluidOutputs(HalkoniteSteel.getFluid(L * 8))
            .EUt(VA[UEV])
            .duration(1 * MINUTE)
            .blastFurnaceTemp(13801) // Infinity
            .buildAndRegister()

        // Vibranium Tritanium Actinium Iron Superhydride
        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(15)
            .input(dust, Vibranium, 5)
            .input(dust, Tritanium, 5)
            .input(dust, ActiniumSuperhydride)
            .input(dust, BETSPerrhenate)
            .fluidInputs(Iron.getPlasma(L))
            .fluidInputs(Krypton.getFluid(130))
            .fluidOutputs(VibraniumTritaniumActiniumIronSuperhydride.getFluid(L * 13))
            .EUt(VA[UEV])
            .duration(195 * SECOND + 19 * TICK)
            .blastFurnaceTemp(14400) // Infinity
            .buildAndRegister()

        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(dust, Vibranium, 5)
            .input(dust, Tritanium, 5)
            .input(dust, ActiniumSuperhydride)
            .input(dust, BETSPerrhenate)
            .fluidInputs(Iron.getPlasma(L))
            .fluidOutputs(VibraniumTritaniumActiniumIronSuperhydride.getFluid(L * 13))
            .EUt(VA[UEV])
            .duration(292 * SECOND + 10 * TICK)
            .blastFurnaceTemp(14400) // Infinity
            .buildAndRegister()

        // Mellion
        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(dust, Rubidium, 11)
            .input(dust, Tritanium, 11)
            .input(dust, Adamantium, 7)
            .input(dust, Firestone, 13)
            .input(dust, MetastableOganesson, 13)
            .input(dust, ActiniumSuperhydride, 8)
            .fluidInputs(ResonantStrangeMeson.getFluid(1000))
            .fluidOutputs(Mellion.getFluid(L * 64))
            .EUt(VA[UXV])
            .duration(20 * SECOND)
            .blastFurnaceTemp(16000)
            .buildAndRegister()

        // Fullerene Superconductor
        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(15)
            .input(dust, TitanSteel, 16)
            .input(dust, LanthanumFullereneNanotube, 4)
            .input(dust, SeaborgiumDopedCarbonNanotube, 4)
            .input(dust, MetastableOganesson, 3)
            .fluidInputs(Xenon.getPlasma(1000))
            .fluidInputs(Krypton.getFluid(280))
            .fluidOutputs(FullereneSuperconductor.getFluid(L * 28))
            .EUt(VA[UIV])
            .duration(2884 * SECOND + 7 * TICK)
            .blastFurnaceTemp(15900) // Halkonite Steel
            .buildAndRegister()

        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(dust, TitanSteel, 16)
            .input(dust, LanthanumFullereneNanotube, 4)
            .input(dust, SeaborgiumDopedCarbonNanotube, 4)
            .input(dust, MetastableOganesson, 3)
            .fluidInputs(Xenon.getPlasma(1000))
            .fluidOutputs(FullereneSuperconductor.getFluid(L * 28))
            .EUt(VA[UIV])
            .duration(4305 * SECOND)
            .blastFurnaceTemp(15900) // Halkonite Steel
            .buildAndRegister()

    }

    private fun registerBinaryAlloy(input1: Material, input1Amount: Int,
                                    input2: Material, input2Amount: Int,
                                    output: Material, outputAmount: Int,
                                    duration: Int)
    {
        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(input1Amount + input2Amount)
            .input(dust, input1, input1Amount)
            .input(dust, input2, input2Amount)
            .fluidOutputs(output.getFluid(L * outputAmount))
            .EUt(16)
            .duration(duration * 3 / 4)
            .blastFurnaceTemp((output.fluid as Fluid).temperature)
            .buildAndRegister()
    }

    private fun registerTrinaryAlloy(input1: Material, input1Amount: Int,
                                     input2: Material, input2Amount: Int,
                                     input3: Material, input3Amount: Int,
                                     output: Material, outputAmount: Int,
                                     duration: Int)
    {
        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(input1Amount + input2Amount + input3Amount)
            .input(dust, input1, input1Amount)
            .input(dust, input2, input2Amount)
            .input(dust, input3, input3Amount)
            .fluidOutputs(output.getFluid(L * outputAmount))
            .EUt(16)
            .duration(duration * 3 / 4)
            .blastFurnaceTemp((output.fluid as Fluid).temperature)
            .buildAndRegister()
    }

}