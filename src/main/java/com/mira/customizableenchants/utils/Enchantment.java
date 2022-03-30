package com.mira.customizableenchants.utils;

import com.mira.customizableenchants.enchants.EnchantType;
import com.mira.customizableenchants.enchants.TriggerType;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public record Enchantment(String id, String display, EnchantType type, TriggerType trigger, int maxLevel, List<Mechanic> mechanics) {
    public Mechanic getMechanic(String id) {
        for (Mechanic mechanic : mechanics) {
            if (mechanic.id().equalsIgnoreCase(id)) return mechanic;
        }
        return null;
    }

    public boolean canApplyTo(EnchantType type1) {
        switch (type) {
            case ALL -> {return true;}
            case BOW, HELMET, CHESTPLATE, BOOTS, LEGGINGS, TRIDENT, CROSSBOW, MELEE, TOOL -> {return type.equals(type1);}
            case ARMOR -> {
                return type.equals(type1) || type1.equals(EnchantType.HELMET) || type1.equals(EnchantType.CHESTPLATE)
                        || type1.equals(EnchantType.LEGGINGS) || type1.equals(EnchantType.BOOTS);
            }
            default -> {return false;}
        }
    }

    public static boolean areCompatible(EnchantType type1, EnchantType type2) {
        switch (type2) {
            case ALL -> {return true;}
            case BOW, HELMET, CHESTPLATE, BOOTS, LEGGINGS, TRIDENT, CROSSBOW, MELEE, TOOL -> {return type2.equals(type1);}
            case ARMOR -> {
                return type2.equals(type1) || type1.equals(EnchantType.HELMET) || type1.equals(EnchantType.CHESTPLATE)
                        || type1.equals(EnchantType.LEGGINGS) || type1.equals(EnchantType.BOOTS);
            }
            default -> {return false;}
        }
    }

    public static List<ItemStack> getCompatibleItems(Player player, EnchantType type1) {
        switch (type1) {
            case BOW, MELEE, CROSSBOW, TRIDENT, TOOL -> {
                return Collections.singletonList(player.getActiveItem());
            }
            case HELMET -> {
                return Collections.singletonList(player.getInventory().getHelmet());
            }
            case CHESTPLATE -> {
                return Collections.singletonList(player.getInventory().getChestplate());
            }
            case LEGGINGS -> {
                return Collections.singletonList(player.getInventory().getLeggings());
            }
            case BOOTS -> {
                return Collections.singletonList(player.getInventory().getBoots());
            }
            case ARMOR -> {
                List<ItemStack> stacks = new ArrayList<>();
                stacks.addAll(Arrays.asList(player.getInventory().getArmorContents()));
                return stacks;
            }
            case ALL -> {
                List<ItemStack> stacks = new ArrayList<>();
                stacks.addAll(Arrays.asList(player.getInventory().getArmorContents()));
                stacks.add(player.getActiveItem());
                return stacks;
            }
            default -> {
                return Collections.singletonList(null);
            }
        }
    }

    public List<ItemStack> getCompatibleItems(Player player) {
        return getCompatibleItems(player, type);
    }

    public static List<ItemStack> getEnchantedItems(Player player, Enchantment enchantment) {
        List<ItemStack> list = getCompatibleItems(player, enchantment.type());
        List<ItemStack> enchantedList = new ArrayList<>();

        for (ItemStack stack : list) {
            if (stack == null) continue;
            NBTItem nbtItem = new NBTItem(stack);
            if (nbtItem.hasKey(enchantment.id())) {
                enchantedList.add(stack);
            }
        }

        return enchantedList;
    }

    public List<ItemStack> getEnchantedItems(Player player) {
        return getEnchantedItems(player, this);
    }
}
