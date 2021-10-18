package com.slavamashkov.multithreading.alishev_course;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        Connection connection = Connection.getConnection();


        for (int i = 0; i < 200; i++) {
            executorService.submit(() -> {
                try {
                    connection.work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

class Connection {
    private static Connection connection;
    private int connectionsCount;
    private Semaphore semaphore = new Semaphore(10);

    private Connection() {

    }

    public static Connection getConnection() {
        if (connection == null) {
            connection = new Connection();
        }
        return connection;
    }

    // work with limit (only 10 threads can exist in the same time)
    public void work() throws InterruptedException {
        semaphore.acquire();
        try {
            doWork();
        } finally {
            semaphore.release();
        }
    }

    private void doWork() {
        synchronized (this) {
            connectionsCount++;
            System.out.println(connectionsCount);
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connectionsCount--;
        }
    }
}