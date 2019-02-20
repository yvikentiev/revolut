package com.revolut.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    public static final int MAX_ACCOUNT = 10;
    public static final int MAX_AMOUNT = 10;
    public static final int INITIAL_BALANCE = 100;

    private Account[] accounts = new Account[MAX_ACCOUNT];
    private Lock[] accountLock = new ReentrantLock[MAX_ACCOUNT];
    private Lock bankLock;

    public Bank() {
        Lock singleLock = new ReentrantLock();
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, INITIAL_BALANCE);
            accountLock[i] = new ReentrantLock();
        }
        bankLock = new ReentrantLock();
    }

    public void transfer(int from, int to, int amount) {

        Lock lock1 = from < to ? accountLock[from] : accountLock[to];
        Lock lock2 = from < to ? accountLock[to] : accountLock[from];
        lock1.lock();
        try {
            lock2.lock();
            try {

                if (amount <= accounts[from].getBalance()) {
                    accounts[from].withdraw(amount);
                    accounts[to].deposit(amount);

                    String message = "%s transfered %d from %s to %s. balance %s, Total balance: %d\n";
                    String threadName = Thread.currentThread().getName();
                    System.out.printf(message, threadName, amount, from, to,
                            accounts[from].getBalance(),
                            accounts[to].getBalance());
                    //getTotalBalance());
                }
            } finally {
                lock2.unlock();
            }
        } finally {
            lock1.unlock();
        }
    }

    public int getTotalBalance() {
        int total = 0;
        bankLock.lock();
        try {
            for (int i = 0; i < accounts.length; i++) {
                total += accounts[i].getBalance();
            }
            return total;
        } finally {
            bankLock.unlock();
        }
    }
}
