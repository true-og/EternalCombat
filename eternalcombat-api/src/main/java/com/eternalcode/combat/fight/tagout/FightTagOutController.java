package com.eternalcode.combat.fight.tagout;

import com.eternalcode.combat.config.implementation.PluginConfig;
import com.eternalcode.combat.fight.event.FightTagEvent;
import java.util.UUID;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FightTagOutController implements Listener {

    private final FightTagOutService tagOutService;
    private final PluginConfig config;

    public FightTagOutController(FightTagOutService tagOutService, PluginConfig config) {
        this.tagOutService = tagOutService;
        this.config = config;
    }

    @EventHandler
    void onTagOut(FightTagEvent event) {
        UUID uniqueId = event.getPlayer();

        if (this.tagOutService.isTaggedOut(uniqueId)) {
            event.cancel(this.config.messages.admin.adminTagOutCanceled);
        }
    }
}
