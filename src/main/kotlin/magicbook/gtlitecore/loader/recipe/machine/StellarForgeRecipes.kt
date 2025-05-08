package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.STELLAR_FORGE_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.LEPTONIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.NAQUADRIA_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.TARANIUM_CHARGE
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class StellarForgeRecipes
{

    companion object
    {

        fun init()
        {
            // TODO LuV-MAX Superconductors simplifications? Advanced recipes of some ABS recipes?

            // LuV Superconductors
            // STELLAR_FORGE_RECIPES.recipeBuilder()
            //     .circuitMeta(1)
            //     .inputs(ItemStack(NAQUADRIA_CHARGE))
            //     .fluidInputs(Indium.getFluid(L * 4 * 64))
            //     .fluidInputs(Tin.getFluid(L * 2 * 64))
            //     .fluidInputs(Barium.getFluid(L * 2 * 64))
            //     .fluidInputs(Titanium.getFluid(L * 64))
            //     .fluidInputs(Copper.getFluid(L * 7 * 64))
            //     .fluidInputs(Oxygen.getPlasma(14000 * 64))
            //     .fluidOutputs(IndiumTinBariumTitaniumCuprate.getFluid(L * 16 * 64 * 2))
            //     .EUt(VA[ZPM].toLong())
            //     .duration(5 * MINUTE)
            //     .buildAndRegister()

            // STELLAR_FORGE_RECIPES.recipeBuilder()
            //     .circuitMeta(2)
            //     .inputs(ItemStack(TARANIUM_CHARGE))
            //     .fluidInputs(Indium.getFluid(L * 4 * 256))
            //     .fluidInputs(Tin.getFluid(L * 2 * 256))
            //     .fluidInputs(Barium.getFluid(L * 2 * 256))
            //     .fluidInputs(Titanium.getFluid(L * 256))
            //     .fluidInputs(Copper.getFluid(L * 7 * 256))
            //     .fluidInputs(Oxygen.getPlasma(14000 * 256))
            //     .fluidOutputs(IndiumTinBariumTitaniumCuprate.getFluid(L * 16 * 256 * 2))
            //     .EUt(VA[UV].toLong())
            //     .duration(1 * MINUTE + 15 * SECOND)
            //     .buildAndRegister()

            // STELLAR_FORGE_RECIPES.recipeBuilder()
            //     .circuitMeta(3)
            //     .inputs(ItemStack(LEPTONIC_CHARGE))
            //     .fluidInputs(Indium.getFluid(L * 4 * 512))
            //     .fluidInputs(Tin.getFluid(L * 2 * 512))
            //     .fluidInputs(Barium.getFluid(L * 2 * 512))
            //     .fluidInputs(Titanium.getFluid(L * 512))
            //     .fluidInputs(Copper.getFluid(L * 7 * 512))
            //     .fluidInputs(Oxygen.getPlasma(14000 * 512))
            //     .fluidOutputs(IndiumTinBariumTitaniumCuprate.getFluid(L * 16 * 512 * 2))
            //     .EUt(VA[UHV].toLong())
            //     .duration(18 * SECOND + 5 * TICK)
            //     .buildAndRegister()

        }

    }

}