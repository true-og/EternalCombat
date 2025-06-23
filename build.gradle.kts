plugins {
    id("eternalcombat-repositories")
    id("com.diffplug.spotless") version "7.0.4"
}

group = "com.eternalcode"

version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://repo.purpurmc.org/snapshots")
}

allprojects {
    apply(plugin = "eternalcombat-repositories")
    apply(plugin = "com.diffplug.spotless")

    spotless {
        java {
            removeUnusedImports()
            palantirJavaFormat()
        }
        kotlinGradle {
            ktfmt().kotlinlangStyle().configure { it.setMaxWidth(120) }
            target("*.gradle.kts")
        }
    }

    tasks.named("build").configure { dependsOn("spotlessApply") }
}
