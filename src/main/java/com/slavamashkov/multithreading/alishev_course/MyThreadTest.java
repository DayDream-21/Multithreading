package com.slavamashkov.multithreading.alishev_course;

import java.util.Random;

public class MyThreadTest {
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread("First");
        MyThread myThread2 = new MyThread("Second");

        myThread1.start();
        myThread2.start();
    }
}

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            try {
                int time = random.nextInt(100);
                Thread.sleep(time);
                System.out.println("[" + currentThread().getName() + "]" +
                        " I'm going to sleep for " + time + "millis");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
