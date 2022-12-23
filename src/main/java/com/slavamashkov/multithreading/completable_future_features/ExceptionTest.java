package com.slavamashkov.multithreading.completable_future_features;

import java.util.concurrent.*;

public class ExceptionTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Double> future = service.submit(ExceptionTest::doSomeLongComputation);
        doSomethingElse();

        try {
            Double result = future.get(2, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.out.println("Time is out");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        service.shutdown();
    }

    private static void doSomethingElse() {
        System.out.println("Something else");
    }

    private static Double doSomeLongComputation() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return 0.0;
    }
}
