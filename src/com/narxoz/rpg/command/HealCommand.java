package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class HealCommand implements ActionCommand {
    private final ArenaFighter target;
    private final int healAmount;
    private int actualHealApplied;

    public HealCommand(ArenaFighter target, int healAmount) {
        this.target = target;
        this.healAmount = healAmount;
    }

    @Override
    public void execute() {
        actualHealApplied = target.heal(healAmount);

        if (actualHealApplied == 0) {
            System.out.println(target.getName() + " cannot be healed!");
        }
        // TODO: Check whether the target has heal potions remaining before healing.
        // TODO: Heal the target by healAmount using target.heal(int).
        // TODO: Store how much was actually applied in actualHealApplied (for undo).
        // Hint: actual heal may be less than healAmount if target is near max health.
    }

    @Override
    public void undo() {
        if (actualHealApplied > 0) {
            target.takeDamage(actualHealApplied);
        }
        // TODO: Remove the heal that was applied.
        // Note: Use actualHealApplied (what was actually gained), not healAmount.
        // Hint: call target.takeDamage(actualHealApplied) to reverse the heal.
    }

    @Override
    public String getDescription() {
        // TODO: Return a readable summary, e.g. "Heal for 20 HP".
        return "Heal for " + healAmount + " HP";
    }
}
