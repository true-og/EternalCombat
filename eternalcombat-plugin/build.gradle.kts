plugins {
    `eternalcombat-java`
    `eternalcombat-repositories`
    `eclipse`
    id("net.minecrell.plugin-yml.bukkit")
    id("com.gradleup.shadow") version "8.3.6"
    id("xyz.jpenilla.run-paper")
}

repositories { maven("https://repo.purpurmc.org/snapshots") }

dependencies {
    implementation(project(":eternalcombat-api"))
    compileOnly("org.purpurmc.purpur:purpur-api:1.19.4-R0.1-SNAPSHOT")
}

bukkit {
    main = "com.eternalcode.combat.CombatPlugin"
    author = "EternalCodeTeam"
    apiVersion = "1.13"
    prefix = "EternalCombat"
    name = "EternalCombat"
    softDepend = listOf("WorldGuard", "PlaceholderAPI")
    version = "${project.version}"
}

tasks { runServer { minecraftVersion("1.19.4") } }

tasks.shadowJar {
    archiveFileName.set("EternalCombat-${project.version}.jar")
    dependsOn("test")
    exclude("org/intellij/lang/annotations/**", "org/jetbrains/annotations/**", "META-INF/**", "kotlin/**", "javax/**")
    val prefix = "com.eternalcode.combat.libs"
    listOf(
            "panda.std",
            "panda.utilities",
            "org.panda-lang",
            "eu.okaeri",
            "net.kyori",
            "org.bstats",
            "dev.rollczi.litecommands",
            "com.eternalcode.gitcheck",
            "org.json.simple",
            "org.apache.commons",
            "javassist",
            "com.github.ben-manes.caffeine",
            "com.eternalcode.commons",
        )
        .forEach { pack -> relocate(pack, "$prefix.$pack") }
}

tasks.named("build").configure { dependsOn("shadowJar") }

tasks.register("runCopyJarScript", Exec::class) {
    group = "build"
    description = "Runs the copyjar.sh script after build completion."
    workingDir(rootDir)
    commandLine("sh", "copyjar.sh", project.version.toString())
}

tasks.named("build") { finalizedBy("runCopyJarScript") }
