package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.blocks.BlockMachineCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities.HULL
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kevlar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium

internal object GTMachineCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // UEV Machine Casing
        ModHandler.addShapedRecipe(true, "casing_uev", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV),
            "PPP", "PwP", "PPP",
            'P', UnificationEntry(plate, Vibranium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(plate, Vibranium, 8)
            .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV))
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // UIV Machine Casing
        ModHandler.addShapedRecipe(true, "casing_uiv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV),
            "PPP", "PwP", "PPP",
            'P', UnificationEntry(plate, Shirabon))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(plate, Shirabon, 8)
            .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV))
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // UXV Machine Casing
        ModHandler.addShapedRecipe(true, "casing_uxv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UXV),
            "PPP", "PwP", "PPP",
            'P', UnificationEntry(plate, Creon))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(plate, Creon, 8)
            .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UXV))
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // OpV Machine Casing
        ModHandler.addShapedRecipe(true, "casing_opv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.OpV),
            "PPP", "PwP", "PPP",
            'P', UnificationEntry(plate, BlackDwarfMatter))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(plate, BlackDwarfMatter, 8)
            .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.OpV))
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // TODO MAX Machine Casing

        // UHV Machine Hull
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV),
                OreDictUnifier.get(cableGtSingle, Europium, 2)),
            arrayOf(Polybenzimidazole.getFluid(L * 2)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV))
            .input(cableGtSingle, Europium, 2)
            .fluidInputs(Kevlar.getFluid(L * 2))
            .output(HULL[UHV])
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // UEV Machine Hull
        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV))
            .input(cableGtSingle, Seaborgium, 2)
            .fluidInputs(Kevlar.getFluid(L * 2))
            .output(HULL[UEV])
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // UIV Machine Hull
        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV))
            .input(cableGtSingle, SuperheavyAlloyA, 2)
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 2))
            .output(HULL[UIV])
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // UXV Machine Hull
        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UXV))
            .input(cableGtSingle, SuperheavyAlloyB, 2)
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 2))
            .output(HULL[UXV])
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // OpV Machine Hull
        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.OpV))
            .input(cableGtSingle, Periodicium, 2)
            .fluidInputs(CosmicFabric.getFluid(L * 2))
            .output(HULL[OpV])
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // TODO MAX Machine Hull

    }

    // @formatter:on

}