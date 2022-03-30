package com.mira.customizableenchants.mechanics;

import com.mira.customizableenchants.trigger.MechanicTrigger;
import com.mira.customizableenchants.utils.MathUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionMechanicType implements MechanicType {
    @Override
    public void execute(MechanicTrigger trigger) {
        String[] args = trigger.mechanic().data().split(" ");
        PotionEffectType effect = PotionEffectType.getByName(args[0]);
        int duration = MathUtils.evaluateQuestion(args[1], trigger.level());
        int amplifier = MathUtils.evaluateQuestion(args[2], trigger.level());

        if (effect == null) return;

        PotionEffect potion = new PotionEffect(effect, duration, amplifier);

        if (args[3].equalsIgnoreCase("target") && trigger.entity().isPresent()) {
            LivingEntity entity = (LivingEntity) trigger.entity().get();
            entity.addPotionEffect(potion);
        } else {
            trigger.player().addPotionEffect(potion);
        }
    }
}
