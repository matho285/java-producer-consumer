package com.mmaksin.java;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Command> queue = new LinkedBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(consumer).start();
        new Thread(producer).start();
    }
}
