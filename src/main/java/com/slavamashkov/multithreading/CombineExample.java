package com.slavamashkov.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CombineExample {
    public static void main(String[] args) {
        int x = 10;
        int t = p(x);

        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> a = service.submit(() -> {
            System.out.println(Thread.currentThread().getName() + ": ");
            return q1(t);
        });
        Future<Integer> b = service.submit(() -> {
            System.out.println(Thread.currentThread().getName() + ": ");
            return q2(t);
        });
        try {
            System.out.println(r(a.get(), b.get()));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        service.shutdown();
    }

    private static int p(int x) {
        return x * 2;
    }

    private static int q1(int t) {
        return t - 8;
    }

    private static int q2(int t) {
        return t + 2;
    }

    private static int r(int a, int b) {
        return a + b;
    }
}
