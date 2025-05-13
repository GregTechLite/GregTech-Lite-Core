package magicbook.gtlitecore.api.unification.material

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.fluids.FluidBuilder
import gregtech.api.fluids.attribute.FluidAttributes
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.Actinium
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Arsenic
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Beryllium
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Caesium
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Cerium
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.CoalTar
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Deuterium
import gregtech.api.unification.material.Materials.Dysprosium
import gregtech.api.unification.material.Materials.EXT2_METAL
import gregtech.api.unification.material.Materials.EXT_METAL
import gregtech.api.unification.material.Materials.Erbium
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Ethenone
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Flint
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Francium
import gregtech.api.unification.material.Materials.Gadolinium
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Garnierite
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Holmium
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Iron3Chloride
import gregtech.api.unification.material.Materials.Lanthanum
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Mercury
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Neodymium
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Phosphate
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Polonium
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.Praseodymium
import gregtech.api.unification.material.Materials.Promethium
import gregtech.api.unification.material.Materials.Pyrite
import gregtech.api.unification.material.Materials.Radium
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.RareEarth
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Rhodium
import gregtech.api.unification.material.Materials.Roentgenium
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Ruthenium
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.STD_METAL
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Samarium
import gregtech.api.unification.material.Materials.Scandium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.SteelMagnetic
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Terbium
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.Uraninite
import gregtech.api.unification.material.Materials.Vanadium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Ytterbium
import gregtech.api.unification.material.Materials.Yttrium
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.material.Materials.ZincSulfide
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.material.info.MaterialFlags.CRYSTALLIZABLE
import gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
import gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING
import gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION
import gregtech.api.unification.material.info.MaterialFlags.EXPLOSIVE
import gregtech.api.unification.material.info.MaterialFlags.FLAMMABLE
import gregtech.api.unification.material.info.MaterialFlags.FORCE_GENERATE_BLOCK
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_BOLT_SCREW
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DENSE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DOUBLE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FINE_WIRE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FOIL
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FRAME
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_LENS
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_LONG_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_RING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROTOR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROUND
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SMALL_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING_SMALL
import gregtech.api.unification.material.info.MaterialFlags.HIGH_SIFTER_OUTPUT
import gregtech.api.unification.material.info.MaterialFlags.IS_MAGNETIC
import gregtech.api.unification.material.info.MaterialFlags.MORTAR_GRINDABLE
import gregtech.api.unification.material.info.MaterialFlags.NO_SMASHING
import gregtech.api.unification.material.info.MaterialFlags.NO_SMELTING
import gregtech.api.unification.material.info.MaterialFlags.NO_WORKING
import gregtech.api.unification.material.info.MaterialIconSet.BRIGHT
import gregtech.api.unification.material.info.MaterialIconSet.CERTUS
import gregtech.api.unification.material.info.MaterialIconSet.DIAMOND
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.material.info.MaterialIconSet.EMERALD
import gregtech.api.unification.material.info.MaterialIconSet.FINE
import gregtech.api.unification.material.info.MaterialIconSet.FLINT
import gregtech.api.unification.material.info.MaterialIconSet.GEM_HORIZONTAL
import gregtech.api.unification.material.info.MaterialIconSet.GEM_VERTICAL
import gregtech.api.unification.material.info.MaterialIconSet.LAPIS
import gregtech.api.unification.material.info.MaterialIconSet.LIGNITE
import gregtech.api.unification.material.info.MaterialIconSet.MAGNETIC
import gregtech.api.unification.material.info.MaterialIconSet.METALLIC
import gregtech.api.unification.material.info.MaterialIconSet.OPAL
import gregtech.api.unification.material.info.MaterialIconSet.QUARTZ
import gregtech.api.unification.material.info.MaterialIconSet.ROUGH
import gregtech.api.unification.material.info.MaterialIconSet.RUBY
import gregtech.api.unification.material.info.MaterialIconSet.SAND
import gregtech.api.unification.material.info.MaterialIconSet.SHINY
import gregtech.api.unification.material.properties.BlastProperty
import gregtech.api.unification.material.properties.MaterialToolProperty
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumOxalate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumSuperhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumTrihydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AdamantiumEnriched
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AdamantiumUnstable
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Aegirine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Albite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Alumina
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminaSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumSelenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumSulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumTrichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumAcetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumCarbonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumHexachloropalladate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumHexachloroplatinate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumPerrhenate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumPersulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumPertechnetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumSulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmorphousBoronNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Anorthite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Augite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Azurite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BETSPerrhenate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Baddeleyite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumStrontiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bedrockium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BerylliumDifluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BerylliumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthChalcogenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthStrontiumCalciumCuprate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthTelluride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BlackPhosphorus
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BluePhosphorus
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BlueVitriol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BoricAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BoronTrichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BoronTrifluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BoronTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bytownite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CadmiumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CadmiumSelenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CadmiumSulfide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumCarbonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumCeriumCobaltIndium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumHexachlorotinate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CalciumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CalciumDifluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CalciumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CalciumSulfide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CarbonTetrachloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Celestine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CeriumCarbonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CeriumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChlorinatedSolvents
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChloroauricAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChromiumGermaniumTelluride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChromiumGermaniumTellurideMagnetic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Clinochlore
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CobaltAluminate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CopperDichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CopperGalliumIndiumSelenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CopperNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cryolite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicBoronNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicHeterodiamond
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicSiliconNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicZirconia
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cuprite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DeepIron
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DeuteriumSuperheavyMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DielectricFormationMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dimethylcadmium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dolomite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DysprosiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ErTmYbLuOxidesSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ErbiumDopedZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ErbiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EuropiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Firestone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FleroviumYtterbiumPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorapatite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Forsterite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FranciumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FranciumCaesiumCadmiumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GSTGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GadoliniumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumTrichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GermaniumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GermaniumTetrachloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GrapheneOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HRAMagnesium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyAlkaliChloridesSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarkDegenerateMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarkEnrichedMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeliumNeon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Heterodiamond
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HexachloroplatinicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HexagonalBoronNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HexagonalSiliconNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HolmiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrobromicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenPeroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenSelenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydroselenicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydroxyquinolineAluminium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IndiumPhosphate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Iron2Chloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Iron3Sulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Jade
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Jasper
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kaolinite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LaPrNdCeOxidesSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Labradorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumGalliumManganate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadBismuthEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadChromate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadDichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadScandiumTantalate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadSulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lignite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumBerylliumFluorides
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumCarbonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumFluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumHydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumNiobate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumSodiumPotassiumFluorides
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumTetrafluoroborate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumThiinediselenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lizardite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LuTmDopedYttriumVanadateDeposition
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LuTmYChloridesSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LuTmYVO
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LutetiumManganeseGermanium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LutetiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnesiumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnetoResonatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ManganeseDifluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ManganeseMonoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ManganeseSulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MercuryCadmiumTelluride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableFlerovium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableHassium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableOganesson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MolybdenumFlue
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MolybdenumTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Muscovite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NdYAG
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NeodymiumDopedYttriumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NeodymiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Nephelite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NiobiumPentachloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NiobiumPentoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NitroniumTetrafluoroborate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NitrosoniumTetrafluoroborate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Oligoclase
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Orpiment
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.OsmiumTetrachloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ozone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumAcetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumBisdibenzylidieneacetone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumFullereneMatrix
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumLoadedRutileNanoparticles
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PerrhenicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pertechnetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phlogopite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phosphine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhosphorusTrichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Picotite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PiranhaSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PlatinumGroupConcentrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PlatinumGroupResidue
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Plutonium244
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PlutoniumPhosphide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PlutoniumTrihydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PoloniumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PoloniumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumBromate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumFluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumFormate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumManganate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumPermanganate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumSulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumTertbutoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumTetrachloroplatinate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PraseodymiumDopedZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PraseodymiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Prasiolite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PromethiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PurifiedPlatinumGroupConcentrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuantumchromodynamicallyConfinedMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RP1RocketFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RadiumDichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RadiumRadonMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RadiumSulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RedPhosphorus
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RhodiumTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RoastedSphalerite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RubidiumChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RubidiumHexachlorotinate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RubidiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RutheniumTrichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SamariumCobalt
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SamariumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ScEuGdSmOxidesSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ScandiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ScandiumTitaniumMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SeleniumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SelenousAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SilicaGel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SilicaGelBase
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SiliconTetrachloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SilverChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SilverOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SilverTetrafluoroborate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumAcetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumAluminate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumAzanide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumAzide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumCarbonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumChlorate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumCyanide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumEthoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumFluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumFormate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumHydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumHypochlorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumPerchlorate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumPeroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumPolonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumPotassiumEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumSeaborgate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumTellurite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumThiosulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumTrifluoroethanolate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumVanadate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Strontianite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StrontiumFerrite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StrontiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StrontiumSulfide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SulfurDichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumPentoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tanzanite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TechnetiumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TechnetiumHeptaoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TelluriumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tenorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TerbiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetrafluoroboricAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThalliumBariumCalciumCuprate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThalliumRoentgeniumChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThalliumSulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThoriumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThoriumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThuliumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TinDichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TinTetrachloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitaniumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TraceRheniumFlue
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TriniumTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TungstenTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.UranylChlorideSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.UranylNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.UranylNitrateSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.VanadiumPentoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.VibraniumUnstable
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.VioletPhosphorus
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WaelzOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WaelzSlag
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WhitePhosphorus
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Wollastonite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WoodsGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YTbDyHoOxidesSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YtterbiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YttriumBariumCopperOxidesMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YttriumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YttriumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZSM5
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZincRichSphalerite
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialFlags.Companion.DISABLE_CRYSTALLIZATION
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialFlags.Companion.GENERATE_BOULE
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialFlags.Companion.NO_ALLOY_BLAST_RECIPES
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialIconSet.Companion.BEDROCKIUM
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialIconSet.Companion.MAGNETO
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialIconSet.Companion.NANOPARTICLES
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialIconSet.Companion.ROASTED
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.gtliteId
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteFirstDegreeMaterials
{

    companion object
    {

        fun init()
        {
            // 2001 Dolomite
            Dolomite = Material.Builder(2001, gtliteId("dolomite"))
                .dust()
                .ore()
                .color(0xBBB8B2).iconSet(DULL)
                .components(Calcium, 1, Magnesium, 1, Carbon, 2, Oxygen, 6)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .build()
                .setFormula("CaMg(CO3)2", true)

            // 2002 Tanzanite
            Tanzanite = Material.Builder(2002, gtliteId("tanzanite"))
                .gem(2)
                .ore()
                .color(0x4000C8).iconSet(GEM_VERTICAL)
                .components(Calcium, 2, Aluminium, 3, Silicon, 3, Hydrogen, 1, Oxygen, 13)
                .flags(NO_SMASHING, NO_SMELTING, HIGH_SIFTER_OUTPUT, CRYSTALLIZABLE, GENERATE_LENS)
                .build()

            // 2003 Azurite
            Azurite = Material.Builder(2003, gtliteId("azurite"))
                .dust()
                .ore()
                .color(0x2E6DCE).iconSet(DULL)
                .components(Copper, 3, Carbon, 2, Oxygen, 8, Hydrogen, 2)
                .build()
                .setFormula("Cu3(CO3)2(OH)2", true)

            // 2004 Forsterite
            Forsterite = Material.Builder(2004, gtliteId("forsterite"))
                .gem()
                .ore()
                .color(0x1D640F).iconSet(LAPIS)
                .components(Magnesium, 2, Silicon, 1, Oxygen, 4)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, GENERATE_BOULE)
                .build()
                .setFormula("Mg2(SiO4)", true)

            // 2005 Augite
            Augite = Material.Builder(2005, gtliteId("augite"))
                .dust()
                .ore()
                .color(0x1B1717).iconSet(ROUGH)
                .components(Calcium, 2, Magnesium, 3, Iron, 3, Silicon, 8, Oxygen, 24)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .build()
                .setFormula("(Ca2MgFe)(MgFe)2(Si2O6)4", true)

            // 2006 Lizardite
            Lizardite = Material.Builder(2006, gtliteId("lizardite"))
                .dust()
                .ore()
                .color(0xA79E42).iconSet(DULL)
                .components(Magnesium, 3, Silicon, 2, Oxygen, 9, Hydrogen, 4)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .build()
                .setFormula("Mg3Si2O5(OH)4", true)

            // 2007 Muscovite
            Muscovite = Material.Builder(2007, gtliteId("muscovite"))
                .dust()
                .ore()
                .color(0x8B876A)
                .components(Potassium, 1, Aluminium, 3, Silicon, 3, Hydrogen, 10, Oxygen, 12)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .build()
                .setFormula("KAl2(AlSi3O10)(OH)2", true)

            // 2008 Clinochlore
            Clinochlore = Material.Builder(2008, gtliteId("clinochlore"))
                .gem()
                .ore()
                .color(0x303E38).iconSet(EMERALD)
                .components(Magnesium, 5, Aluminium, 2, Silicon, 3, Hydrogen, 8, Oxygen, 18)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, CRYSTALLIZABLE)
                .build()
                .setFormula("Mg5Al2Si3O10(OH)8", true)

            // 2009 Albite
            Albite = Material.Builder(2009, gtliteId("albite"))
                .gem()
                .ore()
                .color(0xC4A997).iconSet(CERTUS)
                .components(Sodium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, CRYSTALLIZABLE)
                .build()

            // 2010 Fluorite
            Fluorite = Material.Builder(2010, gtliteId("fluorite"))
                .gem()
                .ore()
                .color(0x276A4C).iconSet(GEM_HORIZONTAL)
                .components(Calcium, 1, Fluorine, 2)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, GENERATE_BOULE)
                .build()

            // 2011 Anorthite
            Anorthite = Material.Builder(2011, gtliteId("anorthite"))
                .gem()
                .ore()
                .color(0x595853).iconSet(CERTUS)
                .components(Calcium, 1, Aluminium, 2, Silicon, 2, Oxygen, 8)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, CRYSTALLIZABLE)
                .build()

            // 2012 Oligoclase
            Oligoclase = Material.Builder(2012, gtliteId("oligoclase"))
                .gem()
                .ore()
                .color(0xC4A997).iconSet(CERTUS)
                .components(Sodium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, CRYSTALLIZABLE)
                .build()

            // 2013 Labradorite
            Labradorite = Material.Builder(2013, gtliteId("labradorite"))
                .gem()
                .ore()
                .color(0x5C7181).iconSet(RUBY)
                .components(Albite, 2, Anorthite, 3)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, CRYSTALLIZABLE)
                .build()

            // 2014 Bytownite
            Bytownite = Material.Builder(2014, gtliteId("bytownite"))
                .gem()
                .ore()
                .color(0xC99C67).iconSet(LAPIS)
                .components(Albite, 1, Anorthite, 4)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_LENS, CRYSTALLIZABLE)
                .build()

            // 2015 Tenorite
            Tenorite = Material.Builder(2015, gtliteId("tenorite"))
                .dust()
                .ore()
                .color(0x443744).iconSet(DULL)
                .components(Copper, 1, Oxygen, 1)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .build()

            // 2016 Cuprite
            Cuprite = Material.Builder(2016, gtliteId("cuprite"))
                .dust()
                .ore()
                .color(0x99292E).iconSet(DULL)
                .components(Copper, 2, Oxygen, 1)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .build()

            // 2017 Wollastonite
            Wollastonite = Material.Builder(2017, gtliteId("wollastonite"))
                .dust()
                .ore()
                .color(0xDFDFDF).iconSet(ROUGH)
                .components(Calcium, 1, Silicon, 1, Oxygen, 3)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .build()

            // 2018 Fluorapatite
            Fluorapatite = Material.Builder(2018, gtliteId("fluorapatite"))
                .gem()
                .ore()
                .color(0x4FB3D8).iconSet(QUARTZ)
                .components(Calcium, 5, Phosphate, 3, Fluorine, 1)
                .flags(DECOMPOSITION_BY_ELECTROLYZING, HIGH_SIFTER_OUTPUT, GENERATE_LENS, GENERATE_BOULE)
                .build()
                .setFormula("Ca5(PO4)3F", true)

            // 2019 Kaolinite
            Kaolinite = Material.Builder(2019, gtliteId("kaolinite"))
                .dust()
                .ore()
                .color(0xDBCAC6).iconSet(DULL)
                .components(Aluminium, 2, Silicon, 2, Hydrogen, 4, Oxygen, 9)
                .flags(DECOMPOSITION_BY_ELECTROLYZING)
                .build()

            // 2020 Lignite
            Lignite = Material.Builder(2020, gtliteId("lignite"))
                .gem(0, 1600)
                .ore()
                .color(6571590)
                .iconSet(LIGNITE)
                .flags(FLAMMABLE, DISABLE_DECOMPOSITION, NO_SMELTING, NO_SMASHING, MORTAR_GRINDABLE)
                .components(Carbon, 3, Water, 1)
                .build()
                .setFormula("C3(H2O)", true);

            // 2021 Firestone
            Firestone = Material.Builder(2021, gtliteId("firestone"))
                .gem(1, 3200)
                .ore()
                .color(0xC81400)
                .iconSet(QUARTZ)
                .flags(NO_SMASHING, NO_SMELTING)
                .components(SiliconDioxide, 2, Flint, 1, Pyrite, 1)
                .build()
                .setFormula("(SiO2)3(FeS2)?", true)

            // 2022 Iron (III) Sulfate
            Iron3Sulfate = Material.Builder(2022, gtliteId("iron_sulfate"))
                .dust()
                .color(0xB09D99)
                .components(Iron, 2, Sulfur, 3, Oxygen, 12)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Fe2(SO4)3", true)

            // 2023 Celestine
            Celestine = Material.Builder(2023, gtliteId("celestine"))
                .gem(2)
                .ore()
                .color(0x4AE3E6).iconSet(OPAL)
                .components(Strontium, 1, Sulfur, 1, Oxygen, 4)
                .flags(CRYSTALLIZABLE, DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_LENS, GENERATE_BOULE)
                .build()

            // 2024 Strontianite
            Strontianite = Material.Builder(2024, gtliteId("strontianite"))
                .dust()
                .ore()
                .color(0x1DAFD3).iconSet(SAND)
                .components(Strontium, 1, Carbon, 1, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2025 Strontium Oxide
            StrontiumOxide = Material.Builder(2025, gtliteId("strontium_oxide"))
                .dust()
                .colorAverage().iconSet(DULL)
                .components(Strontium, 1, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2026 Strontium Sulfide
            StrontiumSulfide = Material.Builder(2026, gtliteId("strontium_sulfide"))
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Strontium, 1, Sulfur, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2027 Alumina
            Alumina = Material.Builder(2027, gtliteId("alumina"))
                .dust()
                .color(0x78C3EB).iconSet(METALLIC)
                .components(Aluminium, 2, Oxygen, 3)
                .build()

            // 2028 Phlogopite
            Phlogopite = Material.Builder(2028, gtliteId("phlogopite"))
                .dust()
                .ore()
                .color(0xDCDD0D)
                .components(Potassium, 1, Magnesium, 3, Aluminium, 1, Silicon, 3, Oxygen, 10, Fluorine, 2)
                .flags(NO_SMASHING, DECOMPOSITION_BY_ELECTROLYZING)
                .build()
                .setFormula("KMg3(AlSi3O10)F2", true)

            // 2029 Baddeleyite
            Baddeleyite = Material.Builder(2029, gtliteId("baddeleyite"))
                .gem()
                .ore()
                .color(0x689F9F).iconSet(GEM_HORIZONTAL)
                .components(Zirconium, 1, Oxygen, 2)
                .flags(HIGH_SIFTER_OUTPUT, DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_LENS, GENERATE_BOULE)
                .build()

            // 2030 Nephelite
            Nephelite = Material.Builder(2030, gtliteId("nephelite"))
                .gem()
                .ore()
                .color(0xE56842).iconSet(CERTUS)
                .components(Potassium, 1, Sodium, 3, Aluminium, 4, Silicon, 4, Oxygen, 16)
                .flags(GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
                .build()
                .setFormula("KNa3(AlSiO4)4", true)

            // 2031 Aegirine
            Aegirine = Material.Builder(2031, gtliteId("aegirine"))
                .gem()
                .ore()
                .color(0x4ACA3B).iconSet(EMERALD)
                .components(Sodium, 1, Iron, 1, Silicon, 2, Oxygen, 6)
                .flags(GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
                .build()

            // 2032 Niobium Pentoxide
            NiobiumPentoxide = Material.Builder(2032, gtliteId("niobium_pentoxide"))
                .dust()
                .color(0xBAB0C3).iconSet(ROUGH)
                .components(Niobium, 2, Oxygen, 5)
                .build()

            // 2033 Tantalum Pentoxide
            TantalumPentoxide = Material.Builder(2033, gtliteId("tantalum_pentoxide"))
                .dust()
                .color(0x72728A).iconSet(ROUGH)
                .components(Tantalum, 2, Oxygen, 5)
                .build()

            // 2034 Calcium Difluoride
            CalciumDifluoride = Material.Builder(2034, gtliteId("calcium_difluoride"))
                .dust()
                .color(0xFFFC9E).iconSet(ROUGH)
                .components(Calcium, 1, Fluorine, 2)
                .build()

            // 2035 Manganese Difluoride
            ManganeseDifluoride = Material.Builder(2035, gtliteId("manganese_difluoride"))
                .dust()
                .color(0xEF4B3D).iconSet(ROUGH)
                .components(Manganese, 1, Fluorine, 2)
                .build()

            // 2036 Heavy Alkali Chlorides Solution
            HeavyAlkaliChloridesSolution = Material.Builder(2036, gtliteId("heavy_alkali_chlorides_solution"))
                .liquid()
                .color(0x8F5353)
                .components(Rubidium, 1, Caesium, 2, Chlorine, 6, Water, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(RbCl)(CsCl)2Cl3(H2O)2", true)

            // 2037 Tin Dichloride
            TinDichloride = Material.Builder(2037, gtliteId("tin_dichloride"))
                .dust()
                .color(0xDBDBDB).iconSet(METALLIC)
                .components(Tin, 1, Chlorine, 2)
                .build()

            // 2038 Tin Tetrachloride
            TinTetrachloride = Material.Builder(2038, gtliteId("tin_tetrachloride"))
                .dust()
                .color(0x33BBF5).iconSet(METALLIC)
                .components(Tin, 1, Chlorine, 4)
                .build()

            // 2039 Caesium Hexachlorotinate
            CaesiumHexachlorotinate = Material.Builder(2039, gtliteId("caesium_hexachlorotinate"))
                .dust()
                .color(0xBDAD88).iconSet(SHINY)
                .components(Caesium, 2, Tin, 1, Chlorine, 6)
                .build()

            // 2040 Rubidium Hexachlorotinate
            RubidiumHexachlorotinate = Material.Builder(2040, gtliteId("rubidium_hexachlorotinate"))
                .dust()
                .color(0xBD888A).iconSet(METALLIC)
                .components(Rubidium, 2, Tin, 1, Chlorine, 6)
                .build()

            // 2041 Cryolite
            Cryolite = Material.Builder(2041, gtliteId("cryolite"))
                .gem()
                .ore()
                .color(0xBFEFFF).iconSet(QUARTZ)
                .components(Sodium, 3, Aluminium, 1, Fluorine, 6)
                .flags(GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
                .build()

            // 2042 Aluminium Hydroxide
            AluminiumHydroxide = Material.Builder(2042, gtliteId("aluminium_hydroxide"))
                .dust()
                .color(0xBEBEC8)
                .components(Aluminium, 1, Oxygen, 3, Hydrogen, 3)
                .build()
                .setFormula("Al(OH)3", true)

            // 2043 Sodium Aluminate
            SodiumAluminate = Material.Builder(2043, gtliteId("sodium_aluminate"))
                .dust()
                .colorAverage()
                .components(Sodium, 1, Aluminium, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2044 Sodium Carbonate
            SodiumCarbonate = Material.Builder(2044, gtliteId("sodium_carbonate"))
                .liquid()
                .colorAverage()
                .components(SodaAsh, 1, Water, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2045 Aluminium Sulfate
            AluminiumSulfate = Material.Builder(2045, gtliteId("aluminium_sulfate"))
                .dust()
                .colorAverage()
                .components(Aluminium, 2, Sulfur, 3, Oxygen, 12)
                .build()
                .setFormula("Al2(SO4)3", true)

            // 2046 ZSM-5
            ZSM5 = Material.Builder(2046, gtliteId("zsm_5"))
                .dust()
                .colorAverage().iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE)
                .components(Sodium, 1, Aluminium, 2, Sulfur, 3, Silicon, 2, Oxygen, 18, Hydrogen, 4)
                .build()
                .setFormula("Na(Al2(SO4)3)(SiO2)2(H2O)2", true)

            // 2047 Molybdenum Trioxide
            MolybdenumTrioxide = Material.Builder(2047, gtliteId("molybdenum_trioxide"))
                .dust()
                .color(0xCBCFDA)
                .iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Molybdenum, 1, Oxygen, 3)
                .build()

            // 2048 Molybdenum Flue
            MolybdenumFlue = Material.Builder(2048, gtliteId("molybdenum_flue"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x39194A)
                .components(Rhenium, 1, Oxygen, 2, RareEarth, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2049 Lead Dichloride
            LeadDichloride = Material.Builder(2049, gtliteId("lead_dichloride"))
                .dust()
                .color(0xF3F3F3).iconSet(ROUGH)
                .components(Lead, 1, Chlorine, 2)
                .build()

            // 2050 Trace Rhenium Flue
            TraceRheniumFlue = Material.Builder(2050, gtliteId("trace_rhenium_flue"))
                .gas(FluidBuilder()
                    .translation("gregtech.fluid.generic"))
                .color(0x96D6D5)
                .components(Rhenium, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2051 Perrhenic Acid
            PerrhenicAcid = Material.Builder(2051, gtliteId("perrhenic_acid"))
                .dust()
                .color(0xE6DC70).iconSet(SHINY)
                .components(Hydrogen, 1, Rhenium, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2052 Ammonium Perrhenate
            AmmoniumPerrhenate = Material.Builder(2052, gtliteId("ammonium_perrhenate"))
                .dust()
                .color(0xA69970).iconSet(METALLIC)
                .components(Nitrogen, 1, Hydrogen, 4, Rhenium, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2053 Jade
            Jade = Material.Builder(2053, gtliteId("jade"))
                .gem(2)
                .ore()
                .color(0x006400).iconSet(RUBY)
                .components(Sodium, 1, Aluminium, 1, Silicon, 2, Oxygen, 6)
                .flags(CRYSTALLIZABLE, GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
                .build()

            // 2054 Jasper
            Jasper = Material.Builder(2054, gtliteId("jasper"))
                .gem(2)
                .ore()
                .color(0xC85050).iconSet(EMERALD)
                .components(Calcium, 1, Magnesium, 5, Oxygen, 24, Hydrogen, 2, Silicon, 8)
                .flags(HIGH_SIFTER_OUTPUT, CRYSTALLIZABLE, GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
                .build()
                .setFormula("CaMg5(OH)2(Si4O11)2", true)

            // 2055 Picotite
            Picotite = Material.Builder(2055, gtliteId("picotite"))
                .gem(3)
                .ore(2, 3)
                .color(0x931C24).iconSet(DIAMOND)
                .components(Iron, 1, Chrome, 2, Oxygen, 4)
                .flags(GENERATE_PLATE, GENERATE_LENS, GENERATE_BOULE)
                .build()

            // 2056 Manganese Monoxide
            ManganeseMonoxide = Material.Builder(2056, gtliteId("manganese_monoxide"))
                .dust()
                .color(0x472400)
                .components(Manganese, 1, Oxygen, 1)
                .build()

            // 2057 Lead Chromate
            LeadChromate = Material.Builder(2057, gtliteId("lead_chromate"))
                .dust()
                .color(0xFFFB00).iconSet(SHINY)
                .components(Lead, 1, Chrome, 1, Oxygen, 4)
                .build()

            // 2058 Lead Nitrate
            LeadNitrate = Material.Builder(2058, gtliteId("lead_nitrate"))
                .dust()
                .color(0xFFFFFF).iconSet(SHINY)
                .components(Lead, 1, Nitrogen, 2, Oxygen, 6)
                .build()
                .setFormula("Pb(NO3)2", true)

            // 2059 Cobalt Aluminate
            CobaltAluminate = Material.Builder(2059,  gtliteId("cobalt_aluminate"))
                .dust()
                .color(0x1605FF).iconSet(SHINY)
                .components(Cobalt, 1, Aluminium, 2, Oxygen, 4)
                .build()

            // 2060 Orpiment
            Orpiment = Material.Builder(2060, gtliteId("orpiment"))
                .gem()
                .ore()
                .color(0xEBD352).iconSet(EMERALD)
                .components(Arsenic, 2, Sulfur, 3)
                .flags(GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
                .build()

            // 2061 Sodium Chlorate
            SodiumChlorate = Material.Builder(2061, gtliteId("sodium_chlorate"))
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Sodium, 1,  Chlorine, 1, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2062 Sodium Perchlorate
            SodiumPerchlorate = Material.Builder(2062, gtliteId("sodium_perchlorate"))
                .dust()
                .color(Salt.materialRGB).iconSet(ROUGH)
                .components(Sodium, 1, Chlorine, 1, Oxygen, 4)
                .build()

            // 2063 Sodium Hypochlorite
            SodiumHypochlorite = Material.Builder(2063, gtliteId("sodium_hypochlorite"))
                .dust()
                .color(0x778D56).iconSet(SHINY)
                .components(Sodium, 1, Chlorine, 1, Oxygen, 1)
                .build()

            // 2064 Tungsten Trioxide
            TungstenTrioxide = Material.Builder(2064, gtliteId("tungsten_trioxide"))
                .dust()
                .color(0xC7D300).iconSet(DULL)
                .components(Tungsten, 1, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2065 Strontium Ferrite
            StrontiumFerrite = Material.Builder(2065, gtliteId("strontium_ferrite"))
                .ingot()
                .fluid()
                .colorAverage().iconSet(MAGNETIC)
                .components(Strontium, 1, Iron, 12, Oxygen, 19)
                .flags(GENERATE_ROD, GENERATE_RING)
                .blast { b ->
                    b.temp(3000, BlastProperty.GasTier.MID)
                        .blastStats(VA[EV], 40 * SECOND)
                        .vacuumStats(VA[MV], 10 * SECOND)
                }
                .build()

            // 2066 Titanium Nitrate
            TitaniumNitrate = Material.Builder(2066, gtliteId("titanium_nitrate"))
                .dust()
                .colorAverage()
                .components(Titanium, 1, Nitrogen, 4, Oxygen, 12)
                .build()
                .setFormula("Ti(NO3)4", true)

            // 2067 Lithium Titanate
            LithiumTitanate = Material.Builder(2067, gtliteId("lithium_titanate"))
                .ingot()
                .fluid()
                .color(0xFE71A9).iconSet(SHINY)
                .components(Lithium, 2, Titanium, 1, Oxygen, 3)
                .flags(EXT2_METAL, NO_ALLOY_BLAST_RECIPES, GENERATE_DOUBLE_PLATE, GENERATE_FOIL,
                    GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_RING)
                .blast { b ->
                    b.temp(3100, BlastProperty.GasTier.MID) // Nichrome
                        .blastStats(VA[EV], 16 * SECOND)
                        .vacuumStats(VA[MV], 8 * SECOND)
                }
                .toolStats(MaterialToolProperty(8.5F, 7.0F, 2304, 4))
                .rotorStats(8.5f, 4.0f, 3200)
                .fluidPipeProperties(2830, 200, true, true, false, false)
                .build()

            // 2068 Palladium Nitrate
            PalladiumNitrate = Material.Builder(2068, gtliteId("palladium_nitrate"))
                .dust()
                .color(0x82312A).iconSet(METALLIC)
                .components(Palladium, 1, Nitrogen, 2, Oxygen, 6)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Pd(NO3)2", true)

            // 2069 Palladium Acetate
            PalladiumAcetate = Material.Builder(2069, gtliteId("palladium_acetate"))
                .dust()
                .color(0x693C2D).iconSet(SHINY)
                .components(Palladium, 1, AceticAcid, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Pd(CH3COOH)2", true)

            // 2070 Palladium Loaded Rutile Nanoparticles
            PalladiumLoadedRutileNanoparticles = Material.Builder(2070, gtliteId("palladium_loaded_rutile_nanoparticles"))
                .dust()
                .colorAverage().iconSet(NANOPARTICLES)
                .components(Palladium, 1, Rutile, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2071 Lithium Oxide
            LithiumOxide = Material.Builder(2071, gtliteId("lithium_oxide"))
                .dust()
                .color(0x9DB6B9).iconSet(DULL)
                .components(Lithium, 2, Oxygen, 1)
                .build()

            // 2072 Lithium Carbonate
            LithiumCarbonate = Material.Builder(2072, gtliteId("lithium_carbonate"))
                .dust()
                .color(0xD1F3F6).iconSet(ROUGH)
                .components(Lithium, 2, Carbon, 1, Oxygen, 3)
                .build()

            // 2073 Blue Vitriol
            BlueVitriol = Material.Builder(2073, gtliteId("blue_vitriol"))
                .liquid()
                .color(0x4242DE)
                .components(Copper, 1, Sulfur, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2074 Sodium Tellurite
            SodiumTellurite = Material.Builder(2074, gtliteId("sodium_tellurite"))
                .dust()
                .color(0xC6C9BE).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 2, Tellurium, 1, Oxygen, 3)
                .build()

            // 2075 Selenium Dioxide
            SeleniumDioxide = Material.Builder(2075, gtliteId("selenium_dioxide"))
                .dust()
                .color(0xE0DDD8).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Selenium, 1, Oxygen, 2)
                .build()

            // 2076 Tellurium Dioxide
            TelluriumDioxide = Material.Builder(2076, gtliteId("tellurium_dioxide"))
                .dust()
                .color(0xE3DDB8).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Tellurium, 1, Oxygen, 2)
                .build()

            // 2077 Selenous Acid
            SelenousAcid = Material.Builder(2077, gtliteId("selenous_acid"))
                .dust()
                .color(0xE0E083).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Selenium, 1, Oxygen, 3)
                .build()

            // 2078 Aluminium Selenide
            AluminiumSelenide = Material.Builder(2078, gtliteId("aluminium_selenide"))
                .dust()
                .color(0x969651)
                .components(Aluminium, 2, Selenium, 3)
                .build()

            // 2079 Hydrogen Selenide
            HydrogenSelenide = Material.Builder(2079, gtliteId("hydrogen_selenide"))
                .gas()
                .color(0x42f554)
                .components(Hydrogen, 2, Selenium, 1)
                .build()

            // 2080 Cadmium Bromide
            CadmiumBromide = Material.Builder(2080, gtliteId("cadmium_bromide"))
                .dust()
                .color(0xFF1774).iconSet(SHINY)
                .components(Cadmium, 1, Bromine, 2)
                .build()

            // 2081 Magnesium Bromide
            MagnesiumBromide = Material.Builder(2081, gtliteId("magnesium_bromide"))
                .dust()
                .color(0x5F4C32).iconSet(METALLIC)
                .components(Magnesium, 1, Bromine, 2)
                .build()

            // 2082 HRA Magnesium
            HRAMagnesium = Material.Builder(2082, gtliteId("hra_magnesium"))
                .dust()
                .color(Magnesium.materialRGB).iconSet(SHINY)
                .components(Magnesium, 1)
                .build()

            // 2083 Hydrobromic Acid
            HydrobromicAcid = Material.Builder(2083, gtliteId("hydrobromic_acid"))
                .liquid(FluidBuilder().attributes(FluidAttributes.ACID))
                .color(0x8D1212)
                .components(Hydrogen, 1, Bromine, 1)
                .build()

            // 2084 Dimethylcadmium
            Dimethylcadmium = Material.Builder(2084, gtliteId("dimethylcadmium"))
                .liquid()
                .color(0x5C037F)
                .components(Carbon, 2, Hydrogen, 6, Cadmium, 1)
                .build()
                .setFormula("(CH3)2Cd", true)

            // 2085 Cadmium Selenide
            CadmiumSelenide = Material.Builder(2085, gtliteId("cadmium_selenide"))
                .dust()
                .color(0x983034).iconSet(METALLIC)
                .components(Cadmium, 1, Selenium, 1)
                .build()

            // 2086 Prasiolite
            Prasiolite = Material.Builder(2086, gtliteId("prasiolite"))
                .gem()
                .ore()
                .color(0x9EB749).iconSet(QUARTZ)
                .components(SiliconDioxide, 5, Iron, 1)
                .flags(GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
                .build()

            // 2087 ZBLAN Glass
            ZBLANGlass = Material.Builder(2087, gtliteId("zblan_glass"))
                .ingot()
                .fluid()
                .color(0xACB4BC).iconSet(SHINY)
                .components(Zirconium, 5, Barium, 2, Lanthanum, 1, Aluminium, 1, Sodium, 2, Fluorine, 6)
                .flags(NO_SMASHING, NO_WORKING, DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(ZrF4)5(BaF2)2(LaF3)(AlF3)(NaF)2", true)

            // 2088 Er-doped ZBLAN Glass
            ErbiumDopedZBLANGlass = Material.Builder(2088, gtliteId("erbium_doped_zblan_glass"))
                .ingot()
                .color(0x505444).iconSet(BRIGHT)
                .components(ZBLANGlass, 1, Erbium, 1)
                .flags(NO_SMASHING, NO_WORKING, DISABLE_DECOMPOSITION, GENERATE_PLATE)
                .build()
                .setFormula("(ZrF4)5(BaF2)2(LaF3)(AlF3)(NaF)2Er", true)

            // 2089 Pr-doped ZBLAN Glass
            PraseodymiumDopedZBLANGlass = Material.Builder(2089, gtliteId("praseodymium_doped_zblan_glass"))
                .ingot()
                .color(0xC5C88D).iconSet(BRIGHT)
                .flags(NO_SMASHING, NO_WORKING, DISABLE_DECOMPOSITION, GENERATE_PLATE)
                .components(ZBLANGlass, 1, Praseodymium, 1)
                .build()
                .setFormula("(ZrF4)5(BaF2)2(LaF3)(AlF3)(NaF)2Pr", true)

            // 2090 Ozone
            Ozone = Material.Builder(2090, gtliteId("ozone"))
                .gas()
                .color(0xBEF4FA)
                .components(Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2091 Cubic Zirconia
            CubicZirconia = Material.Builder(2091, gtliteId("cubic_zirconia"))
                .gem()
                .color(0xFFDFE2).iconSet(DIAMOND)
                .components(Zirconium, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_LENS, GENERATE_BOULE)
                .build()
                .setFormula("c-ZrO2", true)

            // 2092 Bismuth Telluride
            BismuthTelluride = Material.Builder(2092, gtliteId("bismuth_telluride"))
                .dust()
                .color(0x0E8933).iconSet(DULL)
                .components(Bismuth, 2, Tellurium, 3)
                .build()

            // 2093 Magneto Resonatic
            MagnetoResonatic = Material.Builder(2093, gtliteId("magneto_resonatic"))
                .gem()
                .color(0xFF97FF).iconSet(MAGNETO)
                .components(BismuthTelluride, 4, Prasiolite, 3, CubicZirconia, 1, SteelMagnetic, 1)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_LENS, GENERATE_BOULE)
                .build()
                .setFormula("(Bi2Te3)4((SiO2)5Fe)3(ZrO2)Fe", true)

            // 2094 Lanthanum Oxide
            LanthanumOxide = Material.Builder(2094, gtliteId("lanthanum_oxide"))
                .dust()
                .color(0x5F7777).iconSet(SHINY)
                .components(Lanthanum, 2, Oxygen, 3)
                .build()

            // 2095 Cerium Oxide
            CeriumOxide = Material.Builder(2095, gtliteId("cerium_oxide"))
                .dust()
                .color(0x10937F).iconSet(METALLIC)
                .components(Cerium, 1, Oxygen, 2)
                .build()

            // 2096 Praseodymium Oxide
            PraseodymiumOxide = Material.Builder(2096, gtliteId("praseodymium_oxide"))
                .dust()
                .color(0xD0D0D0).iconSet(METALLIC)
                .components(Praseodymium, 2, Oxygen, 3)
                .build()

            // 2097 Neodymium Oxide
            NeodymiumOxide = Material.Builder(2097, gtliteId("neodymium_oxide"))
                .dust()
                .color(0x868686)
                .components(Neodymium, 2, Oxygen, 3)
                .build()

            // 2098 Samarium Oxide
            SamariumOxide = Material.Builder(2098, gtliteId("samarium_oxide"))
                .dust()
                .color(0xFFFFDD)
                .components(Samarium, 2, Oxygen, 3)
                .build()

            // 2099 Europium Oxide
            EuropiumOxide = Material.Builder(2099, gtliteId("europium_oxide"))
                .dust()
                .color(0x20AAAA).iconSet(SHINY)
                .components(Europium, 2, Oxygen, 3)
                .build()

            // 2100 Gadolinium Oxide
            GadoliniumOxide = Material.Builder(2100, gtliteId("gadolinium_oxide"))
                .dust()
                .color(0xEEEEFF).iconSet(METALLIC)
                .components(Gadolinium, 2, Oxygen, 3)
                .build()

            // 2101 Terbium Oxide
            TerbiumOxide = Material.Builder(2101, gtliteId("terbium_oxide"))
                .dust()
                .color(0xA264A2).iconSet(METALLIC)
                .components(Terbium, 2, Oxygen, 3)
                .build()

            // 2102 Dysprosium Oxide
            DysprosiumOxide = Material.Builder(2102, gtliteId("dysprosium_oxide"))
                .dust()
                .color(0xD273D2).iconSet(METALLIC)
                .components(Dysprosium, 2, Oxygen, 3)
                .build()

            // 2103 Holmium Oxide
            HolmiumOxide = Material.Builder(2103, gtliteId("holmium_oxide"))
                .dust()
                .color(0xAF7F2A).iconSet(SHINY)
                .components(Holmium, 2, Oxygen, 3)
                .build()

            // 2104 Erbium Oxide
            ErbiumOxide = Material.Builder(2104, gtliteId("erbium_oxide"))
                .dust()
                .color(0xE07A32).iconSet(METALLIC)
                .components(Erbium, 2, Oxygen, 3)
                .build()

            // 2105 Thulium Oxide
            ThuliumOxide = Material.Builder(2105, gtliteId("thulium_oxide"))
                .dust()
                .color(0x3B9E8B)
                .components(Thulium, 2, Oxygen, 3)
                .build()

            // 2106 Ytterbium Oxide
            YtterbiumOxide = Material.Builder(2106, gtliteId("ytterbium_oxide"))
                .dust()
                .color(0xA9A9A9)
                .components(Ytterbium, 2, Oxygen, 3)
                .build()

            // 2107 Lutetium Oxide
            LutetiumOxide = Material.Builder(2107, gtliteId("lutetium_oxide"))
                .dust()
                .color(0x11BBFF).iconSet(METALLIC)
                .components(Lutetium, 2, Oxygen, 3)
                .build()

            // 2108 Scandium Oxide
            ScandiumOxide = Material.Builder(2108, gtliteId("scandium_oxide"))
                .dust()
                .color(0x43964F).iconSet(METALLIC)
                .components(Scandium, 2, Oxygen, 3)
                .build()

            // 2109 Yttrium Oxide
            YttriumOxide = Material.Builder(2109, gtliteId("yttrium_oxide"))
                .dust()
                .color(0x78544E).iconSet(SHINY)
                .components(Yttrium, 2, Oxygen, 3)
                .build()

            // 2110 Promethium Oxide
            PromethiumOxide = Material.Builder(2110, gtliteId("promethium_oxide"))
                .dust()
                .color(0x1B8828).iconSet(METALLIC)
                .components(Promethium, 2, Oxygen, 3)
                .build()

            // 2111 La-Pr-Nd-Ce Oxides Solution
            LaPrNdCeOxidesSolution = Material.Builder(2111, gtliteId("la_pr_nd_ce_oxides_solution"))
                .liquid()
                .color(0x9CE3DB)
                .components(LanthanumOxide, 1, PraseodymiumOxide, 1, NeodymiumOxide, 1, CeriumOxide, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build()

            // 2112 Sc-Eu-Gd-Sm Oxides Solution
            ScEuGdSmOxidesSolution = Material.Builder(2112, gtliteId("sc_eu_gd_sm_oxides_solution"))
                .liquid()
                .color(0xFFFF99)
                .components(ScandiumOxide, 1, EuropiumOxide, 1, GadoliniumOxide, 1, SamariumOxide, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build()

            // 2113 Y-Tb-Dy-Ho Oxides Solution
            YTbDyHoOxidesSolution = Material.Builder(2113, gtliteId("y_tb_dy_ho_oxides_solution"))
                .liquid()
                .color(0x99FF99)
                .components(YttriumOxide, 1, TerbiumOxide, 1, DysprosiumOxide, 1, HolmiumOxide, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build()

            // 2114 Er-Tm-Yb-Lu Oxides Solution
            ErTmYbLuOxidesSolution = Material.Builder(2114, gtliteId("er_tm_yb_lu_oxides_solution"))
                .liquid()
                .color(0xFFB3FF)
                .components(ErbiumOxide, 1, ThuliumOxide, 1, YtterbiumOxide, 1, LutetiumOxide, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build()

            // 2115 Platinum Group Residue
            PlatinumGroupResidue = Material.Builder(2115, gtliteId("platinum_group_residue"))
                .dust()
                .color(0x64632E).iconSet(ROUGH)
                .components(Iridium, 1, Osmium, 1, Rhodium, 1, Ruthenium, 1, RareEarth, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("RuRhIr2Os(HNO3)3", true)

            // 2116 Platinum Group Concentrate
            PlatinumGroupConcentrate = Material.Builder(2116, gtliteId("platinum_group_concentrate"))
                .liquid()
                .color(0xFFFFA6)
                .components(Gold, 1, Platinum, 1, Palladium, 1, HydrochloricAcid, 6)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("AuPtPd(HCl)6", true)

            // 2117 Purified Platinum Group Concentrate
            PurifiedPlatinumGroupConcentrate = Material.Builder(2117, gtliteId("purified_platinum_group_concentrate"))
                .liquid()
                .color(0xFFFFC8)
                .components(Hydrogen, 2, Platinum, 1, Palladium, 1, Chlorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("H2PtPdCl6", true)

            // 2118 Ammonium Hexachloroplatinate
            AmmoniumHexachloroplatinate = Material.Builder(2118, gtliteId("ammonium_hexachloroplatinate"))
                .liquid()
                .color(0xFEF0C2)
                .flags(DISABLE_DECOMPOSITION)
                .components(Nitrogen, 2, Hydrogen, 8, Platinum, 1, Chlorine, 6)
                .build()
                .setFormula("(NH4)2PtCl6", true)

            // 2119 Ammonium Hexachloropalladate
            AmmoniumHexachloropalladate = Material.Builder(2119, gtliteId("ammonium_hexachloropalladate"))
                .liquid()
                .color(0x808080)
                .components(Nitrogen, 2, Hydrogen, 8, Palladium, 1, Chlorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(NH4)2PdCl6", true)

            // 2120 Sodium Nitrate
            SodiumNitrate = Material.Builder(2120, gtliteId("sodium_nitrate"))
                .dust()
                .color(0x846684).iconSet(ROUGH)
                .components(Sodium, 1, Nitrogen, 1, Oxygen, 3)
                .build()

            // 2121 Hexachloroplatinic Acid
            HexachloroplatinicAcid = Material.Builder(2121, gtliteId("hexachloroplatinic_acid"))
                .liquid(FluidBuilder().attribute(FluidAttributes.ACID))
                .color(0xFEF4D1)
                .components(Hydrogen, 2, Platinum, 1, Chlorine, 6)
                .build()

            // 2122 Carbon Tetrachloride
            CarbonTetrachloride = Material.Builder(2122, gtliteId("carbon_tetrachloride"))
                .liquid()
                .color(0x75201A)
                .components(Carbon, 1, Chlorine, 4)
                .build()

            // 2123 Sodium Peroxide
            SodiumPeroxide = Material.Builder(2123, gtliteId("sodium_peroxide"))
                .dust()
                .color(0xECFF80).iconSet(ROUGH)
                .components(Sodium, 2, Oxygen, 2)
                .build()

            // 2124 Ruthenium Trichloride
            RutheniumTrichloride = Material.Builder(2124, gtliteId("ruthenium_trichloride"))
                .dust()
                .color(0x605C6C).iconSet(METALLIC)
                .components(Ruthenium, 1, Chlorine, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2125 Rhodium Trioxide
            RhodiumTrioxide = Material.Builder(2125, gtliteId("rhodium_trioxide"))
                .dust()
                .color(0xD93D16).iconSet(METALLIC)
                .components(Rhodium, 2, Oxygen, 3)
                .build()

            // 2126 Sulfur Dichloride
            SulfurDichloride = Material.Builder(2126, gtliteId("sulfur_dichloride"))
                .liquid()
                .color(0x761410)
                .components(Sulfur, 1, Chlorine, 2)
                .build()

            // 2127 Osmium Tetrachloride
            OsmiumTetrachloride = Material.Builder(2127, gtliteId("osmium_tetrachloride"))
                .dust()
                .color(0x29080A).iconSet(METALLIC)
                .components(Osmium, 1, Chlorine, 4)
                .build()

            // 2128 Beryllium Oxide
            BerylliumOxide = Material.Builder(2128, gtliteId("beryllium_oxide"))
                .ingot()
                .color(0x54C757)
                .components(Beryllium, 1, Oxygen, 1)
                .flags(EXT_METAL, GENERATE_DOUBLE_PLATE, GENERATE_RING)
                .build()

            // 2129 Hydrogen Peroxide
            HydrogenPeroxide = Material.Builder(2129, gtliteId("hydrogen_peroxide"))
                .liquid()
                .color(0xD2FFFF)
                .components(Hydrogen, 2, Oxygen, 2)
                .build()

            // 2130 Graphene Oxide
            GrapheneOxide = Material.Builder(2130, gtliteId("graphene_oxide"))
                .dust()
                .color(0x777777).iconSet(ROUGH)
                .components(Graphene, 1, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2131 Yttrium Nitrate
            YttriumNitrate = Material.Builder(2131, gtliteId("yttrium_nitrate"))
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Yttrium, 1, Nitrogen, 3, Oxygen, 9)
                .build()
                .setFormula("Y(NO3)3", true)

            // 2132 Barium Nitrate
            BariumNitrate = Material.Builder(2132, gtliteId("barium_nitrate"))
                .dust()
                .colorAverage().iconSet(METALLIC)
                .components(Barium, 1, Nitrogen, 2, Oxygen, 6)
                .build()
                .setFormula("Ba(NO3)2", true)

            // 2133 Copper Nitrate
            CopperNitrate = Material.Builder(2133, gtliteId("copper_nitrate"))
                .dust()
                .colorAverage().iconSet(DULL)
                .components(Copper, 1, Nitrogen, 2, Oxygen, 6)
                .build()
                .setFormula("Cu(NO3)2", true)

            // 2134 Yttrium Barium Copper Oxides Mixture
            YttriumBariumCopperOxidesMixture = Material.Builder(2134, gtliteId("yttrium_barium_copper_oxides_mixture"))
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Yttrium, 1, Barium, 2, Copper, 3, Oxygen, 6)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2135 GST Glass
            GSTGlass = Material.Builder(2135, gtliteId("gst_glass"))
                .ingot()
                .fluid()
                .color(0xCFFFFF).iconSet(SHINY)
                .components(Germanium, 2, Antimony, 2, Tellurium, 5)
                .flags(NO_SMASHING, NO_WORKING, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_PLATE)
                .blast { b ->
                    b.temp(873, BlastProperty.GasTier.MID)
                }
                .build()

            // 2136 Germanium Dioxide
            GermaniumDioxide = Material.Builder(2136, gtliteId("germanium_dioxide"))
                .dust()
                .color(0x666666)
                .components(Germanium, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2137 Roasted Sphalerite
            RoastedSphalerite = Material.Builder(2137, gtliteId("roasted_sphalerite"))
                .dust()
                .color(0xAC8B5C).iconSet(ROASTED)
                .components(GermaniumDioxide, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(GeO2)?", true)

            // 2138 Zn-rich Sphalerite
            ZincRichSphalerite = Material.Builder(2138, gtliteId("zinc_rich_sphalerite"))
                .dust()
                .color(0xC3AC8F).iconSet(METALLIC)
                .components(Zinc, 2, RoastedSphalerite, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Zn2(GaGeO2)?", true)

            // 2139 Waelz Oxide
            WaelzOxide = Material.Builder(2139, gtliteId("waelz_oxide"))
                .dust()
                .color(0xB8B8B8).iconSet(FINE)
                .components(Zinc, 1, GermaniumDioxide, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(GeO2)Zn", true)

            // 2140 Waelz Slag
            WaelzSlag = Material.Builder(2140, gtliteId("waelz_slag"))
                .dust()
                .color(0xAC8B5C).iconSet(ROUGH)
                .components(Gallium, 1, Zinc, 1, Sulfur, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(ZnSO4)Ga", true)

            // 2141 Piranha Solution
            PiranhaSolution = Material.Builder(2141, gtliteId("piranha_solution"))
                .liquid()
                .color(0x4820AB)
                .components(SulfuricAcid, 1, HydrogenPeroxide, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build()

            // 2142 Hydroxyquinoline Aluminium
            HydroxyquinolineAluminium = Material.Builder(2142, gtliteId("hydroxyquinoline_aluminium"))
                .ingot()
                .color(0x3F5A9F).iconSet(SHINY)
                .components(Aluminium, 1, Carbon, 9, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
                .flags(STD_METAL, DISABLE_DECOMPOSITION, GENERATE_FOIL)
                .build()
                .setFormula("(C9H7NO)Al", true)

            // 2143 Hydroselenic Acid
            HydroselenicAcid = Material.Builder(2143, gtliteId("hydroselenic_acid"))
                .liquid(FluidBuilder().attribute(FluidAttributes.ACID))
                .color(0xDBC3B5)
                .components(Hydrogen, 2, Selenium, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2144 Copper Gallium Indium Selenide
            CopperGalliumIndiumSelenide = Material.Builder(2144, gtliteId("copper_gallium_indium_selenide"))
                .ingot()
                .colorAverage().iconSet(SHINY)
                .components(Copper, 1, Gallium, 1, Indium, 1, Selenium, 2)
                .flags(STD_METAL, DISABLE_DECOMPOSITION, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .blast { b ->
                    b.temp(6000, BlastProperty.GasTier.MID) // Naquadah (HSS-G)
                        .blastStats(VA[EV], 30 * SECOND)
                        .vacuumStats(VA[MV], 10 * SECOND)
                }
                .build()

            // 2145 Barium Hydroxide
            BariumHydroxide = Material.Builder(2145, gtliteId("barium_hydroxide"))
                .dust()
                .color(0xFFFFED).iconSet(DULL)
                .components(Barium, 1, Oxygen, 2, Hydrogen, 2)
                .build()
                .setFormula("Ba(OH)2", true)

            // 2146 Barium Titanate
            BariumTitanate = Material.Builder(2146, gtliteId("barium_titanate"))
                .ingot()
                .fluid()
                .color(0x99FF99).iconSet(SHINY)
                .components(Barium, 1, Titanium, 1, Oxygen, 3)
                .flags(EXT_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_FOIL, GENERATE_BOLT_SCREW)
                .blast { b ->
                    b.temp(3600, BlastProperty.GasTier.LOW) // Nichrome
                        .blastStats(VA[IV], 18 * SECOND)
                        .vacuumStats(VA[HV], 8 * SECOND)
                }
                .build()

            // 2147 Samarium Cobalt
            SamariumCobalt = Material.Builder(2147, gtliteId("samarium_cobalt"))
                .ingot()
                .fluid()
                .color(0xB3D683).iconSet(MAGNETIC)
                .components(Samarium, 1,  Cobalt, 5)
                .flags(GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_RING)
                .blast { b ->
                    b.temp(5000, BlastProperty.GasTier.HIGH) // HSS-G (RTM Alloy)
                        .blastStats(VA[IV], 45 * SECOND)
                        .vacuumStats(VA[HV], 20 * SECOND)
                }
                .build()

            // 2148 Potassium Hydroxide
            PotassiumHydroxide = Material.Builder(2148, gtliteId("potassium_hydroxide"))
                .dust()
                .liquid(FluidBuilder().temperature(633))
                .color(0xFA9849)
                .components(Potassium, 1, Oxygen, 1, Hydrogen, 1)
                .build()

            // 2149 Copper Dichloride
            CopperDichloride = Material.Builder(2149, gtliteId("copper_dichloride"))
                .dust()
                .color(0x3FB3B8).iconSet(ROUGH)
                .components(Copper, 1, Chlorine, 2)
                .build()

            // 2150 Sodium Cyanide
            SodiumCyanide = Material.Builder(2150, gtliteId("sodium_cyanide"))
                .dust()
                .color(0x1B3818).iconSet(DULL)
                .components(Sodium, 1, Carbon, 1, Nitrogen, 1)
                .build()

            // 2151 Potassium Bromate
            PotassiumBromate = Material.Builder(2151, gtliteId("potassium_bromate"))
                .dust()
                .color(0x782828).iconSet(ROUGH)
                .components(Potassium, 1, Bromine, 1, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2152 White Phosphorus
            WhitePhosphorus = Material.Builder(2152, gtliteId("white_phosphorus"))
                .gem()
                .color(0xECEADD).iconSet(FLINT)
                .components(Phosphorus, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2153 Red Phosphorus
            RedPhosphorus = Material.Builder(2153, gtliteId("red_phosphorus"))
                .gem()
                .color(0x77040E).iconSet(FLINT)
                .components(Phosphorus, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2154 Violet Phosphorus
            VioletPhosphorus = Material.Builder(2154, gtliteId("violet_phosphorus"))
                .gem()
                .color(0x8000FF).iconSet(FLINT)
                .components(Phosphorus, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2155 Black Phosphorus
            BlackPhosphorus = Material.Builder(2155, gtliteId("black_phosphorus"))
                .gem()
                .color(0x36454F).iconSet(FLINT)
                .components(Phosphorus, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2156 Blue Phosphorus
            BluePhosphorus = Material.Builder(2156, gtliteId("blue_phosphorus"))
                .gem()
                .color(0x9BE3E4).iconSet(FLINT)
                .components(Phosphorus, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2157 Phosphorus Trichloride
            PhosphorusTrichloride = Material.Builder(2157, gtliteId("phosphorus_trichloride"))
                .liquid()
                .color(0xD8D85B)
                .components(Phosphorus, 1, Chlorine, 3)
                .build()

            // 2158 Sodium Fluoride
            SodiumFluoride = Material.Builder(2158, gtliteId("sodium_fluoride"))
                .dust()
                .color(0x460012).iconSet(DULL)
                .components(Sodium, 1, Fluorine, 1)
                .build()

            // 2159 Sodium Trifluoroethanolate
            SodiumTrifluoroethanolate = Material.Builder(2159, gtliteId("sodium_trifluoroethanolate"))
                .dust()
                .color(0x50083E).iconSet(ROUGH)
                .components(Sodium, 1, Carbon, 2, Hydrogen, 4, Oxygen, 1, Fluorine, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2160 Technetium Heptaoxide
            TechnetiumHeptaoxide = Material.Builder(2160, gtliteId("technetium_heptaoxide"))
                .dust()
                .color(0xFCE9A4).iconSet(SHINY)
                .components(Technetium, 2, Oxygen, 7)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2161 Trinium Trioxide
            TriniumTrioxide = Material.Builder(2161, gtliteId("trinium_trioxide"))
                .dust()
                .color(0xC037C5).iconSet(METALLIC)
                .components(Trinium, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2162 Pertechnetate
            Pertechnetate = Material.Builder(2162, gtliteId("pertechnetate"))
                .liquid(FluidBuilder().attributes(FluidAttributes.ACID))
                .color(0xCC3300)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2163 Ammonium Nitrate
            AmmoniumNitrate = Material.Builder(2163, gtliteId("ammonium_nitrate"))
                .dust()
                .color(0xA59ED7).iconSet(METALLIC)
                .components(Ammonia, 1, NitricAcid, 1)
                .build()
                .setFormula("NH4NO3", true)

            // 2164 Ammonium Pertechnetate
            AmmoniumPertechnetate = Material.Builder(2164, gtliteId("ammonium_pertechnetate"))
                .dust()
                .color(0x996666).iconSet(ROUGH)
                .components(Nitrogen, 1, Hydrogen, 4, Technetium, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2165 Technetium Dioxide
            TechnetiumDioxide = Material.Builder(2165, gtliteId("technetium_dioxide"))
                .dust()
                .colorAverage().iconSet(METALLIC)
                .components(Technetium, 1, Oxygen, 2)
                .build()

            // 2166 Indium Phosphate
            IndiumPhosphate = Material.Builder(2166, gtliteId("indium_phosphate"))
                .dust()
                .color(0x2B2E70).iconSet(SHINY)
                .components(Indium, 1, Phosphorus, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2167 Gallium Dioxide
            GalliumDioxide = Material.Builder(2167, gtliteId("gallium_dioxide"))
                .dust()
                .color(0xAB9ABF).iconSet(DULL)
                .components(Gallium, 1, Oxygen, 2)
                .build()

            // 2168 Calcium Sulfide
            CalciumSulfide = Material.Builder(2168, gtliteId("calcium_sulfide"))
                .dust()
                .color(0xF9F9F9).iconSet(METALLIC)
                .components(Calcium, 1, Sulfur, 1)
                .build()

            // 2169 RP-1 Rocket Fuel
            RP1RocketFuel = Material.Builder(2169, gtliteId("rp_1_rocket_fuel"))
                .liquid()
                .color(0xFB2A08)
                .components(CoalTar, 1, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2170 Bismuth Trioxide
            BismuthTrioxide = Material.Builder(2170, gtliteId("bismuth_trioxide"))
                .dust()
                .color(0xF5EF42).iconSet(FINE)
                .components(Bismuth, 2, Oxygen, 3)
                .build()

            // 2171 Bismuth Strontium Calcium Cuprate (BSCCO)
            BismuthStrontiumCalciumCuprate = Material.Builder(2171, gtliteId("bismuth_strontium_calcium_cuprate"))
                .ingot()
                .fluid()
                .color(0xD880D8)
                .components(BismuthTrioxide, 1, Strontianite, 2, Calcite, 1, Tenorite, 2)
                .blast { b ->
                    b.temp(7000, BlastProperty.GasTier.HIGHER) // Naquadah
                        .blastStats(VA[UV], 43 * SECOND)
                        .vacuumStats(VA[IV], 21 * SECOND) }
                .flags(STD_METAL, DECOMPOSITION_BY_CENTRIFUGING, NO_ALLOY_BLAST_RECIPES, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .cableProperties(V[UV], 4, 3)
                .build()
                .setFormula("Bi2Sr2CaCu2O8", true)

            // 2172 Bedrockium
            Bedrockium = Material.Builder(2172, gtliteId("bedrockium"))
                .ingot()
                .fluid()
                .iconSet(BEDROCKIUM)
                .flags(EXT2_METAL, GENERATE_FOIL, GENERATE_DOUBLE_PLATE, GENERATE_FRAME, GENERATE_GEAR,
                    GENERATE_SMALL_GEAR, GENERATE_FINE_WIRE, GENERATE_SPRING, GENERATE_SPRING_SMALL)
                .blast { b ->
                    b.temp(9900, BlastProperty.GasTier.HIGHER) // Tritanium
                        .blastStats(VA[ZPM], 50 * SECOND)
                        .vacuumStats(VA[LuV], 25 * SECOND)
                }
                .cableProperties(V[UHV], 2, 16)
                .build()

            // 2173 Adamantite
            Adamantite = Material.Builder(2173, gtliteId("adamantite"))
                .dust()
                .color(0xC83C3C).iconSet(ROUGH)
                .components(Adamantium, 3, Oxygen, 4)
                .build()

            // 2174 Unstable Adamantium
            AdamantiumUnstable = Material.Builder(2174, gtliteId("adamantium_unstable"))
                .liquid()
                .color(0xFF763C)
                .components(Adamantium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ad*", true)

            // 2175 Enriched Adamantium
            AdamantiumEnriched = Material.Builder(2175, gtliteId("adamantium_enriched"))
                .dust()
                .color(0x64B4FF).iconSet(ROUGH)
                .components(Vibranium, 1, RareEarth, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2176 Unstable Vibranium
            VibraniumUnstable = Material.Builder(2176, gtliteId("vibranium_unstable"))
                .liquid()
                .color(0xFF7832)
                .components(Vibranium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2177 Deep Iron
            DeepIron = Material.Builder(2177, gtliteId("deep_iron"))
                .dust()
                .color(0x968C8C).iconSet(METALLIC)
                .components(Iron, 2, Trinium, 1, Indium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2178 Eutatic Sodium Potassium
            SodiumPotassiumEutatic = Material.Builder(2178, gtliteId("sodium_potassium_eutatic"))
                .ingot()
                .fluid()
                .colorAverage().iconSet(DULL)
                .components(Sodium, 7, Potassium, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2179 Eutatic Lead Bismuth
            LeadBismuthEutatic = Material.Builder(2179, gtliteId("lead_bismuth_eutatic"))
                .ingot()
                .fluid()
                .colorAverage().iconSet(SHINY)
                .components(Lead, 3, Bismuth, 7)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2180 Lithium Fluoride
            LithiumFluoride = Material.Builder(2180, gtliteId("lithium_fluoride"))
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Lithium, 1, Fluorine, 1)
                .build()

            // 2181 Potassium Fluoride
            PotassiumFluoride = Material.Builder(2181, gtliteId("potassium_fluoride"))
                .dust()
                .colorAverage().iconSet(DULL)
                .components(Potassium, 1, Fluorine, 1)
                .build()

            // 2182 Lithium Sodium Potassium Fluorides
            LithiumSodiumPotassiumFluorides = Material.Builder(2182, gtliteId("lithium_sodium_potassium_fluorides"))
                .ingot()
                .fluid()
                .colorAverage().iconSet(METALLIC)
                .components(LithiumFluoride, 1, SodiumFluoride, 1, PotassiumFluoride, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("F3LiNaK", true)

            // 2183 Beryllium Difluoride
            BerylliumDifluoride = Material.Builder(2183, gtliteId("beryllium_difluoride"))
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Beryllium, 1, Fluorine, 2)
                .build()

            // 2184 Lithium Beryllium Fluorides
            LithiumBerylliumFluorides = Material.Builder(2184, gtliteId("lithium_beryllium_fluorides"))
                .ingot()
                .fluid()
                .colorAverage().iconSet(ROUGH)
                .components(LithiumFluoride, 1, BerylliumDifluoride, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("F3LiBe", true)

            // 2185 Flerovium-Ytterbium Plasma
            FleroviumYtterbiumPlasma = Material.Builder(2185, gtliteId("flerovium_ytterbium_plasma"))
                .plasma(FluidBuilder()
                    .temperature(10550)
                    .translation("gregtech.fluid.generic"))
                .colorAverage()
                .components(MetastableFlerovium, 1, Ytterbium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("FlY?", true)

            // 2186 Rubidium Titanate
            RubidiumTitanate = Material.Builder(2186, gtliteId("rubidium_titanate"))
                .ingot()
                .fluid()
                .color(0xB52E15).iconSet(SHINY)
                .components(Rubidium, 2, Titanium, 1, Oxygen, 3)
                .flags(EXT_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_BOLT_SCREW,
                    GENERATE_FOIL)
                .blast { b ->
                    b.temp(4100, BlastProperty.GasTier.LOW) // RTM Alloy
                        .blastStats(VA[LuV], 20 * SECOND)
                        .vacuumStats(VA[EV], 16 * SECOND)
                }
                .build()

            // 2187 Sodium Titanate
            SodiumTitanate = Material.Builder(2187, gtliteId("sodium_titanate"))
                .dust()
                .color(0x2274BA).iconSet(SHINY)
                .components(Sodium, 2, Titanium, 1, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2188 Rubidium Chloride
            RubidiumChloride = Material.Builder(2188, gtliteId("rubidium_chloride"))
                .dust()
                .colorAverage().iconSet(METALLIC)
                .components(Rubidium, 1, Chlorine, 1)
                .build()

            // 2189 Sodium Oxide
            SodiumOxide = Material.Builder(2189, gtliteId("sodium_oxide"))
                .dust()
                .color(0x2C96FC).iconSet(BRIGHT)
                .components(Sodium, 2, Oxygen, 1)
                .build()

            // 2190 Sodium Acetate
            SodiumAcetate = Material.Builder(2190, gtliteId("sodium_acetate"))
                .liquid()
                .color(0xC5D624)
                .components(SodiumHydroxide, 1, Ethenone, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C2H3NaO2", true)

            // 2191 Caesium Bromide
            CaesiumBromide = Material.Builder(2191, gtliteId("caesium_bromide"))
                .dust()
                .colorAverage().iconSet(METALLIC)
                .components(Caesium, 1, Bromine, 1)
                .build()

            // 2192 Francium Bromide
            FranciumBromide = Material.Builder(2192, gtliteId("francium_bromide"))
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Francium, 1, Bromine, 1)
                .build()

            // 2193 Francium Caesium Cadmium Bromide
            FranciumCaesiumCadmiumBromide = Material.Builder(2193, gtliteId("francium_caesium_cadmium_bromide"))
                .ingot()
                .fluid()
                .colorAverage().iconSet(SHINY)
                .components(Francium, 1, Caesium, 1, Cadmium, 2, Bromine, 6)
                .flags(STD_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_FOIL)
                .blast { b ->
                    b.temp(7100, BlastProperty.GasTier.HIGHER)
                        .blastStats(VA[UHV], 20 * SECOND)
                        .vacuumStats(VA[ZPM], 15 * SECOND)
                }
                .build()

            // 2194 Sodium Seaborgate
            SodiumSeaborgate = Material.Builder(2194, gtliteId("sodium_seaborgate"))
                .ingot()
                .fluid()
                .color(0x55bbd4).iconSet(BRIGHT)
                .components(Sodium, 2, Seaborgium, 1, Oxygen, 4)
                .flags(STD_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .blast { b ->
                    b.temp(9800, BlastProperty.GasTier.HIGHER) // Tritanium
                        .blastStats(VA[UV], 24 * SECOND)
                        .vacuumStats(VA[ZPM], 12 * SECOND)
                }
                .build()

            // 2195 Lead Scandium Tantalate
            LeadScandiumTantalate = Material.Builder(2195, gtliteId("lead_scandium_tantalate"))
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Lead, 1, Scandium, 1, Tantalum, 1, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2196 Thallium Sulfate
            ThalliumSulfate = Material.Builder(2196, gtliteId("thallium_sulfate"))
                .dust()
                .color(0x9C222C).iconSet(METALLIC)
                .components(Thallium, 2, Sulfur, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2197 Thallium Barium Calcium Cuprate (TBCCO)
            ThalliumBariumCalciumCuprate = Material.Builder(2197, gtliteId("thallium_barium_calcium_cuprate"))
                .ingot()
                .fluid()
                .color(0x669900).iconSet(SHINY)
                .components(Thallium, 1, Barium, 2, Calcium, 2, Copper, 3, Oxygen, 10)
                .flags(STD_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_FOIL,
                    GENERATE_FINE_WIRE)
                .blast { b ->
                    b.temp(7000, BlastProperty.GasTier.HIGHER) // Naquadah
                        .blastStats(VA[UV], 43 * SECOND)
                        .vacuumStats(VA[IV], 21 * SECOND)
                }
                .cableProperties(V[UHV], 2, 1)
                .build()

            // 2198 Potassium Manganate
            PotassiumManganate = Material.Builder(2198, gtliteId("potassium_manganate"))
                .dust()
                .color(0x873883).iconSet(METALLIC)
                .components(Potassium, 2, Manganese, 1, Oxygen, 4)
                .build()

            // 2199 Potassium Permanganate
            PotassiumPermanganate = Material.Builder(2199, gtliteId("potassium_permanganate"))
                .dust()
                .color(0x871D82).iconSet(DULL)
                .components(Potassium, 1, Manganese, 1, Oxygen, 4)
                .build()

            // 2200 Lanthanum Gallium Manganate
            LanthanumGalliumManganate = Material.Builder(2200, gtliteId("lanthanum_gallium_manganate"))
                .ingot()
                .fluid()
                .color(0x8AA07B).iconSet(METALLIC)
                .components(Lanthanum, 1, Gallium, 1, Manganese, 1, Oxygen, 4)
                .flags(STD_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_FOIL)
                .blast { b ->
                    b.temp(8000, BlastProperty.GasTier.HIGH) // Trinium
                        .blastStats(VA[UV], 45 * SECOND)
                        .vacuumStats(VA[IV], 22 * SECOND + 10 * TICK)
                }
                .build()

            // 2201 Barium Strontium Titanate
            BariumStrontiumTitanate = Material.Builder(2201, gtliteId("barium_strontium_titanate"))
                .ingot()
                .fluid()
                .color(0xFF0066).iconSet(SHINY)
                .components(Barium, 1, Strontium, 1, Titanium, 1, Oxygen, 4)
                .flags(EXT_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_FOIL,
                    GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW)
                .blast { b ->
                    b.temp(7200, BlastProperty.GasTier.HIGH) // Naquadah
                        .blastStats(VA[ZPM], 36 * SECOND)
                        .vacuumStats(VA[IV], 18 * SECOND)
                }
                .build()

            // 2202 Lutetium Manganese Germanium
            LutetiumManganeseGermanium = Material.Builder(2202, gtliteId("lutetium_manganese_germanium"))
                .ingot()
                .fluid()
                .colorAverage().iconSet(MAGNETIC)
                .components(Lutetium, 1, Manganese, 3, Germanium, 6)
                .flags(EXT_METAL, GENERATE_LONG_ROD, GENERATE_RING)
                .blast { b ->
                    b.temp(7000, BlastProperty.GasTier.HIGHER) // Naquadah
                        .blastStats(VA[LuV], 1 * MINUTE)
                        .vacuumStats(VA[EV], 30 * SECOND)
                }
                .build() // This is not a reality magnetic material... it is a fantastic permanent magnet? ^^ i think it is well in Gregtech.

            // 2203 Boric Acid
            BoricAcid = Material.Builder(2203, gtliteId("boric_acid"))
                .dust()
                .color(0xFAFAFA).iconSet(SHINY)
                .components(Hydrogen, 3, Boron, 1, Oxygen, 3)
                .build()

            // 2204 Boron Trioxide
            BoronTrioxide = Material.Builder(2204, gtliteId("boron_trioxide"))
                .dust()
                .color(0xE9FAC0).iconSet(METALLIC)
                .components(Boron, 2, Oxygen, 3)
                .build()

            // 2205 Boron Trifluoride
            BoronTrifluoride = Material.Builder(2205, gtliteId("boron_trifluoride"))
                .gas()
                .color(0xFAF191)
                .components(Boron, 1, Fluorine, 3)
                .build()

            // 2206 Lithium Hydride
            LithiumHydride = Material.Builder(2206, gtliteId("lithium_hydride"))
                .ingot()
                .color(0x9BAFDB).iconSet(METALLIC)
                .components(Lithium, 1, Hydrogen, 1)
                .build()

            // 2207 Lithium Tetrafluoroborate
            LithiumTetrafluoroborate = Material.Builder(2207, gtliteId("lithium_tetrafluoroborate"))
                .dust()
                .color(0x90FAF6).iconSet(SHINY)
                .components(Lithium, 1, Boron, 1, Fluorine, 4)
                .build()

            // 2208 Boron Trichloride
            BoronTrichloride = Material.Builder(2208, gtliteId("boron_trichloride"))
                .gas()
                .color(0x033F1B)
                .components(Boron, 1, Chlorine, 3)
                .build()

            // 2209 Hexagonal Boron Nitride
            HexagonalBoronNitride = Material.Builder(2209, gtliteId("hexagonal_boron_nitride"))
                .gem()
                .color(0x6A6A72).iconSet(GEM_VERTICAL)
                .components(Boron, 1, Nitrogen, 1)
                .flags(DISABLE_DECOMPOSITION, DISABLE_CRYSTALLIZATION, GENERATE_PLATE, GENERATE_LENS)
                .build()
                .setFormula("h-BN", true)

            // 2210 Cubic Boron Nitride
            CubicBoronNitride = Material.Builder(2210, gtliteId("cubic_boron_nitride"))
                .gem()
                .color(0x545572).iconSet(DIAMOND)
                .components(Boron, 1, Nitrogen, 1)
                .flags(EXT_METAL, DISABLE_CRYSTALLIZATION, DISABLE_DECOMPOSITION, FLAMMABLE,
                    EXPLOSIVE, GENERATE_GEAR, GENERATE_LONG_ROD, GENERATE_LENS)
                .toolStats(MaterialToolProperty(14.0F, 9.0F, 12400, 15))
                .build()
                .setFormula("c-BN", true)

            // 2211 Amorphous Boron Nitride
            AmorphousBoronNitride = Material.Builder(2211, gtliteId("amorphous_boron_nitride"))
                .ingot()
                .color(0x9193C5).iconSet(SHINY)
                .components(Boron, 1, Nitrogen, 1)
                .flags(STD_METAL, DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_FOIL)
                .build()
                .setFormula("a-BN", true)

            // 2212 Heterodiamond
            Heterodiamond = Material.Builder(2212, gtliteId("heterodiamond"))
                .gem()
                .color(0x512A72).iconSet(GEM_HORIZONTAL)
                .components(Boron, 1, Carbon, 1, Nitrogen, 1)
                .flags(DISABLE_DECOMPOSITION, DISABLE_CRYSTALLIZATION, GENERATE_PLATE, GENERATE_LENS)
                .build()

            // 2213 Cubic Heterodiamond
            CubicHeterodiamond = Material.Builder(2213, gtliteId("cubic_heterodiamond"))
                .gem()
                .color(0x753DA6).iconSet(DIAMOND)
                .components(Boron, 1, Carbon, 2, Nitrogen, 1)
                .flags(DISABLE_DECOMPOSITION, DISABLE_CRYSTALLIZATION, GENERATE_PLATE, GENERATE_LENS)
                .build()
                .setFormula("c-BC2N", true)

            // 2214 Calcium Carbide
            CalciumCarbide = Material.Builder(2214, gtliteId("calcium_carbide"))
                .dust()
                .color(0x807B70).iconSet(DULL)
                .components(Calcium, 1, Carbon, 2)
                .build()

            // 2215 Calcium Hydroxide
            CalciumHydroxide = Material.Builder(2215, gtliteId("calcium_hydroxide"))
                .dust()
                .color(0x5F8764).iconSet(ROUGH)
                .components(Calcium, 1, Hydrogen, 2, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ca(OH)2", true)

            // 2216 Chromium Germanium Telluride
            ChromiumGermaniumTelluride = Material.Builder(2216, gtliteId("chromium_germanium_telluride"))
                .ingot()
                .fluid()
                .color(0x8F103E).iconSet(METALLIC)
                .components(Chrome, 1, Germanium, 1, Tellurium, 3)
                .flags(DECOMPOSITION_BY_CENTRIFUGING, GENERATE_ROD, GENERATE_LONG_ROD)
                .blast { b ->
                    b.temp(8900, BlastProperty.GasTier.HIGHER) // Trinium
                        .blastStats(VA[LuV], 1 * MINUTE + 30 * SECOND)
                        .vacuumStats(VA[IV], 20 * SECOND)
                }
                .build()

            // 2217 Magnetic Chromium Germanium Telluride
            ChromiumGermaniumTellurideMagnetic = Material.Builder(2217, gtliteId("chromium_germanium_telluride_magnetic"))
                .ingot()
                .color(0x8F103E).iconSet(MAGNETIC)
                .components(ChromiumGermaniumTelluride, 1)
                .flags(GENERATE_ROD, GENERATE_LONG_ROD, IS_MAGNETIC, GENERATE_SPRING_SMALL)
                .ingotSmeltInto(ChromiumGermaniumTelluride)
                .arcSmeltInto(ChromiumGermaniumTelluride)
                .macerateInto(ChromiumGermaniumTelluride)
                .build()

            // 2218 Gallium Trichloride
            GalliumTrichloride = Material.Builder(2218, gtliteId("gallium_trichloride"))
                .dust()
                .color(0x6EB4FF).iconSet(ROUGH)
                .components(Gallium, 1, Chlorine, 3)
                .build()

            // 2219 Gallium Trioxide
            GalliumTrioxide = Material.Builder(2219, gtliteId("gallium_trioxide"))
                .dust()
                .color(0xE4CDFF).iconSet(METALLIC)
                .components(Gallium, 2, Oxygen, 3)
                .build()

            // 2220 Gallium Nitride
            GalliumNitride = Material.Builder(2220, gtliteId("gallium_nitride"))
                .ingot()
                .color(0xFFF458).iconSet(SHINY)
                .flags(STD_METAL)
                .components(Gallium, 1, Nitrogen, 1)
                .build()

            // 2221 Aluminium Trichloride
            AluminiumTrichloride = Material.Builder(2221, gtliteId("aluminium_trichloride"))
                .dust()
                .color(0x78C3EB).iconSet(SHINY)
                .components(Aluminium, 1, Chlorine, 3)
                .build()

            // 2222 Niobium Pentachloride
            NiobiumPentachloride = Material.Builder(2222, gtliteId("niobium_pentachloride"))
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Niobium, 1, Chlorine, 5)
                .build()

            // 2223 Lithium Niobate
            LithiumNiobate = Material.Builder(2223, gtliteId("lithium_niobate"))
                .ingot()
                .fluid()
                .color(0xD27700).iconSet(SHINY)
                .components(Lithium, 1, Niobium, 1, Oxygen, 4)
                .flags(STD_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_LENS, GENERATE_FOIL)
                .blast { b ->
                    b.temp(3226, BlastProperty.GasTier.HIGH) // Nichrome
                        .blastStats(VA[ZPM], 40 * SECOND)
                        .vacuumStats(VA[IV], 10 * SECOND)
                }
                .build()

            // 2224 Manganese Sulfate
            ManganeseSulfate = Material.Builder(2224, gtliteId("manganese_sulfate"))
                .dust()
                .color(0xF0F895).iconSet(ROUGH)
                .components(Manganese, 1, Sulfur, 1, Oxygen, 4)
                .build()

            // 2225 Potassium Sulfate
            PotassiumSulfate = Material.Builder(2225, gtliteId("potassium_sulfate"))
                .dust()
                .color(0xF4FBB0).iconSet(DULL)
                .components(Potassium, 2, Sulfur, 1, Oxygen, 4)
                .build()

            // 2226 Neodymium-doped Yttrium Oxide
            NeodymiumDopedYttriumOxide = Material.Builder(2226, gtliteId("neodymium_doped_yttrium_oxide"))
                .dust()
                .color(0x5AD55F).iconSet(DULL)
                .components(YttriumOxide, 1, NeodymiumOxide, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2227 Aluminium Nitrate
            AluminiumNitrate = Material.Builder(2227, gtliteId("aluminium_nitrate"))
                .dust()
                .color(0x3AB3AA).iconSet(SHINY)
                .components(Aluminium, 1, Nitrogen, 3, Oxygen, 9)
                .build()
                .setFormula("Al(NO3)3", true)

            // 2228 Chlorinated Solvents
            ChlorinatedSolvents = Material.Builder(2228, gtliteId("chlorinated_solvents"))
                .liquid()
                .color(0x40804c)
                .components(Carbon, 2, Hydrogen, 8, Chlorine, 5)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(CH4)2Cl5", true)

            // 2229 Alumina Solution
            AluminaSolution = Material.Builder(2229, gtliteId("alumina_solution"))
                .liquid()
                .color(0x6C4DC1)
                .components(Alumina, 1, Carbon, 25, Hydrogen, 56, Chlorine, 2, Nitrogen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(Al2O3)(CH2Cl2)(C12H27N)2", true)

            // 2230 Nd:YAG
            NdYAG = Material.Builder(2230, gtliteId("nd_yag"))
                .gem()
                .color(0xD99DE4).iconSet(GEM_VERTICAL)
                .components(YttriumOxide, 2, NeodymiumOxide, 1, Alumina, 5)
                .flags(CRYSTALLIZABLE, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_PLATE, GENERATE_LENS)
                .build()
                .setFormula("Nd:YAG", true)

            // 2231 Scandium-Titanium Mixture
            ScandiumTitaniumMixture = Material.Builder(2231, gtliteId("scandium_titanium_mixture"))
                .liquid()
                .colorAverage()
                .components(Scandium, 1, Titanium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2232 Radium-Radon Mixture
            RadiumRadonMixture = Material.Builder(2232, gtliteId("radium_radon_mixture"))
                .liquid()
                .colorAverage()
                .components(Radium, 1, Radon, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2233 Polonium Nitrate
            PoloniumNitrate = Material.Builder(2233, gtliteId("polonium_nitrate"))
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Polonium, 1, Nitrogen, 4, Oxygen, 12)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Po(NO3)4", true)

            // 2234 Sodium Polonate
            SodiumPolonate = Material.Builder(2234, gtliteId("sodium_polonate"))
                .dust()
                .colorAverage().iconSet(DULL)
                .components(Sodium, 2, Polonium, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Na2PoO4", true)

            // 2235 Polonium Dioxide
            PoloniumDioxide = Material.Builder(2235, gtliteId("polonium_dioxide"))
                .dust()
                .colorAverage().iconSet(METALLIC)
                .components(Polonium, 1, Oxygen, 2)
                .build()

            // 2236 Uranyl Chloride Solution
            UranylChlorideSolution = Material.Builder(2236, gtliteId("uranyl_chloride_solution"))
                .liquid()
                .color(0xDFE018)
                .components(Uraninite, 1, Chlorine, 2, Water, 1, RareEarth, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2237 Uranyl Nitrate Solution
            UranylNitrateSolution = Material.Builder(2237, gtliteId("uranyl_nitrate_solution"))
                .liquid()
                .colorAverage()
                .components(Uraninite, 1, Nitrogen, 2, Oxygen, 7, Hydrogen, 2, RareEarth, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(UO2)(NO3)2(H2O)?", true)

            // 2238 Radium Sulfate
            RadiumSulfate = Material.Builder(2238, gtliteId("radium_sulfate"))
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Radium, 1, Sulfur, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2239 Lead Sulfate
            LeadSulfate = Material.Builder(2239, gtliteId("lead_sulfate"))
                .dust()
                .colorAverage().iconSet(DULL)
                .components(Lead, 1, Sulfur, 1, Oxygen, 4)
                .build()

            // 2240 Thorium Nitrate
            ThoriumNitrate = Material.Builder(2240, gtliteId("thorium_nitrate"))
                .dust()
                .colorAverage().iconSet(METALLIC)
                .components(Thorium, 1, Nitrogen, 4, Oxygen, 12)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Th(NO3)4", true)

            // 2241 Radium Dichloride
            RadiumDichloride = Material.Builder(2241, gtliteId("radium_dichloride"))
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Radium, 1, Chlorine, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2242 Thorium Dioxide
            ThoriumDioxide = Material.Builder(2242, gtliteId("thorium_dioxide"))
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Thorium, 1, Oxygen, 2)
                .build()

            // 2243 Uranyl Nitrate
            UranylNitrate = Material.Builder(2243, gtliteId("uranyl_nitrate"))
                .dust()
                .colorAverage().iconSet(SHINY)
                .components(Uraninite, 1, Nitrogen, 2, Oxygen, 6)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("UO2(NO3)2", true)

            // 2244 Barium Oxide
            BariumOxide = Material.Builder(2244, gtliteId("barium_oxide"))
                .dust()
                .colorAverage().iconSet(DULL)
                .components(Barium, 1, Oxygen, 1)
                .build()

            // 2245 Woods Glass
            WoodsGlass = Material.Builder(2245, gtliteId("woods_glass"))
                .ingot()
                .fluid()
                .color(0x730099).iconSet(SHINY)
                .components(SodaAsh, 6, SiliconDioxide, 3, BariumOxide, 2, Garnierite, 2)
                .flags(NO_SMASHING, NO_WORKING, DECOMPOSITION_BY_CENTRIFUGING, GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .blast { b ->
                    b.temp(1163, BlastProperty.GasTier.MID)
                        .blastStats(VA[IV])
                }
                .build()

            // 2246 Potassium Tertbutoxide
            PotassiumTertbutoxide = Material.Builder(2246, gtliteId("potassium_tertbutoxide"))
                .dust()
                .color(0xFB7366).iconSet(DULL)
                .components(Carbon, 4, Hydrogen, 9, Oxygen, 1, Potassium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2247 Caesium Hydroxide
            CaesiumHydroxide = Material.Builder(2247, gtliteId("caesium_hydroxide"))
                .dust()
                .color(0xAF6341).iconSet(METALLIC)
                .components(Caesium, 1, Oxygen, 1, Hydrogen, 1)
                .build()

            // 2248 Caesium Carbonate
            CaesiumCarbonate = Material.Builder(2248, gtliteId("caesium_carbonate"))
                .dust()
                .color(0x62D4F3).iconSet(SHINY)
                .components(Caesium, 2, Carbon, 1, Oxygen, 3)
                .build()

            // 2249 Sodium Hydride
            SodiumHydride = Material.Builder(2249, gtliteId("sodium_hydride"))
                .ingot()
                .color(0xCACAC8).iconSet(METALLIC)
                .components(Sodium, 1, Hydrogen, 1)
                .build()

            // 2250 Vanadium Pentoxide
            VanadiumPentoxide = Material.Builder(2250, gtliteId("vanadium_pentoxide"))
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Vanadium, 2, Oxygen, 5)
                .build()

            // 2251 Actinium Oxalate
            ActiniumOxalate = Material.Builder(2251, gtliteId("actinium_oxalate"))
                .dust()
                .color(0x92ACFF).iconSet(SHINY)
                .components(Actinium, 1, Carbon, 4, Oxygen, 8)
                .build()
                .setFormula("Ac(CO2)4", true)

            // 2252 Actinium Trihydride
            ActiniumTrihydride = Material.Builder(2252, gtliteId("actinium_trihydride"))
                .dust()
                .color(0xD5DFFF).iconSet(SHINY)
                .components(Actinium, 1, Hydrogen, 3)
                .flags(DISABLE_DECOMPOSITION, FORCE_GENERATE_BLOCK)
                .build()

            // 2253 Actinium Superhydride
            ActiniumSuperhydride = Material.Builder(2253, gtliteId("actinium_superhydride"))
                .dust()
                .plasma(FluidBuilder()
                    .temperature(500_000))
                .color(Actinium.materialRGB * 9 / 8).iconSet(SHINY)
                .components(Actinium, 1, Hydrogen, 12)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2254 Sodium Formate
            SodiumFormate = Material.Builder(2254, gtliteId("sodium_formate"))
                .dust()
                .color(0x416CC0).iconSet(ROUGH)
                .components(Carbon, 1, Hydrogen, 1, Oxygen, 2, Sodium, 1)
                .build()
                .setFormula("HCOONa", false)

            // 2255 Sodium Thiosulfate
            SodiumThiosulfate = Material.Builder(2255, gtliteId("sodium_thiosulfate"))
                .dust()
                .color(0x1436A7).iconSet(METALLIC)
                .components(Sodium, 2, Sulfur, 2, Oxygen, 3)
                .build()

            // 2256 Lithium Thiinediselenide
            LithiumThiinediselenide = Material.Builder(2256, gtliteId("lithium_thiinediselenide"))
                .dust()
                .color(0x689E64).iconSet(DULL)
                .components(Carbon, 4, Hydrogen, 4, Sulfur, 2, Lithium, 2, Selenium, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2257 BETS Perrhenate (BETSP)
            BETSPerrhenate = Material.Builder(2257, gtliteId("bets_perrhenate"))
                .dust()
                .color(0x98E993).iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Rhenium, 1, Carbon, 10, Hydrogen, 8, Sulfur, 4, Selenium, 4, Oxygen, 4)
                .build()
                .setFormula("(C10H8S4Se4)ReO4", true)

            // 2258 Helium-Neon
            HeliumNeon = Material.Builder(2258, gtliteId("helium_neon"))
                .gas()
                .color(0xFF0080)
                .components(Helium, 1, Neon, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build()

            // 2259 Germanium Tetrachloride
            GermaniumTetrachloride = Material.Builder(2259, gtliteId("germanium_tetrachloride"))
                .liquid()
                .color(0x787878)
                .components(Germanium, 1, Chlorine, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2260 Silicon Tetrachloride
            SiliconTetrachloride = Material.Builder(2260, gtliteId("silicon_tetrachloride"))
                .liquid()
                .color(0x5B5B7A)
                .components(Silicon, 1, Chlorine, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2261 Caesium Cerium Cobalt Indium
            CaesiumCeriumCobaltIndium = Material.Builder(2261, gtliteId("caesium_cerium_cobalt_indium"))
                .ingot()
                .fluid()
                .color(0x01E068).iconSet(MAGNETIC)
                .components(Caesium, 1, Cerium, 1, Cobalt, 2, Indium, 10)
                .flags(EXT_METAL, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES, GENERATE_FOIL,
                    GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW)
                .blast { b ->
                    b.temp(8900, BlastProperty.GasTier.HIGH) // Trinium
                        .blastStats(VA[UV], 34 * SECOND)
                        .vacuumStats(VA[ZPM], 17 * SECOND)
                }
                .build()

            // 2262 Mercury Cadmium Telluride
            MercuryCadmiumTelluride = Material.Builder(2262, gtliteId("mercury_cadmium_telluride"))
                .ingot()
                .fluid()
                .color(0x823C80).iconSet(BRIGHT)
                .flags(STD_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .components(Mercury, 2, Cadmium, 1, Tellurium, 2)
                .blast { b ->
                    b.temp(12170, BlastProperty.GasTier.HIGHER) // Adamantium
                        .blastStats(VA[UHV], 12 * SECOND)
                        .vacuumStats(VA[UV], 6 * SECOND)
                }
                .build()

            // 2263 Thallium Roentgenium Chloride
            ThalliumRoentgeniumChloride = Material.Builder(2263, gtliteId("thallium_roentgenium_chloride"))
                .ingot()
                .fluid()
                .color(0x3C5CB5).iconSet(MAGNETIC)
                .components(Thallium, 1, Roentgenium, 1, Chlorine, 3)
                .flags(EXT_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_RING)
                .blast { b ->
                    b.temp(12500, BlastProperty.GasTier.HIGHEST) // Adamantium
                        .blastStats(VA[UV], 48 * SECOND)
                        .vacuumStats(VA[ZPM], 24 * SECOND)
                }
                .build()

            // 2264 Silica Gel Base
            SilicaGelBase = Material.Builder(2264, gtliteId("silica_gel_base"))
                .liquid()
                .color(0x7170BE).iconSet(DULL)
                .components(SiliconDioxide, 1, SodiumHydroxide, 1, HydrochloricAcid, 1, Water, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(SiNa(OH)O2)(HCl)(H2O)", true)

            // 2265 Silica Gel
            SilicaGel = Material.Builder(2265, gtliteId("silica_gel"))
                .dust()
                .color(0x9695FD).iconSet(NANOPARTICLES)
                .components(SiliconDioxide, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2266 Nitronium Tetrafluoroborate
            NitroniumTetrafluoroborate = Material.Builder(2266, gtliteId("nitronium_tetrafluoroborate"))
                .dust()
                .color(0x787449).iconSet(DULL)
                .components(Nitrogen, 1, Oxygen, 2, Boron, 1, Fluorine, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2267 Nitrosonium Tetrafluoroborate
            NitrosoniumTetrafluoroborate = Material.Builder(2267, gtliteId("nitrosonium_tetrafluoroborate"))
                .dust()
                .color(0xA32A8C).iconSet(ROUGH)
                .components(Nitrogen, 1, Oxygen, 1, Boron, 1, Fluorine, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2268 Tetrafluoroboric Acid
            TetrafluoroboricAcid = Material.Builder(2268, gtliteId("tetrafluoroboric_acid"))
                .liquid(FluidBuilder().attributes(FluidAttributes.ACID))
                .color(0x83A731)
                .components(Hydrogen, 1, Boron, 1, Fluorine, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2269 Potassium Formate
            PotassiumFormate = Material.Builder(2269, gtliteId("potassium_formate"))
                .dust()
                .color(0x74B5A9).iconSet(METALLIC)
                .components(Carbon, 1, Hydrogen, 3, Oxygen, 1, Potassium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2270 Ammonium Sulfate
            AmmoniumSulfate = Material.Builder(2270, gtliteId("ammonium_sulfate"))
                .dust()
                .color(0x5858F4).iconSet(METALLIC)
                .components(Nitrogen, 4, Hydrogen, 8, Sulfur, 1, Oxygen, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(NH4)2SO4", true)

            // 2271 Ammonium Carbonate
            AmmoniumCarbonate = Material.Builder(2271, gtliteId("ammonium_carbonate"))
                .dust()
                .color(0x7C89D9).iconSet(SHINY)
                .components(Carbon, 1, Hydrogen, 8, Oxygen, 3, Nitrogen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(NH4)2CO3", true)

            // 2272 Ammonium Acetate
            AmmoniumAcetate = Material.Builder(2272, gtliteId("ammonium_acetate"))
                .dust()
                .color(0x646882).iconSet(METALLIC)
                .components(Carbon, 2, Hydrogen, 7, Oxygen, 2, Nitrogen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("NH4CH3CO2", true)

            // 2273 Sodium Azanide
            SodiumAzanide = Material.Builder(2273, gtliteId("sodium_azanide"))
                .dust()
                .colorAverage().iconSet(ROUGH)
                .components(Sodium, 1, Nitrogen, 1, Hydrogen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2274 Sodium Azide
            SodiumAzide = Material.Builder(2274, gtliteId("sodium_azide"))
                .dust()
                .colorAverage()
                .components(Sodium, 1, Nitrogen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2275 Palladium Bisdibenzylidieneacetone
            PalladiumBisdibenzylidieneacetone = Material.Builder(2275, gtliteId("palladium_bisdibenzylidieneacetone"))
                .dust()
                .color(0xBE81A0).iconSet(ROUGH)
                .components(Carbon, 51, Hydrogen, 42, Oxygen, 3, Palladium, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2276 Potassium Tetrachloroplatinate
            PotassiumTetrachloroplatinate = Material.Builder(2276, gtliteId("potassium_tetrachloroplatinate"))
                .dust()
                .color(0xF1B04F).iconSet(SHINY)
                .components(Potassium, 2, Platinum, 1, Chlorine, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2277 Ammonium Persulfate
            AmmoniumPersulfate = Material.Builder(2277, gtliteId("ammonium_persulfate"))
                .dust()
                .color(0x4242B7)
                .components(Nitrogen, 2, Hydrogen, 8, Sulfur, 2, Oxygen, 8)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(NH4)2S2O8", true)

            // 2278 SilverTetrafluoroborate
            SilverTetrafluoroborate = Material.Builder(2278, gtliteId("silver_tetrafluoroborate"))
                .dust()
                .color(0x818024)
                .components(Silver, 1, Boron, 1, Fluorine, 4)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2279 Silver Chloride
            SilverChloride = Material.Builder(2279, gtliteId("silver_chloride"))
                .dust()
                .color(0x8D8D8D).iconSet(METALLIC)
                .components(Silver, 1, Chlorine, 1)
                .build()

            // 2280 Silver Oxide
            SilverOxide = Material.Builder(2280, gtliteId("silver_oxide"))
                .dust()
                .color(0xA4A4A4)
                .components(Silver, 2, Oxygen, 1)
                .build()

            // 2281 Heavy Quark Enriched Mixture
            HeavyQuarkEnrichedMixture = Material.Builder(2281, gtliteId("heavy_quark_enriched_mixture"))
                .plasma(FluidBuilder()
                    .translation("gregtech.fluid.generic")
                    .temperature(400_000_000)
                    .customStill())
                .build()

            // 2282 Deuterium-Superheavy Mixture
            DeuteriumSuperheavyMixture = Material.Builder(2282, gtliteId("deuterium_superheavy_mixture"))
                .liquid(FluidBuilder()
                    .temperature(18240))
                .color(0x7B9EF8)
                .components(Deuterium, 2, MetastableHassium, 1, MetastableFlerovium, 1, MetastableOganesson, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2283 Heavy Quark Degenerate Matter (HQDM)
            HeavyQuarkDegenerateMatter = Material.Builder(2283, gtliteId("heavy_quark_degenerate_matter"))
                .ingot()
                .liquid()
                .plasma(FluidBuilder()
                    .temperature(1_600_000_000)
                    .customStill())
                .color(0x5DBD3A).iconSet(BRIGHT)
                .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_DENSE, GENERATE_RING, GENERATE_ROTOR,
                    GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FRAME, GENERATE_ROUND)
                .rotorStats(48.0f, 16.0f, 983040)
                .fluidPipeProperties(200_000, 10000, true, true, true, true)
                .build()

            // 2284 Quantumchromodynamically ConfinedMatter (QCM)
            QuantumchromodynamicallyConfinedMatter = Material.Builder(2284, gtliteId("quantumchromodynamically_confined_matter"))
                .ingot()
                .liquid()
                .color(0xF0A745).iconSet(BRIGHT)
                .flags(STD_METAL, GENERATE_FOIL, GENERATE_FINE_WIRE)
                .cableProperties(V[UIV], 24, 12)
                .build()

            // 2285 Sodium Ethoxide
            SodiumEthoxide = Material.Builder(2285, gtliteId("sodium_ethoxide"))
                .dust()
                .color(Sodium.materialRGB + Ethanol.materialRGB)
                .components(Carbon, 2, Hydrogen, 5, Oxygen, 1, Sodium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()

            // 2286 Iron (II) Chloride
            Iron2Chloride = Material.Builder(2286, gtliteId("iron_2_chloride"))
                .liquid()
                .color(Iron3Chloride.materialRGB - 10)
                .components(Iron, 1, Chlorine, 2)
                .build()

            // 2287 Palladium Fullerene Matrix
            PalladiumFullereneMatrix = Material.Builder(2287, gtliteId("palladium_fullerene_matrix"))
                .dust()
                .color(0x40AEE0).iconSet(SHINY)
                .components(Carbon, 73, Hydrogen, 15, Nitrogen, 1, Iron, 1, Palladium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(C73H15NFe)Pd", true)

            // 2288 Lutetium-Thulium-Yttrium Chlorides Solution
            LuTmYChloridesSolution = Material.Builder(2288, gtliteId("lu_tm_y_chlorides_solution"))
                .liquid()
                .colorAverage()
                .components(Lutetium, 2, Thulium, 2, Yttrium, 6, Chlorine, 30, Water, 15)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("(LuCl3)2(TmCl3)2(YCl3)6(H2O)15", true)

            // 2289 Sodium Vanadate
            SodiumVanadate = Material.Builder(2289, gtliteId("sodium_vanadate"))
                .dust()
                .color(0xCC9933).iconSet(SHINY)
                .components(Sodium, 3, Vanadium, 1, Oxygen, 4)
                .build()

            // 2290 Lu/Tm-doped Yttrium Vanadate Deposition
            LuTmDopedYttriumVanadateDeposition = Material.Builder(2290, gtliteId("lu_tm_doped_yttrium_vanadate_deposition"))
                .dust()
                .colorAverage().iconSet(FINE)
                .components(Lutetium, 1, Thulium, 1, SodiumVanadate, 1, RareEarth, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Lu/Tm:YVO?", false)

            // 2291 Lu/Tm:YVO
            LuTmYVO = Material.Builder(2291, gtliteId("lu_tm_yvo"))
                .gem()
                .color(0x8C1B23).iconSet(GEM_HORIZONTAL)
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_LENS, CRYSTALLIZABLE)
                .components(Yttrium, 1, Vanadium, 1, Oxygen, 1, Lutetium, 1, Thulium, 1)
                .build()
                .setFormula("Lu/Tm:YVO", false)

            // 2292 Hexagonal Silicon Nitride
            HexagonalSiliconNitride = Material.Builder(2292, gtliteId("hexagonal_silicon_nitride"))
                .gem()
                .color(0x8C7BB6).iconSet(GEM_HORIZONTAL)
                .components(Silicon, 3, Nitrogen, 4)
                .flags(DISABLE_DECOMPOSITION, DISABLE_CRYSTALLIZATION, GENERATE_PLATE, GENERATE_LENS)
                .build()
                .setFormula("h-Si3N4", true)

            // 2293 Cubic Silicon Nitride
            CubicSiliconNitride = Material.Builder(2293, gtliteId("cubic_silicon_nitride"))
                .gem()
                .color(0x415B70).iconSet(RUBY)
                .components(Silicon, 3, Nitrogen, 4)
                .flags(DISABLE_DECOMPOSITION, DISABLE_CRYSTALLIZATION, GENERATE_PLATE, GENERATE_LENS)
                .build()
                .setFormula("c-Si3N4", true)

            // 2294 Cerium Carbonate
            CeriumCarbonate = Material.Builder(2294, gtliteId("cerium_carbonate"))
                .dust()
                .color(0x57855C).iconSet(SHINY)
                .components(Cerium, 2, Carbon, 3, Oxygen, 9)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Ce2(CO3)3", true)

            // 2295 Dielectric Formation Mixture
            DielectricFormationMixture = Material.Builder(2295, gtliteId("dielectric_formation_mixture"))
                .liquid(FluidBuilder().temperature(209))
                .color(0xE62A35)
                .components(ManganeseDifluoride, 1, ZincSulfide, 1, TantalumPentoxide, 1, Rutile, 1, Ethanol, 1)
                .flags(DISABLE_DECOMPOSITION) // Add centrifuging decomposition by OpticalCircuits.
                .build()

            // 2296 Chloroauric Acid
            ChloroauricAcid = Material.Builder(2296, gtliteId("chloroauric_acid"))
                .liquid(FluidBuilder().attributes(FluidAttributes.ACID))
                .color(0xD1B62C)
                .components(Hydrogen, 1, Gold, 1, Chlorine, 4)
                .build()

            // 2297 Cadmium Sulfide
            CadmiumSulfide = Material.Builder(2297, gtliteId("cadmium_sulfide"))
                .dust()
                .color(0xC8C43C).iconSet(METALLIC)
                .components(Cadmium, 1, Sulfur, 1)
                .flags(DECOMPOSITION_BY_ELECTROLYZING, GENERATE_PLATE)
                .build()

            // 2298 Bismuth Chalcogenide
            BismuthChalcogenide = Material.Builder(2298, gtliteId("bismuth_chalcogenide"))
                .ingot()
                .color(0x91994D).iconSet(SHINY)
                .components(Bismuth, 1, Antimony, 1, Tellurium, 2, Sulfur, 1)
                .flags(STD_METAL, DECOMPOSITION_BY_ELECTROLYZING, GENERATE_FOIL)
                .build()

            // 2299 Plutonium Trihydride
            PlutoniumTrihydride = Material.Builder(2299, gtliteId("plutonium_trihydride"))
                .dust()
                .color(0x140002).iconSet(SHINY)
                .components(Plutonium244, 1, Hydrogen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("PuH3", true)

            // 2300 Plutonium Phosphide
            PlutoniumPhosphide = Material.Builder(2300, gtliteId("plutonium_phosphide"))
                .ingot()
                .color(0x1F0104).iconSet(MAGNETIC)
                .components(Plutonium244, 1, Phosphorus, 1)
                .flags(STD_METAL)
                .build()
                .setFormula("PuP", true)

            // 2301 Phosphine
            Phosphine = Material.Builder(2301, gtliteId("phosphine"))
                .gas()
                .color(0xACB330)
                .components(Phosphorus, 1, Hydrogen, 3)
                .flags(DECOMPOSITION_BY_ELECTROLYZING, FLAMMABLE)
                .build()

        }

    }

}