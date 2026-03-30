package com.narxoz.rpg.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActionQueue {
    private final List<ActionCommand> queue = new ArrayList<>();

    public void enqueue(ActionCommand cmd) {
        queue.add(cmd);
    }

    public void undoLast() {
        if (queue.isEmpty()){
            System.out.println("Queue is empty");
            return;
        }
        queue.remove(queue.size() - 1);
        // TODO: Remove the last queued command without executing it.
        // Design question: should cmd.undo() be called, or is it simply removed?
        // For this assignment: just remove the last entry from the queue.
        // Hint: List has a remove(int index) method — think about which index is "last".
    }

    public void executeAll() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        queue.forEach(ActionCommand :: execute);
        queue.clear();
        // TODO: Call execute() on every command in the queue, in order.
        // TODO: Clear the queue after all commands have run.
        // TODO: What should happen if the queue is empty?
    }

    public List<String> getCommandDescriptions() {
        // TODO: Return a snapshot list of descriptions for all queued commands.
        // TODO: Use cmd.getDescription() for each command.
        // Note: the returned list must be independent — modifying it must not affect the queue.
        return queue.stream()
                .map(ActionCommand::getDescription)
                .toList();
    }
}
