package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.LithiumChloride
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.wireFine
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumAcetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumLoadedRutileNanoparticles
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuccinicAcid
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class NanoparticlesChain
{

    companion object
    {

        fun init()
        {
            // Palladium Loaded Rutile Nanoparticles

            // Pd(CH3COOH)2 + 3Li2TiO3 + 2HCl + H2O -> Pd(TiO2) + C4H6O4 + 2LiCl
            CVD_RECIPES.recipeBuilder()
                .input(dust, PalladiumAcetate, 15)
                .input(wireFine, LithiumTitanate, 24)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .output(dust, PalladiumLoadedRutileNanoparticles, 4)
                .output(dust, SuccinicAcid, 14)
                .output(dust, LithiumChloride, 4)
                .EUt(VA[EV].toLong())
                .duration(10 * TICK)
                .temperature(684)
                .buildAndRegister()

        }

    }

}