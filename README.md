# ![logo](logo.png "GregTech Lite Core Logo") GregTech Lite Core

The Spiritual Successor of **[Gregicality Legacy](https://github.com/GregTechCEu/gregicality-legacy)**.

## :bookmark_tabs: Introduction

**GregTech Lite Core** is an Addition Mod for **[GregTech CEu](https://github.com/GregTechCEu/GregTech)**,
and as the Core Mod of Modpack **[GregTech Lite](https://www.mcmod.cn/modpack/655.html)**. This mod is wrote by *Kotlin language*, so it is hard-dependencied with **[Forgelin](https://github.com/ChAoSUnItY/Forgelin-Continuous)**.

## :books: Features

>>> [!note] For Gameplay
1. All endgame stage contents (from *Ultra High Voltage* to *Maximum Voltage*), referenced with [GT5u](https://github.com/GTNewHorizons/GT5-Unofficial) and [GCY](https://github.com/GregTechCEu/gregicality-legacy).
2. Friendly and Powerful Large Machines for all GregTech RecipeMaps, instanceof machines in [GCYM](https://github.com/GregTechCEu/gregicality-multiblocks).
3. A huge earlygame and midgame contents rebalancing, consists of:
  - A lot of new Chemistry Processings and Ore Processings, such as Platinum Group, Rare Earth Elements and Growth Medium.
  - Simplification of some recipes, add more QoL contents for GregTech.
  - Rebalanced some materials/duration consumption of all recipes.
  - Buff all circuit produce amounts, each circuit has higher produce now:
    - Processor: 4 (SoC Recipe: 8)
    - Processor Assembly: 3
    - Computer: 2
    - Mainframe: 1
  - Unified rubbers with tiered stats in all recipes, for example, LuV Electric Pump can use both Styrene Butadiene Rubber and Silicone Rubber now.
4. More diverse underground ecology, referenced with [GT6](https://github.com/GregTech6/gregtech6).
5. More trees, building blocks, and miscellaneous contents, referenced with [GTFO](https://github.com/bruberu/GregTechFoodOption) and [Pam's Harvest Craft](https://github.com/MatrexsVigil/harvestcraft).
6. Add compatibilities with GregTech and some other mods and add some QoL contents for these mods.
>>>

>>> [!important] For Development
1. Convenient methods for generating Multiblock Structure preview, port from [Monomorphism Lib](https://github.com/EpimorphicPioneers/Monomorphism-Lib).
2. Template Block Tier Registries as a base system to build blocks with tiered stats.
3. A Renderer/Shader system for GregTech Meta Contents, referenced with [Avaritia](https://github.com/Morpheus1101/Avaritia), [Botania](https://github.com/VazkiiMods/Botania) and [Monomorphism Lib](https://github.com/EpimorphicPioneers/Monomorphism-Lib).
4. Recipe Builders and Recipe Properties extensions for GregTech Recipe system.
>>>

## :crystal_ball: License

- GregTech Lite Core
  - Copyright (c) Magic_Sweepy, GateGuardian, RainyYC, et al.
  - [![License](https://img.shields.io/badge/License-Apache2.0-red.svg?style=flat-square)](http://www.apache.org/licenses/)
- GregTech Lite Modpack
  - Copyright (c) Magic_Sweepy, GateGuardian
  - [![License](https://img.shields.io/badge/License-AGPLv3-blue.svg?style=flat-square)](https://gitlab.com/gregtech-lite/gregtech-lite/-/blob/main/license)
- Texts, Translations and Questbook contents
  - Copyright (c) Magic_Sweepy
  - [![License](https://img.shields.io/badge/License-CC%20BY--NC--SA%203.0-yellow.svg?style=flat-square)](https://creativecommons.org/licenses/by-nc-sa/3.0/)
- Independently copyrighted Textures and Models
  - Copyright (c) Magic_Sweepy, U.M.T
  - [![License](https://img.shields.io/badge/License-CC%20BY--NC--SA%203.0-yellow.svg?style=flat-square)](https://creativecommons.org/licenses/by-nc-sa/3.0/)

## :coffee: Credits

Thanks for all the credit authors, it's your hard work that has brought about these wonderful contents:

- [**GT5u**](https://github.com/GTNewHorizons/GT5-Unofficial) is one of the referenced bases of the mod, this mod is on [GNU LGPL-3.0 License](https://github.com/GTNewHorizons/GT5-Unofficial/blob/master/LICENSE.txt).
  All references of the mod consists of the following contents:
    - Some material icon textures, e.g. *Cosmic Neutronium*, *Infinity*, *Halkonite Steel*, *Space Time* and *Eternity*, some custom icon textures is also referenced
      with **GT5u**, e.g. *Chromatic Glass*, *Omnium*, *Enriched* (some materials like *Hypogen* and *Legendarium* used this icon set).
    - Some item textures, e.g. *Optical Memory Chip*, *Mining Drones* and *QFT Catalysts*, some custom icon textures is also referenced with **GT5u**, e.g. 
      *Wormhole Generators* referenced with DTPF's *Alignment Matrix* item; Dynamic circuit textures is also referenced with old textures of **GT5u**.
    - A lot of multiblocks and its structures, e.g. *Space Elevator*, *Component Assembly Line*, *Energy Infuser*, *Nano Forge*, *PCB Factory*, 
      *Quantum Force Transformer*, *Antimatter Forge* and *Antimatter Generator*.
    - Some machine overlay textures.
- [**Gregicality Legacy**](https://github.com/GregTechCEu/gregicality-legacy), the predecessor and the goal-level source of the mod, this mod is on [GNU GPL-3.0 License](https://github.com/GregTechCEu/gregicality-legacy/blob/master/LICENSE),
  All references of the mod consists of the following contents:
    - Component Casings textures (p.s. all models is modified with original models in **GCYL**) and the metal casing texture base.
    - A lot of multiblocks and its structures, e.g. *All Large Machines*, *Naquadah Reactor*, *Advanced Fusion Reactor*, *Industrial PBF*, *Cosmic Ray Detector*,
      some multiblocks is referenced with **GCYL**, e.g. *Stellar Forge* (another reference is DTPF in **GT5u**).
    - A lot of chemistry processings, e.g. *PEEK Chain*, *HNIW Chain*, *Carbon Nanotube Chain*, *Sea Water Processing* and *Fission Reacting*.
- [**Gregicality Science**](https://github.com/GregTechCEu/gregicality-science), another predecessor of the mod, the develop base in some legacy version of the mod, this mod is on [MIT License](https://github.com/GregTechCEu/gregicality-science/blob/master/LICENSE),
  All references of the mod consists of the following contents:
    - Circuit textures for all High-tier circuits and *Suprachronal Circuits*.
    - Board and Circuit Boards textures, High-tier SMD textures and Processing Unit textures of *Gooware Circuit*, *Optical Circuit* and *Spintronic Circuit*.
    - Some multiblocks and its structures, e.g. *CVD Unit*, *Nanoscale Fabricator*, *Bedrock Drilling Rig*, *Crystallization Crucible*.
    - Some chemistry processings, e.g. *PEDOT Chain*, *Kevlar Chain*, *Fullerene Chain*, *Bedrockium Processing* and *Fusion Reacting*.
- [**GregTech 6**](https://github.com/GregTech6/gregtech6), referenced textures only, this mod is on [GNU GPL-3.0 License](https://github.com/GregTech6/gregtech6/blob/master/LICENSE),
  All references of the mod consists of the following contents:
    - Laser Emitter, Rolling Pin, Club, Universal Spade, Combination Wrench textures.
    - Some machine overlay textures, e.g. *Nanoscale Fabricator*, *Large Burner Reactor*, *Large Cryogenic Reactor* and a part of *Laminator*, *Loom* textures.
    - Crucible block textures, the model of these blocks referenced with **GCYS**.
- [**GregTechFoodOption**](https://github.com/bruberu/GregTechFoodOption), referenced textures and some world generator codes, this mod is on [GNU LGPL-3.0 License](https://github.com/bruberu/GregTechFoodOption/blob/master/LICENSE.txt),
  All references of the mod consists of the following contents:
    - Saplings, leaves, logs, planks and some tree related texture bases.
    - Food behaviour and a part of textures.
    - Some machine recipe logic, e.g. *Mob Extractor*.
    - A part of World Generator and features system codes (another referenced is from **GTCEu** World Generator system).
- [**NewHorizonsCoreMod**](https://github.com/GTNewHorizons/NewHorizonsCoreMod), referenced some codes, this mod is on [GNU LGPL-3.0 License](https://github.com/GTNewHorizons/NewHorizonsCoreMod/blob/master/LICENSE),
  All references of the mod consists of the following contents:
    - IconLoader class, confirm exit messages configurations.
    - Player login events codes.
- [**Avaritia**](https://github.com/Morpheus1101/Avaritia), referenced some renderer and shader codes, this mod is on [MIT License](https://github.com/Morpheus1101/Avaritia/blob/master/README.md),
  All references of the mod consists of the following contents:
    - *Cosmic Neutronium*, *Infinity* and *Universium* render textures
    - Animation tooltips implementations.
    - Some OpenGL codes like ShaderProgram.
- [**Supersymmetry**](https://github.com/SymmetricDevs/Supersymmetry) Modpack, referenced some textures yet (planed to remove), this modpack is on [GNU LGPL-3.0 License](https://github.com/SymmetricDevs/Supersymmetry/blob/master-ceu/LICENSE),
  All references of the mod consists of the following contents:
    - *CVD Unit* overlay textures, a part of *Laminator* and *Loom* overlay textures.
    - Stone texture templates (another reference is **GTCEu** stone blocks)
    - Some material icon textures.

Salute and thank all credit authors :)