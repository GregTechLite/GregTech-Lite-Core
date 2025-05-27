pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven {
            // RetroFuturaGradle
            name = "GTNH Maven"
            url = uri("https://nexus.gtnewhorizons.com/repository/public/")
            mavenContent {
                includeGroup("com.gtnewhorizons")
                includeGroup("com.gtnewhorizons.retrofuturagradle")
            }
        }
    }
}

plugins {
    // Automatic toolchain provisioning
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

// Due to an IntelliJ bug, this has to be done
// rootProject.name = archives_base_name
rootProject.name = rootProject.projectDir.getName()
