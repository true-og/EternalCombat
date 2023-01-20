package com.eternalcode.combat.config.implementation;

import com.eternalcode.combat.config.ReloadableConfig;
import net.dzikoysk.cdn.source.Resource;
import net.dzikoysk.cdn.source.Source;

import java.io.File;

public class MessageConfig implements ReloadableConfig {

    public String onlyForPlayers = "&cThis command is only available to players!";
    public String noPermission = "&cYou don't have permission to perform this command!";
    public String cantFindPlayer = "&cThe specified player could not be found!";
    public String combatActionBar = "&dCombat ends in: &f{TIME}";
    public String tagPlayer = "&cYou are in combat, do not leave the server!";
    public String unTagPlayer = "&aYou are no longer in combat! You can safely leave the server.";
    public String cantUseCommand = "&cUsing this command during combat is prohibited!";
    public String adminTagPlayer = "&7You have tagged &e{FIRST_PLAYER}&7 and &e{SECOND_PLAYER}&7.";
    public String adminUnTagPlayer = "&7You have removed &e{PLAYER} from fight.";
    public String invalidUsage = "&7Correct usage: &e{COMMAND}.";
    public String inventoryBlocked = "&cYou cannot open this inventory during combat!";
    public String blockPlaceBlocked = "&cYou cannot place blocks during combat below 40 blocks!";
    public String inCombat = "&cYou are in combat!";
    public String notInCombat = "&aYou are not in combat!";
    public String reload = "&aConfiguration successfully reloaded!";
    public String cantTagSelf = "&cYou cannot tag yourself!";

    @Override
    public Resource resource(File folder) {
        return Source.of(folder, "messages.yml");
    }
}