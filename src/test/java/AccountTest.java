import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class AccountTest {
    @Test
    public void testTransfer() {

        Account.transfer("1", "2", 100);

        Account acc1 = Account.getAccount("1");
        Account acc2 = Account.getAccount("2");

        assertThat("amount is 0", (acc1.getAmount() + "").equals("0.0"));
        //assertThat("ammount is 200", acc2.getAmount() == 200);
    }
}
