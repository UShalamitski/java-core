package com.concurrency.BlockingQueue.thread;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Uladzislau_Shalamits on 9/12/2016.
 */
public class Producer<T> implements Runnable {
    protected BlockingQueue<Integer> queue = null;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println("PUT : 3");
            queue.put(3);

            System.out.println("PUT : 1");
            queue.put(1);

            System.out.println("PUT : 2");
            queue.put(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

