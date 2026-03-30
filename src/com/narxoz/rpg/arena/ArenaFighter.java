package com.narxoz.rpg.arena;

public class ArenaFighter extends ArenaUnit{
    private double dodgeChance;
    private final int blockRating;
    private final int armorValue;
    private int healPotions;

    public ArenaFighter(String name, int health, double dodgeChance,
                        int blockRating, int armorValue, int attackPower, int healPotions) {

        super(name, health, attackPower);
        this.dodgeChance = dodgeChance;
        this.blockRating = blockRating;
        this.armorValue = armorValue;
        this.healPotions = healPotions;
    }


    public double getDodgeChance() { return dodgeChance; }
    public int getBlockRating() { return blockRating; }
    public int getArmorValue() { return armorValue; }
    public int getHealPotions() { return healPotions; }

    public int heal(int amount) {
        // TODO: Increase health by amount; do not exceed maxHealth.
        // TODO: Decide what happens when healPotions runs out — should healing be blocked?

        if (healPotions < 1) return 0;

        int maxHeal = maxHealth - health;
        int actual  = Math.min(maxHeal, amount);

        health += actual;
        healPotions--;

        return actual;
    }

    public void modifyDodgeChance(double delta) {
        // TODO: Add delta to dodgeChance.
        // TODO: Decide whether to clamp dodgeChance between 0.0 and 1.0.
        dodgeChance += delta;
        dodgeChance = Math.max(0.0, Math.min(1.0, dodgeChance));
    }

    public boolean isAlive() {
        return health > 0;
    }
}
