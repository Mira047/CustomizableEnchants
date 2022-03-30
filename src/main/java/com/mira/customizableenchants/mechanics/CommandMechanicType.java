package com.mira.customizableenchants.mechanics;

import com.mira.customizableenchants.enchants.Enchantment;
import com.mira.customizableenchants.trigger.MechanicTrigger;
import com.mira.customizableenchants.utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandMechanicType implements MechanicType {
    private static String applyPlaceholders(String command, Player player, Enchantment enchantment, int level) {
        return command.replace("<player_x>", String.valueOf(player.getLocation().getBlockX()))
                .replace("<player_y>", String.valueOf(player.getLocation().getBlockY()))
                .replace("<player_z>", String.valueOf(player.getLocation().getBlockZ()))
                .replace("<player_name>", player.getName())
                .replace("<enchant_name>", enchantment.display())
                .replace("<enchant_level>", MathUtils.toRoman(level));
    }

    @Override
    public void execute(MechanicTrigger trigger) {
        System.out.println("eee");
        String command = applyPlaceholders(trigger.mechanic().data(), trigger.player(), trigger.enchantment(), trigger.level());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }
}
