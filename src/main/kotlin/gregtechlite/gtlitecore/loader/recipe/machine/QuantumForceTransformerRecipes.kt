package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Actinium
import gregtech.api.unification.material.Materials.Agar
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.AquaRegia
import gregtech.api.unification.material.Materials.Astatine
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Bastnasite
import gregtech.api.unification.material.Materials.BatteryAlloy
import gregtech.api.unification.material.Materials.Bauxite
import gregtech.api.unification.material.Materials.Berkelium
import gregtech.api.unification.material.Materials.Biomass
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Cerium
import gregtech.api.unification.material.Materials.Chalcopyrite
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.DyeBlack
import gregtech.api.unification.material.Materials.DyeBlue
import gregtech.api.unification.material.Materials.DyeBrown
import gregtech.api.unification.material.Materials.DyeCyan
import gregtech.api.unification.material.Materials.DyeGray
import gregtech.api.unification.material.Materials.DyeGreen
import gregtech.api.unification.material.Materials.DyeLightBlue
import gregtech.api.unification.material.Materials.DyeLightGray
import gregtech.api.unification.material.Materials.DyeLime
import gregtech.api.unification.material.Materials.DyeMagenta
import gregtech.api.unification.material.Materials.DyeOrange
import gregtech.api.unification.material.Materials.DyePink
import gregtech.api.unification.material.Materials.DyePurple
import gregtech.api.unification.material.Materials.DyeRed
import gregtech.api.unification.material.Materials.DyeWhite
import gregtech.api.unification.material.Materials.DyeYellow
import gregtech.api.unification.material.Materials.Dysprosium
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.Epoxy
import gregtech.api.unification.material.Materials.Erbium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Gadolinium
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.Glue
import gregtech.api.unification.material.Materials.GlycerylTrinitrate
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.Holmium
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Ilmenite
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.IndiumGalliumPhosphide
import gregtech.api.unification.material.Materials.Iodine
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lanthanum
import gregtech.api.unification.material.Materials.Lawrencium
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Meat
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Molybdenite
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Monazite
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neodymium
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Nobelium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Pitchblende
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.PlatinumGroupSludge
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Polonium
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.PolyphenyleneSulfide
import gregtech.api.unification.material.Materials.Polytetrafluoroethylene
import gregtech.api.unification.material.Materials.PolyvinylChloride
import gregtech.api.unification.material.Materials.Praseodymium
import gregtech.api.unification.material.Materials.Promethium
import gregtech.api.unification.material.Materials.Protactinium
import gregtech.api.unification.material.Materials.Pyrite
import gregtech.api.unification.material.Materials.Radium
import gregtech.api.unification.material.Materials.RareEarth
import gregtech.api.unification.material.Materials.RawGrowthMedium
import gregtech.api.unification.material.Materials.ReinforcedEpoxyResin
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Rhodium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Rubber
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.Ruthenium
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.Samarium
import gregtech.api.unification.material.Materials.Scandium
import gregtech.api.unification.material.Materials.Scheelite
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Sphalerite
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.SterileGrowthMedium
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.StyreneButadieneRubber
import gregtech.api.unification.material.Materials.Sugar
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Tennessine
import gregtech.api.unification.material.Materials.Terbium
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Tungstate
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.Uraninite
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.Wheat
import gregtech.api.unification.material.Materials.Ytterbium
import gregtech.api.unification.material.Materials.Yttrium
import gregtech.api.unification.material.Materials.Zircon
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.STEM_CELLS
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.QUANTUM_FORCE_TRANSFORMER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AscorbicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BFGF
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Biotin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Blood
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BrevibacteriumFlavum
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BurntSienna
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CAT
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cellulose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CobaltAluminate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicBoronNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicSiliconNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicZirconia
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CupriavidusNecator
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CyanIndigo
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CyclotetramethyleneTetranitroamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DiaminostilbenedisulfonicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diketopyrrolopyrrole
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DirectBrown77
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EGF
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EosinY
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Erythrosine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EscherichiaColi
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fat
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorescein
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fructose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Glucose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyEnrichedTaraniumFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexanitrohexaaxaisowurtzitane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Indigo
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonE
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonK
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kevlar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadChromate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LightEnrichedTaraniumFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LuTmYVO
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MediumEnrichedTaraniumFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NaquadriaEnergetic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NdYAG
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nigrosin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Octaazacubane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PedotPSS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PedotTMA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phosphorene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Plutonium244
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polyetheretherketone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolyethyleneTerephthalate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polymethylmethacrylate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolyphosphonitrileFluoroRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polystyrene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolytetramethyleneGlycolRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrHoYLF
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrimordialMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrussianBlue
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ScheelesGreen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Sienna
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Sorbose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StreptococcusPyogenes
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Strontianite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TachyonRichTemporalFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitaniumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Universium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Xylose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Yeast
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Zylon
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ADHESION_PROMOTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ADVANCED_PLASTIC_POLYMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ADVANCED_RADIOACTIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ADVANCED_RUBBER_POLYMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ARTIFICIAL_GEM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_BASE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_BIOLOGICAL_INTELLIGENCE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_HIGH_EXPLOSIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_NAQUADAH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ORGANIC_DYE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_PLASTIC_POLYMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_PLATINUM_GROUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_RADIOACTIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_RARE_EARTH_GROUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_RARE_METAL_GROUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_RAW_INTELLIGENCE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_RUBBER_POLYMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_STELLAR_CORE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_TEMPORAL_HARMONY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_TITANIUM_TUNGSTEN_INDIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ULTIMATE_PLASTIC_POLYMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ULTIMATE_RADIOACTIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENERGISED_TESSERACT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAVITON_SHARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NAQUADRIA_SUPERSOLID
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.POLYMER_INSULATOR_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.STABLE_ADHESIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPERCONDUCTOR_COMPOSITE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TIMEPIECE

internal object QuantumForceTransformerRecipes
{

    // @formatter:off

    fun init()
    {
        catalystRecipes()

        // Rubber 1
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_RUBBER_POLYMER)
            .input(dust, Carbon, 64)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Chlorine.getFluid(16000))
            .chancedFluidOutput(SiliconeRubber.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(StyreneButadieneRubber.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(PolyphenyleneSulfide.getFluid(L * 128), 2500, 0)
            .chancedFluidOutput(Rubber.getFluid(L * 256), 2500, 0)
            .EUt(VA[ZPM])
            .duration(20 * SECOND)
            .tier(1)
            .buildAndRegister()

        // Rubber 2
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_ADVANCED_RUBBER_POLYMER)
            .input(dust, Carbon, 64)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Chlorine.getFluid(16000))
            .fluidInputs(Fluorine.getFluid(16000))
            .fluidInputs(Nitrogen.getFluid(16000))
            .chancedFluidOutput(PolyphosphonitrileFluoroRubber.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(PolytetramethyleneGlycolRubber.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(Polyetheretherketone.getFluid(L * 128), 2500, 0)
            .chancedFluidOutput(Zylon.getFluid(L * 128), 2500, 0)
            .chancedFluidOutput(SiliconeRubber.getFluid(L * 256), 2500, 0)
            .chancedFluidOutput(StyreneButadieneRubber.getFluid(L * 256), 2500, 0)
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .tier(2)
            .buildAndRegister()

        // Plastic 1
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_PLASTIC_POLYMER)
            .input(dust, Carbon, 64)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Chlorine.getFluid(16000))
            .fluidInputs(Fluorine.getFluid(16000))
            .chancedFluidOutput(Polyethylene.getFluid(L * 256), 2500, 0)
            .chancedFluidOutput(PolyvinylChloride.getFluid(L * 256), 2500, 0)
            .chancedFluidOutput(Polystyrene.getFluid(L * 128), 2500, 0)
            .chancedFluidOutput(Polytetrafluoroethylene.getFluid(L * 128), 2500, 0)
            .chancedFluidOutput(Epoxy.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(Polybenzimidazole.getFluid(L * 64), 2500, 0)
            .EUt(VA[ZPM])
            .duration(20 * SECOND)
            .tier(1)
            .buildAndRegister()

        // Plastic 2: Kevlar, KaptonE, KaptonK, PMMA, PET, EPoxy
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_ADVANCED_PLASTIC_POLYMER)
            .input(dust, Carbon, 64)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Nitrogen.getFluid(16000))
            .chancedFluidOutput(KaptonK.getFluid(L * 256), 2500, 0)
            .chancedFluidOutput(ReinforcedEpoxyResin.getFluid(L * 256), 2500, 0)
            .chancedFluidOutput(Polymethylmethacrylate.getFluid(L * 128), 2500, 0)
            .chancedFluidOutput(KaptonE.getFluid(L * 128), 2500, 0)
            .chancedFluidOutput(Kevlar.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(PolyethyleneTerephthalate.getFluid(L * 64), 2500, 0)
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .tier(2)
            .buildAndRegister()

        // Plastic 3
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_ULTIMATE_PLASTIC_POLYMER)
            .input(dust, Carbon, 64)
            .input(dust, Phosphorus, 64)
            .input(dust, Sulfur, 64)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Nitrogen.getFluid(16000))
            .chancedOutput(POLYMER_INSULATOR_FOIL, 64, 2500, 0)
            .chancedFluidOutput(PedotPSS.getFluid(L * 256), 2500, 0)
            .chancedFluidOutput(PedotTMA.getFluid(L * 256), 2500, 0)
            .chancedFluidOutput(CarbonNanotube.getFluid(L * 128), 2500, 0)
            .chancedFluidOutput(Phosphorene.getFluid(L * 128), 2500, 0)
            .chancedFluidOutput(Fullerene.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(FullerenePolymerMatrix.getFluid(L * 64), 2500, 0)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .tier(3)
            .buildAndRegister()

        // Platinum Group
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_PLATINUM_GROUP)
            .input(dust, PlatinumGroupSludge, 64)
            .chancedOutput(dust, Platinum, 64, 2500, 0)
            .chancedOutput(dust, Palladium, 64, 2500, 0)
            .chancedOutput(dust, Ruthenium, 64, 2500, 0)
            .chancedOutput(dust, Rhodium, 64, 2500, 0)
            .chancedOutput(dust, Iridium, 64, 2500, 0)
            .chancedOutput(dust, Osmium, 64, 2500, 0)
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .tier(1)
            .buildAndRegister()

        // Radioactive 1
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_RADIOACTIVE)
            .input(dust, Thorium, 32)
            .input(dust, Uranium238, 32)
            .chancedOutput(dust, Uranium235, 64, 2500, 0)
            .chancedOutput(dust, Uranium238, 64, 2500, 0)
            .chancedOutput(dust, Plutonium239, 64, 2500, 0)
            .chancedOutput(dust, Plutonium241, 64, 2500, 0)
            .chancedOutput(dust, Plutonium244, 64, 2500, 0)
            .chancedOutput(dust, Thorium, 64, 2500, 0)
            .EUt(VA[ZPM])
            .duration(20 * SECOND)
            .tier(1)
            .buildAndRegister()

        // Radioactive 2
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_ADVANCED_RADIOACTIVE)
            .input(dust, Protactinium, 32)
            .input(dust, Plutonium239, 32)
            .chancedOutput(dust, Protactinium, 64, 2500, 0)
            .chancedOutput(dust, Neptunium, 64, 2500, 0)
            .chancedOutput(dust, Americium, 64, 2500, 0)
            .chancedOutput(dust, Curium, 64, 2500, 0)
            .chancedOutput(dust, Berkelium, 64, 2500, 0)
            .chancedOutput(dust, Californium, 64, 2500, 0)
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .tier(1)
            .buildAndRegister()

        // Radioactive 3
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_ULTIMATE_PLASTIC_POLYMER)
            .input(dust, Plutonium244, 64)
            .chancedOutput(dust, Einsteinium, 64, 2500, 0)
            .chancedOutput(dust, Fermium, 64, 2500, 0)
            .chancedOutput(dust, Mendelevium, 64, 2500, 0)
            .chancedOutput(dust, Nobelium, 64, 2500, 0)
            .chancedOutput(dust, Lawrencium, 64, 2500, 0)
            .chancedOutput(dust, Rutherfordium, 64, 2500, 0)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .tier(2)
            .buildAndRegister()

        // Titanium Tungsten Indium
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_TITANIUM_TUNGSTEN_INDIUM)
            .input(dust, Lead, 16)
            .input(dust, Bauxite, 32)
            .input(dust, Tungstate, 16)
            .chancedOutput(dust, Titanium, 64, 2500, 0)
            .chancedOutput(dust, TungstenSteel, 64, 2500, 0)
            .chancedOutput(dust, TungstenCarbide, 64, 2500, 0)
            .chancedOutput(dust, Indium, 64, 2500, 0)
            .chancedOutput(dust, Niobium, 64, 2500, 0)
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .tier(1)
            .buildAndRegister()

        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_TITANIUM_TUNGSTEN_INDIUM)
            .input(dust, Rutile, 32)
            .input(dust, Scheelite, 16)
            .input(dust, Ilmenite, 16)
            .chancedOutput(dust, Titanium, 64, 2500, 0)
            .chancedOutput(dust, TungstenSteel, 64, 2500, 0)
            .chancedOutput(dust, TitaniumCarbide, 64, 2500, 0)
            .chancedOutput(dust, Indium, 64, 2500, 0)
            .chancedOutput(dust, Tantalum, 2500, 0)
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .tier(1)
            .buildAndRegister()

        // Adhesives
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_ADHESION_PROMOTER)
            .input(dust, Carbon, 32)
            .input(dust, Tin, 32)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .chancedOutput(STABLE_ADHESIVE, 2000, 0)
            .chancedFluidOutput(Glue.getFluid(256000), 2500, 0)
            .chancedFluidOutput(SolderingAlloy.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(BatteryAlloy.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(TinAlloy.getFluid(L * 128), 2500, 0)
            .chancedFluidOutput(Tin.getFluid(L * 128), 2500, 0)
            .chancedFluidOutput(Lead.getFluid(L * 256), 2500, 0)
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .tier(1)
            .buildAndRegister()

        // REE 1
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_RARE_EARTH_GROUP)
            .input(dust, Monazite, 32)
            .chancedOutput(SUPERCONDUCTOR_COMPOSITE, 2000, 0)
            .chancedOutput(dust, Lanthanum, 64, 2500, 0)
            .chancedOutput(dust, Cerium, 64, 2500, 0)
            .chancedOutput(dust, Praseodymium, 64, 2500, 0)
            .chancedOutput(dust, Neodymium, 64, 2500, 0)
            .chancedOutput(dust, Promethium, 64, 2500, 0)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .tier(2)
            .buildAndRegister()

        // REE 2
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_RARE_EARTH_GROUP)
            .input(dust, Bastnasite, 32)
            .chancedOutput(SUPERCONDUCTOR_COMPOSITE, 2000, 0)
            .chancedOutput(dust, Samarium, 64, 2500, 0)
            .chancedOutput(dust, Europium, 64, 2500, 0)
            .chancedOutput(dust, Gadolinium, 64, 2500, 0)
            .chancedOutput(dust, Terbium, 64, 2500, 0)
            .chancedOutput(dust, Dysprosium, 64, 2500, 0)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .tier(2)
            .buildAndRegister()

        // REE 3
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_RARE_EARTH_GROUP)
            .input(dust, RareEarth, 32)
            .chancedOutput(SUPERCONDUCTOR_COMPOSITE, 2000, 0)
            .chancedOutput(dust, Holmium, 64, 2500, 0)
            .chancedOutput(dust, Erbium, 64, 2500, 0)
            .chancedOutput(dust, Thulium, 64, 2500, 0)
            .chancedOutput(dust, Ytterbium, 64, 2500, 0)
            .chancedOutput(dust, Lutetium, 64, 2500, 0)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .tier(2)
            .buildAndRegister()

        // Rare Metal Group
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_RARE_METAL_GROUP)
            .input(dust, Sphalerite, 32)
            .input(dust, Zircon, 16)
            .input(dust, Molybdenite, 16)
            .chancedOutput(dust, Gallium, 64, 2500, 0)
            .chancedOutput(dust, Molybdenum, 64, 2500, 0)
            .chancedOutput(dust, Germanium, 64, 2500, 0)
            .chancedOutput(dust, Rhenium, 64, 2500, 0)
            .chancedOutput(dust, Zirconium, 64, 2500, 0)
            .chancedOutput(dust, Hafnium, 64, 2500, 0)
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .tier(1)
            .buildAndRegister()

        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_RARE_METAL_GROUP)
            .input(dust, Sphalerite, 32)
            .input(dust, Pyrite, 16)
            .input(dust, Strontianite, 16)
            .chancedOutput(dust, Gallium, 64, 2500, 0)
            .chancedOutput(dust, Molybdenum, 64, 2500, 0)
            .chancedOutput(dust, Germanium, 64, 2500, 0)
            .chancedOutput(dust, Thallium, 64, 2500, 0)
            .chancedOutput(dust, Strontium, 64, 2500, 0)
            .chancedOutput(dust, Rubidium, 64, 2500, 0)
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .tier(1)
            .buildAndRegister()

        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_RARE_METAL_GROUP)
            .input(dust, Pitchblende, 32)
            .input(dust, Uraninite, 16)
            .input(dust, Chalcopyrite, 16)
            .chancedOutput(dust, Polonium, 64, 2500, 0)
            .chancedOutput(dust, Radium, 64, 2500, 0)
            .chancedOutput(dust, Selenium, 64, 2500, 0)
            .chancedOutput(dust, Tellurium, 64, 2500, 0)
            .chancedOutput(dust, Yttrium, 64, 2500, 0)
            .chancedOutput(dust, Scandium, 64, 2500, 0)
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .tier(1)
            .buildAndRegister()

        // Naquadah
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_NAQUADAH)
            .input(dust, Naquadah, 32)
            .input(dust, Antimony, 16)
            .input(dust, Sulfur, 16)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Fluorine.getFluid(16000))
            .chancedOutput(NAQUADRIA_SUPERSOLID, 2000, 0)
            .chancedOutput(dust, Naquadah, 64, 2500, 0)
            .chancedOutput(dust, NaquadahEnriched, 64, 2500, 0)
            .chancedOutput(dust, Naquadria, 64, 2500, 0)
            .chancedOutput(dust, Trinium, 64, 2500, 0)
            .chancedOutput(dust, Technetium, 64, 2500, 0)
            .chancedFluidOutput(Titanium.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(Barium.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(Indium.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(Gallium.getFluid(L * 64), 2500, 0)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .tier(3)
            .buildAndRegister()

        // Raw Intelligence
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_RAW_INTELLIGENCE)
            .input(dust, Calcium, 32)
            .input(dust, Agar, 32)
            .input(dust, Wheat, 32)
            .fluidInputs(DistilledWater.getFluid(16000))
            .chancedOutput(STEM_CELLS, 64, 2500, 0)
            .chancedOutput(STEM_CELLS, 64, 2500, 0)
            .chancedOutput(STEM_CELLS, 64, 2500, 0)
            .chancedOutput(STEM_CELLS, 64, 2500, 0)
            .chancedOutput(STEM_CELLS, 64, 2500, 0)
            .chancedOutput(STEM_CELLS, 64, 2500, 0)
            .chancedFluidOutput(RawGrowthMedium.getFluid(64000), 2500, 0)
            .chancedFluidOutput(SterileGrowthMedium.getFluid(64000), 2500, 0)
            .chancedFluidOutput(Blood.getFluid(128000), 2500, 0)
            .chancedFluidOutput(Fat.getFluid(128000), 2500, 0)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .tier(2)
            .buildAndRegister()

        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_RAW_INTELLIGENCE)
            .input(dust, Yeast, 32)
            .input(dust, Meat, 32)
            .fluidInputs(DistilledWater.getFluid(16000))
            .chancedOutput(dust, BrevibacteriumFlavum, 64, 2500, 0)
            .chancedOutput(dust, CupriavidusNecator, 64, 2500, 0)
            .chancedOutput(dust, StreptococcusPyogenes, 64, 2500, 0)
            .chancedOutput(dust, EscherichiaColi, 64, 2500, 0)
            .chancedOutput(dust, Sugar, 64, 2500, 0)
            .chancedOutput(dust, Sugar, 64, 2500, 0)
            .chancedFluidOutput(BFGF.getFluid(64000), 2500, 0)
            .chancedFluidOutput(EGF.getFluid(64000), 2500, 0)
            .chancedFluidOutput(CAT.getFluid(128000), 2500, 0)
            .chancedFluidOutput(Biomass.getFluid(256000), 2500, 0)
            .chancedFluidOutput(Blood.getFluid(256000), 2500, 0)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .tier(2)
            .buildAndRegister()

        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_RAW_INTELLIGENCE)
            .input(dust, Sugar, 64)
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Oxygen.getFluid(16000))
            .chancedOutput(dust, Glucose, 64, 2500, 0)
            .chancedOutput(dust, Fructose, 64, 2500, 0)
            .chancedOutput(dust, Sorbose, 64, 2500, 0)
            .chancedOutput(dust, Xylose, 64, 2500, 0)
            .chancedOutput(dust, Cellulose, 64, 2500, 0)
            .chancedOutput(dust, Biotin, 64, 2500, 0)
            .chancedFluidOutput(AscorbicAcid.getFluid(64000), 2500, 0)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .tier(2)
            .buildAndRegister()

        // Biological Intelligence
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_BIOLOGICAL_INTELLIGENCE)
            .input(STEM_CELLS, 16)
            .fluidInputs(Bismuth.getPlasma(16000))
            .fluidInputs(Tin.getPlasma(16000))
            .chancedOutput(STEM_CELLS, 64, 2500, 0)
            .chancedOutput(STEM_CELLS, 64, 2500, 0)
            .chancedOutput(STEM_CELLS, 64, 2500, 0)
            .chancedOutput(dust, Iodine, 64, 2500, 0)
            .chancedOutput(dust, Astatine, 64, 2500, 0)
            .chancedOutput(dust, Tennessine, 64, 2500, 0)
            .chancedFluidOutput(MutatedLivingSolder.getFluid(128000), 2500, 0)
            .chancedFluidOutput(Bromine.getFluid(128000), 2500, 0)
            .chancedFluidOutput(RawGrowthMedium.getFluid(256000), 2500, 0)
            .chancedFluidOutput(SterileGrowthMedium.getFluid(256000), 2500, 0)
            .EUt(VA[UEV])
            .duration(20 * SECOND)
            .tier(3)
            .buildAndRegister()

        // High Explosives
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_HIGH_EXPLOSIVE)
            .input(dust, Actinium, 32)
            .input(dust, Carbon, 64)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Nitrogen.getFluid(16000))
            .chancedOutput(dust, Hexanitrohexaaxaisowurtzitane, 64, 2500, 0)
            .chancedOutput(dust, Octaazacubane, 64, 2500, 0)
            .chancedOutput(dust, ActiniumSuperhydride, 64, 2500, 0)
            .chancedFluidOutput(CyclotetramethyleneTetranitroamine.getFluid(128000), 2500, 0)
            .chancedFluidOutput(GlycerylTrinitrate.getFluid(256000), 2500, 0)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .tier(2)
            .buildAndRegister()

        // Temporal Harmony
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_TEMPORAL_HARMONY)
            .input(GRAVITON_SHARD)
            .input(ENERGISED_TESSERACT)
            .fluidInputs(PrimordialMatter.getFluid(L * 8))
            .chancedOutput(dust, Shirabon, 64, 2500, 0)
            .chancedOutput(TIMEPIECE, 1, 2500, 0)
            .chancedFluidOutput(Eternity.getFluid(L * 64), 2500, 0)
            .chancedFluidOutput(TachyonRichTemporalFluid.getFluid(L * 128), 2500, 0)
            .EUt(VA[UIV])
            .duration(20 * SECOND)
            .tier(4)
            .buildAndRegister()

        // Bedrockium
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_STELLAR_CORE)
            .input(dust, Bedrockium, 32)
            .input(dust, Naquadah, 32)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Nitrogen.getFluid(16000))
            .fluidInputs(Fluorine.getFluid(16000))
            .chancedOutput(dust, Bedrockium, 64, 2500, 0)
            .chancedOutput(dust, Adamantium, 64, 2500, 0)
            .chancedOutput(dust, Vibranium, 64, 2500, 0)
            .chancedOutput(dust, Taranium, 64, 2500, 0)
            .chancedOutput(dust, Osmiridium, 64, 2500, 0)
            .chancedOutput(dust, Rhenium, 64, 2500, 0)
            .chancedFluidOutput(HeavyEnrichedTaraniumFuel.getFluid(256000), 2500, 0)
            .chancedFluidOutput(MediumEnrichedTaraniumFuel.getFluid(256000), 2500, 0)
            .chancedFluidOutput(LightEnrichedTaraniumFuel.getFluid(256000), 2500, 0)
            .chancedFluidOutput(IndiumGalliumPhosphide.getFluid(128000), 2500, 0)
            .chancedFluidOutput(Ruridit.getFluid(128000), 2500, 0)
            .chancedFluidOutput(NaquadriaEnergetic.getFluid(64000), 2500, 0)
            .EUt(VA[UEV])
            .duration(20 * SECOND)
            .tier(3)
            .buildAndRegister()

        // Artificial Gems
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_ARTIFICIAL_GEM)
            .input(dust, Zirconium, 16)
            .input(dust, Silicon, 16)
            .input(dust, Boron, 16)
            .input(dust, RareEarth, 32)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Nitrogen.getFluid(16000))
            .chancedOutput(gem, CubicZirconia, 64, 2500, 0)
            .chancedOutput(gem, CubicBoronNitride, 64, 2500, 0)
            .chancedOutput(gem, CubicSiliconNitride, 64, 2500, 0)
            .chancedOutput(gem, NdYAG, 64, 2500, 0)
            .chancedOutput(gem, LuTmYVO, 64, 2500, 0)
            .chancedOutput(gem, PrHoYLF, 64, 2500, 0)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .tier(2)
            .buildAndRegister()

        // Organic Dye 1
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_ORGANIC_DYE)
            .input(dust, Lead, 64)
            .input(dust, Chrome, 16)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Nitrogen.getFluid(16000))
            .chancedOutput(dust, LeadNitrate, 64, 2500, 0)
            .chancedOutput(dust, DiaminostilbenedisulfonicAcid, 64, 2500, 0)
            .chancedOutput(dust, LeadChromate, 64, 2500, 0)
            .chancedOutput(dust, Fluorescein, 64, 2500, 0)
            .chancedOutput(dust, CyanIndigo, 64, 2500, 0)
            .chancedOutput(dust, Indigo, 64, 2500, 0)
            .chancedFluidOutput(DyeWhite.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyeOrange.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyeMagenta.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyeLightBlue.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyeYellow.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyeLime.getFluid(256000), 2500, 0)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .tier(1)
            .buildAndRegister()

        // Organic Dye 2
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_ORGANIC_DYE)
            .input(dust, Iron, 16)
            .input(dust, Cobalt, 16)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Nitrogen.getFluid(16000))
            .chancedOutput(dust, CobaltAluminate, 64, 2500, 0)
            .chancedOutput(dust, PrussianBlue, 64, 2500, 0)
            .chancedOutput(dust, Sienna, 64, 2500, 0)
            .chancedOutput(dust, DirectBrown77, 64, 2500, 0)
            .chancedOutput(dust, ScheelesGreen, 64, 2500, 0)
            .chancedOutput(dust, Erythrosine, 64, 2500, 0)
            .chancedFluidOutput(DyePink.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyeGray.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyeLightGray.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyeCyan.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyePurple.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyeBlue.getFluid(256000), 2500, 0)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .tier(1)
            .buildAndRegister()

        // Organic Dye 3
        QUANTUM_FORCE_TRANSFORMER_RECIPES.recipeBuilder()
            .notConsumable(CATALYST_ORGANIC_DYE)
            .input(dust, Carbon, 32)
            .fluidInputs(Oxygen.getFluid(16000))
            .fluidInputs(Hydrogen.getFluid(16000))
            .fluidInputs(Nitrogen.getFluid(16000))
            .chancedOutput(dust, BurntSienna, 64, 2500, 0)
            .chancedOutput(dust, Graphite, 64, 2500, 0)
            .chancedOutput(dust, Graphene, 64, 2500, 0)
            .chancedOutput(dust, Nigrosin, 64, 2500, 0)
            .chancedOutput(dust, Diketopyrrolopyrrole, 64, 2500, 0)
            .chancedOutput(dust, EosinY, 64, 2500, 0)
            .chancedFluidOutput(DyeBrown.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyeGreen.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyeRed.getFluid(256000), 2500, 0)
            .chancedFluidOutput(DyeBlack.getFluid(256000), 2500, 0)
            .chancedFluidOutput(SulfuricAcid.getFluid(128000), 2500, 0)
            .chancedFluidOutput(AquaRegia.getFluid(128000), 2500, 0)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .tier(1)
            .buildAndRegister()
    }

    private fun catalystRecipes()
    {
        // Catalyst Base
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(20)
            .input(plate, Steel, 8)
            .input(wireFine, Copper, 4)
            .input(screw, Tin, 6)
            .output(CATALYST_BASE)
            .EUt(VH[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Rubber Polymer Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, SiliconeRubber, 64)
            .input(dust, StyreneButadieneRubber, 64)
            .input(dust, Rubber, 64)
            .input(dust, PolyphenyleneSulfide, 64)
            .input(nanite, Carbon, 16)
            .fluidInputs(Neutronium.getFluid(L * 4))
            .output(CATALYST_RUBBER_POLYMER)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Advanced Rubber Polymer Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, PolyphosphonitrileFluoroRubber, 64)
            .input(dust, PolytetramethyleneGlycolRubber, 64)
            .input(dust, Polyetheretherketone, 64)
            .input(dust, Zylon, 64)
            .input(nanite, Carbon, 16)
            .fluidInputs(CosmicNeutronium.getFluid(L * 4))
            .output(CATALYST_ADVANCED_RUBBER_POLYMER)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Plastic Polymer Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, Polybenzimidazole, 64)
            .input(dust, Polytetrafluoroethylene, 64)
            .input(nanite, Carbon, 16)
            .fluidInputs(Neutronium.getFluid(L * 4))
            .output(CATALYST_PLASTIC_POLYMER)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Advanced Plastic Polymer Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, Kevlar, 64)
            .input(dust, KaptonE, 64)
            .input(dust, PolyethyleneTerephthalate, 64)
            .input(dust, Polymethylmethacrylate, 64)
            .input(nanite, Carbon, 16)
            .fluidInputs(CosmicNeutronium.getFluid(L * 4))
            .output(CATALYST_ADVANCED_PLASTIC_POLYMER)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Ultimate Plastic Polymer Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, FullerenePolymerMatrix, 64)
            .input(dust, CarbonNanotube, 64)
            .input(dust, Fullerene, 64)
            .input(dust, Phosphorene, 64)
            .input(dust, PedotPSS, 64)
            .input(dust, PedotTMA, 64)
            .input(nanite, TranscendentMetal)
            .fluidInputs(Shirabon.getFluid(L * 4))
            .output(CATALYST_ULTIMATE_PLASTIC_POLYMER)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Platinum Group Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, RhodiumPlatedPalladium, 64)
            .input(dust, Osmiridium, 64)
            .input(dust, Ruridit, 64)
            .input(nanite, Carbon, 16)
            .fluidInputs(Neutronium.getFluid(L * 4))
            .output(CATALYST_PLATINUM_GROUP)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Radioactive Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, Uranium235, 64)
            .input(dust, Uranium238, 64)
            .input(dust, Plutonium239, 64)
            .input(dust, Plutonium241, 64)
            .input(dust, Plutonium244, 64)
            .input(dust, Thorium, 64)
            .input(nanite, Carbon, 16)
            .fluidInputs(Tritanium.getFluid(L * 4))
            .output(CATALYST_RADIOACTIVE)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Advanced Radioactive Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, Protactinium, 64)
            .input(dust, Neptunium, 64)
            .input(dust, Americium, 64)
            .input(dust, Curium, 64)
            .input(dust, Berkelium, 64)
            .input(dust, Californium, 64)
            .input(nanite, Neutronium, 4)
            .fluidInputs(Neutronium.getFluid(L * 4))
            .output(CATALYST_ADVANCED_RADIOACTIVE)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Ultimate Radioactive Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, Einsteinium, 64)
            .input(dust, Fermium, 64)
            .input(dust, Mendelevium, 64)
            .input(dust, Nobelium, 64)
            .input(dust, Lawrencium, 64)
            .input(dust, Rutherfordium, 64)
            .fluidInputs(CosmicNeutronium.getFluid(L * 4))
            .output(CATALYST_ULTIMATE_RADIOACTIVE)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Titanium Tungsten Indium Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, Titanium, 64)
            .input(dust, Tungsten, 64)
            .input(dust, Indium, 64)
            .fluidInputs(Tritanium.getFluid(L * 4))
            .output(CATALYST_TITANIUM_TUNGSTEN_INDIUM)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Adhesion Promoter Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, BatteryAlloy, 64)
            .input(dust, Tin, 64)
            .input(nanite, Carbon, 32)
            .fluidInputs(Glue.getFluid(64000))
            .output(CATALYST_ADHESION_PROMOTER)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Rare Earth Group Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, Lutetium, 64)
            .input(dust, Thulium, 64)
            .input(dust, Gadolinium, 64)
            .input(nanite, Silver, 4)
            .fluidInputs(Neutronium.getFluid(L * 4))
            .output(CATALYST_RARE_EARTH_GROUP)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Rare Metal Group Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, Molybdenum, 64)
            .input(dust, Gallium, 64)
            .input(dust, Germanium, 64)
            .input(dust, Rhenium, 64)
            .input(nanite, Copper, 4)
            .fluidInputs(Tritanium.getFluid(L * 4))
            .output(CATALYST_RARE_METAL_GROUP)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Naquadah Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, Naquadria, 64)
            .input(dust, Trinium, 64)
            .input(dust, Technetium, 64)
            .input(nanite, Neutronium, 4)
            .fluidInputs(CosmicNeutronium.getFluid(L * 4))
            .output(CATALYST_NAQUADAH)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Raw Intelligence Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(STEM_CELLS, 64)
            .input(nanite, Gold, 4)
            .fluidInputs(CosmicNeutronium.getFluid(L * 4))
            .output(CATALYST_RAW_INTELLIGENCE)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Biological Intelligence Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, BrevibacteriumFlavum, 64)
            .input(dust, CupriavidusNecator, 64)
            .input(dust, StreptococcusPyogenes, 64)
            .input(dust, EscherichiaColi, 64)
            .input(nanite, TranscendentMetal)
            .fluidInputs(Hypogen.getFluid(L * 4))
            .output(CATALYST_BIOLOGICAL_INTELLIGENCE)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // High Explosive Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, Hexanitrohexaaxaisowurtzitane, 64)
            .input(dust, Octaazacubane, 64)
            .input(nanite, Carbon, 16)
            .fluidInputs(CosmicNeutronium.getFluid(L * 4))
            .output(CATALYST_HIGH_EXPLOSIVE)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Temporal Harmony Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, Shirabon, 64)
            .input(nanite, Universium)
            .input(TIMEPIECE)
            .fluidInputs(SpaceTime.getFluid(L * 4))
            .output(CATALYST_TEMPORAL_HARMONY)
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Stellar Core Matter Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, Bedrockium, 64)
            .input(dust, Adamantium, 64)
            .input(dust, Vibranium, 64)
            .input(dust, Taranium, 64)
            .input(nanite, Neutronium, 4)
            .fluidInputs(CosmicNeutronium.getFluid(L * 4))
            .output(CATALYST_STELLAR_CORE)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Artificial Gem Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(gem, CubicZirconia, 64)
            .input(gem, CubicBoronNitride, 64)
            .input(gem, CubicSiliconNitride, 64)
            .input(gem, NdYAG, 64)
            .input(gem, LuTmYVO, 64)
            .input(gem, PrHoYLF, 64)
            .input(nanite, TranscendentMetal)
            .fluidInputs(CosmicNeutronium.getFluid(L * 4))
            .output(CATALYST_ARTIFICIAL_GEM)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Organic Dye Catalyst
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(21)
            .input(CATALYST_BASE)
            .input(dust, DiaminostilbenedisulfonicAcid, 64)
            .input(dust, Fluorescein, 64)
            .input(dust, CyanIndigo, 64)
            .input(dust, PrussianBlue, 64)
            .input(dust, DirectBrown77, 64)
            .input(dust, Erythrosine, 64)
            .input(nanite, Carbon, 16)
            .fluidInputs(Neutronium.getFluid(L * 4))
            .output(CATALYST_ORGANIC_DYE)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

    }

    // @formatter:on

}