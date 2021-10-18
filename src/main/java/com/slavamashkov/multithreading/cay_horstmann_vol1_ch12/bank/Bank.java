package com.slavamashkov.multithreading.cay_horstmann_vol1_ch12.bank;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Bank {
    private final double[] accounts;

    /**
     * Constructs the bank.
     *
     * @param n              the number of accounts
     * @param initialBalance the initial balance for each account
     */
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    /**
     * Transfers money from one account to another.
     *
     * @param from   the account to transfer from
     * @param to     the account to transfer to
     * @param amount the amount to transfer
     */
    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) return;
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        getBalanceOfAcc(from, to);
    }

    /**
     * Gets the sum of all account balances.
     *
     * @return the total balance
     */
    public double getTotalBalance() {
        double sum = 0;

        for (double a : accounts)
            sum += a;

        return sum;
    }

    public void getBalanceOfAcc(int from, int to) {
        DecimalFormat df = new DecimalFormat("#.##");

        double fromAmount = accounts[from];
        double toAmount = accounts[to];

        System.out.println("From-acc: " + df.format(fromAmount));
        System.out.println("To-acc: " + df.format(toAmount));
    }

    /**
     * Gets the number of accounts in the bank.
     *
     * @return the number of accounts
     */
    public int size() {
        return accounts.length;
    }
}
