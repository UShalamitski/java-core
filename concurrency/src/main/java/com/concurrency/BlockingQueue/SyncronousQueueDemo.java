package com.concurrency.BlockingQueue;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by Uladzislau_Shalamits on 9/12/2016.
 */
public class SyncronousQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        new Thread(new Producer(queue)).start();
        Thread.sleep(1000);
        new Thread(new Consumer(queue)).start();
    }

    public static class Consumer implements Runnable {
        protected SynchronousQueue<Integer> queue = null;

        public Consumer(SynchronousQueue queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                System.out.println("TAKE : " + queue.take());
                System.out.println("TAKE : " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static class Producer implements Runnable {
        protected SynchronousQueue queue = null;

        public Producer(SynchronousQueue queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                System.out.println("PUT : 10");
                queue.put(10);
                System.out.println("PUT : 20");
                queue.put(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
