package com.mira.customizableenchants.enchants;

import com.mira.customizableenchants.CustomizableEnchants;
import com.mira.customizableenchants.mechanics.Mechanic;

public class Enchant {
    CustomizableEnchants main = CustomizableEnchants.getPlugin(CustomizableEnchants.class);

    public String id;
    public String display;

    public EnchantType type;

    public int maxLevel;

    public Mechanic[] Mechanics;

    public Enchant(String id){
        String display = main.getConfig().getString("Enchants." + id + "display");
        if(display != null && !display.trim().isEmpty()){
            this.id=id;

            this.display = main.getConfig().getString("Enchants." + id + "display");

            this.type = EnchantType.matchType(main.getConfig().getString("Enchants." + id + "type"));

            this.maxLevel = main.getConfig().getInt("Enchants." + id + "max-level");

        } else this.id="error";
    }

    public void executeMechanic(Mechanic mechanic){

    }

}
