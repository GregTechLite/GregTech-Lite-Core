package gregtechlite.gtlitecore.api.unification.material

import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.ZPM
import gregtech.api.fluids.attribute.FluidAttributes
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Benzene
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Butene
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Dimethylamine
import gregtech.api.unification.material.Materials.Dimethylhydrazine
import gregtech.api.unification.material.Materials.EXT_METAL
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iodine
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.STD_METAL
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Sugar
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Tetranitromethane
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.VinylAcetate
import gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FINE_WIRE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FOIL
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_LENS
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_LONG_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_RING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING_SMALL
import gregtech.api.unification.material.info.MaterialFlags.NO_SMASHING
import gregtech.api.unification.material.info.MaterialFlags.NO_SMELTING
import gregtech.api.unification.material.info.MaterialIconSet.BRIGHT
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.material.info.MaterialIconSet.FINE
import gregtech.api.unification.material.info.MaterialIconSet.METALLIC
import gregtech.api.unification.material.info.MaterialIconSet.ROUGH
import gregtech.api.unification.material.info.MaterialIconSet.SHINY
import gregtechlite.gtlitecore.api.extension.cableProp
import gregtechlite.gtlitecore.api.extension.colorAverage
import gregtechlite.gtlitecore.api.extension.fluidPipeProp
import gregtechlite.gtlitecore.api.extension.liquid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetamide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AceticAnhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AcetoneCyanohydrin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetonitrile
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AcetylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AdipicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AminatedFullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AminooxyaceticAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aminophenol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumBifluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumCyanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumFluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aniline
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AscorbicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Azafullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BETS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Benzaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BenzylBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BenzylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Benzylamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BenzyltrimethylammoniumBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Biotin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BiphenylTetracarboxylicAcidDianhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bipyridine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bistrichloromethylbenzene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Borazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BromoBromomethylNaphthalene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bromobutane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bromodihydrothiine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bromomethane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butanediol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butanol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butyllithium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Carbamide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cellulose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CetaneTrimethylAmmoniumBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChloroaceticAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Chlorobutane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CitricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Codeine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creosol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CrudeHexanitrohexaaxaisowurtzitane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CyanIndigo
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cyclooctadiene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cycloparaphenylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CyclotetramethyleneTetranitroamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DehydroascorbicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DenseHydrazineRocketFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diacetyl
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DiaminostilbenedisulfonicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diaminotoluene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dibenzylideneacetone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dibenzyltetraacetylhexaazaisowurtzitane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diborane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dibromoacrolein
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dibromomethylbenzene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dichlorocyclooctadieneplatinium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dichloroethane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dichloromethane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dicyclopentadiene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DiethylEther
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DiethylSulfide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DiethylhexylPhosphoricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diethylthiourea
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Difluorobenzophenone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diiodobiphenyl
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diisopropylcarbodiimide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diketopyrrolopyrrole
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimethylSulfide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimethylTerephthalate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dimethylacetamide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimethylamineHydrochloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dimethylaminopyridine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dimethylformamide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dinitrodipropanyloxybenzene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dinitrotoluene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DirectBrown77
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DitertbutylDicarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Durene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EDTA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Edot
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EosinY
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Erythrosine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylanthrahydroquinone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylanthraquinone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneDibromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneGlycol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneVinylAcetatePolymer
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylenediamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthylenediaminePyrocatechol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylhexanol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ferrocene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ferrocenylfulleropyrddolidine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorescein
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FluorinatedEthylenePropylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorobenzene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorotoluene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Formaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FormicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fructose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FulvicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GammaButyrolactone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GeodesicPolyarene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Glucose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Glutamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Glyoxal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GrignardReagent
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Guaiacol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HRAMagnesium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexabenzylhexaazaisowurtzitane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexafluoropropylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexamethylenediamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexamethylenetetramine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexanediol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexanitrohexaaxaisowurtzitane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hydrazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrobromicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenCyanide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hydroquinone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hydroxyquinoline
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Indanone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Indene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Indigo
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Isochloropropane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Isophthaloylbisdiethylthiourea
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IsopropylAlcohol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IsopropylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IsopropylSuccinate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonE
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonK
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kevlar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LinoleicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MalonicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mauveine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methoxycreosol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MethylFormate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methylamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methylhydrazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MethylhydrazineNitrateRocketFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methylparatoluate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methyltrichlorosilane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NDifluorophenylpyrrole
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NHydroxysuccinimide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NMethylPyrrolidone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Naphthylamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nigrosin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nitroaniline
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nitrotoluene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Octaazacubane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OctafluoroPentanol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Octene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OxalicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Oxydianiline
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ParaPhenylenediamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ParaToluicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ParaXylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PedotPSS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PedotTMA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pentane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phenothiazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhenothiazinePropylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhenylC61ButyricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhenylC61ButyricStyrene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhenylenedioxydiaceticAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhenylpentanoicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phenylsodium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phosgene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhosphonitrilicChlorideTrimer
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phosphorene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhosphorylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhthalicAnhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polyetheretherketone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolyethyleneTerephthalate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polyisoprene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polymethylmethacrylate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolyphosphonitrileFluoroRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polystyrene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolystyreneSulfonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polytetrahydrofuran
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolytetramethyleneGlycolRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PretreatedZylon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Promethazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pyridine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pyrocatechol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PyromelliticDianhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RawPolyphosphonitrileFluoroRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RawPolytetramethyleneGlycolRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resorcinol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RhodamineB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SacchariaAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Sarcosine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Silane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumSulfanilate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Sorbose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuccinicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuccinicAnhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Succinimide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuccinimidylAcetate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Terephthalaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TerephthalicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TerephthaloylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tertbutanol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TertbutylAlcohol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TertbutylAzidoformate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetraacetyldinitrosohexaazaisowurtzitane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetrabromoethane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetrabromoindigo
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetracene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetraethylammoniumBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetrahydrofuran
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetramethylammoniumChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetramethylammoniumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetrasodiumEDTA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThionylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TolueneDiisocyanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TolueneTetramethylDiisocyanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tributylamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trichloroborazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trichloroethylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trimethylaluminium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trimethylamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trimethylgallium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TrimethyltinChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Triphenylphosphine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trisaminoethylamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Truxene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Xylenol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Xylose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Zylon
import gregtechlite.gtlitecore.api.unification.material.GTMaterialBuilder.addMaterial

object GTLiteOrganicChemistryMaterials
{

    // @formatter:off

    fun init()
    {
        // 8001 Dicyclopentadiene
        Dicyclopentadiene = addMaterial(8001, "dicyclopentadiene")
        {
            liquid()
            {
                temperature(306)
            }
            color(0x9C388B)
            components(Carbon, 10, Hydrogen, 12)
        }

        // 8002 Pentane
        Pentane = addMaterial(8002, "pentane")
        {
            liquid()
            color(0xE8E7BE)
            components(Carbon, 5, Hydrogen, 12)
        }

        // 8003 Polyisoprene
        Polyisoprene = addMaterial(8003, "polyisoprene")
        {
            polymer()
            liquid()
            color(0x575757).iconSet(SHINY)
            components(Carbon, 5, Hydrogen, 8)
            flags(NO_SMASHING, NO_SMELTING, DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_FOIL)
        }

        // 8004 Para Xylene
        ParaXylene = addMaterial(8004, "para_xylene")
        {
            liquid()
            color(0x666040)
            components(Carbon, 8, Hydrogen, 10)
        }

        // 8005 Nitrotoluene
        Nitrotoluene = addMaterial(8005, "nitrotoluene")
        {
            liquid()
            color(0x99236E)
            components(Carbon, 7, Hydrogen, 7, Nitrogen, 1, Oxygen, 2)
        }

        // 8006 Diaminostilbenedisulfonic Acid (DSDA)
        DiaminostilbenedisulfonicAcid = addMaterial(8006, "diaminostilbenedisulfonic_acid")
        {
            dust()
            color(0xF2F2F2).iconSet(ROUGH)
            components(Carbon, 14, Hydrogen, 14, Nitrogen, 2, Oxygen, 6, Sulfur, 2)
        }

        // 8007 Succinic Acid
        SuccinicAcid = addMaterial(8007, "succinic_acid")
        {
            dust()
            color(0x0C3A5B).iconSet(DULL)
            components(Carbon, 4, Hydrogen, 6, Oxygen, 4)
        }

        // 8008 Butanediol
        // Add Electrolysis Reaction by PEDOTChain.
        Butanediol = addMaterial(8008, "butanediol")
        {
            liquid()
            color(0xAAC4DA)
            components(Carbon, 4, Hydrogen, 10, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8009 Tetrahydrofuran
        Tetrahydrofuran = addMaterial(8009, "tetrahydrofuran")
        {
            liquid()
            color(0x0BCF52)
            components(Carbon, 4, Hydrogen, 8, Oxygen, 1)
        }

        // 8010 Ethylene Dibromide
        EthyleneDibromide = addMaterial(8010, "ethylene_dibromide")
        {
            liquid()
            color(0x4F1743)
            components(Carbon, 2, Hydrogen, 4, Bromine, 2)
        }

        // 8011 Grignard Reagent
        GrignardReagent = addMaterial(8011, "grignard_reagent")
        {
            liquid()
            color(0xA12AA1)
            components(Carbon, 1, Hydrogen, 3, HRAMagnesium, 1, Bromine, 1)
        }

        // 8012 Ethylhexanol
        Ethylhexanol = addMaterial(8012, "ethylhexanol")
        {
            liquid()
            color(0xFEEA9A)
            components(Carbon, 8, Hydrogen, 10, Oxygen, 1)
        }

        // 8013 Di-(2-Ethylhexyl) Phosphoric Acid
        DiethylhexylPhosphoricAcid = addMaterial(8013, "diethylhexyl_phosphoric_acid")
        {
            liquid()
            color(0xFFFF99)
            components(Carbon, 16, Hydrogen, 35, Oxygen, 4, Phosphorus, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8014 Formic Acid
        FormicAcid = addMaterial(8014, "formic_acid")
        {
            liquid()
            {
                attribute(FluidAttributes.ACID)
            }
            color(0xFFAA77)
            components(Hydrogen, 2, Carbon, 1, Oxygen, 2)
        }

        // 8015 Methyl Formate
        MethylFormate = addMaterial(8015, "methyl_formate")
        {
            liquid()
            color(0xFFAAAA)
            components(Hydrogen, 4, Carbon, 2, Oxygen, 2)
        }

        // 8016 Thionyl Chloride
        ThionylChloride = addMaterial(8016, "thionyl_chloride")
        {
            liquid()
            color(0xEBE863)
            components(Sulfur, 1, Oxygen, 1, Chlorine, 2)
        }

        // 8017 Phthalic Anhydride
        PhthalicAnhydride = addMaterial(8017, "phthalic_anhydride")
        {
            dust()
            color(0xEEAAEE)
            components(Carbon, 8, Hydrogen, 4, Oxygen, 3)
        }

        // 8018 Ethylanthraquinone
        Ethylanthraquinone = addMaterial(8018, "ethylanthraquinone")
        {
            liquid()
            color(0xCC865A)
            components(Carbon, 16, Hydrogen, 12, Oxygen, 2)
        }

        // 8019 Ethylanthrahydroquinone
        Ethylanthrahydroquinone = addMaterial(8019, "ethylanthrahydroquinone")
        {
            liquid()
            color(0xAD531A)
            components(Carbon, 16, Hydrogen, 18, Oxygen, 2)
        }

        // 8020 Hydrazine
        Hydrazine = addMaterial(8020, "hydrazine")
        {
            liquid()
            color(0xB50707)
            components(Nitrogen, 2, Hydrogen, 4)
        }

        // 8021 Citric Acid
        CitricAcid = addMaterial(8021, "citric_acid")
        {
            liquid()
            color(0xFFCC00)
            components(Carbon, 6, Hydrogen, 8, Oxygen, 7)
        }

        // 8022 Glutamine
        Glutamine = addMaterial(8022, "glutamine")
        {
            dust()
            color(0xEDE9B4).iconSet(DULL)
            components(Carbon, 5, Hydrogen, 10, Nitrogen, 2, Oxygen, 3)
        }

        // 8023 Linoleic Acid
        LinoleicAcid = addMaterial(8023, "linoleic_acid")
        {
            liquid()
            color(0xD5D257)
            components(Carbon, 18, Hydrogen, 32, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8024 Biotin
        Biotin = addMaterial(8024, "biotin")
        {
            dust()
            color(0x68CC6A)
            components(Carbon, 10, Hydrogen, 16, Nitrogen, 2, Oxygen, 3, Sulfur, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8025 Durene
        Durene = addMaterial(8025, "durene")
        {
            dust()
            color(0x336040).iconSet(FINE)
            components(Carbon, 10, Hydrogen, 14)
        }

        // 8026 Pyromellitic Dianhydride (PDMA)
        PyromelliticDianhydride = addMaterial(8026, "pyromellitic_dianhydride")
        {
            dust()
            color(0xF0EAD6).iconSet(ROUGH)
            components(Carbon, 10, Hydrogen, 2, Oxygen, 6)
        }

        // 8027 Aminophenol
        Aminophenol = addMaterial(8027, "aminophenol")
        {
            dust()
            color(0xFFFFFF).iconSet(SHINY)
            components(Carbon, 6, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
        }

        // 8028 Aniline
        Aniline = addMaterial(8028, "aniline")
        {
            liquid()
            color(0x4c911d)
            components(Carbon, 6, Hydrogen, 7, Nitrogen, 1)
        }

        // 8029 Oxydianiline (ODA)
        Oxydianiline = addMaterial(8029, "oxydianiline")
        {
            dust()
            color(0xF0E130)
            components(Carbon, 12, Hydrogen, 12, Nitrogen, 2, Oxygen, 1)
        }

        // 8030 Kapton-K (Poly 4,4'-Oxydiphenylene-Pyromellitimide)
        KaptonK = addMaterial(8030, "kapton_k")
        {
            polymer()
            liquid()
            color(0xFFCE52)
            components(Carbon, 12, Hydrogen, 12, Nitrogen, 2, Oxygen, 1)
            flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
        }

        // 8031 Biphenyl Tetracarboxylic Acid Dianhydride (BTAD)
        BiphenylTetracarboxylicAcidDianhydride = addMaterial(8031, "biphenyl_tetracarboxylic_acid_dianhydride")
        {
            dust()
            color(0xFF7F50)
            components(Carbon, 16, Hydrogen, 6, Oxygen, 6)
        }

        // 8032 Nitroaniline
        Nitroaniline = addMaterial(8032, "nitroaniline")
        {
            liquid()
            color(0x2A6E68)
            components(Carbon, 6, Hydrogen, 6, Nitrogen, 2, Oxygen, 2)
        }

        // 8033 Para-Phenylenediamine
        ParaPhenylenediamine = addMaterial(8033, "para_phenylenediamine")
        {
            dust()
            color(0x4A8E7B).iconSet(ROUGH)
            components(Carbon, 6, Hydrogen, 8, Nitrogen, 2)
        }

        // 8034 Kapton-E
        KaptonE = addMaterial(8034, "kapton_e")
        {
            polymer()
            liquid()
            color(0xFFDF8C)
            components(Carbon, 12, Hydrogen, 12, Nitrogen, 2, Oxygen, 1)
            flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, NO_SMASHING, NO_SMELTING, GENERATE_FOIL)
        }

        // 8035 Hydroxyquinoline
        Hydroxyquinoline = addMaterial(8035, "hydroxyquinoline")
        {
            dust()
            color(0x3A9A71).iconSet(METALLIC)
            components(Carbon, 9, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
        }

        // 8036 Hydrogen Cyanide
        HydrogenCyanide = addMaterial(8036, "hydrogen_cyanide")
        {
            liquid()
            color(0x6E6A5E)
            components(Hydrogen, 1, Carbon, 1, Nitrogen, 1)
        }

        // 8037 Acetone Cyanohydrin
        AcetoneCyanohydrin = addMaterial(8037, "acetone_cyanohydrin")
        {
            liquid()
            color(0xA1FFD0)
            components(Carbon, 4, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
        }

        // 8038 Polymethylmethacrylate (PMMA)
        Polymethylmethacrylate = addMaterial(8038, "polymethylmethacrylate")
        {
            ingot()
            liquid()
            color(0x91CAE1)
            components(Carbon, 5, Hydrogen, 8, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE,
                   GENERATE_LENS)
        }

        // 8039 Diacetyl
        Diacetyl = addMaterial(8039, "diacetyl")
        {
            liquid()
            color(0xF7FF65)
            components(Carbon, 4, Hydrogen, 6, Oxygen, 2)
        }

        // 8040 Ethylene Oxide
        EthyleneOxide = addMaterial(8040, "ethylene_oxide")
        {
            gas()
            color(0xDCBFE1)
            components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
        }

        // 8041 Ethylene Glycol
        EthyleneGlycol = addMaterial(8041, "ethylene_glycol")
        {
            liquid()
            color(0x286632)
            components(Carbon, 2, Hydrogen, 6, Oxygen, 2)
        }

        // 8042 3,4-Ethylenedioxythiophene (EDOT)
        Edot = addMaterial(8042, "edot")
        {
            liquid()
            color(0xB1FFD7)
            components(Carbon, 6, Hydrogen, 6, Oxygen, 2, Sulfur, 1)
        }

        // 8043 Polystyrene (PS)
        Polystyrene = addMaterial(8043, "polystyrene")
        {
            polymer()
            liquid()
            color(0xE1C2C2)
            components(Carbon, 8, Hydrogen, 8)
            flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
        }

        // 8044 Polystyrene Sulfonate (PSS)
        PolystyreneSulfonate = addMaterial(8044, "polystyrene_sulfonate")
        {
            polymer()
            liquid()
            color(0xE17C72)
            components(Carbon, 8, Hydrogen, 8, Sulfur, 1, Oxygen, 3)
            flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
        }

        // 8045 PEDOT:PSS
        PedotPSS = addMaterial(8045, "pedot_pss")
        {
            polymer()
            liquid()
            color(0xE165A7)
            flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
            components(Edot, 1, PolystyreneSulfonate, 1)
            cableProp(V[ZPM], 6, 1)
        }

        // 8046 PEDOT-TMA
        PedotTMA = addMaterial(8046, "pedot_tma")
        {
            polymer()
            liquid()
            color(0x5E9EE1)
            components(Edot, 1, Polymethylmethacrylate, 2)
            flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
            cableProp(V[UV], 8, 4)
        }

        // 8047 Methylamine
        Methylamine = addMaterial(8047, "methylamine")
        {
            gas()
            color(0xAA6600)
            components(Carbon, 1, Hydrogen, 5, Nitrogen, 1)
        }

        // 8048 Trimethylamine
        Trimethylamine = addMaterial(8048, "trimethylamine")
        {
            gas()
            color(0xBB7700)
            components(Carbon, 3, Hydrogen, 9, Nitrogen, 1)
        }

        // 8049 Tetramethylammonium Chloride
        TetramethylammoniumChloride = addMaterial(8049, "tetramethylammonium_chloride")
        {
            dust()
            color(0x27FF81).iconSet(METALLIC)
            components(Carbon, 4, Hydrogen, 12, Nitrogen, 1, Chlorine, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8050 Tetramethylammonium Hydroxide (TMAH)
        TetramethylammoniumHydroxide = addMaterial(8050, "tetramethylammonium_hydroxide")
        {
            liquid()
            color(0x40FFD6)
            components(Nitrogen, 1, Carbon, 4, Hydrogen, 12, Oxygen, 1, Hydrogen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8051 Pyrocatechol
        Pyrocatechol = addMaterial(8051, "pyrocatechol")
        {
            dust()
            color(0x784421).iconSet(DULL)
            components(Carbon, 6, Hydrogen, 6, Oxygen, 2)
        }

        // 8052 1,2-Dichloroethane
        Dichloroethane = addMaterial(8052, "dichloroethane")
        {
            liquid()
            color(0xDAAED3)
            components(Carbon, 2, Hydrogen, 4, Chlorine, 2)
        }

        // 8053 Ethylenediamine
        Ethylenediamine = addMaterial(8053, "ethylenediamine")
        {
            liquid()
            color(0xD00ED0)
            components(Carbon, 2, Hydrogen, 8, Nitrogen, 2)
        }

        // 8054 Ethylenediamine Pyrocatechol (EDP)
        EthylenediaminePyrocatechol = addMaterial(8054, "ethylenediamine_pyrocatechol")
        {
            liquid()
            color(0xFBFF17)
            components(Ethylenediamine, 1, Pyrocatechol, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8055 Formaldehyde
        Formaldehyde = addMaterial(8055, "formaldehyde")
        {
            liquid()
            color(0x858F40)
            components(Carbon, 1, Hydrogen, 2, Oxygen, 1)
        }

        // 8056 Tetrasodium EDTA
        TetrasodiumEDTA = addMaterial(8056, "tetrasodium_ethylenediaminetetraacetic_acid")
        {
            dust()
            color(0x8890E0).iconSet(SHINY)
            components(Carbon, 10, Hydrogen, 12, Nitrogen, 2, Oxygen, 8, Sodium, 4)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8057 Ethylenediaminetetraacetic Acid (EDTA)
        EDTA = addMaterial(8057, "ethylenediaminetetraacetic_acid")
        {
            dust()
            liquid()
            color(0x87E6D9).iconSet(ROUGH)
            components(Carbon, 10, Hydrogen, 16, Nitrogen, 2, Oxygen, 8)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8058 Hexafluoropropylene
        Hexafluoropropylene = addMaterial(8058, "hexafluoropropylene")
        {
            liquid()
            color(0x141D6F)
            components(Carbon, 3, Fluorine, 6)
        }

        // 8059 Fluorinated Ethylene Propylene (FEP)
        FluorinatedEthylenePropylene = addMaterial(8059, "fluorinated_ethylene_propylene")
        {
            liquid()
            color(0xC8C8C8).iconSet(DULL)
            components(Carbon, 5, Fluorine, 10)
        }

        // 8060 Trichloroethylene
        Trichloroethylene = addMaterial(8060, "trichloroethylene")
        {
            liquid()
            color(0xB685B1)
            components(Carbon, 2, Hydrogen, 1, Chlorine, 3)
        }

        // 8061 Chloroacetic Acid
        ChloroaceticAcid = addMaterial(8061, "chloroacetic_acid")
        {
            dust()
            color(0x38541A).iconSet(SHINY)
            components(Carbon, 2, Hydrogen, 3, Chlorine, 1, Oxygen, 2)
        }

        // 8062 Malonic Acid
        MalonicAcid = addMaterial(8062, "malonic_acid")
        {
            dust()
            color(0x61932E).iconSet(SHINY)
            components(Carbon, 3, Hydrogen, 4, Oxygen, 4)
        }

        // 8063 Phosphoryl Chloride
        PhosphorylChloride = addMaterial(8063, "phosphoryl_chloride")
        {
            liquid()
            color(0xE8BB5B)
            components(Phosphorus, 1, Oxygen, 1, Chlorine, 3)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8064 Phosphonitrilic Chloride Trimer
        PhosphonitrilicChlorideTrimer = addMaterial(8064, "phosphonitrilic_chloride_trimer")
        {
            liquid()
            color(0x082C38)
            components(Chlorine, 6, Nitrogen, 3, Phosphorus, 3)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8065 Fluorobenzene
        Fluorobenzene = addMaterial(8065, "fluorobenzene")
        {
            liquid()
            color(0x7CCA88)
            components(Carbon, 6, Hydrogen, 5, Fluorine, 1)
        }

        // 8066 Octafluoro Pentanol
        OctafluoroPentanol = addMaterial(8066, "octafluoro_pentanol")
        {
            liquid()
            color(0xE5EBDE)
            components(Carbon, 5, Hydrogen, 4, Fluorine, 8, Oxygen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8067 Raw Polyphosphonitrile Fluoro Rubber
        RawPolyphosphonitrileFluoroRubber = addMaterial(8067, "raw_polyphosphonitrile_fluoro_rubber")
        {
            dust()
            color(0x585858)
            components(Carbon, 24, Hydrogen, 16, Oxygen, 8, Nitrogen, 4, Phosphorus, 4, Fluorine, 40)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8068 Polyphosphonitrile Fluoro Rubber
        PolyphosphonitrileFluoroRubber = addMaterial(8068, "polyphosphonitrile_fluoro_rubber")
        {
            polymer()
            liquid()
            color(0x372B28)
            components(Carbon, 24, Hydrogen, 16, Oxygen, 8, Nitrogen, 4, Phosphorus, 4, Fluorine, 40)
            flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_ROD, GENERATE_RING, GENERATE_FOIL)
        }

        // 8069 Polytetrahydrofuran
        Polytetrahydrofuran = addMaterial(8069, "polytetrahydrofuran")
        {
            liquid()
            color(0x089B3E)
            components(Carbon, 4, Hydrogen, 10, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8070 Dinitrotoluene
        Dinitrotoluene = addMaterial(8070, "dinitrotoluene")
        {
            liquid()
            color(0xEAEFC9)
            components(Carbon, 7, Hydrogen, 6, Nitrogen, 2, Oxygen, 4)
        }

        // 8071 Diaminotoluene
        Diaminotoluene = addMaterial(8071, "diaminotoluene")
        {
            liquid()
            color(0xEA8204)
            components(Carbon, 7, Hydrogen, 7, Nitrogen, 2)
        }

        // 8072 Phosgene
        Phosgene = addMaterial(8072, "phosgene")
        {
            gas()
            color(0x48927C)
            components(Carbon, 1, Oxygen, 1, Chlorine, 2)
        }

        // 8073 Toluene Diisocyanate
        TolueneDiisocyanate = addMaterial(8073, "toluene_diisocyanate")
        {
            liquid()
            color(0xCCCC66)
            components(Carbon, 9, Hydrogen, 8, Nitrogen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8074 Toluene Tetramethyl Diisocyanate
        TolueneTetramethylDiisocyanate = addMaterial(8074, "toluene_tetramethyl_diisocyanate")
        {
            liquid()
            color(0xBFBFBF)
            components(Carbon, 19, Hydrogen, 12, Oxygen, 3, Nitrogen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8075 Raw Polytetramethylene Glycol Rubber
        RawPolytetramethyleneGlycolRubber = addMaterial(8075, "raw_polytetramethylene_glycol_rubber")
        {
            dust()
            color(0xFFFFFF).iconSet(ROUGH)
            components(Carbon, 23, Hydrogen, 23, Oxygen, 5, Nitrogen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8076 Polytetramethylene Glycol Rubber
        PolytetramethyleneGlycolRubber = addMaterial(8076, "polytetramethylene_glycol_rubber")
        {
            polymer()
            liquid()
            color(0xFFFFFF)
            components(Carbon, 23, Hydrogen, 23, Oxygen, 5, Nitrogen, 2)
            flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_ROD, GENERATE_RING, GENERATE_FOIL)
        }

        // 8077 Dense Hydrazine Rocket Fuel
        DenseHydrazineRocketFuel = addMaterial(8077, "dense_hydrazine_rocket_fuel")
        {
            liquid()
            color(0x912565)
            components(Dimethylhydrazine, 1, Methanol, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8078 Methylhydrazine
        Methylhydrazine = addMaterial(8078, "methylhydrazine")
        {
            liquid()
            color(0x321452)
            components(Carbon, 1, Hydrogen, 6, Nitrogen, 2)
        }

        // 8079 Methylhydrazine Nitrate Rocket Fuel
        MethylhydrazineNitrateRocketFuel = addMaterial(8079, "methylhydrazine_nitrate_rocket_fuel")
        {
            liquid()
            color(0x607186)
            components(Methylhydrazine, 1, Tetranitromethane, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8080 Resorcinol
        Resorcinol = addMaterial(8080, "resorcinol")
        {
            liquid()
            color(0x9DA38D)
            components(Carbon, 6, Hydrogen, 6, Oxygen, 2)
        }

        // 8081 Fluorotoluene
        Fluorotoluene = addMaterial(8081, "fluorotoluene")
        {
            liquid()
            color(0x6EC5B8)
            components(Carbon, 7, Hydrogen, 7, Fluorine, 1)
        }

        // 8082 Difluorobenzophenone
        Difluorobenzophenone = addMaterial(8082, "difluorobenzophenone")
        {
            dust()
            color(0xC44DA5).iconSet(SHINY)
            components(Carbon, 13, Hydrogen, 8, Oxygen, 1, Fluorine, 2)
        }

        // 8083 Hydroquinone
        Hydroquinone = addMaterial(8083, "hydroquinone")
        {
            liquid()
            color(0x83251A)
            components(Carbon, 6, Hydrogen, 6, Oxygen, 2)
        }

        // 8084 Polyetheretherketone (PEEK)
        Polyetheretherketone = addMaterial(8084, "polyetheretherketone")
        {
            polymer()
            liquid()
            color(0x45433D)
            components(Carbon, 20, Hydrogen, 12, Oxygen, 3)
            flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
        }

        // 8085 Fluorescein
        Fluorescein = addMaterial(8085, "fluorescein")
        {
            dust()
            color(0xE0E660).iconSet(SHINY)
            components(Carbon, 20, Hydrogen, 12, Oxygen, 5)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8086 Erythrosine
        Erythrosine = addMaterial(8086, "erythrosine")
        {
            dust()
            color(0xC63611).iconSet(DULL)
            components(Carbon, 20, Hydrogen, 6, Oxygen, 5, Sodium, 2, Iodine, 4)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8087 Isochloropropane
        Isochloropropane = addMaterial(8087, "isochloropropane")
        {
            liquid()
            color(0xC3AC65)
            components(Carbon, 3, Hydrogen, 7, Chlorine, 1)
        }

        // 8088 Acetic Anhydride
        AceticAnhydride = addMaterial(8088, "acetic_anhydride")
        {
            liquid()
            color(0xE2EBD9)
            components(Carbon, 4, Hydrogen, 6, Oxygen, 3)
        }

        // 8089 Dinitrodipropanyloxybenzene
        Dinitrodipropanyloxybenzene = addMaterial(8089, "dinitrodipropanyloxybenzene")
        {
            liquid()
            color(0x9FAD1D)
            components(Carbon, 12, Hydrogen, 16, Oxygen, 6, Nitrogen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8090 Dibromomethylbenzene
        Dibromomethylbenzene = addMaterial(8090, "dibromomethylbenzene")
        {
            liquid()
            color(0x9F4839)
            components(Carbon, 7, Hydrogen, 6, Bromine, 2)
        }

        // 8091 Terephthalaldehyde
        Terephthalaldehyde = addMaterial(8091, "terephthalaldehyde")
        {
            dust()
            color(0x567C2D).iconSet(ROUGH)
            components(Carbon, 8, Hydrogen, 6, Oxygen, 2)
        }

        // 8092 Pretreated Zylon
        PretreatedZylon = addMaterial(8092, "pretreated_zylon")
        {
            dust()
            color(0x623250).iconSet(DULL)
            components(Carbon, 20, Hydrogen, 22, Nitrogen, 2, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8093 Zylon
        Zylon = addMaterial(8093, "zylon")
        {
            polymer()
            liquid()
            color(0xFFE000).iconSet(SHINY)
            components(Carbon, 14, Hydrogen, 6, Nitrogen, 2, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
        }

        // 8094 Diborane
        Diborane = addMaterial(8094, "diborane")
        {
            gas()
            color(0x3F3131)
            components(Boron, 2, Hydrogen, 6)
        }

        // 8095 Borazine
        Borazine = addMaterial(8095, "borazine")
        {
            liquid()
            color(0x542828)
            components(Boron, 3, Hydrogen, 6, Nitrogen, 3)
        }

        // 8096 Trichloroborazine
        Trichloroborazine = addMaterial(8096, "trichloroborazine")
        {
            liquid()
            color(0xD62929)
            components(Boron, 3, Chlorine, 3, Hydrogen, 3, Nitrogen, 3)
        }

        // 8097 γ-Butyrolactone
        GammaButyrolactone = addMaterial(8097, "gamma_butyrolactone")
        {
            liquid()
            color(0xAF04D6)
            components(Carbon, 4, Hydrogen, 6, Oxygen, 2)
        }

        // 8098 N-Methyl-2-Pyrrolidone
        NMethylPyrrolidone = addMaterial(8098, "n_methyl_pyrrolidone")
        {
            liquid()
            color(0xA504D6)
            components(Carbon, 5, Hydrogen, 9, Nitrogen, 1, Oxygen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8099 Acetylene
        Acetylene = addMaterial(8099, "acetylene")
        {
            liquid()
            color(0x959C60)
            components(Carbon, 2, Hydrogen, 2)
        }

        // 8100 Tetrabromoethane
        Tetrabromoethane = addMaterial(8100, "tetrabromoethane")
        {
            liquid()
            color(0x5AAADA)
            components(Carbon, 2, Hydrogen, 2, Bromine, 4)
        }

        // 8101 Terephthalic Acid
        TerephthalicAcid = addMaterial(8101, "terephthalic_acid")
        {
            dust()
            color(0x5ACCDA).iconSet(ROUGH)
            components(Carbon, 8, Hydrogen, 6, Oxygen, 4)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8102 Bistrichloromethylbenzene
        Bistrichloromethylbenzene = addMaterial(8102, "bistrichloromethylbenzene")
        {
            liquid()
            color(0xCF8498)
            components(Carbon, 8, Hydrogen, 4, Chlorine, 6)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8103 Terephthaloyl Chloride
        TerephthaloylChloride = addMaterial(8103, "terephthaloyl_chloride")
        {
            dust()
            color(0xFAC4DA).iconSet(SHINY)
            components(Carbon, 8, Hydrogen, 4, Oxygen, 2, Chlorine, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8104 Kevlar
        Kevlar = addMaterial(8104, "kevlar")
        {
            polymer()
            liquid()
            color(0xF0F078)
            components(Carbon, 14, Hydrogen, 10, Nitrogen, 2, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
            fluidPipeProp(2000, 700, true)
        }

        // 8105 Para Toluic Acid
        ParaToluicAcid = addMaterial(8105, "para_toluic_acid")
        {
            liquid()
            {
                attribute(FluidAttributes.ACID)
            }
            color(0x4FA597)
            components(Carbon, 8, Hydrogen, 8, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8106 Methylparatoluate
        Methylparatoluate = addMaterial(8106, "methylparatoluate")
        {
            liquid()
            color(0x76BCB0)
            components(Carbon, 9, Hydrogen, 10, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8107 Dimethyl Terephthalate
        DimethylTerephthalate = addMaterial(8107, "dimethyl_terephthalate")
        {
            liquid()
            color(0x05D8AF)
            components(Carbon, 10, Hydrogen, 10, Oxygen, 4)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8108 Polyethylene Terephthalate (PET)
        PolyethyleneTerephthalate = addMaterial(8108, "polyethylene_terephthalate")
        {
            polymer()
            liquid()
            color(0x1E5C58)
            components(Carbon, 10, Hydrogen, 6, Oxygen, 4)
            flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
        }

        // 8109 Trimethylaluminium
        Trimethylaluminium = addMaterial(8109, "trimethylaluminium")
        {
            liquid()
            color(0x6ECCFF)
            components(Aluminium, 2, Carbon, 6, Hydrogen, 18)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8110 Trimethylgallium
        Trimethylgallium = addMaterial(8110, "trimethylgallium")
        {
            liquid()
            color(0x4F92FF)
            components(Gallium, 1, Carbon, 3, Hydrogen, 9)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8111 Ammonium Cyanate
        AmmoniumCyanate = addMaterial(8111, "ammonium_cyanate")
        {
            liquid()
            color(0x3a5dcf)
            components(Hydrogen, 4, Nitrogen, 2, Carbon, 1, Oxygen, 1)
        }

        // 8112 Carbamide
        Carbamide = addMaterial(8112, "carbamide")
        {
            dust()
            color(0x16EF57).iconSet(ROUGH)
            components(Carbon, 1, Hydrogen, 4, Nitrogen, 2, Oxygen, 1)
        }

        // 8113 Butanol
        Butanol = addMaterial(8113, "butanol")
        {
            liquid()
            color(0xC7AF2E)
            components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
        }

        // 8114 Tributylamine
        Tributylamine = addMaterial(8114, "tributylamine")
        {
            liquid()
            color(0x801A80)
            components(Carbon, 12, Hydrogen, 27, Nitrogen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8115 Dichloromethane
        Dichloromethane = addMaterial(8115, "dichloromethane")
        {
            liquid()
            color(0xB87FC7)
            components(Carbon, 1, Hydrogen, 2, Chlorine, 2)
        }

        // 8116 Diethyl Ether
        DiethylEther = addMaterial(8116, "diethyl_ether")
        {
            liquid()
            color(0xFFA4A3)
            components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8117 2-Aminooxyacetic Acid
        AminooxyaceticAcid = addMaterial(8117, "aminooxyacetic_acid")
        {
            liquid()
            color(0xECFF1E)
            components(Carbon, 2, Hydrogen, 5, Nitrogen, 1, Oxygen, 3)
        }

        // 8118 BenzylBromide
        BenzylBromide = addMaterial(8118, "benzyl_bromide")
        {
            gas()
            color(0xCF9D8C)
            components(Carbon, 7, Hydrogen, 7, Bromine, 1)
        }

        // 8119 Benzyltrimethylammonium Bromide
        BenzyltrimethylammoniumBromide = addMaterial(8119, "benzyltrimethylammonium_bromide")
        {
            dust()
            colorAverage().iconSet(SHINY)
            components(BenzylBromide, 1, Trimethylamine, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8120 2-Chlorobutane
        Chlorobutane = addMaterial(8120, "chlorobutane")
        {
            liquid()
            color(0xE6772D)
            components(Carbon, 4, Hydrogen, 9, Chlorine, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8121 Tertbutyl Alcohol
        TertbutylAlcohol = addMaterial(8121, "tertbutyl_alcohol")
        {
            liquid()
            color(0xBC8F44)
            components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8122 Indene
        Indene = addMaterial(8122, "indene")
        {
            liquid()
            color(0x171429)
            components(Carbon, 9, Hydrogen, 8)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8123 Indanone
        Indanone = addMaterial(8123, "indanone")
        {
            dust()
            color(0x2E1616).iconSet(SHINY)
            components(Carbon, 9, Hydrogen, 8, Oxygen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8124 Truxene
        Truxene = addMaterial(8124, "truxene")
        {
            liquid()
            color(0x1A3336)
            components(Carbon, 27, Hydrogen, 18)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8125 Bromomethane
        Bromomethane = addMaterial(8125, "bromomethane")
        {
            gas()
            color(0xC82C31)
            components(Carbon, 1, Hydrogen, 3, Bromine, 1)
        }

        // 8126 1-Bromo-2-(Bromomethyl) Naphthalene
        BromoBromomethylNaphthalene = addMaterial(8126, "bromo_bromomethyl_naphthalene")
        {
            liquid()
            color(0x52122E)
            components(Carbon, 11, Hydrogen, 8, Bromine, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8127 1-Bromobutane
        Bromobutane = addMaterial(8127, "bromobutane")
        {
            gas()
            color(0xE6E8A2)
            components(Butene, 1, HydrobromicAcid, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8128 Butyllithium
        Butyllithium = addMaterial(8128, "butyllithium")
        {
            liquid()
            color(0xE683B6B)
            components(Butene, 1, Hydrogen, 1, Lithium, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8129 Acetyl Chloride
        AcetylChloride = addMaterial(8129, "acetyl_chloride")
        {
            liquid()
            color(0xC3C3C3)
            components(Carbon, 2, Hydrogen, 3, Oxygen, 1, Chlorine, 1)
        }

        // 8130 Dimethylacetamide
        Dimethylacetamide = addMaterial(8130, "dimethylacetamide")
        {
            liquid()
            color(0x18AEA5)
            components(Carbon, 4, Hydrogen, 9, Nitrogen, 1, Oxygen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8131 GeodesicPolyarene
        GeodesicPolyarene = addMaterial(8131, "geodesic_polyarene")
        {
            dust()
            color(0x9E81A8).iconSet(METALLIC)
            components(Carbon, 60, Hydrogen, 30)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8132 Fullerene
        Fullerene = addMaterial(8132, "fullerene")
        {
            polymer()
            liquid()
            color(0x72556A).iconSet(BRIGHT)
            components(Carbon, 60)
            flags(STD_METAL, DISABLE_DECOMPOSITION, NO_SMELTING, GENERATE_FOIL, GENERATE_FINE_WIRE)
        }

        // 8133 Glucose
        Glucose = addMaterial(8133, "glucose")
        {
            dust()
            color(Sugar.materialRGB + 5).iconSet(ROUGH)
            components(Carbon, 6, Hydrogen, 12, Oxygen, 6)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8134 Fructose
        Fructose = addMaterial(8134, "fructose")
        {
            dust()
            color(Sugar.materialRGB + 10).iconSet(ROUGH)
            components(Carbon, 6, Hydrogen, 12, Oxygen, 6)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8135 Oxalic Acid
        OxalicAcid = addMaterial(8135, "oxalic_acid")
        {
            liquid()
            color(0x4AAAE2)
            components(Carbon, 2, Hydrogen, 2, Oxygen, 4)
        }

        // 8136 Dibromoacrolein
        Dibromoacrolein = addMaterial(8136, "dibromoacrolein")
        {
            liquid()
            color(0x7C4660)
            components(Carbon, 2, Hydrogen, 2, Bromine, 2, Oxygen, 2)
        }

        // 8137 Bromodihydrothiine
        Bromodihydrothiine = addMaterial(8137, "bromodihydrothiine")
        {
            liquid()
            color(0x66F36E)
            components(Carbon, 4, Hydrogen, 4, Sulfur, 2, Bromine, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8138 Bis-(Ethylenedithio)-Tetraselenafulvalene (BETS)
        BETS = addMaterial(8138, "bisethylenedithiotetraselenafulvalene")
        {
            dust()
            color(0x98E993).iconSet(ROUGH)
            flags(DISABLE_DECOMPOSITION)
            components(Carbon, 10, Hydrogen, 8, Sulfur, 4, Selenium, 4)
        }

        // 8139 Benzaldehyde
        Benzaldehyde = addMaterial(8139, "benzaldehyde")
        {
            liquid()
            color(0x957D53)
            components(Carbon, 7, Hydrogen, 6, Oxygen, 1)
        }

        // 8140 SuccinicAnhydride
        SuccinicAnhydride = addMaterial(8140, "succinic_anhydride")
        {
            dust()
            color(0xA2569D)
            components(Carbon, 4, Hydrogen, 4, Oxygen, 3)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8141 N-Hydroxysuccinimide
        NHydroxysuccinimide = addMaterial(8141, "n_hydroxysuccinimide")
        {
            dust()
            color(0x33BAFB).iconSet(METALLIC)
            components(Carbon, 4, Hydrogen, 5, Nitrogen, 1, Oxygen, 3)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8142 Succinimidyl Acetate
        SuccinimidylAcetate = addMaterial(8142, "succinimidyl_acetate")
        {
            dust()
            color(0x1D3605).iconSet(ROUGH)
            components(Carbon, 6, Hydrogen, 7, Nitrogen, 1, Oxygen, 4)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8143 Dimethylamine Hydrochloride
        DimethylamineHydrochloride = addMaterial(8143, "dimethylamine_hydrochloride")
        {
            liquid()
            color(0xE3EBDC)
            components(Dimethylamine, 1, HydrochloricAcid, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8144 Dimethylformamide (DMF)
        Dimethylformamide = addMaterial(8144, "dimethylformamide")
        {
            liquid()
            color(0x42BDFF)
            components(Carbon, 3, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8145 Succinimide
        Succinimide = addMaterial(8145, "succinimide")
        {
            dust()
            color(0x1774B6).iconSet(ROUGH)
            components(Carbon, 4, Hydrogen, 5, Nitrogen, 1, Oxygen, 2)
        }

        // 8146 Acetonitrile
        Acetonitrile = addMaterial(8146, "acetonitrile")
        {
            liquid()
            color(0x7D82A3)
            components(Carbon, 2, Hydrogen, 3, Nitrogen, 1)
        }

        // 8147 Acetamide
        Acetamide = addMaterial(8147, "acetamide")
        {
            dust()
            color(0x7D82A3)
            components(Carbon, 2, Hydrogen, 5, Nitrogen, 1, Oxygen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8148 Acetaldehyde
        Acetaldehyde = addMaterial(8148, "acetaldehyde")
        {
            liquid()
            color(0xF3F2F1)
            components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
        }

        // 8149 Glyoxal
        Glyoxal = addMaterial(8149, "glyoxal")
        {
            liquid()
            color(0xC9C7AB)
            components(Carbon, 2, Hydrogen, 2, Oxygen, 2)
        }

        // 8150 Benzylamine
        Benzylamine = addMaterial(8150, "benzylamine")
        {
            liquid()
            color(0x527A92)
            components(Carbon, 7, Hydrogen, 9, Nitrogen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8151 Benzyl Chloride
        BenzylChloride = addMaterial(8151, "benzyl_chloride")
        {
            gas()
            color(0x6699CC)
            components(Carbon, 7, Hydrogen, 7, Chlorine, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8152 Hexabenzylhexaazaisowurtzitane (HBHIW)
        Hexabenzylhexaazaisowurtzitane = addMaterial(8152, "hexabenzylhexaazaisowurtzitane")
        {
            dust()
            color(0x48561E)
            components(Carbon, 48, Hydrogen, 48 ,Nitrogen, 6)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8153 Dibenzyltetraacetylhexaazaisowurtzitane (DBTAHIW)
        Dibenzyltetraacetylhexaazaisowurtzitane = addMaterial(8153, "dibenzyltetraacetylhexaazaisowurtzitane")
        {
            dust()
            color(0xB7E8EE)
            components(Carbon, 28, Hydrogen, 32, Nitrogen, 6, Oxygen, 4)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8154 Tetraacetyldinitrosohexaazaisowurtzitane (TADNHIW)
        Tetraacetyldinitrosohexaazaisowurtzitane = addMaterial(8154, "tetraacetyldinitrosohexaazaisowurtzitane")
        {
            dust()
            color(0xEA7584).iconSet(ROUGH)
            components(Carbon, 14, Hydrogen, 18, Nitrogen, 8, Oxygen, 6)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8155 Crude Hexanitrohexaaxaisowurtzitane
        CrudeHexanitrohexaaxaisowurtzitane = addMaterial(8155, "crude_hexanitrohexaaxaisowurtzitane")
        {
            dust()
            color(0x5799EC)
            components(Carbon, 6, Hydrogen, 6, Nitrogen, 12, Oxygen, 12)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8156 Hexanitrohexaaxaisowurtzitane (HNIW)
        Hexanitrohexaaxaisowurtzitane = addMaterial(8156, "hexanitrohexaaxaisowurtzitane")
        {
            dust()
            color(0x0B7222).iconSet(BRIGHT)
            components(Carbon, 6, Hydrogen, 6, Nitrogen, 12, Oxygen, 12)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8157 Hexamethylenetetramine
        Hexamethylenetetramine = addMaterial(8157, "hexamethylenetetramine")
        {
            dust()
            color(0x53576D)
            components(Carbon, 6, Hydrogen, 12, Nitrogen, 4)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8158 Cyclotetramethylene Tetranitroamine (HMX)
        CyclotetramethyleneTetranitroamine = addMaterial(8158, "cyclotetramethylene_tetranitroamine")
        {
            liquid()
            color(0xD0A57B).iconSet(SHINY)
            components(Carbon, 4, Hydrogen, 8, Nitrogen, 8, Oxygen, 8)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8159 Octaazacubane
        Octaazacubane = addMaterial(8159, "octaazacubane")
        {
            dust()
            color(Nitrogen.materialRGB).iconSet(SHINY)
            components(Nitrogen, 8)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8160 Dibenzylideneacetone
        Dibenzylideneacetone = addMaterial(8160, "dibenzylideneacetone")
        {
            liquid()
            color(0xCC6699)
            components(Carbon, 17, Hydrogen, 14, Oxygen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8161 Pyridine
        Pyridine = addMaterial(8161, "pyridine")
        {
            liquid()
            colorAverage(Ammonia, Formaldehyde)
            components(Carbon, 5, Nitrogen, 5, Nitrogen, 1)
        }

        // 8162 Bipyridine
        Bipyridine = addMaterial(8162, "bipyridine")
        {
            dust()
            color(0x978662)
            components(Carbon, 10, Hydrogen, 8, Nitrogen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8163 Cyclooctadiene
        Cyclooctadiene = addMaterial(8163, "cyclooctadiene")
        {
            liquid()
            color(0x40AC40)
            components(Carbon, 8, Hydrogen, 12)
        }

        // 8164 Dichlorocyclooctadieneplatinium
        Dichlorocyclooctadieneplatinium = addMaterial(8164, "dichlorocyclooctadieneplatinium")
        {
            dust()
            color(0xD4E982).iconSet(BRIGHT)
            components(Carbon, 8, Hydrogen, 12, Chlorine, 2, Platinum, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8165 Diiodobiphenyl
        Diiodobiphenyl = addMaterial(8165, "diiodobiphenyl")
        {
            dust()
            color(0x000C52).iconSet(METALLIC)
            components(Carbon, 12, Hydrogen, 8, Iodine, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8166 Trimethyltin Chloride
        TrimethyltinChloride = addMaterial(8166, "trimethyltin_chloride")
        {
            liquid()
            color(0x7F776F)
            components(Carbon, 3, Hydrogen, 6, Tin, 1, Chlorine, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8167 Cycloparaphenylene (CPP)
        Cycloparaphenylene = addMaterial(8167, "cycloparaphenylene")
        {
            liquid()
            color(0x60545A)
            components(Carbon, 6, Hydrogen, 4)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8168 Octene
        Octene = addMaterial(8168, "octene")
        {
            liquid()
            color(0x7E8778)
            components(Carbon, 8, Hydrogen, 16)
        }

        // 8169 Carbon Nanotube (CNT)
        CarbonNanotube = addMaterial(8169, "carbon_nanotube")
        {
            ingot()
            liquid()
            color(0x05090C).iconSet(BRIGHT)
            components(Carbon, 48)
            flags(EXT_METAL, DISABLE_DECOMPOSITION, NO_SMELTING, GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_LONG_ROD,
                  GENERATE_SPRING, GENERATE_SPRING_SMALL)
            cableProp(V[UIV], 8, 6)
        }

        // 8170 Sarcosine
        Sarcosine = addMaterial(8170, "sarcosine")
        {
            dust()
            color(0x339933).iconSet(SHINY)
            components(Carbon, 3, Hydrogen, 7, Nitrogen, 1, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8171 Ferrocene
        Ferrocene = addMaterial(8171, "ferrocene")
        {
            liquid()
            color(0x6569A6)
            components(Carbon, 10, Hydrogen, 10, Iron, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8172 Ferrocenylfulleropyrddolidine
        Ferrocenylfulleropyrddolidine = addMaterial(8172, "ferrocenylfulleropyrddolidine")
        {
            liquid()
            color(0x1D894A)
            components(Carbon, 74, Hydrogen, 19, Nitrogen, 1, Iron, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8173 Dimethylaminopyridine
        Dimethylaminopyridine = addMaterial(8173, "dimethylaminopyridine")
        {
            dust()
            color(0x679887).iconSet(ROUGH)
            components(Carbon, 7, Hydrogen, 10, Nitrogen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8174 Triphenylphosphine (TPP)
        Triphenylphosphine = addMaterial(8174, "triphenylphosphine")
        {
            dust()
            color(0xE8DE3A).iconSet(ROUGH)
            components(Carbon, 18, Hydrogen, 15, Phosphorus, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8175 Isopropyl Alcohol
        IsopropylAlcohol = addMaterial(8175, "isopropyl_alcohol")
        {
            liquid()
            color(0x1EAC4C)
            components(Carbon, 3, Hydrogen, 8, Oxygen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8176 Diisopropylcarbodiimide
        Diisopropylcarbodiimide = addMaterial(8176, "diisopropylcarbodiimide")
        {
            liquid()
            color(0xA0CFFE)
            components(Carbon, 7, Hydrogen, 14, Nitrogen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8177 Phenylpentanoic Acid
        PhenylpentanoicAcid = addMaterial(8177, "phenylpentanoic_acid")
        {
            liquid()
            {
                attributes(FluidAttributes.ACID)
            }
            color(0x1B5154)
            components(Carbon, 11, Hydrogen, 14, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8178 Dimethyl Sulfide
        DimethylSulfide = addMaterial(8178, "dimethyl_sulfide")
        {
            liquid()
            color(0x781111)
            components(Carbon, 2, Hydrogen, 6, Sulfur, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8179 Phenyl-C61-Butyric Acid (PCBA)
        PhenylC61ButyricAcid = addMaterial(8179, "phenyl_c_61_butyric_acid")
        {
            liquid()
            color(0xCAC1F4)
            components(Carbon, 72, Hydrogen, 14, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8180 Phenyl-C61-Butyric Styrene (PCBS)
        PhenylC61ButyricStyrene = addMaterial(8180, "phenyl_c_61_butyric_styrene")
        {
            liquid()
            color(0x11B557)
            components(Carbon, 80, Hydrogen, 21, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8181 Fullerene Polymer Matrix (FPM)
        FullerenePolymerMatrix = addMaterial(8181, "fullerene_polymer_matrix")
        {
            polymer()
            liquid()
            {
                temperature(500)
            }
            color(0x2F0B01).iconSet(SHINY)
            components(Carbon, 153, Hydrogen, 36, Nitrogen, 1, Oxygen, 2, Lead, 1, Iron, 1)
            flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
        }

        // 8182 Phosphorene
        Phosphorene = addMaterial(8182, "phosphorene")
        {
            polymer()
            liquid()
            color(0x273239).iconSet(SHINY)
            components(Phosphorus, 4)
            flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_FOIL)
        }

        // 8183 Methyltrichlorosilane
        Methyltrichlorosilane = addMaterial(8183, "methyltrichlorosilane")
        {
            liquid()
            color(0x4D7CB4)
            components(Carbon, 1, Hydrogen, 3, Chlorine, 3, Silicon, 1)
        }

        // 8184 Diethyl Sulfide
        DiethylSulfide = addMaterial(8184, "diethyl_sulfide")
        {
            liquid()
            color(0xFF7E4B)
            components(Ethylene, 2, Sulfur, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8185 Guaiacol
        Guaiacol = addMaterial(8185, "guaiacol")
        {
            liquid()
            color(0xA63A00)
            components(Carbon, 7, Hydrogen, 8, Oxygen, 2)
        }

        // 8186 Xylenol
        Xylenol = addMaterial(8186, "xylenol")
        {
            liquid()
            color(0xCF7D10)
            components(Carbon, 8, Hydrogen, 10, Oxygen, 1)
        }

        // 8187 Creosol
        Creosol = addMaterial(8187, "creosol")
        {
            liquid()
            color(0x704E46)
            components(Carbon, 7, Hydrogen, 8, Oxygen, 1)
        }

        // 8188 Methoxycreosol
        Methoxycreosol = addMaterial(8188, "methoxycreosol")
        {
            liquid()
            color(0xAF4617)
            components(Carbon, 8, Hydrogen, 10, Oxygen, 2)
        }

        // 8189 Phenothiazine
        Phenothiazine = addMaterial(8189, "phenothiazine")
        {
            dust()
            color(0x67735C).iconSet(FINE)
            components(Carbon, 12, Hydrogen, 9, Nitrogen, 1, Sulfur, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8190 Isopropyl Chloride
        IsopropylChloride = addMaterial(8190, "isopropyl_chloride")
        {
            liquid()
            color(0x17B868)
            components(Carbon, 3, Hydrogen, 7, Chlorine, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8191 10-Phenothiazine-2-Propyl Chloride
        PhenothiazinePropylChloride = addMaterial(8191, "phenothiazine_propyl_chloride")
        {
            liquid()
            color(0x444E1D)
            components(Carbon, 15, Hydrogen, 14, Nitrogen, 1, Sulfur, 1, Chlorine, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8192 Promethazine
        Promethazine = addMaterial(8192, "promethazine")
        {
            dust()
            color(0x92E829).iconSet(SHINY)
            components(Carbon, 17, Hydrogen, 20, Nitrogen, 2, Sulfur, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8193 Codeine
        Codeine = addMaterial(8193, "codeine")
        {
            dust()
            color(0xFADEF2)
            components(Carbon, 18, Hydrogen, 21, Nitrogen, 1, Oxygen, 3)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8194 Ammonium Fluoride
        AmmoniumFluoride = addMaterial(8194, "ammonium_fluoride")
        {
            dust()
            colorAverage(Ammonia, Fluorine)
            components(Nitrogen, 1, Hydrogen, 4, Fluorine, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8195 Ammonium Bifluoride
        AmmoniumBifluoride = addMaterial(8195, "ammonium_bifluoride")
        {
            dust()
            color(0x055370).iconSet(FINE)
            components(Nitrogen, 1, Hydrogen, 5, Fluorine, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8196 N-Difluorophenylpyrrole
        NDifluorophenylpyrrole = addMaterial(8196, "n_difluorophenylpyrrole")
        {
            liquid()
            color(0x6D837E)
            components(Carbon, 10, Hydrogen, 7, Fluorine, 2, Nitrogen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8197 Tetraethylammonium Bromide
        TetraethylammoniumBromide = addMaterial(8197, "tetraethylammonium_bromide")
        {
            liquid()
            color(0xCC3399)
            components(Carbon, 8, Hydrogen, 20, Nitrogen, 1, Bromine, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8198 Phenylsodium
        Phenylsodium = addMaterial(8198, "phenylsodium")
        {
            liquid()
            colorAverage()
            components(Benzene, 1, Sodium, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8199 Indigo
        Indigo = addMaterial(8199, "indigo")
        {
            dust()
            color(0x0000FF).iconSet(DULL)
            components(Carbon, 16, Hydrogen, 10, Nitrogen, 2, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8200 Tetrabromoindigo
        Tetrabromoindigo = addMaterial(8200, "tetrabromoindigo")
        {
            dust()
            colorAverage().iconSet(DULL)
            components(Indigo, 1, Bromine, 4)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8201 Cyan Indigo
        CyanIndigo = addMaterial(8201, "cyan_indigo")
        {
            dust()
            color(0x1661AB).iconSet(DULL)
            components(Indigo, 1, Tetrabromoindigo, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8202 Nigrosin
        Nigrosin = addMaterial(8202, "nigrosin")
        {
            dust()
            color(0x000000).iconSet(DULL)
            components(Carbon, 36, Hydrogen, 26, Nitrogen, 5, Chlorine, 1, Sodium, 2, Sulfur, 2, Oxygen, 6)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8203 Sodium Sulfanilate
        SodiumSulfanilate = addMaterial(8203, "sodium_sulfanilate")
        {
            dust()
            color(0xE49879).iconSet(SHINY)
            components(Carbon, 6, Hydrogen, 6, Nitrogen, 1, Sodium, 1, Oxygen, 3, Sulfur, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8204 Naphthylamine
        Naphthylamine = addMaterial(8204, "naphthylamine")
        {
            liquid()
            color(0xE3E81C)
            components(Carbon, 10, Hydrogen, 9, Nitrogen, 1)
        }

        // 8205 Direct Brown 77
        DirectBrown77 = addMaterial(8205, "direct_brown_77")
        {
            dust()
            color(0x663300).iconSet(DULL)
            components(Carbon, 26, Hydrogen, 19, Nitrogen, 6, Sodium, 1, Oxygen, 3, Sulfur, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8206 Tetracene
        Tetracene = addMaterial(8206, "tetracene")
        {
            dust()
            color(0x99801A).iconSet(SHINY)
            components(Carbon, 18, Hydrogen, 12)
        }

        // 8207 Rhodamine B
        RhodamineB = addMaterial(8207, "rhodamine_b")
        {
            dust()
            color(0xFC2020).iconSet(SHINY)
            components(Carbon, 28, Hydrogen, 31, Chlorine, 1, Nitrogen, 2, Oxygen, 3)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8208 Cetane Trimethyl Ammonium Bromide
        CetaneTrimethylAmmoniumBromide = addMaterial(8208, "cetane_trimethyl_ammonium_bromide")
        {
            liquid()
            color(0x949494)
            components(Carbon, 19, Hydrogen, 42, Bromine, 1, Nitrogen, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8209 Sorbose
        Sorbose = addMaterial(8209, "sorbose")
        {
            dust()
            color(0xFFFFFF).iconSet(METALLIC)
            components(Carbon, 6, Hydrogen, 12, Oxygen, 6)
        }

        // 8210 Ascorbic Acid
        AscorbicAcid = addMaterial(8210, "ascorbic_acid")
        {
            liquid()
            color(0xE6CD00)
            components(Carbon, 6, Hydrogen, 8, Oxygen, 6)
        }

        // 8211 Dehydroascorbic Acid
        DehydroascorbicAcid = addMaterial(8211, "dehydroascorbic_acid")
        {
            liquid()
            color(AscorbicAcid.materialRGB - 10)
            components(Carbon, 6, Hydrogen, 6, Oxygen, 6)
        }

        // 8212 Cellulose
        Cellulose = addMaterial(8212, "cellulose")
        {
            dust()
            color(0xE2EBD9).iconSet(ROUGH)
            components(Carbon, 6, Hydrogen, 10, Oxygen, 5)
        }

        // 8213 Xylose
        Xylose = addMaterial(8213, "xylose")
        {
            dust()
            color(0xDFDFDF).iconSet(ROUGH)
            components(Carbon, 5, Hydrogen, 10, Oxygen, 5)
        }

        // 8214 Saccharic Acid
        SacchariaAcid = addMaterial(8214, "saccharic_acid")
        {
            dust()
            color(Sugar.materialRGB + 5).iconSet(METALLIC)
            components(Carbon, 6, Hydrogen, 10, Oxygen, 8)
        }

        // 8215 Adipic Acid
        AdipicAcid = addMaterial(8215, "adipic_acid")
        {
            dust()
            color(0xDA9288).iconSet(ROUGH)
            components(Carbon, 6, Hydrogen, 10, Oxygen, 4)
        }

        // 8216 Hexanediol
        Hexanediol = addMaterial(8216, "hexanediol")
        {
            liquid()
            color(0x98D1EF)
            components(Carbon, 6, Hydrogen, 14, Oxygen, 2)
        }

        // 8217 Hexamethylenediamine
        Hexamethylenediamine = addMaterial(8217, "hexamethylenediamine")
        {
            liquid()
            color(0x45B387)
            components(Carbon, 6, Hydrogen, 16, Nitrogen, 2)
        }

        // 8218 Tertbutanol
        Tertbutanol = addMaterial(8218, "tertbutanol")
        {
            liquid()
            color(0xCCCC2C)
            components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
        }

        // 8219 Ditertbutyl Dicarbonate
        DitertbutylDicarbonate = addMaterial(8219, "ditertbutyl_dicarbonate")
        {
            dust()
            color(0xCCCCF6).iconSet(ROUGH)
            components(Carbon, 10, Hydrogen, 18, Oxygen, 5)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8220 Tris(2-aminoethyl)amine
        Trisaminoethylamine = addMaterial(8220, "trisaminoethylamine")
        {
            liquid()
            color(0x6F7D87)
            components(Nitrogen, 4, Hydrogen, 18, Carbon, 6)
        }

        // 8221 Tertbutyl Azidoformate
        TertbutylAzidoformate = addMaterial(8221, "tertbutyl_azidoformate")
        {
            liquid()
            color(0x888818)
            components(Carbon, 5, Hydrogen, 9, Nitrogen, 3, Oxygen, 2)
        }

        // 8222 Aminated Fullerene
        AminatedFullerene = addMaterial(8222, "aminated_fullerene")
        {
            liquid()
            color(0x2C2CAA)
            components(Carbon, 60, Nitrogen, 12, Hydrogen, 12)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8223 Azafullerene
        Azafullerene = addMaterial(8223, "azafullerene")
        {
            liquid()
            color(0x8A7A1A)
            components(Carbon, 60, Nitrogen, 12, Hydrogen, 12)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8224 Fulvic Acid
        FulvicAcid = addMaterial(8224, "fulvic_acid")
        {
            liquid()
            {
                temperature(312)
            }
            color(0x59492F)
            components(Carbon, 14, Hydrogen, 12, Oxygen, 8)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8225 Mauveine
        Mauveine = addMaterial(8225, "mauveine")
        {
            dust()
            color(0x660066).iconSet(DULL)
            components(Carbon, 26, Hydrogen, 23, Nitrogen, 4)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8226 Diketopyrrolopyrrole
        Diketopyrrolopyrrole = addMaterial(8226, "diketopyrrolopyrrole")
        {
            dust()
            color(0xFF6600).iconSet(DULL)
            components(Carbon, 18, Hydrogen, 12, Nitrogen, 2, Oxygen, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8227 Isopropyl Succinate
        IsopropylSuccinate = addMaterial(8227, "isopropyl_succinate")
        {
            liquid()
            color(0xB26680)
            components(Carbon, 7, Hydrogen, 12, Oxygen, 4)
        }

        // 8228 Eosin Y
        EosinY = addMaterial(8228, "eosin_y")
        {
            dust()
            color(0xEAB4EB).iconSet(SHINY)
            components(Carbon, 20, Hydrogen, 6, Bromine, 4, Sodium, 2, Oxygen, 5)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8229 Phenylenedioxydiacetic Acid
        PhenylenedioxydiaceticAcid = addMaterial(8229, "phenylenedioxydiacetic_acid")
        {
            liquid()
            color(0xFFBBBA)
            components(Carbon, 10, Hydrogen, 10, Oxygen, 6)
        }

        // 8230 Ethylamine
        Ethylamine = addMaterial(8230, "ethylamine")
        {
            liquid()
            color(0x9E9E9E)
            components(Carbon, 2, Hydrogen, 7, Nitrogen, 1)
        }

        // 8231 Diethylthiourea
        Diethylthiourea = addMaterial(8231, "diethylthiourea")
        {
            liquid()
            color(0x8D8EC2)
            components(Carbon, 5, Hydrogen, 12, Nitrogen, 2, Sulfur, 1)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8232 Isophthaloylbisdiethylthiourea
        Isophthaloylbisdiethylthiourea = addMaterial(8232, "isophthaloylbisdiethylthiourea")
        {
            liquid()
            color(0xA2D4E1)
            components(Carbon, 18, Hydrogen, 26, Nitrogen, 4, Oxygen, 2, Sulfur, 2)
            flags(DISABLE_DECOMPOSITION)
        }

        // 8233 Silane
        Silane = addMaterial(8233, "silane")
        {
            gas()
            color(0x6F6F8B)
            components(Silicon, 1, Hydrogen, 4)
        }

        // 8234 Ethylene-Vinyl Acetate Polymer
        EthyleneVinylAcetatePolymer = addMaterial(8234, "ethylene_vinyl_acetate_polymer")
        {
            liquid()
            colorAverage(Ethylene, VinylAcetate)
            components(Ethylene, 3, VinylAcetate, 2)
            flags(DISABLE_DECOMPOSITION)
        }

    }

    // @formatter:on

}