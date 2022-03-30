package com.mira.customizableenchants.utils;

import com.mira.customizableenchants.CustomizableEnchants;
import com.mira.customizableenchants.enchants.EnchantType;
import com.mira.customizableenchants.enchants.Enchantment;
import com.mira.customizableenchants.trigger.TriggerType;
import com.mira.customizableenchants.mechanics.Mechanic;
import com.mira.customizableenchants.mechanics.MechanicTypes;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ConfigHelper {
    private static final CustomizableEnchants main = CustomizableEnchants.getPlugin(CustomizableEnchants.class);

    private static final HashMap<String, Enchantment> enchantments = new HashMap<>();
    private static final HashMap<String, Mechanic> mechanics = new HashMap<>();

    public static HashMap<String, Enchantment> getEnchantments() {
        return enchantments;
    }

    public static Enchantment getEnchantment(String id) {
        return enchantments.get(id);
    }

    public static HashMap<String, Mechanic> getMechanics() {
        return mechanics;
    }

    public static Mechanic getMechanic(String id) {
        return mechanics.get(id);
    }

    public static void load() {
        Set<String> mechs = main.getConfig().getConfigurationSection("Mechanics").getKeys(false);
        for (String mech : mechs) {
            ConfigurationSection section = main.getConfig().getConfigurationSection("Mechanics").getConfigurationSection(mech);
            String type = section.getString("type");
            String data = section.getString("data");

            Mechanic mechanic = new Mechanic(mech, MechanicTypes.valueOf(type), data);
            mechanics.put(mech, mechanic);
        }

        Set<String> enchants = main.getConfig().getConfigurationSection("Enchants").getKeys(false);
        for (String enchant : enchants) {
            ConfigurationSection section = main.getConfig().getConfigurationSection("Enchants").getConfigurationSection(enchant);

            List<String> mechas = section.getStringList("mechanics");
            List<Mechanic> mechaniclist = new ArrayList<>();
            for (String mech : mechas) mechaniclist.add(getMechanic(mech));

            Enchantment enchantment = new Enchantment(enchant, section.getString("display"), EnchantType.getType(section.getString("type")),
                    TriggerType.valueOf(section.getString("trigger")), section.getInt("max-level"), mechaniclist);
            enchantments.put(enchant, enchantment);
        }
    }
}
