package com.concurrency.BlockingQueue;

import com.concurrency.BlockingQueue.thread.Consumer;
import com.concurrency.BlockingQueue.thread.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Uladzislau_Shalamits on 9/12/2016.
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

        new Thread(new Producer(queue)).start();
        Thread.sleep(1000);
        new Thread(new Consumer(queue)).start();
    }
}
