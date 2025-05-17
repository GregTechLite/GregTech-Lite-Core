package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Glowstone
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NetherStar
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Topaz
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.common.items.MetaItems.ADVANCED_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.HIGHLY_ADVANCED_SOC
import gregtech.common.items.MetaItems.ROBOT_ARM_ZPM
import gregtech.common.items.MetaItems.STEM_CELLS
import gregtech.common.items.MetaItems.SYSTEM_ON_CHIP
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.NANO_FORGE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicBoronNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicZirconia
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HexagonalBoronNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HexagonalSiliconNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumNiobate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TranscendentMetal
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.nanite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ENERGISED_TESSERACT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ULTRA_HIGHLY_ADVANCED_SOC_CHIP

/**
 * Nanites recipes for Nano Forge.
 *
 * Here's all nanites recipes for several recipes which used nanites as components, it is based on the
 * same name ore prefix in [GT5u](https://github.com/GTNewHorizons/GT5-Unofficial). The original author
 * of Nano Forge and PCB Factory is [BlueWeabo](https://github.com/BlueWeabo), here's the general rules
 * for all nanites recipes (the presenter is also BlueWeabo):
 * - Nanites meant to be consumed should either have a short duration or a big output;
 * - Nanites which aren't consumed should have a long duration and output less than 16;
 * - Nanites always take UU Matter as a fluid and a lot of power to make;
 * - Nanites always take UU Matter as a fluid and a lot of power to make;
 *
 * In our environment, the duration of one nanite recipes should be less than 100s for default. As we
 * have known, we don't want player required more times for this machine, pay more energy is enough.
 *
 * @see magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityNanoForge
 *
 * @author BlueWeabo (original author in GT5u)
 */
@Suppress("MISSING_DEPENDENCY_CLASS")
class NanitesChain
{

    companion object
    {

        fun init()
        {
            // The first recipe for Carbon Nanite, used to assemble Nano Forge.
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(circuit, MarkerMaterials.Tier.UV, 4)
                .input(ROBOT_ARM_ZPM, 8)
                .input(STEM_CELLS, 32)
                .input(ring, NaquadahAlloy, 16)
                .input(stick, NaquadahAlloy, 8)
                .input(dust, Carbon, 64)
                .fluidInputs(SolderingAlloy.getFluid(L * 40))
                .fluidInputs(UUMatter.getFluid(16000))
                .output(nanite, Carbon, 2)
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r ->
                    r.researchStack(OreDictUnifier.get(block, Carbon))
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // Carbon Nanite (for Nano Forge controller, some QFT catalysts)
            NANO_FORGE_RECIPES.recipeBuilder()
                .notConsumable(lens, NetherStar)
                .input(block, Carbon, 8)
                .input(SYSTEM_ON_CHIP, 64)
                .fluidInputs(UUMatter.getFluid(200_000))
                .output(nanite, Carbon, 64)
                .EUt(10_000_000) // UIV
                .duration(20 * SECOND)
                .tier(1)
                .buildAndRegister()

            // Silver Nanite (for PCB Factory T2 recipes, some QFT Catalysts)
            NANO_FORGE_RECIPES.recipeBuilder()
                .notConsumable(lens, HexagonalBoronNitride)
                .input(block, Silver, 8)
                .input(ADVANCED_SYSTEM_ON_CHIP, 64)
                .fluidInputs(UUMatter.getFluid(200_000))
                .output(nanite, Silver)
                .EUt(10_000_000) // UIV
                .duration(40 * SECOND)
                .tier(2)
                .buildAndRegister()

            // Gold Nanite (for PCB Factory T3 recipes)
            NANO_FORGE_RECIPES.recipeBuilder()
                .notConsumable(lens, HexagonalSiliconNitride)
                .input(block, Gold, 8)
                .input(HIGHLY_ADVANCED_SOC, 64)
                .fluidInputs(UUMatter.getFluid(300_000))
                .output(nanite, Gold)
                .EUt(100_000_000) // UXV
                .duration(1 * MINUTE)
                .tier(3)
                .buildAndRegister()

            // Glowstone Nanite (for Optoelectronic SoC for Optical Circuits and some QFT Catalysts)
            NANO_FORGE_RECIPES.recipeBuilder()
                .notConsumable(lens, LithiumNiobate)
                .input(block, Glowstone, 16)
                .input(ADVANCED_SYSTEM_ON_CHIP, 64)
                .fluidInputs(UUMatter.getFluid(50_000))
                .output(nanite, Glowstone, 64)
                .EUt(50_000_000) // UXV
                .duration(1 * MINUTE + 30 * SECOND)
                .tier(2)
                .buildAndRegister()

            // Neutronium Nanite (for some QFT Catalysts and UXV+ component recipes)
            NANO_FORGE_RECIPES.recipeBuilder()
                .notConsumable(lens, CubicZirconia)
                .input(block, Neutronium, 8)
                .input(ADVANCED_SYSTEM_ON_CHIP, 64)
                .input(ADVANCED_SYSTEM_ON_CHIP, 32)
                .fluidInputs(UUMatter.getFluid(200_000))
                .output(nanite, Neutronium)
                .EUt(100_000_000) // UIV
                .duration(50 * SECOND)
                .tier(1)
                .buildAndRegister()

            // Copper Nanite (for some QFT Catalysts and Mag Matter)
            NANO_FORGE_RECIPES.recipeBuilder()
                .notConsumable(lens, Topaz)
                .input(block, Copper, 8)
                .input(SYSTEM_ON_CHIP, 64)
                .input(SYSTEM_ON_CHIP, 64)
                .fluidInputs(UUMatter.getFluid(120_000))
                .output(nanite, Copper, 16)
                .EUt(10_000_000) // UIV
                .duration(30 * SECOND)
                .tier(1)
                .buildAndRegister()

            // Iron Nanite (for Harmonic Phonon Matter blasting)
            NANO_FORGE_RECIPES.recipeBuilder()
                .notConsumable(lens, CubicBoronNitride)
                .input(block, Iron, 8)
                .input(HIGHLY_ADVANCED_SOC, 64)
                .input(HIGHLY_ADVANCED_SOC, 64)
                .fluidInputs(UUMatter.getFluid(400_000))
                .output(nanite, Iron, 4)
                .EUt(50_000_000) // UXV
                .duration(30 * SECOND)
                .tier(2)
                .buildAndRegister()

            // Transcendent Metal Nanite (for some QFT Catalysts and Phonon Crystal Seed)
            NANO_FORGE_RECIPES.recipeBuilder()
                .notConsumable(ENERGISED_TESSERACT)
                .input(block, TranscendentMetal, 8)
                .input(HIGHLY_ADVANCED_SOC, 64)
                .input(HIGHLY_ADVANCED_SOC, 64)
                .input(HIGHLY_ADVANCED_SOC, 64)
                .fluidInputs(UUMatter.getFluid(2_000_000))
                .output(nanite, TranscendentMetal)
                .EUt(1_000_000_000) // MAX
                .duration(2 * MINUTE + 30 * SECOND)
                .tier(2)
                .buildAndRegister()

        }

    }

}