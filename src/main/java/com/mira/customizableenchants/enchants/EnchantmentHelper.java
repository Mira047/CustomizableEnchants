package com.mira.customizableenchants.enchants;

import com.mira.customizableenchants.CustomizableEnchants;
import com.mira.customizableenchants.mechanics.Mechanic;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnchantmentHelper {
    static CustomizableEnchants main = CustomizableEnchants.getPlugin(CustomizableEnchants.class);

    public static ItemStack enchantItem(ItemStack item, Enchantment enchantment){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        if(meta.hasLore()){
            lore = meta.getLore();
        }

        lore.add(0, ChatColor.GRAY + enchantment.display() + " I");

        meta.setLore(lore);
        item.setItemMeta(meta);
        
        NBTItem nbti = new NBTItem(item);

        nbti.setInteger(enchantName,nbti.hasKey(enchantName) ? nbti.getInteger(enchantName)+1 : 1);

        // meta = item.getItemMeta();
        // lore.set(0, lore.get(0) + ChatColor.GRAY + " " +MathUtils.RomanNumerals(nbti.getInteger("Enchants." + enchantName)));

        item = nbti.getItem();
        System.out.println(new NBTItem(item));
        return nbti.getItem();
    }

    public static void executeForAll(Player player, TriggerType trigger) {
        for (ItemStack stack : Enchantment.getCompatibleItems(player, EnchantType.ALL)) {
            if (stack == null) continue;
            HashMap<Enchantment, ItemStack> enchanted = Enchantment.getAllEnchantedItems(player);

            for (Map.Entry<Enchantment, ItemStack> key : enchanted.entrySet()) {
                ItemStack item = key.getValue();
                Enchantment enchantment = key.getKey();
                if (item == null || enchantment == null) continue;
                if (enchantment.trigger() != trigger) continue;

                for (Mechanic mechanic : enchantment.mechanics()) {
                    mechanic.type().getExecutor().execute(player, enchantment, mechanic);
                }
            }
        }
    }
}
