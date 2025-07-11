package com.eternalcode.combat.drop;

import java.util.HashMap;
import java.util.Map;

public class DropManager {

    private final Map<DropType, DropModifier> modifiers;

    public DropManager() {
        this.modifiers = new HashMap<>();
    }

    public void registerModifier(DropModifier dropModifier) {
        DropType dropType = dropModifier.getDropType();

        if (dropType == null) {
            throw new RuntimeException("Drop type cannot be null! '%s'"
                    .formatted(dropModifier.getClass().getSimpleName()));
        }

        if (dropType == DropType.UNCHANGED) {
            throw new RuntimeException(
                    "You cannot register DropModifier for this type '%s'".formatted(dropType.name()));
        }

        this.modifiers.put(dropType, dropModifier);
    }

    public DropResult modify(DropType dropType, Drop drop) {
        if (!this.modifiers.containsKey(dropType)) {
            throw new RuntimeException("No drop modifier found for type '%s'".formatted(dropType.name()));
        }

        return this.modifiers.get(dropType).modifyDrop(drop);
    }
}
