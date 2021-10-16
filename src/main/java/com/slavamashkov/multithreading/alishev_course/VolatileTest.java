package com.slavamashkov.multithreading.alishev_course;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class VolatileTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);

        VolatileThread myThread = new VolatileThread();

        myThread.start();

        scanner.nextLine();

        myThread.shutdown();
    }
}

class VolatileThread extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}

