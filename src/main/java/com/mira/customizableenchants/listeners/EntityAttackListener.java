package com.mira.customizableenchants.listeners;

import com.mira.customizableenchants.CustomizableEnchants;
import com.mira.customizableenchants.enchants.Enchant;
import com.mira.customizableenchants.utils.ItemUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EntityAttackListener implements Listener {
    CustomizableEnchants main = CustomizableEnchants.getPlugin(CustomizableEnchants.class);

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        if(damager instanceof Player player){
            // TODO: ALL AND ARMOR
            main.getConfig().getConfigurationSection("Enchants").getKeys(false).forEach(key -> {
                if(!main.getConfig().getString("Enchants." + key + ".type").equals("ARMOR")||!main.getConfig().getString("Enchants." + key + ".type").equals("ALL")) {
                    ItemStack item = ItemUtils.getItemByType(player, key);

                    if(item==null) return;

                    Enchant e = new Enchant(key);

                    if(!e.getTrigger().equals("DAMAGE")) return;

                    e.executeMechanics(player, entity);
                }
            });
        } else if(entity instanceof Player player){
            // TODO: ALL AND ARMOR
            main.getConfig().getConfigurationSection("Enchants").getKeys(false).forEach(key -> {
                if(!main.getConfig().getString("Enchants." + key + ".type").equals("ARMOR")||!main.getConfig().getString("Enchants." + key + ".type").equals("ALL")) {
                    ItemStack item = ItemUtils.getItemByType(player, key);

                    if(item==null) return;

                    Enchant e = new Enchant(key);

                    if(!e.getTrigger().equals("DAMAGED")) return;

                    e.executeMechanics(player, damager);
                }
            });
        }
    }
}
