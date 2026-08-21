# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**GregTech Lite Core** is a Minecraft 1.12.2 Mod for the same name modpack **GregTech Lite**, it has written in Kotlin
language (Kotlin 2.1.0) which provides by **Forgelin Continuous** mod (a lib which use shadow jar to provide Kotlin
support for Minecraft 1.12.2).

### Buildscripts

Use **Retro Futura Gradle** as basic processing handler for Minecraft (MCP mappings `stable_39`).
- Java Language Level / Kotlin JVM Target Level: **8** (Azul platform).
- Available Java library: **ASM** (9.2), **Fastutil** (7.1.0), **Gson** (2.8.0), **Guava** (21.0).
- Available Kotlin library: **Kotlinx Coroutines** (1.9.0), **Kotlinx Serialization** (1.7.3).

Support **Mixins** (`org.spongepowered.asm.mixin`) and **MixinExtras** (`com.llamalad7.mixinextras`) by **MixinBooter** mod.

### External Sources

Browse following external sources instead of guessing APIs:

| File                         | Path                                         |
|------------------------------|----------------------------------------------|
| Minecraft Source Code        | `build/rfg/minecraft-src`                    |
| GregTech Mod                 | `libs/gregtech-1.12.2-<version>.jar`         |
| GregTech Mod Source Code     | `libs/gregtech-1.12.2-<version>-sources.jar` |
| Modular UI 2 Mod Source Code | `libs/modularui-3.0.6-sources.jar`           |
| Morphism Lib Mod             | `libs/morphismlib-1.12.2-1.0.0.jar`          |
| Morphism Lib Mod Source Code | `libs/morphismlib-1.12.2-1.0.0-sources.jar`  |

where `<version>` means a version token for corresponding jar, e.g. `#master-0072` means git branch name `master` and
pull request 72 (Use to mark the dependency version, no need to refer to the git of this project).

### Proper Noun

| Proper Noun                                                                                                                                         | Mean                                                                                                                  |
|-----------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| GregTech / GT / GTCEu / GTCEU                                                                                                                       | A technology and industry mod for Minecraft, the forked version of GTCE / GregTech Community Edition.                 |
| Modular UI / Modular UI 2 / MUI / MUI2                                                                                                              | A ui library for Minecraft which GregTech mod and our repository depends on.                                          |
| Morphism Lib / Mor Lib                                                                                                                              | A library for Minecraft.                                                                                              |
| JEI / Just Enough Items / HEI / Had Enough Items                                                                                                    | An information display mod for Minecraft, we use HEI (the forked version of JEI in 1.12.2) for dependencies.          |
| TOP / The One Probe                                                                                                                                 | A HUD style information display mod for Minecraft.                                                                    |
| AE2 / AppEng / Applied Energistics / Applied Enerigstics 2 / AE2UEL / Applied Energistics 2 Unofficial Extended Life / AE2 Unofficial Extended Life | An automation and technology mod for Minecraft, we use AE2UEL (the forked version of AE2 in 1.12.2) for dependencies. |

## API Usage Guide

At the beginning and most importantly, we **use Kotlin language to write everything** and **only use Java language to write mixins**.

### Code Conventions

1. Use Kotlin syntax instead of Java syntax, e.g. Java Stream API -> Kotlin Sequence, Java `StringBuilder` / String Operation
   -> Kotlin `buildString` / Kotlin String Operation, Java Reflection -> Kotlin Reflect.
2. Use Kotlin constructions instead of constructions in some Java or its external libraries, e.g. Guava `ImmutableList` -> Kotlin `List`,
   Apache Commons `Pair` -> Kotlin `Pair`. The construction in these libraries is only allowed to be used when hard required.
3. Use Kotlin extension method instead of some redundant methods.
4. Use Kotlin design pattern instead of Java design pattern, e.g. Java Builder Pattern -> Kotlin DSL Pattern.
5. Use sealed class + contravariant singleton instead of generic enum (not allowed in Kotlin) or some similar constructions.
   For example, here a strategy class `Strategy<in T>`, we exposing it with `Strategy<Any?>` singletons.
6. Use `MoreCollections` (`gregtechlite.gtlitecore.api.collection.MoreCollections`) if there required high performance in current,
   it is the Kotlin extensions for **FastUtil** and some Java collection.
7. Use certain package name and put all files in correct package name, split it by its domain by default.
8. Use `BlockVariant` (`gregtechlite.gtlitecore.api.block.variant.BlockVariant`) and its related system but not `MetaBlock` / `VariantBlock` by GregTech.
9. Use `BlockAttributeRegistry` (`gregtechlite.gtlitecore.api.block.attribute.BlockAttributeRegistry`) and its related system (registered in `GTLiteAPI`) but not block tier registration in `GregTechAPI` by GregTech.
10. Use `MetaTileEntitySyncer` (`gregtechlite.gtlitecore.api.metatileentity.sync`) and its related system by default, not hand-written `writeToNBT` / `readFromNBT`, e.t.c.
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