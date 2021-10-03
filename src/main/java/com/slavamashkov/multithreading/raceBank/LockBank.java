package com.slavamashkov.multithreading.raceBank;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A bank with a number of bank accounts that uses Lock class
 */
public class LockBank implements Bank{
    private final Lock bankLock = new ReentrantLock();
    private final Condition sufficientFunds;

    private final double[] accounts;

    /**
     * Constructs the bank.
     *
     * @param n              the number of accounts
     * @param initialBalance the initial balance for each account
     */
    public LockBank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);

        sufficientFunds = bankLock.newCondition();
    }

    /**
     * Transfers money from one account to another.
     *
     * @param from   the account to transfer from
     * @param to     the account to transfer to
     * @param amount the amount to transfer
     */
    public void transfer(int from, int to, double amount) throws InterruptedException{
        bankLock.lock();
        try {
            while (accounts[from] < amount) {
                sufficientFunds.await();
            }
                System.out.print(Thread.currentThread());
                accounts[from] -= amount;
                System.out.printf(" %10.2f from %d to %d", amount, from, to);
                accounts[to] += amount;
                System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
                getBalanceOfAcc(from, to);

                sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }
    }

    /**
     * Gets the sum of all account balances.
     *
     * @return the total balance
     */
    public synchronized double getTotalBalance() {
        double sum = 0;

        for (double a : accounts)
            sum += a;

        return sum;
    }

    /**
     * Gets the number of accounts in the bank.
     *
     * @return the number of accounts
     */
    public int size() {
        return accounts.length;
    }

    public void getBalanceOfAcc(int from, int to) {
        DecimalFormat df = new DecimalFormat("#.##");

        double fromAmount = accounts[from];
        double toAmount = accounts[to];

        System.out.println("From-acc: " + df.format(fromAmount));
        System.out.println("To-acc: " + df.format(toAmount));
    }
}
