plugins {
    java
    eclipse
}

tasks.jar {
    enabled = false
}

val pluginJar by tasks.registering(Copy::class) {
    dependsOn(":eternalcombat-plugin:shadowJar")
    from(layout.projectDirectory.file("eternalcombat-plugin/build/libs/EternalCombat v${project.version}.jar"))
    into(layout.buildDirectory.dir("libs"))
    rename { "EternalCombat-${project.version}.jar" }
}

tasks.assemble {
    dependsOn(pluginJar)
}

allprojects {
    group = "com.eternalcode"
    version = "2.2.0"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://repo.eternalcode.pl/releases")
        maven("https://storehouse.okaeri.eu/repository/maven-releases/")
        maven("https://maven.reposilite.com/releases")
        maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
        maven("https://repo.codemc.org/repository/maven-public/")
        maven("https://maven.enginehub.org/repo/")
        maven("https://jitpack.io")
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "eclipse")
    java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }
    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
}
