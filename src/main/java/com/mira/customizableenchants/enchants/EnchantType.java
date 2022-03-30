package com.mira.customizableenchants.enchants;

import java.util.HashMap;
import java.util.Locale;

public enum EnchantType {
    /* Weapons + Tools */
    MELEE,
    BOW,
    CROSSBOW,
    TRIDENT,
    TOOL,
    /* Armor */
    ARMOR,
    HELMET,
    CHESTPLATE,
    LEGGINGS,
    BOOTS,

    ALL;

    private static final HashMap<String, EnchantType> BY_NAME = new HashMap<>();

    public static EnchantType getType(String name) {
        String filtered = name.toUpperCase(Locale.ENGLISH);
        return BY_NAME.get(name);
    }


    static {
        EnchantType[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            EnchantType type = var0[var2];
            BY_NAME.put(type.name(), type);
        }

    }
}
