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

### Non-Official Mods

This repository depends on the following mods which differ from their upstream / official versions:

- **GTCEu** (GregTech CE Unofficial) — the maintained fork of GTCE / GregTech Community Edition.
- **AE2UEL** (Applied Energistics 2 Unofficial Extended Life) — the 1.12.2 continuation of Applied Energistics 2.
- **Modular UI 2** — a community UI library (CleanroomMC).
- **Morphism Lib** — a community Kotlin library (com.morphismmc).

## API Usage Guide

First and most important: we **use Kotlin language to write everything** and **only use Java language to write mixins**.
Module layout: `api`, `common`, `core`, `client`, `integration`, `loader`, `mixins`.

### Code Conventions

1. Use Kotlin syntax instead of Java syntax, e.g. Java Stream API -> Kotlin Sequence, Java `StringBuilder` / String Operation
   -> Kotlin `buildString` / Kotlin String Operation, Java Reflection -> Kotlin Reflect.
2. Use Kotlin constructions instead of constructions in some Java or its external libraries, e.g. Guava `ImmutableList` -> Kotlin `List`,
   Apache Commons `Pair` -> Kotlin `Pair`. The construction in these libraries is only allowed to be used when hard required.
3. Use Kotlin extension method instead of some redundant methods.
4. Use Kotlin design pattern instead of Java design pattern, e.g. Java Builder Pattern -> Kotlin DSL Pattern.
5. Use sealed class + contravariant singleton instead of generic enum (not allowed in Kotlin) or some similar constructions.
   For example, here a strategy class `Strategy<in T>`, we exposing it with `Strategy<Any?>` singletons.
6. Use `MoreCollections` (`gregtechlite.gtlitecore.api.collection.MoreCollections`) when high performance is required,
   it is the Kotlin extensions for **FastUtil** and some Java collections.
7. Use correct package names and put all files in their proper packages, split by domain by default.
8. Use `BlockVariant` (`gregtechlite.gtlitecore.api.block.variant.BlockVariant`) and its related system but not `MetaBlock` / `VariantBlock` by GregTech.
9. Use `BlockAttributeRegistry` (`gregtechlite.gtlitecore.api.block.attribute.BlockAttributeRegistry`) and its related system (registered in `GTLiteAPI`) but not block tier registration in `GregTechAPI` by GregTech.
10. Use `MetaTileEntitySyncer` (`gregtechlite.gtlitecore.api.metatileentity.sync`) and its related system by default, not hand-written `writeToNBT` / `readFromNBT`, etc.
11. Do not change `docs/*`, it is **Dokka**-generated API reference.
12. Do not change `manuscripts/*`, it is manuscriptal asset sources.

### Examples

**Exam 1: Sealed class + Contravariant Singleton**
```kotlin
sealed class CheckStrategy<in T>
{
    object Equals : CheckStrategy<Any?>()
    {
        override fun matches(prev: Any?, cur: Any?) = prev == cur
    }

    object Identity : CheckStrategy<Any?>()
    {
        override fun matches(prev: Any?, cur: Any?) = prev === cur
    }

    object AlwaysUpdate : CheckStrategy<Any?>()
    {
        override fun matches(prev: Any?, cur: Any?) = false
    }

    abstract fun matches(prev: T, cur: T): Boolean
}
```

**Exam 2: VariantBlock -> BlockVariant**
Create blocks by `BlockVariant` instead of `VariantBlock`.
```kotlin
enum class TestBlock(private val serialName: String) : BlockVariant, IStringSerializable {
    BLOCK_1("block_1");

    override val state: IBlockState
        get() = GTLiteBlocks.TEST_BLOCK.getState(this)

    override fun getStack(count: Int): ItemStack = GTLiteBlocks.TEST_BLOCK.getItemVariant(this, count)
}
```

Use adapter for GregTech's blocks, must use same enum name to provide a hook for `valueOf` method.
```kotlin
enum class GTBatteryBlock : BlockVariant
{
  EMPTY_TIER_I;

  override val state: IBlockState 
      get() = MetaBlocks.BATTERY_BLOCK.getStateFromMeta(ordinal)

  override fun getStack(count: Int): ItemStack
      = MetaBlocks.BATTERY_BLOCK.getItemVariant(BlockBatteryPart.BatteryPartType.valueOf(name), count)
}
```

**Exam 3: GregTech Block Tier -> Block Attribute API**

For our block attribute:
```kotlin
val MOTOR_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("motor_casing_tier")

fun init()
{
    MOTOR_CASING_TIER.registerBlockVariants(MotorCasing::class)
}
```

For GregTech block tier adaptation (make it to our API).

```kotlin
val COIL_TIER: BlockAttributeRegistry<IHeatingCoilBlockStats>
    = BlockAttributeRegistryWrapper("CoilType", GregTechAPI.HEATING_COILS, Comparator.comparingInt { it.tier })
```

**Exam 4: Hand-written Sync -> MTE Sync**
```kotlin
class MachineEnergyDistributor(id: ResourceLocation, tier: Int) : TieredMetaTileEntity(id, tier), SyncedMetaTileEntity
{
    override val syncer: MetaTileEntitySyncer = MetaTileEntitySyncer(this)

    var isDistributeMode by syncer.syncedBoolean(true)
}
```
