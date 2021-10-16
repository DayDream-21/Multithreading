package com.slavamashkov.multithreading.alishev_course;


public class SynchronizedTest {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest test = new SynchronizedTest();
        test.doWork();
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join(); // Wait till thread1 ends its work
        thread2.join(); // Wait till thread2 ends its work

        System.out.println(counter);
    }

    public synchronized void increment() {
        this.counter++;
    }
}
