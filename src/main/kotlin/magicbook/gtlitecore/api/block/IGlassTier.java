package magicbook.gtlitecore.api.block;

import gregtech.api.GTValues;
import gregtech.api.util.GTUtility;

/**
 * Used Java Interface because this interface has some situation which used in mixin environments.
 */
public interface IGlassTier extends IBlockTier
{

    int getGlassTier();

    @Override
    default Object getTier()
    {
        return this.getGlassTier();
    }

    default String getVoltageNameByTier()
    {
        return GTValues.VN[this.getGlassTier()];
    }

    default String getColoredVoltageNameByTier()
    {
        return GTValues.VNF[this.getGlassTier()];
    }

    default long getVoltageByTier()
    {
        return GTValues.V[this.getGlassTier()];
    }

    default int getVoltageTierByTier()
    {
        return GTUtility.getTierByVoltage(GTValues.V[this.getGlassTier()]);
    }

}
