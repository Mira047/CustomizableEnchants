package com.mira.customizableenchants.utils;

import com.mira.customizableenchants.CustomizableEnchants;
import com.mira.customizableenchants.enchants.EnchantType;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ItemUtils {
    static CustomizableEnchants main = CustomizableEnchants.getPlugin(CustomizableEnchants.class);

    public static ItemStack getItemByType(Player player, String id){
        PlayerInventory i = player.getInventory();
        EnchantType type = EnchantType.matchType(main.getConfig().getString("Enchants." + id + ".type"));

        if(type==null) return null;

        switch(type){
            case MELEE, BOW, CROSSBOW, TRIDENT, TOOL -> { return i.getItemInMainHand();
            }
            case HELMET -> { return i.getHelmet();
            }
            case CHESTPLATE -> { return i.getChestplate();
            }
            case LEGGINGS -> { return i.getLeggings();
            }
            case BOOTS -> { return i.getBoots();
            }
            // TODO: ARMOR AND ALL
            default -> {
                return null;
            }
        }
    }
}
