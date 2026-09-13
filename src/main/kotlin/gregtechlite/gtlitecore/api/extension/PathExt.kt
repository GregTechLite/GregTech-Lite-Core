package gregtechlite.gtlitecore.api.extension

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import kotlin.streams.asSequence

fun Path.extractTo(targetPath: File, replace: Boolean)
{
    Files.walk(this).use {
        it.asSequence()
            .filter(Files::isRegularFile)
            .forEach { source ->
                val generatePath = targetPath.toPath().resolve(relativize(source).toString())
                Files.createDirectories(generatePath.parent)
                if (replace || !generatePath.toFile().isFile)
                {
                    Files.copy(source, generatePath, StandardCopyOption.REPLACE_EXISTING)
                }
            }
    }
}