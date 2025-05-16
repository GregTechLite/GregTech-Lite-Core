package magicbook.gtlitecore.api.unification.ore

import gregtech.api.unification.material.Materials
import gregtech.api.unification.ore.StoneType
import magicbook.gtlitecore.api.unification.GTLiteMaterials
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.GTLiteStoneVariantBlock
import net.minecraft.block.SoundType
import net.minecraft.block.state.IBlockState

@Suppress("MISSING_DEPENDENCY_CLASS", "unused")
class GTLiteStoneTypes
{

    companion object
    {

        @JvmStatic
        fun init()
        {

            val LIMESTONE = StoneType(12, "limestone", SoundType.STONE,
                GTLiteOrePrefix.oreLimestone, GTLiteMaterials.Limestone,
                { stoneState(GTLiteStoneVariantBlock.StoneType.LIMESTONE) },
                { state: IBlockState? ->
                    stonePredicate(state, GTLiteStoneVariantBlock.StoneType.LIMESTONE)
                }, false)

            val KOMATIITE = StoneType(13, "komatiite", SoundType.STONE,
                GTLiteOrePrefix.oreKomatiite, GTLiteMaterials.Komatiite,
                { stoneState(GTLiteStoneVariantBlock.StoneType.KOMATIITE) },
                { state: IBlockState? ->
                    stonePredicate(state, GTLiteStoneVariantBlock.StoneType.KOMATIITE)
                }, false)

            val GREEN_SCHIST = StoneType(14, "green_schist", SoundType.STONE,
                GTLiteOrePrefix.oreGreenSchist, GTLiteMaterials.GreenSchist,
                { stoneState(GTLiteStoneVariantBlock.StoneType.GREEN_SCHIST) },
                { state: IBlockState? ->
                    stonePredicate(state, GTLiteStoneVariantBlock.StoneType.GREEN_SCHIST)
                }, false)

            val BLUE_SCHIST = StoneType(15, "blue_schist", SoundType.STONE,
                GTLiteOrePrefix.oreBlueSchist, GTLiteMaterials.BlueSchist,
                { stoneState(GTLiteStoneVariantBlock.StoneType.BLUE_SCHIST) },
                { state: IBlockState? ->
                    stonePredicate(state, GTLiteStoneVariantBlock.StoneType.BLUE_SCHIST)
                }, false)

            val KIMBERLITE = StoneType(16, "kimberlite", SoundType.STONE,
                GTLiteOrePrefix.oreKimberlite, GTLiteMaterials.Kimberlite,
                { stoneState(GTLiteStoneVariantBlock.StoneType.KIMBERLITE) },
                { state: IBlockState? ->
                    stonePredicate(state, GTLiteStoneVariantBlock.StoneType.KIMBERLITE)
                }, false)

            val QUARTZITE = StoneType(17, "quartzite", SoundType.STONE,
                GTLiteOrePrefix.oreQuartzite, Materials.Quartzite,
                { stoneState(GTLiteStoneVariantBlock.StoneType.QUARTZITE) },
                { state: IBlockState? ->
                    stonePredicate(state, GTLiteStoneVariantBlock.StoneType.QUARTZITE)
                }, false)

            val SLATE = StoneType(18, "slate", SoundType.STONE,
                GTLiteOrePrefix.oreSlate, GTLiteMaterials.Slate,
                { stoneState(GTLiteStoneVariantBlock.StoneType.SLATE) },
                { state: IBlockState? ->
                    stonePredicate(state, GTLiteStoneVariantBlock.StoneType.SLATE)
                }, false)

            val SHALE = StoneType(19, "shale", SoundType.STONE,
                GTLiteOrePrefix.oreShale, GTLiteMaterials.Shale,
                { stoneState(GTLiteStoneVariantBlock.StoneType.SHALE) },
                { state: IBlockState? ->
                    stonePredicate(state, GTLiteStoneVariantBlock.StoneType.SHALE)
                }, false)

        }

        private fun stoneState(stoneType: GTLiteStoneVariantBlock.StoneType): IBlockState?
                = GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.SMOOTH]?.getState(stoneType)

        private fun stonePredicate(state: IBlockState?, stoneType: GTLiteStoneVariantBlock.StoneType): Boolean
        {
            val block: GTLiteStoneVariantBlock? = GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.SMOOTH]
            return state?.getBlock() === block && block?.getState(state) === stoneType
        }

    }

}