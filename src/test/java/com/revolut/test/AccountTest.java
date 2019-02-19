package com.revolut.test;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class AccountTest {

    Account acc1;
    Account acc2;

    @Before
    public void beforeTests() {
        acc1 = Account.addAccount(1, 100);
        acc2 = Account.addAccount(2, 200);
    }

    @Test(expected = InsufficientFund.class)
    public void testInsufficientFundTransfer() {

        Account acc1 = Account.getAccount("1");
        Account acc2 = Account.getAccount("2");

        Account.transfer("1", "2", 100);

        assertThat("account (1) balance is 0", (acc1.getAmount() + "").equals("0.0"));
        assertThat("account (2) balance is 300", (acc2.getAmount() + "").equals("300.0"));

        Account.transfer("1", "2", 100);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAccountTransfer() {
        Account.transfer("1", "3", 100);
    }
}
