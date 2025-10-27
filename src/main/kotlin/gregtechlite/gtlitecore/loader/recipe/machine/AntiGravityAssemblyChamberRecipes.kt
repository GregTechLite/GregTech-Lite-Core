package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.common.items.MetaItems.CRYSTAL_COMPUTER_ZPM
import gregtech.common.items.MetaItems.CRYSTAL_MAINFRAME_UV
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_HPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_RAM_CHIP

object AntiGravityAssemblyChamberRecipes
{

    // @formatter:off

    fun init()
    {
        // CAL existed recipes added by backends, this class contained all circuit recipes which used Ass Line.

        // UV Crystal Mainframe
        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, HSSE, 16)
            .input(CRYSTAL_COMPUTER_ZPM, 32)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 16)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 16)
            .input(WRAP_ADVANCED_SMD_DIODE, 16)
            .input(WRAP_HPIC_CHIP, 2)
            .input(WRAP_RAM_CHIP, 32)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CRYSTAL_MAINFRAME_UV)
            .EUt(VA[LuV])
            .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, HSSE, 16)
            .input(CRYSTAL_COMPUTER_ZPM, 32)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 4)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 4)
            .input(WRAP_GOOWARE_SMD_DIODE, 4)
            .input(WRAP_HPIC_CHIP, 2)
            .input(WRAP_RAM_CHIP, 32)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CRYSTAL_MAINFRAME_UV)
            .EUt(VA[LuV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, HSSE, 16)
            .input(CRYSTAL_COMPUTER_ZPM, 32)
            .input(WRAP_OPTICAL_SMD_INDUCTOR)
            .input(WRAP_OPTICAL_SMD_CAPACITOR)
            .input(WRAP_OPTICAL_SMD_DIODE)
            .input(WRAP_HPIC_CHIP, 2)
            .input(WRAP_RAM_CHIP, 32)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CRYSTAL_MAINFRAME_UV)
            .EUt(VA[LuV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .buildAndRegister()

        

    }

    // @formatter:on

}