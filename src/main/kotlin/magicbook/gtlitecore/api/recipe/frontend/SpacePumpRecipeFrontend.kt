package magicbook.gtlitecore.api.recipe.frontend

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
import it.unimi.dsi.fastutil.objects.Object2ObjectMap
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Acetylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BoronTrifluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChlorinatedSolvents
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorobenzene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FormicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HexachloroplatinicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrobromicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hydroquinone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Indene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MethylamineMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resorcinol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SeaWater
import magicbook.gtlitecore.api.utils.tuples.Pair
import net.minecraftforge.fluids.FluidStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class SpacePumpRecipeFrontend
{

    companion object
    {
        /**
         * Used to cache all recipes of Space Pump Modules, the left part `(Integer, Integer)` stored
         * the planet id and fluid id which will be set in configuration ui in the machine; the right part
         * stored the fluid stack which the recipe will output.
         *
         * We used inner [Pair] but not Kotlin [kotlin.Pair] to resolve some possible conflicts in mixed
         * programming environment.
         */
        @JvmField
        val RECIPES: Object2ObjectMap<Pair<Int, Int>, FluidStack> = Object2ObjectOpenHashMap()

        @JvmStatic
        fun init()
        {
            // T1
            RECIPES[Pair.of(1, 1)] = Water.getFluid(3_600_000)
            RECIPES[Pair.of(1, 2)] = Chlorobenzene.getFluid(896_000)
            RECIPES[Pair.of(1, 3)] = SulfuricAcid.getFluid(784_000)
            RECIPES[Pair.of(1, 4)] = Iron.getFluid(896_000)
            RECIPES[Pair.of(1, 5)] = RawOil.getFluid(1_400_000)
            RECIPES[Pair.of(1, 6)] = Oxygen.getFluid(2_800_000)
            RECIPES[Pair.of(1, 7)] = SeaWater.getFluid(2_800_000)
            RECIPES[Pair.of(1, 8)] = Air.getFluid(875_000)

            // T2
            RECIPES[Pair.of(2, 1)] = Oil.getFluid(1_400_000)
            RECIPES[Pair.of(2, 2)] = OilHeavy.getFluid(1_792_000)
            RECIPES[Pair.of(2, 3)] = OilLight.getFluid(780_000)
            RECIPES[Pair.of(2, 4)] = NaturalGas.getFluid(1_400_000)
            RECIPES[Pair.of(2, 5)] = Hydrogen.getFluid(2_200_000)
            RECIPES[Pair.of(2, 6)] = CarbonMonoxide.getFluid(4_480_000)
            RECIPES[Pair.of(2, 7)] = Helium.getFluid(2_800_000)
            RECIPES[Pair.of(2, 8)] = Lava.getFluid(1_800_000)

            // T3
            RECIPES[Pair.of(3, 1)] = Helium3.getFluid(1_250_000)
            RECIPES[Pair.of(3, 2)] = NetherAir.getFluid(875_000)
            RECIPES[Pair.of(3, 3)] = Deuterium.getFluid(1_568_000)
            RECIPES[Pair.of(3, 4)] = NitricAcid.getFluid(784_000)
            RECIPES[Pair.of(3, 5)] = Methane.getFluid(1_792_000)
            RECIPES[Pair.of(3, 6)] = Neon.getFluid(2_000_000)
            RECIPES[Pair.of(3, 7)] = EnderPearl.getFluid(640_000)
            RECIPES[Pair.of(3, 8)] = CarbonDioxide.getFluid(2_240_000)

            // T4
            RECIPES[Pair.of(4, 1)] = Copper.getFluid(896_000)
            RECIPES[Pair.of(4, 2)] = Ethylene.getFluid(1_200_000)
            RECIPES[Pair.of(4, 3)] = Ammonia.getFluid(640_000)
            RECIPES[Pair.of(4, 4)] = HydrofluoricAcid.getFluid(1_792_000)
            RECIPES[Pair.of(4, 5)] = EnderAir.getFluid(875_000)
            RECIPES[Pair.of(4, 6)] = Argon.getFluid(1_600_000)
            RECIPES[Pair.of(4, 7)] = Tritium.getFluid(1_568_000)
            RECIPES[Pair.of(4, 8)] = DistilledWater.getFluid(5_000_000)

            // T5
            RECIPES[Pair.of(5, 1)] = Nitrogen.getFluid(1_792_000)
            RECIPES[Pair.of(5, 2)] = Lead.getFluid(896_000)
            RECIPES[Pair.of(5, 3)] = HydrochloricAcid.getFluid(1_792_000)
            RECIPES[Pair.of(5, 4)] = Propene.getFluid(1_200_000)
            RECIPES[Pair.of(5, 5)] = Krypton.getFluid(1_200_000)
            RECIPES[Pair.of(5, 6)] = Toluene.getFluid(800_000)
            RECIPES[Pair.of(5, 7)] = LiquidAir.getFluid(875_000)
            RECIPES[Pair.of(5, 8)] = Chlorine.getFluid(1_480_000)

            // T6
            RECIPES[Pair.of(6, 1)] = Tin.getFluid(896_000)
            RECIPES[Pair.of(6, 2)] = Xenon.getFluid(800_000)
            RECIPES[Pair.of(6, 3)] = Fluorobenzene.getFluid(448_000)
            RECIPES[Pair.of(6, 4)] = SodiumBisulfate.getFluid(392_000)
            RECIPES[Pair.of(6, 5)] = LiquidNetherAir.getFluid(875_000)
            RECIPES[Pair.of(6, 6)] = Ethanol.getFluid(1_200_000)
            RECIPES[Pair.of(6, 7)] = Lubricant.getFluid(1_440_000)
            RECIPES[Pair.of(6, 8)] = Oxygen.getFluid(FluidStorageKeys.LIQUID, 2_800_000)

            // T7
            RECIPES[Pair.of(7, 1)] = FluoroantimonicAcid.getFluid(392_000)
            RECIPES[Pair.of(7, 2)] = Mercury.getFluid(896_000)
            RECIPES[Pair.of(7, 3)] = Glowstone.getFluid(660_000)
            RECIPES[Pair.of(7, 4)] = LiquidEnderAir.getFluid(875_000)
            RECIPES[Pair.of(7, 5)] = UraniumHexafluoride.getFluid(750_000)
            RECIPES[Pair.of(7, 6)] = Fluorine.getFluid(1_792_000)
            RECIPES[Pair.of(7, 7)] = AceticAcid.getFluid(640_000)
            RECIPES[Pair.of(7, 8)] = Radon.getFluid(600_000)

            // T8
            RECIPES[Pair.of(8, 1)] = Helium.getFluid(FluidStorageKeys.LIQUID, 5_600_000)
            RECIPES[Pair.of(8, 2)] = PhthalicAcid.getFluid(875_000)
            RECIPES[Pair.of(8, 3)] = Nickel.getFluid(896_000)
            RECIPES[Pair.of(8, 4)] = Styrene.getFluid(640_000)
            RECIPES[Pair.of(8, 5)] = Nitrochlorobenzene.getFluid(325_000)
            RECIPES[Pair.of(8, 6)] = Magnesium.getFluid(896_000)
            RECIPES[Pair.of(8, 7)] = MethylamineMixture.getFluid(792_000)
            RECIPES[Pair.of(8, 8)] = Phenol.getFluid(650_000)

            // T9
            RECIPES[Pair.of(9, 1)] = BedrockSmoke.getFluid(1_792_000)
            RECIPES[Pair.of(9, 2)] = NitrogenDioxide.getFluid(2_400_000)
            RECIPES[Pair.of(9, 3)] = Resorcinol.getFluid(650_000)
            RECIPES[Pair.of(9, 4)] = Hydroquinone.getFluid(650_000)
            RECIPES[Pair.of(9, 5)] = DinitrogenTetroxide.getFluid(1_200_000)
            RECIPES[Pair.of(9, 6)] = Manganese.getFluid(896_000)
            RECIPES[Pair.of(9, 7)] = HexachloroplatinicAcid.getFluid(480_000)
            RECIPES[Pair.of(9, 8)] = FormicAcid.getFluid(640_000)

            // T10
            RECIPES[Pair.of(10, 1)] = Naphthalene.getFluid(640_000)
            RECIPES[Pair.of(10, 2)] = Butene.getFluid(896_000)
            RECIPES[Pair.of(10, 3)] = HydrobromicAcid.getFluid(1_000_000)
            RECIPES[Pair.of(10, 4)] = Acetylene.getFluid(1_480_000)
            RECIPES[Pair.of(10, 5)] = Indene.getFluid(680_000)
            RECIPES[Pair.of(10, 6)] = BoronTrifluoride.getFluid(560_000)
            RECIPES[Pair.of(10, 7)] = ChlorinatedSolvents.getFluid(750_000)
            RECIPES[Pair.of(10, 8)] = Gallium.getFluid(896_000)
        }

    }

}