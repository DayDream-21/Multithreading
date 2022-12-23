package com.slavamashkov;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SynchronizedMethods {
    private int sum = 0;
    static int staticSum = 0;

    public void calculate() {
        setSum(getSum() + 1);
    }

    public synchronized void syncCalculate() {
        setSum(getSum() + 1);
    }

    public static synchronized void syncStaticCalculate() {
        staticSum = staticSum + 1;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
