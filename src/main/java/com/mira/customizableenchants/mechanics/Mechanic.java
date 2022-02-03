package com.mira.customizableenchants.mechanics;

import com.mira.customizableenchants.CustomizableEnchants;
import com.mira.customizableenchants.utils.MathUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Mechanic {
    CustomizableEnchants main = CustomizableEnchants.getPlugin(CustomizableEnchants.class);

    public String type;

    public int level;

    // Potion
    public PotionEffectType effect;
    public int duration;
    public int amplifier;

    public String target;

    public Mechanic(String id, int level){
        this.level = level;

        String type = main.getConfig().getString("Mechanics." + id + ".type");

        if(type != null && !type.trim().isEmpty()){
            if(type.equals("POTION")){
                String input = main.getConfig().getString("Mechanics." + id + ".effect");

                String[] args = input.split(" ");

                this.effect = PotionEffectType.getByName(args[0]);

                this.duration = MathUtils.evaluateQuestion(args[1],level);
                this.amplifier = MathUtils.evaluateQuestion(args[2],level);

                this.target = args[3];
            }
        }
    }

    public void executeMechanic(Entity target){
        if(type.equals("POTION")){
            PotionEffect potion = new PotionEffect(effect,duration,amplifier);
            ((LivingEntity) (target)).addPotionEffect(potion);
        }
    }


}
