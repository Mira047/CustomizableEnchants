package com.mira.customizableenchants.listeners;

import com.mira.customizableenchants.enchants.EnchantmentHelper;
import com.mira.customizableenchants.trigger.Trigger;
import com.mira.customizableenchants.trigger.TriggerType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Optional;

public class EntityAttackListener implements Listener {
    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if (damager instanceof Player player) {
            Trigger trigger = new Trigger(TriggerType.DAMAGED, player, Optional.of(damaged));
            EnchantmentHelper.executeForAll(trigger);
        }

        if (damaged instanceof Player player) {
            Trigger trigger = new Trigger(TriggerType.DAMAGE, player, Optional.of(damager));
            EnchantmentHelper.executeForAll(trigger);
        }
    }
}
