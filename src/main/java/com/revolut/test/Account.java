package com.revolut.test;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class Account {

    private static Map<String, Account> accountsMap = new HashMap<String, Account>();

    int id;
    double amount;

    public Account(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void withdraw(double amount) {
        if (this.amount < amount) throw new InsufficientFund("Insufficient funds");
        this.amount -= amount;
    }

    public void send(double amount) {
        this.amount += amount;
    }

    public static void transfer(String accId1, String accId2, double value) {
        Account acc1 = getAccount(accId1);
        Account acc2 = getAccount(accId2);

        if (acc1 == null || acc2 == null) {
            throws new InvalidParameterException("account not found");
        }

        Object lock1 = acc1.getId() < acc2.getId() ? acc1 : acc2;
        Object lock2 = acc1.getId() < acc2.getId() ? acc2 : acc1;

        synchronized (lock1) {
            synchronized (lock2) {
                acc1.withdraw(value);
                acc2.send(value);
            }
        }
    }

    public static Account getAccount(String accId1) {
        return accountsMap.get(accId1);
    }

    public static Account addAccount(int id, double amount) {
        Account value = new Account(id, amount);
        accountsMap.put("" + id, value);
        return value;
    }
}
