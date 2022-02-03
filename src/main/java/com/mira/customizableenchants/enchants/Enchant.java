package com.mira.customizableenchants.enchants;

import com.mira.customizableenchants.CustomizableEnchants;
import com.mira.customizableenchants.mechanics.Mechanic;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class Enchant {
    CustomizableEnchants main = CustomizableEnchants.getPlugin(CustomizableEnchants.class);

    private String id;
    private String display;

    private EnchantType type;

    private int level;
    private int maxLevel;

    private String trigger;

    private Mechanic[] Mechanics;

    public Enchant(String id){
        String display = main.getConfig().getString("Enchants." + id + ".display");
        if(display != null && !display.trim().isEmpty()){
            this.id=id;

            this.display = display;

            this.type = EnchantType.matchType(main.getConfig().getString("Enchants." + id + ".type"));

            this.maxLevel = main.getConfig().getInt("Enchants." + id + ".max-level");

            this.trigger = main.getConfig().getString("Enchants." + id + ".trigger");

            if(!main.getConfig().getStringList("Enchants." + id + ".mechanics").isEmpty()){
                for(String text : main.getConfig().getStringList("Enchants." + id + ".mechanics")){
                    Mechanics[main.getConfig().getStringList("Enchants." + id + ".mechanics").indexOf(text)] = new Mechanic(text,this.level);
                }
            }

        } else this.id="error";
    }

    public void executeMechanics(@Nullable Player player, @Nullable Entity attacker){
        for(Mechanic mechanic: Mechanics){
            if(StringUtils.equalsIgnoreCase(mechanic.target,"SELF")){
                mechanic.executeMechanic(player);
            } else if(StringUtils.equalsIgnoreCase(mechanic.target,"TARGET")) {
                mechanic.executeMechanic(attacker);
            }
        }
    }

    public String getId(){
        return id;
    }
    public String getDisplay(){
        return display;
    }

    public EnchantType getType(){
        return type;
    }

    public String getTrigger(){
        return trigger;
    }

    public int getMaxLevel(){
        return maxLevel;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }

}
