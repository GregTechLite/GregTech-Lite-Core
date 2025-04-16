package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
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
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ALLOY_BLAST_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumStrontiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BerylliumDifluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthStrontiumCalciumCuprate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EglinSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GSTGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadBismuthEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumBerylliumFluorides
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumFluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumSodiumPotassiumFluorides
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumFluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumFluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumPotassiumEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Strontianite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StrontiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tenorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZBLANGlass
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import net.minecraftforge.fluids.Fluid

@Suppress("MISSING_DEPENDENCY_CLASS")
class AlloyBlastSmelterRecipes
{

    companion object
    {

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
                .EUt(VA[MV].toLong())
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
                .EUt(VA[MV].toLong())
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
                .EUt(VA[HV].toLong())
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
                .EUt(VA[HV].toLong())
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
                .EUt(VA[UV].toLong())
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
                .EUt(VA[UV].toLong())
                .duration(3 * MINUTE + 13 * SECOND + 10 * TICK)
                .blastFurnaceTemp(7000) // Naquadah
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

}