package com.mira.customizableenchants.listeners;

import com.mira.customizableenchants.CustomizableEnchants;
import com.mira.customizableenchants.enchants.EnchantType;
import com.mira.customizableenchants.enchants.Enchantment;
import com.mira.customizableenchants.enchants.TriggerType;
import com.mira.customizableenchants.mechanics.Mechanic;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class EntityAttackListener implements Listener {
    CustomizableEnchants main = CustomizableEnchants.getPlugin(CustomizableEnchants.class);

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if (damager instanceof Player player) {
            for (ItemStack stack : Enchantment.getCompatibleItems(player, EnchantType.ALL)) {
                if (stack == null) continue;
                HashMap<Enchantment, ItemStack> enchanted = Enchantment.getAllEnchantedItems(player);

                for (Map.Entry<Enchantment, ItemStack> key : enchanted.entrySet()) {
                    ItemStack item = key.getValue();
                    Enchantment enchantment = key.getKey();
                    if (item == null || enchantment == null) continue;
                    if (enchantment.trigger() != TriggerType.DAMAGE) continue;

                    for (Mechanic mechanic : enchantment.mechanics()) {
                        mechanic.type().getExecutor().execute(player, enchantment, mechanic);
                    }
                }
            }
        }

        if (damaged instanceof Player player) {
            for (ItemStack stack : Enchantment.getCompatibleItems(player, EnchantType.ALL)) {
                if (stack == null) continue;
                HashMap<Enchantment, ItemStack> enchanted = Enchantment.getAllEnchantedItems(player);

                for (Map.Entry<Enchantment, ItemStack> key : enchanted.entrySet()) {
                    ItemStack item = key.getValue();
                    Enchantment enchantment = key.getKey();
                    if (item == null || enchantment == null) continue;
                    if (enchantment.trigger() != TriggerType.DAMAGE) continue;

                    for (Mechanic mechanic : enchantment.mechanics()) {
                        mechanic.type().getExecutor().execute(player, enchantment, mechanic);
                    }
                }
            }
        }
    }
}
