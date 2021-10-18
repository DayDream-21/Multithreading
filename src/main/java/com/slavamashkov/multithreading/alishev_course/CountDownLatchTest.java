package com.slavamashkov.multithreading.alishev_course;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Processor(countDownLatch, i));
        }

        executorService.shutdown();

        countDownLatch.await();

        System.out.println("Latch has been opened, main thread is proceeding!");
    }
}

class Processor implements Runnable {
    private final CountDownLatch countDownLatch;
    private final int id;

    public Processor(CountDownLatch countDownLatch, int id) {
        this.countDownLatch = countDownLatch;
        this.id = id;
    }

    @Override
    public void run() {
        Random random = new Random();

        Thread.currentThread().setName("[ Thread " + id + " ]");

        int time = random.nextInt(1000);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName() + " finished after " + time + " ms");
    }
}
