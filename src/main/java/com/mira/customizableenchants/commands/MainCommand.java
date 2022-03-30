package com.mira.customizableenchants.commands;

import com.mira.customizableenchants.CustomizableEnchants;

import com.mira.customizableenchants.enchants.Enchantment;
import com.mira.customizableenchants.enchants.EnchantmentHelper;
import com.mira.customizableenchants.utils.ConfigHelper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


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

                Enchantment enchantment = ConfigHelper.getEnchantment(args[1]);
                if (enchantment == null) {
                    sender.sendMessage(ChatColor.DARK_GRAY + "CustomizableEnchants - " + ChatColor.RED + "Unknown enchantment!");
                }

                player.getInventory().setItemInMainHand(EnchantmentHelper.enchantItem(item, enchantment));
            } else sender.sendMessage(ChatColor.DARK_GRAY + "CustomizableEnchants - " + ChatColor.RED + "This command can only be executed by a player!");
        }
        return false;
    }


}
