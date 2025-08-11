package gregtechlite.gtlitecore.api.recipe

import gregtech.api.GTValues.*
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.recipes.ingredients.GTRecipeInput
import gregtech.api.recipes.ingredients.GTRecipeItemInput
import gregtech.api.recipes.ingredients.GTRecipeOreInput
import gregtech.api.unification.material.Materials
import gregtech.common.metatileentities.MetaTileEntities
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LARGE_MIXER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

object GTLiteRecipeHandler
{

    @JvmStatic
    fun removeChemicalRecipes(itemInputs: Array<ItemStack>, fluidInputs: Array<FluidStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(CHEMICAL_RECIPES, itemInputs, fluidInputs)
        GTRecipeHandler.removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, itemInputs, fluidInputs)
    }

    @JvmStatic
    fun removeChemicalRecipes(itemInputs: Array<ItemStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(CHEMICAL_RECIPES, itemInputs, arrayOfNulls<FluidStack>(0))
        GTRecipeHandler.removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, itemInputs, arrayOfNulls<FluidStack>(0))
    }

    @JvmStatic
    fun removeChemicalRecipes(fluidInputs: Array<FluidStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(CHEMICAL_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
        GTRecipeHandler.removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
    }

    @JvmStatic
    fun removeMixerRecipes(itemInputs: Array<ItemStack>, fluidInputs: Array<FluidStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(MIXER_RECIPES, itemInputs, fluidInputs)
        GTRecipeHandler.removeRecipesByInputs(LARGE_MIXER_RECIPES, itemInputs, fluidInputs)
    }

    @JvmStatic
    fun removeMixerRecipes(itemInputs: Array<ItemStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(MIXER_RECIPES, itemInputs, arrayOfNulls<FluidStack>(0))
        GTRecipeHandler.removeRecipesByInputs(LARGE_MIXER_RECIPES, itemInputs, arrayOfNulls<FluidStack>(0))
    }

    @JvmStatic
    fun removeMixerRecipes(fluidInputs: Array<FluidStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(MIXER_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
        GTRecipeHandler.removeRecipesByInputs(LARGE_MIXER_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
    }

    /**
     * @param tier The tier of io bus/hatch, used voltage tier as default.
     * @param input The input bus/hatch mte.
     * @param output The output bus/hatch mte.
     * @param extraInput Extra input object for recipes, checked via [getGTRecipeInput].
     */
    @JvmStatic
    fun addIOHatchRecipes(tier: Int, input: MetaTileEntity, output: MetaTileEntity, extraInput: Any)
    {
        val extra = getGTRecipeInput(extraInput)
        // Glue recipes for ULV-LV
        if (tier <= LV)
        {
            val glueAmount = if (tier == ULV) 250 else 500
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(Materials.Glue.getFluid(glueAmount))
                .output(input)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(Materials.Glue.getFluid(glueAmount))
                .output(output)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()
        }

        // Polyethylene (PE) recipes for HV and below.
        if (tier <= HV)
        {
            val plasticAmount = getGTHatchFluidAmount(tier + 4)
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(Materials.Polyethylene.getFluid(plasticAmount))
                .output(input)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(Materials.Polyethylene.getFluid(plasticAmount))
                .output(output)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()
        }

        // Polytetrafluoroethylene (PTFE) recipes for LuV and below.
        if (tier <= LuV)
        {
            val ptfeAmount = getGTHatchFluidAmount(tier + 3)
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(ptfeAmount))
                .output(input)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(ptfeAmount))
                .output(output)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()
        }

        // Polybenzimidazole (PBI) recipes for UV and below.
        if (tier <= UV)
        {
            val pbiAmount = getGTHatchFluidAmount(tier)
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(Materials.Polybenzimidazole.getFluid(pbiAmount))
                .output(input)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(Materials.Polybenzimidazole.getFluid(pbiAmount))
                .output(output)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()
        }

        // Kevlar recipes for UEV and below.
        if (tier <= UEV)
        {
            val kevlarAmount = getGTHatchFluidAmount(tier - 1)
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(GTLiteMaterials.Kevlar.getFluid(kevlarAmount))
                .output(input)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(GTLiteMaterials.Kevlar.getFluid(kevlarAmount))
                .output(output)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()
        }

        // Fullerene Polymer Matrix (FPM) recipes for UXV and below.
        if (tier <= UXV)
        {
            val fpmAmount = getGTHatchFluidAmount(tier - 2)
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(GTLiteMaterials.FullerenePolymerMatrix.getFluid(fpmAmount))
                .output(input)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(GTLiteMaterials.FullerenePolymerMatrix.getFluid(fpmAmount))
                .output(output)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()
        }

        // Cosmic Fabric recipes for MAX and below.
        if (tier <= MAX)
        {
            val cosmicFabricAmount = getGTHatchFluidAmount(tier - 4)
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(CosmicFabric.getFluid(cosmicFabricAmount))
                .output(input)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(MetaTileEntities.HULL[tier])
                .inputs(extra)
                .fluidInputs(CosmicFabric.getFluid(cosmicFabricAmount))
                .output(output)
                .EUt(VA[tier])
                .duration(15 * SECOND)
                .buildAndRegister()
        }
    }

    /**
     * Check if the input is compatible with GT format.
     *
     * All allowed format of an inputs:
     * - Vanilla [ItemStack].
     * - Gregtech [gregtech.api.items.metaitem.MetaItem.MetaValueItem].
     * - Ore Dictionary used [String].
     */
    @JvmStatic
    fun getGTRecipeInput(extraInput: Any): GTRecipeInput = when (extraInput)
    {
        is ItemStack -> GTRecipeItemInput(extraInput) // Common item stack.
        is MetaItem<*>.MetaValueItem -> GTRecipeItemInput(extraInput.stackForm) // GT meta item.
        is String -> GTRecipeOreInput(extraInput) // Ore dictionary.
        else -> throw IllegalArgumentException()
    }

    /**
     * Get fluid amount of io hatch consumed by its tier.
     */
    @JvmStatic
    fun getGTHatchFluidAmount(offsetTier: Int): Int = when (offsetTier) {
        ULV  -> 4
        LV   -> L / 16  // 9
        MV   -> L / 8   // 18
        HV   -> L / 4   // 36
        EV   -> L / 2   // 72
        IV   -> L       // 144
        LuV  -> L * 2  // 288
        ZPM  -> L * 3  // 432
        UV   -> L * 4   // 576
        UHV  -> L * 5  // 720
        UEV  -> L * 6  // 864
        UIV  -> L * 7  // 1008
        UXV  -> L * 8  // 1152
        OpV  -> L * 9  // 1296
        MAX  -> L * 10 // 1440
        else -> 1
    }

}