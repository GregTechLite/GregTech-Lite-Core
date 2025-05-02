# GregTech Lite Core

The Spiritual Successor of Gregicality Legacy.



## Features

### For Gameplay
- ...

### For Development
- More functional interfaces supported for primitive type, e.g. `BooleanBiConsumer`.
  - See at `magicbook.api.utils.functions`.
- Java 9 Feature "**StackWalker**" port for Java 8:
  - Used it by `magicbook.api.utils.reflect.StackWalker`.
  - Kotlin extension of `StackWalker` at `magicbook.api.utils.reflect.StackWalkerKt`.
- Java 21 Feature "**Sequenced Collection**" port for Java 8:
  - Used it by `magicbook.api.utils.maps.SequencedCollection`, `SequencedSet` and `SequencedMap`.
- Java 24 Feature "**Gatherer API**" port for Java 8:
  - Used it in your stream by `magicbook.api.utils.stream.LazyStreams#gatherer`.
  - Some implementations of gatherer at `magicbook.api.utils.stream.Gatherers`.

## License

- GregTech Lite Core
  - Copyright (c) Magic_Sweepy
  - [![License](https://img.shields.io/badge/License-Apache2.0-red.svg?style=flat-square)](http://www.apache.org/licenses/)
- GregTech Lite Modpack
  - Copyright (c) Magic_Sweepy
  - [![License](https://img.shields.io/badge/License-AGPLv3-blue.svg?style=flat-square)](https://gitlab.com/gregtech-lite/gregtech-lite/-/blob/main/license)
- Texts, Translations and Questbook contents
  - Copyright (c) Magic_Sweepy
  - [![License](https://img.shields.io/badge/License-CC%20BY--NC--SA%203.0-yellow.svg?style=flat-square)](https://creativecommons.org/licenses/by-nc-sa/3.0/)
- Independently copyrighted Textures and Models
  - Copyright (c) Magic_Sweepy and U.M.T
  - [![License](https://img.shields.io/badge/License-CC%20BY--NC--SA%203.0-yellow.svg?style=flat-square)](https://creativecommons.org/licenses/by-nc-sa/3.0/)

## Credits

Thanks for all the credit authors, it's your hard work that has brought about these wonderful contents:

- Dynamic circuits textures, infinity and cosmic neutronium textures, some multiblock structures, some machine overlay textures, Power IC textures are from [GT5u](https://github.com/GTNewHorizons/GT5-Unofficial),
  this mod is on [GNU LGPL-3.0 License](https://github.com/GTNewHorizons/GT5-Unofficial/blob/master/LICENSE.txt).
- Laser textures, Nanoscale Fabricator overlay textures, Burner/Cryogenic Reactor overlay textures, Rolling Pin textures and Crucible textures are from [GregTech 6](https://github.com/GregTech6/gregtech6),
  this mod is on [GNU GPL-3.0 License](https://github.com/GregTech6/gregtech6/blob/master/LICENSE).
- Circuit board textures, higher SMD textures, some multiblock structures and overlay textures, recipe maps and chemistry processings are from [Gregicality Science](https://github.com/GregTechCEu/gregicality-science), 
  this mod is on [MIT License](https://github.com/GregTechCEu/gregicality-science/blob/master/LICENSE).
- Some chemical processings, metal casing texture templates, fusion casing texture templates are from [Gregicality Legacy](https://github.com/GregTechCEu/gregicality-legacy),
  this mod is on [GNU GPL-3.0 License](https://github.com/GregTechCEu/gregicality-legacy/blob/master/LICENSE).
- Tree textures, some food textures and machine textures, a part of world generator codes and machine logic codes are from [GregTechFoodOption](https://github.com/bruberu/GregTechFoodOption),
  this mod is on [GNU LGPL-3.0 License](https://github.com/bruberu/GregTechFoodOption/blob/master/LICENSE.txt).
- Vertex Buffer Objects (VBO), quads, some JOML extends utilities and some renderer codes are from [GTNHLib](https://github.com/GTNewHorizons/GTNHLib),
  this mod is on [GNU LGPL-3.0 License](https://github.com/GTNewHorizons/GTNHLib/blob/master/LICENSE.txt).
- Cosmic neutronium and infinity render textures, animation tooltips implementations and some OpenGL codes are from [Avaritia](https://github.com/Morpheus1101/Avaritia),
  this mod is on [MIT License](https://github.com/Morpheus1101/Avaritia/blob/master/README.md).
- Some machine overlay textures, stone texture templates and material textures from [Supersymmetry](https://github.com/SymmetricDevs/Supersymmetry),
  this modpack is on [GNU LGPL-3.0 License](https://github.com/SymmetricDevs/Supersymmetry/blob/master-ceu/LICENSE).

Salute and thank all credit authors :)