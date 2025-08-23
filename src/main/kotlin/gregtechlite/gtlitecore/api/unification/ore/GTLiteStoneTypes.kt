package gregtechlite.gtlitecore.api.unification.ore

import gregtech.api.unification.material.Materials
import gregtech.api.unification.ore.StoneType
import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import gregtechlite.gtlitecore.api.unification.material.builder.StoneTypeBuilder
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import gregtechlite.gtlitecore.common.block.GTLiteStoneVariantBlock
import net.minecraft.block.SoundType
import net.minecraft.block.state.IBlockState

object GTLiteStoneTypes
{

    lateinit var LIMESTONE: StoneType
    lateinit var KOMATIITE: StoneType
    lateinit var GREEN_SCHIST: StoneType
    lateinit var BLUE_SCHIST: StoneType
    lateinit var KIMBERLITE: StoneType
    lateinit var QUARTZITE: StoneType
    lateinit var SLATE: StoneType
    lateinit var SHALE: StoneType

    fun init()
    {

        LIMESTONE = StoneTypeBuilder.builder(12, "limestone")
            .sound(SoundType.STONE)
            .prefix(GTLiteOrePrefix.oreLimestone)
            .material(GTLiteMaterials.Limestone)
            .state(stoneState(GTLiteStoneVariantBlock.StoneType.LIMESTONE))
            .condition { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.LIMESTONE) }
            .build()

        KOMATIITE = StoneTypeBuilder.builder(13, "komatiite")
            .sound(SoundType.STONE)
            .prefix(GTLiteOrePrefix.oreKomatiite)
            .material(GTLiteMaterials.Komatiite)
            .state(stoneState(GTLiteStoneVariantBlock.StoneType.KOMATIITE))
            .condition { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.KOMATIITE) }
            .build()

        GREEN_SCHIST = StoneTypeBuilder.builder(14, "green_schist")
            .sound(SoundType.STONE)
            .prefix(GTLiteOrePrefix.oreGreenSchist)
            .material(GTLiteMaterials.GreenSchist)
            .state(stoneState(GTLiteStoneVariantBlock.StoneType.GREEN_SCHIST))
            .condition { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.GREEN_SCHIST) }
            .build()

        BLUE_SCHIST = StoneTypeBuilder.builder(15, "blue_schist")
            .sound(SoundType.STONE)
            .prefix(GTLiteOrePrefix.oreBlueSchist)
            .material(GTLiteMaterials.BlueSchist)
            .state(stoneState(GTLiteStoneVariantBlock.StoneType.BLUE_SCHIST))
            .condition { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.BLUE_SCHIST) }
            .build()

        KIMBERLITE = StoneTypeBuilder.builder(16, "kimberlite")
            .sound(SoundType.STONE)
            .prefix(GTLiteOrePrefix.oreKimberlite)
            .material(GTLiteMaterials.Kimberlite)
            .state(stoneState(GTLiteStoneVariantBlock.StoneType.KIMBERLITE))
            .condition { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.KIMBERLITE) }
            .build()

        QUARTZITE = StoneTypeBuilder.builder(17, "quartzite")
            .sound(SoundType.STONE)
            .prefix(GTLiteOrePrefix.oreQuartzite)
            .material(Materials.Quartzite)
            .state(stoneState(GTLiteStoneVariantBlock.StoneType.QUARTZITE))
            .condition { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.QUARTZITE) }
            .build()

        SLATE = StoneTypeBuilder.builder(18, "slate")
            .sound(SoundType.STONE)
            .prefix(GTLiteOrePrefix.oreSlate)
            .material(GTLiteMaterials.Slate)
            .state(stoneState(GTLiteStoneVariantBlock.StoneType.SLATE))
            .condition { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.SLATE) }
            .build()

        SHALE = StoneTypeBuilder.builder(19, "shale")
            .sound(SoundType.STONE)
            .prefix(GTLiteOrePrefix.oreShale)
            .material(GTLiteMaterials.Shale)
            .state(stoneState(GTLiteStoneVariantBlock.StoneType.SHALE))
            .condition { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.SHALE) }
            .build()

        if (ConfigHolder.worldgen.allUniqueStoneTypes)
        {
            LIMESTONE.shouldBeDroppedAsItem = true
            KOMATIITE.shouldBeDroppedAsItem = true
            GREEN_SCHIST.shouldBeDroppedAsItem = true
            BLUE_SCHIST.shouldBeDroppedAsItem = true
            KIMBERLITE.shouldBeDroppedAsItem = true
            QUARTZITE.shouldBeDroppedAsItem = true
            SLATE.shouldBeDroppedAsItem = true
            SHALE.shouldBeDroppedAsItem = true
        }

    }

    // region Custom State Supplier and Predicate

    private fun stoneState(stoneType: GTLiteStoneVariantBlock.StoneType): IBlockState
    {
        return GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.SMOOTH]!!.getState(stoneType)
    }

    private fun stonePredicate(state: IBlockState?, stoneType: GTLiteStoneVariantBlock.StoneType): Boolean
    {
        val block = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.SMOOTH]
        return state?.getBlock() === block && block?.getState(state) === stoneType
    }

    // endregion

}