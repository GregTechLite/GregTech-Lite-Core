package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.Acetone
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Ash
import gregtech.api.unification.material.Materials.Benzene
import gregtech.api.unification.material.Materials.Biomass
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Charcoal
import gregtech.api.unification.material.Materials.CharcoalByproducts
import gregtech.api.unification.material.Materials.Coal
import gregtech.api.unification.material.Materials.CoalTar
import gregtech.api.unification.material.Materials.Coke
import gregtech.api.unification.material.Materials.Creosote
import gregtech.api.unification.material.Materials.DarkAsh
import gregtech.api.unification.material.Materials.Dimethylbenzene
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Ethylbenzene
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.FermentedBiomass
import gregtech.api.unification.material.Materials.HydrogenSulfide
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.MethylAcetate
import gregtech.api.unification.material.Materials.Naphthalene
import gregtech.api.unification.material.Materials.OilHeavy
import gregtech.api.unification.material.Materials.Phenol
import gregtech.api.unification.material.Materials.Sugar
import gregtech.api.unification.material.Materials.Toluene
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.WoodGas
import gregtech.api.unification.material.Materials.WoodTar
import gregtech.api.unification.material.Materials.WoodVinegar
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.common.items.MetaItems.BIO_CHAFF
import gregtech.common.items.MetaItems.FERTILIZER
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.min
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.COMPLEX_PYROLYSIS_RECIPES
import gregtechlite.gtlitecore.api.s
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Guaiacol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hydroquinone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resorcinol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Xylenol

internal object ConcentratedCokingClusterRecipes
{
    // @formatter:off

    fun init()
    {
        // region Wood Chemistry

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            circuitMeta(1)
            input("logWood", 64)
            output(gem, Charcoal, 64)
            output(dust, Ash, 16)
            fluidOutputs(Creosote.getFluid(4000))
            fluidOutputs(CharcoalByproducts.getFluid(4000))
            fluidOutputs(WoodVinegar.getFluid(3000))
            fluidOutputs(WoodTar.getFluid(1500))
            fluidOutputs(WoodGas.getFluid(1500))
            fluidOutputs(OilHeavy.getFluid(200))
            EUt(VA[HV])
            duration(10.s)
            blastFurnaceTemp(2400)
        }

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            circuitMeta(2)
            input("logWood", 64)
            output(gem, Charcoal, 64)
            fluidOutputs(Phenol.getFluid(1000))
            fluidOutputs(Hydroquinone.getFluid(1500))
            fluidOutputs(Resorcinol.getFluid(1500))
            fluidOutputs(Xylenol.getFluid(2500))
            fluidOutputs(Guaiacol.getFluid(600))
            fluidOutputs(Lubricant.getFluid(400))
            EUt(VA[HV])
            duration(10.s)
            blastFurnaceTemp(2400)
        }

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            circuitMeta(3)
            input("logWood", 64)
            output(gem, Charcoal, 64)
            fluidOutputs(Methanol.getFluid(1200))
            fluidOutputs(AceticAcid.getFluid(400))
            fluidOutputs(Dimethylbenzene.getFluid(400))
            fluidOutputs(Acetone.getFluid(200))
            fluidOutputs(Ethanol.getFluid(40))
            fluidOutputs(MethylAcetate.getFluid(40))
            EUt(VA[HV])
            duration(10.s)
            blastFurnaceTemp(2400)
        }

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            circuitMeta(4)
            input("logWood", 64)
            output(gem, Charcoal, 64)
            fluidOutputs(Benzene.getFluid(1400))
            fluidOutputs(Methane.getFluid(520))
            fluidOutputs(Toluene.getFluid(300))
            fluidOutputs(Ethylene.getFluid(80))
            fluidOutputs(CarbonDioxide.getFluid(1960))
            fluidOutputs(CarbonMonoxide.getFluid(1360))
            EUt(VA[HV])
            duration(10.s)
            blastFurnaceTemp(2400)
        }

        // endregion

        // region Coal Chemistry

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            circuitMeta(1)
            input(gem, Coal, 64)
            output(gem, Coke, 64)
            fluidOutputs(Creosote.getFluid(32000))
            fluidOutputs(CarbonDioxide.getFluid(1000))
            EUt(VA[HV])
            duration(10.s)
            blastFurnaceTemp(2600)
        }

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            circuitMeta(1)
            input(gem, Charcoal, 64)
            output(gem, Coke, 64)
            fluidOutputs(Creosote.getFluid(32000))
            fluidOutputs(CarbonDioxide.getFluid(1000))
            EUt(VA[HV])
            duration(10.s)
            blastFurnaceTemp(2600)
        }

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            circuitMeta(2)
            input(block, Coal, 64)
            output(block, Coke, 64)
            fluidOutputs(Creosote.getFluid(288000))
            fluidOutputs(CarbonDioxide.getFluid(9000))
            EUt(VA[HV])
            duration(1.min + 30.s)
            blastFurnaceTemp(2600)
        }

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            circuitMeta(2)
            input(block, Charcoal, 64)
            output(block, Coke, 64)
            fluidOutputs(Creosote.getFluid(288000))
            fluidOutputs(CarbonDioxide.getFluid(9000))
            EUt(VA[HV])
            duration(1.min + 30.s)
            blastFurnaceTemp(2600)
        }

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            circuitMeta(3)
            input(gem, Coal, 64)
            output(gem, Coke, 64)
            output(dust, DarkAsh, 16)
            fluidOutputs(CoalTar.getFluid(2000))
            fluidOutputs(Naphthalene.getFluid(1600))
            fluidOutputs(HydrogenSulfide.getFluid(1200))
            fluidOutputs(Ammonia.getFluid(1200))
            fluidOutputs(Ethylbenzene.getFluid(1000))
            fluidOutputs(Phenol.getFluid(400))
            EUt(VA[HV])
            duration(10.s)
            blastFurnaceTemp(2600)
        }

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            circuitMeta(3)
            input(gem, Charcoal, 64)
            output(gem, Coke, 64)
            output(dust, DarkAsh, 16)
            fluidOutputs(CoalTar.getFluid(2000))
            fluidOutputs(Naphthalene.getFluid(1600))
            fluidOutputs(HydrogenSulfide.getFluid(1200))
            fluidOutputs(Ammonia.getFluid(1200))
            fluidOutputs(Ethylbenzene.getFluid(1000))
            fluidOutputs(Phenol.getFluid(400))
            EUt(VA[HV])
            duration(10.s)
            blastFurnaceTemp(2600)
        }

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            circuitMeta(4)
            input(block, Coal, 64)
            output(block, Coke, 64)
            output(dust, DarkAsh, 64)
            fluidOutputs(CoalTar.getFluid(18000))
            fluidOutputs(Naphthalene.getFluid(14400))
            fluidOutputs(HydrogenSulfide.getFluid(10800))
            fluidOutputs(Ammonia.getFluid(10800))
            fluidOutputs(Ethylbenzene.getFluid(9000))
            fluidOutputs(Phenol.getFluid(3600))
            EUt(VA[HV])
            duration(1.min + 30.s)
            blastFurnaceTemp(2600)
        }

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            circuitMeta(4)
            input(block, Charcoal, 64)
            output(block, Coke, 64)
            output(dust, DarkAsh, 64)
            fluidOutputs(CoalTar.getFluid(18000))
            fluidOutputs(Naphthalene.getFluid(14400))
            fluidOutputs(HydrogenSulfide.getFluid(10800))
            fluidOutputs(Ammonia.getFluid(10800))
            fluidOutputs(Ethylbenzene.getFluid(9000))
            fluidOutputs(Phenol.getFluid(3600))
            EUt(VA[HV])
            duration(1.min + 30.s)
            blastFurnaceTemp(2600)
        }

        // endregion

        // region Bio Chemistry

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            input(BIO_CHAFF, 64)
            fluidInputs(Water.getFluid(96000))
            output(FERTILIZER, 64)
            fluidOutputs(Biomass.getFluid(80000))
            fluidOutputs(FermentedBiomass.getFluid(96000))
            EUt(VA[HV])
            duration(10.s)
            blastFurnaceTemp(1200)
        }

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            input(dust, Sugar, 64)
            input(dust, Sugar, 14)
            output(dust, Charcoal, 48)
            fluidOutputs(Water.getFluid(4500))
            EUt(VA[HV])
            duration(10.s)
            blastFurnaceTemp(500)
        }

        COMPLEX_PYROLYSIS_RECIPES.addRecipe {
            input(block, Sugar, 64)
            input(block, Sugar, 14)
            output(block, Charcoal, 48)
            fluidOutputs(Water.getFluid(40500))
            EUt(VA[HV])
            duration(90.s)
            blastFurnaceTemp(500)
        }

        // endregion
    }

    // @formatter:on
}