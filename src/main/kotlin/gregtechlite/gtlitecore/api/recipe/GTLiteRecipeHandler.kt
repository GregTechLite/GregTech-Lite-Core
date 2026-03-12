package gregtechlite.gtlitecore.api.recipe

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.recipes.ingredients.GTRecipeInput
import gregtech.api.recipes.ingredients.GTRecipeItemInput
import gregtech.api.recipes.ingredients.GTRecipeOreInput
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.crushed
import gregtech.api.unification.ore.OrePrefix.crushedCentrifuged
import gregtech.api.unification.ore.OrePrefix.crushedPurified
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustImpure
import gregtech.api.unification.ore.OrePrefix.dustPure
import gregtech.api.unification.ore.OrePrefix.ore
import gregtech.api.unification.ore.OrePrefix.oreEndstone
import gregtech.api.unification.ore.OrePrefix.oreNetherrack
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.metatileentities.MetaTileEntities
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler.getGTRecipeInput
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LARGE_MIXER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
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
        GTRecipeHandler.removeRecipesByInputs(CHEMICAL_RECIPES, itemInputs, arrayOfNulls<FluidStack>(0))
        GTRecipeHandler.removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, itemInputs, arrayOfNulls<FluidStack>(0))
    }

    /**
     * Removes a Chemical Reactor recipe and its corresponding recipe in Large Chemical Reactor (LCR).
     *
     * @param fluidInputs The fluid inputs of the recipe which will be removed.
     */
    @JvmStatic
    fun removeChemicalRecipes(fluidInputs: Array<FluidStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(CHEMICAL_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
        GTRecipeHandler.removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
    }

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
     * @param fluidInputs The fluid inputs of the recipe which will be removed.
     */
    @JvmStatic
    fun removeMixerRecipes(fluidInputs: Array<FluidStack>)
    {
        GTRecipeHandler.removeRecipesByInputs(MIXER_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
        GTRecipeHandler.removeRecipesByInputs(LARGE_MIXER_RECIPES, arrayOfNulls<ItemStack>(0), fluidInputs)
    }

    /**
     * Adds several assembling recipes for input and output hatches.
     *
     * This method is useful for add new IO buses or hatches and provide all tier recipes from LV to MAX.
     *
     * @param tier       The voltage tier of hatches.
     * @param input      The [MetaTileEntity] of the input hatch.
     * @param output     The [MetaTileEntity] of the output hatch.
     * @param extraInput If those hatches need some additional input items, then should add it at there, used common GTCEu
     *                   input format as default).
     *
     * @see getGTRecipeInput
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
     * - Ore Dictionary [String].
     *
     * @param extraInput The additional input items, or just some input ingredients which should be checked.
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
     * Gets fluid amount of io hatch consumed by its tier.
     *
     * @param offsetTier The offset of voltage tier.
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