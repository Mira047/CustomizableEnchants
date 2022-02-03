package com.mira.customizableenchants.commands;

import com.mira.customizableenchants.CustomizableEnchants;

import com.mira.customizableenchants.enchants.EnchantManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;


public class MainCommand implements CommandExecutor {
    CustomizableEnchants main = CustomizableEnchants.getPlugin(CustomizableEnchants.class);

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Nothing
        if (args.length == 0) {
            sender.sendMessage(ChatColor.DARK_GRAY + "CustomizableEnchants - " + ChatColor.RED + "Incorrect Command usage!");
            return false;
        }

        // Reload Command
        if (args[0].equals("reload")) {
            main.reloadConfig();
            sender.sendMessage(ChatColor.DARK_GRAY + "CustomizableEnchants - " + ChatColor.GRAY + "Config reloaded!");
            return true;
        }

        // Enchant Command
        if (args[0].equals("enchant")) {
            if (sender instanceof Player player) {
                ItemStack item = player.getInventory().getItemInMainHand();
                item = EnchantManager.enchantItem(item, args[1].toUpperCase(Locale.ROOT));
            } else sender.sendMessage(ChatColor.DARK_GRAY + "CustomizableEnchants - " + ChatColor.RED + "This command can only be executed by a player!");
        }
        return false;
    }


}
