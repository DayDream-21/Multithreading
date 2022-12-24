package com.slavamashkov.multithreading.completable_future_features;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        /*long start = System.nanoTime();
        System.out.println(new BestPriceTest().findPricesFutures("iPhone100XS"));
        long duration = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Done in " + duration + " msecs");*/

        long start = System.nanoTime();
        CompletableFuture[] futures = new BestPriceTest().findPricesStream("iPhone")
                .map(f ->
                        f.thenAccept(s ->
                                System.out.println(s +
                                        " (done in " +
                                        ((System.nanoTime() - start) / 1_000_000) +
                                        " msecs)"
                                )
                        )
                )
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
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
                                shop.getPrice(product),
                                executor
                            )
                        )
                        .map(future -> future.thenApply(Quote::parse))
                        .map(future -> future.thenCompose(quote ->
                                CompletableFuture.supplyAsync(() ->
                                    Discount.applyDiscount(quote),
                                    executor
                                )
                            )
                        )
                        .collect(Collectors.toList());

        return pricesFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() ->
                                shop.getPrice(product),
                                executor
                            )
                        )
                        .map(future -> future.thenApply(Quote::parse))
                        .map(future -> future.thenCompose(quote ->
                                CompletableFuture.supplyAsync(() ->
                                    Discount.applyDiscount(quote),
                                    executor
                                )
                            )
                        );
    }
}
