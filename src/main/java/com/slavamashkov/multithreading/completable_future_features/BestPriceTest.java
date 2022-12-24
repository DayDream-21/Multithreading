package com.slavamashkov.multithreading.completable_future_features;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class BestPriceTest {
    List<Shop> shops = List.of(
            new Shop("Best prices"),
            new Shop("Lets save big"),
            new Shop("Huge sales"),
            new Shop("All for ..."),
            new Shop("Min prices"),
            new Shop("Almost free"),
            new Shop("My fav shop"),
            new Shop("Mvideo"),
            new Shop("Yandex"),
            new Shop("Ozon"),
            new Shop("Amazon"),
            new Shop("Ebay"),
            new Shop("Comp universe")
    );

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(new BestPriceTest().findPricesFutures("iPhone100XS"));
        long duration = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Done in " + duration + " msecs");
    }

    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f \n", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f \n", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), (Runnable r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public List<String> findPricesFutures(String product) {
        List<CompletableFuture<String>> pricesFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() ->
                                        String.format("%s price is %.2f \n", shop.getName(), shop.getPrice(product)),
                                        executor
                                )
                        )
                        .collect(Collectors.toList());

        return pricesFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
