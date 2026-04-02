package com.narxoz.rpg;

import com.narxoz.rpg.arena.ArenaFighter;
import com.narxoz.rpg.arena.ArenaOpponent;
import com.narxoz.rpg.arena.TournamentResult;
import com.narxoz.rpg.chain.ArmorHandler;
import com.narxoz.rpg.chain.BlockHandler;
import com.narxoz.rpg.chain.DefenseHandler;
import com.narxoz.rpg.chain.DodgeHandler;
import com.narxoz.rpg.chain.HpHandler;
import com.narxoz.rpg.command.ActionQueue;
import com.narxoz.rpg.command.AttackCommand;
import com.narxoz.rpg.command.DefendCommand;
import com.narxoz.rpg.command.HealCommand;
import com.narxoz.rpg.tournament.TournamentEngine;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 6 Demo: Chain of Responsibility + Command ===\n");

        ArenaFighter commandHero = new ArenaFighter("Gats", 100, 0.20, 25, 5, 18, 3);

        ArenaOpponent commandOpponent = new ArenaOpponent("Griffit", 90, 14);

        // -----------------------------------------------------------------------
        // Part 1 — Command Queue Demo
        // -----------------------------------------------------------------------
        System.out.println("--- Command Queue Demo ---");

        ActionQueue queue = new ActionQueue();

        commandHero.takeDamage(20);
        System.out.println("Hero HP before execute: " + commandHero.getHealth());
        System.out.println("Hero dodge before execute: " + commandHero.getDodgeChance());
        System.out.println("Opponent HP before execute: " + commandOpponent.getHealth());

        // Enqueue three hero actions.
        queue.enqueue(new AttackCommand(commandOpponent, commandHero.getAttackPower()));
        queue.enqueue(new HealCommand(commandHero, 20));
        queue.enqueue(new DefendCommand(commandHero, 0.15));

        System.out.println("Queued actions:");

        for (String desc : queue.getCommandDescriptions()) {
            System.out.println("  " + desc);
        }

        // Demonstrate undoLast() — removes the last queued command.
        System.out.println("\nUndoing last queued action...");
        queue.undoLast();

        System.out.println("Queue after undo:");
        for (String desc : queue.getCommandDescriptions()) {
            System.out.println("  " + desc);
        }

        System.out.println("\nExecuting remaining queued commands...");
        queue.executeAll();

        System.out.println("Hero HP after execute:  " + commandHero.getHealth());
        System.out.println("Hero dodge after execute: " + commandHero.getDodgeChance());
        System.out.println("Opponent HP after execute: " + commandOpponent.getHealth());

        // -----------------------------------------------------------------------
        // Part 2 — Defense Chain Demo
        // -----------------------------------------------------------------------
        System.out.println("\n--- Defense Chain Demo ---");

        ArenaFighter chainHero = new ArenaFighter("SpongeBob", 100, 0.20, 25, 5, 18, 3);

        // Build a standalone chain to prove it works independently.
        DefenseHandler dodge = new DodgeHandler(0.50, 99L);
        DefenseHandler block = new BlockHandler(0.30);
        DefenseHandler armor = new ArmorHandler(5);
        DefenseHandler hp    = new HpHandler();
        dodge.setNext(block).setNext(armor).setNext(hp);

        System.out.println("Sending 20 incoming damage through the defense chain...");
        System.out.println("Hero HP before: " + chainHero.getHealth());
        // TODO: Route 20 points of incoming damage through the chain.
        dodge.handle(20, chainHero);
        System.out.println("Hero HP after:  " + chainHero.getHealth());

        // -----------------------------------------------------------------------
        // Part 3 — Full Tournament Demo
        // -----------------------------------------------------------------------
        System.out.println("\n--- Full Arena Tournament ---");

        // TODO: Replace these placeholder names and stats with your own characters.
        ArenaFighter tournamentHero     = new ArenaFighter("Iron Man",    120, 0.25, 25, 8, 22, 3);
        ArenaOpponent tournamentOpponent = new ArenaOpponent("Thanos", 100, 16);

        TournamentResult result = new TournamentEngine(tournamentHero, tournamentOpponent)
                .setRandomSeed(42L)
                .runTournament();

        System.out.println("Winner : " + result.getWinner());
        System.out.println("Rounds : " + result.getRounds());
        System.out.println("Battle log:");
        for (String line : result.getLog()) {
            System.out.println("  " + line);
        }

        System.out.println("\n=== Demo Complete ===");
    }
}
