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
                { StoneState(GTLiteStoneVariantBlock.StoneType.LIMESTONE) },
                { state: IBlockState? ->
                    StonePredicate(state, GTLiteStoneVariantBlock.StoneType.LIMESTONE)
                }, true)

            val KOMATIITE = StoneType(13, "komatiite", SoundType.STONE,
                GTLiteOrePrefix.oreKomatiite, GTLiteMaterials.Komatiite,
                { StoneState(GTLiteStoneVariantBlock.StoneType.KOMATIITE) },
                { state: IBlockState? ->
                    StonePredicate(state, GTLiteStoneVariantBlock.StoneType.KOMATIITE)
                }, true)

            val GREEN_SCHIST = StoneType(14, "green_schist", SoundType.STONE,
                GTLiteOrePrefix.oreGreenSchist, GTLiteMaterials.GreenSchist,
                { StoneState(GTLiteStoneVariantBlock.StoneType.GREEN_SCHIST) },
                { state: IBlockState? ->
                    StonePredicate(state, GTLiteStoneVariantBlock.StoneType.GREEN_SCHIST)
                }, true)

            val BLUE_SCHIST = StoneType(15, "blue_schist", SoundType.STONE,
                GTLiteOrePrefix.oreBlueSchist, GTLiteMaterials.BlueSchist,
                { StoneState(GTLiteStoneVariantBlock.StoneType.BLUE_SCHIST) },
                { state: IBlockState? ->
                    StonePredicate(state, GTLiteStoneVariantBlock.StoneType.BLUE_SCHIST)
                }, true)

            val KIMBERLITE = StoneType(16, "kimberlite", SoundType.STONE,
                GTLiteOrePrefix.oreKimberlite, GTLiteMaterials.Kimberlite,
                { StoneState(GTLiteStoneVariantBlock.StoneType.KIMBERLITE) },
                { state: IBlockState? ->
                    StonePredicate(state, GTLiteStoneVariantBlock.StoneType.KIMBERLITE)
                }, true)

            val QUARTZITE = StoneType(17, "quartzite", SoundType.STONE,
                GTLiteOrePrefix.oreQuartzite, Materials.Quartzite,
                { StoneState(GTLiteStoneVariantBlock.StoneType.QUARTZITE) },
                { state: IBlockState? ->
                    StonePredicate(state, GTLiteStoneVariantBlock.StoneType.QUARTZITE)
                }, true)

            val SLATE = StoneType(18, "slate", SoundType.STONE,
                GTLiteOrePrefix.oreSlate, GTLiteMaterials.Slate,
                { StoneState(GTLiteStoneVariantBlock.StoneType.SLATE) },
                { state: IBlockState? ->
                    StonePredicate(state, GTLiteStoneVariantBlock.StoneType.SLATE)
                }, true)

            val SHALE = StoneType(19, "shale", SoundType.STONE,
                GTLiteOrePrefix.oreShale, GTLiteMaterials.Shale,
                { StoneState(GTLiteStoneVariantBlock.StoneType.SHALE) },
                { state: IBlockState? ->
                    StonePredicate(state, GTLiteStoneVariantBlock.StoneType.SHALE)
                }, true)

        }

        private fun StoneState(stoneType: GTLiteStoneVariantBlock.StoneType): IBlockState?
                = GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.SMOOTH]?.getState(stoneType)

        private fun StonePredicate(state: IBlockState?, stoneType: GTLiteStoneVariantBlock.StoneType): Boolean
        {
            val block: GTLiteStoneVariantBlock? = GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.SMOOTH]
            return state?.getBlock() === block && block?.getState(state) === stoneType
        }

    }

}