package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class BlockHandler extends DefenseHandler {
    private final double blockPercent;

    public BlockHandler(double blockPercent) {
        this.blockPercent = blockPercent;
    }

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        int blockedDamage = (int) (incomingDamage * blockPercent);
        blockedDamage = Math.min(blockedDamage, incomingDamage);

        int remainder = incomingDamage - blockedDamage;

        System.out.println("Blocked " + blockedDamage + " damage");

        passToNext(remainder, target);
    }
}
