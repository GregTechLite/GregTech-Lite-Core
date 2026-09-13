package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.common.items.MetaItems.FIELD_GENERATOR_LuV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_OpV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UEV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UHV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UIV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UXV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_ZPM
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_CHEST
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_TANK
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Abyssalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ArceusAlloy2B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IncoloyMA813
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ReneN5
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ToxicAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TransitionAlloyA
import gregtechlite.gtlitecore.common.block.variant.QuantumStorageUnit
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FIELD_GENERATOR_MAX

internal object QuantumStorageUnitRecipes
{
    // @formatter:off

    fun init()
    {
        // T1
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(18)
            input(QUANTUM_CHEST[IV])
            input(QUANTUM_TANK[IV])
            input(FIELD_GENERATOR_LuV)
            input(plateDouble, IncoloyMA813, 4)
            input(rotor, IncoloyMA813, 2)
            input(wireGtSingle, Tungsten, 8)
            fluidInputs(SolderingAlloy.getFluid(L * 4))
            outputs(QuantumStorageUnit.T1.stack)
            EUt(VA[LuV])
            duration(10 * SECOND)
        }

        // T2
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(18)
            input(QUANTUM_CHEST[IV], 2)
            input(QUANTUM_TANK[IV], 2)
            input(FIELD_GENERATOR_ZPM)
            input(plateDouble, ReneN5, 4)
            input(rotor, ReneN5, 2)
            input(wireGtSingle, Osmium, 8)
            fluidInputs(SolderingAlloy.getFluid(L * 8))
            outputs(QuantumStorageUnit.T2.stack)
            EUt(VA[ZPM])
            duration(10 * SECOND)
        }

        // T3
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(18)
            input(QUANTUM_CHEST[LuV], 2)
            input(QUANTUM_CHEST[LuV], 2)
            input(FIELD_GENERATOR_UV)
            input(plateDouble, TransitionAlloyA, 4)
            input(rotor, TransitionAlloyA, 2)
            input(wireGtSingle, Naquadah, 8)
            fluidInputs(SolderingAlloy.getFluid(L * 16))
            outputs(QuantumStorageUnit.T3.stack)
            EUt(VA[UV])
            duration(10 * SECOND)
        }

        // T4
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(18)
            input(QUANTUM_CHEST[LuV], 4)
            input(QUANTUM_CHEST[LuV], 4)
            input(FIELD_GENERATOR_UHV)
            input(plateDouble, Pikyonium64B, 4)
            input(rotor, Pikyonium64B, 2)
            input(wireGtSingle, Tritanium, 8)
            fluidInputs(SolderingAlloy.getFluid(L * 32))
            outputs(QuantumStorageUnit.T4.stack)
            EUt(VA[UHV])
            duration(10 * SECOND)
        }

        // T5
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(18)
            input(QUANTUM_CHEST[ZPM], 4)
            input(QUANTUM_CHEST[ZPM], 4)
            input(FIELD_GENERATOR_UEV)
            input(plateDouble, ToxicAlloy, 4)
            input(rotor, ToxicAlloy, 2)
            input(wireGtSingle, Bedrockium, 8)
            fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            outputs(QuantumStorageUnit.T5.stack)
            EUt(VA[UEV])
            duration(10 * SECOND)
        }

        // T6
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(18)
            input(QUANTUM_CHEST[ZPM], 8)
            input(QUANTUM_CHEST[ZPM], 8)
            input(FIELD_GENERATOR_UIV)
            input(plateDouble, ArceusAlloy2B, 4)
            input(rotor, ArceusAlloy2B, 2)
            input(wireGtSingle, MetastableOganesson, 8)
            fluidInputs(MutatedLivingSolder.getFluid(L * 128))
            outputs(QuantumStorageUnit.T6.stack)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        // T7
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(18)
            input(QUANTUM_CHEST[UV], 8)
            input(QUANTUM_TANK[UV], 8)
            input(FIELD_GENERATOR_UXV)
            input(plateDouble, Abyssalloy, 4)
            input(rotor, Abyssalloy, 2)
            input(wireGtSingle, Hypogen, 8)
            fluidInputs(MutatedLivingSolder.getFluid(L * 256))
            outputs(QuantumStorageUnit.T7.stack)
            EUt(VA[UXV])
            duration(10 * SECOND)
        }

        // T8
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(18)
            input(QUANTUM_CHEST[UV], 16)
            input(QUANTUM_CHEST[UV], 16)
            input(FIELD_GENERATOR_OpV)
            input(plateDouble, Mellion, 4)
            input(rotor, Mellion, 2)
            input(wireGtSingle, SpaceTime, 8)
            fluidInputs(MutatedLivingSolder.getFluid(L * 512))
            outputs(QuantumStorageUnit.T8.stack)
            EUt(VA[OpV])
            duration(10 * SECOND)
        }

        // T9
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(18)
            input(QUANTUM_CHEST[UHV], 16)
            input(QUANTUM_CHEST[UHV], 16)
            input(FIELD_GENERATOR_MAX)
            input(plateDouble, Periodicium, 4)
            input(rotor, Periodicium, 2)
            input(wireGtSingle, Eternity, 8)
            fluidInputs(MutatedLivingSolder.getFluid(L * 1024))
            outputs(QuantumStorageUnit.T9.stack)
            EUt(VA[MAX])
            duration(10 * SECOND)
        }
    }

    // @formatter:on
}