package com.mira.customizableenchants.enchants;

import com.mira.customizableenchants.CustomizableEnchants;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EnchantManager {
    static CustomizableEnchants main = CustomizableEnchants.getPlugin(CustomizableEnchants.class);

    public static ItemStack enchantItem(@NotNull ItemStack item, @NotNull String enchantName){
        /* Enchant Tags */
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();


        NBTItem nbti = new NBTItem(item);

        lore.add(0,main.getConfig().getString("Enchants." + enchantName + ".dispaly") + "I");

        nbti.setInteger(main.getConfig().getString("Enchants." + enchantName),nbti.hasKey("Enchants." + enchantName) ? nbti.getInteger("Enchants." + enchantName)+1 : 1);

        item = nbti.getItem();

        return item;
    }
}
