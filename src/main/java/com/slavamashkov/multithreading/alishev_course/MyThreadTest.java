package com.slavamashkov.multithreading.alishev_course;

public class MyThreadTest {
    public static void main(String[] args) {
        Runnable task1 = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hello from task1");
            }
        };

        Thread thread = new Thread(task1);
        thread.start();

        MyThread myThread = new MyThread();
        myThread.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello from MyThread");
        }
    }
}
