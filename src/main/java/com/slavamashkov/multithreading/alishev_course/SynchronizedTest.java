package com.slavamashkov.multithreading.alishev_course;


public class SynchronizedTest {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest test = new SynchronizedTest();
        test.doWork();
    }

    public void doWork() {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        });

        thread1.start();
        thread2.start();

        System.out.println(counter);
    }
}
