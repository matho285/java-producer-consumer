package com.mmaksin.java;

import com.mmaksin.java.commands.Add;
import com.mmaksin.java.beans.User;
import com.mmaksin.java.commands.Delete;
import com.mmaksin.java.commands.PrintAll;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Command> queue;

    public Producer(BlockingQueue<Command> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            User user = new User(1, "ad1", "Robert");
            DatabaseManager databaseManager = new DatabaseManager();
            queue.put(new Add(databaseManager, user));

            User user2 = new User(2, "ad2", "Martin");
            queue.put(new Add(databaseManager, user2));

            queue.put(new PrintAll(databaseManager));
            queue.put(new Delete(databaseManager));
            queue.put(new PrintAll(databaseManager));
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
