package gregtechlite.gtlitecore.common.metatileentity.multiblock.generator

import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.value.sync.BooleanSyncValue
import com.cleanroommc.modularui.widgets.ToggleButton
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VNF
import gregtech.api.capability.IRotorHolder
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.ROTOR_HOLDER
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.recipes.RecipeMap
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.metatileentities.multi.electric.generator.LargeTurbineWorkableHandler
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine
import gregtechlite.gtlitecore.api.capability.RotorHandler
import gregtechlite.gtlitecore.api.capability.RotorMode
import gregtechlite.gtlitecore.api.data.Schema
import gregtechlite.gtlitecore.api.data.handler.CheckStrategy
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import gregtechlite.gtlitecore.api.metatileentity.sync.MetaTileEntitySyncer
import gregtechlite.gtlitecore.api.metatileentity.sync.SyncedMetaTileEntity
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockMegaTurbine(id: ResourceLocation,
                            recipeMap: RecipeMap<*>,
                            tier: Int,
                            casingState: IBlockState,
                            gearboxState: IBlockState,
                            casingRenderer: ICubeRenderer,
                            frontOverlay: ICubeRenderer,
                            hasMufflerHatch: Boolean)
    : MetaTileEntityLargeTurbine(id, recipeMap, tier, casingState, gearboxState, casingRenderer, hasMufflerHatch, frontOverlay),
    RotorHandler, SyncedMetaTileEntity
{
    override val syncer: MetaTileEntitySyncer = MetaTileEntitySyncer(this)

    override var mode by syncer.synced(Schema("mode", RotorMode.COMMON, CheckStrategy.Equals,
        { tag, key, value -> tag.setByte(key, value.ordinal.toByte()) },
        { tag, key -> RotorMode.entries.toTypedArray().getOrElse(tag.getByte(key).toInt()) { RotorMode.COMMON } },
        { buf, value -> buf.writeByte(value.ordinal) },
        { buf -> RotorMode.entries.getOrElse(buf.readByte().toInt()) { RotorMode.COMMON } }))

    override val rotorHolders: List<IRotorHolder>?
        get() = getAbilities(ROTOR_HOLDER).takeIf { it.isNotEmpty() }

    init
    {
        recipeMapWorkable = MegaTurbineWorkableHandler(this, tier)
        recipeMapWorkable.maximumOverclockVoltage = V[tier]
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockMegaTurbine(metaTileEntityId, recipeMap, tier, casingState, gearboxState, casingRenderer, frontOverlay, hasMufflerHatch)

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCCCC", "CRCACRC", "CCCACCC", "CCCACCC", "CRCACRC", "CCCACCC", "CCCACCC", "CRCACRC", "CCCCCCC")
        .aisle("CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC")
        .aisle("CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC")
        .aisle("CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC")
        .aisle("CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC")
        .aisle("CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC", "CCCCCCC", "CGCCCGC", "CCCCCCC")
        .aisle("CCCCCCC", "CRCACRC", "CCCACCC", "CCCACCC", "CRCSCRC", "CCCACCC", "CCCACCC", "CRCACRC", "CCCCCCC")
        .where('S', selfPredicate())
        .where('C', states(casingState))
        .where('G', states(gearboxState))
        .where('R', abilities(ROTOR_HOLDER))
        .where('A', states(casingState)
            .or(abilities(OUTPUT_ENERGY)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(1))
            .or(abilities(OUTPUT_LASER)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(0))
            .or(abilities(MAINTENANCE_HATCH)
                    .setExactLimit(1))
            .or(abilities(IMPORT_ITEMS)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(1))
            .or(abilities(IMPORT_FLUIDS)
                    .setMinGlobalLimited(1)
                    .setPreviewCount(1))
            .or(abilities(EXPORT_FLUIDS)
                    .setMinGlobalLimited(1)
                    .setPreviewCount(1))
            .or(abilities(MUFFLER_HATCH)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(0)))
        .build()

    // @formatter:on

    override fun getMaxVoltage(): Long
    {
        val maxProduction = recipeMapWorkable.maxVoltage
        val currentProduction = (recipeMapWorkable as MegaTurbineWorkableHandler).boostProduction(maxProduction)
        return if (isActive && currentProduction < maxProduction) recipeMapWorkable.maxVoltage else 0
    }

    override fun isRotorFaceFree(): Boolean
    {
        val holders = rotorHolders ?: return false
        if (!isStructureFormed) return false
        for (rotorHolder in holders)
            if (!rotorHolder.isFrontFaceFree) return false
        return true
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        tooltip.add(I18n.format("gregtech.universal.tooltip.base_production_eut", V[tier] * 2 * 16))
        tooltip.add(I18n.format("gregtech.multiblock.turbine.efficiency_tooltip", VNF[tier]))
        tooltip.add(I18n.format("gtlitecore.machine.mega_turbine.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.mega_turbine.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_turbine.autofill"))
    }

    override fun createUIFactory(): MultiblockUIFactory = super.createUIFactory()
        .createFlexButton { _, guiSyncManager ->
            val modeSync = BooleanSyncValue(::getRotorMode, ::setRotorMode)
            return@createFlexButton ToggleButton()
                .size(18)
                .disableHoverBackground()
                .overlay(true, GTLiteMuiTextures.BUTTON_HIGH_SPEED_MODE[1])
                .overlay(false, GTLiteMuiTextures.BUTTON_HIGH_SPEED_MODE[0])
                .addTooltip(true, IKey.lang("gtlitecore.machine.mega_turbine.mode.enabled"))
                .addTooltip(false, IKey.lang("gtlitecore.machine.mega_turbine.mode.disabled"))
                .value(modeSync)
        }

    private fun getRotorMode(): Boolean = mode != RotorMode.COMMON

    private fun setRotorMode(mode: Boolean)
    {
        this.mode = if (!mode) RotorMode.COMMON else RotorMode.HIGH_SPEED
    }

    private inner class MegaTurbineWorkableHandler(mte: RecipeMapMultiblockController, tier: Int) : LargeTurbineWorkableHandler(mte, tier)
    {
        private val outputEnergy = V[tier] * 2 * 16

        override fun getMaxVoltage(): Long
        {
            val turbine = metaTileEntity as RotorHandler
            val rotorHolders = turbine.rotorHolders
            if (rotorHolders != null && rotorHolders[0].hasRotor())
            {
                if (turbine.mode == RotorMode.COMMON)
                    return outputEnergy * rotorHolders[0].totalPower / 100
                return (outputEnergy * 3) * rotorHolders[0].totalPower / 100
            }
            return 0
        }

        // Force support access for protected method in java side, maybe we can fix it later?
        @Suppress("RedundantVisibilityModifier")
        public override fun boostProduction(production: Long): Long = super.boostProduction(production)
    }
}
