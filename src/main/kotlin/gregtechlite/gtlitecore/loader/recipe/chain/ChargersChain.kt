package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.GlycerylTrinitrate
import gregtech.api.unification.material.Materials.Lawrencium
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ArceusAlloy2B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CyclotetramethyleneTetranitroamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DegenerateRhenium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HadronicResonantGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyLeptonMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexanitrohexaaxaisowurtzitane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Octaazacubane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks.LEPTONIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks.NAQUADRIA_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks.QUANTUM_CHROMODYNAMIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks.TARANIUM_CHARGE
import net.minecraft.item.ItemStack

internal object ChargersChain
{

    // @formatter:off

    fun init()
    {


        // Naquadria Charge
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(15)
            .input(frameGt, Naquadria)
            .input(dust, Hexanitrohexaaxaisowurtzitane)
            .input(plate, NaquadahEnriched, 2)
            .input(plate, Mendelevium, 4)
            .input(foil, NaquadahAlloy, 8)
            .fluidInputs(GlycerylTrinitrate.getFluid(16000))
            .outputs(ItemStack(NAQUADRIA_CHARGE))
            .EUt(VA[UV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Taranium Charge
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(15)
            .inputs(ItemStack(NAQUADRIA_CHARGE))
            .input(dust, DegenerateRhenium)
            .input(plate, Taranium, 2)
            .input(plate, Lawrencium, 4)
            .input(foil, Tritanium, 8)
            .fluidInputs(CyclotetramethyleneTetranitroamine.getFluid(4000))
            .outputs(ItemStack(TARANIUM_CHARGE))
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Leptonic Charge
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(15)
            .inputs(ItemStack(TARANIUM_CHARGE))
            .input(dust, Octaazacubane)
            .input(plate, MetastableOganesson, 2)
            .input(plate, Dubnium, 4)
            .input(foil, Vibranium, 8)
            .fluidInputs(HeavyLeptonMixture.getFluid(1000))
            .outputs(ItemStack(LEPTONIC_CHARGE))
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Quantum Chromodynamic Charge
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(15)
            .inputs(ItemStack(LEPTONIC_CHARGE))
            .input(dust, ActiniumSuperhydride)
            .input(plate, HeavyQuarkDegenerateMatter, 2)
            .input(plate, Bohrium, 4)
            .input(foil, ArceusAlloy2B, 8)
            .fluidInputs(HadronicResonantGas.getFluid(500))
            .outputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    // @formatter:on

}