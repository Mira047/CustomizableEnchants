package com.mira.customizableenchants.mechanics;

import com.mira.customizableenchants.enchants.Enchantment;
import org.bukkit.entity.Player;

public interface MechanicType {
    void execute(Player player, Enchantment enchantment, Mechanic mechanic, int level);
}
