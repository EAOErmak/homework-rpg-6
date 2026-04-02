package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaOpponent;

public class AttackCommand implements ActionCommand {
    private final ArenaOpponent target;
    private final int attackPower;
    private int damageDealt;

    public AttackCommand(ArenaOpponent target, int attackPower) {
        this.target = target;
        this.attackPower = attackPower;
    }

    @Override
    public void execute() {
        damageDealt = 0;
        int actualDamageApplied = target.takeDamage(attackPower);
        if(actualDamageApplied == 0) {
            System.out.println(target.getName() + " is already dead, cannot take damage");
            return;
        }
        damageDealt = actualDamageApplied;
    }

    @Override
    public void undo() {
        if(damageDealt > 0) {
            target.restoreHealth(damageDealt);
        }
    }

    @Override
    public String getDescription() {
        return "Attack " + target.getName() + " for " + attackPower + " damage";
    }
}
