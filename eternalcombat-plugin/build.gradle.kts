import xyz.jpenilla.runpaper.task.RunServer

plugins {
    java
    id("com.gradleup.shadow") version "8.3.6"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("xyz.jpenilla.run-paper")
}

dependencies {
    implementation(project(":eternalcombat-api"))

    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("com.github.retrooper:packetevents-spigot:2.8.0")
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:7.3.0-SNAPSHOT") // Import WorldEdit.
    compileOnly(
        "com.sk89q.worldguard:worldguard-bukkit:7.0.9"
    ) { // Import WorldGuard but without its bundled WorldEdit.
        exclude(group = "com.sk89q.worldedit")
    }
    compileOnly("com.github.angeschossen:LandsAPI:7.15.20")

    // EternalCode Commons (split modules)
    implementation("com.eternalcode:eternalcode-commons-shared:+")
    implementation("com.eternalcode:eternalcode-commons-bukkit:+")
    implementation("com.eternalcode:eternalcode-commons-adventure:+")

    // Other libs
    implementation("net.kyori:adventure-api:4.17.0")
    implementation("net.kyori:adventure-text-minimessage:4.17.0")
    implementation("net.kyori:adventure-platform-bukkit:4.3.3")
    implementation("dev.rollczi:litecommands-bukkit:3.10.5")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
    implementation("org.bstats:bstats-bukkit:3.0.2")
    implementation("io.papermc:paperlib:1.0.8")

    implementation("com.eternalcode:multification-bukkit:1.2.2")
    implementation("com.eternalcode:multification-okaeri:1.1.4")
    implementation("com.eternalcode:gitcheck:1.0.0")

    implementation("eu.okaeri:okaeri-configs-core:5.0.9")
    implementation("eu.okaeri:okaeri-configs-yaml-snakeyaml:5.0.9")
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.9")
    implementation("eu.okaeri:okaeri-configs-serdes-commons:5.0.9")
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.9")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

bukkit {
    name = "EternalCombat"
    main = "com.eternalcode.combat.CombatPlugin"
    version = project.version.toString()
    apiVersion = "1.19"
    authors = listOf("EternalCodeTeam")
    prefix = "EternalCombat"
    softDepend = listOf("Lands")
    depend = listOf("packetevents")
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveFileName.set("EternalCombat v${project.version}.jar")
    exclude(
        "org/intellij/lang/annotations/**",
        "org/jetbrains/annotations/**",
        "META-INF/**",
        "kotlin/**",
        "javax/**",
        "org/checkerframework/**",
        "com/google/errorprone/**"
    )
    val prefix = "com.eternalcode.combat.libs"
    listOf(
        "eu.okaeri",
        "net.kyori",
        "org.bstats",
        "org.yaml",
        "dev.rollczi.litecommands",
        "com.eternalcode.gitcheck",
        "org.json.simple",
        "com.github.benmanes.caffeine",
        "com.eternalcode.commons",
        "com.eternalcode.multification",
        "io.papermc"
    ).forEach { relocate(it, "$prefix.$it") }
}

tasks.named<RunServer>("runServer") {
    minecraftVersion("1.19.4")
    downloadPlugins {
        url("https://github.com/retrooper/packetevents/releases/download/v2.8.0/packetevents-spigot-2.8.0.jar")
    }
}

