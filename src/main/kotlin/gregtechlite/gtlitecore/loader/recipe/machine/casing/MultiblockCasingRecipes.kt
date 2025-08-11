package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.BlueSteel
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.RedSteel
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems.COMPONENT_GRINDER_TUNGSTEN
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UV
import gregtech.common.metatileentities.MetaTileEntities.HULL
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MolybdenumDisilicide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumCarbide
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing

internal object MultiblockCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Heat Vent
        ModHandler.addShapedRecipe(true, "heat_vent", ActiveUniqueCasing.HEAT_VENT.getStack(ConfigHolder.recipes.casingsPerCraft),
            "PDP", "RLR", "PDP",
            'P', UnificationEntry(plate, TantalumCarbide),
            'D', UnificationEntry(plateDouble, MolybdenumDisilicide),
            'R', UnificationEntry(rotor, Titanium),
            'L', UnificationEntry(stickLong, MolybdenumDisilicide))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(plate, TantalumCarbide, 4)
            .input(plateDouble, MolybdenumDisilicide, 2)
            .input(rotor, Titanium, 2)
            .input(stickLong, MolybdenumDisilicide, 1)
            .outputs(ActiveUniqueCasing.HEAT_VENT.getStack(ConfigHolder.recipes.casingsPerCraft))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Substrate Casing
        ModHandler.addShapedRecipe(true, "substrate_casing", MultiblockCasing.SUBSTRATE_CASING.getStack(ConfigHolder.recipes.casingsPerCraft),
            "PPP", "RFR", "R R",
            'P', UnificationEntry(plate, Palladium),
            'R', UnificationEntry(stick, RedSteel),
            'F', UnificationEntry(frameGt, BlueSteel))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(frameGt, BlueSteel)
            .input(plate, Palladium, 3)
            .input(stick, RedSteel, 4)
            .outputs(MultiblockCasing.SUBSTRATE_CASING.getStack(ConfigHolder.recipes.casingsPerCraft))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Advanced Substrate Casing
        ModHandler.addShapedRecipe(true, "advanced_substrate_casing", MultiblockCasing.ADVANCED_SUBSTRATE_CASING.getStack(ConfigHolder.recipes.casingsPerCraft),
            "PPP", "RFR", "R R",
            'P', UnificationEntry(plate, Ruridit),
            'R', UnificationEntry(stick, Duranium),
            'F', UnificationEntry(frameGt, NaquadahAlloy))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(frameGt, NaquadahAlloy)
            .input(plate, Ruridit, 3)
            .input(stick, Duranium, 4)
            .outputs(MultiblockCasing.ADVANCED_SUBSTRATE_CASING.getStack(ConfigHolder.recipes.casingsPerCraft))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Drill Head
        ModHandler.addShapedRecipe(true, "drill_head", MultiblockCasing.DRILL_HEAD.stack,
            "PGP", "MHM", "SSS",
            'P', ELECTRIC_PISTON_UV.stackForm,
            'G', UnificationEntry(gear, Tritanium),
            'M', ELECTRIC_MOTOR_UV.stackForm,
            'H', HULL[UV].stackForm,
            'S', COMPONENT_GRINDER_TUNGSTEN.stackForm)
    }

    // @formatter:on

}