package com.mira.customizableenchants.enchants;

import com.mira.customizableenchants.CustomizableEnchants;
import com.mira.customizableenchants.utils.MathUtils;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnchantManager {
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
}
