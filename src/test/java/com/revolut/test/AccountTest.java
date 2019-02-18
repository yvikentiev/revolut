package com.revolut.test;

import com.revolut.test.Account;
import com.revolut.test.InsufficientFund;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class AccountTest {

    @Test(expected = InsufficientFund.class)
    public void testTransfer() {
        Account acc1 = Account.addAccount(1,100);
        Account acc2 = Account.addAccount(2,200);

        Account.transfer("1", "2", 100);

        assertThat("account (1) balance is 0", (acc1.getAmount() + "").equals("0.0"));
        assertThat("account (2) balance is 300", (acc2.getAmount() + "").equals("300.0"));

        Account.transfer("1", "2", 100);

        //assertThat("ammount is 200", acc2.getAmount() == 200);
    }
}
