package com.narxoz.rpg.arena;

public class ArenaFighter extends ArenaUnit {
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
        if (healPotions < 1 || amount <= 0 || health >= maxHealth) return 0;

        int maxHeal = maxHealth - health;
        int actual = Math.min(maxHeal, amount);

        if (actual <= 0) return 0;

        health += actual;
        healPotions--;
        return actual;
    }

    public void modifyDodgeChance(double delta) {
        dodgeChance += delta;
        dodgeChance = Math.max(0.0, Math.min(1.0, dodgeChance));
    }
}
