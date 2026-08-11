package gregtechlite.gtlitecore.common.metatileentity

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.network.sync.DescSynced
import gregtechlite.gtlitecore.api.network.sync.Persisted
import gregtechlite.gtlitecore.api.network.sync.UpdateListener
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.jetbrains.annotations.TestOnly

@TestOnly
class MetaTileEntityTestManaged(id: ResourceLocation) : MetaTileEntityTestManagedBase(id)
{
    @field:Persisted
    @field:DescSynced
    @field:UpdateListener(name = "onTestIntChanged")
    var testInt: Int = 0

    @field:Persisted
    @field:DescSynced
    var testLong: Long = 0L

    @field:Persisted
    @field:DescSynced
    var testBool: Boolean = false

    @field:Persisted
    @field:DescSynced
    var testString: String = "init"

    @field:Persisted
    @field:DescSynced
    var testFacing: EnumFacing = EnumFacing.UP

    @field:DescSynced
    var runtimeOnly: Int = 0

    @field:Persisted
    var diskOnly: String = "persisted-only"

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MetaTileEntityTestManaged(metaTileEntityId)

    override fun update()
    {
        super.update()
        if (world != null && !world.isRemote)
        {
            if (offsetTimer % (2 * SECOND) == 0L)
            {
                testInt += 1
                testBool = !testBool
                testString = "tick-$offsetTimer"
                testFacing = testFacing.rotateAround(EnumFacing.Axis.Y)
                // Inherited managed fields (declared on the base class).
                inheritedInt += 1
                inheritedMode = (inheritedMode + 1) % 4
                LOGGER.debug("Managed server update @ {} mutating: testInt={} testBool={} testString={} testFacing={} inheritedInt={} inheritedMode={}",
                    pos, testInt, testBool, testString, testFacing, inheritedInt, inheritedMode)
            }
            runtimeOnly = ((offsetTimer / SECOND) % 1000L).toInt()
        }
    }

    fun onTestIntChanged(newVal: Int, oldVal: Int)
    {
        LOGGER.debug("Managed onTestIntChanged @ {}: {}->{}", pos, oldVal, newVal)
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
    }
}
