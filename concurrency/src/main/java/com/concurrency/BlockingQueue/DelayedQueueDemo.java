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


}
