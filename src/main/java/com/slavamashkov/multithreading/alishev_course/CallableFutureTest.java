package com.slavamashkov.multithreading.alishev_course;

import java.util.Random;
import java.util.concurrent.*;

public class CallableFutureTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> future = executorService.submit(() -> {
            Random random = new Random();

            System.out.println("Starting");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished");

            int randomValue = random.nextInt(10);

            if (randomValue < 5 ) {
                throw new Exception("Something wrong");
            }

            return randomValue;
        });

        executorService.shutdown();


        try {
            int result = future.get(); // wait till thread finish
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            Throwable ex = e.getCause();
            System.out.println(ex.getMessage());
        }
    }
}
