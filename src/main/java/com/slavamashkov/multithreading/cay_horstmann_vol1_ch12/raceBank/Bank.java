package com.slavamashkov.multithreading.cay_horstmann_vol1_ch12.raceBank;

public interface Bank {
    /**
     * Transfers money from one account to another.
     *
     * @param from   the account to transfer from
     * @param to     the account to transfer to
     * @param amount the amount to transfer
     */
    void transfer(int from, int to, double amount) throws InterruptedException;

    /**
     * Gets the sum of all account balances.
     *
     * @return the total balance
     */
    double getTotalBalance();

    /**
     * Gets the number of accounts in the bank.
     *
     * @return the number of accounts
     */
    int size();

    void getBalanceOfAcc(int from, int to);
}
