package com.concurrency.BlockingQueue.thread;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Uladzislau_Shalamits on 9/12/2016.
 */
public class Consumer implements Runnable {
    protected BlockingQueue<Integer> queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println("TAKE : " + queue.take());
            System.out.println("TAKE : " + queue.take());
            System.out.println("TAKE : " + queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
