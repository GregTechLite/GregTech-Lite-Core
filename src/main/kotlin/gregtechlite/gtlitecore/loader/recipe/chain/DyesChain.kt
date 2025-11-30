package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.ArsenicTrioxide
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.Barite
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.BariumSulfide
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.ChromiumTrioxide
import gregtech.api.unification.material.Materials.CobaltOxide
import gregtech.api.unification.material.Materials.DilutedSulfuricAcid
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.Iodine
import gregtech.api.unification.material.Materials.Iron3Chloride
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Massicot
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Naphthalene
import gregtech.api.unification.material.Materials.NitrationMixture
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Nitrobenzene
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Pyrolusite
import gregtech.api.unification.material.Materials.RockSalt
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Toluene
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.SU
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.getFluid
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Alumina
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AluminiumSulfate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aniline
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumDichloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumManganate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlueVitriol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BurntSienna
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butanol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonTetrachloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CobaltAluminate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CopperArsenite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CopperDichloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CyanIndigo
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DiaminostilbenedisulfonicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diaminotoluene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diketopyrrolopyrrole
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DirectBrown77
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EosinY
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Erythrosine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneDibromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorescein
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Formaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenCyanide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Indigo
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Iron2Chloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IsopropylAlcohol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IsopropylSuccinate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadChromate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ManganeseBlue
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ManganeseMonoxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mauveine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Naphthylamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nigrosin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nitrotoluene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhthalicAnhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PigmentRed
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumFerrocyanideTrihydrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumManganate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrussianBlue
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pyridine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resorcinol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ScheelesGreen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Sienna
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumHypochlorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumSulfanilate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuccinicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetrabromoindigo

internal object DyesChain
{

    // @formatter:off

    fun init()
    {
        siennaProcess() // Brown & Red
        leadAcidateProcess() // Yellow & White
        cobaltAluminateProcess() // Blue
        dsdaProcess() // White
        fluoresceinErythrosineProcess() // Yellow & Red
        prussianBlueProcess() // Blue
        indigoProcess() // Blue
        nigrosinProcess() // Black
        directBrown77Process() // Brown
        scheelesGreenProcess() // Green
        mauveineProcess() // Purple
        diketopyrrolopyrroleProcess() // Orange
        eosinYProcess() // Pink
        manganeseBlueProcess() // Light Blue
        pigmentRedProcess() // Magenta
    }

    private fun siennaProcess()
    {

        // Mn + O -> MnO
        ROASTER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Manganese)
            .fluidInputs(Oxygen.getFluid(1000))
            .output(dust, ManganeseMonoxide, 2)
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Mn + 2O -> MnO2
        ROASTER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Manganese)
            .fluidInputs(Oxygen.getFluid(2000))
            .output(dust, Pyrolusite, 3)
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // MnO + Fe2O3 -> (MnO)(Fe2O3)
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, ManganeseMonoxide)
            .input(dust, BandedIron)
            .output(dust, Sienna, 2)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // (MnO)(Fe2O3) (Brown) -> (MnO)(Fe2O3) (Red)
        ModHandler.addSmeltingRecipe(OreDictUnifier.get(dust, Sienna),
            OreDictUnifier.get(dust, BurntSienna))

    }

    private fun leadAcidateProcess()
    {
        // PbO + CrO3 -> PbCrO4
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Massicot, 2)
            .input(dust, ChromiumTrioxide, 4)
            .output(dust, LeadChromate, 6)
            .EUt(VA[MV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // PbO + 2HNO3 -> Pb(NO3)2 + H2O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Massicot, 2)
            .fluidInputs(NitricAcid.getFluid(2000))
            .output(dust, LeadNitrate, 9)
            .fluidOutputs(Steam.getFluid(1 * SU))
            .EUt(VA[MV])
            .duration(4 * SECOND)
            .buildAndRegister()
    }

    private fun cobaltAluminateProcess()
    {
        // Al2O3 + CoO -> CoAl2O4
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Alumina, 5)
            .input(dust, CobaltOxide, 2)
            .output(dust, CobaltAluminate, 7)
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    private fun dsdaProcess()
    {
        // C7H8 + 2(HNO3)(H2SO4) + H2SO4 -> C7H7NO2 + 3H2SO4
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Toluene.getFluid(1000))
            .fluidInputs(NitrationMixture.getFluid(2000))
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .fluidOutputs(Nitrotoluene.getFluid(1000))
            .fluidOutputs(DilutedSulfuricAcid.getFluid(3000))
            .EUt(VA[EV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // NaClO + C7H7NO2 + 2H2SO4 -> 0.5C14H14N2O6S2 + NaCl + 4H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, Zinc)
            .input(dust, SodiumHypochlorite, 3)
            .fluidInputs(Nitrotoluene.getFluid(1000))
            .fluidInputs(SulfuricAcid.getFluid(2000))
            .output(dust, DiaminostilbenedisulfonicAcid, 19) // 38 / 2
            .output(dust, Salt, 2)
            .fluidOutputs(Water.getFluid(4000))
            .EUt(VA[EV])
            .duration(16 * SECOND)
            .buildAndRegister()
    }

    private fun fluoresceinErythrosineProcess()
    {
        // C6H4(CO)2O + 2C6H6O2 -> C20H12O5 + 2H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .input(dust, PhthalicAnhydride, 15)
            .fluidInputs(Resorcinol.getFluid(2000))
            .output(dust, Fluorescein, 37)
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[EV])
            .duration(11 * SECOND + 5 * TICK)
            .buildAndRegister()

        // C20H12O5 + 2NaOH + 4I -> C20H6O5Na2I4 + 4H + 2H2O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Fluorescein, 37)
            .input(dust, SodiumHydroxide, 6)
            .input(dust, Iodine, 4)
            .output(dust, Erythrosine, 37)
            .fluidOutputs(Hydrogen.getFluid(4000))
            .fluidOutputs(Steam.getFluid(2 * SU))
            .EUt(VHA[HV])
            .duration(7 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    private fun prussianBlueProcess()
    {
        // FeCl2 + 6HCN + 4KOH -> K4Fe(CN)6(H2O)3 + 2HCl + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, PotassiumHydroxide, 12)
            .fluidInputs(Iron2Chloride.getFluid(1000))
            .fluidInputs(HydrogenCyanide.getFluid(6000))
            .output(dust, PotassiumFerrocyanideTrihydrate, 26)
            .fluidInputs(HydrochloricAcid.getFluid(2000))
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[EV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 3K4Fe(CN)6(H2O)3 + 4FeCl3 -> Fe4[Fe(CN)6]3 + 12KCl + 9H2O
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, PotassiumFerrocyanideTrihydrate, 64)
            .input(dust, PotassiumFerrocyanideTrihydrate, 14)
            .fluidInputs(Iron3Chloride.getFluid(4000))
            .output(dust, PrussianBlue, 43)
            .output(dust, RockSalt, 24)
            .fluidOutputs(Water.getFluid(9000))
            .EUt(VA[EV])
            .duration(7 * SECOND + 10 * TICK)
            .buildAndRegister()

    }

    private fun indigoProcess()
    {
        // 2C6H5NH2 + 2CH2O + 2HCN -> C16H10N2O2 + 2NH3 + 4H
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Aniline.getFluid(2000))
            .fluidInputs(Formaldehyde.getFluid(2000))
            .fluidInputs(HydrogenCyanide.getFluid(2000))
            .output(dust, Indigo, 30)
            .fluidOutputs(Ammonia.getFluid(2000))
            .fluidOutputs(Hydrogen.getFluid(4000))
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // C16H10N2O2 + 4Br -> C16H10Br4N2O2
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Indigo, 30)
            .fluidInputs(Bromine.getFluid(4000))
            .output(dust, Tetrabromoindigo, 34)
            .EUt(VA[MV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C16H10N2O2 + C16H10Br4N2O2 -> (C16H10N2O2)2Br4
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Indigo)
            .input(dust, Tetrabromoindigo)
            .output(dust, CyanIndigo, 2)
            .EUt(VA[EV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // (C16H10N2O2)2Br4 decomposition.
        CENTRIFUGE_RECIPES.recipeBuilder()
            .input(dust, CyanIndigo, 2)
            .output(dust, Indigo)
            .output(dust, Tetrabromoindigo)
            .EUt(VA[LV])
            .duration(4 * SECOND + 5 * TICK)
            .buildAndRegister()
    }

    private fun nigrosinProcess()
    {
        // 4C6H5NH2 + 2C6H5NO2 + HCl + 2H2SO4 + 2NaOH -> 0.5C36H26N5ClNa2S2O6 + 8H2O + NH3
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(12)
            .input(dust, SodiumHydroxide, 6)
            .fluidInputs(Aniline.getFluid(4000))
            .fluidInputs(Nitrobenzene.getFluid(2000))
            .fluidInputs(HydrochloricAcid.getFluid(500))
            .fluidInputs(SulfuricAcid.getFluid(2000))
            .output(dust, Nigrosin, 39)
            .fluidOutputs(Water.getFluid(8000))
            .fluidOutputs(Ammonia.getFluid(1000))
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    private fun directBrown77Process()
    {
        // NaOH + C6H5NH2 + H2SO4 -> C6H6NNaO3S + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, SodiumHydroxide, 3)
            .fluidInputs(Aniline.getFluid(1000))
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .output(dust, SodiumSulfanilate, 18)
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // C10H8 + HNO3 -> C10H8NH + 3O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(Naphthalene.getFluid(1000))
            .fluidInputs(NitricAcid.getFluid(1000))
            .fluidOutputs(Naphthylamine.getFluid(1000))
            .fluidOutputs(Oxygen.getFluid(3000))
            .EUt(VA[HV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C6H6NNaO3S + 2C10H8NH + 3NaNO3 -> C26H19N6NaO3S + 3NaOH + H2O + 5O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, SodiumSulfanilate, 18)
            .input(dust, SodiumNitrate, 15)
            .fluidInputs(Naphthylamine.getFluid(2000))
            .output(dust, DirectBrown77, 56)
            .output(dust, SodiumHydroxide, 9)
            .fluidOutputs(Water.getFluid(1000))
            .fluidOutputs(Oxygen.getFluid(5000))
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()

    }

    private fun scheelesGreenProcess()
    {
        // As2O3 + 3CuSO4 -> Cu3(AsO4)2 + 3SO2 + O (drop)
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, ArsenicTrioxide, 5)
            .fluidInputs(BlueVitriol.getFluid(3000))
            .output(dust, CopperArsenite, 13)
            .fluidOutputs(SulfurDioxide.getFluid(3000))
            .EUt(VA[MV])
            .duration(12 * SECOND)
            .buildAndRegister()

        // Cu3(AsO4)2 + 2HCl -> 2AsCuHO3 + CuCl2 + 2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, CopperArsenite, 13)
            .fluidInputs(HydrochloricAcid.getFluid(2000))
            .output(dust, ScheelesGreen, 12)
            .output(dust, CopperDichloride, 3)
            .fluidOutputs(Oxygen.getFluid(2000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()
    }

    private fun mauveineProcess()
    {
        // 4C6H5NH2 + 17Cl + 2CH4-> C26H23N4 + 13HCl + 4CCl4
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(13)
            .fluidInputs(Aniline.getFluid(4000))
            .fluidInputs(Chlorine.getFluid(17000))
            .fluidInputs(Methane.getFluid(2000))
            .output(dust, Mauveine, 53)
            .fluidOutputs(HydrochloricAcid.getFluid(13000))
            .fluidOutputs(CarbonTetrachloride.getFluid(4000))
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

    }

    private fun diketopyrrolopyrroleProcess()
    {
        // C4H6O4 + 2C3H8O -> C7H12O4 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, SuccinicAcid, 14)
            .fluidInputs(IsopropylAlcohol.getFluid(2000))
            .fluidOutputs(IsopropylSuccinate.getFluid(1000))
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[HV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C7H12O4 + 2C5H5N + 5O -> C18H12N2O2 + CO2 + 5H2O
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(IsopropylSuccinate.getFluid(1000))
            .fluidInputs(Pyridine.getFluid(2000))
            .fluidInputs(Oxygen.getFluid(5000))
            .output(dust, Diketopyrrolopyrrole, 34)
            .fluidOutputs(CarbonDioxide.getFluid(1000))
            .fluidOutputs(Ice.getFluid(5000))
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    private fun eosinYProcess()
    {

        // 2C2H4Br2 + Na2O + C20H12O5 + 2O -> C20H6Br4Na2O5 + C4H9OH + 2H2O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, SodiumOxide, 3)
            .input(dust, Fluorescein, 37)
            .fluidInputs(EthyleneDibromide.getFluid(2000))
            .fluidInputs(Oxygen.getFluid(2000))
            .output(dust, EosinY, 37)
            .fluidOutputs(Butanol.getFluid(1000))
            .fluidOutputs(Steam.getFluid(2 * SU))
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

    }

    private fun manganeseBlueProcess()
    {
        // Ba + 2Cl -> BaCl2
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Barium)
            .fluidInputs(Chlorine.getFluid(2000))
            .output(dust, BariumDichloride, 3)
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // BaCl2 + K2MnO4 -> BaMnO4 + 2KCl
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, BariumDichloride, 3)
            .input(dust, PotassiumManganate, 7)
            .output(dust, BariumManganate, 6)
            .output(dust, RockSalt, 4)
            .EUt(VA[MV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // BaSO4 + BaMnO4 -> (BaSO4)(BaMnO4)
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Barite)
            .input(dust, BariumManganate)
            .output(dust, ManganeseBlue, 2)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    private fun pigmentRedProcess()
    {

        // 2BaS + H2SO4 + 2C6H3(NH2)2CH3 + 2C10H8 + 6CO2 -> C40H26N4O8S2Ba + 6H2O + BaO + S + O (drop)
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .notConsumable(dust, Rutile)
            .notConsumable(dust, AluminiumSulfate)
            .input(dust, BariumSulfide, 4)
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .fluidInputs(Diaminotoluene.getFluid(2000))
            .fluidInputs(Naphthalene.getFluid(2000))
            .fluidInputs(CarbonDioxide.getFluid(6000))
            .output(dust, PigmentRed, 64)
            .output(dust, PigmentRed, 17)
            .output(dust, BariumOxide, 2)
            .output(dust, Sulfur)
            .fluidOutputs(Water.getFluid(6000))
            .EUt(VA[IV])
            .duration(45 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}