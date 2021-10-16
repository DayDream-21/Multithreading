package com.slavamashkov.multithreading.alishev_course;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedTest {
    private AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest test = new SynchronizedTest();
        test.doWork();
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join(); // Wait till thread1 ends its work
        thread2.join(); // Wait till thread2 ends its work

        System.out.println(counter);
    }
}
