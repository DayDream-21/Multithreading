package com.slavamashkov.multithreading.completable_future_features;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ShopTest {
    public static void main(String[] args) {
        Shop shop = new Shop("My shop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsyncWithSupply("my product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        doSomethingElse();

        try {
            double price = futurePrice.get(2, TimeUnit.SECONDS);
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static void doSomethingElse() {
        System.out.println("Something else");
    }
}
