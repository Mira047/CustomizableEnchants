package com.mira.customizableenchants.listeners;

import com.mira.customizableenchants.enchants.EnchantmentHelper;
import com.mira.customizableenchants.enchants.TriggerType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityAttackListener implements Listener {
    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if (damager instanceof Player player) {
            EnchantmentHelper.executeForAll(player, TriggerType.DAMAGED);
        }

        if (damaged instanceof Player player) {
            EnchantmentHelper.executeForAll(player, TriggerType.DAMAGE);
        }
    }
}
