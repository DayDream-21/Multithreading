package com.slavamashkov.multithreading.alishev_course;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerTest {
    private static BlockingQueue<Integer> integersQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread producerThread = new Thread(() -> {
            Thread.currentThread().setName("ProducerThread");

            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            Thread.currentThread().setName("ConsumerThread");

            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }

    private static void produce() throws InterruptedException {
        Random random = new Random();

        while (true) {
            System.out.println("[ " + Thread.currentThread().getName() + " ] ");
            integersQueue.put(random.nextInt(100));
        }
    }

    private static void consume() throws InterruptedException {
        Random random = new Random();

        while (true) {
            System.out.println("[ " + Thread.currentThread().getName() + " ] ");
            Thread.sleep(100);

            if (random.nextInt(10) == 5) {
                System.out.println(integersQueue.take());
                System.out.println("Queue size: " + integersQueue.size());
            }
        }
    }
}
