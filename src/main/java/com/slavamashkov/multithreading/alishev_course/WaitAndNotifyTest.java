package com.slavamashkov.multithreading.alishev_course;

import java.util.Scanner;

public class WaitAndNotifyTest {
    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify wn = new WaitAndNotify();


        Thread producerThread = new Thread(() -> {
            try {
                wn.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                wn.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }
}

class WaitAndNotify {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread started...");
            wait(); // To go further we need:
                    // 1 - wait till the notify method is called
                    // 2 - intrinsic lock returned
            System.out.println("Producer thread resumed...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);

        synchronized (this) {
            System.out.println("Waiting for \"return\" key pressed");
            scanner.nextLine();
            this.notify(); // calling notify method

            Thread.sleep(5000); // After 5 sec return intrinsic lock back to the produce method
        }
    }
}
