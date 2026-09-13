package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Roentgenium
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.common.items.MetaItems.NEUTRON_REFLECTOR
import gregtech.common.items.MetaItems.SENSOR_UHV
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CaesiumCeriumCobaltIndium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SamariumCobalt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trinaquadalloy
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LOW_DENSITY_STRUCTURE

internal object BlackholeFormerCasingRecipes
{
    // @formatter:off

    fun init()
    {
        // Hawking Radiation Absorption Casing
        SPACE_ASSEMBLER_RECIPES.addRecipe {
            input(frameGt, Roentgenium, 4)
            input(LOW_DENSITY_STRUCTURE, 8)
            input(NEUTRON_REFLECTOR, 16)
            input(plateDense, Adamantium, 2)
            input(plateDense, Fermium, 2)
            input(plateDense, Trinaquadalloy, 2)
            input(SENSOR_UHV)
            fluidInputs(Neutronium.getFluid(L * 40))
            fluidInputs(SamariumCobalt.getFluid(L * 20))
            fluidInputs(CaesiumCeriumCobaltIndium.getFluid(L * 10))
            outputs(MultiblockCasing.HAWKING_RADIATION_ABSORPTION_CASING.getStack(64))
            EUt(VA[UHV])
            duration(5 * SECOND)
            tier(1)
        }
    }

    // @formatter:on
}