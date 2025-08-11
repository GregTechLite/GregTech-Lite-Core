package gregtechlite.gtlitecore.common.entity

import gregtech.client.renderer.handler.GTExplosiveRenderer
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.common.entity.explosive.EntityLeptonicCharge
import gregtechlite.gtlitecore.common.entity.explosive.EntityNaquadriaCharge
import gregtechlite.gtlitecore.common.entity.explosive.EntityQuantumChromodynamicCharge
import gregtechlite.gtlitecore.common.entity.explosive.EntityTaraniumCharge
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.registry.EntityRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object GTLiteMetaEntities
{

    fun init()
    {

        EntityRegistry.registerModEntity(GTLiteMod.id("naquadria_charge"),
            EntityNaquadriaCharge::class.java, "NaquadriaCharge", 1,
            GTLiteAPI.instance, 64, 3, true)

        EntityRegistry.registerModEntity(GTLiteMod.id("taranium_charge"),
            EntityTaraniumCharge::class.java, "TaraniumCharge", 2,
            GTLiteAPI.instance, 64, 3, true)

        EntityRegistry.registerModEntity(GTLiteMod.id("leptonic_charge"),
            EntityLeptonicCharge::class.java, "LeptonicCharge", 3,
            GTLiteAPI.instance, 64, 3, true)

        EntityRegistry.registerModEntity(GTLiteMod.id("quantum_chromodynamic_charge"),
            EntityQuantumChromodynamicCharge::class.java, "QuantumChromodynamicCharge", 4,
            GTLiteAPI.instance, 64, 3, true)

    }

    @SideOnly(Side.CLIENT)
    @JvmStatic
    fun initRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(
            EntityNaquadriaCharge::class.java) { GTExplosiveRenderer(it) }

        RenderingRegistry.registerEntityRenderingHandler(
            EntityTaraniumCharge::class.java) { GTExplosiveRenderer(it) }

        RenderingRegistry.registerEntityRenderingHandler(
            EntityLeptonicCharge::class.java) { GTExplosiveRenderer(it) }

        RenderingRegistry.registerEntityRenderingHandler(
            EntityQuantumChromodynamicCharge::class.java) { GTExplosiveRenderer(it) }
    }

}