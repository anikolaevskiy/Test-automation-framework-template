package com.example.aqa.app.server;

import com.example.aqa.app.server.model.Something;
import com.example.aqa.app.server.model.Token;
import com.example.aqa.app.server.model.User;
import lombok.RequiredArgsConstructor;

/**
 * {@link RestApiClient} implementation backed by a Feign client.
 */
@RequiredArgsConstructor
public class FeignRestApiClient implements RestApiClient {

    private final ServerFeignClient serverFeignClient;

    @Override
    public Token auth(String username, String password) {
        return serverFeignClient.auth(new User(username, password));
    }

    @Override
    public Something getSomething() {
        return serverFeignClient.getSomething();
    }

    @Override
    public Something createSomething(String stringValue, int intValue) {
        return serverFeignClient.createSomething(new Something(stringValue, intValue));
    }
}
