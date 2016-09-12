package com.concurrency.BlockingQueue;

import com.concurrency.BlockingQueue.thread.Consumer;
import com.concurrency.BlockingQueue.thread.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Uladzislau_Shalamits on 9/12/2016.
 */
public class LinkedBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();

        new Thread(new Producer(queue)).start();
        Thread.sleep(500);
        new Thread(new Consumer(queue)).start();
    }

}
