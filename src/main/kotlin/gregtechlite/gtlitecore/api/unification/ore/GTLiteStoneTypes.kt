package gregtechlite.gtlitecore.api.unification.ore

import gregtech.api.unification.material.Materials
import gregtech.api.unification.ore.StoneType
import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import gregtechlite.gtlitecore.api.unification.ore.StoneTypeDSL.Companion.of
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

        LIMESTONE = of(12, "limestone")
        {
            sound = SoundType.STONE
            prefix = GTLiteOrePrefix.oreLimestone
            material = GTLiteMaterials.Limestone
            state = { stoneState(GTLiteStoneVariantBlock.StoneType.LIMESTONE) }
            predicate = { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.LIMESTONE) }
        }

        KOMATIITE = of(13, "komatiite")
        {
            sound = SoundType.STONE
            prefix = GTLiteOrePrefix.oreKomatiite
            material = GTLiteMaterials.Komatiite
            state = { stoneState(GTLiteStoneVariantBlock.StoneType.KOMATIITE) }
            predicate = { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.KOMATIITE) }
        }

        GREEN_SCHIST = of(14, "green_schist")
        {
            sound = SoundType.STONE
            prefix = GTLiteOrePrefix.oreGreenSchist
            material = GTLiteMaterials.GreenSchist
            state = { stoneState(GTLiteStoneVariantBlock.StoneType.GREEN_SCHIST) }
            predicate = { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.GREEN_SCHIST) }
        }

        BLUE_SCHIST = of(15, "blue_schist")
        {
            sound = SoundType.STONE
            prefix = GTLiteOrePrefix.oreBlueSchist
            material = GTLiteMaterials.BlueSchist
            state = { stoneState(GTLiteStoneVariantBlock.StoneType.BLUE_SCHIST) }
            predicate = { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.BLUE_SCHIST) }
        }

        KIMBERLITE = of(16, "kimberlite")
        {
            sound = SoundType.STONE
            prefix = GTLiteOrePrefix.oreKimberlite
            material = GTLiteMaterials.Kimberlite
            state = { stoneState(GTLiteStoneVariantBlock.StoneType.KIMBERLITE) }
            predicate = { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.KIMBERLITE) }
        }

        QUARTZITE = of(17, "quartzite")
        {
            sound = SoundType.STONE
            prefix = GTLiteOrePrefix.oreQuartzite
            material = Materials.Quartzite
            state = { stoneState(GTLiteStoneVariantBlock.StoneType.QUARTZITE) }
            predicate = { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.QUARTZITE) }
        }

        SLATE = of(18, "slate")
        {
            sound = SoundType.STONE
            prefix = GTLiteOrePrefix.oreSlate
            material = GTLiteMaterials.Slate
            state = { stoneState(GTLiteStoneVariantBlock.StoneType.SLATE) }
            predicate = { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.SLATE) }
        }

        SHALE = of(19, "shale")
        {
            sound = SoundType.STONE
            prefix = GTLiteOrePrefix.oreShale
            material = GTLiteMaterials.Shale
            state = { stoneState(GTLiteStoneVariantBlock.StoneType.SHALE) }
            predicate = { stonePredicate(it, GTLiteStoneVariantBlock.StoneType.SHALE) }
        }

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