plugins {
    `eternalcombat-java`
    `eternalcombat-repositories`
    `eternalcombat-publish`
    `eternalcombat-java-unit-test`
}

repositories { maven("https://repo.purpurmc.org/snapshots") }

dependencies {
    compileOnlyApi("org.spigotmc:spigot-api:${Versions.SPIGOT_API}")
    compileOnlyApi("org.purpurmc.purpur:purpur-api:1.19.4-R0.1-SNAPSHOT")

    api("net.kyori:adventure-platform-bukkit:${Versions.ADVENTURE_PLATFORM_BUKKIT}")
    api("net.kyori:adventure-text-minimessage:${Versions.ADVENTURE_TEXT_MINIMESSAGE}")

    api("dev.rollczi.litecommands:bukkit-adventure:${Versions.LITE_COMMANDS}")

    api("eu.okaeri:okaeri-configs-yaml-bukkit:${Versions.OKAERI_CONFIGS_YAML_BUKKIT}")
    api("eu.okaeri:okaeri-configs-serdes-commons:${Versions.OKAERI_CONFIGS_SERDES_COMMONS}")
    api("eu.okaeri:okaeri-configs-serdes-bukkit:${Versions.OKAERI_CONFIGS_SERDES_BUKKIT}")

    api("org.panda-lang:panda-utilities:${Versions.PANDA_UTILITIES}")

    api("com.eternalcode:gitcheck:${Versions.GIT_CHECK}")

    api("commons-io:commons-io:${Versions.APACHE_COMMONS}")

    api("org.bstats:bstats-bukkit:${Versions.B_STATS_BUKKIT}")

    api("com.github.ben-manes.caffeine:caffeine:${Versions.CAFFEINE}")

    api("com.eternalcode:eternalcode-commons-bukkit:${Versions.ETERNALCODE_COMMONS}")
    api("com.eternalcode:eternalcode-commons-adventure:${Versions.ETERNALCODE_COMMONS}")

    compileOnly("com.sk89q.worldguard:worldguard-bukkit:${Versions.WORLD_GUARD_BUKKIT}")

    compileOnlyApi("me.clip:placeholderapi:${Versions.PLACEHOLDER_API}")
}
