# ![logo](logo.png "GregTech Lite Core Logo") GregTech Lite Core

The Spiritual Successor of **[Gregicality Legacy][GCYL]**.

## :bookmark_tabs: Introduction

**GregTech Lite Core** is an Addition Mod for **[GregTech CEu][GTCEu]**, and as the Core Mod of Modpack **[GregTech Lite][GTLite]**.
This mod is wrote by *Kotlin language*, so it is hard dependent on **[Forgelin Continuous][Forgelin]**.

## :books: Features

### For Gameplay
- All endgame stage contents (from *Ultra High Voltage* to *Maximum Voltage*), referenced with [GT5u][GT5u] and [GCYL][GCYL].
- Friendly and Powerful Large Machines for all GregTech RecipeMaps, instanceof machines in [GCYM][GCYM].
- A huge earlygame and midgame contents rebalancing, consists of:
  - A lot of new Chemistry Processings and Ore Processings, such as Platinum Group, Rare Earth Elements and Growth Medium.
  - Simplification of some recipes, add more QoL contents for GregTech.
  - Rebalanced some materials/duration consumption of all recipes.
  - Buff all circuit produce amounts, each circuit has higher produce now:
    - Processor: 4 (SoC Recipe: 8)
    - Processor Assembly: 3
    - Computer: 2
    - Mainframe: 1
  - Unified rubbers with tiered stats in all recipes, for example, LuV Electric Pump can use both Styrene Butadiene Rubber and Silicone Rubber now.
- More diverse underground ecology, referenced with [GT6][GT6].
- More trees, building blocks, and miscellaneous contents, referenced with [GTFO][GTFO] and [Pam's Harvest Craft][HarvestCraft].
- Add compatibilities with GregTech and some other mods and add some QoL contents for these mods.

### For Development
- Template Block Tier Registries as a base system to build blocks with tiered stats.
- A Renderer/Shader system for GregTech Meta Contents, referenced with [Avaritia][Avaritia], [Botania][Botania] and [Monomorphism Lib][MonomorphismLib].
- Recipe Builders and Recipe Properties extensions for GregTech Recipe system.

## :coffee: License

Strictly speaking, the code we write and create is licensed under the **[Apache 2.0 License][GTLiteCoreLicense]**.
Regarding the attribution of textures and code, please refer to the **credit contents** below.

### Credits

Thanks for all the credit authors, it's your hard work that has brought about these wonderful contents:

- [**GT5u**][GT5u] is one of the referenced bases of the mod, this mod is on [GNU LGPL-3.0 License][GT5uLicense].
  All references of the mod consists of the following contents:
  - Some material iconSet textures:
    - Direct use or minor modification: Bedrockium, Cosmic Neutronium, Infinity, Spacetime, Halkonite Steel, Magmatter, Eternity.
    - Use some texture as render base: Omnium, Glitch, White Dwarf, Universium.
    - Referenced with some idea: Chromatic, Enriched.
  - Some item textures:
    - Direct use or minor modification: Optical Memory Chip, LV-MAX tier Mining Drones, Dynamic Circuit textures.
    - Use some texture as render base: QFT Catalysts, Wormhole Generators (referenced with DTPF Alignment Matrix).
  - Some multiblock machines and its textures:
    - Direct use or minor modification: Space Elevator and its modules, CoAL, Energy Infuser, Nano Forge, PCB Factory,
      QFT, Antimatter Forge and Generator, EOH.
    - Referenced with some idea: Fusion Reactor MK4 and MK5, Large Steam Compressor, Large Steam Alloy Smelter.
  - Some machine overlay textures.
  - Some renderer code for iconSet and shaderProgram or other OpenGL contents.
- [**Gregicality Legacy**][GCYL], the predecessor and the goal-level source of the mod, this mod is on [GNU GPL-3.0 License][GCYLLicense],
  All references of the mod consists of the following contents:
  - Some textures and models: 
    - Direct use or minor modification: Component Casings model base and Metal Casing texture base.
  - Some multiblock machines and its textures:
    - Direct use or minor modification: Large Processing Machines, Stellar Forge, Cosmic Ray Detector, Naquadah Reactor and Advanced Fusion
      Reactor.
  - Some chemistry processings:
    - Carbon Nanotube (CNT) and its variant, Fullerene and Fullerene Polymer Matrix (FPM) Chain.
    - Battery Chain and Algae Chain, Ascorbic Acid Chain, Citric Acid Chain.
    - Growth Medium Chain, Heavy Conductive Mixture Chain, HMX and HNIW Chain.
    - Mica Insulator Chain and Polymer Insulator Chain.
    - PEEK Chain, Nd:YAG, Pr/Ho:YLF, Zylon and RHTIH Chain.
- [**Gregicality Science**][GCYS], another predecessor of the mod, the develop base in some legacy version of the mod, this mod is on [MIT License][GCYSLicense],
  All references of the mod consists of the following contents:
  - Some item and block textures:
    - High-Tier Circuit textures and Suprachronal Circuit textures.
    - High-Tier Board, Circuit Board and SMD textures and the Processing Unit textures of Gooware, Optical and Spintronic Circuits.
    - Substrate Casings and Drill Head textures.
    - Crucible block model (textures is referenced with [GT6][GT6]).
  - Some multiblock machines:
    - Direct use or minor modification: CVD Unit, PECVD, Hydraulic Fracker, Nanoscale Fabricator, Bedrock Drilling Rid, Crystallization Crucible.
    - Referenced with some idea: LICVD, Large Burner Reactor and Large Cryogenic Reactor structure.
  - Some chemistry processings:
    - PEDOT, PMMA and Kevlar Chain, a part of Kevlar and Fullerene Chain, Carbon Nanotube (CNT) Recycling Chain.
    - Bedrockium, Adamantium and Vibranium Processing, Thallium, Germanium, Rubidium Processing.
    - Platinum Group, Rare Earth Elements Processing, Selenium, Tungsten and Tellurium Processing.
- [**GregTech 6**][GT6], referenced textures only, this mod is on [GNU GPL-3.0 License][GT6License],
  All references of the mod consists of the following contents:
  - Some item, block and tool textures:
    - Direct use or minor modification: Laser Emitter, Rolling Pin, Club, Universal Spade and Combination Wrench textures,
      some food textures.
    - Referenced with some idea: Crucible block textures.
  - Some machine overlay textures:
    - Direct use or minor modification: Nanoscale Fabricator, Large Burner Reactor, Large Cryogenic Reactor textures, 
      a part of Laminator, Loom, Acid Generator, Food Processor textures.
    - Referenced with some idea: Some cover textures such as Air Vent and Drain.
- [**GregTechFoodOption**][GTFO], referenced textures and some world generator codes, this mod is on [GNU LGPL-3.0 License][GTFOLicense],
  All references of the mod consists of the following contents:
  - Some item and block textures:
    - Direct use or minor modification: Saplings, Leaves, Logs, Planks and some tree related texture bases, some food textures.
  - Some code references:
    - A part of machine recipe logic such as Mob Extractor.
    - A part of World Generator related code.
- [**NewHorizonsCoreMod**][NHCore], referenced some code, this mod is on [GNU LGPL-3.0 License][NHCoreLicense],
  All references of the mod consists of the following contents:
    - The `IconLoader` class, confirm exit messages configurations.
    - Player login events codes.
- [**Supersymmetry Core**][SusyCore], referenced some textures and code, this mod is on [GNU GPL-3.0 License][SusyCoreLicense].
  All references of the mod consists of the following contents:
  - Some iconSet textures or item textures;
    - Direct use or minor modification: Some iconSet textures. 
    - Referenced with some idea: Stone and its variant textures.
  - Some machine textures:
    - Direct use or minor modification: CVD Unit overlay textures, a part of Laminator and Loom overlay textures.
    - Referenced with some idea: Coagulation Tank and Sap Collector.
    - Some code references:
      - Delegator metaTileEntity base and its TOP integration.
      - Sap Collector processing logic and Coagulation Tank recipe templates.

Salute and thank all credit authors :)

[Avaritia]: https://github.com/Morpheus1101/Avaritia
[Botania]: https://github.com/VazkiiMods/Botania
[Forgelin]: https://github.com/ChAoSUnItY/Forgelin-Continuous
[GTCEu]: https://github.com/GregTechCEu/GregTech
[GT5u]: https://github.com/GTNewHorizons/GT5-Unofficial
[GT6]: https://github.com/GregTech6/gregtech6
[GCYM]: https://github.com/GregTechCEu/gregicality-multiblocks
[GCYS]: https://github.com/GregTechCEu/gregicality-science
[GCYL]: https://github.com/GregTechCEu/gregicality-legacy
[GTFO]: https://github.com/bruberu/GregTechFoodOption
[HarvestCraft]: https://github.com/MatrexsVigil/harvestcraft
[NHCore]: https://github.com/GTNewHorizons/NewHorizonsCoreMod
[SusyCore]: https://github.com/SymmetricDevs/Susy-Core

[GTLite]: https://github.com/GregTechLite/GregTech-Lite-Modpack
[MonomorphismLib]: https://github.com/EpimorphicPioneers/Monomorphism-Lib

[GTLiteCoreLicense]: https://github.com/GregTechLite/GregTech-Lite-Core/blob/main/LICENSE
[GT5uLicense]: https://github.com/GTNewHorizons/GT5-Unofficial/blob/master/LICENSE.txt
[GT6License]: https://github.com/GregTech6/gregtech6/blob/master/LICENSE
[GCYLLicense]: https://github.com/GregTechCEu/gregicality-legacy/blob/master/LICENSE
[GCYSLicense]: https://github.com/GregTechCEu/gregicality-science/blob/master/LICENSE
[GTFOLicense]: https://github.com/bruberu/GregTechFoodOption/blob/master/LICENSE.txt
[NHCoreLicense]: https://github.com/GTNewHorizons/NewHorizonsCoreMod/blob/master/LICENSE
[SusyCoreLicense]: https://github.com/SymmetricDevs/Susy-Core/blob/main/LICENSE