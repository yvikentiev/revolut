package com.revolut.test;

public class InsufficientFund extends  RuntimeException {
    public InsufficientFund(String message) {
        super(message);
    }
}
