# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**GregTech Lite Core** is a Minecraft 1.12.2 mod for the same name modpack **GregTech Lite**. It is written in Kotlin
(Kotlin 2.1.0), which is provided by the **Forgelin Continuous** mod (a library that uses a shadow jar to provide
Kotlin support for Minecraft 1.12.2).

### Buildscripts

Use **Retro Futura Gradle** as the basic processing handler for Minecraft (MCP mappings `stable_39`).
- Java Language Level / Kotlin JVM Target Level: **8** (Azul platform).

Support **Mixins** (`org.spongepowered.asm.mixin`) and **MixinExtras** (`com.llamalad7.mixinextras`) by **MixinBooter** mod.

### External Sources

Browse raw source code instead of guessing APIs:

| Source | Path |
|---|---|
| Minecraft Source Code | `build/rfg/minecraft-src` |
| GregTech (GTCEu) Source Code | `GregTech/` — workspace-local clone of `https://github.com/GregTechCEu/GregTech` |
| Modular UI 2 Source Code | `ModularUI/` — workspace-local clone of `https://github.com/CleanroomMC/ModularUI` |

MorphismLib sources are not provided in this repository; find and add them yourself if needed.
Note: `libs/*.jar` (GregTech, MorphismLib) are **build dependencies only — do not use them as reference source code**.

### Lesser-Known Dependencies

Model knowledge about the following dependencies is unreliable — treat their sources
(see External Sources) as the reference instead of guessing their APIs:

- **GTCEu** (GregTech CE Unofficial) — the maintained fork of GTCE / GregTech Community Edition.
- **AE2UEL** (Applied Energistics 2 Unofficial Extended Life) — the 1.12.2 continuation of Applied Energistics 2.
- **Modular UI 2** — a community UI library (CleanroomMC).
- **Morphism Lib** — a community Kotlin library (com.morphismmc).

## API Usage Guide

First and most important: we **use Kotlin language to write everything** and **only use Java language to write mixins**.
Module layout — keep files under the package matching their domain: `api`, `common`, `core`, `client`, `integration`, `loader`, `mixins`.

### Code Conventions

1. **Kotlin-first syntax.** Prefer Kotlin syntax over Java syntax in mod code: Stream -> Sequence, `StringBuilder` -> `buildString`, reflection -> kotlin.reflect. (The Kotlin stdlib/reflect come from the Forgelin Continuous jar; the only Java sources are mixins.)
2. **Kotlin constructs over library constructs.** Prefer Kotlin `List` / `Pair` over Guava `ImmutableList` / Apache Commons `Pair`; library constructions are allowed only when hard required.
3. **Extension functions first.** Prefer Kotlin extension functions over redundant helper methods; repo-wide extensions live in `api/extension/` (e.g. `ItemStackExt.kt`, `RecipeBuilderExt.kt`).
4. **Kotlin-style design patterns.** Prefer Kotlin-style design patterns over Java-style ones in mod code, e.g. Kotlin DSL instead of the Java Builder pattern; a Java-style pattern is allowed only when it is the required boundary (e.g. an external Java API).
5. **Sealed strategies as contravariant singletons.** Use sealed class + contravariant singletons for strategy-like polymorphism (generic enums are not allowed in Kotlin); see `api/data/handler/CheckStrategy.kt` for the full form.
6. **MoreCollections for hot paths.** When high performance is required, use `MoreCollections` (`gregtechlite.gtlitecore.api.collection.MoreCollections`); it wraps **FastUtil** and some Java collections — see the `// region` blocks in `api/collection/MoreCollections.kt`.
7. Use `BlockVariant` (`gregtechlite.gtlitecore.api.block.variant.BlockVariant`) and its related system but not `MetaBlock` / `VariantBlock` by GregTech.
8. Use `BlockAttributeRegistry` (`gregtechlite.gtlitecore.api.block.attribute.BlockAttributeRegistry`) and its related system (registered in `GTLiteAPI`) but not block tier registration in `GregTechAPI` by GregTech.
9. **Sync via MetaTileEntitySyncer.** Use `MetaTileEntitySyncer` and its related system by default (see `api/metatileentity/sync/MetaTileEntitySyncer.kt`); do not hand-write `writeToNBT` / `readFromNBT` or custom packets for synced fields.
10. **Never hand-edit `docs/*`** — it is the `dokkaGfm` output (git-ignored); regenerate it instead.
11. **Never hand-edit `manuscripts/*`** — manuscriptal (SAI2) asset sources archived by the `processManuscriptalResources` task; edit the originals under `src/main/resources/textures/` and re-run the task.

### Examples

See the real code for full forms:

- Sealed class + contravariant singleton: `src/main/kotlin/gregtechlite/gtlitecore/api/data/handler/CheckStrategy.kt`
- BlockVariant enum (and GT adapter, keep enum names matching GT `valueOf`): `src/main/kotlin/gregtechlite/gtlitecore/common/block/variant/component/MotorCasing.kt`
- Block attribute tiers: `src/main/kotlin/gregtechlite/gtlitecore/api/GTLiteAPI.kt` (`MOTOR_CASING_TIER`, `COIL_TIER`, `BlockAttributeRegistryWrapper`) — the `BlockAttributeRegistry<Int>` overload needs `StateTier`
- MTE sync: `src/main/kotlin/gregtechlite/gtlitecore/common/metatileentity/electric/MachineEnergyDistributor.kt`
