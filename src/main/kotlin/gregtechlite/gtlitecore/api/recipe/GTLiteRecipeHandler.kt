package gregtechlite.gtlitecore.api.recipe

import gregtech.api.GTValues.L
import gregtech.api.GTValues.VA
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.*
import gregtech.api.recipes.ingredients.GTRecipeInput
import gregtech.api.recipes.ingredients.GTRecipeItemInput
import gregtech.api.recipes.ingredients.GTRecipeOreInput
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.*
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.metatileentities.MetaTileEntities.*
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler.getGTRecipeInput
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LARGE_MIXER_RECIPES
import gregtechlite.gtlitecore.api.recipe.util.TieredAdhesiveFluid
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreBlueSchist
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreGreenSchist
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreKimberlite
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreKomatiite
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreLimestone
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreQuartzite
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreShale
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreSlate
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

object GTLiteRecipeHandler
{

    // region Chemical Reactor Recipe Removal

    /**
     * Removes a Chemical Reactor recipe and its corresponding recipe in Large Chemical Reactor (LCR).
     *
     * @param itemInputs  The item inputs of the recipe which will be removed.
     * @param fluidInputs The fluid inputs of the recipe which will be removed.
     */
    @JvmStatic
    fun removeChemicalRecipes(itemInputs: Array<ItemStack>, fluidInputs: Array<FluidStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(CHEMICAL_RECIPES, itemInputs, fluidInputs)
        GTRecipeHandler.removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, itemInputs, fluidInputs)
    }

    /**
     * Removes a Chemical Reactor recipe and its corresponding recipe in Large Chemical Reactor (LCR).
     *
     * @param itemInputs The item inputs of the recipe which will be removed.
     */
    @JvmStatic
    fun removeChemicalRecipes(itemInputs: Array<ItemStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(CHEMICAL_RECIPES, itemInputs, arrayOf())
        GTRecipeHandler.removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, itemInputs, arrayOf())
    }

    /**
     * Removes a Chemical Reactor recipe and its corresponding recipe in Large Chemical Reactor (LCR).
     *
     * @param itemInputs The item inputs of the recipe which will be removed.
     */
    @JvmName("_removeChemicalRecipes")
    @JvmStatic
    fun removeChemicalRecipes(vararg itemInputs: ItemStack)
    {
        return removeChemicalRecipes(arrayOf(*itemInputs))
    }

    /**
     * Removes a Chemical Reactor recipe and its corresponding recipe in Large Chemical Reactor (LCR).
     *
     * @param fluidInputs The fluid inputs of the recipe which will be removed.
     */
    @JvmStatic
    fun removeChemicalRecipes(fluidInputs: Array<FluidStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(CHEMICAL_RECIPES, arrayOf(), fluidInputs)
        GTRecipeHandler.removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, arrayOf(), fluidInputs)
    }

    /**
     * Removes a Chemical Reactor recipe and its corresponding recipe in Large Chemical Reactor (LCR).
     *
     * @param fluidInputs The fluid inputs of the recipe which will be removed.
     */
    @JvmName("_removeChemicalRecipes")
    @JvmStatic
    fun removeChemicalRecipes(vararg fluidInputs: FluidStack)
    {
        return removeChemicalRecipes(arrayOf(*fluidInputs))
    }

    // endregion

    // region Mixer Recipe Removal

    /**
     * Removes a Mixer recipe and its corresponding recipe in Large Mixer (LM).
     *
     * @param itemInputs  The item inputs of the recipe which will be removed.
     * @param fluidInputs The fluid inputs of the recipe which will be removed.
     */
    @JvmStatic
    fun removeMixerRecipes(itemInputs: Array<ItemStack>, fluidInputs: Array<FluidStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(MIXER_RECIPES, itemInputs, fluidInputs)
        GTRecipeHandler.removeRecipesByInputs(LARGE_MIXER_RECIPES, itemInputs, fluidInputs)
    }

    /**
     * Removes a Mixer recipe and its corresponding recipe in Large Mixer (LM).
     *
     * @param itemInputs The item inputs of the recipe which will be removed.
     */
    @JvmStatic
    fun removeMixerRecipes(itemInputs: Array<ItemStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(MIXER_RECIPES, itemInputs, arrayOfNulls<FluidStack>(0))
        GTRecipeHandler.removeRecipesByInputs(LARGE_MIXER_RECIPES, itemInputs, arrayOfNulls<FluidStack>(0))
    }

    /**
     * Removes a Mixer recipe and its corresponding recipe in Large Mixer (LM).
     *
     * @param itemInputs The item inputs of the recipe which will be removed.
     */
    @JvmName("_removeMixerRecipes")
    @JvmStatic
    fun removeMixerRecipes(vararg itemInputs: ItemStack)
    {
        return removeMixerRecipes(arrayOf(*itemInputs))
    }

    /**
     * Removes a Mixer recipe and its corresponding recipe in Large Mixer (LM).
     *
     * @param fluidInputs The fluid inputs of the recipe which will be removed.
     */
    @JvmStatic
    fun removeMixerRecipes(fluidInputs: Array<FluidStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(MIXER_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
        GTRecipeHandler.removeRecipesByInputs(LARGE_MIXER_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
    }

    @JvmName("_removeMixerRecipes")
    @JvmStatic
    fun removeMixerRecipes(vararg fluidInputs: FluidStack)
    {
        return removeMixerRecipes(arrayOf(*fluidInputs))
    }

    // endregion

    /**
     * Adds several assembling recipes for input and output hatches.
     *
     * This method is useful for add new IO buses or hatches and provide all tier recipes from LV to MAX.
     *
     * @param tier       The voltage tier of hatches.
     * @param input      The [MetaTileEntity] of the input hatch.
     * @param output     The [MetaTileEntity] of the output hatch.
     * @param extraInput If those hatches need some additional input items, then should add it at there, used common GTCEu
     *                   input format as default.
     *
     * @see getGTRecipeInput
     */
    @JvmStatic
    fun addIOHatchRecipes(tier: Int, input: MetaTileEntity, output: MetaTileEntity, extraInput: Any)
    {
        val extra = getGTRecipeInput(extraInput)

        TieredAdhesiveFluid.generateRecipeFluidStacks(tier)
            .forEach {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(HULL[tier])
                    .inputs(extra)
                    .fluidInputs(it)
                    .output(input)
                    .EUt(VA[tier])
                    .duration(15 * SECOND)
                    .buildAndRegister()

                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .input(HULL[tier])
                    .inputs(extra)
                    .fluidInputs(it)
                    .output(output)
                    .EUt(VA[tier])
                    .duration(15 * SECOND)
                    .buildAndRegister()
            }
    }

    /**
     * Adds several assembling recipes for quadruple/nonuple input and output hatches.
     *
     * This method is used for add missing fluid hatches recipes from UHV to OpV.
     *
     * @param tier          The voltage tier of hatches.
     * @param pipeMaterial  The material of the pipes used in the recipes.
     *
     */
    @JvmStatic
    fun addMultiFluidHatchRecipes(tier: Int, pipeMaterial: Material)
    {
        val adhesiveFluid = TieredAdhesiveFluid.materialFromTier(tier)

        // add Quadruple Import Hatch recipe
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(FLUID_IMPORT_HATCH[tier])
            .input(pipeQuadrupleFluid, pipeMaterial)
            .fluidInputs(adhesiveFluid.getFluid(L * 4))
            .output(QUADRUPLE_IMPORT_HATCH[tier])
            .EUt(VA[tier])
            .duration(15 * SECOND)
            .buildAndRegister()

        // add Quadruple Export Hatch recipe
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(FLUID_EXPORT_HATCH[tier])
            .input(pipeQuadrupleFluid, pipeMaterial)
            .fluidInputs(adhesiveFluid.getFluid(L * 4))
            .output(QUADRUPLE_EXPORT_HATCH[tier])
            .EUt(VA[tier])
            .duration(15 * SECOND)
            .buildAndRegister()

        // add Nonuple Import Hatch recipe
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(9)
            .input(FLUID_IMPORT_HATCH[tier])
            .input(pipeNonupleFluid, pipeMaterial)
            .fluidInputs(adhesiveFluid.getFluid(L * 9))
            .output(NONUPLE_IMPORT_HATCH[tier])
            .EUt(VA[tier])
            .duration(30 * SECOND)
            .buildAndRegister()

        // add Nonuple Export Hatch recipe
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(9)
            .input(FLUID_EXPORT_HATCH[tier])
            .input(pipeNonupleFluid, pipeMaterial)
            .fluidInputs(adhesiveFluid.getFluid(L * 9))
            .output(NONUPLE_EXPORT_HATCH[tier])
            .EUt(VA[tier])
            .duration(30 * SECOND)
            .buildAndRegister()
    }

    /**
     * Check if the input is compatible with GT format.
     *
     * All allowed format of an inputs:
     * - Vanilla [ItemStack].
     * - Gregtech [gregtech.api.items.metaitem.MetaItem.MetaValueItem].
     * - Ore Dictionary [String].
     *
     * @param extraInput The additional input items, or just some input ingredients which should be checked.
     */
    @JvmStatic
    fun getGTRecipeInput(extraInput: Any): GTRecipeInput = when (extraInput)
    {
        is ItemStack                 -> GTRecipeItemInput(extraInput) // Common item stack.
        is MetaItem<*>.MetaValueItem -> GTRecipeItemInput(extraInput.stackForm) // GT meta item.
        is String                    -> GTRecipeOreInput(extraInput) // Ore dictionary.
        else                         -> throw IllegalArgumentException()
    }

    /**
     * Adds smelting recipes for all required ore variants and prefixes.
     *
     * @param oreType      The material of the ore which will be input in furnace.
     * @param outputType   The output material of the ore which will be output in furnace.
     * @param outputPrefix The format of [outputType], used [dust] as default.
     * @param outputCount  The count of the output material as default, will be available to use multiplier.
     */
    @JvmStatic
    fun addOreSmelting(oreType: Material,
                       outputType: Material = oreType,
                       outputPrefix: OrePrefix = dust,
                       outputCount: Int = 1)
    {
        ModHandler.addSmeltingRecipe(UnificationEntry(ore, oreType),
                                     OreDictUnifier.get(outputPrefix, outputType, outputCount))
        ModHandler.addSmeltingRecipe(UnificationEntry(oreNetherrack, oreType),
                                     OreDictUnifier.get(outputPrefix, outputType, outputCount * 2))
        ModHandler.addSmeltingRecipe(UnificationEntry(oreEndstone, oreType),
                                     OreDictUnifier.get(outputPrefix, outputType, outputCount * 4))

        if (ConfigHolder.worldgen.allUniqueStoneTypes)
        {
            val oreVariants = arrayOf(oreLimestone, oreKomatiite, oreGreenSchist, oreBlueSchist, oreKimberlite,
                                      oreQuartzite, oreSlate, oreShale)
            for (oreVariant in oreVariants)
            {
                ModHandler.addSmeltingRecipe(UnificationEntry(oreVariant, oreType),
                                             OreDictUnifier.get(outputPrefix, outputType, outputCount))
            }
        }

        ModHandler.addSmeltingRecipe(UnificationEntry(crushed, oreType),
                                     OreDictUnifier.get(outputPrefix, outputType, outputCount))
        ModHandler.addSmeltingRecipe(UnificationEntry(crushedCentrifuged, Sulfur),
                                     OreDictUnifier.get(outputPrefix, outputType, outputCount))
        ModHandler.addSmeltingRecipe(UnificationEntry(crushedPurified, Sulfur),
                                     OreDictUnifier.get(outputPrefix, outputType, outputCount))
        ModHandler.addSmeltingRecipe(UnificationEntry(dustImpure, Sulfur),
                                     OreDictUnifier.get(outputPrefix, outputType, outputCount))
        ModHandler.addSmeltingRecipe(UnificationEntry(dustPure, Sulfur),
                                     OreDictUnifier.get(outputPrefix, outputType, outputCount))
    }

    /**
     * Removes smelting recipes for all required ore variants and prefixes.
     *
     * @param oreType      The material of the ore which will be input in furnace.
     */
    @JvmStatic
    fun removeOreSmelting(oreType: Material)
    {
        val oreVariants = mutableListOf<OrePrefix>(ore, oreNetherrack, oreEndstone)
        if (ConfigHolder.worldgen.allUniqueStoneTypes)
        {
            oreVariants.addAll(listOf(oreLimestone, oreKomatiite, oreGreenSchist, oreBlueSchist, oreKimberlite,
                                      oreQuartzite, oreSlate, oreShale))
        }

        for (oreVariant in oreVariants)
        {
            ModHandler.removeFurnaceSmelting(UnificationEntry(oreVariant, oreType))
        }

        ModHandler.removeFurnaceSmelting(UnificationEntry(crushed, oreType))
        ModHandler.removeFurnaceSmelting(UnificationEntry(crushedCentrifuged, oreType))
        ModHandler.removeFurnaceSmelting(UnificationEntry(crushedPurified, oreType))

        ModHandler.removeFurnaceSmelting(UnificationEntry(dustImpure, oreType))
        ModHandler.removeFurnaceSmelting(UnificationEntry(dustPure, oreType))
    }

}