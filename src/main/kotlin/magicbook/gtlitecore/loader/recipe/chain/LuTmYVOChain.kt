package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.DissolvedCalciumAcetate
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.MethylAcetate
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Propene
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumCarbonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Carbamide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LuTmDopedYttriumVanadateDeposition
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LuTmYChloridesSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LuTmYVO
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LutetiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumCarbonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumVanadate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThuliumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.VanadiumPentoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YttriumOxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class LuTmYVOChain
{

    companion object
    {

        fun init()
        {
            // Lu2O3 + Tm2O3 + 3Y2O3 + 30HCl -> 30(LuCl3)2(TmCl3)2(YCl3)6(H2O)15
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, LutetiumOxide, 5)
                .input(dust, ThuliumOxide, 5)
                .input(dust, YttriumOxide, 15)
                .fluidInputs(HydrochloricAcid.getFluid(30000))
                .fluidOutputs(LuTmYChloridesSolution.getFluid(30000))
                .EUt(VA[IV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // 3(Na2CO3)(H2O) + V2O5 -> 2Na3VO4 + C3H6O2 + 7O
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, VanadiumPentoxide, 7)
                .notConsumable(DissolvedCalciumAcetate.getFluid(1))
                .fluidInputs(SodiumCarbonate.getFluid(3000))
                .output(dust, SodiumVanadate, 16)
                .fluidOutputs(MethylAcetate.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(7000))
                .EUt(VA[IV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Na3VO4 + 2CH4N2O + (LuCl3)2(TmCl3)2(YCl3)6(H2O)15 -> Lu/Tm:YVO? + 0.9Cl
            CVD_RECIPES.recipeBuilder()
                .input(dust, SodiumVanadate, 8)
                .input(dust, Carbamide, 16)
                .fluidInputs(LuTmYChloridesSolution.getFluid(1000))
                .output(dust, LuTmDopedYttriumVanadateDeposition, 10)
                .fluidOutputs(Chlorine.getFluid(900))
                .EUt(VA[ZPM].toLong())
                .duration(6 * SECOND)
                .temperature(1440)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Lu/Tm:YVO? + C2H6O -> Lu/Tm:YVO + (NH4)2CO3 + C3H6
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .input(dust, LuTmDopedYttriumVanadateDeposition, 10)
                .fluidInputs(Ethanol.getFluid(1000))
                .output(dust, LuTmYVO)
                .output(dust, AmmoniumCarbonate, 14)
                .fluidOutputs(Propene.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

    }

}