package magicbook.gtlitecore.core

import net.minecraft.launchwrapper.IClassTransformer
import org.objectweb.asm.Opcodes

@Suppress("unused")
class GTLiteASMTransformer : IClassTransformer, Opcodes
{

    override fun transform(name: String?, transformedName: String?, basicClass: ByteArray?): ByteArray = basicClass!!

}