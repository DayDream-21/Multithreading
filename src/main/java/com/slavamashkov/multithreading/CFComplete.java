package com.slavamashkov.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CFComplete {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        int x = 1337;

        CompletableFuture<Integer> a = new CompletableFuture<>();
        service.submit(() -> a.complete(f(x)));

        int b = g(x);
        System.out.println(a.get() + b);

        service.shutdown();
    }

    private static int f(int x) {
        return x + 1;
    }

    private static int g(int x) {
        return x + 2;
    }
}
