package magicbook.gtlitecore.api.utils.reflect

import java.util.function.Function
import java.util.stream.Stream

class StackWalkerKt
{

    fun StackWalker.walkKt(action: (Stream<StackFrame>) -> Unit)
    {
        this.walk { stream ->
            action(stream)
            null
        }
    }

    fun StackWalker.getCallerClass(): Class<*>
    {
        return this.walk { stream ->
            stream.filter { frame ->
                !frame.className.startsWith("java.lang")
                        && !frame.className.startsWith(StackWalker::class.java.name)
            }.findFirst().map { it.declaringClass }.orElse(null)
        }
    }

}