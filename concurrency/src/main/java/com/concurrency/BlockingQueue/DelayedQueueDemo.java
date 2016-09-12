package com.concurrency.BlockingQueue;

import com.concurrency.BlockingQueue.thread.Consumer;
import com.concurrency.BlockingQueue.thread.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


/**
 * Created by Uladzislau_Shalamits on 9/12/2016.
 */
public class DelayedQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue queue = new DelayQueue();

        new Thread(new Producer(queue)).start();
        Thread.sleep(1000);
        new Thread(new Consumer(queue)).start();
    }

    public static class Producer implements Runnable {
        protected DelayQueue queue = null;

        public Producer(DelayQueue queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                System.out.println("PUT : 3");

                Delayed element1 = new Delayed() {
                    @Override
                    public long getDelay(TimeUnit unit) {
                        return 1;
                    }

                    @Override
                    public int compareTo(Delayed o) {
                        return 0;
                    }
                };



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
}
