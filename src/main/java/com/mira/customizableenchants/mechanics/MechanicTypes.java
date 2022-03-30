package com.mira.customizableenchants.mechanics;

public enum MechanicTypes {
    POTION,
    COMMAND;

    public MechanicType getExecutor() {
        switch (this) {
            case POTION -> {
                return new PotionMechanicType();
            }
            case COMMAND -> {
                return new CommandMechanicType();
            }
            default -> {
                return null;
            }
        }
    }
}
