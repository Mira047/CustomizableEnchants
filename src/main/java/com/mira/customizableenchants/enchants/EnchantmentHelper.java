package com.mira.customizableenchants.enchants;

import com.mira.customizableenchants.CustomizableEnchants;
import com.mira.customizableenchants.mechanics.Mechanic;
import com.mira.customizableenchants.trigger.MechanicTrigger;
import com.mira.customizableenchants.trigger.Trigger;
import com.mira.customizableenchants.utils.MathUtils;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class EnchantmentHelper {
    static CustomizableEnchants main = CustomizableEnchants.getPlugin(CustomizableEnchants.class);

    private static Map.Entry<Integer, String> getLoreEntry(List<String> lore, String startWith) {
        for (int i = 0; i < lore.size(); i++) {
            String str = lore.get(i);
            if (str.startsWith(startWith)) {
                return new AbstractMap.SimpleEntry<>(i, str);
            }
        }
        return new AbstractMap.SimpleEntry<>(0, "");
    }

    public static ItemStack enchantItem(ItemStack item, Enchantment enchantment){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (meta.hasLore()) lore = meta.getLore();
        NBTItem nbti = new NBTItem(item);

        if (nbti.hasKey(enchantment.id())) {
            int level = nbti.getInteger(enchantment.id());
            nbti.setInteger(enchantment.id(), ++level);

            Map.Entry<Integer, String> entry = getLoreEntry(lore, ChatColor.GRAY + enchantment.display());
            lore.set(entry.getKey(), ChatColor.GRAY + enchantment.display() + MathUtils.toRoman(level));
        } else {
            nbti.setInteger(enchantment.id(), 1);
            lore.add(0, ChatColor.GRAY + enchantment.display() + MathUtils.toRoman(1));
        }

        return nbti.getItem();
    }

    public static void executeForAll(Trigger trigger) {
        for (ItemStack stack : Enchantment.getCompatibleItems(trigger.player(), EnchantType.ALL)) {
            if (stack == null) continue;
            HashMap<Enchantment, ItemStack> enchanted = Enchantment.getAllEnchantedItems(trigger.player());

            for (Map.Entry<Enchantment, ItemStack> key : enchanted.entrySet()) {
                ItemStack item = key.getValue();
                Enchantment enchantment = key.getKey();
                if (item == null || enchantment == null) continue;
                if (enchantment.trigger() != trigger.type()) continue;

                NBTItem nbti = new NBTItem(item);

                // can't be null because Enchantment.getAllEnchantedItems checks for that
                int level = nbti.getInteger(enchantment.id());

                for (Mechanic mechanic : enchantment.mechanics()) {
                    MechanicTrigger newTrigger = new MechanicTrigger(trigger.type(), trigger.player(),
                            enchantment, level, mechanic, trigger.triggeredBy());

                    mechanic.type().getExecutor().execute(newTrigger);
                }
            }
        }
    }
}
