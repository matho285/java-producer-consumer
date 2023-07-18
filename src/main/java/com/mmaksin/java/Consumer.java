package com.mmaksin.java;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Command> queue;

    public Consumer(BlockingQueue<Command> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int i = 0;

        try {
            while (i < 10) {
                Command item = queue.take();
                item.execute();
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
