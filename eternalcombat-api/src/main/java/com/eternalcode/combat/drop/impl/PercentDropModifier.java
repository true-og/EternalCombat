package com.eternalcode.combat.drop.impl;

import com.eternalcode.combat.drop.Drop;
import com.eternalcode.combat.drop.DropModifier;
import com.eternalcode.combat.drop.DropResult;
import com.eternalcode.combat.drop.DropSettings;
import com.eternalcode.combat.drop.DropType;
import com.eternalcode.combat.util.InventoryUtil;
import com.eternalcode.combat.util.MathUtil;
import com.eternalcode.combat.util.RemoveItemResult;
import java.util.List;
import org.bukkit.inventory.ItemStack;

public class PercentDropModifier implements DropModifier {

    private final DropSettings settings;

    public PercentDropModifier(DropSettings settings) {
        this.settings = settings;
    }

    @Override
    public DropType getDropType() {
        return DropType.PERCENT;
    }

    @Override
    public DropResult modifyDrop(Drop drop) {
        int dropItemPercent = 100 - MathUtil.clamp(this.settings.dropItemPercent, 0, 100);
        List<ItemStack> droppedItems = drop.getDroppedItems();

        int itemsToDelete = InventoryUtil.calculateItemsToDelete(dropItemPercent, droppedItems, ItemStack::getAmount);
        int droppedExp = MathUtil.getRoundedCountFromPercentage(dropItemPercent, drop.getDroppedExp());

        RemoveItemResult result = InventoryUtil.removeRandomItems(droppedItems, itemsToDelete);

        return new DropResult(result.restItems(), result.removedItems(), droppedExp);
    }
}
