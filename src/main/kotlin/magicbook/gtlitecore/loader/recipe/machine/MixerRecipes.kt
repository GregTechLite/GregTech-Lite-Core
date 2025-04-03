package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Arsenic
import gregtech.api.unification.material.Materials.Blaze
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Electrotine
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Nichrome
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Obsidian
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.Redstone
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Saltpeter
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Stone
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Vanadium
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Yttrium
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.LARGE_MIXER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumBronze
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BabbitAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BlazingPyrotheum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BosonicUUMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EglinSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EglinSteelBase
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FermionicUUMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FreeElectronGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GelidCryotheum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Grisium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HSLASteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyC276
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyN
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyX
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IncoloyMA813
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IncoloyMA956
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Inconel625
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kovar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MaragingSteel250
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MolybdenumDisilicide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Monel500
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ReneN5
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Staballoy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Stellite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StrontiumFerrite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Talonite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TectonicPetrotheum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tumbaga
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WatertightSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZephyreanAerotheum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Zeron100
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZirconiumCarbide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SAND_DUST

@Suppress("MISSING_DEPENDENCY_CLASS")
class MixerRecipes
{

    companion object
    {

        fun init()
        {
            // Strontium Ferrite
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Strontium)
                .input(dust, Iron, 12)
                .fluidInputs(Oxygen.getFluid(19000))
                .output(dust, StrontiumFerrite, 32)
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Kovar
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Invar, 15)
                .input(dust, Cobalt, 3)
                .output(dust, Kovar, 18)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Iron, 10)
                .input(dust, Nickel, 5)
                .input(dust, Cobalt, 3)
                .output(dust, Kovar, 18)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Maraging Steel 250
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Iron, 16)
                .input(dust, Molybdenum, 1)
                .input(dust, Titanium, 1)
                .input(dust, Nickel, 4)
                .input(dust, Cobalt, 2)
                .output(dust, MaragingSteel250, 24)
                .EUt(VA[EV].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // Inconel-625
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Nickel, 3)
                .input(dust, Chrome, 7)
                .input(dust, Molybdenum, 10)
                .input(dust, Invar, 10)
                .input(dust, Nichrome, 13)
                .output(dust, Inconel625, 48)
                .EUt(VA[EV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // Staballoy
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Uranium, 9)
                .input(dust, Titanium, 1)
                .output(dust, Staballoy, 10)
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Talonite
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Cobalt, 4)
                .input(dust, Chrome, 3)
                .input(dust, Phosphorus, 2)
                .input(dust, Molybdenum, 1)
                .output(dust, Talonite, 10)
                .EUt(VA[HV].toLong())
                .duration(24 * SECOND)
                .buildAndRegister()

            // Zeron-100
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Chrome, 13)
                .input(dust, Nickel, 3)
                .input(dust, Molybdenum, 2)
                .input(dust, Copper, 10)
                .input(dust, Tungsten, 2)
                .input(dust, Steel, 20)
                .output(dust, Zeron100, 60)
                .EUt(VA[IV].toLong())
                .duration(48 * SECOND)
                .buildAndRegister()

            // Watertight Steel
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Iron, 7)
                .input(dust, Aluminium, 4)
                .input(dust, Nickel, 2)
                .input(dust, Chrome, 1)
                .input(dust, Sulfur, 1)
                .output(dust, WatertightSteel, 15)
                .EUt(VA[HV].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // Stellite
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Cobalt, 9)
                .input(dust, Chrome, 9)
                .input(dust, Manganese, 5)
                .input(dust, Titanium, 2)
                .output(dust, Stellite, 25)
                .EUt(VA[HV].toLong())
                .duration(18 * SECOND)
                .buildAndRegister()

            // Tumbaga
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Gold, 7)
                .input(dust, Bronze, 3)
                .output(dust, Tumbaga, 10)
                .EUt(VA[MV].toLong())
                .duration(14 * SECOND)
                .buildAndRegister()

            // Eglin Steel Base
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Iron, 4)
                .input(dust, Kanthal, 1)
                .input(dust, Invar, 5)
                .output(dust, EglinSteelBase, 10)
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Eglin Steel
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, EglinSteelBase, 10)
                .input(dust, Sulfur, 1)
                .input(dust, Silicon, 1)
                .input(dust, Carbon, 1)
                .output(dust, EglinSteel, 13)
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // One-Step recipe of Eglin Steel.
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Iron, 4)
                .input(dust, Kanthal, 1)
                .input(dust, Invar, 5)
                .input(dust, Sulfur, 1)
                .input(dust, Silicon, 1)
                .input(dust, Carbon, 1)
                .output(dust, EglinSteel, 13)
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Grisium
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Titanium, 9)
                .input(dust, Carbon, 9)
                .input(dust, Potassium, 9)
                .input(dust, Lithium, 9)
                .input(dust, Sulfur, 9)
                .fluidInputs(Hydrogen.getFluid(5000))
                .output(dust, Grisium, 50)
                .EUt(VA[EV].toLong())
                .duration(38 * SECOND)
                .buildAndRegister()

            // Babbit Alloy
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Tin, 5)
                .input(dust, Lead, 36)
                .input(dust, Antimony, 8)
                .input(dust, Arsenic)
                .output(dust, BabbitAlloy, 50)
                .EUt(VA[MV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // HSLA Steel
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Invar, 2)
                .input(dust, Vanadium, 1)
                .input(dust, Titanium, 1)
                .input(dust, Molybdenum, 1)
                .output(dust, HSLASteel, 5)
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Incoloy-MA813
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, VanadiumSteel, 4)
                .input(dust, Niobium, 2)
                .input(dust, Chrome, 3)
                .input(dust, Nickel, 4)
                .output(dust, IncoloyMA813, 13)
                .EUt(VA[IV].toLong())
                .duration(22 * SECOND)
                .buildAndRegister()

            // Monel-500
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Nickel, 23)
                .input(dust, Manganese, 2)
                .input(dust, Copper, 10)
                .input(dust, Aluminium, 4)
                .input(dust, Titanium, 1)
                .output(dust, Monel500, 40)
                .EUt(VA[IV].toLong())
                .duration(56 * SECOND)
                .buildAndRegister()

            // Incoloy-MA956
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Iron, 16)
                .input(dust, Aluminium, 3)
                .input(dust, Chrome, 5)
                .input(dust, Yttrium, 1)
                .output(dust, IncoloyMA956, 25)
                .EUt(VA[IV].toLong())
                .duration(34 * SECOND)
                .buildAndRegister()

            // Zirconium Carbide
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Zirconium, 1)
                .input(dust, Carbon, 1)
                .output(dust, ZirconiumCarbide, 2)
                .EUt(VA[HV].toLong())
                .duration(18 * SECOND)
                .buildAndRegister()

            // Tantalum Carbide
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Tantalum, 1)
                .input(dust, Carbon, 1)
                .output(dust, TantalumCarbide, 2)
                .EUt(VA[EV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Molybdenum Disilicide
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Molybdenum, 1)
                .input(dust, Silicon, 2)
                .output(dust, MolybdenumDisilicide, 3)
                .EUt(VA[EV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()

            // Hastelloy-C276
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Nickel, 12)
                .input(dust, Molybdenum, 8)
                .input(dust, Chrome, 7)
                .input(dust, Tungsten)
                .input(dust, Cobalt)
                .input(dust, Copper)
                .output(dust, HastelloyC276, 30)
                .EUt(VA[IV].toLong())
                .duration(45 * SECOND)
                .buildAndRegister()

            // Hastelloy-X
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Nickel, 8)
                .input(dust, Iron, 3)
                .input(dust, Tungsten, 4)
                .input(dust, Molybdenum, 2)
                .input(dust, Chrome, 1)
                .input(dust, Niobium, 1)
                .output(dust, HastelloyX, 19)
                .EUt(VA[IV].toLong())
                .duration(43 * SECOND)
                .buildAndRegister()

            // Hastelloy-N
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Yttrium, 2)
                .input(dust, Molybdenum, 4)
                .input(dust, Chrome, 2)
                .input(dust, Titanium, 2)
                .input(dust, Nickel, 15)
                .output(dust, HastelloyN, 25)
                .EUt(VA[IV].toLong())
                .duration(38 * SECOND)
                .buildAndRegister()

            // Aluminium Bronze
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Aluminium, 2)
                .input(dust, Manganese)
                .input(dust, Bronze)
                .output(dust, AluminiumBronze, 4)
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Rene N5
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Nickel, 22)
                .input(dust, Cobalt, 4)
                .input(dust, Chrome, 3)
                .input(dust, Aluminium, 3)
                .input(dust, Tungsten, 2)
                .input(dust, Hafnium)
                .input(dust, Rhenium, 2)
                .input(dust, Tantalum, 3)
                .output(dust, ReneN5, 40)
                .EUt(VA[LuV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Blazing Pyrotheum
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Blaze, 2)
                .input(dust, Redstone)
                .input(dust, Sulfur)
                .output(dust, BlazingPyrotheum, 4)
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Gelid Cryotheum
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Ice, 2)
                .input(dust, Electrotine)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, GelidCryotheum, 4)
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Tectonic Petrotheum
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Clay, 2)
                .input(dust, Obsidian)
                .input(dust, Stone)
                .output(dust, TectonicPetrotheum, 4)
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Zephyrean Aerotheum
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(SAND_DUST, 2)
                .input(dust, Saltpeter)
                .fluidInputs(Air.getFluid(1000))
                .output(dust, ZephyreanAerotheum, 4)
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // UU Matter
            LARGE_MIXER_RECIPES.recipeBuilder()
                .fluidInputs(BosonicUUMatter.getFluid(1000))
                .fluidInputs(FermionicUUMatter.getFluid(1000))
                .fluidInputs(FreeElectronGas.getFluid(2000))
                .fluidOutputs(UUMatter.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

        }

    }

}