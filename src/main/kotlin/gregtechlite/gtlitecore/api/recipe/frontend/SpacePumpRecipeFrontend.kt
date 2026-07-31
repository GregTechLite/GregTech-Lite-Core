package gregtechlite.gtlitecore.api.recipe.frontend

import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Butene
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chlorobenzene
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Deuterium
import gregtech.api.unification.material.Materials.DinitrogenTetroxide
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.EnderAir
import gregtech.api.unification.material.Materials.EnderPearl
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.FluoroantimonicAcid
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Glowstone
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Helium3
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Lava
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.LiquidAir
import gregtech.api.unification.material.Materials.LiquidEnderAir
import gregtech.api.unification.material.Materials.LiquidNetherAir
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Mercury
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Naphthalene
import gregtech.api.unification.material.Materials.NaturalGas
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.NetherAir
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Nitrochlorobenzene
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.NitrogenDioxide
import gregtech.api.unification.material.Materials.Oil
import gregtech.api.unification.material.Materials.OilHeavy
import gregtech.api.unification.material.Materials.OilLight
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Phenol
import gregtech.api.unification.material.Materials.PhthalicAcid
import gregtech.api.unification.material.Materials.Propene
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.RawOil
import gregtech.api.unification.material.Materials.SodiumBisulfate
import gregtech.api.unification.material.Materials.Styrene
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Toluene
import gregtech.api.unification.material.Materials.Tritium
import gregtech.api.unification.material.Materials.UraniumHexafluoride
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Xenon
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BedrockSmoke
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronTrifluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChlorinatedSolvents
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorobenzene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FormicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HexachloroplatinicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrobromicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hydroquinone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Indene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MethylamineMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resorcinol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SeaWater
import net.minecraftforge.fluids.FluidStack

object SpacePumpRecipeFrontend
{

    /**
     * Used to cache all recipes of Space Pump Modules, the left part `(Integer, Integer)` stored
     * the planet id and fluid id which will be set in configuration ui in the machine; the right part
     * stored the fluid stack which the recipe will output.
     *
     * We used inner [Pair] but not Kotlin [Pair] to resolve some possible conflicts in mixed
     * programming environment.
     */
    val RECIPES: MutableMap<Pair<Int, Int>, FluidStack> = openHashMapOf()

    fun init()
    {
        // T1
        RECIPES[Pair(1, 1)] = Water.getFluid(900_000)
        RECIPES[Pair(1, 2)] = Chlorobenzene.getFluid(224_000)
        RECIPES[Pair(1, 3)] = SulfuricAcid.getFluid(196_000)
        RECIPES[Pair(1, 4)] = Iron.getFluid(224_000)
        RECIPES[Pair(1, 5)] = RawOil.getFluid(350_000)
        RECIPES[Pair(1, 6)] = Oxygen.getFluid(700_000)
        RECIPES[Pair(1, 7)] = SeaWater.getFluid(700_000)
        RECIPES[Pair(1, 8)] = Air.getFluid(220_000)

        // T2
        RECIPES[Pair(2, 1)] = Oil.getFluid(350_000)
        RECIPES[Pair(2, 2)] = OilHeavy.getFluid(448_000)
        RECIPES[Pair(2, 3)] = OilLight.getFluid(195_000)
        RECIPES[Pair(2, 4)] = NaturalGas.getFluid(350_000)
        RECIPES[Pair(2, 5)] = Hydrogen.getFluid(550_000)
        RECIPES[Pair(2, 6)] = CarbonMonoxide.getFluid(1_120_000)
        RECIPES[Pair(2, 7)] = Helium.getFluid(700_000)
        RECIPES[Pair(2, 8)] = Lava.getFluid(450_000)

        // T3
        RECIPES[Pair(3, 1)] = Helium3.getFluid(312_000)
        RECIPES[Pair(3, 2)] = NetherAir.getFluid(220_000)
        RECIPES[Pair(3, 3)] = Deuterium.getFluid(392_000)
        RECIPES[Pair(3, 4)] = NitricAcid.getFluid(196_000)
        RECIPES[Pair(3, 5)] = Methane.getFluid(448_000)
        RECIPES[Pair(3, 6)] = Neon.getFluid(500_000)
        RECIPES[Pair(3, 7)] = EnderPearl.getFluid(160_000)
        RECIPES[Pair(3, 8)] = CarbonDioxide.getFluid(560_000)

        // T4
        RECIPES[Pair(4, 1)] = Copper.getFluid(224_000)
        RECIPES[Pair(4, 2)] = Ethylene.getFluid(300_000)
        RECIPES[Pair(4, 3)] = Ammonia.getFluid(160_000)
        RECIPES[Pair(4, 4)] = HydrofluoricAcid.getFluid(448_000)
        RECIPES[Pair(4, 5)] = EnderAir.getFluid(220_000)
        RECIPES[Pair(4, 6)] = Argon.getFluid(400_000)
        RECIPES[Pair(4, 7)] = Tritium.getFluid(392_000)
        RECIPES[Pair(4, 8)] = DistilledWater.getFluid(1_250_000)

        // T5
        RECIPES[Pair(5, 1)] = Nitrogen.getFluid(448_000)
        RECIPES[Pair(5, 2)] = Lead.getFluid(224_000)
        RECIPES[Pair(5, 3)] = HydrochloricAcid.getFluid(448_000)
        RECIPES[Pair(5, 4)] = Propene.getFluid(300_000)
        RECIPES[Pair(5, 5)] = Krypton.getFluid(300_000)
        RECIPES[Pair(5, 6)] = Toluene.getFluid(200_000)
        RECIPES[Pair(5, 7)] = LiquidAir.getFluid(220_000)
        RECIPES[Pair(5, 8)] = Chlorine.getFluid(370_000)

        // T6
        RECIPES[Pair(6, 1)] = Tin.getFluid(224_000)
        RECIPES[Pair(6, 2)] = Xenon.getFluid(200_000)
        RECIPES[Pair(6, 3)] = Fluorobenzene.getFluid(112_000)
        RECIPES[Pair(6, 4)] = SodiumBisulfate.getFluid(98_000)
        RECIPES[Pair(6, 5)] = LiquidNetherAir.getFluid(220_000)
        RECIPES[Pair(6, 6)] = Ethanol.getFluid(300_000)
        RECIPES[Pair(6, 7)] = Lubricant.getFluid(360_000)
        RECIPES[Pair(6, 8)] = Oxygen.getFluid(FluidStorageKeys.LIQUID, 700_000)

        // T7
        RECIPES[Pair(7, 1)] = FluoroantimonicAcid.getFluid(98_000)
        RECIPES[Pair(7, 2)] = Mercury.getFluid(224_000)
        RECIPES[Pair(7, 3)] = Glowstone.getFluid(165_000)
        RECIPES[Pair(7, 4)] = LiquidEnderAir.getFluid(220_000)
        RECIPES[Pair(7, 5)] = UraniumHexafluoride.getFluid(188_000)
        RECIPES[Pair(7, 6)] = Fluorine.getFluid(448_000)
        RECIPES[Pair(7, 7)] = AceticAcid.getFluid(160_000)
        RECIPES[Pair(7, 8)] = Radon.getFluid(150_000)

        // T8
        RECIPES[Pair(8, 1)] = Helium.getFluid(FluidStorageKeys.LIQUID, 1_400_000)
        RECIPES[Pair(8, 2)] = PhthalicAcid.getFluid(220_000)
        RECIPES[Pair(8, 3)] = Nickel.getFluid(224_000)
        RECIPES[Pair(8, 4)] = Styrene.getFluid(160_000)
        RECIPES[Pair(8, 5)] = Nitrochlorobenzene.getFluid(81_250)
        RECIPES[Pair(8, 6)] = Magnesium.getFluid(224_000)
        RECIPES[Pair(8, 7)] = MethylamineMixture.getFluid(198_000)
        RECIPES[Pair(8, 8)] = Phenol.getFluid(162_000)

        // T9
        RECIPES[Pair(9, 1)] = BedrockSmoke.getFluid(448_000)
        RECIPES[Pair(9, 2)] = NitrogenDioxide.getFluid(600_000)
        RECIPES[Pair(9, 3)] = Resorcinol.getFluid(162_000)
        RECIPES[Pair(9, 4)] = Hydroquinone.getFluid(162_000)
        RECIPES[Pair(9, 5)] = DinitrogenTetroxide.getFluid(300_000)
        RECIPES[Pair(9, 6)] = Manganese.getFluid(224_000)
        RECIPES[Pair(9, 7)] = HexachloroplatinicAcid.getFluid(120_000)
        RECIPES[Pair(9, 8)] = FormicAcid.getFluid(160_000)

        // T10
        RECIPES[Pair(10, 1)] = Naphthalene.getFluid(160_000)
        RECIPES[Pair(10, 2)] = Butene.getFluid(224_000)
        RECIPES[Pair(10, 3)] = HydrobromicAcid.getFluid(250_000)
        RECIPES[Pair(10, 4)] = Acetylene.getFluid(370_000)
        RECIPES[Pair(10, 5)] = Indene.getFluid(170_000)
        RECIPES[Pair(10, 6)] = BoronTrifluoride.getFluid(140_000)
        RECIPES[Pair(10, 7)] = ChlorinatedSolvents.getFluid(188_000)
        RECIPES[Pair(10, 8)] = Gallium.getFluid(224_000)
    }

}