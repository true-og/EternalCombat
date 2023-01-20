package com.eternalcode.combat.listener;

import com.eternalcode.combat.NotificationAnnouncer;
import com.eternalcode.combat.combat.CombatManager;
import com.eternalcode.combat.config.implementation.MessageConfig;
import com.eternalcode.combat.config.implementation.PluginConfig;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;

public class BlockPlaceListener implements Listener {

    private final CombatManager combatManager;
    private final NotificationAnnouncer announcer;
    private final MessageConfig messages;
    private final PluginConfig config;

    public BlockPlaceListener(CombatManager combatManager, NotificationAnnouncer announcer, MessageConfig messages, PluginConfig config) {
        this.combatManager = combatManager;
        this.announcer = announcer;
        this.messages = messages;
        this.config = config;
    }

    @EventHandler
    void onPlace(BlockPlaceEvent event) {
        if (!this.config.blockPlace) {
            return;
        }

        Player player = event.getPlayer();
        UUID uniqueId = player.getUniqueId();

        if (!this.combatManager.isInCombat(uniqueId)) {
            return;
        }

        Block block = event.getBlock();
        int level = block.getY();

        if (level <= this.config.blockPlaceLevel) {
            event.setCancelled(true);

            this.announcer.announceMessage(uniqueId, this.messages.blockPlaceBlocked);
        }
    }
}