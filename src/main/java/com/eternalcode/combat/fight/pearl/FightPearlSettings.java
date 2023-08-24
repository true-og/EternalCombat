package com.eternalcode.combat.fight.pearl;

import net.dzikoysk.cdn.entity.Contextual;
import net.dzikoysk.cdn.entity.Description;

import java.time.Duration;

@Contextual
public class FightPearlSettings {

    @Description({ "# Is pearl damage to be enabled?", "# This will work globally" })
    public boolean pearlThrowDamageEnabled = true;

    @Description("# Set true, If you want to lock pearls during the combat")
    public boolean pearlThrowBlocked = false;

    @Description({
        "# Block throwing pearls with delay?",
        "# If you set this to for example 3s, player will have to wait 3 seconds before throwing another pearl",
        "# Set to 0 to disable"
    })
    public Duration pearlThrowDelay = Duration.ofSeconds(3);

    @Description("# Message sent when player tries to throw ender pearl, but are disabled")
    public String pearlThrowBlockedDuringCombat = "&cThrowing ender pearls is prohibited during combat!";

    @Description("# Message sent when player tries to throw ender pearl, but has delay")
    public String pearlThrowBlockedDelayDuringCombat = "&cYou must wait {TIME} before next throw!";

}