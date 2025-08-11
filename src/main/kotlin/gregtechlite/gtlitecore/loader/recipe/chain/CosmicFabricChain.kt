package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT
import gregtech.common.items.MetaItems.SHAPE_MOLD_PLATE
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.STELLAR_FORGE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumFullereneNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NeutroniumDopedCarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SeaborgiumDopedCarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks.QUANTUM_CHROMODYNAMIC_CHARGE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HIGHLY_DENSE_POLYMER_PLATE
import net.minecraft.item.ItemStack

internal object CosmicFabricChain
{

    // @formatter:off

    fun init()
    {
        // Highly Dense Polymer Plate
        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(plate, FullerenePolymerMatrix, 3)
            .input(plate, TranscendentMetal, 3)
            .input(plate, NeutroniumDopedCarbonNanotube, 3)
            .input(plate, CosmicNeutronium, 3)
            .input(plate, SeaborgiumDopedCarbonNanotube, 3)
            .input(plate, LanthanumFullereneNanotube, 3)
            .output(HIGHLY_DENSE_POLYMER_PLATE, 3)
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Highly Dense Polymer Plate -> Cosmic Fabric (plasma)
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(HIGHLY_DENSE_POLYMER_PLATE)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidOutputs(CosmicFabric.getPlasma(1000))
            .EUt(VA[UXV])
            .duration(30 * SECOND)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(HIGHLY_DENSE_POLYMER_PLATE, 64)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidOutputs(CosmicFabric.getPlasma(64000))
            .EUt(VA[OpV])
            .duration(7 * SECOND + 10 * TICK)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(HIGHLY_DENSE_POLYMER_PLATE, 64)
            .input(HIGHLY_DENSE_POLYMER_PLATE, 64)
            .input(HIGHLY_DENSE_POLYMER_PLATE, 64)
            .input(HIGHLY_DENSE_POLYMER_PLATE, 64)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidOutputs(CosmicFabric.getPlasma(256000))
            .EUt(VA[MAX])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Cosmic Fabric (plasma) -> Cosmic Fabric (liquid)
        VACUUM_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(CosmicFabric.getPlasma(1000))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
            .fluidOutputs(CosmicFabric.getFluid(L))
            .fluidOutputs(Helium.getFluid(500))
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        VACUUM_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .fluidInputs(CosmicFabric.getPlasma(1000))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
            .output(dust, CosmicFabric)
            .fluidOutputs(Helium.getFluid(500))
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        VACUUM_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .fluidInputs(CosmicFabric.getPlasma(1000))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
            .output(dustSmall, CosmicFabric, 4)
            .fluidOutputs(Helium.getFluid(500))
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        VACUUM_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .fluidInputs(CosmicFabric.getPlasma(1000))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
            .output(dustTiny, CosmicFabric, 9)
            .fluidOutputs(Helium.getFluid(500))
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        VACUUM_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_INGOT)
            .fluidInputs(CosmicFabric.getPlasma(1000))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
            .output(ingot, CosmicFabric)
            .fluidOutputs(Helium.getFluid(500))
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        VACUUM_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_PLATE)
            .fluidInputs(CosmicFabric.getPlasma(1000))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
            .output(plate, CosmicFabric)
            .fluidOutputs(Helium.getFluid(500))
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}