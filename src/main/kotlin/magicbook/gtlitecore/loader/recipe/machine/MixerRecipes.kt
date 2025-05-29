package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Actinium
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Arsenic
import gregtech.api.unification.material.Materials.Astatine
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Berkelium
import gregtech.api.unification.material.Materials.Beryllium
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Blaze
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Caesium
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Cerium
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copernicium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Dysprosium
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.Electrotine
import gregtech.api.unification.material.Materials.Erbium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Francium
import gregtech.api.unification.material.Materials.Gadolinium
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Garnierite
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Holmium
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iodine
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Lanthanum
import gregtech.api.unification.material.Materials.Lawrencium
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Livermorium
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Meitnerium
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Mercury
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Moscovium
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neodymium
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nichrome
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Nihonium
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.NiobiumNitride
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Nobelium
import gregtech.api.unification.material.Materials.Obsidian
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Polonium
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.Praseodymium
import gregtech.api.unification.material.Materials.Promethium
import gregtech.api.unification.material.Materials.Protactinium
import gregtech.api.unification.material.Materials.Radium
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.Redstone
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Rhodium
import gregtech.api.unification.material.Materials.Roentgenium
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.Ruthenium
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Saltpeter
import gregtech.api.unification.material.Materials.Samarium
import gregtech.api.unification.material.Materials.Scandium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Stone
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Tennessine
import gregtech.api.unification.material.Materials.Terbium
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Vanadium
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Xenon
import gregtech.api.unification.material.Materials.Ytterbium
import gregtech.api.unification.material.Materials.Yttrium
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.LARGE_MIXER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Abyssalloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumGroupAlloyA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumGroupAlloyB
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumSuperhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AlkaliEarthGroupAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AlkaliGroupAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumBronze
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ArceusAlloy2B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BETSPerrhenate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BabbitAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bedrockium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BlazingPyrotheum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BosonicUUMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChromiumGermaniumTelluride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CinobiteA243
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EglinSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EglinSteelBase
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EnrichedNaquadahAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FermionicUUMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Firestone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FreeElectronGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FullereneSuperconductor
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GSTGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GelidCryotheum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Grisium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HDCS
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HSLASteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HafniumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyC276
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyK243
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyN
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyX
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyX78
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IncoloyMA813
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IncoloyMA956
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Inconel625
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Jasper
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kovar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumFullereneNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumGroupAlloyA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumGroupAlloyB
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadBismuthEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Legendarium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LutetiumManganeseGermanium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnetoResonatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MaragingSteel250
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Mellion
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableFlerovium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableHassium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableOganesson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MolybdenumDisilicide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Monel500
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Nitinol60
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NobleGasMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NonMetallicMixtureA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NonMetallicMixtureB
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Periodicium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pikyonium64B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PreciousMetalAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RareEarthAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RefractoryAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ReneN5
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ResonantStrangeMeson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SamariumCobalt
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SeaborgiumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SeaborgiumDopedCarbonNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumPotassiumEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Staballoy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Stellite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StrontiumFerrite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheavyAlloyA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheavyAlloyB
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tairitsium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Talonite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TectonicPetrotheum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitanSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitaniumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitaniumTungstenCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ToxicAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TransitionAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TransitionAlloyA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TransitionAlloyB
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trinaquadalloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tumbaga
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.VibraniumTritaniumActiniumIronSuperhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WatertightSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WoodsGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZephyreanAerotheum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Zeron100
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZirconiumCarbide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SAND_DUST

@Suppress("MISSING_DEPENDENCY_CLASS")
class MixerRecipes
{

    companion object
    {

        fun init()
        {
            // Strontium Ferrite
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Strontium)
                .input(dust, Iron, 12)
                .fluidInputs(Oxygen.getFluid(19000))
                .output(dust, StrontiumFerrite, 32)
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // ZBLAN Glass
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Zirconium, 5)
                .input(dust, Barium, 2)
                .input(dust, Lanthanum)
                .input(dust, Aluminium)
                .input(dust, Sodium, 2)
                .fluidInputs(Fluorine.getFluid(6200))
                .output(dust, ZBLANGlass, 11)
                .EUt(VA[HV].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // GST Glass
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Germanium, 2)
                .input(dust, Antimony, 2)
                .input(dust, Tellurium, 5)
                .output(dust, GSTGlass, 9)
                .EUt(VA[HV].toLong())
                .duration(12 * SECOND + 10)
                .buildAndRegister()

            // Samarium Cobalt
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Samarium)
                .input(dust, Cobalt, 5)
                .output(dust, SamariumCobalt, 6)
                .EUt(VA[LuV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Sodium Potassium
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Sodium, 7)
                .input(dust, Potassium, 3)
                .output(dust, SodiumPotassiumEutatic, 10)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Eutatic Lead Bismuth
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Lead, 3)
                .input(dust, Bismuth, 7)
                .output(dust, LeadBismuthEutatic, 10)
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Lutetium Manganese Germanium
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Lutetium)
                .input(dust, Manganese, 3)
                .input(dust, Germanium, 6)
                .output(dust, LutetiumManganeseGermanium, 10)
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Chromium Germanium Telluride
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Chrome)
                .input(dust, Germanium)
                .input(dust, Tellurium, 3)
                .output(dust, ChromiumGermaniumTelluride, 5)
                .EUt(VA[UV].toLong())
                .duration(45 * SECOND)
                .buildAndRegister()

            // Woods Glass
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, SodaAsh, 6)
                .input(dust, SiliconDioxide, 3)
                .input(dust, BariumOxide, 2)
                .input(dust, Garnierite, 2)
                .output(dust, WoodsGlass, 13)
                .EUt(VA[IV].toLong())
                .duration(45 * SECOND)
                .buildAndRegister()

            // Mellion
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(dust, Rubidium, 11)
                .input(dust, Tritanium, 11)
                .input(dust, Adamantium, 7)
                .input(dust, Firestone, 13)
                .input(dust, MetastableOganesson, 13)
                .input(dust, ActiniumSuperhydride, 8)
                .fluidInputs(ResonantStrangeMeson.getFluid(1000))
                .output(dust, Mellion, 64)
                .EUt(VA[UXV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // Kovar
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Invar, 15)
                .input(dust, Cobalt, 3)
                .output(dust, Kovar, 18)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Iron, 10)
                .input(dust, Nickel, 5)
                .input(dust, Cobalt, 3)
                .output(dust, Kovar, 18)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Maraging Steel 250
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Iron, 16)
                .input(dust, Molybdenum, 1)
                .input(dust, Titanium, 1)
                .input(dust, Nickel, 4)
                .input(dust, Cobalt, 2)
                .output(dust, MaragingSteel250, 24)
                .EUt(VA[EV].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // Inconel-625
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Nickel, 3)
                .input(dust, Chrome, 7)
                .input(dust, Molybdenum, 10)
                .input(dust, Invar, 10)
                .input(dust, Nichrome, 13)
                .output(dust, Inconel625, 48)
                .EUt(VA[EV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // Staballoy
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Uranium, 9)
                .input(dust, Titanium, 1)
                .output(dust, Staballoy, 10)
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Talonite
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Cobalt, 4)
                .input(dust, Chrome, 3)
                .input(dust, Phosphorus, 2)
                .input(dust, Molybdenum, 1)
                .output(dust, Talonite, 10)
                .EUt(VA[HV].toLong())
                .duration(24 * SECOND)
                .buildAndRegister()

            // Zeron-100
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Chrome, 13)
                .input(dust, Nickel, 3)
                .input(dust, Molybdenum, 2)
                .input(dust, Copper, 10)
                .input(dust, Tungsten, 2)
                .input(dust, Steel, 20)
                .output(dust, Zeron100, 60)
                .EUt(VA[IV].toLong())
                .duration(48 * SECOND)
                .buildAndRegister()

            // Watertight Steel
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Iron, 7)
                .input(dust, Aluminium, 4)
                .input(dust, Nickel, 2)
                .input(dust, Chrome, 1)
                .input(dust, Sulfur, 1)
                .output(dust, WatertightSteel, 15)
                .EUt(VA[HV].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // Stellite
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Cobalt, 9)
                .input(dust, Chrome, 9)
                .input(dust, Manganese, 5)
                .input(dust, Titanium, 2)
                .output(dust, Stellite, 25)
                .EUt(VA[HV].toLong())
                .duration(18 * SECOND)
                .buildAndRegister()

            // Tumbaga
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Gold, 7)
                .input(dust, Bronze, 3)
                .output(dust, Tumbaga, 10)
                .EUt(VA[MV].toLong())
                .duration(14 * SECOND)
                .buildAndRegister()

            // Eglin Steel Base
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Iron, 4)
                .input(dust, Kanthal, 1)
                .input(dust, Invar, 5)
                .output(dust, EglinSteelBase, 10)
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Eglin Steel
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, EglinSteelBase, 10)
                .input(dust, Sulfur, 1)
                .input(dust, Silicon, 1)
                .input(dust, Carbon, 1)
                .output(dust, EglinSteel, 13)
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // One-Step recipe of Eglin Steel.
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Iron, 4)
                .input(dust, Kanthal, 1)
                .input(dust, Invar, 5)
                .input(dust, Sulfur, 1)
                .input(dust, Silicon, 1)
                .input(dust, Carbon, 1)
                .output(dust, EglinSteel, 13)
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Grisium
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Titanium, 9)
                .input(dust, Carbon, 9)
                .input(dust, Potassium, 9)
                .input(dust, Lithium, 9)
                .input(dust, Sulfur, 9)
                .fluidInputs(Hydrogen.getFluid(5000))
                .output(dust, Grisium, 50)
                .EUt(VA[EV].toLong())
                .duration(38 * SECOND)
                .buildAndRegister()

            // Babbit Alloy
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Tin, 5)
                .input(dust, Lead, 36)
                .input(dust, Antimony, 8)
                .input(dust, Arsenic)
                .output(dust, BabbitAlloy, 50)
                .EUt(VA[MV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // HSLA Steel
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Invar, 2)
                .input(dust, Vanadium)
                .input(dust, Titanium)
                .input(dust, Molybdenum)
                .output(dust, HSLASteel, 5)
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Incoloy-MA813
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, VanadiumSteel, 4)
                .input(dust, Niobium, 2)
                .input(dust, Chrome, 3)
                .input(dust, Nickel, 4)
                .output(dust, IncoloyMA813, 13)
                .EUt(VA[IV].toLong())
                .duration(22 * SECOND)
                .buildAndRegister()

            // Monel-500
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Nickel, 23)
                .input(dust, Manganese, 2)
                .input(dust, Copper, 10)
                .input(dust, Aluminium, 4)
                .input(dust, Titanium)
                .output(dust, Monel500, 40)
                .EUt(VA[IV].toLong())
                .duration(56 * SECOND)
                .buildAndRegister()

            // Incoloy-MA956
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Iron, 16)
                .input(dust, Aluminium, 3)
                .input(dust, Chrome, 5)
                .input(dust, Yttrium)
                .output(dust, IncoloyMA956, 25)
                .EUt(VA[IV].toLong())
                .duration(34 * SECOND)
                .buildAndRegister()

            // Zirconium Carbide
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Zirconium)
                .input(dust, Carbon)
                .output(dust, ZirconiumCarbide, 2)
                .EUt(VA[HV].toLong())
                .duration(18 * SECOND)
                .buildAndRegister()

            // Tantalum Carbide
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Tantalum)
                .input(dust, Carbon)
                .output(dust, TantalumCarbide, 2)
                .EUt(VA[EV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Molybdenum Disilicide
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Molybdenum, 1)
                .input(dust, Silicon, 2)
                .output(dust, MolybdenumDisilicide, 3)
                .EUt(VA[EV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()

            // Hastelloy-C276
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Nickel, 12)
                .input(dust, Molybdenum, 8)
                .input(dust, Chrome, 7)
                .input(dust, Tungsten)
                .input(dust, Cobalt)
                .input(dust, Copper)
                .output(dust, HastelloyC276, 30)
                .EUt(VA[IV].toLong())
                .duration(45 * SECOND)
                .buildAndRegister()

            // Hastelloy-X
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Nickel, 8)
                .input(dust, Iron, 3)
                .input(dust, Tungsten, 4)
                .input(dust, Molybdenum, 2)
                .input(dust, Chrome, 1)
                .input(dust, Niobium, 1)
                .output(dust, HastelloyX, 19)
                .EUt(VA[IV].toLong())
                .duration(43 * SECOND)
                .buildAndRegister()

            // Hastelloy-N
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Yttrium, 2)
                .input(dust, Molybdenum, 4)
                .input(dust, Chrome, 2)
                .input(dust, Titanium, 2)
                .input(dust, Nickel, 15)
                .output(dust, HastelloyN, 25)
                .EUt(VA[IV].toLong())
                .duration(38 * SECOND)
                .buildAndRegister()

            // Aluminium Bronze
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Aluminium, 2)
                .input(dust, Manganese)
                .input(dust, Bronze)
                .output(dust, AluminiumBronze, 4)
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Rene N5
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Nickel, 22)
                .input(dust, Cobalt, 4)
                .input(dust, Chrome, 3)
                .input(dust, Aluminium, 3)
                .input(dust, Tungsten, 2)
                .input(dust, Hafnium)
                .input(dust, Rhenium, 2)
                .input(dust, Tantalum, 3)
                .output(dust, ReneN5, 40)
                .EUt(VA[LuV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Titanium Carbide
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Titanium, 1)
                .input(dust, Carbon, 1)
                .output(dust, TitaniumCarbide, 2)
                .EUt(VA[HV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // Titanium Tungsten Carbide
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, TungstenCarbide, 1)
                .input(dust, TitaniumCarbide, 2)
                .output(dust, TitaniumTungstenCarbide, 3)
                .EUt(VA[EV].toLong())
                .duration(24 * SECOND)
                .buildAndRegister()

            // Trinaquadalloy
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Trinium, 6)
                .input(dust, Naquadah, 2)
                .input(dust, Carbon)
                .output(dust, Trinaquadalloy, 9)
                .EUt(VA[ZPM].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // Enriched Naquadah Alloy
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, NaquadahEnriched, 4)
                .input(dust, Ruridit, 2)
                .input(dust, Rutherfordium)
                .output(dust, EnrichedNaquadahAlloy, 7)
                .EUt(VA[ZPM].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // HDCS
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, TungstenSteel, 12)
                .input(dust, HSSS, 9)
                .input(dust, HSSG, 6)
                .input(dust, Ruridit, 3)
                .input(dust, MagnetoResonatic, 2)
                .input(dust, Plutonium241)
                .output(dust, HDCS, 33)
                .EUt(VA[UHV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // Titan Steel
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, TitaniumTungstenCarbide, 3)
                .input(dust, Jasper, 3)
                .input(dust, Tritanium, 2)
                .output(dust, TitanSteel, 8)
                .EUt(VA[UHV].toLong())
                .duration(35 * SECOND)
                .buildAndRegister()

            // Tairitsium
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, TungstenSteel, 8)
                .input(dust, Naquadria, 7)
                .input(dust, Bedrockium, 4)
                .input(dust, Carbon, 4)
                .input(dust, VanadiumSteel, 3)
                .input(dust, Francium)
                .output(dust, Tairitsium, 26)
                .EUt(VA[UHV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Hastelloy-X78
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, NaquadahAlloy, 10)
                .input(dust, Rhenium, 5)
                .input(dust, Naquadria, 4)
                .input(dust, Gadolinium, 3)
                .input(dust, Strontium, 2)
                .input(dust, Polonium, 3)
                .input(dust, Rutherfordium, 2)
                .input(dust, Fermium)
                .output(dust, HastelloyX78, 30)
                .EUt(VA[UHV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Hastelloy-K243
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, HastelloyX78, 5)
                .input(dust, NiobiumNitride, 2)
                .input(dust, Tritanium, 4)
                .input(dust, TungstenCarbide, 4)
                .input(dust, Promethium, 4)
                .input(dust, Mendelevium)
                .output(dust, HastelloyK243, 20)
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE + 20 * SECOND)
                .buildAndRegister()

            // Pikyonium 64B
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Inconel718, 8)
                .input(dust, EglinSteel, 5)
                .input(dust, NaquadahEnriched, 4)
                .input(dust, TungstenSteel, 4)
                .input(dust, Cerium, 3)
                .input(dust, Antimony, 2)
                .input(dust, Platinum, 2)
                .input(dust, Ytterbium)
                .output(dust, Pikyonium64B, 29)
                .EUt(VA[UV].toLong())
                .duration(45 * SECOND)
                .buildAndRegister()

            // Arceus Alloy 2B
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(dust, Pikyonium64B, 6)
                .input(dust, Vibranium, 4)
                .input(dust, Osmiridium, 2)
                .input(dust, Lawrencium, 3)
                .input(dust, Thallium, 2)
                .input(dust, Astatine, 2)
                .input(dust, Trinium)
                .output(dust, ArceusAlloy2B, 20)
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE + 25 * SECOND)
                .buildAndRegister()

            // Vibranium Tritanium Actinium Iron Superhydride
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Vibranium, 5)
                .input(dust, Tritanium, 5)
                .input(dust, ActiniumSuperhydride)
                .input(dust, BETSPerrhenate)
                .fluidInputs(Iron.getPlasma(L))
                .output(dust, VibraniumTritaniumActiniumIronSuperhydride, 13)
                .EUt(VA[UEV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Hafnium Carbide
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Hafnium)
                .input(dust, Carbon)
                .output(dust, HafniumCarbide, 2)
                .EUt(VA[EV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // Seaborgium Carbide
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Seaborgium)
                .input(dust, Carbon)
                .output(dust, SeaborgiumCarbide, 2)
                .EUt(VA[ZPM].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Superheavy Alloy (Light)
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Rutherfordium)
                .input(dust, Dubnium)
                .input(dust, Seaborgium)
                .input(dust, Bohrium)
                .input(dust, MetastableHassium)
                .input(dust, Meitnerium)
                .input(dust, Darmstadtium)
                .input(dust, Roentgenium)
                .output(dust, SuperheavyAlloyA, 8)
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE + 20 * SECOND)
                .buildAndRegister()

            // Superheavy Alloy (Heavy)
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Copernicium)
                .input(dust, Nihonium)
                .input(dust, MetastableFlerovium)
                .input(dust, Moscovium)
                .input(dust, Livermorium)
                .input(dust, Tennessine)
                .input(dust, MetastableOganesson)
                .input(dust, Tritanium)
                .output(dust, SuperheavyAlloyB, 8)
                .EUt(VA[UIV].toLong())
                .duration(2 * MINUTE + 40 * SECOND)
                .buildAndRegister()

            // Precious Metal Alloy
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Ruthenium)
                .input(dust, Rhodium)
                .input(dust, Palladium)
                .input(dust, Silver)
                .input(dust, Osmium)
                .input(dust, Iridium)
                .input(dust, Platinum)
                .input(dust, Gold)
                .output(dust, PreciousMetalAlloy, 8)
                .EUt(VA[UV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .buildAndRegister()

            // Nitinol 60
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Nickel, 2)
                .input(dust, Titanium, 3)
                .output(dust, Nitinol60, 5)
                .EUt(VA[EV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Abyssal Alloy
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, StainlessSteel, 5)
                .input(dust, TungstenCarbide, 5)
                .input(dust, Nichrome, 5)
                .input(dust, Bronze, 5)
                .input(dust, IncoloyMA956, 5)
                .input(dust, Iodine)
                .input(dust, Germanium)
                .fluidInputs(Radon.getFluid(1000))
                .output(dust, Abyssalloy, 28)
                .EUt(VA[UIV].toLong())
                .duration(2 * MINUTE + 30 * SECOND)
                .buildAndRegister()

            // Fullerene Superconductor
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, TitanSteel, 16)
                .input(dust, LanthanumFullereneNanotube, 4)
                .input(dust, SeaborgiumDopedCarbonNanotube, 4)
                .input(dust, MetastableOganesson, 3)
                .fluidInputs(Xenon.getPlasma(1000))
                .output(dust, FullereneSuperconductor, 28)
                .EUt(VA[UIV].toLong())
                .duration(3 * MINUTE)
                .buildAndRegister()

            // Lanthanum Group Alloy (Light)
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Lanthanum)
                .input(dust, Cerium)
                .input(dust, Praseodymium)
                .input(dust, Neodymium)
                .input(dust, Promethium)
                .input(dust, Samarium)
                .input(dust, Europium)
                .input(dust, Gadolinium)
                .output(dust, LanthanumGroupAlloyA, 8)
                .EUt(VA[UHV].toLong())
                .duration(45 * SECOND)
                .buildAndRegister()

            // Lanthanum Group Alloy (Heavy)
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Terbium)
                .input(dust, Dysprosium)
                .input(dust, Holmium)
                .input(dust, Erbium)
                .input(dust, Thulium)
                .input(dust, Ytterbium)
                .input(dust, Lutetium)
                .input(dust, Scandium)
                .output(dust, LanthanumGroupAlloyB, 8)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // Actinium Group Alloy (Light)
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Actinium)
                .input(dust, Thorium)
                .input(dust, Protactinium)
                .input(dust, Uranium)
                .input(dust, Neptunium)
                .input(dust, Plutonium239)
                .input(dust, Americium)
                .input(dust, Curium)
                .output(dust, ActiniumGroupAlloyA, 8)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // Actinium Group Alloy (Heavy)
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Berkelium)
                .input(dust, Californium)
                .input(dust, Einsteinium)
                .input(dust, Fermium)
                .input(dust, Mendelevium)
                .input(dust, Nobelium)
                .input(dust, Lawrencium)
                .input(dust, Yttrium)
                .output(dust, ActiniumGroupAlloyB, 8)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // Cinobite A243
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Zeron100, 8)
                .input(dust, Naquadria, 4)
                .input(dust, Gadolinium, 3)
                .input(dust, Aluminium, 2)
                .input(dust, Tin)
                .input(dust, Titanium, 6)
                .input(dust, Osmiridium)
                .fluidInputs(Mercury.getFluid(1000))
                .output(dust, CinobiteA243, 26)
                .EUt(VA[UEV].toLong())
                .duration(35 * SECOND)
                .buildAndRegister()

            // Alkali Group Alloy
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Lithium)
                .input(dust, Sodium)
                .input(dust, Potassium)
                .input(dust, Rubidium)
                .input(dust, Caesium)
                .input(dust, Francium)
                .output(dust, AlkaliGroupAlloy, 6)
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Alkali Earth Group Alloy
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(dust, Beryllium)
                .input(dust, Magnesium)
                .input(dust, Calcium)
                .input(dust, Strontium)
                .input(dust, Barium)
                .input(dust, Radium)
                .output(dust, AlkaliEarthGroupAlloy, 6)
                .EUt(VA[LuV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // Light Transition Alloy
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Titanium)
                .input(dust, Vanadium)
                .input(dust, Chrome)
                .input(dust, Manganese)
                .input(dust, Iron)
                .input(dust, Cobalt)
                .input(dust, Nickel)
                .input(dust, Copper)
                .output(dust, TransitionAlloyA, 8)
                .EUt(VA[ZPM].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Heavy Transition Alloy
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Aluminium)
                .input(dust, Zinc)
                .input(dust, Gallium)
                .input(dust, Germanium)
                .input(dust, Cadmium)
                .input(dust, Indium)
                .input(dust, Tin)
                .input(dust, Antimony)
                .output(dust, TransitionAlloyB, 8)
                .EUt(VA[ZPM].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Transition Alloy
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, TransitionAlloyA, 8)
                .input(dust, TransitionAlloyB, 8)
                .output(dust, TransitionAlloy, 16)
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Refractory Alloy
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Zirconium)
                .input(dust, Niobium)
                .input(dust, Molybdenum)
                .input(dust, Technetium)
                .input(dust, Hafnium)
                .input(dust, Tantalum)
                .input(dust, Tungsten)
                .input(dust, Rhenium)
                .output(dust, RefractoryAlloy, 8)
                .EUt(VA[UV].toLong())
                .duration(1 * MINUTE + 20 * SECOND)
                .buildAndRegister()

            // Toxic Alloy
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Arsenic)
                .input(dust, Tellurium)
                .input(dust, Mercury)
                .input(dust, Thallium)
                .input(dust, Lead)
                .input(dust, Bismuth)
                .input(dust, Polonium)
                .input(dust, Astatine)
                .output(dust, ToxicAlloy, 8)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE + 40 * SECOND)
                .buildAndRegister()

            // Rare Earth Alloy
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, LanthanumGroupAlloyA, 8)
                .input(dust, LanthanumGroupAlloyB, 8)
                .input(dust, ActiniumGroupAlloyA, 8)
                .input(dust, ActiniumGroupAlloyB, 8)
                .output(dust, RareEarthAlloy, 32)
                .EUt(VA[IV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Light Non-Metallic Mixture
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .fluidOutputs(NonMetallicMixtureA.getFluid(6000))
                .EUt(VA[LuV].toLong())
                .duration(45 * SECOND)
                .buildAndRegister()

            // Heavy Non-Metallic Mixture
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(dust, Boron)
                .input(dust, Carbon)
                .input(dust, Silicon)
                .input(dust, Phosphorus)
                .input(dust, Sulfur)
                .input(dust, Selenium)
                .input(dust, Iodine)
                .fluidOutputs(NonMetallicMixtureB.getFluid(7000))
                .EUt(VA[LuV].toLong())
                .duration(45 * SECOND)
                .buildAndRegister()

            // Noble Gas Mixture
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .fluidInputs(Helium.getFluid(1000))
                .fluidInputs(Neon.getFluid(1000))
                .fluidInputs(Argon.getFluid(1000))
                .fluidInputs(Krypton.getFluid(1000))
                .fluidInputs(Xenon.getFluid(1000))
                .fluidInputs(Radon.getFluid(1000))
                .fluidOutputs(NobleGasMixture.getFluid(6000))
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // Periodicium
            LARGE_MIXER_RECIPES.recipeBuilder()
                .input(dust, AlkaliGroupAlloy, 6)             // Li Na K  Rb Cs Fr
                .input(dust, AlkaliEarthGroupAlloy, 6)        // Be Mg Ca Sr Ba Ra
                .input(dust, TransitionAlloy, 16)             // Ti V  Cr Mn Fe Co Ni Cu
                                                                     // Al Zn Ga Ge Cd In Sn Sb
                .input(dust, RefractoryAlloy, 8)              // Zr Nb Mo Tc Hf Ta W  Re
                .input(dust, PreciousMetalAlloy, 8)           // Ru Rh Pd Ag Os Ir Pt Au
                .input(dust, ToxicAlloy, 8)                   // As Te Hg Tl Pb Bi Po At
                .input(dust, RareEarthAlloy, 32)              // La Ce Pr Nd Pm Sm Eu Ga
                                                                     // Tb Dy Ho Er Tm Yb Lu Sc
                                                                     // Ac Th Pa U  Np Pu Am Cm
                                                                     // Bk Cf Es Fm Md No Lr Y
                .input(dust, SuperheavyAlloyA, 8)             // Rf Db Sg Bh Hs Mt Ds Rg
                .input(dust, SuperheavyAlloyB, 8)             // Cn Nh Fl Mc Lv Ts Og Tr
                .fluidInputs(NonMetallicMixtureA.getFluid(6000))     // H  N  O  F  Cl Br
                .fluidInputs(NonMetallicMixtureB.getFluid(7000))     // B  C  Si P  S  Se I
                .fluidInputs(NobleGasMixture.getFluid(6000))         // He Ne Ar Kr Xe Rn
                .output(dust, Periodicium, 118)
                .fluidOutputs(Tritanium.getFluid(L)) // From Superheavy Alloy B.
                .EUt(VA[UXV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // Legendarium
            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Naquadria)
                .input(dust, Trinium)
                .input(dust, Duranium)
                .input(dust, Tritanium)
                .input(dust, Neutronium)
                .input(dust, Adamantium)
                .input(dust, Vibranium)
                .input(dust, Taranium)
                .output(dust, Legendarium, 8)
                .EUt(VA[UXV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .buildAndRegister()

            // Blazing Pyrotheum
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Blaze, 2)
                .input(dust, Redstone)
                .input(dust, Sulfur)
                .output(dust, BlazingPyrotheum, 4)
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Gelid Cryotheum
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Ice, 2)
                .input(dust, Electrotine)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, GelidCryotheum, 4)
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Tectonic Petrotheum
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Clay, 2)
                .input(dust, Obsidian)
                .input(dust, Stone)
                .output(dust, TectonicPetrotheum, 4)
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Zephyrean Aerotheum
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(SAND_DUST, 2)
                .input(dust, Saltpeter)
                .fluidInputs(Air.getFluid(1000))
                .output(dust, ZephyreanAerotheum, 4)
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // UU Matter
            LARGE_MIXER_RECIPES.recipeBuilder()
                .fluidInputs(BosonicUUMatter.getFluid(1000))
                .fluidInputs(FermionicUUMatter.getFluid(1000))
                .fluidInputs(FreeElectronGas.getFluid(2000))
                .fluidOutputs(UUMatter.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

        }

    }

}