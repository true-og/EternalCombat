package com.eternalcode.combat.region;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;
import java.util.Optional;

public class WorldGuardRegionProvider implements RegionProvider {

    private final List<String> regions;

    public WorldGuardRegionProvider(List<String> regions) {
        this.regions = regions;
    }

    @Override
    public boolean isInRegion(Location location) {
        return this.regions.stream().anyMatch(region -> this.isInCombatRegion(location, region));
    }

    @Override
    public Region getRegion(Location location) {
        ApplicableRegionSet applicableRegions = getProtectedRegions(location);

        Optional<ProtectedRegion> first = applicableRegions.getRegions().stream().findFirst();

        if (first.isEmpty()) {
            return null;
        }

        ProtectedRegion protectedRegion = first.get();
        BlockVector3 minimumPoint = protectedRegion.getMinimumPoint();
        BlockVector3 maximumPoint = protectedRegion.getMaximumPoint();

        World world = location.getWorld();
        Location adaptMin = BukkitAdapter.adapt(world, minimumPoint);
        Location adaptMax = BukkitAdapter.adapt(world, maximumPoint);

        return new Region(adaptMin, adaptMax);
    }

    private boolean isInCombatRegion(Location location, String regionName) {
        ApplicableRegionSet applicableRegions = getProtectedRegions(location);

        return applicableRegions.getRegions().stream().anyMatch(region -> region.getId().equalsIgnoreCase(regionName));
    }

    private static ApplicableRegionSet getProtectedRegions(Location location) {
        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery regionQuery = regionContainer.createQuery();

        return regionQuery.getApplicableRegions(BukkitAdapter.adapt(location));
    }
}
