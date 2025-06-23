package com.eternalcode.combat.util;

import java.util.Collections;
import java.util.List;
import org.bukkit.inventory.ItemStack;

public record RemoveItemResult(List<ItemStack> restItems, List<ItemStack> removedItems) {

    public List<ItemStack> restItems() {
        return Collections.unmodifiableList(this.restItems);
    }

    @Override
    public List<ItemStack> removedItems() {
        return Collections.unmodifiableList(this.removedItems);
    }
}
