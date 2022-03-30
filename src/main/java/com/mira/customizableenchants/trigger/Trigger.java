package com.mira.customizableenchants.trigger;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Optional;

public record Trigger(TriggerType type, Player player, Optional<Entity> triggeredBy) {
}
