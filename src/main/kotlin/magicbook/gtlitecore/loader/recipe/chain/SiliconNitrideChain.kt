package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Chloromethane
import gregtech.api.unification.material.Materials.Ethane
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.common.items.MetaItems.SHAPE_MOLD_PLATE
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.PLASMA_CVD_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicSiliconNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HexagonalSiliconNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MalonicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Methyltrichlorosilane
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class SiliconNitrideChain
{

    companion object
    {

        fun init()
        {
            // Si + 3CH3Cl-> Si(CH3)Cl3 + C2H6
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Silicon)
                .fluidInputs(Chloromethane.getFluid(3000))
                .fluidOutputs(Methyltrichlorosilane.getFluid(1000))
                .fluidOutputs(Ethane.getFluid(1000))
                .EUt(96) // MV
                .duration(12 * SECOND)
                .buildAndRegister()

            // 3Si(CH3)Cl3 + 4HNO3 -> h-Si3N4 + 9HCl + C3H4O4 + 8O
            PLASMA_CVD_RECIPES.recipeBuilder()
                .notConsumable(plate, Thallium)
                .notConsumable(SHAPE_MOLD_PLATE)
                .fluidInputs(Methyltrichlorosilane.getFluid(3000))
                .fluidInputs(NitricAcid.getFluid(4000))
                .fluidInputs(Argon.getPlasma(10000))
                .output(plate, HexagonalSiliconNitride)
                .output(dust, MalonicAcid, 11)
                .fluidOutputs(HydrochloricAcid.getFluid(9000))
                .fluidOutputs(Oxygen.getFluid(8000))
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .temperature(1250)
                .buildAndRegister()

            // h-Si3N4 -> c-Si3N4
            CVD_RECIPES.recipeBuilder()
                .input(dust, HexagonalSiliconNitride)
                .output(gem, CubicSiliconNitride)
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .temperature(3501)
                .buildAndRegister()
        }

    }

}