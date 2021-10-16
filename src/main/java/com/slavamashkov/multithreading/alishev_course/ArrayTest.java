package com.slavamashkov.multithreading.alishev_course;

import java.util.*;

public class ArrayTest {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> integerList = Collections.synchronizedList(new ArrayList<>());

        Runnable writer1 = () -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("[ " + Thread.currentThread().getName() + " ]");
                integerList.add(random.nextInt(5)); // random number from 0 to 5
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writer2 = () -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("[ " + Thread.currentThread().getName() + " ]");
                integerList.add(random.nextInt(10-5) + 5); // random number from 5 to 10
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread writerThread1 = new Thread(writer1);
        writerThread1.setName("Writer 1");
        writerThread1.start();

        Thread writerThread2 = new Thread(writer2);
        writerThread2.setName("Writer 2");
        writerThread2.start();

        Runnable reader1 = () -> {
            while (writerThread1.isAlive() || writerThread2.isAlive()) {
                System.out.println("[ " + Thread.currentThread().getName() + " ]");
                System.out.println(integerList);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread readerThread1 = new Thread(reader1);
        readerThread1.setName("Reader ");
        readerThread1.start();

    }
}
