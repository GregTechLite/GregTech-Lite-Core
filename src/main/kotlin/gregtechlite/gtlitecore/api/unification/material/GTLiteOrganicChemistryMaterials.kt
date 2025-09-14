package gregtechlite.gtlitecore.api.unification.material

import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.ZPM
import gregtech.api.fluids.FluidBuilder
import gregtech.api.fluids.attribute.FluidAttributes
import gregtech.api.unification.material.Material
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
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.extension.colorAverage
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

object GTLiteOrganicChemistryMaterials
{

    // @formatter:off

    fun init()
    {
        // 8001 Dicyclopentadiene
        Dicyclopentadiene = Material.Builder(8001, GTLiteMod.id("dicyclopentadiene"))
            .liquid(FluidBuilder().temperature(306))
            .color(0x9C388B)
            .components(Carbon, 10, Hydrogen, 12)
            .build()

        // 8002 Pentane
        Pentane = Material.Builder(8002, GTLiteMod.id("pentane"))
            .liquid()
            .color(0xE8E7BE)
            .components(Carbon, 5, Hydrogen, 12)
            .build()

        // 8003 Polyisoprene
        Polyisoprene = Material.Builder(8003, GTLiteMod.id("polyisoprene"))
            .polymer()
            .liquid()
            .color(0x575757).iconSet(SHINY)
            .components(Carbon, 5, Hydrogen, 8)
            .flags(NO_SMASHING, NO_SMELTING, DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_FOIL)
            .build()

        // 8004 Para Xylene
        ParaXylene = Material.Builder(8004, GTLiteMod.id("para_xylene"))
            .liquid()
            .color(0x666040)
            .components(Carbon, 8, Hydrogen, 10)
            .build()
            .setFormula("C6H4(CH3)2", true)

        // 8005 Nitrotoluene
        Nitrotoluene = Material.Builder(8005, GTLiteMod.id("nitrotoluene"))
            .liquid()
            .color(0x99236E)
            .components(Carbon, 7, Hydrogen, 7, Nitrogen, 1, Oxygen, 2)
            .build()
            .setFormula("C6H4CH3NO2", true)

        // 8006 Diaminostilbenedisulfonic Acid (DSDA)
        DiaminostilbenedisulfonicAcid = Material.Builder(8006, GTLiteMod.id("diaminostilbenedisulfonic_acid"))
            .dust()
            .color(0xF2F2F2).iconSet(ROUGH)
            .components(Carbon, 14, Hydrogen, 14, Nitrogen, 2, Oxygen, 6, Sulfur, 2)
            .build()

        // 8007 Succinic Acid
        SuccinicAcid = Material.Builder(8007, GTLiteMod.id("succinic_acid"))
            .dust()
            .color(0x0C3A5B).iconSet(DULL)
            .components(Carbon, 4, Hydrogen, 6, Oxygen, 4)
            .build()

        // 8008 Butanediol
        Butanediol = Material.Builder(8008, GTLiteMod.id("butanediol"))
            .liquid()
            .color(0xAAC4DA)
            .components(Carbon, 4, Hydrogen, 10, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION) // Re-added electrolysis reaction by PedotChain.
            .build()
            .setFormula("C4H8(OH)2", true)

        // 8009 Tetrahydrofuran
        Tetrahydrofuran = Material.Builder(8009, GTLiteMod.id("tetrahydrofuran"))
            .liquid()
            .color(0x0BCF52)
            .components(Carbon, 4, Hydrogen, 8, Oxygen, 1)
            .build()
            .setFormula("(CH2)4O", true)

        // 8010 Ethylene Dibromide
        EthyleneDibromide = Material.Builder(8010, GTLiteMod.id("ethylene_dibromide"))
            .liquid()
            .color(0x4F1743)
            .components(Carbon, 2, Hydrogen, 4, Bromine, 2)
            .build()

        // 8011 Grignard Reagent
        GrignardReagent = Material.Builder(8011, GTLiteMod.id("grignard_reagent"))
            .liquid()
            .color(0xA12AA1)
            .components(Carbon, 1, Hydrogen, 3, HRAMagnesium, 1, Bromine, 1)
            .build()

        // 8012 Ethylhexanol
        Ethylhexanol = Material.Builder(8012, GTLiteMod.id("ethylhexanol"))
            .liquid()
            .color(0xFEEA9A)
            .components(Carbon, 8, Hydrogen, 10, Oxygen, 1)
            .build()

        // 8013 Di-(2-Ethylhexyl) Phosphoric Acid
        DiethylhexylPhosphoricAcid = Material.Builder(8013, GTLiteMod.id("diethylhexyl_phosphoric_acid"))
            .liquid()
            .color(0xFFFF99)
            .components(Carbon, 16, Hydrogen, 35, Oxygen, 4, Phosphorus, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(C8H7O)2PO2H", true)

        // 8014 Formic Acid
        FormicAcid = Material.Builder(8014, GTLiteMod.id("formic_acid"))
            .liquid(FluidBuilder().attribute(FluidAttributes.ACID))
            .color(0xFFAA77)
            .components(Hydrogen, 2, Carbon, 1, Oxygen, 2)
            .build()
            .setFormula("HCOOH", true)

        // 8015 Methyl Formate
        MethylFormate = Material.Builder(8015, GTLiteMod.id("methyl_formate"))
            .liquid()
            .color(0xFFAAAA)
            .components(Hydrogen, 4, Carbon, 2, Oxygen, 2)
            .build()
            .setFormula("HCO2CH3", true)

        // 8016 Thionyl Chloride
        ThionylChloride = Material.Builder(8016, GTLiteMod.id("thionyl_chloride"))
            .liquid()
            .color(0xEBE863)
            .components(Sulfur, 1, Oxygen, 1, Chlorine, 2)
            .build()

        // 8017 Phthalic Anhydride
        PhthalicAnhydride = Material.Builder(8017, GTLiteMod.id("phthalic_anhydride"))
            .dust()
            .color(0xEEAAEE)
            .components(Carbon, 8, Hydrogen, 4, Oxygen, 3)
            .build()
            .setFormula("C6H4(CO)2O", true)

        // 8018 Ethylanthraquinone
        Ethylanthraquinone = Material.Builder(8018, GTLiteMod.id("ethylanthraquinone"))
            .liquid()
            .color(0xCC865A)
            .components(Carbon, 16, Hydrogen, 12, Oxygen, 2)
            .build()
            .setFormula("C6H4(CO)2C6H3Et", true)

        // 8019 Ethylanthrahydroquinone
        Ethylanthrahydroquinone = Material.Builder(8019, GTLiteMod.id("ethylanthrahydroquinone"))
            .liquid()
            .color(0xAD531A)
            .components(Carbon, 16, Hydrogen, 18, Oxygen, 2)
            .build()
            .setFormula("C6H4(CH2OH)2C6H3Et", true)

        // 8020 Hydrazine
        Hydrazine = Material.Builder(8020, GTLiteMod.id("hydrazine"))
            .liquid()
            .color(0xB50707)
            .components(Nitrogen, 2, Hydrogen, 4)
            .build()

        // 8021 Citric Acid
        CitricAcid = Material.Builder(8021, GTLiteMod.id("citric_acid"))
            .liquid()
            .color(0xFFCC00)
            .components(Carbon, 6, Hydrogen, 8, Oxygen, 7)
            .build()

        // 8022 Glutamine
        Glutamine = Material.Builder(8022, GTLiteMod.id("glutamine"))
            .dust()
            .color(0xEDE9B4).iconSet(DULL)
            .components(Carbon, 5, Hydrogen, 10, Nitrogen, 2, Oxygen, 3)
            .build()

        // 8023 Linoleic Acid
        LinoleicAcid = Material.Builder(8023, GTLiteMod.id("linoleic_acid"))
            .liquid()
            .color(0xD5D257)
            .components(Carbon, 18, Hydrogen, 32, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8024 Biotin
        Biotin = Material.Builder(8024, GTLiteMod.id("biotin"))
            .dust()
            .color(0x68CC6A)
            .components(Carbon, 10, Hydrogen, 16, Nitrogen, 2, Oxygen, 3, Sulfur, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8025 Durene
        Durene = Material.Builder(8025, GTLiteMod.id("durene"))
            .dust()
            .color(0x336040).iconSet(FINE)
            .components(Carbon, 10, Hydrogen, 14)
            .build()
            .setFormula("C6H2(CH3)4", true)

        // 8026 Pyromellitic Dianhydride (PDMA)
        PyromelliticDianhydride = Material.Builder(8026, GTLiteMod.id("pyromellitic_dianhydride"))
            .dust()
            .color(0xF0EAD6).iconSet(ROUGH)
            .components(Carbon, 10, Hydrogen, 2, Oxygen, 6)
            .build()
            .setFormula("C6H2(C2O3)2", true)

        // 8027 Aminophenol
        Aminophenol = Material.Builder(8027, GTLiteMod.id("aminophenol"))
            .dust()
            .color(0xFFFFFF).iconSet(SHINY)
            .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
            .build()

        // 8028 Aniline
        Aniline = Material.Builder(8028, GTLiteMod.id("aniline"))
            .liquid()
            .color(0x4c911d)
            .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1)
            .build()
            .setFormula("C6H5NH2", true)

        // 8029 Oxydianiline (ODA)
        Oxydianiline = Material.Builder(8029, GTLiteMod.id("oxydianiline"))
            .dust()
            .color(0xF0E130)
            .components(Carbon, 12, Hydrogen, 12, Nitrogen, 2, Oxygen, 1)
            .build()
            .setFormula("O(C6H4NH2)2", true)

        // 8030 Kapton-K (Poly 4,4'-Oxydiphenylene-Pyromellitimide)
        KaptonK = Material.Builder(8030, GTLiteMod.id("kapton_k"))
            .polymer()
            .liquid()
            .color(0xFFCE52)
            .components(Carbon, 12, Hydrogen, 12, Nitrogen, 2, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
            .build()
            .setFormula("(C7H2N2O4)(O(C6H4)2)", true)

        // 8031 Biphenyl Tetracarboxylic Acid Dianhydride (BTAD)
        BiphenylTetracarboxylicAcidDianhydride = Material.Builder(8031, GTLiteMod.id("biphenyl_tetracarboxylic_acid_dianhydride"))
            .dust()
            .color(0xFF7F50)
            .components(Carbon, 16, Hydrogen, 6, Oxygen, 6)
            .build()
            .setFormula("(C8H3O3)2", true)

        // 8032 Nitroaniline
        Nitroaniline = Material.Builder(8032, GTLiteMod.id("nitroaniline"))
            .liquid()
            .color(0x2A6E68)
            .components(Carbon, 6, Hydrogen, 6, Nitrogen, 2, Oxygen, 2)
            .build()
            .setFormula("H2NC6H4NO2", true)

        // 8033 Para-Phenylenediamine
        ParaPhenylenediamine = Material.Builder(8033, GTLiteMod.id("para_phenylenediamine"))
            .dust()
            .color(0x4A8E7B).iconSet(ROUGH)
            .components(Carbon, 6, Hydrogen, 8, Nitrogen, 2)
            .build()
            .setFormula("H2NC6H4NH2", true)

        // 8034 Kapton-E
        KaptonE = Material.Builder(8034, GTLiteMod.id("kapton_e"))
            .polymer()
            .liquid()
            .color(0xFFDF8C)
            .components(Carbon, 12, Hydrogen, 12, Nitrogen, 2, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, NO_SMASHING, NO_SMELTING, GENERATE_FOIL)
            .build()
            .setFormula("O(C6H4NH2)2", true)

        // 8035 Hydroxyquinoline
        Hydroxyquinoline = Material.Builder(8035, GTLiteMod.id("hydroxyquinoline"))
            .dust()
            .color(0x3A9A71).iconSet(METALLIC)
            .components(Carbon, 9, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
            .build()

        // 8036 Hydrogen Cyanide
        HydrogenCyanide = Material.Builder(8036, GTLiteMod.id("hydrogen_cyanide"))
            .liquid()
            .color(0x6E6A5E)
            .components(Hydrogen, 1, Carbon, 1, Nitrogen, 1)
            .build()

        // 8037 Acetone Cyanohydrin
        AcetoneCyanohydrin = Material.Builder(8037, GTLiteMod.id("acetone_cyanohydrin"))
            .liquid()
            .color(0xA1FFD0)
            .components(Carbon, 4, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
            .build()

        // 8038 Polymethylmethacrylate (PMMA)
        Polymethylmethacrylate = Material.Builder(8038, GTLiteMod.id("polymethylmethacrylate"))
            .ingot()
            .liquid()
            .color(0x91CAE1)
            .components(Carbon, 5, Hydrogen, 8, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE,
                   GENERATE_LENS)
            .build()

        // 8039 Diacetyl
        Diacetyl = Material.Builder(8039,  GTLiteMod.id("diacetyl"))
            .liquid()
            .color(0xF7FF65)
            .components(Carbon, 4, Hydrogen, 6, Oxygen, 2)
            .build()

        // 8040 Ethylene Oxide
        EthyleneOxide = Material.Builder(8040, GTLiteMod.id("ethylene_oxide"))
            .gas()
            .color(0xDCBFE1)
            .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
            .build()

        // 8041 Ethylene Glycol
        EthyleneGlycol = Material.Builder(8041, GTLiteMod.id("ethylene_glycol"))
            .liquid()
            .color(0x286632)
            .components(Carbon, 2, Hydrogen, 6, Oxygen, 2)
            .build()
            .setFormula("C2H4(OH)2", true)

        // 8042 3,4-Ethylenedioxythiophene (EDOT)
        Edot = Material.Builder(8042, GTLiteMod.id("edot"))
            .liquid()
            .color(0xB1FFD7)
            .components(Carbon, 6, Hydrogen, 6, Oxygen, 2, Sulfur, 1)
            .build()

        // 8043 Polystyrene (PS)
        Polystyrene = Material.Builder(8043, GTLiteMod.id("polystyrene"))
            .polymer()
            .liquid()
            .color(0xE1C2C2)
            .components(Carbon, 8, Hydrogen, 8)
            .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
            .build()

        // 8044 Polystyrene Sulfonate (PSS)
        PolystyreneSulfonate = Material.Builder(8044, GTLiteMod.id("polystyrene_sulfonate"))
            .polymer()
            .liquid()
            .color(0xE17C72)
            .components(Carbon, 8, Hydrogen, 8, Sulfur, 1, Oxygen, 3)
            .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
            .build()
            .setFormula("C8H7SO3H", true)

        // 8045 PEDOT:PSS
        PedotPSS = Material.Builder(8045, GTLiteMod.id("pedot_pss"))
            .polymer()
            .liquid()
            .color(0xE165A7)
            .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
            .components(Edot, 1, PolystyreneSulfonate, 1)
            .cableProperties(V[ZPM], 6, 1)
            .build()

        // 8046 PEDOT-TMA
        PedotTMA = Material.Builder(8046, GTLiteMod.id("pedot_tma"))
            .polymer()
            .liquid()
            .color(0x5E9EE1)
            .components(Edot, 1, Polymethylmethacrylate, 2)
            .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
            .cableProperties(V[UV], 8, 4)
            .build()

        // 8047 Methylamine
        Methylamine = Material.Builder(8047, GTLiteMod.id("methylamine"))
            .gas()
            .color(0xAA6600)
            .components(Carbon, 1, Hydrogen, 5, Nitrogen, 1)
            .build()
            .setFormula("CH3NH2", true)

        // 8048 Trimethylamine
        Trimethylamine = Material.Builder(8048, GTLiteMod.id("trimethylamine"))
            .gas()
            .color(0xBB7700)
            .components(Carbon, 3, Hydrogen, 9, Nitrogen, 1)
            .build()
            .setFormula("(CH3)3N", true)

        // 8049 Tetramethylammonium Chloride
        TetramethylammoniumChloride = Material.Builder(8049, GTLiteMod.id("tetramethylammonium_chloride"))
            .dust()
            .color(0x27FF81).iconSet(METALLIC)
            .components(Carbon, 4, Hydrogen, 12, Nitrogen, 1, Chlorine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("N(CH3)4Cl", true)

        // 8050 Tetramethylammonium Hydroxide (TMAH)
        TetramethylammoniumHydroxide = Material.Builder(8050, GTLiteMod.id("tetramethylammonium_hydroxide"))
            .liquid()
            .color(0x40FFD6)
            .components(Nitrogen, 1, Carbon, 4, Hydrogen, 12, Oxygen, 1, Hydrogen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("N(CH3)4OH", true)

        // 8051 Pyrocatechol
        Pyrocatechol = Material.Builder(8051, GTLiteMod.id("pyrocatechol"))
            .dust()
            .color(0x784421).iconSet(DULL)
            .components(Carbon, 6, Hydrogen, 6, Oxygen, 2)
            .build()

        // 8052 1,2-Dichloroethane
        Dichloroethane = Material.Builder(8052, GTLiteMod.id("dichloroethane"))
            .liquid()
            .color(0xDAAED3)
            .components(Carbon, 2, Hydrogen, 4, Chlorine, 2)
            .build()

        // 8053 Ethylenediamine
        Ethylenediamine = Material.Builder(8053, GTLiteMod.id("ethylenediamine"))
            .liquid()
            .color(0xD00ED0)
            .components(Carbon, 2, Hydrogen, 8, Nitrogen, 2)
            .build()
            .setFormula("C2H4(NH2)2", true)

        // 8054 Ethylenediamine Pyrocatechol (EDP)
        EthylenediaminePyrocatechol = Material.Builder(8054, GTLiteMod.id("ethylenediamine_pyrocatechol"))
            .liquid()
            .color(0xFBFF17)
            .components(Ethylenediamine, 1, Pyrocatechol, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8055 Formaldehyde
        Formaldehyde = Material.Builder(8055, GTLiteMod.id("formaldehyde"))
            .liquid()
            .color(0x858F40)
            .components(Carbon, 1, Hydrogen, 2, Oxygen, 1)
            .build()

        // 8056 Tetrasodium EDTA
        TetrasodiumEDTA = Material.Builder(8056, GTLiteMod.id("tetrasodium_ethylenediaminetetraacetic_acid"))
            .dust()
            .color(0x8890E0).iconSet(SHINY)
            .components(Carbon, 10, Hydrogen, 12, Nitrogen, 2, Oxygen, 8, Sodium, 4)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8057 Ethylenediaminetetraacetic Acid (EDTA)
        EDTA = Material.Builder(8057, GTLiteMod.id("ethylenediaminetetraacetic_acid"))
            .dust()
            .liquid()
            .color(0x87E6D9).iconSet(ROUGH)
            .components(Carbon, 10, Hydrogen, 16, Nitrogen, 2, Oxygen, 8)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8058 Hexafluoropropylene
        Hexafluoropropylene = Material.Builder(8058, GTLiteMod.id("hexafluoropropylene"))
            .liquid()
            .color(0x141D6F)
            .components(Carbon, 3, Fluorine, 6)
            .build()

        // 8059 Fluorinated Ethylene Propylene (FEP)
        FluorinatedEthylenePropylene = Material.Builder(8059, GTLiteMod.id("fluorinated_ethylene_propylene"))
            .liquid()
            .color(0xC8C8C8).iconSet(DULL)
            .components(Carbon, 5, Fluorine, 10)
            .build()

        // 8060 Trichloroethylene
        Trichloroethylene = Material.Builder(8060, GTLiteMod.id("trichloroethylene"))
            .liquid()
            .color(0xB685B1)
            .components(Carbon, 2, Hydrogen, 1, Chlorine, 3)
            .build()

        // 8061 Chloroacetic Acid
        ChloroaceticAcid = Material.Builder(8061, GTLiteMod.id("chloroacetic_acid"))
            .dust()
            .color(0x38541A).iconSet(SHINY)
            .components(Carbon, 2, Hydrogen, 3, Chlorine, 1, Oxygen, 2)
            .build()

        // 8062 Malonic Acid
        MalonicAcid = Material.Builder(8062, GTLiteMod.id("malonic_acid"))
            .dust()
            .color(0x61932E).iconSet(SHINY)
            .components(Carbon, 3, Hydrogen, 4, Oxygen, 4)
            .build()

        // 8063 Phosphoryl Chloride
        PhosphorylChloride = Material.Builder(8063, GTLiteMod.id("phosphoryl_chloride"))
            .liquid()
            .color(0xE8BB5B)
            .components(Phosphorus, 1, Oxygen, 1, Chlorine, 3)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8064 Phosphonitrilic Chloride Trimer
        PhosphonitrilicChlorideTrimer = Material.Builder(8064, GTLiteMod.id("phosphonitrilic_chloride_trimer"))
            .liquid()
            .color(0x082C38)
            .components(Chlorine, 6, Nitrogen, 3, Phosphorus, 3)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8065 Fluorobenzene
        Fluorobenzene = Material.Builder(8065, GTLiteMod.id("fluorobenzene"))
            .liquid()
            .color(0x7CCA88)
            .components(Carbon, 6, Hydrogen, 5, Fluorine, 1)
            .build()

        // 8066 Octafluoro Pentanol
        OctafluoroPentanol = Material.Builder(8066, GTLiteMod.id("octafluoro_pentanol"))
            .liquid()
            .color(0xE5EBDE)
            .components(Carbon, 5, Hydrogen, 4, Fluorine, 8, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8067 Raw Polyphosphonitrile Fluoro Rubber
        RawPolyphosphonitrileFluoroRubber = Material.Builder(8067, GTLiteMod.id("raw_polyphosphonitrile_fluoro_rubber"))
            .dust()
            .color(0x585858)
            .components(Carbon, 24, Hydrogen, 16, Oxygen, 8, Nitrogen, 4, Phosphorus, 4, Fluorine, 40)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(CH2CF3)6(CH2C3F7)2(C2F4)2(NPO)4O4", true)

        // 8068 Polyphosphonitrile Fluoro Rubber
        PolyphosphonitrileFluoroRubber = Material.Builder(8068, GTLiteMod.id("polyphosphonitrile_fluoro_rubber"))
            .polymer()
            .liquid()
            .color(0x372B28)
            .components(Carbon, 24, Hydrogen, 16, Oxygen, 8, Nitrogen, 4, Phosphorus, 4, Fluorine, 40)
            .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_ROD, GENERATE_RING, GENERATE_FOIL)
            .build()
            .setFormula("(CH2CF3)6(CH2C3F7)2(C2F4)2(NPO)4O4", true)

        // 8069 Polytetrahydrofuran
        Polytetrahydrofuran = Material.Builder(8069, GTLiteMod.id("polytetrahydrofuran"))
            .liquid()
            .color(0x089B3E)
            .components(Carbon, 4, Hydrogen, 10, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(C4H8O)OH2", true)

        // 8070 Dinitrotoluene
        Dinitrotoluene = Material.Builder(8070, GTLiteMod.id("dinitrotoluene"))
            .liquid()
            .color(0xEAEFC9)
            .components(Carbon, 7, Hydrogen, 6, Nitrogen, 2, Oxygen, 4)
            .build()

        // 8071 Diaminotoluene
        Diaminotoluene = Material.Builder(8071, GTLiteMod.id("diaminotoluene"))
            .liquid()
            .color(0xEA8204)
            .components(Carbon, 7, Hydrogen, 7, Nitrogen, 2)
            .build()
            .setFormula("C6H3(NH2)2CH3", true)

        // 8072 Phosgene
        Phosgene = Material.Builder(8072, GTLiteMod.id("phosgene"))
            .gas()
            .color(0x48927C)
            .components(Carbon, 1, Oxygen, 1, Chlorine, 2)
            .build()

        // 8073 Toluene Diisocyanate
        TolueneDiisocyanate = Material.Builder(8073, GTLiteMod.id("toluene_diisocyanate"))
            .liquid()
            .color(0xCCCC66)
            .components(Carbon, 9, Hydrogen, 8, Nitrogen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("CH3C6H3(NCO)2", true)

        // 8074 Toluene Tetramethyl Diisocyanate
        TolueneTetramethylDiisocyanate = Material.Builder(8074, GTLiteMod.id("toluene_tetramethyl_diisocyanate"))
            .liquid()
            .color(0xBFBFBF)
            .components(Carbon, 19, Hydrogen, 12, Oxygen, 3, Nitrogen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(CONH)2(C6H4)2CH2(C4O)", true)

        // 8075 Raw Polytetramethylene Glycol Rubber
        RawPolytetramethyleneGlycolRubber = Material.Builder(8075, GTLiteMod.id("raw_polytetramethylene_glycol_rubber"))
            .dust()
            .color(0xFFFFFF).iconSet(ROUGH)
            .components(Carbon, 23, Hydrogen, 23, Oxygen, 5, Nitrogen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(CONH)2(C6H4)2CH2(C4O)HO(CH2)4OH", true)

        // 8076 Polytetramethylene Glycol Rubber
        PolytetramethyleneGlycolRubber = Material.Builder(8076, GTLiteMod.id("polytetramethylene_glycol_rubber"))
            .polymer()
            .liquid()
            .color(0xFFFFFF)
            .components(Carbon, 23, Hydrogen, 23, Oxygen, 5, Nitrogen, 2)
            .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_ROD, GENERATE_RING, GENERATE_FOIL)
            .build()
            .setFormula("(CONH)2(C6H4)2CH2(C4O)HO(CH2)4OH", true)

        // 8077 Dense Hydrazine Rocket Fuel
        DenseHydrazineRocketFuel = Material.Builder(8077, GTLiteMod.id("dense_hydrazine_rocket_fuel"))
            .liquid()
            .color(0x912565)
            .components(Dimethylhydrazine, 1, Methanol, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8078 Methylhydrazine
        Methylhydrazine = Material.Builder(8078, GTLiteMod.id("methylhydrazine"))
            .liquid()
            .color(0x321452)
            .components(Carbon, 1, Hydrogen, 6, Nitrogen, 2)
            .build()

        // 8079 Methylhydrazine Nitrate Rocket Fuel
        MethylhydrazineNitrateRocketFuel = Material.Builder(8079, GTLiteMod.id("methylhydrazine_nitrate_rocket_fuel"))
            .liquid()
            .color(0x607186)
            .components(Methylhydrazine, 1, Tetranitromethane, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8080 Resorcinol
        Resorcinol = Material.Builder(8080, GTLiteMod.id("resorcinol"))
            .liquid()
            .color(0x9DA38D)
            .components(Carbon, 6, Hydrogen, 6, Oxygen, 2)
            .build()

        // 8081 Fluorotoluene
        Fluorotoluene = Material.Builder(8081, GTLiteMod.id("fluorotoluene"))
            .liquid()
            .color(0x6EC5B8)
            .components(Carbon, 7, Hydrogen, 7, Fluorine, 1)
            .build()

        // 8082 Difluorobenzophenone
        Difluorobenzophenone = Material.Builder(8082, GTLiteMod.id("difluorobenzophenone"))
            .dust()
            .color(0xC44DA5).iconSet(SHINY)
            .components(Carbon, 13, Hydrogen, 8, Oxygen, 1, Fluorine, 2)
            .build()
            .setFormula("(FC6H4)2CO", true)

        // 8083 Hydroquinone
        Hydroquinone = Material.Builder(8083, GTLiteMod.id("hydroquinone"))
            .liquid()
            .color(0x83251A)
            .components(Carbon, 6, Hydrogen, 6, Oxygen, 2)
            .build()
            .setFormula("C6H4(OH)2", true)

        // 8084 Polyetheretherketone (PEEK)
        Polyetheretherketone = Material.Builder(8084, GTLiteMod.id("polyetheretherketone"))
            .polymer()
            .liquid()
            .color(0x45433D)
            .components(Carbon, 20, Hydrogen, 12, Oxygen, 3)
            .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
            .build()

        // 8085 Fluorescein
        Fluorescein = Material.Builder(8085, GTLiteMod.id("fluorescein"))
            .dust()
            .color(0xE0E660).iconSet(SHINY)
            .components(Carbon, 20, Hydrogen, 12, Oxygen, 5)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8086 Erythrosine
        Erythrosine = Material.Builder(8086, GTLiteMod.id("erythrosine"))
            .dust()
            .color(0xC63611).iconSet(DULL)
            .components(Carbon, 20, Hydrogen, 6, Oxygen, 5, Sodium, 2, Iodine, 4)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8087 Isochloropropane
        Isochloropropane = Material.Builder(8087, GTLiteMod.id("isochloropropane"))
            .liquid()
            .color(0xC3AC65)
            .components(Carbon, 3, Hydrogen, 7, Chlorine, 1)
            .build()
            .setFormula("CH3CHClCH3", true)

        // 8088 Acetic Anhydride
        AceticAnhydride = Material.Builder(8088, GTLiteMod.id("acetic_anhydride"))
            .liquid()
            .color(0xE2EBD9)
            .components(Carbon, 4, Hydrogen, 6, Oxygen, 3)
            .build()

        // 8089 Dinitrodipropanyloxybenzene
        Dinitrodipropanyloxybenzene = Material.Builder(8089, GTLiteMod.id("dinitrodipropanyloxybenzene"))
            .liquid()
            .color(0x9FAD1D)
            .components(Carbon, 12, Hydrogen, 16, Oxygen, 6, Nitrogen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C12H16O2(NO2)2", true)

        // 8090 Dibromomethylbenzene
        Dibromomethylbenzene = Material.Builder(8090, GTLiteMod.id("dibromomethylbenzene"))
            .liquid()
            .color(0x9F4839)
            .components(Carbon, 7, Hydrogen, 6, Bromine, 2)
            .build()

        // 8091 Terephthalaldehyde
        Terephthalaldehyde = Material.Builder(8091, GTLiteMod.id("terephthalaldehyde"))
            .dust()
            .color(0x567C2D).iconSet(ROUGH)
            .components(Carbon, 8, Hydrogen, 6, Oxygen, 2)
            .build()

        // 8092 Pretreated Zylon
        PretreatedZylon = Material.Builder(8092, GTLiteMod.id("pretreated_zylon"))
            .dust()
            .color(0x623250).iconSet(DULL)
            .components(Carbon, 20, Hydrogen, 22, Nitrogen, 2, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8093 Zylon
        Zylon = Material.Builder(8093, GTLiteMod.id("zylon"))
            .polymer()
            .liquid()
            .color(0xFFE000).iconSet(SHINY)
            .components(Carbon, 14, Hydrogen, 6, Nitrogen, 2, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
            .build()

        // 8094 Diborane
        Diborane = Material.Builder(8094, GTLiteMod.id("diborane"))
            .gas()
            .color(0x3F3131)
            .components(Boron, 2, Hydrogen, 6)
            .build()

        // 8095 Borazine
        Borazine = Material.Builder(8095, GTLiteMod.id("borazine"))
            .liquid()
            .color(0x542828)
            .components(Boron, 3, Hydrogen, 6, Nitrogen, 3)
            .build()

        // 8096 Trichloroborazine
        Trichloroborazine = Material.Builder(8096, GTLiteMod.id("trichloroborazine"))
            .liquid()
            .color(0xD62929)
            .components(Boron, 3, Chlorine, 3, Hydrogen, 3, Nitrogen, 3)
            .build()

        // 8097 γ-Butyrolactone
        GammaButyrolactone = Material.Builder(8097, GTLiteMod.id("gamma_butyrolactone"))
            .liquid()
            .color(0xAF04D6)
            .components(Carbon, 4, Hydrogen, 6, Oxygen, 2)
            .build()

        // 8098 N-Methyl-2-Pyrrolidone
        NMethylPyrrolidone = Material.Builder(8098, GTLiteMod.id("n_methyl_pyrrolidone"))
            .liquid()
            .color(0xA504D6)
            .components(Carbon, 5, Hydrogen, 9, Nitrogen, 1, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8099 Acetylene
        Acetylene = Material.Builder(8099, GTLiteMod.id("acetylene"))
            .liquid()
            .color(0x959C60)
            .components(Carbon, 2, Hydrogen, 2)
            .build()

        // 8100 Tetrabromoethane
        Tetrabromoethane = Material.Builder(8100, GTLiteMod.id("tetrabromoethane"))
            .liquid()
            .color(0x5AAADA)
            .components(Carbon, 2, Hydrogen, 2, Bromine, 4)
            .build()

        // 8101 Terephthalic Acid
        TerephthalicAcid = Material.Builder(8101, GTLiteMod.id("terephthalic_acid"))
            .dust()
            .color(0x5ACCDA).iconSet(ROUGH)
            .components(Carbon, 8, Hydrogen, 6, Oxygen, 4)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C6H4(CO2H)2", true)

        // 8102 Bistrichloromethylbenzene
        Bistrichloromethylbenzene = Material.Builder(8102, GTLiteMod.id("bistrichloromethylbenzene"))
            .liquid()
            .color(0xCF8498)
            .components(Carbon, 8, Hydrogen, 4, Chlorine, 6)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C6H4(CCl3)2", true)

        // 8103 Terephthaloyl Chloride
        TerephthaloylChloride = Material.Builder(8103, GTLiteMod.id("terephthaloyl_chloride"))
            .dust()
            .color(0xFAC4DA).iconSet(SHINY)
            .components(Carbon, 8, Hydrogen, 4, Oxygen, 2, Chlorine, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C6H4(COCl)2", true)

        // 8104 Kevlar
        Kevlar = Material.Builder(8104, GTLiteMod.id("kevlar"))
            .polymer()
            .liquid()
            .color(0xF0F078)
            .components(Carbon, 14, Hydrogen, 10, Nitrogen, 2, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
            .fluidPipeProperties(2000, 700, true)
            .build()
            .setFormula("(C6H4)2(CO)2(NH)2", true)

        // 8105 Para Toluic Acid
        ParaToluicAcid = Material.Builder(8105, GTLiteMod.id("para_toluic_acid"))
            .liquid(FluidBuilder().attribute(FluidAttributes.ACID))
            .color(0x4FA597)
            .components(Carbon, 8, Hydrogen, 8, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8106 Methylparatoluate
        Methylparatoluate = Material.Builder(8106, GTLiteMod.id("methylparatoluate"))
            .liquid()
            .color(0x76BCB0)
            .components(Carbon, 9, Hydrogen, 10, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8107 Dimethyl Terephthalate
        DimethylTerephthalate = Material.Builder(8107, GTLiteMod.id("dimethyl_terephthalate"))
            .liquid()
            .color(0x05D8AF)
            .components(Carbon, 10, Hydrogen, 10, Oxygen, 4)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8108 Polyethylene Terephthalate (PET)
        PolyethyleneTerephthalate = Material.Builder(8108, GTLiteMod.id("polyethylene_terephthalate"))
            .polymer()
            .liquid()
            .color(0x1E5C58)
            .components(Carbon, 10, Hydrogen, 6, Oxygen, 4)
            .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL)
            .build()

        // 8109 Trimethylaluminium
        Trimethylaluminium = Material.Builder(8109, GTLiteMod.id("trimethylaluminium"))
            .liquid()
            .color(0x6ECCFF)
            .components(Aluminium, 2, Carbon, 6, Hydrogen, 18)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("Al2(CH3)6", true)

        // 8110 Trimethylgallium
        Trimethylgallium = Material.Builder(8110, GTLiteMod.id("trimethylgallium"))
            .liquid()
            .color(0x4F92FF)
            .components(Gallium, 1, Carbon, 3, Hydrogen, 9)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("Ga(CH3)3", true)

        // 8111 Ammonium Cyanate
        AmmoniumCyanate = Material.Builder(8111, GTLiteMod.id("ammonium_cyanate"))
            .liquid()
            .color(0x3a5dcf)
            .components(Hydrogen, 4, Nitrogen, 2, Carbon, 1, Oxygen, 1)
            .build()
            .setFormula("NH4CNO", true)

        // 8112 Carbamide
        Carbamide = Material.Builder(8112, GTLiteMod.id("carbamide"))
            .dust()
            .color(0x16EF57).iconSet(ROUGH)
            .components(Carbon, 1, Hydrogen, 4, Nitrogen, 2, Oxygen, 1)
            .build()

        // 8113 Butanol
        Butanol = Material.Builder(8113, GTLiteMod.id("butanol"))
            .liquid()
            .color(0xC7AF2E)
            .components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
            .build()
            .setFormula("C4H9OH", true)

        // 8114 Tributylamine
        Tributylamine = Material.Builder(8114, GTLiteMod.id("tributylamine"))
            .liquid()
            .color(0x801A80)
            .components(Carbon, 12, Hydrogen, 27, Nitrogen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(C4H9)3N", true)

        // 8115 Dichloromethane
        Dichloromethane = Material.Builder(8115, GTLiteMod.id("dichloromethane"))
            .liquid()
            .color(0xB87FC7)
            .components(Carbon, 1, Hydrogen, 2, Chlorine, 2)
            .build()

        // 8116 Diethyl Ether
        DiethylEther = Material.Builder(8116, GTLiteMod.id("diethyl_ether"))
            .liquid()
            .color(0xFFA4A3)
            .components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(C2H5)2O", true)

        // 8117 2-Aminooxyacetic Acid
        AminooxyaceticAcid = Material.Builder(8117, GTLiteMod.id("aminooxyacetic_acid"))
            .liquid()
            .color(0xECFF1E)
            .components(Carbon, 2, Hydrogen, 5, Nitrogen, 1, Oxygen, 3)
            .build()

        // 8118 BenzylBromide
        BenzylBromide = Material.Builder(8118, GTLiteMod.id("benzyl_bromide"))
            .gas()
            .color(0xCF9D8C)
            .components(Carbon, 7, Hydrogen, 7, Bromine, 1)
            .build()
            .setFormula("C6H5CH2Br", true)

        // 8119 Benzyltrimethylammonium Bromide
        BenzyltrimethylammoniumBromide = Material.Builder(8119, GTLiteMod.id("benzyltrimethylammonium_bromide"))
            .dust()
            .colorAverage().iconSet(SHINY)
            .components(BenzylBromide, 1, Trimethylamine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C6H5CH2N(CH3)3Br", true)

        // 8120 2-Chlorobutane
        Chlorobutane = Material.Builder(8120, GTLiteMod.id("chlorobutane"))
            .liquid()
            .color(0xE6772D)
            .components(Carbon, 4, Hydrogen, 9, Chlorine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8121 Tertbutyl Alcohol
        TertbutylAlcohol = Material.Builder(8121, GTLiteMod.id("tertbutyl_alcohol"))
            .liquid()
            .color(0xBC8F44)
            .components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8122 Indene
        Indene = Material.Builder(8122, GTLiteMod.id("indene"))
            .liquid()
            .color(0x171429)
            .components(Carbon, 9, Hydrogen, 8)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C6H4C3H4", true)

        // 8123 Indanone
        Indanone = Material.Builder(8123, GTLiteMod.id("indanone"))
            .dust()
            .color(0x2E1616).iconSet(SHINY)
            .components(Carbon, 9, Hydrogen, 8, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C6H4C3H4O", true)

        // 8124 Truxene
        Truxene = Material.Builder(8124, GTLiteMod.id("truxene"))
            .liquid()
            .color(0x1A3336)
            .components(Carbon, 27, Hydrogen, 18)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8125 Bromomethane
        Bromomethane = Material.Builder(8125, GTLiteMod.id("bromomethane"))
            .gas()
            .color(0xC82C31)
            .components(Carbon, 1, Hydrogen, 3, Bromine, 1)
            .build()

        // 8126 1-Bromo-2-(Bromomethyl) Naphthalene
        BromoBromomethylNaphthalene = Material.Builder(8126, GTLiteMod.id("bromo_bromomethyl_naphthalene"))
            .liquid()
            .color(0x52122E)
            .components(Carbon, 11, Hydrogen, 8, Bromine, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8127 1-Bromobutane
        Bromobutane = Material.Builder(8127, GTLiteMod.id("bromobutane"))
            .gas()
            .color(0xE6E8A2)
            .components(Butene, 1, HydrobromicAcid, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C4H9Br", true)

        // 8128 Butyllithium
        Butyllithium = Material.Builder(8128, GTLiteMod.id("butyllithium"))
            .liquid()
            .color(0xE683B6B)
            .components(Butene, 1, Hydrogen, 1, Lithium, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C4H9Li", true)

        // 8129 Acetyl Chloride
        AcetylChloride = Material.Builder(8129, GTLiteMod.id("acetyl_chloride"))
            .liquid()
            .color(0xC3C3C3)
            .components(Carbon, 2, Hydrogen, 3, Oxygen, 1, Chlorine, 1)
            .build()
            .setFormula("CH3COCl", true)

        // 8130 Dimethylacetamide
        Dimethylacetamide = Material.Builder(8130, GTLiteMod.id("dimethylacetamide"))
            .liquid()
            .color(0x18AEA5)
            .components(Carbon, 4, Hydrogen, 9, Nitrogen, 1, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(CH3)2NC(O)CH3", true)

        // 8131 GeodesicPolyarene
        GeodesicPolyarene = Material.Builder(8131, GTLiteMod.id("geodesic_polyarene"))
            .dust()
            .color(0x9E81A8).iconSet(METALLIC)
            .components(Carbon, 60, Hydrogen, 30)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8132 Fullerene
        Fullerene = Material.Builder(8132, GTLiteMod.id("fullerene"))
            .polymer()
            .liquid()
            .color(0x72556A).iconSet(BRIGHT)
            .flags(STD_METAL, DISABLE_DECOMPOSITION, NO_SMELTING, GENERATE_FOIL, GENERATE_FINE_WIRE)
            .components(Carbon, 60)
            .build()

        // 8133 Glucose
        Glucose = Material.Builder(8133, GTLiteMod.id("glucose"))
            .dust()
            .color(Sugar.materialRGB + 5).iconSet(ROUGH)
            .components(Carbon, 6, Hydrogen, 12, Oxygen, 6)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8134 Fructose
        Fructose = Material.Builder(8134, GTLiteMod.id("fructose"))
            .dust()
            .color(Sugar.materialRGB + 10).iconSet(ROUGH)
            .components(Carbon, 6, Hydrogen, 12, Oxygen, 6)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8135 Oxalic Acid
        OxalicAcid = Material.Builder(8135, GTLiteMod.id("oxalic_acid"))
            .liquid()
            .color(0x4AAAE2)
            .components(Carbon, 2, Hydrogen, 2, Oxygen, 4)
            .build() // (HOOC)(COOH)

        // 8136 Dibromoacrolein
        Dibromoacrolein = Material.Builder(8136, GTLiteMod.id("dibromoacrolein"))
            .liquid()
            .color(0x7C4660)
            .components(Carbon, 2, Hydrogen, 2, Bromine, 2, Oxygen, 2)
            .build()

        // 8137 Bromodihydrothiine
        Bromodihydrothiine = Material.Builder(8137, GTLiteMod.id("bromodihydrothiine"))
            .liquid()
            .color(0x66F36E)
            .components(Carbon, 4, Hydrogen, 4, Sulfur, 2, Bromine, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8138 Bis-(Ethylenedithio)-Tetraselenafulvalene (BETS)
        BETS = Material.Builder(8138, GTLiteMod.id("bisethylenedithiotetraselenafulvalene"))
            .dust()
            .color(0x98E993).iconSet(ROUGH)
            .flags(DISABLE_DECOMPOSITION)
            .components(Carbon, 10, Hydrogen, 8, Sulfur, 4, Selenium, 4)
            .build()

        // 8139 Benzaldehyde
        Benzaldehyde = Material.Builder(8139, GTLiteMod.id("benzaldehyde"))
            .liquid()
            .color(0x957D53)
            .components(Carbon, 7, Hydrogen, 6, Oxygen, 1)
            .build()

        // 8140 SuccinicAnhydride
        SuccinicAnhydride = Material.Builder(8140, GTLiteMod.id("succinic_anhydride"))
            .dust()
            .color(0xA2569D)
            .components(Carbon, 4, Hydrogen, 4, Oxygen, 3)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(CH2CO)2O", true)

        // 8141 N-Hydroxysuccinimide
        NHydroxysuccinimide = Material.Builder(8141, GTLiteMod.id("n_hydroxysuccinimide"))
            .dust()
            .color(0x33BAFB).iconSet(METALLIC)
            .components(Carbon, 4, Hydrogen, 5, Nitrogen, 1, Oxygen, 3)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(CH2CO)2NOH", true)

        // 8142 Succinimidyl Acetate
        SuccinimidylAcetate = Material.Builder(8142, GTLiteMod.id("succinimidyl_acetate"))
            .dust()
            .color(0x1D3605).iconSet(ROUGH)
            .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1, Oxygen, 4)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8143 Dimethylamine Hydrochloride
        DimethylamineHydrochloride = Material.Builder(8143, GTLiteMod.id("dimethylamine_hydrochloride"))
            .liquid()
            .color(0xE3EBDC)
            .components(Dimethylamine, 1, HydrochloricAcid, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C2H8NCl", true)

        // 8144 Dimethylformamide (DMF)
        Dimethylformamide = Material.Builder(8144, GTLiteMod.id("dimethylformamide"))
            .liquid()
            .color(0x42BDFF)
            .components(Carbon, 3, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(CH3)2NC(O)H", true)

        // 8145 Succinimide
        Succinimide = Material.Builder(8145, GTLiteMod.id("succinimide"))
            .dust()
            .color(0x1774B6).iconSet(ROUGH)
            .components(Carbon, 4, Hydrogen, 5, Nitrogen, 1, Oxygen, 2)
            .build()

        // 8146 Acetonitrile
        Acetonitrile = Material.Builder(8146, GTLiteMod.id("acetonitrile"))
            .liquid()
            .color(0x7D82A3)
            .components(Carbon, 2, Hydrogen, 3, Nitrogen, 1)
            .build()
            .setFormula("CH3CN", true)

        // 8147 Acetamide
        Acetamide = Material.Builder(8147, GTLiteMod.id("acetamide"))
            .dust()
            .color(0x7D82A3)
            .components(Carbon, 2, Hydrogen, 5, Nitrogen, 1, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("CH3CONH2", true)

        // 8148 Acetaldehyde
        Acetaldehyde = Material.Builder(8148, GTLiteMod.id("acetaldehyde"))
            .liquid()
            .color(0xF3F2F1)
            .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
            .build()

        // 8149 Glyoxal
        Glyoxal = Material.Builder(8149, GTLiteMod.id("glyoxal"))
            .liquid()
            .color(0xC9C7AB)
            .components(Carbon, 2, Hydrogen, 2, Oxygen, 2)
            .build()

        // 8150 Benzylamine
        Benzylamine = Material.Builder(8150, GTLiteMod.id("benzylamine"))
            .liquid()
            .color(0x527A92)
            .components(Carbon, 7, Hydrogen, 9, Nitrogen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8151 Benzyl Chloride
        BenzylChloride = Material.Builder(8151, GTLiteMod.id("benzyl_chloride"))
            .gas()
            .color(0x6699CC)
            .components(Carbon, 7, Hydrogen, 7, Chlorine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8152 Hexabenzylhexaazaisowurtzitane (HBHIW)
        Hexabenzylhexaazaisowurtzitane = Material.Builder(8152, GTLiteMod.id("hexabenzylhexaazaisowurtzitane"))
            .dust()
            .color(0x48561E)
            .components(Carbon, 48, Hydrogen, 48 ,Nitrogen, 6)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8153 Dibenzyltetraacetylhexaazaisowurtzitane (DBTAHIW)
        Dibenzyltetraacetylhexaazaisowurtzitane = Material.Builder(8153, GTLiteMod.id("dibenzyltetraacetylhexaazaisowurtzitane"))
            .dust()
            .color(0xB7E8EE)
            .components(Carbon, 28, Hydrogen, 32, Nitrogen, 6, Oxygen, 4)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8154 Tetraacetyldinitrosohexaazaisowurtzitane (TADNHIW)
        Tetraacetyldinitrosohexaazaisowurtzitane = Material.Builder(8154, GTLiteMod.id("tetraacetyldinitrosohexaazaisowurtzitane"))
            .dust()
            .color(0xEA7584).iconSet(ROUGH)
            .components(Carbon, 14, Hydrogen, 18, Nitrogen, 8, Oxygen, 6)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8155 Crude Hexanitrohexaaxaisowurtzitane
        CrudeHexanitrohexaaxaisowurtzitane = Material.Builder(8155, GTLiteMod.id("crude_hexanitrohexaaxaisowurtzitane"))
            .dust()
            .color(0x5799EC)
            .components(Carbon, 6, Hydrogen, 6, Nitrogen, 12, Oxygen, 12)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8156 Hexanitrohexaaxaisowurtzitane (HNIW)
        Hexanitrohexaaxaisowurtzitane = Material.Builder(8156, GTLiteMod.id("hexanitrohexaaxaisowurtzitane"))
            .dust()
            .color(0x0B7222).iconSet(BRIGHT)
            .components(Carbon, 6, Hydrogen, 6, Nitrogen, 12, Oxygen, 12)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8157 Hexamethylenetetramine
        Hexamethylenetetramine = Material.Builder(8157, GTLiteMod.id("hexamethylenetetramine"))
            .dust()
            .color(0x53576D)
            .components(Carbon, 6, Hydrogen, 12, Nitrogen, 4)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(CH2)6N4", true)

        // 8158 Cyclotetramethylene Tetranitroamine (HMX)
        CyclotetramethyleneTetranitroamine = Material.Builder(8158, GTLiteMod.id("cyclotetramethylene_tetranitroamine"))
            .liquid()
            .color(0xD0A57B).iconSet(SHINY)
            .components(Carbon, 4, Hydrogen, 8, Nitrogen, 8, Oxygen, 8)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8159 Octaazacubane
        Octaazacubane = Material.Builder(8159, GTLiteMod.id("octaazacubane"))
            .dust()
            .color(Nitrogen.materialRGB).iconSet(SHINY)
            .components(Nitrogen, 8)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8160 Dibenzylideneacetone
        Dibenzylideneacetone = Material.Builder(8160, GTLiteMod.id("dibenzylideneacetone"))
            .liquid()
            .color(0xCC6699)
            .components(Carbon, 17, Hydrogen, 14, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8161 Pyridine
        Pyridine = Material.Builder(8161, GTLiteMod.id("pyridine"))
            .liquid()
            .colorAverage(Ammonia, Formaldehyde)
            .components(Carbon, 5, Nitrogen, 5, Nitrogen, 1)
            .build()

        // 8162 Bipyridine
        Bipyridine = Material.Builder(8162, GTLiteMod.id("bipyridine"))
            .dust()
            .color(0x978662)
            .components(Carbon, 10, Hydrogen, 8, Nitrogen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8163 Cyclooctadiene
        Cyclooctadiene = Material.Builder(8163, GTLiteMod.id("cyclooctadiene"))
            .liquid()
            .color(0x40AC40)
            .components(Carbon, 8, Hydrogen, 12)
            .build()

        // 8164 Dichlorocyclooctadieneplatinium
        Dichlorocyclooctadieneplatinium = Material.Builder(8164, GTLiteMod.id("dichlorocyclooctadieneplatinium"))
            .dust()
            .color(0xD4E982).iconSet(BRIGHT)
            .components(Carbon, 8, Hydrogen, 12, Chlorine, 2, Platinum, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8165 Diiodobiphenyl
        Diiodobiphenyl = Material.Builder(8165, GTLiteMod.id("diiodobiphenyl"))
            .dust()
            .color(0x000C52).iconSet(METALLIC)
            .components(Carbon, 12, Hydrogen, 8, Iodine, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8166 Trimethyltin Chloride
        TrimethyltinChloride = Material.Builder(8166, GTLiteMod.id("trimethyltin_chloride"))
            .liquid()
            .color(0x7F776F)
            .components(Carbon, 3, Hydrogen, 6, Tin, 1, Chlorine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(CH3)3SnCl", true)

        // 8167 Cycloparaphenylene (CPP)
        Cycloparaphenylene = Material.Builder(8167, GTLiteMod.id("cycloparaphenylene"))
            .liquid()
            .color(0x60545A)
            .components(Carbon, 6, Hydrogen, 4)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8168 Octene
        Octene = Material.Builder(8168, GTLiteMod.id("octene"))
            .liquid()
            .color(0x7E8778)
            .components(Carbon, 8, Hydrogen, 16)
            .build()

        // 8169 Carbon Nanotube
        CarbonNanotube = Material.Builder(8169, GTLiteMod.id("carbon_nanotube"))
            .ingot()
            .liquid()
            .color(0x05090C).iconSet(BRIGHT)
            .components(Carbon, 48)
            .flags(EXT_METAL, DISABLE_DECOMPOSITION, NO_SMELTING, GENERATE_FOIL, GENERATE_FINE_WIRE,
                GENERATE_LONG_ROD, GENERATE_SPRING, GENERATE_SPRING_SMALL)
            .cableProperties(V[UIV], 8, 6)
            .build()

        // 8170 Sarcosine
        Sarcosine = Material.Builder(8170, GTLiteMod.id("sarcosine"))
            .dust()
            .color(0x339933).iconSet(SHINY)
            .components(Carbon, 3, Hydrogen, 7, Nitrogen, 1, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8171 Ferrocene
        Ferrocene = Material.Builder(8171, GTLiteMod.id("ferrocene"))
            .liquid()
            .color(0x6569A6)
            .components(Carbon, 10, Hydrogen, 10, Iron, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8172 Ferrocenylfulleropyrddolidine
        Ferrocenylfulleropyrddolidine = Material.Builder(8172, GTLiteMod.id("ferrocenylfulleropyrddolidine"))
            .liquid()
            .color(0x1D894A)
            .components(Carbon, 74, Hydrogen, 19, Nitrogen, 1, Iron, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8173 Dimethylaminopyridine
        Dimethylaminopyridine = Material.Builder(8173, GTLiteMod.id("dimethylaminopyridine"))
            .dust()
            .color(0x679887).iconSet(ROUGH)
            .components(Carbon, 7, Hydrogen, 10, Nitrogen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(CH3)2NC5H4N", true)

        // 8174 Triphenylphosphine (TPP)
        Triphenylphosphine = Material.Builder(8174, GTLiteMod.id("triphenylphosphine"))
            .dust()
            .color(0xE8DE3A).iconSet(ROUGH)
            .components(Carbon, 18, Hydrogen, 15, Phosphorus, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(C6H5)3P", true)

        // 8175 Isopropyl Alcohol
        IsopropylAlcohol = Material.Builder(8175, GTLiteMod.id("isopropyl_alcohol"))
            .liquid()
            .color(0x1EAC4C)
            .components(Carbon, 3, Hydrogen, 8, Oxygen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8176 Diisopropylcarbodiimide
        Diisopropylcarbodiimide = Material.Builder(8176, GTLiteMod.id("diisopropylcarbodiimide"))
            .liquid()
            .color(0xA0CFFE)
            .components(Carbon, 7, Hydrogen, 14, Nitrogen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8177 Phenylpentanoic Acid
        PhenylpentanoicAcid = Material.Builder(8177, GTLiteMod.id("phenylpentanoic_acid"))
            .liquid(FluidBuilder().attributes(FluidAttributes.ACID))
            .color(0x1B5154)
            .components(Carbon, 11, Hydrogen, 14, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8178 Dimethyl Sulfide
        DimethylSulfide = Material.Builder(8178, GTLiteMod.id("dimethyl_sulfide"))
            .liquid()
            .color(0x781111)
            .components(Carbon, 2, Hydrogen, 6, Sulfur, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(CH3)2S", true)

        // 8179 Phenyl-C61-Butyric Acid (PCBA)
        PhenylC61ButyricAcid = Material.Builder(8179, GTLiteMod.id("phenyl_c_61_butyric_acid"))
            .liquid()
            .color(0xCAC1F4)
            .components(Carbon, 72, Hydrogen, 14, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8180 Phenyl-C61-Butyric Styrene (PCBS)
        PhenylC61ButyricStyrene = Material.Builder(8180, GTLiteMod.id("phenyl_c_61_butyric_styrene"))
            .liquid()
            .color(0x11B557)
            .components(Carbon, 80, Hydrogen, 21, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8181 Fullerene Polymer Matrix (FPM)
        FullerenePolymerMatrix = Material.Builder(8181, GTLiteMod.id("fullerene_polymer_matrix"))
            .polymer()
            .liquid(FluidBuilder().temperature(500))
            .color(0x2F0B01).iconSet(SHINY)
            .components(Carbon, 153, Hydrogen, 36, Nitrogen, 1, Oxygen, 2, Lead, 1, Iron, 1)
            .flags(DISABLE_DECOMPOSITION, NO_SMASHING, NO_SMELTING, GENERATE_PLATE, GENERATE_FOIL, GENERATE_FINE_WIRE)
            .build()
            .setFormula("(C153H36NO2)PdFe", true)

        // 8182 Phosphorene
        Phosphorene = Material.Builder(8182, GTLiteMod.id("phosphorene"))
            .polymer()
            .liquid()
            .color(0x273239).iconSet(SHINY)
            .components(Phosphorus, 4)
            .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, GENERATE_FOIL)
            .build()

        // 8183 Methyltrichlorosilane
        Methyltrichlorosilane = Material.Builder(8183, GTLiteMod.id("methyltrichlorosilane"))
            .liquid()
            .color(0x4D7CB4)
            .components(Carbon, 1, Hydrogen, 3, Chlorine, 3, Silicon, 1)
            .build()
            .setFormula("Si(CH3)Cl3", true)

        // 8184 Diethyl Sulfide
        DiethylSulfide = Material.Builder(8184, GTLiteMod.id("diethyl_sulfide"))
            .liquid()
            .color(0xFF7E4B)
            .components(Ethylene, 2, Sulfur, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(C2H5)2S", true)

        // 8185 Guaiacol
        Guaiacol = Material.Builder(8185, GTLiteMod.id("guaiacol"))
            .liquid()
            .color(0xA63A00)
            .components(Carbon, 7, Hydrogen, 8, Oxygen, 2)
            .build()

        // 8186 Xylenol
        Xylenol = Material.Builder(8186, GTLiteMod.id("xylenol"))
            .liquid()
            .color(0xCF7D10)
            .components(Carbon, 8, Hydrogen, 10, Oxygen, 1)
            .build()

        // 8187 Creosol
        Creosol = Material.Builder(8187, GTLiteMod.id("creosol"))
            .liquid()
            .color(0x704E46)
            .components(Carbon, 7, Hydrogen, 8, Oxygen, 1)
            .build()

        // 8188 Methoxycreosol
        Methoxycreosol = Material.Builder(8188, GTLiteMod.id("methoxycreosol"))
            .liquid()
            .color(0xAF4617)
            .components(Carbon, 8, Hydrogen, 10, Oxygen, 2)
            .build()

        // 8189 Phenothiazine
        Phenothiazine = Material.Builder(8189, GTLiteMod.id("phenothiazine"))
            .dust()
            .color(0x67735C).iconSet(FINE)
            .components(Carbon, 12, Hydrogen, 9, Nitrogen, 1, Sulfur, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C12H9NS", true)

        // 8190 Isopropyl Chloride
        IsopropylChloride = Material.Builder(8190, GTLiteMod.id("isopropyl_chloride"))
            .liquid()
            .color(0x17B868)
            .components(Carbon, 3, Hydrogen, 7, Chlorine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(CH3)2CHCl", true)

        // 8191 10-Phenothiazine-2-Propyl Chloride
        PhenothiazinePropylChloride = Material.Builder(8191, GTLiteMod.id("phenothiazine_propyl_chloride"))
            .liquid()
            .color(0x444E1D)
            .components(Carbon, 15, Hydrogen, 14, Nitrogen, 1, Sulfur, 1, Chlorine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8192 Promethazine
        Promethazine = Material.Builder(8192, GTLiteMod.id("promethazine"))
            .dust()
            .color(0x92E829).iconSet(SHINY)
            .components(Carbon, 17, Hydrogen, 20, Nitrogen, 2, Sulfur, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8193 Codeine
        Codeine = Material.Builder(8193, GTLiteMod.id("codeine"))
            .dust()
            .color(0xFADEF2)
            .components(Carbon, 18, Hydrogen, 21, Nitrogen, 1, Oxygen, 3)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8194 Ammonium Fluoride
        AmmoniumFluoride = Material.Builder(8194, GTLiteMod.id("ammonium_fluoride"))
            .dust()
            .colorAverage(Ammonia, Fluorine)
            .components(Nitrogen, 1, Hydrogen, 4, Fluorine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8195 Ammonium Bifluoride
        AmmoniumBifluoride = Material.Builder(8195, GTLiteMod.id("ammonium_bifluoride"))
            .dust()
            .color(0x055370).iconSet(FINE)
            .components(Nitrogen, 1, Hydrogen, 5, Fluorine, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("NH4HF2", true)

        // 8196 N-Difluorophenylpyrrole
        NDifluorophenylpyrrole = Material.Builder(8196, GTLiteMod.id("n_difluorophenylpyrrole"))
            .liquid()
            .color(0x6D837E)
            .components(Carbon, 10, Hydrogen, 7, Fluorine, 2, Nitrogen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8197 Tetraethylammonium Bromide
        TetraethylammoniumBromide = Material.Builder(8197, GTLiteMod.id("tetraethylammonium_bromide"))
            .liquid()
            .color(0xCC3399)
            .components(Carbon, 8, Hydrogen, 20, Nitrogen, 1, Bromine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("N(CH2CH3)4Br", true)

        // 8198 Phenylsodium
        Phenylsodium = Material.Builder(8198, GTLiteMod.id("phenylsodium"))
            .liquid()
            .colorAverage()
            .components(Benzene, 1, Sodium, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C6H5Na", true)

        // 8199 Indigo
        Indigo = Material.Builder(8199, GTLiteMod.id("indigo"))
            .dust()
            .color(0x0000FF).iconSet(DULL)
            .components(Carbon, 16, Hydrogen, 10, Nitrogen, 2, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8200 Tetrabromoindigo
        Tetrabromoindigo = Material.Builder(8200, GTLiteMod.id("tetrabromoindigo"))
            .dust()
            .colorAverage().iconSet(DULL)
            .components(Indigo, 1, Bromine, 4)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C16H6Br4N2O2", true)

        // 8201 Cyan Indigo
        CyanIndigo = Material.Builder(8201, GTLiteMod.id("cyan_indigo"))
            .dust()
            .color(0x1661AB).iconSet(DULL)
            .components(Indigo, 1, Tetrabromoindigo, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(C16H10N2O2)2Br4", true)

        // 8202 Nigrosin
        Nigrosin = Material.Builder(8202, GTLiteMod.id("nigrosin"))
            .dust()
            .color(0x000000).iconSet(DULL)
            .components(Carbon, 36, Hydrogen, 26, Nitrogen, 5, Chlorine, 1, Sodium, 2, Sulfur, 2, Oxygen, 6)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8203 Sodium Sulfanilate
        SodiumSulfanilate = Material.Builder(8203, GTLiteMod.id("sodium_sulfanilate"))
            .dust()
            .color(0xE49879).iconSet(SHINY)
            .components(Carbon, 6, Hydrogen, 6, Nitrogen, 1, Sodium, 1, Oxygen, 3, Sulfur, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8204 Naphthylamine
        Naphthylamine = Material.Builder(8204, GTLiteMod.id("naphthylamine"))
            .liquid()
            .color(0xE3E81C)
            .components(Carbon, 10, Hydrogen, 9, Nitrogen, 1)
            .build()
            .setFormula("C10H8NH", true)

        // 8205 Direct Brown 77
        DirectBrown77 = Material.Builder(8205, GTLiteMod.id("direct_brown_77"))
            .dust()
            .color(0x663300).iconSet(DULL)
            .components(Carbon, 26, Hydrogen, 19, Nitrogen, 6, Sodium, 1, Oxygen, 3, Sulfur, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8206 Tetracene
        Tetracene = Material.Builder(8206, GTLiteMod.id("tetracene"))
            .dust()
            .color(0x99801A).iconSet(SHINY)
            .components(Carbon, 18, Hydrogen, 12)
            .build()

        // 8207 Rhodamine B
        RhodamineB = Material.Builder(8207, GTLiteMod.id("rhodamine_b"))
            .dust()
            .color(0xFC2020).iconSet(SHINY)
            .components(Carbon, 28, Hydrogen, 31, Chlorine, 1, Nitrogen, 2, Oxygen, 3)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8208 Cetane Trimethyl Ammonium Bromide
        CetaneTrimethylAmmoniumBromide = Material.Builder(8208, GTLiteMod.id("cetane_trimethyl_ammonium_bromide"))
            .liquid()
            .color(0x949494)
            .components(Carbon, 19, Hydrogen, 42, Bromine, 1, Nitrogen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8209 Sorbose
        Sorbose = Material.Builder(8209, GTLiteMod.id("sorbose"))
            .dust()
            .color(0xFFFFFF).iconSet(METALLIC)
            .components(Carbon, 6, Hydrogen, 12, Oxygen, 6)
            .build()

        // 8210 Ascorbic Acid
        AscorbicAcid = Material.Builder(8210, GTLiteMod.id("ascorbic_acid"))
            .liquid()
            .color(0xE6CD00)
            .components(Carbon, 6, Hydrogen, 8, Oxygen, 6)
            .build()

        // 8211 Dehydroascorbic Acid
        DehydroascorbicAcid = Material.Builder(8211, GTLiteMod.id("dehydroascorbic_acid"))
            .liquid()
            .color(AscorbicAcid.materialRGB - 10)
            .components(Carbon, 6, Hydrogen, 6, Oxygen, 6)
            .build()

        // 8212 Cellulose
        Cellulose = Material.Builder(8212, GTLiteMod.id("cellulose"))
            .dust()
            .color(0xE2EBD9).iconSet(ROUGH)
            .components(Carbon, 6, Hydrogen, 10, Oxygen, 5)
            .build()

        // 8213 Xylose
        Xylose = Material.Builder(8213, GTLiteMod.id("xylose"))
            .dust()
            .color(0xDFDFDF).iconSet(ROUGH)
            .components(Carbon, 5, Hydrogen, 10, Oxygen, 5)
            .build()

        // 8214 Saccharic Acid
        SacchariaAcid = Material.Builder(8214, GTLiteMod.id("saccharic_acid"))
            .dust()
            .color(Sugar.materialRGB + 5).iconSet(METALLIC)
            .components(Carbon, 6, Hydrogen, 10, Oxygen, 8)
            .build()

        // 8215 Adipic Acid
        AdipicAcid = Material.Builder(8215, GTLiteMod.id("adipic_acid"))
            .dust()
            .color(0xDA9288).iconSet(ROUGH)
            .components(Carbon, 6, Hydrogen, 10, Oxygen, 4)
            .build()

        // 8216 Hexanediol
        Hexanediol = Material.Builder(8216, GTLiteMod.id("hexanediol"))
            .liquid()
            .color(0x98D1EF)
            .components(Carbon, 6, Hydrogen, 14, Oxygen, 2)
            .build()

        // 8217 Hexamethylenediamine
        Hexamethylenediamine = Material.Builder(8217, GTLiteMod.id("hexamethylenediamine"))
            .liquid()
            .color(0x45B387)
            .components(Carbon, 6, Hydrogen, 16, Nitrogen, 2)
            .build()

        // 8218 Tertbutanol
        Tertbutanol = Material.Builder(8218, GTLiteMod.id("tertbutanol"))
            .liquid()
            .color(0xCCCC2C)
            .components(Carbon, 4, Hydrogen, 10, Oxygen, 1)
            .build()

        // 8219 Ditertbutyl Dicarbonate
        DitertbutylDicarbonate = Material.Builder(8219, GTLiteMod.id("ditertbutyl_dicarbonate"))
            .dust()
            .color(0xCCCCF6).iconSet(ROUGH)
            .components(Carbon, 10, Hydrogen, 18, Oxygen, 5)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8220 Tris(2-aminoethyl)amine
        Trisaminoethylamine = Material.Builder(8220, GTLiteMod.id("trisaminoethylamine"))
            .liquid()
            .color(0x6F7D87)
            .components(Nitrogen, 4, Hydrogen, 18, Carbon, 6)
            .build()
            .setFormula("(NH2CH2CH2)3N", true)

        // 8221 Tertbutyl Azidoformate
        TertbutylAzidoformate = Material.Builder(8221, GTLiteMod.id("tertbutyl_azidoformate"))
            .liquid()
            .color(0x888818)
            .components(Carbon, 5, Hydrogen, 9, Nitrogen, 3, Oxygen, 2)
            .build()

        // 8222 Aminated Fullerene
        AminatedFullerene = Material.Builder(8222, GTLiteMod.id("aminated_fullerene"))
            .liquid()
            .color(0x2C2CAA)
            .components(Carbon, 60, Nitrogen, 12, Hydrogen, 12)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8223 Azafullerene
        Azafullerene = Material.Builder(8223, GTLiteMod.id("azafullerene"))
            .liquid()
            .color(0x8A7A1A)
            .components(Carbon, 60, Nitrogen, 12, Hydrogen, 12)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8224 Fulvic Acid
        FulvicAcid = Material.Builder(8224, GTLiteMod.id("fulvic_acid"))
            .liquid(FluidBuilder().temperature(312))
            .color(0x59492F)
            .components(Carbon, 14, Hydrogen, 12, Oxygen, 8)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8225 Mauveine
        Mauveine = Material.Builder(8225, GTLiteMod.id("mauveine"))
            .dust()
            .color(0x660066).iconSet(DULL)
            .components(Carbon, 26, Hydrogen, 23, Nitrogen, 4)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8226 Diketopyrrolopyrrole
        Diketopyrrolopyrrole = Material.Builder(8226, GTLiteMod.id("diketopyrrolopyrrole"))
            .dust()
            .color(0xFF6600).iconSet(DULL)
            .components(Carbon, 18, Hydrogen, 12, Nitrogen, 2, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8227 Isopropyl Succinate
        IsopropylSuccinate = Material.Builder(8227, GTLiteMod.id("isopropyl_succinate"))
            .liquid()
            .color(0xB26680)
            .components(Carbon, 7, Hydrogen, 12, Oxygen, 4)
            .build()

        // 8228 Eosin Y
        EosinY = Material.Builder(8228, GTLiteMod.id("eosin_y"))
            .dust()
            .color(0xEAB4EB).iconSet(SHINY)
            .components(Carbon, 20, Hydrogen, 6, Bromine, 4, Sodium, 2, Oxygen, 5)
            .flags(DISABLE_DECOMPOSITION)
            .build()

        // 8229 Phenylenedioxydiacetic Acid
        PhenylenedioxydiaceticAcid = Material.Builder(8229, GTLiteMod.id("phenylenedioxydiacetic_acid"))
            .liquid()
            .color(0xFFBBBA)
            .components(Carbon, 10, Hydrogen, 10, Oxygen, 6)
            .build()

        // 8230 Ethylamine
        Ethylamine = Material.Builder(8230, GTLiteMod.id("ethylamine"))
            .liquid()
            .color(0x9E9E9E)
            .components(Carbon, 2, Hydrogen, 7, Nitrogen, 1)
            .build()
            .setFormula("C2H5NH2", true)

        // 8231 Diethylthiourea
        Diethylthiourea = Material.Builder(8231, GTLiteMod.id("diethylthiourea"))
            .liquid()
            .color(0x8D8EC2)
            .components(Carbon, 5, Hydrogen, 12, Nitrogen, 2, Sulfur, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("(C2H5NH)2CS", true);

        // 8232 Isophthaloylbisdiethylthiourea
        Isophthaloylbisdiethylthiourea = Material.Builder(8232, GTLiteMod.id("isophthaloylbisdiethylthiourea"))
            .liquid()
            .color(0xA2D4E1)
            .components(Carbon, 18, Hydrogen, 26, Nitrogen, 4, Oxygen, 2, Sulfur, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()

    }

    // @formatter:on

}