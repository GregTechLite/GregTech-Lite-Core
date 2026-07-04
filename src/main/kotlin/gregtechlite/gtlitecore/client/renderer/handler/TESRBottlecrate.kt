package gregtechlite.gtlitecore.client.renderer.handler

import gregtechlite.gtlitecore.common.block.BlockBottlecrate
import gregtechlite.gtlitecore.common.tileentity.Bottlecrate
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.BOTTLE_HEIGHT
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.BOTTLE_LENGTH
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.BOTTLE_WIDTH
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.CAP_HEIGHT
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.CAP_LENGTH
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.CAP_WIDTH
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.DEPTH_OFFSET
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.FLUID_HEIGHT
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.FLUID_LENGTH
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.FLUID_MARGIN
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.FLUID_WIDTH
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.NECK_HEIGHT
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.NECK_LENGTH
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.NECK_WIDTH
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.getPhysIndex
import gregtechlite.gtlitecore.common.util.BottlecrateUtils.getStartPoint
import net.minecraft.block.BlockHorizontal.FACING
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.RenderHelper
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.opengl.GL11



@SideOnly(Side.CLIENT)
class TESRBottlecrate : TileEntitySpecialRenderer<Bottlecrate>(){

    val TEXTURE = ResourceLocation("gtlitecore","textures/shaders/bottle_in_crate.png")

    override fun render(
        te: Bottlecrate,
        x: Double,
        y: Double,
        z: Double,
        partialTicks: Float,
        destroyStage: Int,
        alpha: Float
    ) {
        val state = te.world.getBlockState(te.pos)
        val facing = state.getValue(FACING)

        te.getInventory().forEachIndexed { i,bottle->
            if (!bottle.isEmpty){
                val physIndex = getPhysIndex(i,facing)
                renderBottleToSlot(x,y,z,physIndex,bottle)
        } }

    }


    private fun drawBox(bf: BufferBuilder,w:Double,h:Double,l:Double,uMin: Double,vMin:Double,uMax:Double,vMax:Double){
        bf.pos(.0, .0, .0).tex(uMin, vMin).endVertex()
        bf.pos(w, .0, .0).tex(uMax, vMin).endVertex()
        bf.pos(w, h, .0).tex(uMax, vMax).endVertex()
        bf.pos(.0, h, .0).tex(uMin, vMax).endVertex()

        bf.pos(w, .0, l).tex(uMin, vMin).endVertex()
        bf.pos(.0, .0, l).tex(uMax, vMin).endVertex()
        bf.pos(.0, h, l).tex(uMax, vMax).endVertex()
        bf.pos(w, h, l).tex(uMin, vMax).endVertex()

        bf.pos(.0, .0, l).tex(uMin, vMin).endVertex()
        bf.pos(.0, .0, .0).tex(uMax, vMin).endVertex()
        bf.pos(.0, h, .0).tex(uMax, vMax).endVertex()
        bf.pos(.0, h, l).tex(uMin, vMax).endVertex()

        bf.pos(w, .0, .0).tex(uMin, vMin).endVertex()
        bf.pos(w, .0, l).tex(uMax, vMin).endVertex()
        bf.pos(w, h, l).tex(uMax, vMax).endVertex()
        bf.pos(w, h, .0).tex(uMin, vMax).endVertex()
    }

    private fun drawBottomandTop(bf: BufferBuilder,w:Double,h:Double,l:Double,uMin: Double,vMin:Double,uMax:Double,vMax:Double){

        bf.pos(.0, .0, .0).tex(uMin, vMin).endVertex()
        bf.pos(.0, .0, l).tex(uMin, vMax).endVertex()
        bf.pos(w, .0, l).tex(uMax, vMax).endVertex()
        bf.pos(w, .0, .0).tex(uMax, vMin).endVertex()

        bf.pos(.0, h, .0).tex(uMin, vMin).endVertex()
        bf.pos(.0, h, l).tex(uMin, vMax).endVertex()
        bf.pos(w, h, l).tex(uMax, vMax).endVertex()
        bf.pos(w, h, .0).tex(uMax, vMin).endVertex()

    }

    private fun drawBottle(bx: Double, by: Double, bz: Double){
        GlStateManager.pushMatrix()
        GlStateManager.translate(bx+DEPTH_OFFSET, by, bz+DEPTH_OFFSET)

        this.bindTexture(TEXTURE)

        RenderHelper.disableStandardItemLighting()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(
            GlStateManager.SourceFactor.SRC_ALPHA,
            GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA
        )
        GlStateManager.disableCull()


        val tessellator = Tessellator.getInstance()
        val bf = tessellator.buffer

        //draw bottle
        bf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX)
        drawBox(bf,BOTTLE_WIDTH,BOTTLE_HEIGHT,BOTTLE_LENGTH,.0,1.0,4.0/16.0,5.0/16.0)
        tessellator.draw()

        //draw neck
        GlStateManager.translate((BOTTLE_WIDTH-NECK_WIDTH)/2, BOTTLE_HEIGHT, (BOTTLE_LENGTH-NECK_LENGTH)/2)
        bf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX)
        drawBox(bf,NECK_WIDTH,NECK_HEIGHT,NECK_LENGTH,2.0/16.0,5.0/16.0,4.0/16.0,3.0/16.0)
        tessellator.draw()

        //draw cap
        GlStateManager.translate((NECK_WIDTH-CAP_WIDTH)/2,NECK_HEIGHT,(NECK_LENGTH-CAP_LENGTH)/2)
        bf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX)
        drawBox(bf,CAP_WIDTH,CAP_HEIGHT,CAP_LENGTH,.0,3.0/16.0,2.0/16.0,2.0/16.0)
        drawBottomandTop(bf,CAP_WIDTH,CAP_HEIGHT,CAP_LENGTH,.0,5.0/16.0,2.0/16.0,3.0/16.0)
        tessellator.draw()

        GlStateManager.enableCull()
        GlStateManager.disableBlend()
        RenderHelper.enableStandardItemLighting()
        GlStateManager.popMatrix()
    }

    private fun drawColorCube(bf:BufferBuilder,w:Double, h: Double, l: Double, color:Int){

        val a = (color shr 24) and 0xFF
        val r = (color shr 16) and 0xFF
        val g = (color shr 8) and 0xFF
        val b = color and 0xFF

        bf.pos(.0, h, .0).color(r,g,b,a).endVertex()
        bf.pos(w, h, .0).color(r,g,b,a).endVertex()
        bf.pos(w, h, l).color(r,g,b,a).endVertex()
        bf.pos(.0, h, l).color(r,g,b,a).endVertex()

        bf.pos(.0, .0, .0).color(r,g,b,a).endVertex()
        bf.pos(.0, h, .0).color(r,g,b,a).endVertex()
        bf.pos(.0, h, l).color(r,g,b,a).endVertex()
        bf.pos(.0, .0, l).color(r,g,b,a).endVertex()

        bf.pos(w, .0, .0).color(r,g,b,a).endVertex()
        bf.pos(w, h, .0).color(r,g,b,a).endVertex()
        bf.pos(w, h, l).color(r,g,b,a).endVertex()
        bf.pos(w, .0, l).color(r,g,b,a).endVertex()

        bf.pos(.0, .0, .0).color(r,g,b,a).endVertex()
        bf.pos(w, .0, .0).color(r,g,b,a).endVertex()
        bf.pos(w, h, .0).color(r,g,b,a).endVertex()
        bf.pos(.0, h, .0).color(r,g,b,a).endVertex()

        bf.pos(.0, .0, l).color(r,g,b,a).endVertex()
        bf.pos(w, .0, l).color(r,g,b,a).endVertex()
        bf.pos(w, h, l).color(r,g,b,a).endVertex()
        bf.pos(.0, h, l).color(r,g,b,a).endVertex()

        bf.pos(.0, .0, .0).color(r,g,b,a).endVertex()
        bf.pos(w, .0, .0).color(r,g,b,a).endVertex()
        bf.pos(w, .0, l).color(r,g,b,a).endVertex()
        bf.pos(.0, .0, l).color(r,g,b,a).endVertex()

    }

    private fun drawFluid(x:Double,y:Double,z:Double,h:Double,color: Int){
        GlStateManager.pushMatrix()
        GlStateManager.translate(x,y,z)

        RenderHelper.disableStandardItemLighting()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
        GlStateManager.disableCull()
        GlStateManager.disableTexture2D()

        val tessellator = Tessellator.getInstance()
        val bf = tessellator.buffer
        bf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR)
        drawColorCube(bf,FLUID_WIDTH,h,FLUID_LENGTH,color)
        tessellator.draw();

        GlStateManager.enableTexture2D()
        GlStateManager.enableCull()
        GlStateManager.disableBlend();
        RenderHelper.enableStandardItemLighting()
        GlStateManager.popMatrix()
    }

    private fun drawTest(x:Double,y:Double,z:Double){
        GlStateManager.pushMatrix()
        GlStateManager.translate(x+1.0,y,z)

        RenderHelper.disableStandardItemLighting()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
        GlStateManager.disableCull()
        GlStateManager.disableTexture2D()

        val tessellator = Tessellator.getInstance()
        val bf = tessellator.buffer
        bf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR)
        drawColorCube(bf,FLUID_WIDTH,1.0,FLUID_LENGTH, 0xFF000000.toInt())
        tessellator.draw();

        GlStateManager.enableTexture2D()
        GlStateManager.enableCull()
        GlStateManager.disableBlend();
        RenderHelper.enableStandardItemLighting()
        GlStateManager.popMatrix()
    }



    fun renderBottleToSlot(x: Double, y:Double, z:Double, physIndex:Int, bottle: ItemStack){

        val (xOffset,yOffset,zOffset) = getStartPoint(physIndex)

        val handler = FluidUtil.getFluidHandler(bottle)
        if (handler == null) return

        val fluidStack = handler.tankProperties[0].contents
        val capacity = handler.tankProperties[0].capacity.toDouble()


        val fxOffset = xOffset+FLUID_MARGIN
        val fyOffset = yOffset+FLUID_MARGIN
        val fzOffset = zOffset+FLUID_MARGIN
        if(fluidStack!=null){
            val color = fluidStack.fluid?.color ?:0x00000000
            val amount = fluidStack.amount.toDouble()
            val height = amount/capacity*FLUID_HEIGHT
            drawFluid(x+fxOffset,y+fyOffset,z+fzOffset,height,color)
        }

        val bxOffset = xOffset+DEPTH_OFFSET
        val byOffset = yOffset
        val bzOffset = zOffset+DEPTH_OFFSET

        drawBottle(x+bxOffset,y+byOffset,z+bzOffset)
    }
}