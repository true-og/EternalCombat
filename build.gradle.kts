plugins {
    java
    eclipse
    id("xyz.jpenilla.run-paper") version "2.2.3" apply false
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

