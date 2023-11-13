package com.eternalcode.combat.region;

import org.bukkit.Location;

public interface RegionProvider {

    boolean isInRegion(Location location);

    Region getRegion(Location location);
}
