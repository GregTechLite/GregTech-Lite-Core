package gregtechlite.gtlitecore.common.block.base

import gregtech.api.items.toolitem.ToolClasses
import gregtech.api.recipes.ModHandler
import gregtech.client.model.MaterialStateMapper
import gregtech.client.model.modelfactories.MaterialBlockModelLoader
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockMaterialBase
import gregtech.common.blocks.properties.PropertyMaterial
import gregtech.common.creativetab.GTCreativeTabs
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconType
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLiving
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("unused")
abstract class BlockMetalWall private constructor() : BlockMaterialBase(Material.IRON)
{

    init
    {
        this.setTranslationKey("wall_gt")
        this.setHardness(4.0f)
        this.setResistance(8.0f)
        this.setCreativeTab(GTCreativeTabs.TAB_GREGTECH_MATERIALS)
        this.setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)
    }

    companion object
    {
        fun create(materials: Array<gregtech.api.unification.material.Material>): BlockMetalWall
        {
            val property = PropertyMaterial.create("variant", materials)
            return object : BlockMetalWall()
            {
                override fun getVariantProperty(): PropertyMaterial = property
            }
        }
    }

    override fun getHarvestTool(state: IBlockState): String
    {
        val material = getGtMaterial(state)
        if (ModHandler.isMaterialWood(material)) return ToolClasses.AXE
        return ToolClasses.WRENCH
    }

    override fun getSoundType(state: IBlockState,
                              world: World,
                              pos: BlockPos,
                              entity: Entity?): SoundType
    {
        val material = getGtMaterial(state)
        if (ModHandler.isMaterialWood(material))
            return SoundType.WOOD
        return SoundType.METAL
    }

    fun getSoundType(stack: ItemStack): SoundType
    {
        val material = getGtMaterial(stack)
        if (ModHandler.isMaterialWood(material))
            return SoundType.WOOD
        return SoundType.METAL
    }

    override fun getHarvestLevel(state: IBlockState) = 1

    @Deprecated("Deprecated in Java")
    override fun getMaterial(state: IBlockState): Material
    {
        val material = getGtMaterial(state)
        if (ModHandler.isMaterialWood(material))
            return Material.WOOD
        return super.getMaterial(state)
    }

    override fun canCreatureSpawn(state: IBlockState,
                                  world: IBlockAccess,
                                  pos: BlockPos,
                                  type: EntityLiving.SpawnPlacementType) = false

    override fun addInformation(stack: ItemStack,
                                worldIn: World?,
                                tooltip: MutableList<String?>,
                                flagIn: ITooltipFlag
    )
    {
        super.addInformation(stack, worldIn, tooltip, flagIn)
        if (ConfigHolder.misc.debug)
            tooltip.add("MetaItem Id: wallGt" + getGtMaterial(stack).toCamelCaseString())
    }

    @SideOnly(Side.CLIENT)
    fun onModelRegister()
    {
        ModelLoader.setCustomStateMapper(this,
                                         MaterialStateMapper(GTLiteMaterialIconType.wallGt) { s -> getGtMaterial(s).materialIconSet })
        for (state in getBlockState().validStates)
        {
            ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(this), getMetaFromState(state),
                MaterialBlockModelLoader.registerItemModel(
                    GTLiteMaterialIconType.wallGt,
                    getGtMaterial(state).materialIconSet
                )
            )
        }
    }

}