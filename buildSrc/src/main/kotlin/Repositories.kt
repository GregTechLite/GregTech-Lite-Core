import org.gradle.api.Project
import org.gradle.kotlin.dsl.repositories

fun Project.repositories() {
    repositories {
        maven {
            name = "CleanroomMC Maven"
            url = uri("https://maven.cleanroommc.com")
        }
        maven {
            name = "SpongePowered Maven"
            url = uri("https://repo.spongepowered.org/maven")
        }
        maven {
            name = "CurseMaven"
            url = uri("https://cursemaven.com")
            content {
                includeGroup("curse.maven")
            }
        }
        maven {
            name = "BlameJared Maven"
            url = uri("https://maven.blamejared.com")
        }
        maven {
            name = "GTCEu Maven"
            url = uri("https://maven.gtceu.com")
        }
        mavenLocal() // Must be last for caching to work.
    }
}