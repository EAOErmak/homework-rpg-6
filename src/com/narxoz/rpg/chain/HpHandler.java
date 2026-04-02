package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class HpHandler extends DefenseHandler {
    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        int actualDamage = target.takeDamage(incomingDamage);
        System.out.println(target.getName() + " took " + actualDamage + " damage");
    }
}
