package com.slavamashkov.multithreading.alishev_course;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerTest {
    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        Thread producerThread = new Thread(() -> {
            Thread.currentThread().setName("ProducerThread");

            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            Thread.currentThread().setName("ConsumerThread");

            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }
}

class ProducerConsumer {
    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object(); // Object for locking

    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock) {
                while (queue.size() == LIMIT) {
                    lock.wait();
                }

                queue.offer(value++); // Thread-unsafe method
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (queue.size() == 0) {
                    lock.wait();
                }

                int value = queue.poll();
                System.out.println(value);
                System.out.println("Queue size: " + queue.size());
                lock.notify();
            }

            Thread.sleep(1000);
        }
    }
}
