package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.GalliumArsenide
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Xenon
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.common.items.MetaItems.NAQUADAH_BOULE
import gregtech.common.items.MetaItems.NEUTRONIUM_BOULE
import gregtech.common.items.MetaItems.PHOSPHORUS_BOULE
import gregtech.common.items.MetaItems.SILICON_BOULE
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.getFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SolarGradeSilicon
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HASSIUM_BOULE

internal object ElectricBlastFurnaceRecipes
{

    // @formatter:off

    fun init()
    {
        // Monocrystalline Boule
        BLAST_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, SolarGradeSilicon, 32)
            .input(dustSmall, GalliumArsenide)
            .output(SILICON_BOULE)
            .EUt(VA[MV])
            .duration(1 * MINUTE + 52 * SECOND + 10 * TICK) // 450sec / 4
            .blastFurnaceTemp(1784) // Cupronickel
            .buildAndRegister()

        // Phosphorus-doped Monocrystalline Boule
        BLAST_RECIPES.recipeBuilder()
            .input(dust, SolarGradeSilicon, 64)
            .input(dust, Phosphorus, 8)
            .input(dustSmall, GalliumArsenide, 2)
            .fluidInputs(Nitrogen.getFluid(8000))
            .output(PHOSPHORUS_BOULE)
            .EUt(VA[HV])
            .duration(2 * MINUTE + 30 * SECOND) // 600sec / 4
            .blastFurnaceTemp(2484) // Kanthal
            .buildAndRegister()

        // Naquadah-doped Monocrystalline Boule
        BLAST_RECIPES.recipeBuilder()
            .input(block, SolarGradeSilicon, 16)
            .input(ingot, Naquadah)
            .input(dust, GalliumArsenide)
            .fluidInputs(Argon.getFluid(8000))
            .output(NAQUADAH_BOULE)
            .EUt(VA[EV])
            .duration(3 * MINUTE + 7 * SECOND + 10 * TICK) // 750sec / 4
            .blastFurnaceTemp(5400) // HSS-G
            .buildAndRegister()

        // Neutronium-doped Monocrystalline Boule
        BLAST_RECIPES.recipeBuilder()
            .input(block, SolarGradeSilicon, 32)
            .input(ingot, Neutronium, 4)
            .input(dust, GalliumArsenide, 2)
            .fluidInputs(Xenon.getFluid(8000))
            .output(NEUTRONIUM_BOULE)
            .EUt(VA[IV])
            .duration(3 * MINUTE + 45 * SECOND) // 900sec / 4
            .blastFurnaceTemp(6484) // Naquadah
            .buildAndRegister()

        // Hassium-doped Monocrystalline Boule
        BLAST_RECIPES.recipeBuilder()
            .input(block, Silicon, 64)
            .input(ingot, MetastableHassium, 8)
            .input(dust, GalliumArsenide, 4)
            .fluidInputs(Radon.getFluid(8000))
            .output(HASSIUM_BOULE)
            .EUt(VA[LuV])
            .duration(17 * MINUTE + 30 * SECOND)
            .blastFurnaceTemp(9400) // Tritanium
            .buildAndRegister()

        BLAST_RECIPES.recipeBuilder()
            .input(block, SolarGradeSilicon, 64)
            .input(ingot, MetastableHassium, 8)
            .input(dust, GalliumArsenide, 4)
            .fluidInputs(Radon.getFluid(8000))
            .output(HASSIUM_BOULE)
            .EUt(VA[LuV])
            .duration(4 * MINUTE + 22 * SECOND + 10 * TICK) // 1050sec / 4
            .blastFurnaceTemp(9400) // Tritanium
            .buildAndRegister()

    }

    // @formatter:on

}