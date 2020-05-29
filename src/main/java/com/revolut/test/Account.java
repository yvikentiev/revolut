package com.revolut.test;

import java.util.HashMap;
import java.util.Map;

public class Account {

    private static Map<String, Account> accountsMap = new HashMap<String, Account>();

    int id;
    int balance;

    public Account(int id, int amount) {
        this.id = id;
        this.balance = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void transfer(String accId1, String accId2, int value) {
        Account acc1 = getAccount(accId1);
        Account acc2 = getAccount(accId2);

        if (acc1 == null || acc2 == null) {
            throw new IllegalArgumentException("account not found");
        }

        Object lock1 = acc1.getId() < acc2.getId() ? acc1 : acc2;
        Object lock2 = acc1.getId() < acc2.getId() ? acc2 : acc1;

        synchronized (lock1) {
            synchronized (lock2) {
                acc1.withdraw(value);
                acc2.deposit(value);
            }
        }
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        if (this.balance < amount) throw new InsufficientFund("Insufficient funds");
        this.balance -= amount;
    }

    public static Account addAccount(int id, int amount) {
        Account value = new Account(id, amount);
        accountsMap.put("" + id, value);
        return value;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public static Account getAccount(String accId1) {
        return accountsMap.get(accId1);
    }


}
