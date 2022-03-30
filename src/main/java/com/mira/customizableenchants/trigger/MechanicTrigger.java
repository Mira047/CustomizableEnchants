package com.mira.customizableenchants.trigger;

import com.mira.customizableenchants.enchants.Enchantment;
import com.mira.customizableenchants.mechanics.Mechanic;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Optional;

public record MechanicTrigger(TriggerType type, Player player, Enchantment enchantment,
                              int level, Mechanic mechanic, Optional<Entity> entity) {
}
