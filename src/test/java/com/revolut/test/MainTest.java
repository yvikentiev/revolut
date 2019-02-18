package com.revolut.test;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;

public class MainTest {
    @Test
    public void testSparkServerRule() {
        Client client = ClientBuilder.newBuilder().build();
        Response response = client.target(URI.create("http://localhost:8080/transfer?account1=1&account2=2&amount=1"))
                .request()
                .get();

        assertThat("status is 200", response.getStatus() == 200);
        assertThat("transfer status", response.readEntity(String.class).equals("transfered"));
    }
}
