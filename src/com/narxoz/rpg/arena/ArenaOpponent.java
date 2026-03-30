package com.narxoz.rpg.arena;

public class ArenaOpponent extends ArenaUnit{

    public ArenaOpponent(String name, int health, int attackPower) {
       super(name, health, attackPower);
    }

    public void restoreHealth(int amount) {
        health = Math.min(maxHealth, health + amount);
    }
}
