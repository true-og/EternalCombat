package com.eternalcode.combat.fight;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class FightTag {

    private final UUID taggedPlayer;
    private final Instant endOfCombatLog;

    private double healthBeforeDeath;
    private FightDeathCause deathCause;

    FightTag(UUID personToAddCombat, Instant endOfCombatLog) {
        this.taggedPlayer = personToAddCombat;
        this.endOfCombatLog = endOfCombatLog;

        this.healthBeforeDeath = 0;
        this.deathCause = FightDeathCause.UNKNOWN;
    }

    public UUID getTaggedPlayer() {
        return this.taggedPlayer;
    }

    public Instant getEndOfCombatLog() {
        return this.endOfCombatLog;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(this.endOfCombatLog);
    }

    public Duration getRemainingDuration() {
        Duration between = Duration.between(Instant.now(), this.endOfCombatLog);

        if (between.isNegative()) {
            return Duration.ZERO;
        }

        return between;
    }

    public double getHealthBeforeDeath() {
        return this.healthBeforeDeath;
    }

    public void setHealthBeforeDeath(double health) {
        this.healthBeforeDeath = health;
    }

    public FightDeathCause getDeathCause() {
        return this.deathCause;
    }

    public void setDeathCause(FightDeathCause deathCause) {
        this.deathCause = deathCause;
    }
}