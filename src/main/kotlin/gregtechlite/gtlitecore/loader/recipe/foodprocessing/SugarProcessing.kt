package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.recipes.RecipeMaps.MACERATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Sugar
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.common.items.MetaItems.SHAPE_MOLD_PLATE
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SLICER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AppleCaneSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AppleSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CaneSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fructose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Glucose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HardAppleCandySyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OxalicAcid
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexanediol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SacchariaAcid
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HARD_APPLE_CANDY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_FLAT
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.APPLE_PULP
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.HARD_APPLE_CANDY_CHUNK
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.HARD_APPLE_CANDY_DUST
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.HARD_APPLE_CANDY_PLATE
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object SugarProcessing
{

    // @formatter:off

    fun init()
    {
        // Sugar (C2(H2O)5O25) -> Glucose (C6H12O6) + Fructose (C6H12O6)
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, Sugar, 48)
            .fluidInputs(Water.getFluid(1000))
            .output(dust, Glucose, 24)
            .output(dust, Fructose, 24)
            .EUt(VA[HV])
            .duration(15 * SECOND)
            .buildAndRegister()

        // C6H12O6 + 9O -> 3C2H2O4 + 3H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Glucose, 24)
            .fluidInputs(Oxygen.getFluid(9000))
            .fluidOutputs(OxalicAcid.getFluid(3000))
            .fluidOutputs(Water.getFluid(3000))
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // C6H12O6 + 3O -> C6H10O8 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Glucose, 24)
            .fluidInputs(Oxygen.getFluid(3000))
            .output(dust, SacchariaAcid, 24)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // C6H12O6 + H2O -> C6H14O2 + 5O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Fructose, 24)
            .fluidInputs(Water.getFluid(1000))
            .fluidOutputs(Hexanediol.getFluid(1000))
            .fluidOutputs(Oxygen.getFluid(5000))
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Apple sugar chain.

        // Apple -> Apple Pulp
        MACERATOR_RECIPES.recipeBuilder()
            .inputs(ItemStack(Items.APPLE))
            .outputs(APPLE_PULP.getItemStack(2))
            .chancedOutput(APPLE_PULP.itemStack, 3000, 0)
            .chancedOutput(APPLE_PULP.itemStack, 1500, 0)
            .EUt(4) // ULV
            .duration(4 * SECOND)
            .buildAndRegister()

        // Apple Pulp -> Apple Syrup
        MIXER_RECIPES.recipeBuilder()
            .inputs(APPLE_PULP.getItemStack(4))
            .input(dust, Sugar, 9)
            .fluidInputs(Water.getFluid(1000))
            .fluidOutputs(AppleSyrup.getFluid(2000))
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        MIXER_RECIPES.recipeBuilder()
            .inputs(APPLE_PULP.getItemStack(4))
            .input(dust, Sugar, 9)
            .fluidInputs(DistilledWater.getFluid(1000))
            .fluidOutputs(AppleSyrup.getFluid(2000))
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Cane Syrup
        FERMENTING_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .inputs(ItemStack(Items.REEDS))
            .fluidInputs(Water.getFluid(250))
            .fluidOutputs(CaneSyrup.getFluid(100))
            .EUt(VA[LV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // Apple-Cane Syrup
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(AppleSyrup.getFluid(500))
            .fluidInputs(CaneSyrup.getFluid(500))
            .fluidOutputs(AppleCaneSyrup.getFluid(1000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Apple-Cane Syrup -> Hard Apple Candy Syrup
        FLUID_HEATER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(AppleCaneSyrup.getFluid(200))
            .fluidOutputs(HardAppleCandySyrup.getFluid(100))
            .EUt(VA[LV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Hard Apple Candy Syrup -> Hard Apple Candy Chunk
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_PLATE)
            .fluidInputs(HardAppleCandySyrup.getFluid(1000))
            .outputs(HARD_APPLE_CANDY_CHUNK.itemStack)
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Hard Apple Candy Chunk -> Hard Apple Candy Plate
        SLICER_RECIPES.recipeBuilder()
            .notConsumable(SLICER_BLADE_FLAT)
            .inputs(HARD_APPLE_CANDY_CHUNK.itemStack)
            .outputs(HARD_APPLE_CANDY_PLATE.getItemStack(9))
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Hard Apple Candy Plate -> Hard Apple Candy
        ROASTER_RECIPES.recipeBuilder()
            .inputs(HARD_APPLE_CANDY_PLATE.itemStack)
            .output(HARD_APPLE_CANDY)
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Hard Apple Candy -> Hard Apple Candy Dust
        MACERATOR_RECIPES.recipeBuilder()
            .input(HARD_APPLE_CANDY)
            .outputs(HARD_APPLE_CANDY_DUST.getItemStack(2))
            .EUt(4) // ULV
            .duration(12 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}