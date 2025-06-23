package com.eternalcode.combat.fight.bossbar;

import java.time.Duration;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;

public record FightBossBar(Audience audience, BossBar bossBar, float progress, Duration combatDuration) {}
