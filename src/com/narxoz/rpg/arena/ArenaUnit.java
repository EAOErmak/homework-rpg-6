package com.narxoz.rpg.arena;

public abstract class ArenaUnit {
    protected String name;
    protected int health;
    protected int attackPower;
    protected int maxHealth;

    public ArenaUnit(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;

    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getAttackPower() { return attackPower; }

    public int takeDamage(int amount) {
        if (!isAlive()) return 0;

        int actual = Math.min(health, amount);
        health -= actual;

        return actual;
    }

    public boolean isAlive() {
        return health > 0;
    }
}