package com.slavamashkov.multithreading.seminar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableTest {


    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        String abc = "abc";
        if (abc != null) {
            System.out.println("abc = " + abc);
        }

        Callable<String> c = () -> "Hello " + Thread.currentThread().getName();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            futures.add(service.submit(c));
        }
        for(Future<String> f : futures) {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        service.shutdown();
    }
}
