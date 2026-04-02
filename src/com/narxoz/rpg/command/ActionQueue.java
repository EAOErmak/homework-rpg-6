package com.narxoz.rpg.command;

import java.util.ArrayList;
import java.util.List;

public class ActionQueue {
    private final List<ActionCommand> queue = new ArrayList<>();

    public void enqueue(ActionCommand cmd) {
        queue.add(cmd);
    }

    public void undoLast() {
        if(queue.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        //Here I just could use removeLast()
        queue.remove(queue.size() - 1);
    }

    public void executeAll() {
        if(queue.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        queue.forEach(ActionCommand::execute);
        queue.clear();
    }

    public List<String> getCommandDescriptions() {
        return queue.stream()
                .map(ActionCommand::getDescription)
                .toList();
    }
}
