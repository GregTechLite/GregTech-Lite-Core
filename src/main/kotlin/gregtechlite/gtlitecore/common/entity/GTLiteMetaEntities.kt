package gregtechlite.gtlitecore.common.entity

import gregtech.client.renderer.handler.GTExplosiveRenderer
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.GTLiteAPI
import net.minecraft.entity.Entity
import net.minecraftforge.fml.client.registry.IRenderFactory
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.registry.EntityRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.reflect.KClass

object GTLiteMetaEntities
{

    internal fun init()
    {
        registerEntity(1, "naquadria_charge", "NaquadriaCharge",
                       EntityNaquadriaCharge::class)
        registerEntity(2, "taranium_charge", "TaraniumCharge",
                       EntityTaraniumCharge::class)
        registerEntity(3, "leptonic_charge", "LeptonicCharge",
                       EntityLeptonicCharge::class)
        registerEntity(4, "quantum_chromodynamic_charge", "QuantumChromodynamicCharge",
                       EntityQuantumChromodynamicCharge::class)
    }

    @SideOnly(Side.CLIENT)
    @JvmStatic
    fun initRenderers()
    {
        registerEntityRenderer(EntityNaquadriaCharge::class) { GTExplosiveRenderer(it) }
        registerEntityRenderer(EntityTaraniumCharge::class) { GTExplosiveRenderer(it) }
        registerEntityRenderer(EntityLeptonicCharge::class) { GTExplosiveRenderer(it) }
        registerEntityRenderer(EntityQuantumChromodynamicCharge::class) { GTExplosiveRenderer(it) }
    }

}

/**
 * Register the entity with the mod location.
 *
 * @param id                   The mod specific id for the entity, each entity should have unique id in one mod.
 * @param registryName         The registry name of the mod, used the mod location as default.
 * @param entityContainer      The entity class with Kotlin format.
 * @param trackingRange        The range at which the game will send tracking updates.
 * @param updateFrequency      The frequency of tracking updates.
 * @param sendsVelocityUpdates Whether to send velocity information packets as well.
 */
private fun registerEntity(id: Int, registryName: String, entityName: String,
                           entityContainer: KClass<out Entity>,
                           trackingRange: Int = 64,
                           updateFrequency: Int = 3,
                           sendsVelocityUpdates: Boolean = true)
{
    EntityRegistry.registerModEntity(GTLiteMod.id(registryName),
                                     entityContainer.java, entityName, id,
                                     GTLiteAPI.instance, trackingRange, updateFrequency, sendsVelocityUpdates)
}

/**
 * Register the renderer of the mod entity.
 *
 * @param entityContainer The entity class with Kotlin format.
 * @param renderer        The render factory for the entity.
 */
private fun <T : Entity> registerEntityRenderer(entityContainer: KClass<T>, renderer: IRenderFactory<in T>)
{
    RenderingRegistry.registerEntityRenderingHandler(entityContainer.java, renderer)
}