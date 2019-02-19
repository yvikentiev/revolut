package com.revolut.test;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.net.URI;

import static com.revolut.test.Main.main;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainTest {

    Account acc1;
    Account acc2;


    @Before
    public void beforeTest() {
        acc1 = Account.addAccount(1, 100);
        acc2 = Account.addAccount(2, 200);

        main(null);
    }

    @Test
    public void testServer() {

        Client client = ClientBuilder.newBuilder().build();
        Response response = client.target(URI.create("http://localhost:8080/transfer?account1=1&account2=2&amount=1"))
                .request()
                .get();

        assertThat("status is 200", response.getStatus() == 200);
    }
}
