package com.mira.customizableenchants;

import com.mira.customizableenchants.misc.Metrics;

import org.bukkit.plugin.java.JavaPlugin;

public class CustomizableEnchants extends JavaPlugin {
    public void onEnable() {
        getLogger().info("CustomizableEnchants Enabled!");

        saveDefaultConfig();
        reloadConfig();

        int pluginId = 14167;
        Metrics metrics = new Metrics(this, pluginId);
    }

    public void onDisable() {
        getLogger().info("CustomizableEnchants Disabled!");
    }
}
