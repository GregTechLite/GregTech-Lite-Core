package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MACERATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Biomass
import gregtech.api.unification.material.Materials.Bone
import gregtech.api.unification.material.Materials.Chloroform
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Meat
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.TricalciumPhosphate
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.common.items.MetaItems.SHAPE_MOLD_BALL
import gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fat
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mud
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OliveOil
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MUD_BALL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OLIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.ANIMAL_FAT
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object AnimalFatProcessing
{

    // @formatter:off

    fun init()
    {
        // Olive oil
        EXTRACTOR_RECIPES.recipeBuilder()
            .input(OLIVE)
            .fluidOutputs(OliveOil.getFluid(10))
            .EUt(2) // ULV
            .duration(1 * SECOND + 12 * TICK)
            .buildAndRegister()

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(OliveOil.getFluid(24))
            .fluidOutputs(Lubricant.getFluid(12))
            .EUt(96) // MV
            .duration(16 * TICK)
            .buildAndRegister()

        // Fat
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, SodaAsh)
            .fluidInputs(OliveOil.getFluid(8000))
            .fluidOutputs(Fat.getFluid(4000))
            .EUt(VA[LV])
            .duration(20 * SECOND)
            .buildAndRegister()

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_INGOT)
            .fluidInputs(Fat.getFluid(100))
            .outputs(ANIMAL_FAT.itemStack)
            .EUt(VH[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(ANIMAL_FAT.itemStack)
            .fluidOutputs(Fat.getFluid(100))
            .EUt(VA[LV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // Redo all meats macerating, add animal fats to outputs.
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES,
            ItemStack(Items.PORKCHOP))

        MACERATOR_RECIPES.recipeBuilder()
            .inputs(ItemStack(Items.PORKCHOP))
            .output(dust, Meat, 2)
            .outputs(ANIMAL_FAT.getItemStack(2))
            .output(dustTiny, Bone)
            .EUt(2) // ULV
            .duration(5 * SECOND + 2 * TICK)
            .buildAndRegister()

        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES,
            ItemStack(Items.BEEF))

        MACERATOR_RECIPES.recipeBuilder()
            .inputs(ItemStack(Items.BEEF))
            .output(dust, Meat, 2)
            .outputs(ANIMAL_FAT.itemStack)
            .output(dustTiny, Bone)
            .chancedOutput(dust, Meat, 5000, 0)
            .EUt(2) // ULV
            .duration(5 * SECOND + 2 * TICK)
            .buildAndRegister()

        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES,
            ItemStack(Items.CHICKEN))

        MACERATOR_RECIPES.recipeBuilder()
            .inputs(ItemStack(Items.CHICKEN))
            .output(dust, Meat)
            .output(dustTiny, Bone)
            .outputs(ANIMAL_FAT.itemStack)
            .EUt(2) // ULV
            .duration(5 * SECOND + 2 * TICK)
            .buildAndRegister()

        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES,
            ItemStack(Items.RABBIT))

        MACERATOR_RECIPES.recipeBuilder()
            .inputs(ItemStack(Items.RABBIT))
            .output(dust, Meat)
            .output(dustTiny, Bone)
            .outputs(ANIMAL_FAT.itemStack)
            .chancedOutput(dust, Meat, 5000, 0)
            .EUt(2) // ULV
            .duration(5 * SECOND + 2 * TICK)
            .buildAndRegister()

        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES,
            ItemStack(Items.MUTTON))

        MACERATOR_RECIPES.recipeBuilder()
            .inputs(ItemStack(Items.MUTTON))
            .output(dust, Meat, 2)
            .output(dustTiny, Bone)
            .outputs(ANIMAL_FAT.itemStack)
            .EUt(2) // ULV
            .duration(5 * SECOND + 2 * TICK)
            .buildAndRegister()

        // Advanced recipes for Animal Fat.
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .inputs(ItemStack(Items.PORKCHOP, 32))
            .fluidInputs(Methanol.getFluid(4000))
            .fluidInputs(Chloroform.getFluid(4000))
            .output(dust, Meat, 40)
            .outputs(ItemStack(Items.DYE, 16, 15))
            .fluidOutputs(Fat.getFluid(3200))
            .fluidOutputs(Mud.getFluid(12000))
            .EUt(VH[HV])
            .duration(50 * SECOND)
            .buildAndRegister()

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .inputs(ItemStack(Items.BEEF, 32))
            .fluidInputs(Methanol.getFluid(4000))
            .fluidInputs(Chloroform.getFluid(4000))
            .output(dust, Meat, 40)
            .outputs(ItemStack(Items.DYE, 16, 15))
            .fluidOutputs(Fat.getFluid(3200))
            .fluidOutputs(Mud.getFluid(12000))
            .EUt(VH[HV])
            .duration(50 * SECOND)
            .buildAndRegister()

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .inputs(ItemStack(Items.CHICKEN, 32))
            .fluidInputs(Methanol.getFluid(4000))
            .fluidInputs(Chloroform.getFluid(4000))
            .output(dust, Meat, 40)
            .outputs(ItemStack(Items.DYE, 16, 15))
            .fluidOutputs(Fat.getFluid(3200))
            .fluidOutputs(Mud.getFluid(12000))
            .EUt(VH[HV])
            .duration(50 * SECOND)
            .buildAndRegister()

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .inputs(ItemStack(Items.RABBIT, 32))
            .fluidInputs(Methanol.getFluid(4000))
            .fluidInputs(Chloroform.getFluid(4000))
            .output(dust, Meat, 40)
            .outputs(ItemStack(Items.DYE, 16, 15))
            .fluidOutputs(Fat.getFluid(3200))
            .fluidOutputs(Mud.getFluid(12000))
            .EUt(VH[HV])
            .duration(50 * SECOND)
            .buildAndRegister()

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .inputs(ItemStack(Items.MUTTON, 32))
            .fluidInputs(Methanol.getFluid(4000))
            .fluidInputs(Chloroform.getFluid(4000))
            .output(dust, Meat, 40)
            .outputs(ItemStack(Items.DYE, 16, 15))
            .fluidOutputs(Fat.getFluid(3200))
            .fluidOutputs(Mud.getFluid(12000))
            .EUt(VH[HV])
            .duration(50 * SECOND)
            .buildAndRegister()

        // Get animal fat with bone.
        AUTOCLAVE_RECIPES.recipeBuilder()
            .inputs(ItemStack(Items.DYE, 1, 15))
            .fluidInputs(Water.getFluid(250))
            .outputs(ANIMAL_FAT.itemStack)
            .output(dust, TricalciumPhosphate, 13)
            .EUt(VA[LV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Mud -> Biomass.
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Mud.getFluid(500))
            .fluidInputs(Water.getFluid(1000))
            .fluidOutputs(Biomass.getFluid(1000))
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Mud.getFluid(500))
            .fluidInputs(DistilledWater.getFluid(1000))
            .fluidOutputs(Biomass.getFluid(1000))
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // Mud and Mud ball converts.
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_BALL)
            .fluidInputs(Mud.getFluid(100))
            .output(MUD_BALL)
            .EUt(VA[LV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        EXTRACTOR_RECIPES.recipeBuilder()
            .input(MUD_BALL)
            .fluidOutputs(Mud.getFluid(100))
            .EUt(7) // ULV
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}