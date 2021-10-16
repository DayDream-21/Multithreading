package com.slavamashkov.multithreading.alishev_course;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2); // Thread pool

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Work(i)); // Give 5 tasks to executor service
        }

        executorService.shutdown(); // Stop giving tasks and start execute them
        System.out.println("All tasks submitted");

        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

class Work implements Runnable {
    private final int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Work " + id + " was completed");
    }
}


