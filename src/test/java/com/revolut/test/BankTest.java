package com.revolut.test;

import org.junit.Test;

public class BankTest {

    @Test
    public void TransactionTest() {
        Bank bank = new Bank();

        try {
            for (int p = 0; p < 1000; p++) {
                for (int i = 0; i < Bank.MAX_ACCOUNT; i++) {
                    Thread t = new Thread(new Transaction(bank, i));
                    t.start();
                }
            }

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private class Transaction implements Runnable {
        private Bank bank;
        private int fromAccount;

        public Transaction(Bank bank, int fromAccount) {
            this.bank = bank;
            this.fromAccount = fromAccount;
        }

        public void run() {
            while (true) {
                try {

                    int toAccount = (int) (Math.random() * Bank.MAX_ACCOUNT);
                    if (toAccount == fromAccount) continue;
                    int amount = (int) (Math.random() * Bank.MAX_AMOUNT);
                    if (amount == 0) continue;
                    bank.transfer(fromAccount, toAccount, amount);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Throwable e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
