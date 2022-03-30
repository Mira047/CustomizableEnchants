package com.mira.customizableenchants;

import com.mira.customizableenchants.commands.MainCommand;
import com.mira.customizableenchants.enchants.EnchantmentHelper;
import com.mira.customizableenchants.listeners.EntityAttackListener;
import com.mira.customizableenchants.misc.Metrics;

import com.mira.customizableenchants.trigger.Trigger;
import com.mira.customizableenchants.trigger.TriggerType;
import com.mira.customizableenchants.utils.ConfigHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;

public class CustomizableEnchants extends JavaPlugin {
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        ConfigHelper.load();

        this.getCommand("customizableenchants").setExecutor(new MainCommand());

        int pluginId = 14167;
        Metrics metrics = new Metrics(this, pluginId);

        registerTickTask();

        this.getServer().getPluginManager().registerEvents(new EntityAttackListener(), this);

        getLogger().info("CustomizableEnchants Enabled!");
    }

    public void onDisable() {
        getLogger().info("CustomizableEnchants Disabled!");
    }

    private void registerTickTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : getServer().getOnlinePlayers()) {
                    Trigger trigger = new Trigger(TriggerType.PASSIVE, player, Optional.empty());
                    EnchantmentHelper.executeForAll(trigger);
                }
            }
        }.runTaskTimer(this, 1L, 1L);
    }
}
