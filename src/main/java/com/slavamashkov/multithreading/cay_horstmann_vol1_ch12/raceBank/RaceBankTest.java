package com.slavamashkov.multithreading.cay_horstmann_vol1_ch12.raceBank;

/**
 * This program shows how multiple threads can safely access a data structure,
 * using synchronized methods.
 */
public class RaceBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 800;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args) {
        Bank lockBank = new LockBank(NACCOUNTS, INITIAL_BALANCE);
        Bank syncBank = new SyncBank(NACCOUNTS, INITIAL_BALANCE);

        createAndStartThread(lockBank);
    }

    private static void createAndStartThread(Bank bank) {
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException ignored) {}
            };

            var t = new Thread(r);
            t.start();
        }
    }
}
