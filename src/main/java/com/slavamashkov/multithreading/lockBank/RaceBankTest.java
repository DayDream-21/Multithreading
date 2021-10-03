package com.slavamashkov.multithreading.lockBank;

/**
 * This program shows how multiple threads can safely access a data structure,
 * using synchronized methods.
 */
public class RaceBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args) {
        var lockBank = new LockBank(NACCOUNTS, INITIAL_BALANCE);
        var syncBank = new SyncBank(NACCOUNTS, INITIAL_BALANCE);

        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (syncBank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        syncBank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException ignored) {}
            };

            var t = new Thread(r);
            t.start();
        }
    }
}
