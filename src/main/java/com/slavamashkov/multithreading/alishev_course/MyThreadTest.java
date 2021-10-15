package com.slavamashkov.multithreading.alishev_course;

import java.util.Random;

public class MyThreadTest {
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();

        myThread1.start();
        myThread2.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            try {
                int time = random.nextInt(100);
                Thread.sleep(time);
                System.out.println("Hello from MyThread #" + i +
                        " I'm going to sleep for " + time + "millis");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
