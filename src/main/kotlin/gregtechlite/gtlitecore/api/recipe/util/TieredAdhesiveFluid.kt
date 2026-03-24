package gregtechlite.gtlitecore.api.recipe.util

import gregtech.api.GTValues.*
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import net.minecraftforge.fluids.FluidStack

enum class TieredAdhesiveFluid(val material: Material, val costOffset: Int = 0)
{
    // Glue recipes for ULV-LV.
    LV_OR_BELOW(Materials.Glue),

    // Polyethylene (PE) recipes for HV and below.
    HV_OR_BELOW(Materials.Polyethylene, 4),

    // Polytetrafluoroethylene (PTFE) recipes for LuV and below.
    LUV_OR_BELOW(Materials.Polytetrafluoroethylene, 3),

    // Polybenzimidazole (PBI) recipes for UV and below.
    UV_OR_BELOW(Materials.Polybenzimidazole),

    // Kevlar recipes for UEV and below.
    UEV_OR_BELOW(GTLiteMaterials.Kevlar, -1),

    // Fullerene Polymer Matrix (FPM) recipes for UXV and below.
    UXV_OR_BELOW(GTLiteMaterials.FullerenePolymerMatrix, -2),

    // Cosmic Fabric recipes for MAX and below.
    MAX_OR_BELOW(GTLiteMaterials.CosmicFabric, -4);

    companion object
    {
        /**
         * Determines the appropriate [TieredAdhesiveFluid] based on the given voltage tier.
         *
         * @param tier Voltage tier.
         */
        @JvmStatic
        fun fromTier(tier: Int): TieredAdhesiveFluid = when
        {
            tier <= LV  -> LV_OR_BELOW
            tier <= HV  -> HV_OR_BELOW
            tier <= LuV -> LUV_OR_BELOW
            tier <= UV  -> UV_OR_BELOW
            tier <= UEV -> UEV_OR_BELOW
            tier <= UXV -> UXV_OR_BELOW
            tier <= MAX -> MAX_OR_BELOW
            else        -> throw Error("Invalid tier")
        }

        /**
         * Determines the appropriate [gregtech.api.unification.material.Material] for adhesive fluid recipes based on the given voltage tier.
         *
         * @param tier Voltage tier.
         */
        @JvmStatic
        fun materialFromTier(tier: Int): Material = fromTier(tier).material

        /**
         * Determines the appropriate [net.minecraftforge.fluids.FluidStack] for adhesive fluid recipes based on the given voltage tier.
         *
         * The fluid amount is determined by the voltage tier.
         *
         * @param tier Voltage tier.
         */
        @JvmStatic
        fun fluidStackFromTier(tier: Int): FluidStack = materialFromTier(tier).getFluid(
                if (tier > LV)
                    getGTHatchFluidAmount(tier + fromTier(tier).costOffset)
                else if (tier == ULV) 250 else 500
        )


        /**
         * Generates a list of [FluidStack] for adhesive fluid recipes based on the given voltage tier.
         *
         * This method is useful to generate IO buses/hatches recipe required fluid stacks for each tier,
         * as it will include all fluids from the current tier and above, ensuring compatibility with
         * higher-tier adhesive recipes.
         *
         * @param tier The voltage tier for which to generate the adhesive fluid stacks.
         */
        @JvmStatic
        fun generateRecipeFluidStacks(tier: Int): ArrayList<FluidStack>
        {
            val fluidStacks = ArrayList<FluidStack>()
            val current = fromTier(tier)

            // Add all fluids from current tier and above
            entries.dropWhile { it != current }
                .forEach {
                    fluidStacks.add(
                            it.material.getFluid(
                                    if (tier > LV)
                                        getGTHatchFluidAmount(tier + it.costOffset)
                                    else if (tier == ULV) 250 else 500
                            )
                    )
                }

            return fluidStacks
        }

        /**
         * Gets fluid amount of io hatch consumed by its tier.
         *
         * @param offsetTier The offset of voltage tier.
         */
        @JvmStatic
        fun getGTHatchFluidAmount(offsetTier: Int): Int = when (offsetTier)
        {
            ULV  -> 4
            LV   -> L / 16 // 9
            MV   -> L / 8  // 18
            HV   -> L / 4  // 36
            EV   -> L / 2  // 72
            IV   -> L      // 144
            LuV  -> L * 2  // 288
            ZPM  -> L * 3  // 432
            UV   -> L * 4  // 576
            UHV  -> L * 5  // 720
            UEV  -> L * 6  // 864
            UIV  -> L * 7  // 1008
            UXV  -> L * 8  // 1152
            OpV  -> L * 9  // 1296
            MAX  -> L * 10 // 1440
            else -> 1
        }
    }

}