package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES
import gregtech.api.recipes.RecipeMaps.POLARIZER_RECIPES
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UEV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UHV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UIV
import gregtech.common.items.MetaItems.SHAPE_MOLD_BALL
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ELECTRIC_IMPLOSION_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.STELLAR_FORGE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FreeElectronGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NeutronProtonFermiSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks.LEPTONIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks.QUANTUM_CHROMODYNAMIC_CHARGE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_LARGE_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BOSE_EINSTEIN_CONDENSATE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_EXOTIC_MATTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_HIGH_DENSITY_PROTONIC_MATTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_KERR_SINGULARITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_KN_SINGULARITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_RN_SINGULARITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EIGENFOLDED_SPACETIME_MANIFOLD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MACROWORMHOLE_GENERATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICROWORMHOLE_GENERATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NEUTRONIUM_SPHERE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RECURSIVELY_FOLDED_NEGATIVE_SPACE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.STABILIZED_WORMHOLE_GENERATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TIME_DILATION_CONTAINMENT_CELL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TRIPLET_NEUTRONIUM_SPHERE
import net.minecraft.item.ItemStack

internal object BlackHolesChain
{
    // @formatter:off

    fun init()
    {
        // Time Dilation Containment Cell
        AUTOCLAVE_RECIPES.recipeBuilder()
            .input(BOSE_EINSTEIN_CONDENSATE)
            .input(nanite, MagMatter)
            .fluidInputs(SpaceTime.getFluid(L * 4))
            .output(TIME_DILATION_CONTAINMENT_CELL)
            .fluidOutputs(Rubidium.getPlasma(L * 8))
            .EUt(VA[UXV])
            .duration(2 * TICK)
            .buildAndRegister()

        // Neutronium Sphere
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_BALL)
            .fluidInputs(Neutronium.getFluid(L))
            .output(NEUTRONIUM_SPHERE)
            .EUt(VA[UHV])
            .duration(1 * TICK)
            .buildAndRegister()

        // High Density Protonic Matter Containment Cell
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(TIME_DILATION_CONTAINMENT_CELL)
            .input(NEUTRONIUM_SPHERE, 8)
            .fluidInputs(NeutronProtonFermiSuperfluid.getFluid(16000))
            .output(CONTAINED_HIGH_DENSITY_PROTONIC_MATTER)
            .EUt(VA[UIV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // Exotic Matter Containment Cell
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .input(CONTAINED_HIGH_DENSITY_PROTONIC_MATTER, 2)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .output(CONTAINED_EXOTIC_MATTER)
            .output(TIME_DILATION_CONTAINMENT_CELL)
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Triplet Neutronium Sphere
        POLARIZER_RECIPES.recipeBuilder()
            .input(NEUTRONIUM_SPHERE)
            .fluidInputs(Magnetium.getFluid(L * 4))
            .output(TRIPLET_NEUTRONIUM_SPHERE)
            .EUt(VA[UEV])
            .duration(2 * TICK)
            .buildAndRegister()

        // Contained RN (Reissner-Nordstrom) Black Hole Singularity
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .input(TIME_DILATION_CONTAINMENT_CELL)
            .input(TRIPLET_NEUTRONIUM_SPHERE, 16)
            .inputs(ItemStack(LEPTONIC_CHARGE))
            .output(CONTAINED_RN_SINGULARITY)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Contained KN (Kerr-Newmann) Black Hole Singularity
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .input(CONTAINED_RN_SINGULARITY, 2)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .output(CONTAINED_KN_SINGULARITY)
            .output(TIME_DILATION_CONTAINMENT_CELL)
            .EUt(VA[UXV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // Contained Kerr Singularity
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(CONTAINED_KN_SINGULARITY)
            .fluidInputs(Mellion.getFluid(L / 8))
            .output(CONTAINED_KERR_SINGULARITY)
            .fluidOutputs(FreeElectronGas.getFluid(16000))
            .EUt(VA[OpV])
            .duration(1 * TICK)
            .buildAndRegister()

        // Microwormhole Generator
        SPACE_ASSEMBLER_RECIPES.recipeBuilder()
            .input(CONTAINED_KERR_SINGULARITY)
            .input(FIELD_GENERATOR_UHV)
            .input(plate, CosmicNeutronium, 2)
            .input(screw, Taranium, 4)
            .fluidInputs(SpaceTime.getFluid(L))
            .output(MICROWORMHOLE_GENERATOR)
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .tier(3)
            .buildAndRegister()

        // Macrowormhole Generator
        SPACE_ASSEMBLER_RECIPES.recipeBuilder()
            .input(CONTAINED_KERR_SINGULARITY)
            .input(FIELD_GENERATOR_UEV)
            .input(plate, Infinity, 4)
            .input(plate, HeavyQuarkDegenerateMatter, 8)
            .input(CONTAINED_HIGH_DENSITY_PROTONIC_MATTER)
            .input(BATTERY_HULL_LARGE_NEUTRONIUM)
            .input(screw, Shirabon, 16)
            .fluidInputs(SpaceTime.getFluid(L * 4))
            .output(MACROWORMHOLE_GENERATOR)
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .tier(4)
            .buildAndRegister()

        // Stabilized Wormhole Generator
        SPACE_ASSEMBLER_RECIPES.recipeBuilder()
            .input(MACROWORMHOLE_GENERATOR)
            .input(CONTAINED_KN_SINGULARITY, 2)
            .input(FIELD_GENERATOR_UIV)
            .input(plate, Mellion, 8)
            .input(plate, QuantumchromodynamicallyConfinedMatter, 16)
            .input(CONTAINED_EXOTIC_MATTER)
            .input(rotor, Creon, 6)
            .input(rotor, Hypogen, 6)
            .input(wireGtSingle, VibraniumTritaniumActiniumIronSuperhydride, 16)
            .input(screw, TranscendentMetal, 32)
            .fluidInputs(SpaceTime.getFluid(L * 16))
            .output(STABILIZED_WORMHOLE_GENERATOR)
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .tier(5)
            .buildAndRegister()

        // Recursively Folded Negative Space
        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(MICROWORMHOLE_GENERATOR)
            .output(RECURSIVELY_FOLDED_NEGATIVE_SPACE, 2)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(MACROWORMHOLE_GENERATOR)
            .output(RECURSIVELY_FOLDED_NEGATIVE_SPACE, 16)
            .EUt(VA[UXV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // Eigenfolded Spacetime Manifold
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .input(STABILIZED_WORMHOLE_GENERATOR)
            .input(RECURSIVELY_FOLDED_NEGATIVE_SPACE)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .output(EIGENFOLDED_SPACETIME_MANIFOLD)
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on
}